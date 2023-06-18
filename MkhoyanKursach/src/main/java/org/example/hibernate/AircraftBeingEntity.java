package org.example.hibernate;

import jakarta.persistence.*;


@Entity
@Table(name = "AircraftBeing")
public class AircraftBeingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aircraft_id")
    private int aircraftId;

    @ManyToOne
    @JoinColumn(name = "tail_number")
    private AircraftEntity aircraft;

    @ManyToOne
    @JoinColumn(name = "shift_number")
    private ShiftEntity shift;

    // Constructors, getters, setters
}
