package be.howest.ti.ooansd.festivalj.domain;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Artist {

    private static final Logger LOGGER = Logger.getLogger(Artist.class.getName());

    private final String name;
    private final Stars rating;
    private final Genre genre;

    public Artist(String name, Stars rating, Genre genre) {
        LOGGER.log(Level.INFO, ()->"Creating artist with name: " + name + ".");
        this.name = name;
        this.rating = rating;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public Stars getRating() {
        return rating;
    }

    public Genre getGenre() {
        return genre;
    }

    public boolean canPerformAtTheSameTime(Artist who) {
        return !this.equals(who);
    }
}
