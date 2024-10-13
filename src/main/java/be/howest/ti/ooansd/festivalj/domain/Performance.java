package be.howest.ti.ooansd.festivalj.domain;

import java.io.Serializable;
import java.util.Objects;

public class Performance implements Comparable<Performance>, Serializable {

    private final Stage where;
    private final TimeBlock when;
    private final Artist who;

    public Performance(Stage where, TimeBlock when, Artist who) {
        this.where = where;
        this.when = when;
        this.who = who;
    }

    @Override
    public int compareTo(Performance o) {
        return this.when.compareTo(o.when);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Performance that = (Performance) o;
        return where == that.where && when == that.when && Objects.equals(who, that.who);
    }

    @Override
    public int hashCode() {
        return Objects.hash(where, when, who);
    }

    public Stage getWhere() {
        return where;
    }

    public TimeBlock getWhen() {
        return when;
    }

    public Artist getWho() {
        return who;
    }

    @Override
    public String toString() {
        return when + ":" + who;
    }
}
