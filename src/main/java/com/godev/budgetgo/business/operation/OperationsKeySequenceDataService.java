package com.godev.budgetgo.business.operation;

import com.godev.budgetgo.domain.operation.OperationsKeySequence;
import com.godev.budgetgo.domain.storage.Storage;

public interface OperationsKeySequenceDataService {

    OperationsKeySequence getByStorage(Storage storage);

    OperationsKeySequence createFor(Storage storage);

    OperationsKeySequence increment(OperationsKeySequence entity);

    void delete(OperationsKeySequence entity);
}
