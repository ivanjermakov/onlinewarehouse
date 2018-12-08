package by.itechart.mail.task;

import by.itechart.common.entity.User;
import by.itechart.common.service.UserService;
import by.itechart.company.service.CompanyService;
import by.itechart.mail.entity.BirthdayMailTemplate;
import by.itechart.mail.service.BirthdayMailSendService;
import by.itechart.mail.service.MailService;
import by.itechart.mail.service.MailTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class BirthdayTask {

    private final static Logger LOGGER = LoggerFactory.getLogger(BirthdayTask.class);

    private final MailService mailService;
    private final MailTemplateService mailTemplateService;
    private final UserService userService;
    private final CompanyService companyService;
    private final BirthdayMailSendService birthdayMailSendService;

    @Autowired
    public BirthdayTask(MailService mailService, MailTemplateService mailTemplateService, UserService userService, CompanyService companyService, BirthdayMailSendService birthdayMailSendService) {
        this.mailService = mailService;
        this.mailTemplateService = mailTemplateService;
        this.userService = userService;
        this.companyService = companyService;
        this.birthdayMailSendService = birthdayMailSendService;
    }

    //    TODO: optimize: maybe send mail async
    //    in 7:00AM each morning
    @Scheduled(cron = "0 0 7 * * *")
    public void happyBirthday() {
        companyService.getCompanies(Pageable.unpaged()).forEach(c -> {
            BirthdayMailTemplate birthdayMailTemplate = mailTemplateService.getTemplate(c.getId());
            userService.getUsersWithBirthday(c.getId(), LocalDate.now()).stream()
                    .filter(u -> u.getEmail() != null)
                    .forEach(u -> congratulateUser(u, birthdayMailTemplate));
        });
    }

    //    from 8:00AM to 12:00AM every morning each hour (4 times per day)
    @Scheduled(cron = "0 0 8-12 * * *")
    public void retryCongratulation() {
        birthdayMailSendService.getUsersToCongratulate()
                .forEach(u -> congratulateUser(u, mailTemplateService.getTemplate(u.getCompany().getId())));
    }

    private void congratulateUser(User user, BirthdayMailTemplate birthdayMailTemplate) {
        try {
            String subject = "С Днем Рождения!";
            String html = mailTemplateService.generateBirthdayMailHtml(
                    user.getFirstname() + " " + user.getLastname(),
                    (int) user.getBirth().until(LocalDate.now(), ChronoUnit.YEARS),
                    birthdayMailTemplate
            );
            mailService.sendHtml(user.getEmail(), subject, html);
            birthdayMailSendService.generate(user, true);
        } catch (MessagingException e) {
            LOGGER.error("Exception sending message: {}", e);
            birthdayMailSendService.generate(user, false);
        }
    }
}
