package be.howest.ti.ooansd.festivalj.domain;

public enum TimeBlock {
    MORNING,
    AFTERNOON,
    EVENING,
    NIGHT;

    public boolean hasOverlapWith(TimeBlock timeBlock) {
        return this == timeBlock;
    }
}
