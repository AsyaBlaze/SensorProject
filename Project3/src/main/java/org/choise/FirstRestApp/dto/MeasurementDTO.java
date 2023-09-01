package org.choise.FirstRestApp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.choise.FirstRestApp.models.Sensor;

public class MeasurementDTO {
    @Min(value = -100, message = "Temperature should be higher then -100")
    @Max(value = 100, message = "Temperature should be lesser then 100")
    private double value;

    @NotNull
    private boolean raining;

    @NotNull
    private SensorDTO sensor;

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

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO name) {
        this.sensor = name;
    }
}
