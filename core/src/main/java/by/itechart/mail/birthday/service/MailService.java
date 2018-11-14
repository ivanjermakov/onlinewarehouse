package by.itechart.mail.birthday.service;

import javax.mail.MessagingException;

public interface MailService {

    void send(String from, String to, String subject, String content);

    void sendHtml(String from, String to, String subject, String html) throws MessagingException;

}
