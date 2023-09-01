package dto;

import java.util.List;

public class MeasurementsList {
    List<MeasurementDTO> measurementsList;

    public MeasurementsList(List<MeasurementDTO> measurementsList) {
        this.measurementsList = measurementsList;
    }

    public List<MeasurementDTO> getMeasurementsList() {
        return measurementsList;
    }

    public void setMeasurementsList(List<MeasurementDTO> measurementsList) {
        this.measurementsList = measurementsList;
    }
}
