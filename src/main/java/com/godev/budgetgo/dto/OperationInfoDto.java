package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.godev.budgetgo.json.LocalDateSerializer;

import java.time.LocalDate;

public class OperationInfoDto {

    private Long id;

    private long moneyDelta;

    @JsonProperty("category")
    private CategoryInfoDto categoryInfoDto;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    private String description;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateCreated;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateModified;

    @JsonProperty("lastEditor")
    private UserInfoDto lastEditorInfoDto;

    @JsonProperty("creator")
    private UserInfoDto creatorInfoDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getMoneyDelta() {
        return moneyDelta;
    }

    public void setMoneyDelta(long moneyDelta) {
        this.moneyDelta = moneyDelta;
    }

    public CategoryInfoDto getCategoryInfoDto() {
        return categoryInfoDto;
    }

    public void setCategoryInfoDto(CategoryInfoDto categoryInfoDto) {
        this.categoryInfoDto = categoryInfoDto;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public UserInfoDto getLastEditorInfoDto() {
        return lastEditorInfoDto;
    }

    public void setLastEditorInfoDto(UserInfoDto lastEditorInfoDto) {
        this.lastEditorInfoDto = lastEditorInfoDto;
    }

    public UserInfoDto getCreatorInfoDto() {
        return creatorInfoDto;
    }

    public void setCreatorInfoDto(UserInfoDto creatorInfoDto) {
        this.creatorInfoDto = creatorInfoDto;
    }
}
