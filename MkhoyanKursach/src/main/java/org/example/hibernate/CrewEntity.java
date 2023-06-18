package org.example.hibernate;

import jakarta.persistence.*;


@Entity
@Table(name = "Crew")
public class CrewEntity {
    @Id
    @Column(name = "crew_number")
    private int crewNumber;

    @ManyToOne
    @JoinColumn(name = "tail_number")
    private AircraftEntity aircraft;

    @ManyToOne
    @JoinColumn(name = "personnel_number")
    private EmployeeEntity employee;

    // Constructors, getters, setters
}
