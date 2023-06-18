package org.example.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Runway")
public class RunwayEntity {
    @Id
    @Column(name = "runway_number")
    private String runwayNumber;

    @Column(name = "length")
    private int length;

    @Column(name = "class")
    private String runwayClass;

    @Column(name = "runway_type")
    private String runwayType;

    // Constructors, getters, setters
}
