package org.example.hibernate;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "Flight")
public class FlightEntity {
    @Id
    @Column(name = "flight_number")
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "runway_number")
    private RunwayEntity runway;

    @Column(name = "range")
    private int range;

    @Column(name = "departure_date")
    private Date departureDate;

    // Constructors, getters, setters
}
