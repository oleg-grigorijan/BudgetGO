package com.godev.budgetgo.business.operation.impl;

import com.godev.budgetgo.domain.operation.OperationsKeySequence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationsKeySequenceRepository extends JpaRepository<OperationsKeySequence, Long> {
}
