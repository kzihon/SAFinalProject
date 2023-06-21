package edu.miu.saproject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDeleteConsumer {

//    private WikimediaDataRepo wikimediaDataRepo;
//    public KafkaDatabaseConsumer(WikimediaDataRepo wikimediaDataRepo){
//        this.wikimediaDataRepo = wikimediaDataRepo;
//    }

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDeleteConsumer.class);

    @KafkaListener(
            topics = "wikimedia-delete",
            groupId = "myGroup")
    public void consume(String eventMessage){

        LOGGER.info(String.format("Event message received -> %s",eventMessage));

//        WikimediaData wikimediaData = new WikimediaData();
//        wikimediaData.setWikiEventData(eventMessage);
//        wikimediaDataRepo.save(wikimediaData);
    }
}
