package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.validation.constraint.NullOrNotBlank;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserSettingsPatchesDto {
    @NullOrNotBlank
    @Pattern(regexp = User.LOGIN_PATTERN)
    @Size(max = User.LOGIN_MAX_LENGTH)
    private String login;

    @NullOrNotBlank
    @Email
    @Size(max = User.EMAIL_MAX_LENGTH)
    private String email;

    @NullOrNotBlank
    @Size(max = User.NAME_MAX_LENGTH)
    private String name;

    @NullOrNotBlank
    @Size(max = User.SURNAME_MAX_LENGTH)
    private String surname;

    @Size(min = User.PASSWORD_MIN_LENGTH)
    private String password;

    @JsonProperty("isEmailPublic")
    private Boolean emailPublic;

    private Long mainCurrencyId;

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

    public Boolean getEmailPublic() {
        return emailPublic;
    }

    public void setEmailPublic(Boolean emailPublic) {
        this.emailPublic = emailPublic;
    }

    public Long getMainCurrencyId() {
        return mainCurrencyId;
    }

    public void setMainCurrencyId(Long mainCurrencyId) {
        this.mainCurrencyId = mainCurrencyId;
    }
}
