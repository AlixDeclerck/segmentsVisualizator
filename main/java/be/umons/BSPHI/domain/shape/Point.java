package be.umons.BSPHI.domain.shape;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Point in the scene
 */
public class Point {
	
	/**
	 * The X-coordinate of the point
	 */
    private double x;
    
    /**
     * The Y-coordinate of the point
     */
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    /**
     * 
     * @param l
     * @return True if the point is above the line l
     */
	public boolean isAbove(Line l) {
		return l.isUnder(this);
	}
	
	/**
	 * 
	 * @param l
	 * @return True if the point is under the line l
	 */
	public boolean isUnder(Line l) {
		return l.isAbove(this);
	}
	
	/**
	 * 
	 * @param l
	 * @return True if the point is on the line l
	 */
    public boolean belongTo(Line l) {
    	return l.belongTo(this);
    }
	
    /**
     * 
     * @param s A segment
     * @return true if the point is on the segment s
     */
	public boolean belongTo(Segment s) {
		Line l = new Line(s);
		if (belongTo(l)) {
			/* If the segment to compare is vertical Y must be between the minimized point and the maximized one. */
			if (l.isVertical())
				return (
					y >= min(s.getP1().getY(), s.getP2().getY())
					&& 
					y <= max(s.getP1().getY(), s.getP2().getY())
				);
			else
				/* If the segment to compare is not vertical X must be between the minimized point and the maximized one. */
				return (
	                x >= min(s.getP1().getX(), s.getP2().getX())
	                &&
	                x<= max(s.getP1().getX(), s.getP2().getX())
	            );
		}
		return false;
	}
}
