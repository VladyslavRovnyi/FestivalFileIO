package be.howest.ti.ooansd.festivalj.domain;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Festival {

    private final Map<Stage, Lineup> performances = new EnumMap<>(Stage.class);
    private final String name;
    private final Year year;

    public Festival(String name, Year year) {
        this.name = name;
        this.year = year;
    }

    public String toString() {
        return name + " (" + year + ")";
    }


    public Set<Performance> getPerformances() {
        return performances.values().stream()
                .flatMap(l->l.getPerformances().stream())
                .collect(Collectors.toSet());
    }

    public void addPerformance(Performance performance) {
        Lineup l = performances.computeIfAbsent(performance.getWhere(), s->new Lineup());
        if (hasOverlap(performance)) {
            throw new IllegalArgumentException("Overlap");
        }
        l.add(performance);
    }

    private boolean hasOverlap(Performance performance) {
        return getPerformances().stream()
                .filter(p->!p.getWho().canPerformAtTheSameTime(performance.getWho()))
                .map(Performance::getWhen)
                .distinct()
                .anyMatch(t->t.hasOverlapWith(performance.getWhen()));
    }

    public void remove(Performance performance) {
        performances.get(performance.getWhere()).remove(performance);
    }
}
