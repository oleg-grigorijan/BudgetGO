package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.godev.budgetgo.entity.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreationDto {
    private String login;
    private String email;
    private String name;
    private String surname;
    private String password;

    public User getUser() {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setEmailPublic(true);
        return user;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
