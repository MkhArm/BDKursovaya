package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AircraftBeing {
    private SimpleIntegerProperty aircraftId;
    private SimpleStringProperty tailNumber;
    private SimpleIntegerProperty shiftNumber;

    public AircraftBeing(int aircraftId, String tailNumber, int shiftNumber) {
        this.aircraftId = new SimpleIntegerProperty(aircraftId);
        this.tailNumber = new SimpleStringProperty(tailNumber);
        this.shiftNumber = new SimpleIntegerProperty(shiftNumber);
    }

    public int getAircraftId() {
        return aircraftId.get();
    }

    public SimpleIntegerProperty aircraftIdProperty() {
        return aircraftId;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId.set(aircraftId);
    }

    public String getTailNumber() {
        return tailNumber.get();
    }

    public SimpleStringProperty tailNumberProperty() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber.set(tailNumber);
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
}

