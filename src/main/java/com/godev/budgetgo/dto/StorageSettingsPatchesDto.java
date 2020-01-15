package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.AssertFalse;
import java.util.Optional;

public class StorageSettingsPatchesDto {

    @JsonProperty("isIncludedInUserStatistics")
    private Boolean includedInUserStatistics;

    @AssertFalse
    @JsonProperty("isInvitation")
    private Boolean invitation;

    public Optional<Boolean> getIncludedInUserStatistics() {
        return Optional.ofNullable(includedInUserStatistics);
    }

    public void setIncludedInUserStatistics(Boolean includedInUserStatistics) {
        this.includedInUserStatistics = includedInUserStatistics;
    }

    public Optional<Boolean> getInvitation() {
        return Optional.ofNullable(invitation);
    }

    public void setInvitation(Boolean invitation) {
        this.invitation = invitation;
    }
}
