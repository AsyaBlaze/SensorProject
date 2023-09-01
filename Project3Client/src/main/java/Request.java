import dto.MeasurementDTO;
import dto.MeasurementsList;
import dto.SensorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Request {

    public static void main(String[] args) {
       createSensor("Garden sensor");
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            writeMeasurement(random.nextDouble(-50.00, 50.00),
                    random.nextBoolean(), "Garden sensor");
        }
        List<MeasurementDTO> measurementDTOList = getAllMeasurements();
        for (MeasurementDTO mes : measurementDTOList) {
            System.out.println(mes + "\n");
        }
    }

    public static void writeMeasurement(double value, boolean raining, String sensorName) {
        String url = "http://localhost:8080/measurements/add";

        Map<String, Object> jsonToSend = new HashMap<>();
        jsonToSend.put("value", value);
        jsonToSend.put("raining", raining);
        jsonToSend.put("sensor", Map.of("name", sensorName));

        makePostRequest(url, jsonToSend);
    }

    public static void createSensor(String sensorsName) {
        Map<String, Object> jsonToSend = new HashMap<>();
        jsonToSend.put("name", sensorsName);

        String url = "http://localhost:8080/sensors/registration"; // request сервис для тестирования подобных API
        makePostRequest(url, jsonToSend);
    }

    public static List<MeasurementDTO> getAllMeasurements() {
        String url = "http://localhost:8080/measurements";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, MeasurementsList.class).getMeasurementsList();

    }

    public static void makePostRequest(String url, Map<String, Object> data) {
        final RestTemplate restTemplate = new RestTemplate();

        final HttpEntity<Map<String, Object>> request = new HttpEntity<>(data);


        try {
            restTemplate.postForObject(url, request, String.class);
            System.out.println("Измерение успешно отправлено на сервер!");
        }  catch (Error e) {
            System.out.println("Ошибка!");
            System.out.println(e.getMessage());
        }
    }
}
