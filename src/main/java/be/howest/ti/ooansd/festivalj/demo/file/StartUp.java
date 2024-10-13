package be.howest.ti.ooansd.festivalj.demo.file;

import be.howest.ti.ooansd.festivalj.domain.*;
import be.howest.ti.ooansd.festivalj.magic.MagicBox;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartUp {

    private static final Logger LOGGER = Logger.getLogger(StartUp.class.getName());


    public static void main(String[] args) {
        System.out.println("Hello World!");
        MagicBox magic = new MagicBox();

        List<Artist> artistList = magic.getArtists();

        Festival festival = new Festival("HowestSummerBreez", new Year(2025));
        festival.addPerformance(new Performance(Stage.MAIN, TimeBlock.EVENING, artistList.get(0)));
        festival.addPerformance(new Performance(Stage.MAIN, TimeBlock.NIGHT, artistList.get(1)));
        festival.addPerformance(new Performance(Stage.DANCE_HALL, TimeBlock.NIGHT, artistList.get(2)));

        /*
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./festival.txt"))) {

            out.writeObject(festival);

        } catch (IOException e) {
            LOGGER.log(Level.INFO, "Failed to write", e);
        }*/

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("./festival.txt"))) {

            Festival newFestival = (Festival) in.readObject();
            System.out.println(newFestival);

        } catch (IOException e) {
            LOGGER.log(Level.INFO, "Failed to write", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main2(String[] args) {

        System.out.println("Hello World!");

        File f = new File("./");
        System.out.println(f.getAbsolutePath());
        System.out.println(f.isDirectory());
        for (File subF : f.listFiles()) {
            System.out.println((subF.isDirectory()?"dir" :  "f  ") + subF.getName());
        }

    }


    public static void main1(String[] args) {
        System.out.println("Hello World!");
        MagicBox magic = new MagicBox();

        List<Artist> artistList = magic.getArtists();

        /*
        for (Artist artist : artistList) {
            try (PrintStream out = new PrintStream(new FileOutputStream("./output.txt", true))) {
                out.println(format(artist));
            } catch (FileNotFoundException ex) {
                LOGGER.log(Level.INFO, "Failed to write", ex);
            }
        }
         */

        try (PrintStream out = new PrintStream(new FileOutputStream("./output.txt"))) {
            printAll(artistList, out);
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.INFO, "Failed to write", ex);
        }



        try (Scanner in = new Scanner(new FileInputStream("./output.txt"))) {
            Artist a = parseArtist(in.nextLine());
            System.out.println(a);
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.INFO, "Failed to read", ex);
        }
    }

    private static void printAll(List<Artist> artistList, PrintStream out) {
        for (Artist artist : artistList) {
            out.println(format(artist));
        }
    }


    private static void printAllArtists(List<Artist> artistList, PrintStream out) {
        for (Artist artist : artistList) {

            out.println(format(artist));
        }
    }

    private static String format(Artist artist) {
        return artist.getRating().asInt()  +   " " + artist.getGenre()+" " + artist.getName();
    }

    private static Artist parseArtist(String txt) {
        String[] allData = txt.split(" ", 3);
        String rating = allData[0];
        String genre = allData[1];
        String name = allData[2];
        return new Artist(name, new Stars(Integer.parseInt(rating)), Genre.valueOf(genre));
    }
}
