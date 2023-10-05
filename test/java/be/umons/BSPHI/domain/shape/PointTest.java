package be.umons.BSPHI.domain.shape;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {
	
	@Test
	public void testGetX() {
		double x = 4.5845;
		double y = 0;
		Point p = new Point(x, y);
		assertTrue(p.getX()==x);
	}
	
	@Test
	public void testIsAbove() {
		Point p1 = new Point(0,0);
		Point p2 = new Point(1,1);
		Line l = new Line(p1, p2);
		
		Point pTest1 = new Point(0,1);
		Point pTest2 = new Point(45,45.12);
		assertTrue(pTest1.isAbove(l));
		assertTrue(pTest2.isAbove(l));
	}
	
	@Test 
	public void testIsUnder() {
		Point p1 = new Point(0,0);
		Point p2 = new Point(1,-1);
		Line l = new Line(p1, p2);
		
		Point pTest1 = new Point(0,1);
		Point pTest2 = new Point(45,-44.46);
		Point pTest3 = new Point(40,-50);
		assertFalse(pTest1.isUnder(l));
		assertFalse(pTest2.isUnder(l));
		assertTrue(pTest3.isUnder(l));
	}
	
	@Test
	public void testBelongToLine() {
		Point p1 = new Point(0,0);
		Point p2 = new Point(2,3);
		
		Line l = new Line(p1, p2);
		
		Point pTest1 = new Point(0,1);
		Point pTest2 = new Point(45,-44.46);
		Point pTest3 = new Point(40,60);
		Point pTest4 = new Point(-2222.22222,-3333.33333);
		
		assertFalse(pTest1.belongTo(l));
		assertFalse(pTest2.belongTo(l));
		assertTrue(pTest3.belongTo(l));
		assertTrue(pTest4.belongTo(l));
	}
	
	@Test
	public void testBelongToSegment() {
		Point p1 = new Point(0,0);
		Point p2 = new Point(2,3);
		
		Segment s = new Segment(p1, p2);
		
		Point pTest1 = new Point(0,0);
		Point pTest2 = new Point(1,1.5);
		Point pTest3 = new Point(40,60);
		Point pTest4 = new Point(-2222.22222,-3333.33333);
		Point pTest5 = new Point(0,1);
		
		assertTrue(pTest1.belongTo(s));
		assertTrue(pTest2.belongTo(s));
		assertFalse(pTest3.belongTo(s));
		assertFalse(pTest4.belongTo(s));
		assertFalse(pTest5.belongTo(s));
	}

}
