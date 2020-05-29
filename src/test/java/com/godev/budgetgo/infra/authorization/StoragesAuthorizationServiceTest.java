package com.godev.budgetgo.infra.authorization;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.business.storage.StoragesDataService;
import com.godev.budgetgo.business.storage.StoragesRelationsDataService;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.domain.storage.UserStorageKey;
import com.godev.budgetgo.domain.storage.UserStorageRole;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import com.godev.budgetgo.infra.authorization.impl.StoragesAuthorizationServiceImpl;
import com.godev.budgetgo.infra.error.exception.StorageAccessDeniedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class StoragesAuthorizationServiceTest {

    private StoragesAuthorizationService authorizationService;

    @Mock
    private UserStorageRoleAuthoritiesService authoritiesService;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @Mock
    private StoragesDataService storagesDataService;

    @Mock
    private StoragesRelationsDataService relationsDataService;

    @BeforeEach
    void setUp() {
        authorizationService = new StoragesAuthorizationServiceImpl(
                authoritiesService,
                authenticationFacade,
                storagesDataService,
                relationsDataService
        );
    }

    @Test
    void getAllAuthorizedEntities_general_correctReturnValue() {
        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);
        ArrayList<Storage> entities = new ArrayList<>();
        entities.add(new Storage());
        when(storagesDataService.getByUser(authenticatedUser)).thenReturn(entities);

        Assertions.assertThat(authorizationService.getAllAuthorizedEntities()).isSameAs(entities);
    }

    @Test
    void authorizeAccess_hasAccess_noExceptionThrown() {
        Storage storage = new Storage();
        storage.setId(1L);
        User authenticatedUser = new User();
        authenticatedUser.setId(2L);
        StorageRelations relations = new StorageRelations();
        relations.setStorage(storage);
        relations.setUser(authenticatedUser);
        relations.setId(new UserStorageKey(authenticatedUser.getId(), storage.getId()));

        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);
        when(relationsDataService.findById(new UserStorageKey(authenticatedUser.getId(), storage.getId())))
                .thenReturn(Optional.of(relations));

        authorizationService.authorizeAccess(storage);
    }

    @Test
    void authorizeAccess_noAccess_exceptionThrown() {
        Storage storage = new Storage();
        storage.setId(1L);
        User authenticatedUser = new User();
        authenticatedUser.setId(2L);

        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);
        when(relationsDataService.findById(new UserStorageKey(authenticatedUser.getId(), storage.getId())))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> authorizationService.authorizeAccess(storage)).isInstanceOf(StorageAccessDeniedException.class);
    }

    @Test
    void authorizeModificationAccess_hasAccess_noExceptionThrown() {
        Storage storage = new Storage();
        storage.setId(1L);
        User authenticatedUser = new User();
        authenticatedUser.setId(2L);
        StorageRelations relations = new StorageRelations();
        relations.setStorage(storage);
        relations.setUser(authenticatedUser);
        relations.setId(new UserStorageKey(authenticatedUser.getId(), storage.getId()));
        relations.setUserRole(UserStorageRole.ADMIN);

        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);
        when(relationsDataService.findById(new UserStorageKey(authenticatedUser.getId(), storage.getId())))
                .thenReturn(Optional.of(relations));
        when(authoritiesService.canModifyStorage(any(UserStorageRole.class))).thenReturn(true);

        authorizationService.authorizeModificationAccess(storage);
    }

    @Test
    void authorizeModificationAccess_noAccess_exceptionThrown() {
        Storage storage = new Storage();
        storage.setId(1L);
        User authenticatedUser = new User();
        authenticatedUser.setId(2L);
        StorageRelations relations = new StorageRelations();
        relations.setStorage(storage);
        relations.setUser(authenticatedUser);
        relations.setId(new UserStorageKey(authenticatedUser.getId(), storage.getId()));
        relations.setUserRole(UserStorageRole.VIEWER);

        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);
        when(relationsDataService.findById(new UserStorageKey(authenticatedUser.getId(), storage.getId())))
                .thenReturn(Optional.of(relations));
        when(authoritiesService.canModifyStorage(any(UserStorageRole.class))).thenReturn(false);

        Assertions.assertThatThrownBy(() -> authorizationService.authorizeModificationAccess(storage))
                .isInstanceOf(StorageAccessDeniedException.class);
    }
}
