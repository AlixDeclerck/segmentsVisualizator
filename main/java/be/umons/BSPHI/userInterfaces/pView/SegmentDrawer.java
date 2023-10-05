package be.umons.BSPHI.userInterfaces.pView;

import be.umons.BSPHI.domain.shape.Segment;
import be.umons.BSPHI.domain.shape.SegmentList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * Class which manage the displaying of the segments on the pane
 */
public class SegmentDrawer {
	
	public static final double SEGMENT_STROKE_WIDTH = 1;
	
	private Pane pane;
	
	public SegmentDrawer(Pane pane) {
		this.pane = pane;
	}
	
	public void addSegmentList(SegmentList sl) {
		for (Segment s : sl)
			addSegment(s);
	}
	
	/**
	 * Add a segment on the pane
	 * @param s The segment that will be displayed
	 */
	public void addSegment(Segment s) {
	    Line line = new Line();
	    line.setStartX(s.getP1().getX());
	    line.setStartY(s.getP1().getY());
	    line.setEndX(s.getP2().getX());
	    line.setEndY(s.getP2().getY());
	    
	    line.setStroke(s.getColor());
	    line.setStrokeWidth(SEGMENT_STROKE_WIDTH);
	    
	    pane.getChildren().add(line);  
	}
	
	/**
	 * Clear the pane
	 */
	public void clearScreen() {
		pane.getChildren().clear();
	}
	
}
