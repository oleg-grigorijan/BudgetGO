package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.validation.constraint.NullOrNotBlank;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;

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

    public Optional<String> getLogin() {
        return Optional.ofNullable(login);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getSurname() {
        return Optional.ofNullable(surname);
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Optional<Boolean> getEmailPublic() {
        return Optional.ofNullable(emailPublic);
    }

    public void setEmailPublic(Boolean emailPublic) {
        this.emailPublic = emailPublic;
    }

    public Optional<Long> getMainCurrencyId() {
        return Optional.ofNullable(mainCurrencyId);
    }

    public void setMainCurrencyId(Long mainCurrencyId) {
        this.mainCurrencyId = mainCurrencyId;
    }
}
