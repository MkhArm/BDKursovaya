package org.example;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Aircraft {
    private SimpleStringProperty tailNumber;
    private SimpleStringProperty aircraftType;
    private SimpleStringProperty runwayType;
    private SimpleIntegerProperty loadCapacity;
    private SimpleBooleanProperty readiness;

    public Aircraft(String tailNumber, String aircraftType, String runwayType, int loadCapacity, boolean readiness) {
        this.tailNumber = new SimpleStringProperty(tailNumber);
        this.aircraftType = new SimpleStringProperty(aircraftType);
        this.runwayType = new SimpleStringProperty(runwayType);
        this.loadCapacity = new SimpleIntegerProperty(loadCapacity);
        this.readiness = new SimpleBooleanProperty(readiness);
    }

    public String getTailNumber() {
        return tailNumber.get();
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber.set(tailNumber);
    }

    public SimpleStringProperty tailNumberProperty() {
        return tailNumber;
    }

    public String getAircraftType() {
        return aircraftType.get();
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType.set(aircraftType);
    }

    public SimpleStringProperty aircraftTypeProperty() {
        return aircraftType;
    }

    public String getRunwayType() {
        return runwayType.get();
    }

    public void setRunwayType(String runwayType) {
        this.runwayType.set(runwayType);
    }

    public SimpleStringProperty runwayTypeProperty() {
        return runwayType;
    }

    public int getLoadCapacity() {
        return loadCapacity.get();
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity.set(loadCapacity);
    }

    public SimpleIntegerProperty loadCapacityProperty() {
        return loadCapacity;
    }

    public boolean isReadiness() {
        return readiness.get();
    }

    public void setReadiness(boolean readiness) {
        this.readiness.set(readiness);
    }

    public SimpleBooleanProperty readinessProperty() {
        return readiness;
    }
}
