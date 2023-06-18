package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Employee {
    private SimpleIntegerProperty personnelNumber;
    private SimpleStringProperty fullName;
    private SimpleStringProperty education;
    private SimpleStringProperty position;
    private SimpleObjectProperty<Date> startDate;

    public Employee(int personnelNumber, String fullName, String education, String position, Date startDate) {
        this.personnelNumber = new SimpleIntegerProperty(personnelNumber);
        this.fullName = new SimpleStringProperty(fullName);
        this.education = new SimpleStringProperty(education);
        this.position = new SimpleStringProperty(position);
        this.startDate = new SimpleObjectProperty<>(startDate);
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

    public String getFullName() {
        return fullName.get();
    }

    public SimpleStringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public String getEducation() {
        return education.get();
    }

    public SimpleStringProperty educationProperty() {
        return education;
    }

    public void setEducation(String education) {
        this.education.set(education);
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public Date getStartDate() {
        return startDate.get();
    }

    public SimpleObjectProperty<Date> startDateProperty() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate.set(startDate);
    }
}

