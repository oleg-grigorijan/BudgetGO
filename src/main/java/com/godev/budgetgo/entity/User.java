package com.godev.budgetgo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Cloneable {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isEmailPublic() {
        return isEmailPublic;
    }

    public void setEmailPublic(boolean emailPublic) {
        isEmailPublic = emailPublic;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Currency getMainCurrency() {
        return mainCurrency;
    }

    public void setMainCurrency(Currency mainCurrency) {
        this.mainCurrency = mainCurrency;
    }

    /**
     * @return Shallow clone of instance
     */
    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isEmailPublic == user.isEmailPublic &&
                isAdmin == user.isAdmin &&
                id.equals(user.id) &&
                login.equals(user.login) &&
                email.equals(user.email) &&
                name.equals(user.name) &&
                surname.equals(user.surname) &&
                passwordHash.equals(user.passwordHash) &&
                mainCurrency.equals(user.mainCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, name, surname, passwordHash, isEmailPublic, isAdmin, mainCurrency);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", isEmailPublic=" + isEmailPublic +
                ", isAdmin=" + isAdmin +
                ", mainCurrency=" + mainCurrency +
                '}';
    }
}
