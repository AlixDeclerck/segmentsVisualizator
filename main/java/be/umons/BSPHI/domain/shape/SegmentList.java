package be.umons.BSPHI.domain.shape;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

public class SegmentList extends ArrayList<Segment> {

	private static final long serialVersionUID = 6153200298001449831L;
	
	/**
	 * Generate a list of random segments. The color of a segment is totally random, and the coordinates
	 * are randomly generated between 0 and the specified bounds. 
	 * @param number The number of segments that will be generated
	 * @param xBound The maximum X-coordinate of the segments that will be generated
	 * @param yBound The maximum Y-coordinate of the segments that will be generated
	 * @return The SegmentList object which is a ArrayList of the generated segments
	 */
	public static SegmentList generateSegmentList(int number, int xBound, int yBound) {
		Random rand = new Random();
		SegmentList l = new SegmentList();
		for (int i=0; i<number; i++) {
			Point p1 = new Point(rand.nextDouble()*xBound, rand.nextDouble()*yBound);
			Point p2 = new Point(rand.nextDouble()*xBound, rand.nextDouble()*yBound);
			l.add(new Segment(p1, p2, Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))));
		}
		return l;
	}
}
