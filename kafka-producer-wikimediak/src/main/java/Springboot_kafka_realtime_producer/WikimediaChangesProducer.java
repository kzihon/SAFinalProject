package Springboot_kafka_realtime_producer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;


@Service
public class WikimediaChangesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private KafkaTemplate<String,String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage() throws InterruptedException {
        String createTopic = "wikimedia-create";
        String changeTopic = "wikimedia-update";
        String deleteTopic = "wikimedia-delete";

        EventHandler createEventHandler = new WikimediaChangesHandler(kafkaTemplate,createTopic);
        String createUrl = "https://stream.wikimedia.org/v2/stream/page-create";

        EventHandler updateEventHandler = new WikimediaChangesHandler(kafkaTemplate,changeTopic);
        String changeUrl = "https://stream.wikimedia.org/v2/stream/page-links-change";

        EventHandler deleteEventHandler = new WikimediaChangesHandler(kafkaTemplate,deleteTopic);
        String deleteUrl = "https://stream.wikimedia.org/v2/stream/page-delete";

        EventSource.Builder createBuilder = new EventSource.Builder(createEventHandler, URI.create(createUrl));
        EventSource.Builder changeBuilder = new EventSource.Builder(updateEventHandler, URI.create(changeUrl));
        EventSource.Builder deleteBuilder = new EventSource.Builder(deleteEventHandler, URI.create(deleteUrl));

        EventSource createEventSource = createBuilder.build();
        EventSource changeEventSource = changeBuilder.build();
        EventSource deleteEventSource = deleteBuilder.build();

        createEventSource.start();
        changeEventSource.start();
        deleteEventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
