package de.smartgrow.userservice.kafka;

import de.smartgrow.userservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import user.events.UserEvent;

@Service
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(User user){
        UserEvent event = UserEvent.newBuilder()
                .setUserId(user.getId().toString())
                .setName(user.getUsername())
                .setEmail(user.getEmail())
                .setEventType("USER_CREATED")
                .build();

        try {
            kafkaTemplate.send("user", event.toByteArray());
        } catch (Exception e){
            log.error("Error sending UserCreated even: {}", event);
        }


    }

}
