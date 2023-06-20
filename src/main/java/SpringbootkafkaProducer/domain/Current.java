package SpringbootkafkaProducer.domain;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Current {
    private long last_updated_epoch;
    private String last_updated;
    private double temp_c;
    private double temp_f;
    private int is_day;
    private Condition condition;
    private double wind_mph;
    private double precip_in;
    private double humidity;
    private double cloud;
    private double feelslike_c;
    private double vis_km;
    private double vis_miles;
    private double uv;
    private double gust_mph;
    private double gust_kph;






}
