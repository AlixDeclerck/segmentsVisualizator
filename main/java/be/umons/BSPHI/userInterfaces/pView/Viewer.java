package be.umons.BSPHI.userInterfaces.pView;

import be.umons.BSPHI.domain.PlanAdapter;
import be.umons.BSPHI.domain.shape.Point;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * The viewer is the eye looking the segments in the scene
 */
public class Viewer {
	
	private Pane pane;

	private Point viewPoint;
	private double viewAngle;

	private Point targetPoint;
	private Circle targetCircle;

	private Line leftArm;
	private Line rightArm;
	private int armsLength;

	/**
	 * 
	 * @param viewPoint
	 * @param viewAngle
	 * @param targetPoint A point which represent a 2D vector
	 */
	public Viewer(Pane pane, Point targetPoint, Point viewPoint, int armsLength, double viewAngle) {
		
		this.pane = pane;
		this.targetPoint = targetPoint;
		this.viewPoint = viewPoint;
		this.armsLength = armsLength;
		this.viewAngle = viewAngle;
		
		Point p1 = computeP1();
		Point p2 = computeP2();
		
		leftArm = new Line(viewPoint.getX(), viewPoint.getY(), p1.getX(), p1.getY());
		rightArm = new Line(viewPoint.getX(), viewPoint.getY(), p2.getX(), p2.getY());
		
	    leftArm.setStrokeWidth(1);
	    rightArm.setStrokeWidth(1);

	    targetCircle = new Circle(targetPoint.getX(), targetPoint.getY(), 2); 
	}
	
	public void setViewPoint(Point p) {
		viewPoint = p;
		updateLines();
	}
	
	public void setTargetPoint(Point p) {
		targetPoint = p;
		targetCircle.setCenterX(targetPoint.getX());
		targetCircle.setCenterY(targetPoint.getY());
		updateLines();
	}

	private void updateLines() {
		Point p1 = computeP1();
		Point p2 = computeP2();

		leftArm.setStartX(viewPoint.getX());
		leftArm.setStartY(viewPoint.getY());
		leftArm.setEndX(p1.getX());
		leftArm.setEndY(p1.getY());

		rightArm.setStartX(viewPoint.getX());
		rightArm.setStartY(viewPoint.getY());
		rightArm.setEndX(p2.getX());
		rightArm.setEndY(p2.getY());
	}

	public void display() {
	    pane.getChildren().add(leftArm);
	    pane.getChildren().add(rightArm);
	    pane.getChildren().add(targetCircle);
	}
	
	public Point computeP1() {
		return computeP(viewAngle/2);
	}

	private Point computeP2() {
		return computeP(-viewAngle/2);
	}
	
	private Point computeP(double angleVal) {
		double dirAngle = PlanAdapter.getAngleFromVector(targetPoint.getX()-viewPoint.getX(), targetPoint.getY()-viewPoint.getY());

		double x = viewPoint.getX()+armsLength*Math.cos(dirAngle+angleVal);
		double y = viewPoint.getY()+armsLength*Math.sin(dirAngle+angleVal);

		return new Point(x, y);
	}

	public Point getViewPoint() {
		return viewPoint;
	}

	public Point getTargetPoint() {
		return targetPoint;
	}

	public double getViewAngle() {
		return viewAngle;
	}
}
