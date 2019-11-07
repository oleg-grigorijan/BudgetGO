package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StorageSettingsPatchesDto {
    @JsonProperty("isIncludedInUserStatistics")
    private Boolean includedInUserStatistics;

    public Boolean getIncludedInUserStatistics() {
        return includedInUserStatistics;
    }

    public void setIncludedInUserStatistics(Boolean includedInUserStatistics) {
        this.includedInUserStatistics = includedInUserStatistics;
    }
}
