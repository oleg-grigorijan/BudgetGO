package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.validation.constraint.NullOrNotBlank;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.Optional;

@ApiModel("Storage patches model")
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class StoragePatchesDto {

    @NullOrNotBlank
    @Size(max = Storage.NAME_MAX_LENGTH)
    private String name;

    @Size(max = Storage.DESCRIPTION_MAX_LENGTH)
    private String description;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }
}
