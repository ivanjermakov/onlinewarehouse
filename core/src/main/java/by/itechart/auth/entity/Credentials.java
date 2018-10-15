package by.itechart.auth.entity;

import by.itechart.common.entity.Address;
import by.itechart.common.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "credentials")
public class Credentials {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "login")
    private String login;

    @Column(name = "hash")
    private String hash;


}
