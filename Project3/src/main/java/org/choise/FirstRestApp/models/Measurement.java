package org.choise.FirstRestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.Cascade;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Entity
@Table(name = "measurements")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_measurements")
    private int id;

    @Min(value = -100, message = "Temperature should be higher then -100")
    @Max(value = 100, message = "Temperature should be lesser then 100")
    @Column(name = "temperature")
    private double value;

    @Column(name = "raining")
    private boolean raining;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time")
    private Timestamp date_time;

    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;

    public Measurement() {
    }

    public Measurement(double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor name) {
        this.sensor= name;
    }
}
