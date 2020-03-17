package com.godev.budgetgo.api.rest.storage;

import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsPatchesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = "Storage settings")
@RequestMapping("/api/storages/{id}/settings")
public interface StorageSettingsController {

    @ApiOperation("Returns settings of storage found by id for authorized user")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    StorageSettingsInfoDto getById(@PathVariable Long id);

    @ApiOperation("Patches settings of storage found by id for authorized user")
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    StorageSettingsInfoDto patchById(@PathVariable Long id, @RequestBody StorageSettingsPatchesDto patchesDto);

    @ApiOperation("Unsubscribes authorized user from storage")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id);
}
