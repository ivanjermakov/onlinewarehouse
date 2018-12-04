package by.itechart.mail.service;

import by.itechart.mail.dto.BirthdayMailTemplateDto;
import by.itechart.mail.entity.BirthdayMailTemplate;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.io.IOException;

@Validated
public interface MailTemplateService {

    BirthdayMailTemplateDto updateBirthdayMailTemplate(long companyId, @Valid BirthdayMailTemplateDto birthdayMailTemplateDto) throws IOException;

    String generateBirthdayMailHtml(String fullName, Integer age, BirthdayMailTemplate birthdayMailTemplate);

    BirthdayMailTemplate getTemplate(long companyId);

    BirthdayMailTemplate saveOrUpdateTemplate(long companyId, BirthdayMailTemplate birthdayMailTemplate);

}
