package org.example.hibernate;

import jakarta.persistence.*;


@Entity
@Table(name = "CrewComposition")
public class CrewCompositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;

    @ManyToOne
    @JoinColumn(name = "crew_number")
    private CrewEntity crew;

    @ManyToOne
    @JoinColumn(name = "personnel_number")
    private EmployeeEntity employee;

    // Constructors, getters, setters
}
