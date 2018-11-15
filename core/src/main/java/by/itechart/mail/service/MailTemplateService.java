package by.itechart.mail.service;

import by.itechart.mail.dto.BirthdayMailTemplateDto;
import by.itechart.mail.entity.BirthdayMailTemplate;
import org.springframework.stereotype.Service;

@Service
public interface MailTemplateService {

    void updateBirthdayMailTemplate(long companyId, BirthdayMailTemplateDto birthdayMailTemplateDto);

    String generateBirthdayMailHtml(String fullName, Integer age, BirthdayMailTemplate birthdayMailTemplate);

    BirthdayMailTemplate getTemplate(long companyId);

    void saveOrUpdateTemplate(long companyId, BirthdayMailTemplate birthdayMailTemplate);
}
