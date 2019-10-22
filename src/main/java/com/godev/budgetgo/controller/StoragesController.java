package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.service.StoragesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/storages")
public class StoragesController {

    private final StoragesService storagesService;

    public StoragesController(StoragesService storagesService) {
        this.storagesService = storagesService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StorageInfoDto> getAll() {
        return storagesService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(HttpServletResponse response, @RequestBody StorageCreationDto creationDto) {
        Long newStorageId = storagesService.create(creationDto).getId();
        response.addHeader("Location", "/api/storages/" + newStorageId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StorageInfoDto getById(@PathVariable Long id) {
        return storagesService.findById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StorageInfoDto patch(@PathVariable Long id, @RequestBody StoragePatchesDto patches) {
        return storagesService.patch(id, patches);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        storagesService.deleteById(id);
    }
}
