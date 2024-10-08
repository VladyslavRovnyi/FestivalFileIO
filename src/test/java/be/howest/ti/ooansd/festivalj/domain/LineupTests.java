package be.howest.ti.ooansd.festivalj.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LineupTests {

    private Artist alice;
    private Artist bob;
    private Artist carol;

    private Lineup lineup;

    @BeforeEach
    void initArtists() {
        alice = new Artist("Alice with the Accordion", new Stars(3), Genre.ACOUSTIC);
        bob = new Artist("Bob with the Banjo", new Stars(4), Genre.BLUES);
        carol = new Artist("Carol with the Clarinet", new Stars(5), Genre.CLASSICAL);

        lineup = new Lineup();
    }

    @Test
    void aNewLineUpIsEmpty() {
        assertEquals(Collections.emptyList(), lineup.getPerformances());
    }

    @Test
    void weCanAddPerformancesToALineup() {
        Performance p = new Performance(Stage.BOILER, TimeBlock.AFTERNOON, bob);
        lineup.add(p);
        assertEquals(Collections.singletonList(p), lineup.getPerformances());
    }

    @Test
    void performancesInALineupAreInChronoOrder() {
        Performance afternoonPerformance = new Performance(Stage.BOILER, TimeBlock.AFTERNOON, alice);
        Performance nightPerformance = new Performance(Stage.BOILER, TimeBlock.NIGHT, bob);
        Performance morningPerformance = new Performance(Stage.BOILER, TimeBlock.MORNING, carol);

        lineup.add(afternoonPerformance);
        lineup.add(nightPerformance);
        lineup.add(morningPerformance);

        assertEquals(
                List.of(morningPerformance, afternoonPerformance, nightPerformance),
                lineup.getPerformances()
        );
    }

    @Test
    void cannotCombineMultipleStagesInOneLineup() {

        Performance p1 = new Performance(Stage.BOILER, TimeBlock.AFTERNOON, alice);
        Performance p2 = new Performance(Stage.CLUB, TimeBlock.NIGHT, bob);
        lineup.add(p1);
        assertThrows(IllegalArgumentException.class, () -> lineup.add(p2));
    }

    @Test
    void cannotAddPerformancesWithOverlapToSameLineup() {

        lineup.add(new Performance(Stage.BOILER, TimeBlock.AFTERNOON, alice));
        Performance p2 = new Performance(Stage.BOILER, TimeBlock.AFTERNOON, bob);
        assertThrows(IllegalArgumentException.class, () -> lineup.add(p2));
    }







}
