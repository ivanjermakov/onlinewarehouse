package by.itechart.mail.task;

import by.itechart.common.service.UserService;
import by.itechart.company.service.CompanyService;
import by.itechart.mail.entity.BirthdayMailTemplate;
import by.itechart.mail.service.MailService;
import by.itechart.mail.service.MailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class ScheduledTasks {

    private final MailService mailService;
    private final MailTemplateService mailTemplateService;
    private final UserService userService;
    private final CompanyService companyService;

    @Autowired
    public ScheduledTasks(MailService mailService, MailTemplateService mailTemplateService, UserService userService, CompanyService companyService) {
        this.mailService = mailService;
        this.mailTemplateService = mailTemplateService;
        this.userService = userService;
        this.companyService = companyService;
    }

    //    TODO: optimize: maybe send mail async
    @Scheduled(cron = "0 0 0 * * *")
    public void happyBirthday() {
        LocalDate now = LocalDate.now();
        companyService.getCompanies(Pageable.unpaged()).forEach(c -> {
            BirthdayMailTemplate birthdayMailTemplate = mailTemplateService.getTemplate(c.getId());
            userService.getUsersWithBirthday(c.getId(), now).stream()
                    .filter(u -> u.getEmail() != null)
                    .forEach(u -> {
                        try {
                            String subject = "С Днем Рождения!";
                            String html = mailTemplateService.generateBirthdayMailHtml(
                                    u.getFirstname() + " " + u.getLastname(),
                                    (int) u.getBirth().until(now, ChronoUnit.YEARS),
                                    birthdayMailTemplate
                            );
                            mailService.sendHtml(u.getEmail(), subject, html);
                        } catch (MessagingException e) {
//                            TODO: handle mail sending error
                        }
                    });
        });

    }

}
