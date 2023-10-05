package be.umons.BSPHI.domain.shape;

import java.util.HashMap;
import java.util.Map;

/**
 * Segments and shapes made with segments to create BSP and paint them on the scene
 */
public enum ShapeEnum {

    SEGMENT("Segments",90.0),
    ELLIPSE("Ellipses",3.0),
    RECTANGLE("Rectangles",4.0),
    RANDOM("Randoms", 4.0),
    OCTANGLE("Octangles",1.0),
    OCTOGONE("Octogones",1.0),
    RECTANGLE_EDITED("Rectangles edit",4.0),
    OTHER("others",3.0);

    private String title;
    private double size;
    private static final Map<String, ShapeEnum> lookup = new HashMap();

    static {
        for(ShapeEnum h : ShapeEnum.values())
            lookup.put(h.title(), h);
    }

    ShapeEnum(String title, double size){
        this.title = title;
        this.size = size;
    }

    public String title(){
        return title;
    }

    public double size() {return size;}

    public static ShapeEnum shape(String title) {
        return lookup.get(title);
    }
}
