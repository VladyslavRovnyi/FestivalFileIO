package be.howest.ti.ooansd.festivalj.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FestivalTests {

    private Artist alice;
    private Artist bob;
    private Artist carol;
    private Festival festival;

    @BeforeEach
    void initArtists() {
        alice = new Artist("Alice with the Accordion", new Stars(3), Genre.ACOUSTIC);
        bob = new Artist("Bob with the Banjo", new Stars(4), Genre.BLUES);
        carol = new Artist("Carol with the Clarinet", new Stars(5), Genre.CLASSICAL);
        festival = new Festival("test", new Year(2021));
    }

    @Test
    void inAFestivalDifferentArtistCanPerformAtTheSameTime() {

        final Performance p1 = new Performance(Stage.BOILER, TimeBlock.EVENING, alice);
        final Performance p2 = new Performance(Stage.CLUB, TimeBlock.EVENING, bob);
        final Performance p3 = new Performance(Stage.DANCE_HALL, TimeBlock.EVENING, carol);

        festival.addPerformance(p1);
        festival.addPerformance(p2);
        festival.addPerformance(p3);

        assertEquals(
                Set.of(p1,p2,p3), festival.getPerformances()
        );
    }

    @Test
    void inAFestivalTheSameArtistCannotPerformAtTheSameTime() {

        final Performance p1 = new Performance(Stage.BOILER, TimeBlock.EVENING, alice);
        final Performance p2 = new Performance(Stage.CLUB, TimeBlock.EVENING, alice);

        festival.addPerformance(p1);

        assertThrows(IllegalArgumentException.class, () -> festival.addPerformance(p2));
    }



}
