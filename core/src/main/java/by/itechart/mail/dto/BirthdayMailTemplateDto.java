package by.itechart.mail.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BirthdayMailTemplateDto {
    @NotEmpty
    private String text;
    private String backgroundColor;
    private String headerImagePath;
}
