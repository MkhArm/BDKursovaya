package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Date;

public class Flight {
    private SimpleStringProperty flightNumber;
    private SimpleStringProperty runwayNumber;
    private SimpleIntegerProperty range;
    private SimpleObjectProperty<Date> departureDate;

    public Flight(String flightNumber, String runwayNumber, int range, Date departureDate) {
        this.flightNumber = new SimpleStringProperty(flightNumber);
        this.runwayNumber = new SimpleStringProperty(runwayNumber);
        this.range = new SimpleIntegerProperty(range);
        this.departureDate = new SimpleObjectProperty<>(departureDate);
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

    public String getRunwayNumber() {
        return runwayNumber.get();
    }

    public SimpleStringProperty runwayNumberProperty() {
        return runwayNumber;
    }

    public void setRunwayNumber(String runwayNumber) {
        this.runwayNumber.set(runwayNumber);
    }

    public int getRange() {
        return range.get();
    }

    public SimpleIntegerProperty rangeProperty() {
        return range;
    }

    public void setRange(int range) {
        this.range.set(range);
    }

    public Date getDepartureDate() {
        return departureDate.get();
    }

    public SimpleObjectProperty<Date> departureDateProperty() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate.set(departureDate);
    }
}