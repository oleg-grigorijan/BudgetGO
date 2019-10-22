package com.godev.budgetgo.service.implementations;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.exception.CurrencyNotFoundException;
import com.godev.budgetgo.exception.StorageNotFoundException;
import com.godev.budgetgo.repository.CurrenciesRepository;
import com.godev.budgetgo.repository.StoragesRepository;
import com.godev.budgetgo.service.StoragesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoragesServiceImpl implements StoragesService {

    private final StoragesRepository storagesRepository;
    private final CurrenciesRepository currenciesRepository;

    public StoragesServiceImpl(StoragesRepository storagesRepository, CurrenciesRepository currenciesRepository) {
        this.storagesRepository = storagesRepository;
        this.currenciesRepository = currenciesRepository;
    }

    @Override
    public StorageInfoDto findById(Long id) {
        return storagesRepository
                .findById(id)
                .map(StorageInfoDto::from)
                .orElseThrow(StorageNotFoundException::new);
    }

    @Override
    public List<StorageInfoDto> findAll() {
        return storagesRepository
                .findAll()
                .stream()
                .map(StorageInfoDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public StorageInfoDto create(StorageCreationDto creationDto) {
        // TODO: Validation
        Storage storage = creationDto.getStorage();
        Currency currency = currenciesRepository
                .findById(creationDto.getCurrencyId())
                .orElseThrow(CurrencyNotFoundException::new);
        storage.setCurrency(currency);

        // TODO: Set creator in user_storage_relations
        storagesRepository.add(storage);
        return StorageInfoDto.from(storage);
    }

    @Override
    public StorageInfoDto patch(Long id, StoragePatchesDto patchesDto) {
        Storage storage = storagesRepository
                .findById(id)
                .orElseThrow(StorageNotFoundException::new);

        // TODO: Validation
        patchesDto.applyPatchesTo(storage);

        storagesRepository.update(storage);
        return StorageInfoDto.from(storage);
    }

    @Override
    public void deleteById(Long id) {
        Storage storage = storagesRepository
                .findById(id)
                .orElseThrow(StorageNotFoundException::new);

        storagesRepository.delete(storage);
    }
}