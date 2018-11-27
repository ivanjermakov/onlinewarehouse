package by.itechart.mail.service;

import javax.mail.MessagingException;

public interface MailService {

    void send(String to, String subject, String content);

    void sendHtml(String to, String subject, String html) throws MessagingException;
}
