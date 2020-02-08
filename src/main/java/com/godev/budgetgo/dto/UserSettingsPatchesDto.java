package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.validation.constraint.NullOrNotBlank;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;

@ApiModel("User settings patches model")
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
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

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getSurname() {
        return Optional.ofNullable(surname);
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }

    public Optional<Boolean> getEmailPublic() {
        return Optional.ofNullable(emailPublic);
    }

    public Optional<Long> getMainCurrencyId() {
        return Optional.ofNullable(mainCurrencyId);
    }
}
