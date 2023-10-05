package be.umons.BSPHI.domain.shape;

import javafx.scene.paint.Color;

/**
 * Segment in the scene
 */
public class Segment {
	
	/**
	 * The first extremity of the segment
	 */
    private Point p1;
    
    /**
     * The second extremity of the segment
     */
    private Point p2;
    
    /**
     * The javafx color of the segment
     */
    private Color color;

    public Segment(Point p1, Point p2, Color color) {
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;
    }
    
    /**
     * Build a segment with the javaFX Color.BLACK as default color
     * @param p1
     * @param p2
     */
    public Segment(Point p1, Point p2) {
    	this(p1, p2, Color.BLACK);
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }
    
    public Color getColor() {
    	return color;
    }
}
