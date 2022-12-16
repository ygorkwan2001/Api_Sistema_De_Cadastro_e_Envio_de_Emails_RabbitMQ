package com.ms.email.services;

import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;
import com.ms.email.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String email) {
        EmailModel emailModel = new EmailModel();
        UUID codgenerator = UUID.randomUUID();
        emailModel.setCodconfirmation(codgenerator);
        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("igorkwan2022@gmail.com");
            message.setTo(email);
            message.setSubject("Confirmação Recebimento email do RabbitMQ");
            message.setText("Cadastro Confirmado com Sucesso. " +
                    "seu código de confirmação e: " + codgenerator);
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.PROCESSING);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            emailRepository.save(emailModel);
        }
    }
}
