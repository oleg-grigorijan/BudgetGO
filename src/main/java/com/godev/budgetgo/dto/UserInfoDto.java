package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.godev.budgetgo.entity.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoDto {
    private Long id;
    private String login;
    private String email;
    private String name;
    private String surname;
    private String password;
    private Boolean emailPublic;

    public static UserInfoDto from(User user) {
        UserInfoDto dto = new UserInfoDto();
        dto.id = user.getId();
        dto.login = user.getLogin();
        dto.email = user.getEmail();
        dto.name = user.getName();
        dto.surname = user.getSurname();
        dto.emailPublic = user.isEmailPublic();
        return dto;
    }

    public static UserInfoDto publicInfoFrom(User user) {
        UserInfoDto dto = new UserInfoDto();
        dto.id = user.getId();
        dto.login = user.getLogin();
        if (user.isEmailPublic()) dto.email = user.getEmail();
        dto.name = user.getName();
        dto.surname = user.getSurname();
        return dto;
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

    public Boolean isEmailPublic() {
        return emailPublic;
    }

    public void setEmailPublic(Boolean emailPublic) {
        this.emailPublic = emailPublic;
    }
}
