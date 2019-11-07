package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.CollectionCreationDto;
import com.godev.budgetgo.dto.CollectionInfoDto;
import com.godev.budgetgo.service.request.CollectionsRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/collections")
public class CollectionsController {
    private final CollectionsRequestService requestService;

    public CollectionsController(
            CollectionsRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @Secured("ROLE_USER")
    @ResponseStatus(HttpStatus.OK)
    public List<CollectionInfoDto> getAll() {
        return requestService.getAll();
    }

    @PostMapping
    @Secured("ROLE_USER")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            HttpServletResponse response,
            @RequestBody CollectionCreationDto creationDto
    ) {
        requestService.create(creationDto);
        response.addHeader("Location", "/api/collections/" + creationDto.getCategoryId());
    }

    @GetMapping("/{categoryId}")
    @Secured("ROLE_USER")
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
