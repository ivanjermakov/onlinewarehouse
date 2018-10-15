package by.itechart.auth.entity;

import by.itechart.common.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
