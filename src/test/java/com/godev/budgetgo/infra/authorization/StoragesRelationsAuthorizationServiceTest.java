package com.godev.budgetgo.infra.authorization;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.api.rest.storage.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.business.storage.StoragesRelationsDataService;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.domain.storage.UserStorageKey;
import com.godev.budgetgo.domain.storage.UserStorageRole;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import com.godev.budgetgo.infra.authorization.impl.StoragesRelationsAuthorizationServiceImpl;
import com.godev.budgetgo.infra.error.exception.StorageAccessDeniedException;
import com.godev.budgetgo.infra.error.exception.StorageRelationsAccessDeniedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class StoragesRelationsAuthorizationServiceTest {

    private StoragesRelationsAuthorizationService authorizationService;

    @Mock
    private UserStorageRoleAuthoritiesService authoritiesService;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @Mock
    private StoragesRelationsDataService relationsDataService;

    @BeforeEach
    void setUp() {
        authorizationService = new StoragesRelationsAuthorizationServiceImpl(authoritiesService, authenticationFacade, relationsDataService);
    }

    @Test
    void authorizeCreationAccess_hasAccess_noExceptionThrown() {
        StorageRelations relationsToAuthorize = new StorageRelations();
        relationsToAuthorize.setUserRole(UserStorageRole.EDITOR);
        relationsToAuthorize.setStorage(new Storage());
        relationsToAuthorize.getStorage().setId(2L);
        relationsToAuthorize.setUser(new User());
        relationsToAuthorize.getUser().setId(3L);

        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        StorageRelations authUserRelations = new StorageRelations();
        authUserRelations.setUserRole(UserStorageRole.ADMIN);
        authUserRelations.setStorage(relationsToAuthorize.getStorage());
        authUserRelations.setUser(authenticatedUser);
        authUserRelations.setId(new UserStorageKey(authUserRelations.getUser().getId(), authUserRelations.getStorage().getId()));
        when(relationsDataService.findById(new UserStorageKey(authenticatedUser.getId(), relationsToAuthorize.getStorage().getId())))
                .thenReturn(Optional.of(authUserRelations));

        when(authoritiesService.canBeCreatedBy(relationsToAuthorize.getUserRole(), authUserRelations.getUserRole()))
                .thenReturn(true);

        authorizationService.authorizeCreationAccess(relationsToAuthorize);
    }

    @Test
    void authorizeCreationAccess_noStorageAccess_exceptionThrown() {
        StorageRelations relationsToAuthorize = new StorageRelations();
        relationsToAuthorize.setUserRole(UserStorageRole.EDITOR);
        relationsToAuthorize.setStorage(new Storage());
        relationsToAuthorize.getStorage().setId(2L);
        relationsToAuthorize.setUser(new User());
        relationsToAuthorize.getUser().setId(3L);

        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        when(relationsDataService.findById(new UserStorageKey(authenticatedUser.getId(), relationsToAuthorize.getStorage().getId())))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorizationService.authorizeCreationAccess(relationsToAuthorize))
                .isInstanceOf(StorageAccessDeniedException.class);
    }

    @Test
    void authorizeCreationAccess_noCreationAuthority_exceptionThrown() {
        StorageRelations relationsToAuthorize = new StorageRelations();
        relationsToAuthorize.setUserRole(UserStorageRole.EDITOR);
        relationsToAuthorize.setStorage(new Storage());
        relationsToAuthorize.getStorage().setId(2L);
        relationsToAuthorize.setUser(new User());
        relationsToAuthorize.getUser().setId(3L);

        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        StorageRelations authUserRelations = new StorageRelations();
        authUserRelations.setUserRole(UserStorageRole.VIEWER);
        authUserRelations.setStorage(relationsToAuthorize.getStorage());
        authUserRelations.setUser(authenticatedUser);
        authUserRelations.setId(new UserStorageKey(authUserRelations.getUser().getId(), authUserRelations.getStorage().getId()));
        when(relationsDataService.findById(new UserStorageKey(authenticatedUser.getId(), relationsToAuthorize.getStorage().getId())))
                .thenReturn(Optional.of(authUserRelations));

        when(authoritiesService.canBeCreatedBy(relationsToAuthorize.getUserRole(), authUserRelations.getUserRole()))
                .thenReturn(false);

        assertThatThrownBy(() -> authorizationService.authorizeCreationAccess(relationsToAuthorize))
                .isInstanceOf(StorageRelationsAccessDeniedException.class);
    }

    @Test
    void authorizeModificationAccess_hasAccess_noExceptionThrown() {
        StorageRelations relationsToAuthorize = new StorageRelations();
        relationsToAuthorize.setUserRole(UserStorageRole.EDITOR);
        relationsToAuthorize.setStorage(new Storage());
        relationsToAuthorize.getStorage().setId(2L);
        relationsToAuthorize.setUser(new User());
        relationsToAuthorize.getUser().setId(3L);

        StorageRelationsPatchesDto patchesDto = new StorageRelationsPatchesDto();
        patchesDto.setUserRole(UserStorageRole.VIEWER);

        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        StorageRelations authUserRelations = new StorageRelations();
        authUserRelations.setUserRole(UserStorageRole.ADMIN);
        authUserRelations.setStorage(relationsToAuthorize.getStorage());
        authUserRelations.setUser(authenticatedUser);
        authUserRelations.setId(new UserStorageKey(authUserRelations.getUser().getId(), authUserRelations.getStorage().getId()));
        when(relationsDataService.findById(new UserStorageKey(authenticatedUser.getId(), relationsToAuthorize.getStorage().getId())))
                .thenReturn(Optional.of(authUserRelations));

        when(authoritiesService.canBeModifiedBy(relationsToAuthorize.getUserRole(), authUserRelations.getUserRole()))
                .thenReturn(true);

        when(authoritiesService.canBeCreatedBy(patchesDto.getUserRole().get(), authUserRelations.getUserRole()))
                .thenReturn(true);

        authorizationService.authorizeModificationAccess(relationsToAuthorize, patchesDto);
    }

    @Test
    void authorizeModificationAccess_noStorageAccess_exceptionThrown() {
        StorageRelations relationsToAuthorize = new StorageRelations();
        relationsToAuthorize.setUserRole(UserStorageRole.EDITOR);
        relationsToAuthorize.setStorage(new Storage());
        relationsToAuthorize.getStorage().setId(2L);
        relationsToAuthorize.setUser(new User());
        relationsToAuthorize.getUser().setId(3L);

        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        when(relationsDataService.findById(new UserStorageKey(authenticatedUser.getId(), relationsToAuthorize.getStorage().getId())))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorizationService.authorizeModificationAccess(relationsToAuthorize, new StorageRelationsPatchesDto()))
                .isInstanceOf(StorageAccessDeniedException.class);
    }

    @Test
    void authorizeModificationAccess_noModificationAuthority_exceptionThrown() {
        StorageRelations relationsToAuthorize = new StorageRelations();
        relationsToAuthorize.setUserRole(UserStorageRole.ADMIN);
        relationsToAuthorize.setStorage(new Storage());
        relationsToAuthorize.getStorage().setId(2L);
        relationsToAuthorize.setUser(new User());
        relationsToAuthorize.getUser().setId(3L);

        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        StorageRelations authUserRelations = new StorageRelations();
        authUserRelations.setUserRole(UserStorageRole.VIEWER);
        authUserRelations.setStorage(relationsToAuthorize.getStorage());
        authUserRelations.setUser(authenticatedUser);
        authUserRelations.setId(new UserStorageKey(authUserRelations.getUser().getId(), authUserRelations.getStorage().getId()));
        when(relationsDataService.findById(new UserStorageKey(authenticatedUser.getId(), relationsToAuthorize.getStorage().getId())))
                .thenReturn(Optional.of(authUserRelations));

        when(authoritiesService.canBeModifiedBy(relationsToAuthorize.getUserRole(), authUserRelations.getUserRole()))
                .thenReturn(false);

        assertThatThrownBy(() -> authorizationService.authorizeModificationAccess(relationsToAuthorize, new StorageRelationsPatchesDto()))
                .isInstanceOf(StorageRelationsAccessDeniedException.class);
    }

    @Test
    void authorizeDeletionAccessAccess_hasAccess_noExceptionThrown() {
        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        StorageRelations relationsToAuthorize = new StorageRelations();
        relationsToAuthorize.setUserRole(UserStorageRole.VIEWER);
        relationsToAuthorize.setStorage(new Storage());
        relationsToAuthorize.getStorage().setId(2L);
        relationsToAuthorize.setUser(new User());
        relationsToAuthorize.getUser().setId(3L);

        StorageRelations authUserRelations = new StorageRelations();
        authUserRelations.setUserRole(UserStorageRole.ADMIN);
        authUserRelations.setStorage(relationsToAuthorize.getStorage());
        authUserRelations.setUser(authenticatedUser);
        authUserRelations.setId(new UserStorageKey(authUserRelations.getUser().getId(), authUserRelations.getStorage().getId()));
        when(relationsDataService.findById(new UserStorageKey(authenticatedUser.getId(), relationsToAuthorize.getStorage().getId())))
                .thenReturn(Optional.of(authUserRelations));

        when(authoritiesService.canBeModifiedBy(relationsToAuthorize.getUserRole(), authUserRelations.getUserRole()))
                .thenReturn(true);

        authorizationService.authorizeDeletionAccess(relationsToAuthorize);
    }

    @Test
    void authorizeDeletionAccess_selfDeletion_noExceptionThrown() {
        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        StorageRelations relationsToAuthorize = new StorageRelations();
        relationsToAuthorize.setStorage(new Storage());
        relationsToAuthorize.getStorage().setId(2L);
        relationsToAuthorize.setUser(authenticatedUser);

        authorizationService.authorizeDeletionAccess(relationsToAuthorize);
    }

    @Test
    void authorizeDeletionAccess_noStorageAccess_exceptionThrown() {
        StorageRelations relationsToAuthorize = new StorageRelations();
        relationsToAuthorize.setUserRole(UserStorageRole.EDITOR);
        relationsToAuthorize.setStorage(new Storage());
        relationsToAuthorize.getStorage().setId(2L);
        relationsToAuthorize.setUser(new User());
        relationsToAuthorize.getUser().setId(3L);

        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        when(relationsDataService.findById(new UserStorageKey(authenticatedUser.getId(), relationsToAuthorize.getStorage().getId())))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorizationService.authorizeDeletionAccess(relationsToAuthorize))
                .isInstanceOf(StorageAccessDeniedException.class);
    }

    @Test
    void authorizeDeletionAccess_noModificationAuthority_exceptionThrown() {
        StorageRelations relationsToAuthorize = new StorageRelations();
        relationsToAuthorize.setUserRole(UserStorageRole.ADMIN);
        relationsToAuthorize.setStorage(new Storage());
        relationsToAuthorize.getStorage().setId(2L);
        relationsToAuthorize.setUser(new User());
        relationsToAuthorize.getUser().setId(3L);

        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        StorageRelations authUserRelations = new StorageRelations();
        authUserRelations.setUserRole(UserStorageRole.VIEWER);
        authUserRelations.setStorage(relationsToAuthorize.getStorage());
        authUserRelations.setUser(authenticatedUser);
        authUserRelations.setId(new UserStorageKey(authUserRelations.getUser().getId(), authUserRelations.getStorage().getId()));
        when(relationsDataService.findById(new UserStorageKey(authenticatedUser.getId(), relationsToAuthorize.getStorage().getId())))
                .thenReturn(Optional.of(authUserRelations));

        when(authoritiesService.canBeModifiedBy(relationsToAuthorize.getUserRole(), authUserRelations.getUserRole()))
                .thenReturn(false);

        assertThatThrownBy(() -> authorizationService.authorizeDeletionAccess(relationsToAuthorize))
                .isInstanceOf(StorageRelationsAccessDeniedException.class);
    }
}
