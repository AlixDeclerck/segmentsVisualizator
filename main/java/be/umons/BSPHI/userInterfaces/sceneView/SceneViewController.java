package be.umons.BSPHI.userInterfaces.sceneView;

import be.umons.BSPHI.domain.BSPNode;
import be.umons.BSPHI.domain.GraphicalScanConverter;
import be.umons.BSPHI.domain.Painter;
import be.umons.BSPHI.domain.heuristic.Heuristic;
import be.umons.BSPHI.domain.heuristic.HeuristicContext;
import be.umons.BSPHI.domain.shape.Point;
import be.umons.BSPHI.domain.shape.SegmentList;
import be.umons.BSPHI.userInterfaces.pView.SegmentDrawer;
import be.umons.BSPHI.userInterfaces.pView.ViewBar;
import be.umons.BSPHI.userInterfaces.pView.Viewer;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.util.HashMap;

import static be.umons.BSPHI.domain.shape.SegmentList.generateSegmentList;
import static be.umons.BSPHI.domain.FileLoader.readFile;
import static be.umons.BSPHI.userInterfaces.DisplayAdapter.adjustScreenSize;
import static be.umons.BSPHI.userInterfaces.DisplayAdapter.roundingStrategy;
import static java.lang.Integer.valueOf;
import static javafx.scene.input.MouseButton.SECONDARY;

/**
 * The SceneView controller handle all sceneView.fxml features that include the Painter segments scene,
 * the viewer and the view bar (to draw the segment seen by the viewer)
 */
public class SceneViewController {

	/**
	 * The length of the two "arms" of the viewer
	 */
	public static final int VIEWER_LENGTH = 40;

	/**
	 * The viewpoint coordinate at application start
	 */
	public static final int VIEWPOINT_COORD = 250;

	/**
	 * The target point coordinate at application start
	 */
	public static final int TARGET_POINT_COORD = 1;

	/**
	 * The pview field of vision in degree
	 */
	public static final int VIEW_ANGLE_DEG = 80;

	public static final String ELEMENT_SEPARATOR = " ";

	@FXML
	private BorderPane sceneViewZone;

	private HashMap<String, Color> colors = new HashMap<String, Color>();
	private TextField wValueText;
	private Slider slider;
	private Painter painter;
	private ViewBar viewBar;
	private Viewer viewer;
	private BSPNode bsp;
	private SegmentDrawer sd;
	private HeuristicContext heuristicContext;

	/**
	 * Controller (for sceneView.fxml) initialisation
	 * @param slider
	 * @param heuristicContext
	 * @param colors
	 * @param wValueText
	 */
	public void init(Slider slider, HeuristicContext heuristicContext, HashMap<String, Color> colors, TextField wValueText) {
		viewer = new Viewer(sceneViewZone,
				new Point(TARGET_POINT_COORD, TARGET_POINT_COORD),
				new Point(VIEWPOINT_COORD, VIEWPOINT_COORD),
				VIEWER_LENGTH,
				Math.toRadians(VIEW_ANGLE_DEG));
		viewer.display();
		
		viewBar = new ViewBar(sceneViewZone);
		
		GraphicalScanConverter scanConverter = new GraphicalScanConverter(viewer, viewBar);
		
		sd = new SegmentDrawer(sceneViewZone);
		
		painter = new Painter(scanConverter, viewer);
		
		this.slider = slider;
		this.colors = colors;
		this.heuristicContext = heuristicContext;
		this.wValueText = wValueText;
	}

	/**
	 * When the user ask to generate segments
	 */
	public void runSegments() {
    	SegmentList sl = generateSegmentList(
    			roundingStrategy(slider.getValue()),
				roundingStrategy(sceneViewZone.getWidth()),
				roundingStrategy(sceneViewZone.getHeight()));

    	updateScene(sl);
    }

	/**
	 * When the user ask to generate specific shapes
	 */
    public void runScenes(String filePath) {
    	updateScene(readFile(
    			filePath,
				roundingStrategy(sceneViewZone.getWidth()),
				roundingStrategy(sceneViewZone.getHeight()),
				colors, ELEMENT_SEPARATOR));
    }

	/**
	 * When the user ask to update the scene
	 */
    private void updateScene(SegmentList newSegList) {
    	sd.clearScreen();
    	sd.addSegmentList(newSegList);

		bsp = new BSPNode(newSegList, heuristicContext.getHeuristic(valueOf(wValueText.getText())));

		viewer.display();
		viewBar.clear();
		painter.paint(bsp);
    }

	/**
	 * When the user click on the scene, left button change the viewer position and right button change the target point
	 */
	public void onClick(MouseEvent mouseEvent) {
		if (painter != null) {
			if (mouseEvent.getButton().equals(SECONDARY))
				viewer.setTargetPoint(new Point(mouseEvent.getSceneX(), adjustScreenSize(mouseEvent.getSceneY())));
			else
				viewer.setViewPoint(new Point(mouseEvent.getSceneX(), adjustScreenSize(mouseEvent.getSceneY())));

			viewBar.clear();
			if (bsp != null)
				painter.paint(bsp);
		}
	}

	/**
	 * TestConsole and JUnit tests usage
	 * @param filePath
	 * @param height
	 * @param width
	 * @param heuristic
	 */
	public void runScenes(String filePath, int height, int width, Heuristic heuristic) {
		bsp = new BSPNode(readFile(
				filePath,
				roundingStrategy(height),
				roundingStrategy(width),
				colors, ELEMENT_SEPARATOR), heuristic);
	}

	public BSPNode getBsp() {
		return this.bsp;
	}
}
