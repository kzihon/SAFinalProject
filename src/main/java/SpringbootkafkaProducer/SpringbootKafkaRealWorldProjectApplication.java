package SpringbootkafkaProducer;

import SpringbootkafkaProducer.domain.WeatherData;
import SpringbootkafkaProducer.service.WeatherService;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.ably.lib.types.AblyException;
import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.types.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableScheduling
//@EnableKafka
public class SpringbootKafkaRealWorldProjectApplication implements CommandLineRunner {
    @Autowired
    public WeatherService weatherService;

// change form innocent side

    public static void main(String[] args) {
            SpringApplication.run(SpringbootKafkaRealWorldProjectApplication.class, args);

    }

    public void run(String... args) throws Exception {
        weatherService.fetchWeatherData();
    }


//		SpringApplication.run(SpringbootKafkaRealWorldProjectApplication.class, args);
//		Properties props = new Properties();
//		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//



}
