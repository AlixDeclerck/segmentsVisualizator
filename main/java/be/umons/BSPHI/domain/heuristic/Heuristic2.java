package be.umons.BSPHI.domain.heuristic;

import be.umons.BSPHI.domain.shape.SegmentList;

/**
 * This heuristic always choose the first segment of the list as cutting segment.
 */
public class Heuristic2 implements Heuristic {

	@Override
	public int getIndexCuttingSegment(SegmentList list) {
		return 0;
	}
	
}
