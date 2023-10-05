package be.umons.BSPHI.domain.heuristic;

import static be.umons.BSPHI.domain.heuristic.HeuristicEnum.H3;
import static be.umons.BSPHI.domain.heuristic.HeuristicEnum.heuristic;

/**
 * State pattern that record the chosen Heuristic
 */
public class HeuristicContext {

    private HeuristicEnum state;

    public void setState(String state){
        this.state = heuristic(state);
    }

    public Heuristic getHeuristic(int wValue) {
       switch (state) {
           case H1 : return new Heuristic1();
           case H2 : return new Heuristic2();
           default : return new Heuristic3(wValue);
       }
    }

    public boolean isHeuristic3() {
        return H3.equals(state);
    }
}
