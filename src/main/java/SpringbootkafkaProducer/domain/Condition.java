package SpringbootkafkaProducer.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Condition {
    private String text;
    private String icon;
    private int code;

}
