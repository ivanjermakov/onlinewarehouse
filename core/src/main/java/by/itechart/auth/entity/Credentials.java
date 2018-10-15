package by.itechart.auth.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "credentials")
public class Credentials extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "login")
    private String login;

    @Column(name = "hash")
    private String hash;

    public Credentials() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
