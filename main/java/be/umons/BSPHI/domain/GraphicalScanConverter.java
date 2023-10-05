package be.umons.BSPHI.domain;

import static be.umons.BSPHI.domain.PlanAdapter.adaptVerticalAxisToPlan;
import static be.umons.BSPHI.domain.PlanAdapter.getAngleFromVector;
import static be.umons.BSPHI.domain.PlanAdapter.normalize;
import static be.umons.BSPHI.domain.shape.Line.getIntersection;

import be.umons.BSPHI.domain.shape.Line;
import be.umons.BSPHI.domain.shape.Point;
import be.umons.BSPHI.domain.shape.Segment;
import be.umons.BSPHI.domain.shape.SegmentList;
import be.umons.BSPHI.userInterfaces.pView.ViewBar;
import be.umons.BSPHI.userInterfaces.pView.Viewer;

/**
 * Scan-converting implementation for the graphical mode
 */
public class GraphicalScanConverter implements ScanConverter{

	private Viewer viewer;
	private ViewBar viewBar;
	
	public GraphicalScanConverter(Viewer viewer, ViewBar viewBar) {
		this.viewer = viewer;
		this.viewBar = viewBar;
	}
	
	@Override
	public void scanConvert(SegmentList sl) {
		for(Segment s : sl)
			scanConvert(s);
	}
	
	/**
	 * Display one segment facing the different situations from the view to the segment
	 * Determinate the place that the given segment has into the viewer's field of vision, and tell the viewBar if something is seen
	 * @param s The segment to scan-convert
	 */
	private void scanConvert(Segment s) {
		
		/**
		 * viewAngle is the amplitude of the angle between the two arms of the viewer
		 */
		double viewAngle = viewer.getViewAngle();

		/**
		 * viewPoint is the point from where the scene is viewed
		 */
		Point viewPoint  = adaptVerticalAxisToPlan(viewer.getViewPoint());

		/**
		 * targetPoint is the point towards the viewer looks
		 */
		Point targetPoint = adaptVerticalAxisToPlan(viewer.getTargetPoint());
		
		/**
		 * viewerP1 is a point of the viewer's left arm into the javaFX scene
		 * in the system of coordinates used here, it is a point of the right arm
		 */
		Point viewerP1 = adaptVerticalAxisToPlan(viewer.computeP1());

		/**
		 * Segment to draw in the viewBar
		 */
		Segment segmentToDraw = new Segment(adaptVerticalAxisToPlan(s.getP1()), adaptVerticalAxisToPlan(s.getP2()));
		
		/**
		 * The angle between the X-axis and the line passing through the viewPoint and the targetPoint
		 */
		double dirAngle = getAngleFromVector(targetPoint.getX()-viewPoint.getX(), targetPoint.getY()-viewPoint.getY());
		
		/**
		 * The angle between the X-axis and the right arm of the viewer
		 */
		double alpha = dirAngle - viewAngle/2;
		alpha = normalize(alpha);
		
		/**
		 * The angle between the X-axis and the line passing through the viewPoint and a point of the segment
		 */
		double angle1 = getAngleFromVector(segmentToDraw.getP1().getX()-viewPoint.getX(), segmentToDraw.getP1().getY()-viewPoint.getY());
		
		/**
		 * The angle between the X-axis and the line passing through the viewPoint and the other point of the segment
		 */
		double angle2 = getAngleFromVector(segmentToDraw.getP2().getX()-viewPoint.getX(), segmentToDraw.getP2().getY()-viewPoint.getY());
		
		angle1 -= alpha;
		angle1 = normalize(angle1);
		
		angle2 -= alpha;
		angle2 = normalize(angle2);
		
		/*
		 * By subtracting alpha to angle1 and angle2, we rotate all the scene of -alpha radians.
		 * In this way we know that the right arm of the viewer is horizontal and 
		 * the angle between the X-axis and the left arm is viewAngle
		 */

		/*
		 * To ensure angle 2 is the biggest, to facilitate further processing
		 */
		if (angle1>angle2) {
			double tmp = angle1;
			angle1 = angle2;
			angle2 = tmp;
		}
		
		if (angleInFieldOfVision(angle1, viewAngle)) {
			double begin, end;

			if (angleInFieldOfVision(angle2, viewAngle)) {
				/*
				 * case where the two points are in the field of vision
				 */
				begin = (viewAngle-angle2)/viewAngle;
				end = (viewAngle-angle1)/viewAngle;

			} else {
				
				/*
				 * Case where a point of the segment is outside the field of vision :
				 */
				double a = (viewAngle-angle1)/viewAngle;
				
				/*
				 * if the segment cross the right arm, we paint the bar from a to 1
				 * else it means the the segment cross the left arm, so paint the bar from 0 to a
				 */
				if (segmentCrossViewerRightArm(viewPoint, viewerP1, segmentToDraw)) {
					begin = a;
					end = 1;
				} else {
					begin = 0;
					end = a;
				}
			}

			viewBar.paint(begin, end, s.getColor());

		} else {
			/*
			 * Case where the two extremities of the segment are outside the field of vision 
			 * if the segment cross the right arm that means that the segment take all the field of vision
			 * else there is nothing to paint
			 */
			if (segmentCrossViewerRightArm(viewPoint, viewerP1, segmentToDraw)) 
				viewBar.paint(0, 1, s.getColor());
		}
	}
	
	/**
	 * 
	 * @param viewPoint The viewer's viewPoint
	 * @param viewerP1 A point placed on the right arm of the viewer
	 * @param segmentToVisualize The segment that will be visualized
	 * @return True if there is an intersection point between the right arm of the viewer and the segmentToVisualize
	 */
	boolean segmentCrossViewerRightArm(Point viewPoint, Point viewerP1, Segment segmentToVisualize) {
		
		/**
		 * The guiding line of the segmentToVisualize
		 */
		Line sLine = new Line(segmentToVisualize);
		
		/**
		 * The point of intersection between the axis of the right arm of the viewer and sLine
		 */
		Point intersectionPoint = getIntersection(new Line(viewPoint, viewerP1), sLine);
		
		if (intersectionPoint==null)
			return false;
		return intersectionPoint.belongTo(segmentToVisualize) && !viewPoint.belongTo(new Segment(viewerP1, intersectionPoint));
	}
	
	/**
	 * 
	 * @param angle The viewer's amplitude of view
	 * @param viewAngle
	 * @return True if the specified angle is in the viewer's field of vision
	 */
	boolean angleInFieldOfVision(double angle, double viewAngle) {
		return angle < viewAngle;
	}
}
