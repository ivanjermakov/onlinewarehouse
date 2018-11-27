package by.itechart.mail.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.company.entity.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "birthday_mail_template")
public class BirthdayMailTemplate extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "text")
    private String text;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "header_image_path")
    private String headerImagePath;

}
