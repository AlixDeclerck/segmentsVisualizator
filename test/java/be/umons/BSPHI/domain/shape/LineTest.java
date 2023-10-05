package be.umons.BSPHI.domain.shape;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineTest {

	@Test
	public void testIsVertical() {
		Point p1 = new Point(4,5);
		Point p2 = new Point(4,-255);
		Point p3 = new Point(3.5, 3.5);
		
		Line l1 = new Line(p1, p2);
		Line l2 = new Line(p2, p3);
		Line l3 = new Line(p1, p3);
		
		assertTrue(l1.isVertical());
		assertFalse(l2.isVertical());
		assertFalse(l3.isVertical());
	}

	
	@Test
	public void testEqualsLine() {
		Point p1 = new Point(4,5);
		Point p2 = new Point(4,-255);
		Point p3 = new Point(3.5, 3.5);
		Point p4 = new Point(1, 1);
		Point p5 = new Point(-40, -40);
		
		Line l1 = new Line(p1, p2);
		Line l2 = new Line(p1, p2);
		Line l3 = new Line(p2, p3);
		Line l4 = new Line(p3, p4);
		Line l5 = new Line(p4, p5);
		
		assertTrue(l1.equals(l2));
		assertFalse(l1.equals(l3));
		assertFalse(l2.equals(l4));
		assertTrue(l4.equals(l5));
	}
	
	
	@Test
	public void testGetIntersection() {
		Point p1 = new Point(4,5);
		Point p2 = new Point(4,-255);
		Point p3 = new Point(3.5, 3.5);
		Point p4 = new Point(1, 1);
		
		Line l1 = new Line(p1, p2);
		Line l2 = new Line(p3, p4);
		Line l3 = new Line(p1, p3);
		
		Point pInter1 = Line.getIntersection(l1, l2);
		Point pInter2 = Line.getIntersection(l1, l3);
		
		assertTrue(pInter1.belongTo(l1));
		assertTrue(pInter1.belongTo(l2));
		
		assertTrue(pInter2.getX()==p1.getX() && pInter2.getY()==p1.getY());
	}
}
