package Springboot_kafka_realtime_producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topic(){
//        return new NewTopic("wikimedia", 1, (short) 1);
        return TopicBuilder.name("wikimedia")//.partitions(1).replicas(1).build();
                .build();
    }
}
