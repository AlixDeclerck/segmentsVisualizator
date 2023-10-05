package be.umons.BSPHI.domain;

import be.umons.BSPHI.domain.shape.Point;

import static java.lang.Math.PI;

/**
 * Handle adaptations needed in the domain layer
 */
public class PlanAdapter {

	private PlanAdapter() {}

	/**
	 * 
	 * @param deltaX the X-coordinate of the vector
	 * @param deltaY the Y-coordinate of the vector
	 * @return the angle in radians between the X-axis and the vector specified by the deltaX and deltaY parameters
	 */
	public static double getAngleFromVector(double deltaX, double deltaY) {
		return normalize(Math.atan2(deltaY, deltaX));
	}
		
	/**
	 *  
	 * @param val The amplitude in radians of the angle
	 * @return the value of the angle replaced in the [0, 2*Pi] interval
	 */
	public static double normalize(double val) {
		if (val < 0)
			return val+2*PI;
		return val;
	}

	/**
	 * Calculation are made on a plan with an y axis above the x axis
	 * @param p the vertical axis value
	 * @return p complement
	 */
	public static Point adaptVerticalAxisToPlan(Point p) {
		return new Point(p.getX(), -p.getY());
	}
}
