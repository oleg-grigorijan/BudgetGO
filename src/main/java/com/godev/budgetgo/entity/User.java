package com.godev.budgetgo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "is_email_public", nullable = false)
    private boolean emailPublic;

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
        return emailPublic;
    }

    public void setEmailPublic(boolean emailPublic) {
        this.emailPublic = emailPublic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return emailPublic == user.emailPublic &&
                id.equals(user.id) &&
                login.equals(user.login) &&
                email.equals(user.email) &&
                name.equals(user.name) &&
                surname.equals(user.surname) &&
                passwordHash.equals(user.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, name, surname, passwordHash, emailPublic);
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
                ", emailPublic=" + emailPublic +
                '}';
    }
}
