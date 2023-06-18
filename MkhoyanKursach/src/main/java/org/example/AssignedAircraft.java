package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AssignedAircraft {
    private SimpleIntegerProperty raceId;
    private SimpleStringProperty flightNumber;
    private SimpleStringProperty tailNumber;

    public AssignedAircraft(int raceId, String flightNumber, String tailNumber) {
        this.raceId = new SimpleIntegerProperty(raceId);
        this.flightNumber = new SimpleStringProperty(flightNumber);
        this.tailNumber = new SimpleStringProperty(tailNumber);
    }

    public int getRaceId() {
        return raceId.get();
    }

    public SimpleIntegerProperty raceIdProperty() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId.set(raceId);
    }

    public String getFlightNumber() {
        return flightNumber.get();
    }

    public SimpleStringProperty flightNumberProperty() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber.set(flightNumber);
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
}
