package miu.edu.service;

import miu.edu.model.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaProducerService {
    private String metaTopic="meta-topic";

    @Autowired
    private KafkaTemplate<String, Meta> kafkaTemplate;
    public void send(Meta meta){
        kafkaTemplate.send(metaTopic, meta);
    }

}
