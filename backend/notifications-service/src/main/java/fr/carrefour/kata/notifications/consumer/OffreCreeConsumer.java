package fr.carrefour.kata.notifications.consumer;

import fr.carrefour.kata.kafka.OffreCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OffreCreeConsumer {
    private final Logger log = LoggerFactory.getLogger(OffreCreeConsumer.class);

    @KafkaListener(topics = "offre-creee", groupId = "notifications-service")
    public void onMessage(OffreCreatedEvent event) {
        System.out.println("=================================");
        System.out.println("      MESSAGE KAFKA RECU");
        System.out.println("=================================");

        System.out.println(event);
        // TODO: add notification processing logic here (email, push, etc.)
    }
}
