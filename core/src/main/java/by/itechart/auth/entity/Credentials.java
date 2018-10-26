package by.itechart.auth.entity;

import by.itechart.common.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "credentials")
public class Credentials {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "hash")
    private String hash;
}
