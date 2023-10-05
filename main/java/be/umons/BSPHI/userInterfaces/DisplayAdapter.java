package be.umons.BSPHI.userInterfaces;

import static java.lang.Math.round;

/**
 * Handle adaptations needed in the graphical layer
 */
public class DisplayAdapter {

    private DisplayAdapter() {}

    /**
     * to adapt the scene and click selection with the up menu size
     */
    final static double UP_MENU_HEIGHT = 25;

    public static double adjustScreenSize(double y) {
        return y- UP_MENU_HEIGHT;
    }

    /**
     * Strategy used to cast double to int consistently
     * @param value
     * @return int
     */
    public static int roundingStrategy(double value) {
        return (int) round(value);
    }

}
