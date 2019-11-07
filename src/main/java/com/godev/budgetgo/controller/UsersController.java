package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.service.request.UsersRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersRequestService requestService;

    public UsersController(UsersRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(HttpServletResponse response, @RequestBody UserCreationDto newUser) {
        Long newUserId = requestService.create(newUser).getId();
        response.addHeader("Location", "/api/users/" + newUserId);
    }

    @GetMapping
    @Secured("ROLE_USER")
    @ResponseStatus(HttpStatus.FOUND)
    public void getByLogin(
            HttpServletResponse response,
            @RequestParam String login
    ) {
        response.addHeader("Location", "/api/users/" + requestService.getByLogin(login).getId());
    }

    @GetMapping("/{id}")
    @Secured("ROLE_USER")
    public UserInfoDto getById(@PathVariable Long id) {
        return requestService.getById(id);
    }

    @PatchMapping("/{id}")
    @Secured("ROLE_USER")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDto patch(@PathVariable Long id, @RequestBody UserSettingsPatchesDto patches) {
        return requestService.patch(id, patches);
    }
}
