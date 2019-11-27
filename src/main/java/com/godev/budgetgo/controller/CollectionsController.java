package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.CollectionCreationDto;
import com.godev.budgetgo.dto.CollectionInfoDto;
import com.godev.budgetgo.request.CollectionsRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/collections")
public class CollectionsController {
    private final CollectionsRequestService requestService;

    public CollectionsController(CollectionsRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CollectionInfoDto> getAll() {
        return requestService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionInfoDto create(HttpServletResponse response, @RequestBody @Valid CollectionCreationDto creationDto) {
        CollectionInfoDto createdDto = requestService.create(creationDto);
        response.addHeader("Location", "/api/collections/" + creationDto.getCategoryId());
        return createdDto;
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CollectionInfoDto getByCategoryId(@PathVariable Long categoryId) {
        return requestService.getByCategoryId(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long categoryId) {
        requestService.deleteByCategoryId(categoryId);
    }
}
