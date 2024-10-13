package be.howest.ti.ooansd.festivalj.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lineup implements Serializable {


    private final List<Performance> performances = new ArrayList<>();



    public List<Performance> getPerformances() {

        return Collections.unmodifiableList(performances);
    }

    public void add(Performance performance) {
        if (performances.isEmpty()) {
            performances.add(performance);
        } else if (!sameStage(performance)) {
            throw new IllegalArgumentException("Not same stage");
        } else if (causesOverlap(performance)) {
            throw new IllegalArgumentException("There is some overlap (time)");
        } else {
            performances.add(performance);
            performances.sort(Performance::compareTo);
        }
    }

    private boolean causesOverlap(Performance performance) {
        TimeBlock when = performance.getWhen();
        for(Performance p : performances) {
            if (p.getWhen().hasOverlapWith(when)) {
                return true;
            }
        }
        return false;
    }

    private boolean sameStage(Performance performance) {
        return performances.get(0).getWhere() == performance.getWhere();
    }

    public Stage getWhere() {
        if (performances.isEmpty()) {
            throw new IllegalStateException();
        }
        return performances.get(0).getWhere();
    }

    public void remove(Performance performance) {
        performances.remove(performance);
    }
}
