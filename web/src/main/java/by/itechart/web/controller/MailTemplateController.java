package by.itechart.web.controller;

import by.itechart.mail.dto.BirthdayMailTemplateDto;
import by.itechart.mail.service.MailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies/{companyId}/mail-templates")
public class MailTemplateController {

    private final MailTemplateService mailTemplateService;

    @Autowired
    public MailTemplateController(@Qualifier("mailTemplateService") MailTemplateService mailTemplateService) {
        this.mailTemplateService = mailTemplateService;
    }

    @PostMapping("/updateBirthdayTemplate")
    public void updateBirthdayMailTemplate(@PathVariable long companyId,
                                           @RequestBody BirthdayMailTemplateDto birthdayMailTemplateDto) {
        mailTemplateService.updateBirthdayMailTemplate(birthdayMailTemplateDto);
    }
}
