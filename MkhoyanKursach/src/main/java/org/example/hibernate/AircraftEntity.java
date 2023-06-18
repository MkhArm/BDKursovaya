package org.example.hibernate;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "Aircraft")
public class AircraftEntity {
    @Id
    @Column(name = "tail_number")
    private String tailNumber;

    @Column(name = "aircraft_type")
    private String aircraftType;

    @Column(name = "runway_type")
    private String runwayType;

    @Column(name = "load_capacity")
    private int loadCapacity;

    @Column(name = "readiness")
    private boolean readiness;

    // Constructors, getters, setters
}
