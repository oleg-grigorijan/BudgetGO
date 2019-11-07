package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.service.factory.UsersFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class UsersFactoryImpl implements UsersFactory {
    private final PasswordEncoder passwordEncoder;

    public UsersFactoryImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createFrom(UserCreationDto dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setEmailPublic(dto.isEmailPublic());
        return user;
    }
}
