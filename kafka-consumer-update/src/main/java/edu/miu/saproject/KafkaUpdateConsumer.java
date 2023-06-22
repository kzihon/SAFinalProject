package edu.miu.saproject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaUpdateConsumer {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaUpdateConsumer.class);

    @KafkaListener(
            topics = "RTDIS_1",
            groupId = "myGroup")
    public void consume(String eventMessage)  {
        LOGGER.info(String.format("Event message received -> %s",eventMessage));
        String newTopic = parseTopic(eventMessage);
        if (newTopic != null) {
            pushToKafka(newTopic, eventMessage);
        }
//        WikimediaData wikimediaData = new WikimediaData();
//        wikimediaData.setWikiEventData(eventMessage);
//        wikimediaDataRepo.save(wikimediaData);
    }

    public void pushToKafka(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
    }

    public String parseTopic(String eventMessage) {
        int startIndex = eventMessage.indexOf("mediawiki");

        if (startIndex != -1) {
            // Find the end index of the closing double quote
            int endIndex = eventMessage.indexOf("\"", startIndex + 1);

            if (endIndex != -1) {
                // Extract the desired substring
                String extractedSubstring = eventMessage.substring(startIndex, endIndex);
                System.out.println("Extracted substring: " + extractedSubstring);
                // if statement to check if extractedSubstring contains create, delete, change substring
                if (extractedSubstring.contains("create")) {
                    return "DS_create";
                } else if (extractedSubstring.contains("delete")) {
                    return "DS_delete";
                } else if (extractedSubstring.contains("change")) {
                    return "DS_update";
                } else {
                    System.out.println("Substring 'create', 'delete', or 'change' not found.");
                    return null;
                }

            } else {
                System.out.println("Closing double quote not found.");
                return null;
            }
        } else {
            System.out.println("Substring 'mediawiki' not found.");
            return null;
        }

    }
}
