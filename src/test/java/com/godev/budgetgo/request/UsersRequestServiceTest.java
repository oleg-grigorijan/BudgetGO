package com.godev.budgetgo.request;

import com.godev.budgetgo.converter.UsersConverter;
import com.godev.budgetgo.data.UsersDataService;
import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.request.impl.UsersRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersRequestServiceTest {

    private UsersRequestService requestService;

    @Mock
    private UsersDataService dataService;

    @Mock
    private UsersConverter converter;

    @BeforeEach
    void setUp() {
        requestService = new UsersRequestServiceImpl(dataService, converter);
    }

    @Test
    void create_general_dataServiceAddCall() {
        User user = new User();
        when(converter.convertToEntity(any(UserCreationDto.class))).thenReturn(user);

        requestService.create(new UserCreationDto());
        verify(dataService).add(refEq(user));
    }
}
