package org.choise.FirstRestApp.controllers;

import jakarta.validation.Valid;
import org.choise.FirstRestApp.dto.SensorDTO;
import org.choise.FirstRestApp.models.Sensor;
import org.choise.FirstRestApp.services.SensorsService;
import org.choise.FirstRestApp.util.SensorNotCreatedException;
import org.choise.FirstRestApp.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorsController(SensorsService sensorsService,
                             ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult) {
        Sensor sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            bindingResult.getFieldErrors().stream()
                    .map(error -> errorMsg.append(error.getField())
                            .append(" - ")
                            .append(error.getDefaultMessage())
                            .append(";"));
            throw new SensorNotCreatedException(errorMsg.toString());
        }
        sensorsService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        Sensor sensor = modelMapper.map(sensorDTO, Sensor.class);
        return sensor; // конвертируем DTO в нашу модель - все поля что есть в DTO подставятся в класс модели
    }

}
