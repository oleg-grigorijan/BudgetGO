package com.godev.budgetgo.controller.impl;

import com.godev.budgetgo.controller.UsersController;
import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.request.UsersRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class UsersControllerImpl implements UsersController {

    private final UsersRequestService requestService;

    public UsersControllerImpl(UsersRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public UserInfoDto create(HttpServletResponse response, @Valid UserCreationDto newUser) {
        UserInfoDto createdDto = requestService.create(newUser);
        response.addHeader("Location", "/api/users/" + createdDto.getId());
        return createdDto;
    }

    @Override
    public UserInfoDto getByLogin(String login) {
        return requestService.getByLogin(login);
    }

    @Override
    public ResponseEntity<?> getByEmail(String email) {
        UserInfoDto dto = this.requestService.getByEmail(email);
        if (dto.getEmailPublic()) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public UserInfoDto getById(Long id) {
        return requestService.getById(id);
    }
}
