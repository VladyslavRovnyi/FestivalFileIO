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
        var artistsByGenre = readArtistsFromFiles();
        var list = new ArrayList<Integer>();
        list.add(Integer.valueOf(1));
//        list.add(Double.valueOf(3));
//        list.add(new ReadDemoApp());

//        var line = """
//                first
//                second
//                third
//                """;

//        tryExample(artistsByGenre);
        readWrongArtist();
        System.out.println("FINISHED");
    }

    private static void readWrongArtist() {
        var line = "*** Spider Man";
        Artist artist = parseArtist(line, Genre.BLUES);
        System.out.println(artist);
    }

    private static void tryExample(Map<Genre, List<Artist>> artistsByGenre) {
        try {
            verifyArtists(artistsByGenre);
        } catch (RuntimeException e) {
            System.out.println("Runtime exception");
        }
        catch (Exception e) {
            System.out.println("In catch");
        } finally {
            System.out.println("Run always");
        }
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

    private static List<Artist> readArtistsFromFilesToList(){
        var artists = new ArrayList<Artist>();
        for (Genre genre : Genre.values()) {
            String fileName = genre.toString().toLowerCase() + ".txt";
            File file = new File(fileName);
            if (!file.exists()) {
                continue;
            }
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String rating = scanner.next();
                    String name = scanner.nextLine();
                    var stars = new Stars(rating.length());
                    Artist artist = new Artist(name, stars, genre);
                    artists.add(artist);
                }
            } catch (FileNotFoundException ex) {
                System.err.println("Failed to read file: " + fileName);
                ex.printStackTrace();
            }

        }
        return artists;
    }

    private static Artist parseArtist(String line, Genre genre) {
        String[] parts = line.split(" ", 2);
        int starCount = parts[0].length();
        Stars stars = new Stars(starCount);
        String name = parts[1];
        return new Artist(name, stars, genre);
    }

    private static void verifyArtists(Map<Genre, List<Artist>> artistsByGenre) {
        throw new RuntimeException("My error");
//        artistsByGenre.forEach((genre, artists) -> {
//            for (Artist artist : artists) {
//                System.out.println("Verified artist: " + artist);
//            }
//        });
    }
}
