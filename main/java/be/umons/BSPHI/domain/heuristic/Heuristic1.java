package be.umons.BSPHI.domain.heuristic;

import be.umons.BSPHI.domain.shape.SegmentList;

import java.util.Random;

/**
 * This heuristic choose a random segment in the list as cutting segment.
 */
public class Heuristic1 implements Heuristic {

	@Override
	public int getIndexCuttingSegment(SegmentList list) {
		Random rand = new Random();
		return rand.nextInt(list.size());
	}
	
}
