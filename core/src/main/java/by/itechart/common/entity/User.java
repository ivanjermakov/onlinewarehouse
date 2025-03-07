package by.itechart.common.entity;

import by.itechart.company.entity.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(name = "username", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(name = "password", length = 100)
//    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    //    TODO: should be firstName
    @Column(name = "first_name", length = 50)
    @NotNull
    @Size(max = 50)
    private String firstname;

    //    TODO: should be lastName
    @Column(name = "last_name", length = 50)
    @NotNull
    @Size(max = 50)
    private String lastname;

    @Column(name = "email", length = 50)
    @NotNull
    @Email
    @Size(min = 4, max = 50)
    private String email;

    @Column(name = "activation_code")
    private String activationCode;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "deleted")
    private LocalDate deleted;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "enabled")
    @NotNull
    private Boolean enabled;

    @Column(name = "lastpasswordresrtdate")
    @Temporal(TemporalType.TIMESTAMP)
//    @NotNull
    private Date lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private List<Authority> authorities;

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
}
