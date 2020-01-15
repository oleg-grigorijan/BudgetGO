package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.request.UsersRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersRequestService requestService;

    public UsersController(UsersRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoDto create(HttpServletResponse response, @RequestBody @Valid UserCreationDto newUser) {
        UserInfoDto createdDto = requestService.create(newUser);
        response.addHeader("Location", "/api/users/" + createdDto.getId());
        return createdDto;
    }

    @GetMapping(params = "login")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDto getByLogin(@RequestParam String login) {
        return requestService.getByLogin(login);
    }

    @GetMapping(params = "email")
    public ResponseEntity<UserInfoDto> getByEmail(@RequestParam String email) {
        UserInfoDto dto = this.requestService.getByEmail(email);
        if (dto.getEmailPublic()) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDto getById(@PathVariable Long id) {
        return requestService.getById(id);
    }
}
