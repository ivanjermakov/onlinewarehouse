package by.itechart.mail.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.repository.CompanyRepository;
import by.itechart.mail.dto.BirthdayMailTemplateDto;
import by.itechart.mail.entity.BirthdayMailTemplate;
import by.itechart.mail.repository.MailTemplateRepository;
import lombok.Getter;
import org.antlr.stringtemplate.StringTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Getter
@Service
public class MailTemplateServiceImpl implements MailTemplateService {

    private BirthdayMailTemplate defaultBirthdayMailTemplate;
    private final MailTemplateRepository mailTemplateRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public MailTemplateServiceImpl(MailTemplateRepository mailTemplateRepository, CompanyRepository companyRepository) {
        this.mailTemplateRepository = mailTemplateRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public BirthdayMailTemplateDto updateBirthdayMailTemplate(long companyId, BirthdayMailTemplateDto birthdayMailTemplateDto) {
//        TODO: check file presence
        BirthdayMailTemplate birthdayMailTemplate = ObjectMapperUtils.map(birthdayMailTemplateDto, BirthdayMailTemplate.class);
        birthdayMailTemplate.setCompany(companyRepository.getById(companyId));
        return ObjectMapperUtils.map(
                saveOrUpdateTemplate(companyId, birthdayMailTemplate), BirthdayMailTemplateDto.class
        );
    }

    @Override
    public String generateBirthdayMailHtml(String fullName, Integer age, BirthdayMailTemplate birthdayMailTemplate) {
        StringTemplate template = new StringTemplate(birthdayMailTemplate.getText());
        template.setAttribute("backgroundColor", birthdayMailTemplate.getBackgroundColor());
        template.setAttribute("headerImagePath", birthdayMailTemplate.getHeaderImagePath());
        template.setAttribute("fullName", fullName);
        template.setAttribute("age", age);

        return template.toString();
    }

    @Override
    public BirthdayMailTemplate getTemplate(long companyId) {
        return Optional.ofNullable(mailTemplateRepository.getTemplate(companyId)).orElse(defaultBirthdayMailTemplate);
    }

    @Override
    public BirthdayMailTemplate saveOrUpdateTemplate(long companyId, BirthdayMailTemplate birthdayMailTemplate) {
        BirthdayMailTemplate existing = mailTemplateRepository.getTemplate(companyId);
        if (existing != null) {
            existing.setText(birthdayMailTemplate.getText());
            existing.setHeaderImagePath(birthdayMailTemplate.getHeaderImagePath());
            existing.setBackgroundColor(birthdayMailTemplate.getBackgroundColor());
            mailTemplateRepository.save(existing);
            return existing;
        } else {
            mailTemplateRepository.save(birthdayMailTemplate);
            return birthdayMailTemplate;
        }
    }

    @PostConstruct
    private void initBirthdayMailTemplate() {
        //        TODO: something more elegant, maybe read from .html file
        //        TODO: fix mail design
        String defaultTemplateText = "<head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\"/>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
                "    <title>С Днем Рождения!</title>\n" +
                "    \n" +
                "    <style>\n" +
                "        body {\n" +
                "            background: $backgroundColor$;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<img src=\"$headerImagePath$\">\n" +
                "<p>Уважаемый $fullName$!</p>\n" +
                "<p>Поздраляем Вас с $age$-и летием. Желаем всего самого наилучшего!</p>\n" +
                "<p>С уваженем, коллектив ООО ”Склад-Сервис”</p>\n" +
                "</body>";


        defaultBirthdayMailTemplate = new BirthdayMailTemplate();
        defaultBirthdayMailTemplate.setText(defaultTemplateText);
        defaultBirthdayMailTemplate.setBackgroundColor("#FFFFFF");
        defaultBirthdayMailTemplate.setHeaderImagePath("#");
    }
}
