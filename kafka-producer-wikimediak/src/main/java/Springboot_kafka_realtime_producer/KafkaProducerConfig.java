package Springboot_kafka_realtime_producer;



import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {




    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServer;

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }


//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Meta> cpuDataKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Meta> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(metaDataConsumerFactory());
//        return factory;
//    }
//
//    @Bean
//    public ConsumerFactory<String, Meta> metaDataConsumerFactory() {
//        JsonDeserializer<Meta> deserializer = new JsonDeserializer<>(
//                Meta.class
//        );
//        deserializer.setRemoveTypeHeaders(false);
//        deserializer.addTrustedPackages("*");
//        deserializer.setUseTypeMapperForKey(true);
//
//        Map<String, Object> props = consumerConfigs();
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "meta-data-consumer-group");
//
//        return new DefaultKafkaConsumerFactory<>(props,
//                new StringDeserializer(), deserializer);
//    }


}
