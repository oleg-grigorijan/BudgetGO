package com.godev.budgetgo.business.storage;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.business.storage.impl.StorageRelationsFactoryImpl;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.UserStorageRole;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class StorageRelationsFactoryTest {

    private StorageRelationsFactory factory;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @BeforeEach
    void setUp() {
        factory = new StorageRelationsFactoryImpl(authenticationFacade);
    }

    @Test
    void createCreatorEntityForStorage_general_correctPropertiesInCreatedRelations() {
        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);
        Storage storage = new Storage();
        storage.setId(2L);

        assertThat(factory.createCreatorEntityForStorage(storage)).matches(relations -> relations.getStorage() == storage
                && relations.getUser() == authenticatedUser
                && relations.getId().getUserId().equals(authenticatedUser.getId())
                && relations.getId().getStorageId().equals(storage.getId())
                && relations.getUserRole() == UserStorageRole.CREATOR
                && relations.isIncludedInUserStatistics()
                && relations.getInviter() == authenticatedUser
                && !relations.isInvitation()
        );
    }

    @Test
    void createCreatorEntityForStorage_general_noUnspecifiedProperties() {
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(new User());

        assertThat(factory.createCreatorEntityForStorage(new Storage())).hasNoNullFieldsOrProperties();
    }
}
