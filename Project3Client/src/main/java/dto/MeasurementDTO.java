package dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

public class MeasurementDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_measurement;

    @Min(value = -100, message = "Temperature should be higher then -100")
    @Max(value = 100, message = "Temperature should be lesser then 100")
    private double value;

    @NotEmpty
    private boolean raining;

    private String sensor;

    public int getId_measurement() {
        return id_measurement;
    }

    public void setId_measurement(int id_measurement) {
        this.id_measurement = id_measurement;
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

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                '}';
    }
}
