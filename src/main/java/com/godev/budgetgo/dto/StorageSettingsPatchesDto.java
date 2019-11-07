package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StorageSettingsPatchesDto {
    @JsonProperty("isIncludedInUserStatistics")
    private Boolean includedInUserStatistics;
    @JsonProperty("isInvitation")
    private Boolean invitation;

    public Boolean getIncludedInUserStatistics() {
        return includedInUserStatistics;
    }

    public void setIncludedInUserStatistics(Boolean includedInUserStatistics) {
        this.includedInUserStatistics = includedInUserStatistics;
    }

    public Boolean getInvitation() {
        return invitation;
    }

    public void setInvitation(Boolean invitation) {
        this.invitation = invitation;
    }
}
