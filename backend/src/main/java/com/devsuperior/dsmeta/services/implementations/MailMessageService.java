package com.devsuperior.dsmeta.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.devsuperior.dsmeta.services.MessageService;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class MailMessageService implements MessageService {

    private JavaMailSender emailSender;

    private String from;

    @Override
    public void sendMessage(String to, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom(from);
        message.setTo(to); 
        message.setSubject(title); 
        message.setText(content);
        emailSender.send(message);   
    }

}
