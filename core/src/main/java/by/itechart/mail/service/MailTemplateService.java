package by.itechart.mail.service;

import by.itechart.mail.dto.BirthdayMailTemplateDto;
import by.itechart.mail.entity.BirthdayMailTemplate;

import java.io.IOException;

public interface MailTemplateService {

    BirthdayMailTemplateDto updateBirthdayMailTemplate(long companyId, BirthdayMailTemplateDto birthdayMailTemplateDto) throws IOException;

    String generateBirthdayMailHtml(String fullName, Integer age, BirthdayMailTemplate birthdayMailTemplate);

    BirthdayMailTemplate getTemplate(long companyId);

    BirthdayMailTemplate saveOrUpdateTemplate(long companyId, BirthdayMailTemplate birthdayMailTemplate);
}
