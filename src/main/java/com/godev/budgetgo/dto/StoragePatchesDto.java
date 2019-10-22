package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.Storage;

public class StoragePatchesDto {
    private String name;
    private String description;

    public void applyPatchesTo(Storage storage) {
        if (name != null) storage.setName(name);
        if (description != null) storage.setDescription(description);
    }

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
