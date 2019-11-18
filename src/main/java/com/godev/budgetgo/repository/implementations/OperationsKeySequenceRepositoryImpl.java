package com.godev.budgetgo.repository.implementations;

import com.godev.budgetgo.entity.OperationsKeySequence;
import com.godev.budgetgo.repository.OperationsKeySequenceRepository;
import org.springframework.stereotype.Repository;

@Repository
class OperationsKeySequenceRepositoryImpl extends AbstractRepository<OperationsKeySequence, Long> implements OperationsKeySequenceRepository {

    OperationsKeySequenceRepositoryImpl() {
        super(OperationsKeySequence.class);
    }
}
