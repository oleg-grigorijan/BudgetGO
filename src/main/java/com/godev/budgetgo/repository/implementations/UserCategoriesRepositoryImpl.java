package com.godev.budgetgo.repository.implementations;

import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategory;
import com.godev.budgetgo.entity.UserCategoryKey;
import com.godev.budgetgo.repository.UserCategoriesRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class UserCategoriesRepositoryImpl extends AbstractRepository<UserCategory, UserCategoryKey> implements UserCategoriesRepository {

    UserCategoriesRepositoryImpl() {
        super(UserCategory.class);
    }

    @Override
    public List<UserCategory> getByUser(User user) {
        return entityManager
                .createQuery("SELECT c FROM UserCategory c WHERE c.user.id = :userId", entityClass)
                .setParameter("userId", user.getId())
                .getResultList();
    }
}
