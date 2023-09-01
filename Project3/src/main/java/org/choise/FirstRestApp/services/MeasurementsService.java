package org.choise.FirstRestApp.services;

import org.choise.FirstRestApp.models.Measurement;
import org.choise.FirstRestApp.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private MeasurementsRepository measurementsRepository;
    private SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    public int countRainyDays() {
        return measurementsRepository.findByRainingTrue().size();
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setDate_time(new Timestamp(System.currentTimeMillis()));

        measurementsRepository.save(measurement);
    }
}
