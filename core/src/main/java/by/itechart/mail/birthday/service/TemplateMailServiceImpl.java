package by.itechart.mail.birthday.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.mail.birthday.BirthdayMailTemplate;
import by.itechart.mail.birthday.dto.BirthdayMailTemplateDto;
import org.antlr.stringtemplate.StringTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TemplateMailServiceImpl implements TemplateMailService {

    private BirthdayMailTemplate birthdayMailTemplate;

    @Override
    public void updateBirthdayMailTemplate(BirthdayMailTemplateDto birthdayMailTemplateDto) {
        this.birthdayMailTemplate = ObjectMapperUtils.map(birthdayMailTemplateDto, BirthdayMailTemplate.class);
    }

    @Override
    public String generateBirthdayMailHtml(String fullName, Integer age) {
        StringTemplate template = new StringTemplate(birthdayMailTemplate.getText());
        template.setAttribute("backgroundColor", birthdayMailTemplate.getBackgroundColor());
        template.setAttribute("headerImagePath", birthdayMailTemplate.getHeaderImagePath());
        template.setAttribute("fullName", fullName);
        template.setAttribute("age", age);

        return template.toString();
    }

    @PostConstruct
    private void initBirthdayMailTemplate() {
//        TODO: something more elegant, maybe read from .html file
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

        birthdayMailTemplate = new BirthdayMailTemplate(defaultTemplateText,
                "#FFFFFF",
                "#");
    }

}
