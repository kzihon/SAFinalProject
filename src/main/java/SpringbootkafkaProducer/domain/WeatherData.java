package SpringbootkafkaProducer.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class WeatherData {
    private Location location;
    private Current current;
}
