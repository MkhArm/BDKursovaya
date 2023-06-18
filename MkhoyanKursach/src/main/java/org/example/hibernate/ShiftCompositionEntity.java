package org.example.hibernate;

import jakarta.persistence.*;


@Entity
@Table(name = "ShiftComposition")
public class ShiftCompositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_id")
    private int shiftId;

    @ManyToOne
    @JoinColumn(name = "shift_number")
    private ShiftEntity shift;

    @ManyToOne
    @JoinColumn(name = "personnel_number")
    private EmployeeEntity employee;

    // Constructors, getters, setters
}
