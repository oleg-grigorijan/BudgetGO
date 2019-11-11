package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.validation.constraint.NullOrNotBlank;

import javax.validation.constraints.Size;
import java.util.Optional;

public class StoragePatchesDto {

    @NullOrNotBlank
    @Size(max = Storage.NAME_MAX_LENGTH)
    private String name;

    @Size(max = Storage.DESCRIPTION_MAX_LENGTH)
    private String description;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
