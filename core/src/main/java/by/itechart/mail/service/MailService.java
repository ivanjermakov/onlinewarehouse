package by.itechart.mail.service;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface MailService {

    void send(String to, String subject, String content);

    void sendHtml(String to, String subject, String html) throws MessagingException;

}
