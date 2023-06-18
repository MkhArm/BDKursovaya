package org.example.hibernate;

import jakarta.persistence.*;


@Entity
@Table(name = "AssignedAircraft")
public class AssignedAircraftEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "race_id")
    private int raceId;

    @ManyToOne
    @JoinColumn(name = "flight_number")
    private FlightEntity flight;

    @ManyToOne
    @JoinColumn(name = "tail_number")
    private AircraftEntity aircraft;

    // Constructors, getters, setters
}
