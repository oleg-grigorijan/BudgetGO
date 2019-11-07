package com.godev.budgetgo.repository.implementations;

import com.godev.budgetgo.entity.Collection;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategoryKey;
import com.godev.budgetgo.repository.CollectionsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class CollectionsRepositoryImpl
        extends AbstractRepository<Collection, UserCategoryKey>
        implements CollectionsRepository {

    CollectionsRepositoryImpl() {
        super(Collection.class);
    }

    @Override
    public List<Collection> findByUser(User user) {
        return entityManager
                .createQuery("SELECT c FROM Collection c WHERE c.user.id = :userId", entityClass)
                .setParameter("userId", user.getId())
                .getResultList();
    }
}
