package org.example;

import javafx.beans.property.SimpleIntegerProperty;

public class CrewComposition {
    private SimpleIntegerProperty employeeId;
    private SimpleIntegerProperty crewNumber;
    private SimpleIntegerProperty personnelNumber;

    public CrewComposition(int employeeId, int crewNumber, int personnelNumber) {
        this.employeeId = new SimpleIntegerProperty(employeeId);
        this.crewNumber = new SimpleIntegerProperty(crewNumber);
        this.personnelNumber = new SimpleIntegerProperty(personnelNumber);
    }

    public int getEmployeeId() {
        return employeeId.get();
    }

    public SimpleIntegerProperty employeeIdProperty() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId.set(employeeId);
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
