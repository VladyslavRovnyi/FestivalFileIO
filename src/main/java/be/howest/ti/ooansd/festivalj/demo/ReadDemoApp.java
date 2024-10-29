package be.howest.ti.ooansd.festivalj.demo;

import be.howest.ti.ooansd.festivalj.domain.Artist;
import be.howest.ti.ooansd.festivalj.domain.Genre;
import be.howest.ti.ooansd.festivalj.domain.Stars;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReadDemoApp {

    public static void main(String[] args) {
        Map<Genre, List<Artist>> artistsByGenre = readArtistsFromFiles();
        verifyArtists(artistsByGenre);
    }

    private static Map<Genre, List<Artist>> readArtistsFromFiles() {
        Map<Genre, List<Artist>> artistsByGenre = new HashMap<>();
        for (Genre genre : Genre.values()) {
            String fileName = genre.toString().toLowerCase() + ".txt";
            File file = new File(fileName);
            if (file.exists()) {
                List<Artist> artists = new ArrayList<>();
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        artists.add(parseArtist(line, genre));
                    }
                    artistsByGenre.put(genre, artists);
                } catch (FileNotFoundException ex) {
                    System.err.println("Failed to read file: " + fileName);
                    ex.printStackTrace();
                }
            }
        }
        return artistsByGenre;
    }

    private static Artist parseArtist(String line, Genre genre) {
        String[] parts = line.split(" ", 2);
        int starCount = parts[0].length();
        Stars stars = new Stars(starCount);
        String name = parts[1];
        return new Artist(name, stars, genre);
    }

    private static void verifyArtists(Map<Genre, List<Artist>> artistsByGenre) {
        artistsByGenre.forEach((genre, artists) -> {
            for (Artist artist : artists) {
                System.out.println("Verified artist: " + artist);
            }
        });
    }
}
