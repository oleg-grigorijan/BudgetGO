package com.godev.budgetgo.service.implementations;

import com.godev.budgetgo.dto.UserDto;
import com.godev.budgetgo.dto.UserPublicInfoDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.exception.UserNotFoundException;
import com.godev.budgetgo.repository.UsersRepository;
import com.godev.budgetgo.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserPublicInfoDto findByEmail(String email) {
        return usersRepository
                .findByEmail(email)
                .filter(User::isEmailPublic)
                .map(UserPublicInfoDto::from)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserPublicInfoDto findByLogin(String login) {
        return usersRepository
                .findByLogin(login)
                .map(UserPublicInfoDto::from)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserPublicInfoDto findById(Long id) {
        return usersRepository
                .findById(id)
                .map(UserPublicInfoDto::from)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDto create(UserDto entity) {
        // TODO: Validation
        User user = new User();
        user.setPropertiesBy(entity);
        // TODO: Hashing
        user.setPasswordHash(entity.getPassword());

        usersRepository.add(user);
        return UserDto.from(user);
    }

    @Override
    public UserDto update(UserDto entity) {
        // TODO: Validation
        User user = new User();
        user.setPropertiesBy(entity);
        // TODO: Hashing
        user.setPasswordHash(entity.getPassword());

        usersRepository.update(user);
        return UserDto.from(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = usersRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        usersRepository.delete(user);
    }
}
