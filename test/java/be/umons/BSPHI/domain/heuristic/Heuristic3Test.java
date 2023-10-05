package be.umons.BSPHI.domain.heuristic;

import be.umons.BSPHI.userInterfaces.navigation.MainController;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static be.umons.BSPHI.domain.FileLoader.readFile;
import static be.umons.BSPHI.userInterfaces.DisplayAdapter.roundingStrategy;
import static org.junit.Assert.*;

public class Heuristic3Test {

    public static final String RESOURCES_BUNDLE_1 = "resources/Scenes/other/other01.txt";
    public static final String RESOURCES_BUNDLE_2 = "resources/Scenes/other/other02.txt";
    public static final String RESOURCES_BUNDLE_3 = "resources/Scenes/other/other03.txt";

    public static final String ELEMENT_SEPARATOR = " ";
    public static final int SCREEN_HEIGHT = 818;
    public static final int SCREEN_WIDTH = 500;

    private MainController mainController = new MainController();
    private HashMap<String, Color> colors;

    @Before
    public void initialize() {
        colors = mainController.initColors();
    }

    @Test
    public void getIndexCuttingSegment_O1_with_W4() {
        Heuristic h3 = new Heuristic3(4);
        h3.getIndexCuttingSegment(readFile(RESOURCES_BUNDLE_1, roundingStrategy(SCREEN_WIDTH), roundingStrategy(SCREEN_HEIGHT), colors, ELEMENT_SEPARATOR));
        int maxIndex = ((Heuristic3) h3).getMaxIndex();
        assertEquals(151, maxIndex);
    }

    @Test
    public void getIndexCuttingSegment_O1_with_W8() {
        Heuristic h3 = new Heuristic3(8);
        h3.getIndexCuttingSegment(readFile(RESOURCES_BUNDLE_1, roundingStrategy(SCREEN_WIDTH), roundingStrategy(SCREEN_HEIGHT), colors, ELEMENT_SEPARATOR));
        int maxIndex = ((Heuristic3) h3).getMaxIndex();
        assertEquals(151, maxIndex);
    }

    @Test
    public void getIndexCuttingSegment_O1_with_W12() {
        Heuristic h3 = new Heuristic3(12);
        h3.getIndexCuttingSegment(readFile(RESOURCES_BUNDLE_1, roundingStrategy(SCREEN_WIDTH), roundingStrategy(SCREEN_HEIGHT), colors, ELEMENT_SEPARATOR));
        int maxIndex = ((Heuristic3) h3).getMaxIndex();
        assertEquals(151, maxIndex);
    }

    @Test
    public void getIndexCuttingSegment_O1_with_W16() {
        Heuristic h3 = new Heuristic3(16);
        h3.getIndexCuttingSegment(readFile(RESOURCES_BUNDLE_1, roundingStrategy(SCREEN_WIDTH), roundingStrategy(SCREEN_HEIGHT), colors, ELEMENT_SEPARATOR));
        int maxIndex = ((Heuristic3) h3).getMaxIndex();
        assertEquals(151, maxIndex);
    }

    @Test
    public void getIndexCuttingSegment_O2_with_W4() {
        Heuristic h3 = new Heuristic3(4);
        h3.getIndexCuttingSegment(readFile(RESOURCES_BUNDLE_2, roundingStrategy(SCREEN_WIDTH), roundingStrategy(SCREEN_HEIGHT), colors, ELEMENT_SEPARATOR));
        int maxIndex = ((Heuristic3) h3).getMaxIndex();
        assertEquals(2, maxIndex);
    }

    @Test
    public void getIndexCuttingSegment_O2_with_W8() {
        Heuristic h3 = new Heuristic3(8);
        h3.getIndexCuttingSegment(readFile(RESOURCES_BUNDLE_2, roundingStrategy(SCREEN_WIDTH), roundingStrategy(SCREEN_HEIGHT), colors, ELEMENT_SEPARATOR));
        int maxIndex = ((Heuristic3) h3).getMaxIndex();
        assertEquals(2, maxIndex);
    }

    @Test
    public void getIndexCuttingSegment_O2_with_W12() {
        Heuristic h3 = new Heuristic3(12);
        h3.getIndexCuttingSegment(readFile(RESOURCES_BUNDLE_2, roundingStrategy(SCREEN_WIDTH), roundingStrategy(SCREEN_HEIGHT), colors, ELEMENT_SEPARATOR));
        int maxIndex = ((Heuristic3) h3).getMaxIndex();
        assertEquals(5, maxIndex);
    }

    @Test
    public void getIndexCuttingSegment_O2_with_W16() {
        Heuristic h3 = new Heuristic3(16);
        h3.getIndexCuttingSegment(readFile(RESOURCES_BUNDLE_2, roundingStrategy(SCREEN_WIDTH), roundingStrategy(SCREEN_HEIGHT), colors, ELEMENT_SEPARATOR));
        int maxIndex = ((Heuristic3) h3).getMaxIndex();
        assertEquals(5, maxIndex);
    }

    @Test
    public void getIndexCuttingSegment_O3_with_W4() {
        Heuristic h3 = new Heuristic3(4);
        h3.getIndexCuttingSegment(readFile(RESOURCES_BUNDLE_3, roundingStrategy(SCREEN_WIDTH), roundingStrategy(SCREEN_HEIGHT), colors, ELEMENT_SEPARATOR));
        int maxIndex = ((Heuristic3) h3).getMaxIndex();
        assertEquals(110, maxIndex);
    }

    @Test
    public void getIndexCuttingSegment_O3_with_W8() {
        Heuristic h3 = new Heuristic3(8);
        h3.getIndexCuttingSegment(readFile(RESOURCES_BUNDLE_3, roundingStrategy(SCREEN_WIDTH), roundingStrategy(SCREEN_HEIGHT), colors, ELEMENT_SEPARATOR));
        int maxIndex = ((Heuristic3) h3).getMaxIndex();
        assertEquals(110, maxIndex);
    }

    @Test
    public void getIndexCuttingSegment_O3_with_W12() {
        Heuristic h3 = new Heuristic3(12);
        h3.getIndexCuttingSegment(readFile(RESOURCES_BUNDLE_3, roundingStrategy(SCREEN_WIDTH), roundingStrategy(SCREEN_HEIGHT), colors, ELEMENT_SEPARATOR));
        int maxIndex = ((Heuristic3) h3).getMaxIndex();
        assertEquals(110, maxIndex);
    }

    @Test
    public void getIndexCuttingSegment_O3_with_W16() {
        Heuristic h3 = new Heuristic3(16);
        h3.getIndexCuttingSegment(readFile(RESOURCES_BUNDLE_3, roundingStrategy(SCREEN_WIDTH), roundingStrategy(SCREEN_HEIGHT), colors, ELEMENT_SEPARATOR));
        int maxIndex = ((Heuristic3) h3).getMaxIndex();
        assertEquals(110, maxIndex);
    }

}