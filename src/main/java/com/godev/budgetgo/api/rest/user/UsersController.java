package com.godev.budgetgo.api.rest.user;

import com.godev.budgetgo.api.rest.user.dto.UserCreationDto;
import com.godev.budgetgo.api.rest.user.dto.UserInfoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "Users")
@RequestMapping("/api/users")
public interface UsersController {

    @ApiOperation("Registers user")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserInfoDto create(HttpServletResponse response, @RequestBody UserCreationDto newUser);

    @ApiOperation("Finds user by login")
    @GetMapping(params = "login")
    @ResponseStatus(HttpStatus.OK)
    UserInfoDto getByLogin(@RequestParam String login);

    @ApiOperation("Finds user by email")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = UserInfoDto.class),
            @ApiResponse(code = 204, message = "No content for existing user with private email", response = void.class)
    })
    @GetMapping(params = "email")
    ResponseEntity<?> getByEmail(@RequestParam String email);

    @ApiOperation("Finds user by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserInfoDto getById(@PathVariable Long id);
}
