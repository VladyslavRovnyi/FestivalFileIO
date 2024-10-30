package be.howest.ti.ooansd.festivalj.demo;

import be.howest.ti.ooansd.festivalj.domain.Artist;
import be.howest.ti.ooansd.festivalj.domain.Genre;
import be.howest.ti.ooansd.festivalj.domain.Stars;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WriteDemoApp {

    public static void main(String[] args) {
        List<Artist> artists = generateArtists(10);
        System.out.println(artists);
//        Map<Genre, List<Artist>> artistsByGenre = groupArtistsByGenre(artists);
//        writeArtistsToFiles(artistsByGenre);
    }

    private static List<Artist> generateArtists() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Frank",
                "Grace", "Hank", "Mike", "Bobby", "Judy", "Kyle",
                "Laura", "Mason", "Nina", "Oscar", "Paul", "Quincy",
                "Rachel", "Sam");
        Genre[] genres = Genre.values();
        Random random = new Random();
        List<Artist> artists = new ArrayList<>();

        for (String name : names) {
            Genre genre = genres[random.nextInt(genres.length)];
            Stars stars = new Stars(random.nextInt(5) + 1);  // Star rating from 1 to 5
            artists.add(new Artist(name, stars, genre));
        }
        return artists;
    }

    private static List<Artist> generateArtists(int size) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Frank",
                "Grace", "Hank", "Mike", "Bobby", "Judy", "Kyle",
                "Laura", "Mason", "Nina", "Oscar", "Paul", "Quincy",
                "Rachel", "Sam");
        Genre[] genres = Genre.values();
        List<Artist> artists = new ArrayList<>();

        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < size; i++) {
            var name = names.get(random.nextInt(names.size()));
            Genre genre = genres[random.nextInt(genres.length)];
            Stars stars = new Stars(random.nextInt(5) + 1);  // Star rating from 1 to 5
            artists.add(new Artist(name, stars, genre));
        }
        return artists;
    }

    private static Map<Genre, List<Artist>> groupArtistsByGenre(List<Artist> artists) {
        Map<Genre, List<Artist>> artistsByGenre = new HashMap<>();
        for (Artist artist : artists) {
            artistsByGenre.computeIfAbsent(artist.getGenre(), k -> new ArrayList<>()).add(artist);
        }
        return artistsByGenre;
    }

    private static void writeArtistsToFiles(Map<Genre, List<Artist>> artistsByGenre) {
        for (Map.Entry<Genre, List<Artist>> entry : artistsByGenre.entrySet()) {
            String fileName = entry.getKey().toString().toLowerCase() + ".txt";
            try (PrintStream out = new PrintStream(fileName)) {
                for (Artist artist : entry.getValue()) {
                    out.println(formatArtistForFile(artist));
                }
            } catch (FileNotFoundException ex) {
                System.err.println("Failed to write to file: " + fileName);
                ex.printStackTrace();
            }
        }
    }

    private static String formatArtistForFile(Artist artist) {
        String stars = "*".repeat(artist.getRating().asInt());
        if (artist.getName().equals("Alice") && artist.getRating().asInt() == 3) {
            return "*** Alice";
        } else {
            return stars + " " + artist.getName();
        }
    }
}
