package org.choise.FirstRestApp.util;

import org.choise.FirstRestApp.models.Measurement;
import org.choise.FirstRestApp.models.Sensor;
import org.choise.FirstRestApp.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class MeasurementValidator implements Validator {

    private final SensorsService sensorService;

    @Autowired
    public MeasurementValidator(SensorsService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurement.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurement measurement = (Measurement) o;

        if (measurement.getSensor() == null) {
            return;
        }
        Optional<Sensor> sensor = sensorService.findByName(measurement.getSensor().getName());
        if (sensor.isEmpty())
            errors.rejectValue("sensor", "No sensors with name '" + measurement.getSensor().getName() + "' exist");
        else
            measurement.setSensor(sensor.orElse(null));
    }
}
