package be.howest.ti.ooansd.festivalj.domain;

public class Stars {

    private final int rating;

    public Stars(int rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5.");
        }
        this.rating = rating;
    }

    public int asInt() {
        return rating;
    }

    public String toString() {
        return "*".repeat(rating);
    }

}