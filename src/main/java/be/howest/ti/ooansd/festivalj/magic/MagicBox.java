package be.howest.ti.ooansd.festivalj.magic;

import be.howest.ti.ooansd.festivalj.domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MagicBox {

    private Festival festival;

    public void initializeFestival(String name, Year year) {
        festival = new Festival(name, year);
    }

    private final List<Artist> artists = new ArrayList<>(List.of(
            new Artist("Alice with the Accordion", new Stars(3), Genre.ACOUSTIC),
    new Artist("Bob with the Banjo", new Stars(4), Genre.BLUES),
    new Artist("Carol with the Clarinet", new Stars(5), Genre.CLASSICAL)

    ));

    public List<Artist> getArtists() {
        return Collections.unmodifiableList(artists);
    }

    public void createArtist(String name) {
        artists.add(new Artist(name, new Stars(0), null));
    }

    public boolean isAvailable(Performance performance) {
        try {
            festival.addPerformance(performance);
            festival.remove(performance);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public void addPerformance(Performance performance) {
        festival.addPerformance(performance);
    }


}
