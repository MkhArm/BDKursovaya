package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Runway {
    private SimpleStringProperty runwayNumber;
    private SimpleIntegerProperty length;
    private SimpleStringProperty runwayClass;
    private SimpleStringProperty runwayType;

    public Runway(String runwayNumber, int length, String runwayClass, String runwayType) {
        this.runwayNumber = new SimpleStringProperty(runwayNumber);
        this.length = new SimpleIntegerProperty(length);
        this.runwayClass = new SimpleStringProperty(runwayClass);
        this.runwayType = new SimpleStringProperty(runwayType);
    }

    public String getRunwayNumber() {
        return runwayNumber.get();
    }

    public void setRunwayNumber(String runwayNumber) {
        this.runwayNumber.set(runwayNumber);
    }

    public SimpleStringProperty runwayNumberProperty() {
        return runwayNumber;
    }

    public int getLength() {
        return length.get();
    }

    public void setLength(int length) {
        this.length.set(length);
    }

    public SimpleIntegerProperty lengthProperty() {
        return length;
    }

    public String getRunwayClass() {
        return runwayClass.get();
    }

    public void setRunwayClass(String runwayClass) {
        this.runwayClass.set(runwayClass);
    }

    public SimpleStringProperty runwayClassProperty() {
        return runwayClass;
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
}