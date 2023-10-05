package be.umons.BSPHI.domain;

import be.umons.BSPHI.domain.shape.SegmentList;

/**
 * Scan-converting implementation for the console mode
 */
public class ConsoleScanConverter implements ScanConverter{

	private static final int ITERATION_CST = 100;
	
	@Override
	public void scanConvert(SegmentList sl) {
		for (int i = 0; i < sl.size()*ITERATION_CST; i++)  ;
	}

}
