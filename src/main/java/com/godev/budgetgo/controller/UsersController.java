package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.dto.UserPatchesDto;
import com.godev.budgetgo.exception.BadRequestException;
import com.godev.budgetgo.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService userService) {
        this.usersService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(HttpServletResponse response, @RequestBody UserCreationDto newUser) {
        Long newUserId = usersService.create(newUser).getId();
        response.addHeader("Location", "/api/users/" + newUserId);
    }

    @GetMapping
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
        if (login != null) foundUser = usersService.findByLogin(login);
        if (email != null) foundUser = usersService.findByEmail(email);

        response.addHeader("Location", "/api/users/" + foundUser.getId());
        return foundUser;
    }

    @GetMapping("/{id}")
    public UserInfoDto getById(@PathVariable Long id) {
        return usersService.findById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDto patch(@PathVariable Long id, @RequestBody UserPatchesDto patches) {
        return usersService.patch(id, patches);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usersService.deleteById(id);
    }
}
