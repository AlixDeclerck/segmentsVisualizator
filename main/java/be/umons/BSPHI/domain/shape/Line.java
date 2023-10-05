package be.umons.BSPHI.domain.shape;


/**
 * A line is the extension of a vector
 */
public class Line {

	/**
	 * Factor we use to give accuracy to processing
	 */
	private static final double EPSILON=1e-12;
	
	/**
	 * The slope of the line. Not used if the line is vertical.
	 */
	private double slope;
	
	/**
	 * The intercept of the line. Not used if the line is vertical.
	 */
	private double intercept;
	
	/**
	 * A boolean which specify if the line is vertical
	 */
	private boolean vertical;
	
	/**
	 * Used only if the line is vertical. In this case it is the X-coordinate of each point of the line
	 */
	private double x_coordinate;	

	public Line(Point p1, Point p2) {
		if (Math.abs(p2.getX()-p1.getX())<EPSILON) {
			vertical = true;
			x_coordinate = p2.getX();
		}
		else {
			vertical = false;
			slope = (p2.getY()-p1.getY())/(p2.getX()-p1.getX());
			intercept = p1.getY()-slope*p1.getX();
		}
	}
	
	public Line(Segment s) {
		this(s.getP1(), s.getP2());
	}
	
	public boolean isVertical() {
		return vertical;
	}
	
	boolean isAbove(Point p) {
		if (vertical)
			return p.getX() - x_coordinate < EPSILON;
		else
			return p.getY() - (p.getX()*slope+intercept) < EPSILON;
	}
	
	boolean isUnder(Point p) {
		if (vertical)
			return p.getX() - x_coordinate > -EPSILON;
		else
			return p.getY() - (p.getX()*slope+intercept) > -EPSILON;
	}
	
	boolean belongTo(Point p) {
		if (vertical)
			return Math.abs(x_coordinate-p.getX())<EPSILON;
		else
			return Math.abs(slope*p.getX()+intercept-p.getY()) < EPSILON ;
	}
	
	public boolean equals(Line other) {
		if (vertical || other.vertical) {
			if (vertical)
				return other.vertical && (x_coordinate==other.x_coordinate);
			return false;
		}
		else
			return Math.abs(slope-other.slope)<EPSILON && Math.abs(intercept-other.intercept)<EPSILON;
	}
	
	/**
	 * 
	 * @param l1
	 * @param l2
	 * @return The point which is the intersection between the lines l1 and l2. If the line are parallels,
	 * the method will return null.
	 */
    public static Point getIntersection(Line l1, Line l2) {
    	if (l1.vertical || l2.vertical) {
    		if (l1.vertical && l2.vertical)
    			return null;
    		if (l1.vertical)
    			return new Point(l1.x_coordinate, l1.x_coordinate*l2.slope+l2.intercept);
    		if (l2.vertical)
    			return new Point(l2.x_coordinate, l2.x_coordinate*l1.slope+l1.intercept);
    	}
    	if (Math.abs(l1.slope-l2.slope)<EPSILON)
    		return null;
    	else {
    		double x = (l2.intercept-l1.intercept)/(l1.slope-l2.slope);
    		double y = x*l1.slope+l1.intercept;
    		return new Point(x, y);
    	}
    }
}