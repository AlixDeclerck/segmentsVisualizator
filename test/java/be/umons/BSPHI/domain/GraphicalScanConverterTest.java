package be.umons.BSPHI.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import be.umons.BSPHI.domain.shape.Point;
import be.umons.BSPHI.domain.shape.Segment;
import be.umons.BSPHI.userInterfaces.pView.ViewBar;
import be.umons.BSPHI.userInterfaces.pView.Viewer;
import javafx.scene.layout.Pane;


public class GraphicalScanConverterTest {
	
    Viewer viewer = new Viewer(new Pane(), new Point(100,-100), new Point(0, 0), 40, 80);
    GraphicalScanConverter scanConverter = new GraphicalScanConverter(viewer, new ViewBar(new Pane()));
    
    /*
   @Test
    public void crossingLineSituation() {
        Point p1 = new Point(30,40);
        Point p2 = new Point(50,60);
        Segment segmentToDraw = new Segment(p1, p2);
        Point intersectionPoint = new Point(0,12);
        boolean result = scanConverter.crossingLineSituation(segmentToDraw, intersectionPoint);
        assertTrue(result);
    }
    */

    @Test
    public void testSegmentCrossViewerRightArm() {
        Point p1 = new Point(3,4);
        Point p2 = new Point(4,-1);
        Point viewPoint = new Point(0,0);
        Point viewerP1 = new Point(10,0);
        Segment segmentToDraw = new Segment(p1, p2);
        boolean result = scanConverter.segmentCrossViewerRightArm(viewPoint, viewerP1, segmentToDraw);
        assertTrue(result);
    }

    @Test
    public void onePointInTheFieldOfVision_TRUE() {
        assertTrue(scanConverter.angleInFieldOfVision(50, 80));
        assertFalse(scanConverter.angleInFieldOfVision(90, 80));
    }

    @Test
    public void onePointInTheFieldOfVision_FALSE_BECAUSE_EQUALS() {
        double viewAngle = 50;
        double angle2 = 50;
        scanConverter.angleInFieldOfVision(viewAngle, angle2);
        assertFalse(scanConverter.angleInFieldOfVision(viewAngle, angle2));
    }

}
