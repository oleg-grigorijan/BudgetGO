package com.godev.budgetgo.business.user;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.business.user.impl.UserCategoriesDataServiceImpl;
import com.godev.budgetgo.business.user.impl.UserCategoriesRepository;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.domain.user.UserCategory;
import com.godev.budgetgo.domain.user.UserCategoryKey;
import com.godev.budgetgo.infra.error.exception.UserCategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class UserCategoriesDataServiceTest {

    private UserCategoriesDataService dataService;

    @Mock
    UserCategoriesRepository repository;

    @BeforeEach
    void setUp() {
        dataService = new UserCategoriesDataServiceImpl(repository);
    }

    @Test
    void getById_general_correctReturnValue() {
        UserCategory entity = new UserCategory();
        entity.setId(new UserCategoryKey(1L, 2L));

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        assertThat(dataService.getById(entity.getId())).isSameAs(entity);
    }

    @Test
    void getById_nonExistingEntity_exceptionThrown() {
        when(repository.findById(any(UserCategoryKey.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> dataService.getById(new UserCategoryKey(1L, 2L))).isInstanceOf(UserCategoryNotFoundException.class);
    }

    @Test
    void getAll_general_correctReturnValue() {
        ArrayList<UserCategory> entities = new ArrayList<>();
        entities.add(new UserCategory());
        when(repository.findAll()).thenReturn(entities);

        assertThat(dataService.getAll()).isSameAs(entities);
    }

    @Test
    void getByUser_general_correctReturnValue() {
        User user = new User();
        user.setId(1L);

        ArrayList<UserCategory> entities = new ArrayList<>();
        entities.add(new UserCategory());
        when(repository.findByUser(user)).thenReturn(entities);

        assertThat(dataService.getByUser(user)).isSameAs(entities);
    }

    @Test
    void add_general_repositoryAddCall() {
        UserCategory entity = new UserCategory();

        dataService.add(entity);
        verify(repository).save(refEq(entity));
    }

    @Test
    void update_general_repositoryUpdateCall() {
        UserCategory entity = new UserCategory();
        entity.setId(new UserCategoryKey(1L, 2L));

        dataService.update(entity);
        verify(repository).save(refEq(entity));
    }

    @Test
    void delete_general_repositoryDeleteCall() {
        UserCategory entity = new UserCategory();
        entity.setId(new UserCategoryKey(1L, 2L));

        dataService.delete(entity);
        verify(repository).delete(refEq(entity));
    }
}
