package com.godev.budgetgo.service.request;


import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.dto.UserPatchesDto;

public interface UsersRequestService
        extends RequestService<Long, UserInfoDto, UserCreationDto, UserPatchesDto> {

    UserInfoDto getByLogin(String login);
}
