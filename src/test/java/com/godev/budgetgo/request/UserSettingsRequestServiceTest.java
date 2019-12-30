package com.godev.budgetgo.request;

import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.converter.UserSettingsConverter;
import com.godev.budgetgo.data.UsersDataService;
import com.godev.budgetgo.dto.UserSettingsInfoDto;
import com.godev.budgetgo.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.request.impl.UserSettingsRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserSettingsRequestServiceTest {

    private UserSettingsRequestService requestService;

    @Mock
    private UsersDataService dataService;

    @Mock
    private UserSettingsConverter converter;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @BeforeEach
    void setUp() {
        requestService = new UserSettingsRequestServiceImpl(dataService, converter, authenticationFacade);
    }

    @Test
    void get_general_dtoForAuthenticatedUser() {
        User authenticatedUser = new User();
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);
        UserSettingsInfoDto dto = new UserSettingsInfoDto();
        when(converter.convertToDto(refEq(authenticatedUser))).thenReturn(dto);

        assertThat(requestService.get()).isSameAs(dto);
    }

    @Test
    void patch_general_dataServiceUpdateCall() {
        User authenticatedUser = new User();
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);
        User patchedUser = new User();
        when(converter.merge(refEq(authenticatedUser), any(UserSettingsPatchesDto.class))).thenReturn(patchedUser);

        requestService.patch(new UserSettingsPatchesDto());
        verify(dataService).update(refEq(patchedUser));
    }
}
