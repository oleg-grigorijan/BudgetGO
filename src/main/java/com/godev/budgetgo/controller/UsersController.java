package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.UserDto;
import com.godev.budgetgo.dto.UserPublicInfoDto;
import com.godev.budgetgo.exception.BadRequestException;
import com.godev.budgetgo.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private UsersService usersService;

    public UsersController(UsersService userService) {
        this.usersService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(HttpServletResponse response, @RequestBody UserDto newUser) {
        newUser.setId(null);
        Long newUserId = usersService.create(newUser).getId();
        response.addHeader("Location", "/api/users/" + newUserId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public UserPublicInfoDto getByLogin(
            HttpServletResponse response,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String email
    ) {
        if ((login == null && email == null) || (login != null && email != null)) {
            throw new BadRequestException();
        }

        UserPublicInfoDto foundUser = null;
        if (login != null) foundUser = usersService.findByLogin(login);
        if (email != null) foundUser = usersService.findByEmail(email);

        response.addHeader("Location", "/api/users/" + foundUser.getId());
        return foundUser;
    }

    @GetMapping("/{id}")
    public UserPublicInfoDto getById(@PathVariable Long id) {
        return usersService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody UserDto updatedUser) {
        updatedUser.setId(id);
        usersService.update(updatedUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usersService.deleteById(id);
    }
}
