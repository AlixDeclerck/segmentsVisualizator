package be.umons.BSPHI.domain;

import be.umons.BSPHI.domain.heuristic.Heuristic1;
import be.umons.BSPHI.domain.heuristic.Heuristic2;
import be.umons.BSPHI.domain.heuristic.Heuristic3;
import be.umons.BSPHI.domain.shape.Line;
import be.umons.BSPHI.domain.shape.Point;
import be.umons.BSPHI.domain.shape.Segment;
import be.umons.BSPHI.domain.shape.SegmentList;
import be.umons.BSPHI.userInterfaces.sceneView.SceneViewController;
import org.junit.Test;

import static javafx.scene.paint.Color.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BSPNodeTest {

	private SceneViewController sceneViewController = new SceneViewController();

	public static final String RESOURCES_BUNDLE_1 = "resources/Scenes/other/other01.txt";
	public static final String RESOURCES_BUNDLE_2 = "resources/Scenes/other/other02.txt";

	private BSPNode bsp = new BSPNode(SegmentList.generateSegmentList(50, 200, 200), new Heuristic1());

	private Point p1 = new Point(10, 50);
	private Point p2 = new Point(30, 50);
	private Point p3 = new Point(10, 10);
	private Point p4 = new Point(30, 10);
	private Point p5 = new Point(10, 30);
	private Point p6 = new Point(30, 30);

	private Segment s1 = new Segment(p1, p2, ANTIQUEWHITE);
	private Segment s2 = new Segment(p3, p4, BURLYWOOD);
	private Segment s3 = new Segment(p5, p6, DARKSLATEBLUE);

	private Line l1 = new Line(s3);

	@Test
	public void testBSPHeuristic2_B1() {
		sceneViewController.runScenes(RESOURCES_BUNDLE_1, 818, 500, new Heuristic2());
		BSPNode bsp = sceneViewController.getBsp();
		assertTrue(bsp.getHeight() >= 100);
	}

	@Test
	public void testBSPHeuristic3_B1() {
		sceneViewController.runScenes(RESOURCES_BUNDLE_1, 818, 500, new Heuristic3(8));
		BSPNode bsp = sceneViewController.getBsp();
		assertEquals(49,bsp.getHeight());
	}

	@Test
	public void testBSPHeuristic2_B2() {
		sceneViewController.runScenes(RESOURCES_BUNDLE_2, 818, 500, new Heuristic2());
		BSPNode bsp = sceneViewController.getBsp();
		assertTrue(bsp.getHeight() >= 4);
	}

	@Test
	public void testBSPHeuristic3_B3() {
		sceneViewController.runScenes(RESOURCES_BUNDLE_2, 818, 500, new Heuristic3(8));
		BSPNode bsp = sceneViewController.getBsp();
		assertEquals(3,bsp.getHeight());
	}

	@Test
	public void testBSPNode() {
		BSPNode bsp = new BSPNode(SegmentList.generateSegmentList(50, 200, 200), new Heuristic3(8));
		assertEquals(1,bsp.getSegments().size());
	}

	@Test
	public void segmentUnderCuttingLine_False() {
		bsp.setCuttingLine(l1);
		assertEquals(bsp.segmentUnderCuttingLine(s1),false);
	}

	@Test
	public void segmentAboveCuttingLine() {
		bsp.setCuttingLine(l1);
		assertEquals(bsp.segmentAboveCuttingLine(s1),true);
	}

	@Test
	public void segmentUnderCuttingLine() {
		bsp.setCuttingLine(l1);
		assertEquals(bsp.segmentUnderCuttingLine(s2),true);
	}

	@Test
	public void segmentAboveCuttingLine_False() {
		bsp.setCuttingLine(l1);
		assertEquals(bsp.segmentAboveCuttingLine(s2),false);
	}

	@Test
	public void segmentCut() {
		assertEquals(bsp.segmentCut(5, 5, s1),true);
	}
}
