package by.itechart.mail.birthday;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BirthdayMailTemplate {

    private String text;
    private String backgroundColor;
    private String headerImagePath;

}
