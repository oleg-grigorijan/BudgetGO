package com.godev.budgetgo.service.authorization;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserPatchesDto;
import com.godev.budgetgo.entity.User;

public interface UsersAuthorizationService
        extends AuthorizationService<User, UserCreationDto, UserPatchesDto> {
}
