package com.ms.email.consumers;

import com.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${spring.queue.name}")
    public void listen(@Payload String email) {
        emailService.sendEmail(email);
        System.out.println("Email Status: " + email);
    }
}
