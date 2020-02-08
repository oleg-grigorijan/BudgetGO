package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ApiModel("User creation model")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class UserCreationDto {

    @NotBlank
    @Pattern(regexp = User.LOGIN_PATTERN)
    @Size(max = User.LOGIN_MAX_LENGTH)
    private String login;

    @NotBlank
    @Email
    @Size(max = User.EMAIL_MAX_LENGTH)
    private String email;

    @NotBlank
    @Size(max = User.NAME_MAX_LENGTH)
    private String name;

    @NotBlank
    @Size(max = User.SURNAME_MAX_LENGTH)
    private String surname;

    @NotNull
    @Size(min = User.PASSWORD_MIN_LENGTH)
    private String password;

    @JsonProperty("isEmailPublic")
    private boolean emailPublic = true;

    @NotNull
    private Long mainCurrencyId;
}
