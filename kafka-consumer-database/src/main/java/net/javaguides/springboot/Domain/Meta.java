package net.javaguides.springboot.Domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meta {
    private String uri;
    private String request_id;
    private String id;
    private String dt;
    private String domain;
    private String stream;
    private String topic;
    private int partition;
    private long offset;
}