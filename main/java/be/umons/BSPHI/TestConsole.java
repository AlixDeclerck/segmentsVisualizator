package be.umons.BSPHI;

import be.umons.BSPHI.domain.BSPNode;
import be.umons.BSPHI.domain.ConsoleScanConverter;
import be.umons.BSPHI.domain.Painter;
import be.umons.BSPHI.domain.heuristic.Heuristic;
import be.umons.BSPHI.domain.heuristic.Heuristic1;
import be.umons.BSPHI.domain.heuristic.Heuristic2;
import be.umons.BSPHI.domain.heuristic.Heuristic3;
import be.umons.BSPHI.domain.shape.Point;
import be.umons.BSPHI.userInterfaces.navigation.MainController;
import be.umons.BSPHI.userInterfaces.pView.Viewer;
import be.umons.BSPHI.userInterfaces.sceneView.SceneViewController;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.valueOf;
import static java.lang.System.nanoTime;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * TestConsole show by heuristics  : the BSP height and nodes number + painting and processing time measures
 */
public class TestConsole {

	private static final int VIRTUAL_MAP_WIDTH = 500;
	private static final int VIRTUAL_MAP_HEIGHT = 818;
    private static final String TOTAL_TIME_HEURISTIC = "total time heuristic ";
    private static MainController mainController = new MainController();
    private static SceneViewController controller = new SceneViewController();

    public static void main(String[ ] args) {
        String lvl1, lvl2;
        do {
            Scanner heuristic = new Scanner(System.in);
            System.out.println("Please choose the heuristic (1,2,3, 0 to quit) : ");
            lvl1 = heuristic.nextLine();
            if (lvl1.equals("1")) totalProcessingTime(lvl1, runScenes(mainController.initScenes().entrySet().iterator(), new Heuristic1()));
            else if (lvl1.equals("2")) totalProcessingTime(lvl1, runScenes(mainController.initScenes().entrySet().iterator(), new Heuristic2()));
            else if (lvl1.equals("3")) {
                do {
                    Scanner w = new Scanner(System.in);
                    System.out.println("Please choose the w value (1,4,8,12,16, 0 to choose the heuristic) : ");
                    lvl2 = w.nextLine();
                    if (lvl2.equals("1") || lvl2.equals("4") || lvl2.equals("8") || lvl2.equals("12") || lvl2.equals("16"))  totalProcessingTime(lvl1, runScenes(mainController.initScenes().entrySet().iterator(), new Heuristic3(valueOf(lvl2))));
                } while (!lvl2.equals("0"));

            }
        } while (!lvl1.equals("0"));
    }

    private static void totalProcessingTime(String heuristicNumber, long time) {
        System.out.println(TOTAL_TIME_HEURISTIC +heuristicNumber+" : "+(time/1000)+" seconds\n");
    }

    private static long runScenes(Iterator scenes, Heuristic heuristic) {
        long totalTime = 0;
        while (scenes.hasNext()) {
            Map.Entry scene = (Map.Entry) scenes.next();
            totalTime += runScene(nanoTime(), (String)scene.getValue(), heuristic);
        }
        return totalTime;
    }

    private static long runScene(long startTime, String sceneFilePath, Heuristic heuristic) {       
    	System.out.println("Scene : "+sceneFilePath);
    	Painter painter = new Painter(new ConsoleScanConverter(), 
    			new Viewer(null, new Point(1,1), new Point(1,1), 0, 0));
        controller.runScenes(sceneFilePath, VIRTUAL_MAP_HEIGHT, VIRTUAL_MAP_WIDTH, heuristic);
        
        BSPNode bsp = controller.getBsp();
        long buildingTime = NANOSECONDS.toMillis(nanoTime() - startTime);
        
        painter.paint(bsp);
        long paintingTime = NANOSECONDS.toMillis(nanoTime() - (buildingTime+startTime));
        
        System.out.println("Height  : "+bsp.getHeight());
        System.out.println("Nodes number : "+bsp.getNodesCount());
        System.out.println("processing time : "+buildingTime+" milliseconds");
        System.out.println("painting time : "+paintingTime+  " milliseconds");
        System.out.println("----------------------------------------------------\n");
        
        return paintingTime;
    }
}