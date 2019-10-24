package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.service.factory.UsersFactory;
import org.springframework.stereotype.Service;

@Service
class UsersFactoryImpl implements UsersFactory {
    @Override
    public User createFrom(UserCreationDto dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPasswordHash(dto.getPassword()); // TODO: Hashing
        user.setEmailPublic(true);
        return user;
    }
}
