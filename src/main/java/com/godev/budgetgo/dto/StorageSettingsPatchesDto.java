package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.AssertFalse;
import java.util.Optional;

@ApiModel("Storage settings patches model")
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class StorageSettingsPatchesDto {

    @JsonProperty("isIncludedInUserStatistics")
    private Boolean includedInUserStatistics;

    @AssertFalse
    @JsonProperty("isInvitation")
    private Boolean invitation;

    public Optional<Boolean> getIncludedInUserStatistics() {
        return Optional.ofNullable(includedInUserStatistics);
    }

    public Optional<Boolean> getInvitation() {
        return Optional.ofNullable(invitation);
    }
}
