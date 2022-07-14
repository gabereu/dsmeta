package com.devsuperior.dsmeta.config.beans;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.devsuperior.dsmeta.services.MessageService;
import com.devsuperior.dsmeta.services.implementations.MailMessageService;

@Configuration
public class ServiceBeans {
    
    @Bean
public JavaMailSender getJavaMailSender(
    @Value("${spring.mail.host}") String host,
    @Value("${spring.mail.port}") Integer port,
    @Value("${spring.mail.username}") String username,
    @Value("${spring.mail.password}") String password,
    @Value("${spring.mail.properties.mail.smtp.auth}") Boolean auth,
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}") Boolean starttls
) {
    System.out.println("Teste " + port);
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(host);
    mailSender.setPort(port);
    
    mailSender.setUsername(username);
    mailSender.setPassword(password);
    
    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true");
    
    return mailSender;
}

    @Bean
    public MessageService getMessageService(ApplicationContext appContext, @Value("${spring.mail.username}") String from, JavaMailSender emailSender){
        return MailMessageService.builder()
            .from(from)
            .emailSender(emailSender)
        .build();
    }

}
