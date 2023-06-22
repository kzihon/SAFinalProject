package edu.miu.saproject;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVWriter;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaCreateConsumer {

//    private WikimediaDataRepo wikimediaDataRepo;
//    public KafkaDatabaseConsumer(WikimediaDataRepo wikimediaDataRepo){
//        this.wikimediaDataRepo = wikimediaDataRepo;
//    }

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaCreateConsumer.class);
    List<Object> eventData = new ArrayList<>();
    String filePath = "/Users/fili/Desktop/SAFinalProjectd/wikipdiaCsv/output.json";
    JSONObject jsonData = new JSONObject();

    @KafkaListener(
            topics = "DS_create",
            groupId = "myGroup")
    public void consume(String eventMessage) throws IOException {

        LOGGER.info(String.format("Event message received -> %s", eventMessage));
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("create-output.csv", true))) {
            // Write data rows
            String[] row1 = parseTopic(eventMessage);
            csvWriter.writeNext(row1);
            System.out.println("CSV file was updated successfully !!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] parseTopic(String eventMessage) {
        String[] row = new String[5];
        // add a formatted timestamp to the first column
        LocalDateTime timestamp = LocalDateTime.now();

        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the timestamp as a string
        String timestampString = timestamp.format(formatter);
        row[0] = timestampString;
        int startIndex = eventMessage.indexOf("topic");

        if (startIndex != -1) {
            // Find the end index of the closing double quote
            int endIndex = eventMessage.indexOf("\\", startIndex + 10);

            if (endIndex != -1) {
                // Extract the desired substring
                String extractedSubstring = eventMessage.substring(startIndex+10, endIndex);
                row[1] = extractedSubstring;
                // if statement to check if extractedSubstring contains create, delete, change substring
            }
        }

        startIndex = eventMessage.indexOf("https");

        if (startIndex != -1) {
            // Find the end index of the closing double quote
            int endIndex = eventMessage.indexOf("\"", startIndex + 1);

            if (endIndex != -1) {
                // Extract the desired substring
                String extractedSubstring = eventMessage.substring(startIndex, endIndex-1);
                row[2] = extractedSubstring;
                // if statement to check if extractedSubstring contains create, delete, change substring
            }
        }
        startIndex = eventMessage.indexOf("user_text");

        if (startIndex != -1) {
            // Find the end index of the closing double quote
            int endIndex = eventMessage.indexOf("\"", startIndex + 14);

            if (endIndex != -1) {
                // Extract the desired substring
                String extractedSubstring = eventMessage.substring(startIndex+14, endIndex-1);
                row[3] = extractedSubstring;
                // if statement to check if extractedSubstring contains create, delete, change substring
            }
        }
        startIndex = eventMessage.indexOf("domain");

        if (startIndex != -1) {
            // Find the end index of the closing double quote
            int endIndex = eventMessage.indexOf("\"", startIndex + 11);

            if (endIndex != -1) {
                // Extract the desired substring
                String extractedSubstring = eventMessage.substring(startIndex+11, endIndex-1);
                row[4] = extractedSubstring;
                // if statement to check if extractedSubstring contains create, delete, change substring
            }
        }
        System.out.println(row[0] + " " + row[1] + " " + row[2]);
        return row;

    }
}