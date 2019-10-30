package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.service.request.StoragesRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/storages")
@Secured("ROLE_USER")
public class StoragesController {

    private final StoragesRequestService requestService;

    public StoragesController(StoragesRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StorageInfoDto> getAll() {
        return requestService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(HttpServletResponse response, @RequestBody StorageCreationDto creationDto) {
        Long newStorageId = requestService.create(creationDto).getId();
        response.addHeader("Location", "/api/storages/" + newStorageId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StorageInfoDto getById(@PathVariable Long id) {
        return requestService.getById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StorageInfoDto patch(@PathVariable Long id, @RequestBody StoragePatchesDto patches) {
        return requestService.patch(id, patches);
    }
}
