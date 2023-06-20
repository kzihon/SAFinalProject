package SpringbootkafkaProducer.service;

import SpringbootkafkaProducer.JsonSerializer;
import SpringbootkafkaProducer.domain.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
   // private final static String API_KEY = "Hbqg4w.HvLbxw:GUlTbekCGyUB23xyf6C7qDA-AmN9qYMn-pUBnDfawBM";

//    @Autowired
//    private KafkaTemplate<String, WeatherData> kafkaTemplate;
////    @Value("${app.topic.weatherDataTopic}")
//    private String topic ="weatherDataTopic";
//
//    private JsonSerializer jsonSerializer;
    @Scheduled(fixedRate = 5000)
    public void fetchWeatherData () {

        RestTemplate restTemplate = new RestTemplate();
        WeatherData data = restTemplate.getForObject("http://api.weatherapi.com/v1/current.json?key=7a6bae7963524d018ac230516231906&q=52557&aqi=no", WeatherData.class);

//        kafkaTemplate.send(jsonSerializer.serialize(topic,data));
        System.out.println(data);
    }
}
