package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Shift {
    private SimpleIntegerProperty shiftNumber;
    private SimpleStringProperty periodDay;

    public Shift(int shiftNumber, String periodDay) {
        this.shiftNumber = new SimpleIntegerProperty(shiftNumber);
        this.periodDay = new SimpleStringProperty(periodDay);
    }

    public int getShiftNumber() {
        return shiftNumber.get();
    }

    public SimpleIntegerProperty shiftNumberProperty() {
        return shiftNumber;
    }

    public String getPeriodDay() {
        return periodDay.get();
    }

    public SimpleStringProperty periodDayProperty() {
        return periodDay;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber.set(shiftNumber);
    }

    public void setPeriodDay(String periodDay) {
        this.periodDay.set(periodDay);
    }
}