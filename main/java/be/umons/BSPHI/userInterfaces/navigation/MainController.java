package be.umons.BSPHI.userInterfaces.navigation;

import be.umons.BSPHI.domain.heuristic.HeuristicContext;
import be.umons.BSPHI.domain.shape.ShapeEnum;
import be.umons.BSPHI.userInterfaces.sceneView.SceneViewController;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.HashMap;

import static be.umons.BSPHI.domain.heuristic.HeuristicEnum.*;
import static be.umons.BSPHI.domain.shape.ShapeEnum.*;
import static be.umons.BSPHI.userInterfaces.DisplayAdapter.roundingStrategy;
import static java.lang.System.nanoTime;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * The Main controller handle all main.fxml features that include information bar to show statistics,
 * a slider to select the size of shapes chosen. Also an heuristic choice can be made.
 */
public class MainController {

	@FXML
	SceneViewController sceneViewController;

	@FXML
	Slider slider;

	@FXML
	Label infoBar;
	
	@FXML
	ChoiceBox<String> heuristicBox, shapeBox;

	@FXML
	TextField wValueText;

	private ShapeEnum selectedShape = SEGMENT;
	private HeuristicContext heuristicContext = new HeuristicContext();
	private HashMap<String, String> scenes = new HashMap();

	private static int sceneCounter;
	private static long totalProcessingTime;

	private final String SCENE_INTRODUCTION = "The scene ";
	private final String SEGMENT_NUMBER = " containing selection n. ";
	private final String PROCESSING_TIME = " segments, was build in ";
	private final String GENERATION_TIME_MEASURED = " milliseconds for a total of ";
	private final String MILLISECONDS = " ms ";
	private final String AVERAGE = ", average : ";

	private long startTime = nanoTime();
	private long processingTime, avg;
	private StringBuilder text = new StringBuilder();

	/**
	 * Controller (for main.fxml) initialisation
	 */
	@FXML
	public void initialize() {
		sceneViewController.init(slider, heuristicContext, initColors(), wValueText);
		scenes = initScenes();
		initHeuristics();
		initShape();
	}

	/**
	 * When the user ask to generate a scene
	 */
	@FXML
	public void generateScene() {
		initStats();
		if (SEGMENT.equals(selectedShape)) {
			sceneViewController.runSegments();
		} else {
			sceneViewController.runScenes(scenes.get(selectedShape.title()+roundingStrategy(slider.getValue())));
		}
		writeStats();
	}

	/**
	 * To initialize statistics counter
	 */
	private void initStats() {
		startTime = nanoTime();
		processingTime = 0;
		avg = 0;
		text = new StringBuilder();
		sceneCounter++;
	}

	/**
	 * To write statistics
	 */
	private void writeStats() {
		processingTime = NANOSECONDS.toMillis(nanoTime() - startTime);
		totalProcessingTime = totalProcessingTime + processingTime;
		avg = totalProcessingTime/sceneCounter;

		text.append(SCENE_INTRODUCTION);
		text.append(sceneCounter);
		text.append(SEGMENT_NUMBER);
		text.append(roundingStrategy(slider.getValue()));
		text.append(PROCESSING_TIME);
		text.append(processingTime);
		text.append(GENERATION_TIME_MEASURED);
		text.append(totalProcessingTime);
		text.append(MILLISECONDS);
		text.append(AVERAGE);
		text.append(avg);
		text.append(MILLISECONDS);
		infoBar.setText(text.toString());
	}

	/**
	 * Give the JavaFX colors (value) from a given color (key)
	 * the h(k) function is the color itself (no duplicates, no collisions)
	 * @return colors
	 */
	public HashMap<String, Color> initColors() {
		HashMap<String, Color> colors = new HashMap<String, Color>();
		colors.put("Bleu", Color.BLUE);
		colors.put("Violet", Color.VIOLET);
		colors.put("Vert", Color.GREEN);
		colors.put("Gris", Color.GRAY);
		colors.put("Orange", Color.ORANGE);
		colors.put("Noir", Color.BLACK);
		colors.put("Jaune", Color.YELLOW);
		colors.put("Rose", Color.PINK);
		colors.put("Rouge", Color.RED);
		colors.put("Marron", Color.BROWN);
		colors.put("default",Color.DARKBLUE);
		return colors;
	}

	/**
	 * Give the text file (to read the shapes) URI (value) from h(k) function (key)
	 * the h(k) function is the ShapeEnum values concatenated with a natural number that can be used during selection
	 * @return scenes
	 */
	public HashMap<String, String> initScenes() {
		HashMap<String, String> scenes = new HashMap<String, String>();
		scenes.put(ELLIPSE.title()+"0","resources/Scenes/ellipses/ellipsesSmall.txt");
		scenes.put(ELLIPSE.title()+"1","resources/Scenes/ellipses/ellipsesSmall.txt");
		scenes.put(ELLIPSE.title()+"2","resources/Scenes/ellipses/ellipsesMedium.txt");
		scenes.put(ELLIPSE.title()+"3","resources/Scenes/ellipses/ellipsesLarge.txt");
		scenes.put(RECTANGLE.title()+"0","resources/Scenes/rectangles/rectanglesSmall.txt");
		scenes.put(RECTANGLE.title()+"1","resources/Scenes/rectangles/rectanglesSmall.txt");
		scenes.put(RECTANGLE.title()+"2","resources/Scenes/rectangles/rectanglesMedium.txt");
		scenes.put(RECTANGLE.title()+"3","resources/Scenes/rectangles/rectanglesLarge.txt");
		scenes.put(RECTANGLE.title()+"4","resources/Scenes/rectangles/rectanglesHuge.txt");
		scenes.put(RANDOM.title()+"0","resources/Scenes/random/randomSmall.txt");
		scenes.put(RANDOM.title()+"1","resources/Scenes/random/randomSmall.txt");
		scenes.put(RANDOM.title()+"2","resources/Scenes/random/randomMedium.txt");
		scenes.put(RANDOM.title()+"3","resources/Scenes/random/randomLarge.txt");
		scenes.put(RANDOM.title()+"4","resources/Scenes/random/randomHuge.txt");
		scenes.put(OCTANGLE.title()+"0","resources/Scenes/first/octangle.txt");
		scenes.put(OCTANGLE.title()+"1","resources/Scenes/first/octangle.txt");
		scenes.put(OCTOGONE.title()+"0","resources/Scenes/first/octogone.txt");
		scenes.put(OCTOGONE.title()+"1","resources/Scenes/first/octogone.txt");
		scenes.put(RECTANGLE_EDITED.title()+"0","resources/Scenes/rectanglesEdites/rectanglesSmall.txt");
		scenes.put(RECTANGLE_EDITED.title()+"1","resources/Scenes/rectanglesEdites/rectanglesSmall.txt");
		scenes.put(RECTANGLE_EDITED.title()+"2","resources/Scenes/rectanglesEdites/rectanglesMedium.txt");
		scenes.put(RECTANGLE_EDITED.title()+"3","resources/Scenes/rectanglesEdites/rectanglesLarge.txt");
		scenes.put(RECTANGLE_EDITED.title()+"4","resources/Scenes/rectanglesEdites/rectanglesHuge.txt");
		scenes.put(OTHER.title()+"0","resources/Scenes/other/other01.txt");
		scenes.put(OTHER.title()+"1","resources/Scenes/other/other01.txt");
		scenes.put(OTHER.title()+"2","resources/Scenes/other/other02.txt");
		scenes.put(OTHER.title()+"3","resources/Scenes/other/other03.txt");
		return scenes;
	}

	/**
	 * To initialize the heuristics choice box and Listen the user selections
	 */
	private void initHeuristics() {
		heuristicContext.setState(H1.title());
		heuristicBox.getItems().addAll(H1.title(), H2.title(), H3.title());
		heuristicBox.setValue(H1.title());
		heuristicBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			heuristicContext.setState(newValue);
			if (heuristicContext.isHeuristic3()) wValueText.setDisable(false);
			else wValueText.setDisable(true);
		});
	}

	/**
	 * To initialize the shapes choice box and Listen the user selections
	 */
	private void initShape() {
		shapeBox.getItems().addAll(SEGMENT.title(), ELLIPSE.title(), RECTANGLE.title(), RANDOM.title(), OCTANGLE.title(), OCTOGONE.title(), RECTANGLE_EDITED.title(), OTHER.title());
		shapeBox.setValue(selectedShape.title());
		slider.setMax(selectedShape.size());
		shapeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			selectedShape = shape(newValue);
			slider.setMax(selectedShape.size());
		});
	}

}
