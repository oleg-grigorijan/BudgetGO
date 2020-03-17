package com.godev.budgetgo.api.rest.user;

import com.godev.budgetgo.api.rest.user.dto.UserSettingsInfoDto;
import com.godev.budgetgo.api.rest.user.dto.UserSettingsPatchesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = "Authorized user")
@RequestMapping("/api/me")
public interface UserSettingsController {

    @ApiOperation("Returns authorized user account info")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    UserSettingsInfoDto get();

    @ApiOperation("Patches authorized user account info")
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    UserSettingsInfoDto patch(@RequestBody UserSettingsPatchesDto patchesDto);
}
