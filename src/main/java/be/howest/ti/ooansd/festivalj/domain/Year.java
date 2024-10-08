package be.howest.ti.ooansd.festivalj.domain;

public class Year {

    private final int startYear;

    public Year(int year) {
        this.startYear = year;
    }

    public int asInt() {
        return startYear;
    }

    public String toString() {
        return String.format("%d-%d", startYear, startYear +1);
    }
}
