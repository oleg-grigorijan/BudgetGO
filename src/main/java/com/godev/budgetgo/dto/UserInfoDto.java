package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("User info model")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class UserInfoDto {

    private Long id;

    private String login;

    private String email;

    private String name;

    private String surname;

    @JsonProperty("isEmailPublic")
    private Boolean emailPublic;
}
