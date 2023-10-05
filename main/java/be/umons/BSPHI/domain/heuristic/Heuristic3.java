package be.umons.BSPHI.domain.heuristic;

import be.umons.BSPHI.domain.shape.Line;
import be.umons.BSPHI.domain.shape.Segment;
import be.umons.BSPHI.domain.shape.SegmentList;

/**
 * This heuristic choose the index d that maximize (fdPlus*fdMinus - wValue*fd)  where:
 * - fdPlus is the number of segments above d
 * - fdMinus is the number of segments under d
 * - fd is the number of segments intersected by d
 * - The w value is a weight to adjust
 */
public class Heuristic3 implements Heuristic {
	
	private int wValue, maxIndex;

	public Heuristic3(int value) {
		wValue = value;
	}

	@Override
	public int getIndexCuttingSegment(SegmentList list) {
		maxIndex = 0;
		int max = getHeuristicCost(list, 0);
		for (int i=1; i<list.size(); i++) {
			int cost = getHeuristicCost(list, i);
			if (cost > max) {
				max = cost;
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	/**
	 * Compute the value of fdPlus*fdMinus - wValue*fd
	 * @param list The list of the segments
	 * @param dIndex The tested index
	 * @return int The value of fdPlus*fdMinus - wValue*fd
	 */
	int getHeuristicCost(SegmentList list, int dIndex) {
		Line d = new Line(list.get(dIndex));
		int fdPlus = 0;
		int fdMinus = 0;
		int fd = 0;
		for (int i=0; i<list.size(); i++) {
			if (i != dIndex) {
				Segment s=list.get(i);
				if (s.getP1().isAbove(d) && s.getP2().isAbove(d))
					fdPlus++;
				else if (s.getP1().isUnder(d) && s.getP2().isUnder(d))
					fdMinus++;
				else if (!d.equals(new Line(s)))
					fd++;
			}
		}
		return fdPlus*fdMinus - wValue*fd;
	}

	/**
	 * Used for the tests
	 */
	int getMaxIndex() {
		return maxIndex;
	}

}
