package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PreparingRunway {
    private SimpleIntegerProperty shiftId;
    private SimpleIntegerProperty shiftNumber;
    private SimpleStringProperty runwayNumber;

    public PreparingRunway(int shiftId, int shiftNumber, String runwayNumber) {
        this.shiftId = new SimpleIntegerProperty(shiftId);
        this.shiftNumber = new SimpleIntegerProperty(shiftNumber);
        this.runwayNumber = new SimpleStringProperty(runwayNumber);
    }

    public int getShiftId() {
        return shiftId.get();
    }

    public SimpleIntegerProperty shiftIdProperty() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId.set(shiftId);
    }

    public int getShiftNumber() {
        return shiftNumber.get();
    }

    public SimpleIntegerProperty shiftNumberProperty() {
        return shiftNumber;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber.set(shiftNumber);
    }

    public String getRunwayNumber() {
        return runwayNumber.get();
    }

    public SimpleStringProperty runwayNumberProperty() {
        return runwayNumber;
    }

    public void setRunwayNumber(String runwayNumber) {
        this.runwayNumber.set(runwayNumber);
    }
}

