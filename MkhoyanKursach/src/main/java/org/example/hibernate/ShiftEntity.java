package org.example.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "shift")
public class ShiftEntity {

    @Id
    public int shift_number;

    @Column (name = "period_day")
    public String period_day;


    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [period_day = '" + period_day + "', " +
                "shift_number=" + shift_number + " ]";
    }
}
