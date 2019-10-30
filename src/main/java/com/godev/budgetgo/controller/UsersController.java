package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.dto.UserPatchesDto;
import com.godev.budgetgo.exception.BadRequestException;
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
    public UserInfoDto getByLoginOrEmail(
            HttpServletResponse response,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String email
    ) {
        if ((login == null && email == null) || (login != null && email != null)) {
            throw new BadRequestException();
        }

        UserInfoDto foundUser = null;
        if (login != null) foundUser = requestService.getByLogin(login);
        if (email != null) foundUser = requestService.getByEmail(email);

        response.addHeader("Location", "/api/users/" + foundUser.getId());
        return foundUser;
    }

    @GetMapping("/{id}")
    @Secured("ROLE_USER")
    public UserInfoDto getById(@PathVariable Long id) {
        return requestService.getById(id);
    }

    @PatchMapping("/{id}")
    @Secured("ROLE_USER")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDto patch(@PathVariable Long id, @RequestBody UserPatchesDto patches) {
        return requestService.patch(id, patches);
    }
}
