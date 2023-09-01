package org.choise.FirstRestApp.controllers;

import jakarta.validation.Valid;
import org.choise.FirstRestApp.dto.MeasurementDTO;
import org.choise.FirstRestApp.dto.MeasurementsList;
import org.choise.FirstRestApp.models.Measurement;
import org.choise.FirstRestApp.services.MeasurementsService;
import org.choise.FirstRestApp.util.MeasurementNotCreatedException;
import org.choise.FirstRestApp.util.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping()
    public MeasurementsList showAll() {
        List<MeasurementDTO> rsl =  measurementsService.findAll().stream().map(this::convertToMeasurementDTO)
                .toList();
        return new MeasurementsList(rsl);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO,
                                             BindingResult bindingResult) {
        Measurement measurement = convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurement, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            bindingResult.getFieldErrors().stream()
                    .map(fieldError -> stringBuilder.append(fieldError.getField())
                            .append(" - ").append(fieldError.getDefaultMessage())
                            .append(";"));
            throw new MeasurementNotCreatedException(stringBuilder.toString());
        }
        measurementsService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public int rainyDaysCount() {
        return measurementsService.countRainyDays();
    }


    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        Measurement rsl = modelMapper.map(measurementDTO, Measurement.class);
        rsl.setDate_time(new Timestamp(System.currentTimeMillis()));
        return rsl;
    }
}
