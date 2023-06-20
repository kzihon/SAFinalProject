package Springboot_kafka_realtime_producer.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Length {
    private int old;
    @JsonProperty("new")
    private int newLength;
}