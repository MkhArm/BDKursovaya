package org.example;

import javafx.beans.property.SimpleIntegerProperty;

public class ShiftComposition {
    private SimpleIntegerProperty shiftId;
    private SimpleIntegerProperty shiftNumber;
    private SimpleIntegerProperty personnelNumber;

    public ShiftComposition(int shiftId, int shiftNumber, int personnelNumber) {
        this.shiftId = new SimpleIntegerProperty(shiftId);
        this.shiftNumber = new SimpleIntegerProperty(shiftNumber);
        this.personnelNumber = new SimpleIntegerProperty(personnelNumber);
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

