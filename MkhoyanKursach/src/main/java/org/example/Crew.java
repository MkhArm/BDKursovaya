package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Crew {
    private SimpleIntegerProperty crewNumber;
    private SimpleStringProperty tailNumber;
    private SimpleIntegerProperty personnelNumber;

    public Crew(int crewNumber, String tailNumber, int personnelNumber) {
        this.crewNumber = new SimpleIntegerProperty(crewNumber);
        this.tailNumber = new SimpleStringProperty(tailNumber);
        this.personnelNumber = new SimpleIntegerProperty(personnelNumber);
    }

    public int getCrewNumber() {
        return crewNumber.get();
    }

    public SimpleIntegerProperty crewNumberProperty() {
        return crewNumber;
    }

    public void setCrewNumber(int crewNumber) {
        this.crewNumber.set(crewNumber);
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

    public int getPersonnelNumber() {
        return personnelNumber.get();
    }

    public SimpleIntegerProperty personnelNumberProperty() {
        return personnelNumber;
    }

    public void setPersonnelNumber(int personnelNumber) {
        this.personnelNumber.set(personnelNumber);
    }
}

