package com.godev.budgetgo.request;

import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.converter.UserCategoriesConverter;
import com.godev.budgetgo.data.UserCategoriesDataService;
import com.godev.budgetgo.dto.UserCategoryCreationDto;
import com.godev.budgetgo.dto.UserCategoryPatchesDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategory;
import com.godev.budgetgo.entity.UserCategoryKey;
import com.godev.budgetgo.request.impl.UserCategoriesRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserCategoriesRequestServiceTest {

    private UserCategoriesRequestService requestService;

    @Mock
    private UserCategoriesDataService dataService;

    @Mock
    private UserCategoriesConverter converter;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @BeforeEach
    void setUp() {
        requestService = new UserCategoriesRequestServiceImpl(dataService, converter, authenticationFacade);
    }

    @Test
    void getAll_general_dataServiceGetByAuthenticatedUserCall() {
        User authenticatedUser = new User();
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        requestService.getAll();
        verify(dataService).getByUser(refEq(authenticatedUser));
    }

    @Test
    void getByCategoryId_general_dataServiceGetByIdForAuthenticatedUserCall() {
        User authenticatedUser = new User();
        long authenticatedUserId = 1L;
        authenticatedUser.setId(authenticatedUserId);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        requestService.getByCategoryId(2L);
        verify(dataService).getById(argThat(id -> id.getUserId().equals(authenticatedUserId)));
    }

    @Test
    void create_general_dataServiceAddCall() {
        UserCategory userCategory = new UserCategory();
        when(converter.convertFromDto(any(UserCategoryCreationDto.class))).thenReturn(userCategory);

        requestService.create(new UserCategoryCreationDto());
        verify(dataService).add(refEq(userCategory));
    }

    @Test
    void patchByCategoryId_general_dataServiceGetByIdForAuthenticatedUserCall() {
        User authenticatedUser = new User();
        long authenticatedUserId = 1L;
        authenticatedUser.setId(authenticatedUserId);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        requestService.patchByCategoryId(2L, new UserCategoryPatchesDto());
        verify(dataService).getById(argThat(id -> id.getUserId().equals(authenticatedUserId)));
    }

    @Test
    void patchByCategoryId_general_dataServiceUpdateCall() {
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(new User());
        when(dataService.getById(any(UserCategoryKey.class))).thenReturn(new UserCategory());
        UserCategory patchedUserCategory = new UserCategory();
        when(converter.merge(any(UserCategory.class), any(UserCategoryPatchesDto.class))).thenReturn(patchedUserCategory);

        requestService.patchByCategoryId(2L, new UserCategoryPatchesDto());
        verify(dataService).update(refEq(patchedUserCategory));
    }

    @Test
    void deleteByCategoryId_general_dataServiceGetByIdForAuthenticatedUserCall() {
        User authenticatedUser = new User();
        long authenticatedUserId = 1L;
        authenticatedUser.setId(authenticatedUserId);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        requestService.deleteByCategoryId(2L);
        verify(dataService).getById(argThat(id -> id.getUserId().equals(authenticatedUserId)));
    }

    @Test
    void deleteByCategoryId_general_dataServiceDeleteCall() {
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(new User());
        UserCategory userCategory = new UserCategory();
        when(dataService.getById(any(UserCategoryKey.class))).thenReturn(userCategory);

        requestService.deleteByCategoryId(1L);
        verify(dataService).delete(refEq(userCategory));
    }
}
