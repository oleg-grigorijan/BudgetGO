package com.godev.budgetgo.api.rest.user.impl;

import com.godev.budgetgo.api.rest.user.UsersController;
import com.godev.budgetgo.api.rest.user.dto.UserCreationDto;
import com.godev.budgetgo.api.rest.user.dto.UserInfoDto;
import com.godev.budgetgo.business.user.UsersRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UsersControllerImpl implements UsersController {

    private final UsersRequestService requestService;

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
