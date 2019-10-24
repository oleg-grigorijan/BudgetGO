package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.service.factory.UserDtoFactory;
import org.springframework.stereotype.Service;

@Service
class UserDtoFactoryImpl implements UserDtoFactory {
    @Override
    public UserInfoDto createFrom(User e) {
        UserInfoDto dto = new UserInfoDto();
        dto.setId(e.getId());
        dto.setLogin(e.getLogin());
        dto.setEmail(e.getEmail());
        dto.setEmailPublic(e.isEmailPublic());
        dto.setName(e.getName());
        dto.setSurname(e.getSurname());
        return dto;
    }
}
