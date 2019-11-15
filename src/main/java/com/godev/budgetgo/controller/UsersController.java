package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.service.request.UsersRequestService;
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
    public void create(HttpServletResponse response, @RequestBody @Valid UserCreationDto newUser) {
        Long newUserId = requestService.create(newUser).getId();
        response.addHeader("Location", "/api/users/" + newUserId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public void getByLogin(
            HttpServletResponse response,
            @RequestParam String login
    ) {
        response.addHeader("Location", "/api/users/" + requestService.getByLogin(login).getId());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDto getById(@PathVariable Long id) {
        return requestService.getById(id);
    }
}
