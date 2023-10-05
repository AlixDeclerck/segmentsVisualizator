package be.umons.BSPHI.domain;

import be.umons.BSPHI.domain.shape.Point;
import be.umons.BSPHI.domain.shape.SegmentList;
import be.umons.BSPHI.userInterfaces.pView.Viewer;

/**
 * The painter's algorithm
 */
public class Painter {

	private ScanConverter scanConverter;
	
	private Viewer viewer;
	
	public Painter(ScanConverter scanConverter, Viewer viewer) {
		this.scanConverter = scanConverter;
		this.viewer = viewer;
	}

	/**
	 * The painter's algorithm
	 * @param BSP
	 */
	public void paint(BSPNode BSP) {
		Point viewPoint = viewer.getViewPoint();
		if (BSP.isLeaf())
			displaySegments(BSP.getSegments());
		else {
			if(viewPoint.belongTo(BSP.getCuttingLine())) {
				paint(BSP.getRightChild());
				paint(BSP.getLeftChild());
			}
			if(viewPoint.isUnder(BSP.getCuttingLine())) {
				paint(BSP.getRightChild());
				displaySegments(BSP.getSegments());
				paint(BSP.getLeftChild());
			}
			else {
				paint(BSP.getLeftChild());
				displaySegments(BSP.getSegments());
				paint(BSP.getRightChild());
			}
		}
	}

	/**
	 * Display a list of segments in the viewBar using displaySegment
	 * @param list The list 
	 */
	private void displaySegments(SegmentList list) {
		scanConverter.scanConvert(list);
	}
}
