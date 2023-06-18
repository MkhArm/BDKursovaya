package org.example.hibernate;

import java.util.Objects;

public class Shift {

    private int shift_number;
    private String period_day;

    public Shift(int shift_number, String period_day) {
        this.setShift_number(shift_number);
        this.setPeriod_day(period_day);
    }

    // region // setters & getters

    public int getShift_number() {
        return shift_number;
    }

    public String getPeriod_day() {
        return period_day;
    }

    public void setShift_number(int shift_number) {
        this.shift_number = shift_number;
    }

    public void setPeriod_day(String period_day) {
        this.period_day = period_day;
    }

    // endregion

    @Override
    public String toString() {
        String msg = getClass().getSimpleName() + " [" +
                "shift_number = '" + shift_number + '\'' +
                ", period_day = '" + period_day + '\'' + "]";
        return msg;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shift_number);
    }
}
