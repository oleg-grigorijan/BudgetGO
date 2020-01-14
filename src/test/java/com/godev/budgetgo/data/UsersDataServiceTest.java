package com.godev.budgetgo.data;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.data.impl.UsersDataServiceImpl;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.exception.UserNotFoundException;
import com.godev.budgetgo.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class UsersDataServiceTest {

    private UsersDataService dataService;

    @Mock
    UsersRepository repository;

    @BeforeEach
    void setUp() {
        dataService = new UsersDataServiceImpl(repository);
    }

    @Test
    void getById_general_correctReturnValue() {
        User entity = new User();
        entity.setId(1L);

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        assertThat(dataService.getById(entity.getId())).isSameAs(entity);
    }

    @Test
    void getById_nonExistingEntity_exceptionThrown() {
        when(repository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> dataService.getById(1L)).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void getAll_general_correctReturnValue() {
        ArrayList<User> entities = new ArrayList<>();
        entities.add(new User());
        when(repository.getAll()).thenReturn(entities);

        assertThat(dataService.getAll()).isSameAs(entities);
    }

    @Test
    void getByEmail_general_correctReturnValue() {
        User entity = new User();
        entity.setId(1L);
        entity.setEmail("abc");

        when(repository.findByEmail(entity.getEmail())).thenReturn(Optional.of(entity));

        assertThat(dataService.getByEmail(entity.getEmail())).isSameAs(entity);
    }

    @Test
    void getByEmail_nonExistingEntity_exceptionThrown() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> dataService.getByEmail("abc")).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void getByLogin_general_correctReturnValue() {
        User entity = new User();
        entity.setId(1L);
        entity.setLogin("abc");

        when(repository.findByLogin(entity.getLogin())).thenReturn(Optional.of(entity));

        assertThat(dataService.getByLogin(entity.getLogin())).isSameAs(entity);
    }

    @Test
    void getByLogin_nonExistingEntity_exceptionThrown() {
        when(repository.findByLogin(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> dataService.getByLogin("abc")).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void add_general_repositoryAddCall() {
        User entity = new User();

        dataService.add(entity);
        verify(repository).add(refEq(entity));
    }

    @Test
    void update_general_repositoryUpdateCall() {
        User entity = new User();
        entity.setId(1L);

        dataService.update(entity);
        verify(repository).update(refEq(entity));
    }

    @Test
    void delete_general_repositoryDeleteCall() {
        User entity = new User();
        entity.setId(1L);

        dataService.delete(entity);
        verify(repository).delete(refEq(entity));
    }
}
