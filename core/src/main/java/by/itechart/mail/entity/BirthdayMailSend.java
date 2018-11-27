package by.itechart.mail.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "birthday_mail_send")
public class BirthdayMailSend extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "timestamp")
    private LocalDate date;

    @Column(name = "successful")
    private Boolean successful;
}
