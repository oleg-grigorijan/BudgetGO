package com.godev.budgetgo.domain.user;

import com.godev.budgetgo.domain.currency.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    public static final int LOGIN_MAX_LENGTH = 255;

    public static final String LOGIN_PATTERN = "^[a-zA-Z0-9_.\\-]*$";

    public static final int EMAIL_MAX_LENGTH = 255;

    public static final int NAME_MAX_LENGTH = 255;

    public static final int SURNAME_MAX_LENGTH = 255;

    public static final int PASSWORD_MIN_LENGTH = 6;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false, length = LOGIN_MAX_LENGTH)
    private String login;

    @Column(unique = true, nullable = false, length = EMAIL_MAX_LENGTH)
    private String email;

    @Column(nullable = false, length = NAME_MAX_LENGTH)
    private String name;

    @Column(nullable = false, length = SURNAME_MAX_LENGTH)
    private String surname;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "is_email_public", nullable = false)
    private boolean isEmailPublic;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_currency_id", nullable = false)
    private Currency mainCurrency;

    public User cloneShallow() {
        User e = new User();
        e.setId(id);
        e.setLogin(login);
        e.setEmail(email);
        e.setName(name);
        e.setSurname(surname);
        e.setPasswordHash(passwordHash);
        e.setEmailPublic(isEmailPublic);
        e.setAdmin(isAdmin);
        e.setMainCurrency(mainCurrency);
        return e;
    }
}
