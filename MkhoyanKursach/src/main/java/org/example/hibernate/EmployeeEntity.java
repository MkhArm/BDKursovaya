package org.example.hibernate;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "Employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personnel_number")
    private int personnelNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "education")
    private String education;

    @Column(name = "position")
    private String position;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    // Constructors, getters, setters
}
