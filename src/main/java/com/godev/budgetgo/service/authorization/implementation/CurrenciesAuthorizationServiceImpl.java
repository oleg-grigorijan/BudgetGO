package com.godev.budgetgo.service.authorization.implementation;

import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.service.authorization.CurrenciesAuthorizationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CurrenciesAuthorizationServiceImpl implements CurrenciesAuthorizationService {
    @Override
    public List<Currency> getAllAuthorizedEntities() {
        return null;
    }

    @Override
    public void authorizeGet(Currency entity) {

    }

    @Override
    public void authorizeCreate(Currency entity) {

    }

    @Override
    public void authorizePatch(Currency entity, Currency patchedEntity) {

    }
}
