package be.umons.BSPHI.domain.heuristic;

import java.util.HashMap;
import java.util.Map;

/**
 * Heuristic enumeration
 */
public enum HeuristicEnum {

    H1 ("Heuristic 1"),
    H2 ("Heuristic 2"),
    H3 ("Heuristic 3");

    private String title;

    private static final Map<String, HeuristicEnum> lookup = new HashMap();

    static {
        for(HeuristicEnum h : HeuristicEnum.values())
            lookup.put(h.title(), h);
    }

    HeuristicEnum(String title){
        this.title = title;
    }

    public String title(){
        return title;
    }

    public static HeuristicEnum heuristic(String title) {
        return lookup.get(title);
    }
}
