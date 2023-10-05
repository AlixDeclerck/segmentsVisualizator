package be.umons.BSPHI.domain;


import be.umons.BSPHI.domain.heuristic.Heuristic;
import be.umons.BSPHI.domain.shape.Line;
import be.umons.BSPHI.domain.shape.Point;
import be.umons.BSPHI.domain.shape.Segment;
import be.umons.BSPHI.domain.shape.SegmentList;

import static be.umons.BSPHI.domain.shape.Line.getIntersection;

public class BSPNode {

	private boolean leaf;

	private Line cuttingLine;
	private SegmentList segments;
	
	/**
	 * The node's left child
	 */
	private BSPNode left;
	
	/**
	 * The node's right child
	 */
	private BSPNode right;

	/**
	 * Recursively builds the BSP tree 
	 * @param segments A list of segments
	 * @param heuristic The building heuristic
	 */
	public BSPNode(SegmentList segments, Heuristic heuristic) {

		if (segments.size() < 2) {
			this.segments = segments;
			leaf=true;
		} 
		else {
			this.segments = new SegmentList();
			leaf = false;
			
			int indexCuttingSegment = heuristic.getIndexCuttingSegment(segments);

			cuttingLine = new Line(segments.get(indexCuttingSegment));
			SegmentList aboveList = new SegmentList();
			SegmentList underList = new SegmentList();

			for (int i=0; i<segments.size(); i++) {
				Segment segment = segments.get(i);

				if (segmentCut(indexCuttingSegment, i, segment)) this.segments.add(segment);
				else if (segmentAboveCuttingLine(segment)) aboveList.add(segment);
				else if (segmentUnderCuttingLine(segment)) underList.add(segment);
				else {
					Point intersect = getIntersection(cuttingLine, new Line(segment));
					if (pointAboveCuttingLine(segment.getP1())) {
						underList.add(new Segment(segment.getP2(), intersect, segment.getColor()));
						aboveList.add(new Segment(intersect, segment.getP1(), segment.getColor()));
					} else {
						underList.add(new Segment(segment.getP1(), intersect, segment.getColor()));
						aboveList.add(new Segment(intersect, segment.getP2(), segment.getColor()));
					}
				}
			}
			left = new BSPNode(underList, heuristic);
			right = new BSPNode(aboveList, heuristic);
		}
	}

	public boolean isLeaf() {
		return leaf;
	}
	
	/**
	 * don't need  to process the segment that was choose by heuristic
	 * @param indexCuttingSegment the index of the segment used to generate the cutting line (
	 * @param i the index of the segment in the segmentList
	 * @param segment
	 * @return true if the segment indica
	 */
	boolean segmentCut(int indexCuttingSegment, int i, Segment segment) {
		return i == indexCuttingSegment || cuttingLine.equals(new Line(segment));
	}

	boolean segmentUnderCuttingLine(Segment segment) {
		return pointUnderCuttingLine(segment.getP1()) && pointUnderCuttingLine(segment.getP2());
	}

	boolean segmentAboveCuttingLine(Segment segment) {
		return pointAboveCuttingLine(segment.getP1()) && pointAboveCuttingLine(segment.getP2());
	}

	private boolean pointAboveCuttingLine(Point p1) {
		return p1.isAbove(cuttingLine);
	}

	private boolean pointUnderCuttingLine(Point p1) {
		return p1.isUnder(cuttingLine);
	}
	
	public Line getCuttingLine() {
		return cuttingLine;
	}

	public SegmentList getSegments() {
		return segments;
	}

	public BSPNode getLeftChild() {
		return left;
	}

	public BSPNode getRightChild() {
		return right;
	}
	
	/**
	 * Used for the tests
	 */
	public void setCuttingLine(Line cuttingLine) {
		this.cuttingLine = cuttingLine;
	}
	
	public int getNodesCount() {
		if (leaf)
			return 1;
		return left.getNodesCount() + 1 + right.getNodesCount();
	}

	public int getHeight() {
		if (leaf)
			return 0;
		return 1+Math.max(left.getHeight(), right.getHeight());
	}
}
