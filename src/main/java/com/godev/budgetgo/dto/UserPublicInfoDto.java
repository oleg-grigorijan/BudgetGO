package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.User;

public class UserPublicInfoDto {
    private Long id;
    private String login;
    private String email;
    private String name;
    private String surname;

    public static UserPublicInfoDto from(User user) {
        UserPublicInfoDto instance = new UserPublicInfoDto();
        instance.id = user.getId();
        instance.login = user.getLogin();
        if (user.isEmailPublic()) instance.email = user.getEmail();
        instance.name = user.getName();
        instance.surname = user.getSurname();
        return instance;
    }

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
}
