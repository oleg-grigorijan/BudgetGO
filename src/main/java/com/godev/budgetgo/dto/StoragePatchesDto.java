package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.validation.constraint.NullOrNotBlank;

import javax.validation.constraints.Size;

public class StoragePatchesDto {

    @NullOrNotBlank
    @Size(max = Storage.NAME_MAX_LENGTH)
    private String name;

    @Size(max = Storage.DESCRIPTION_MAX_LENGTH)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
