package by.itechart.mail.birthday.service;

import by.itechart.mail.birthday.dto.BirthdayMailTemplateDto;
import org.springframework.stereotype.Service;

@Service
public interface TemplateMailService {

    void updateBirthdayMailTemplate(BirthdayMailTemplateDto birthdayMailTemplateDto);

    String generateBirthdayMailHtml(String fullName, Integer age);

}
