package com.godev.budgetgo.service.implementations;

import com.godev.budgetgo.dto.UserStorageRelationsCreationDto;
import com.godev.budgetgo.dto.UserStorageRelationsInfoDto;
import com.godev.budgetgo.dto.UserStorageRelationsPatchDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.exception.StorageNotFoundException;
import com.godev.budgetgo.exception.UserNotFoundException;
import com.godev.budgetgo.exception.UserStorageRelationsNotFoundException;
import com.godev.budgetgo.repository.StoragesRepository;
import com.godev.budgetgo.repository.UsersRepository;
import com.godev.budgetgo.repository.UsersStoragesRelationsRepository;
import com.godev.budgetgo.service.UsersStoragesRelationsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersStoragesRelationsServiceImpl implements UsersStoragesRelationsService {

    private final UsersStoragesRelationsRepository relationsRepository;
    private final StoragesRepository storagesRepository;
    private final UsersRepository usersRepository;

    public UsersStoragesRelationsServiceImpl(
            UsersStoragesRelationsRepository relationsRepository,
            StoragesRepository storagesRepository,
            UsersRepository usersRepository
    ) {
        this.relationsRepository = relationsRepository;
        this.storagesRepository = storagesRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public UserStorageRelationsInfoDto findById(UserStorageKey id) {
        return relationsRepository
                .findById(id)
                .map(UserStorageRelationsInfoDto::publicInfoFrom)
                .orElseThrow(UserStorageRelationsNotFoundException::new);
    }

    @Override
    public List<UserStorageRelationsInfoDto> findByStorageId(Long storageId) {
        return relationsRepository
                .findByStorageId(storageId)
                .stream()
                .map(UserStorageRelationsInfoDto::publicInfoFrom)
                .collect(Collectors.toList());
    }

    @Override
    public UserStorageRelationsInfoDto create(
            Long storageId,
            UserStorageRelationsCreationDto creationDto
    ) {
        UserStorageRelations relations = creationDto.getRelations();

        Storage storage = storagesRepository
                .findById(storageId)
                .orElseThrow(StorageNotFoundException::new);
        relations.setStorage(storage);

        User user = usersRepository
                .findById(creationDto.getUserId())
                .orElseThrow(UserNotFoundException::new);
        relations.setUser(user);

        relations.setId(new UserStorageKey(creationDto.getUserId(), storageId));

        relationsRepository.add(relations);
        return UserStorageRelationsInfoDto.publicInfoFrom(relations);
    }

    @Override
    public UserStorageRelationsInfoDto patch(
            UserStorageKey id,
            UserStorageRelationsPatchDto patchDto
    ) {
        UserStorageRelations relations = relationsRepository
                .findById(id)
                .orElseThrow(UserStorageRelationsNotFoundException::new);

        // TODO: Validation
        patchDto.applyPatchesTo(relations);

        relationsRepository.update(relations);
        return UserStorageRelationsInfoDto.publicInfoFrom(relations);
    }

    @Override
    public void deleteById(UserStorageKey id) {
        UserStorageRelations relations = relationsRepository
                .findById(id)
                .orElseThrow(UserStorageRelationsNotFoundException::new);

        relationsRepository.delete(relations);
    }
}
