package be.umons.BSPHI.domain.heuristic;

import be.umons.BSPHI.userInterfaces.navigation.MainController;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static be.umons.BSPHI.domain.FileLoader.readFile;
import static be.umons.BSPHI.userInterfaces.DisplayAdapter.roundingStrategy;
import static org.junit.Assert.*;

public class Heuristic2Test {

    public static final String RESOURCES_BUNDLE_1 = "resources/Scenes/other/other01.txt";

    public static final String ELEMENT_SEPARATOR = " ";
    public static final int SCREEN_HEIGHT = 818;
    public static final int SCREEN_WIDTH = 500;

    private MainController mainController = new MainController();
    private HashMap<String, Color> colors;
    private Heuristic h2 = new Heuristic2();
    private int heuristicIndex = 0;

    @Before
    public void initialize() {
        colors = mainController.initColors();
    }

    @Test
    public void getIndexCuttingSegment_O1() {
        int index = h2.getIndexCuttingSegment(readFile(RESOURCES_BUNDLE_1, roundingStrategy(SCREEN_WIDTH), roundingStrategy(SCREEN_HEIGHT), colors, ELEMENT_SEPARATOR));
        assertEquals(heuristicIndex, index);
    }
}