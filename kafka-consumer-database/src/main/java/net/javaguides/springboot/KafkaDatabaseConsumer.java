package net.javaguides.springboot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaDatabaseConsumer {

//    private WikimediaDataRepo wikimediaDataRepo;
//    public KafkaDatabaseConsumer(WikimediaDataRepo wikimediaDataRepo){
//        this.wikimediaDataRepo = wikimediaDataRepo;
//    }

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    @KafkaListener(
            topics = "RTDIS_1",
            groupId = "myGroup")
    public void consume(String eventMessage)  {
        eventMessage = eventMessage.substring(1, eventMessage.length() - 1);
        eventMessage = eventMessage.replaceAll("\\\\", "");
        LOGGER.info(String.format("Event message received -> %s",eventMessage));
        try {
// Parse the JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String json = eventMessage;
            JsonNode rootNode = objectMapper.readTree(json);
            System.out.println();
// Extract specific data fields
            String topic = rootNode.path("meta").path("url").asText();
            System.out.println("topic = " + topic);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        WikimediaData wikimediaData = new WikimediaData();
//        wikimediaData.setWikiEventData(eventMessage);
//        wikimediaDataRepo.save(wikimediaData);
    }

    public void pushToKafka() {
        /*
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
        * */
    }
}
