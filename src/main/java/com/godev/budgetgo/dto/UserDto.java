package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.User;

public class UserDto {
    private Long id;
    private String login;
    private String email;
    private String name;
    private String surname;
    private String password;
    private boolean emailPublic;

    public static UserDto from(User user) {
        UserDto instance = new UserDto();
        instance.id = user.getId();
        instance.login = user.getLogin();
        instance.email = user.getEmail();
        instance.name = user.getName();
        instance.surname = user.getSurname();
        instance.emailPublic = user.isEmailPublic();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmailPublic() {
        return emailPublic;
    }

    public void setEmailPublic(boolean emailPublic) {
        this.emailPublic = emailPublic;
    }
}
