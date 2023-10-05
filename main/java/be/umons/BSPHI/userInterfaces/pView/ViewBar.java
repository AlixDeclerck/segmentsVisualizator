package be.umons.BSPHI.userInterfaces.pView;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * The ViewBar is basically a rectangle that contain what is seen by the eye in the scene
 */
public class ViewBar extends Group {
	
	public static final double UPPER_LEFT_X_COORDINATE_ADAPTER = 0.1;
	public static final double UPPER_LEFT_Y_COORDINATE_ADAPTER = 1.05;
	public static final double VIEW_BAR_WIDTH_ADAPTER = 0.8;
	public static final double VIEW_BAR_HEIGHT_ADAPTER = 0.08;
	
	private Pane sceneViewZone;
	private Rectangle mainRect;
	
	public ViewBar(Pane sceneViewZone) {
		super();
		this.sceneViewZone = sceneViewZone;
		mainRect = new Rectangle();
	}
	
	public void init(double x, double y, double width, double height) {
		getChildren().clear();
		mainRect.setX(x);
		mainRect.setY(y);
		mainRect.setWidth(width);
		mainRect.setHeight(height);
		mainRect.setFill(Color.WHITE);
		mainRect.setStroke(Color.BLACK);
		mainRect.setStrokeWidth(2);
		getChildren().add(mainRect);
		if (! sceneViewZone.getChildren().contains(this))
			sceneViewZone.getChildren().add(this);
	}
	
	/**
	 * Paint the bar from begin to end into the specified color. The begin and end value are included between 0 and 1, 
	 * 0 is left edge of the bar and 1 the right edge. End must be bigger than begin
	 * @param begin The begin bound, between 0 and 1
	 * @param end The bound, between 0 and 1
	 * @param color A JavaFX color
	 */
	public void paint(double begin, double end, Color color) {
		Rectangle r = new Rectangle(mainRect.getX()+begin*mainRect.getWidth(), 
				mainRect.getY(), (end-begin)*mainRect.getWidth(), mainRect.getHeight());
		r.setFill(color);
		getChildren().add(r);  
	}
	
	public void clear() {
		Bounds bounds = sceneViewZone.getBoundsInParent();
		double viewBarX = bounds.getMinX() + sceneViewZone.getWidth()* UPPER_LEFT_X_COORDINATE_ADAPTER;
		double viewBarY = bounds.getMinY() + sceneViewZone.getHeight()* UPPER_LEFT_Y_COORDINATE_ADAPTER;
		init(viewBarX, viewBarY, sceneViewZone.getWidth()* VIEW_BAR_WIDTH_ADAPTER, sceneViewZone.getHeight()* VIEW_BAR_HEIGHT_ADAPTER);
	}	
}
