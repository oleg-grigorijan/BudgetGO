package com.godev.budgetgo.service.data;

import com.godev.budgetgo.entity.OperationsKeySequence;
import com.godev.budgetgo.entity.Storage;

public interface OperationsKeySequenceDataService {
    OperationsKeySequence getByStorage(Storage storage);

    OperationsKeySequence createFor(Storage storage);

    OperationsKeySequence increment(OperationsKeySequence entity);

    void delete(OperationsKeySequence entity);
}
