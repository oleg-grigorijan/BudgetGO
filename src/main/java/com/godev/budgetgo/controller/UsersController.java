package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.request.UsersRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public void getByLogin(HttpServletResponse response, @RequestParam String login) {
        response.addHeader("Location", "/api/users/" + requestService.getByLogin(login).getId());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDto getById(@PathVariable Long id) {
        return requestService.getById(id);
    }
}
