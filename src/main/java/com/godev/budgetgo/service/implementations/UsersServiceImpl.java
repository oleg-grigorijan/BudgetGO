package com.godev.budgetgo.service.implementations;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.dto.UserPatchesDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.exception.NotFoundExcepion;
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
    public UserInfoDto findByEmail(String email) {
        return usersRepository
                .findByEmail(email)
                .filter(User::isEmailPublic)
                .map(UserInfoDto::publicInfoFrom)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserInfoDto findByLogin(String login) {
        return usersRepository
                .findByLogin(login)
                .map(UserInfoDto::publicInfoFrom)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserInfoDto findById(Long id) {
        return usersRepository
                .findById(id)
                .map(UserInfoDto::publicInfoFrom)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserInfoDto create(UserCreationDto creationDto) {
        // TODO: Validation
        User user = creationDto.getUser();
        // TODO: Hashing
        user.setPasswordHash(creationDto.getPassword());

        usersRepository.add(user);
        return UserInfoDto.from(user);
    }

    @Override
    public UserInfoDto patch(Long id, UserPatchesDto patchesDto) {
        User user = usersRepository
                .findById(id)
                .orElseThrow(NotFoundExcepion::new);

        // TODO: Validation
        patchesDto.applyPatchesTo(user);
        // TODO: Hashing
        if (patchesDto.getPassword() != null) {
            user.setPasswordHash(patchesDto.getPassword());
        }

        usersRepository.update(user);
        return UserInfoDto.from(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = usersRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        usersRepository.delete(user);
    }
}
