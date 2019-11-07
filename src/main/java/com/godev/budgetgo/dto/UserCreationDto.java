package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreationDto {
    private String login;
    private String email;
    private String name;
    private String surname;
    private String password;
    @JsonProperty("isEmailPublic")
    private boolean emailPublic = true;
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

    public boolean isEmailPublic() {
        return emailPublic;
    }

    public void setEmailPublic(boolean emailPublic) {
        this.emailPublic = emailPublic;
    }

    public Long getMainCurrencyId() {
        return mainCurrencyId;
    }

    public void setMainCurrencyId(Long mainCurrencyId) {
        this.mainCurrencyId = mainCurrencyId;
    }
}
