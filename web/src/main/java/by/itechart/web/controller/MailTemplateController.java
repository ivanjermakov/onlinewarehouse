package by.itechart.web.controller;

import by.itechart.common.dto.UserDto;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.mail.dto.BirthdayMailTemplateDto;
import by.itechart.mail.service.BirthdayMailSendService;
import by.itechart.mail.service.MailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/mail-templates")
public class MailTemplateController {

    private final MailTemplateService mailTemplateService;
    private final BirthdayMailSendService birthdayMailSendService;

    @Autowired
    public MailTemplateController(MailTemplateService mailTemplateService, BirthdayMailSendService birthdayMailSendService) {
        this.mailTemplateService = mailTemplateService;
        this.birthdayMailSendService = birthdayMailSendService;
    }

    @GetMapping("/birthday")
    public BirthdayMailTemplateDto getBirthdayTemplate(@PathVariable long companyId) {
        return ObjectMapperUtils.map(mailTemplateService.getTemplate(companyId), BirthdayMailTemplateDto.class);
    }

    @PutMapping("/birthday")
    public BirthdayMailTemplateDto updateBirthdayMailTemplate(@PathVariable long companyId,
                                           @RequestBody BirthdayMailTemplateDto birthdayMailTemplateDto) throws IOException {
        return mailTemplateService.updateBirthdayMailTemplate(companyId, birthdayMailTemplateDto);
    }

    @GetMapping("/not-celebrated")
    public List<UserDto> getNotCelebratedUsers() {
        return ObjectMapperUtils.mapAll(birthdayMailSendService.getNotCelebratedUsers(), UserDto.class);
    }
}
