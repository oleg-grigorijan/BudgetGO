package com.godev.budgetgo.infra.authorization;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.domain.storage.UserStorageRole;
import com.godev.budgetgo.infra.authorization.impl.UserStorageRoleAuthoritiesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@UnitTest
class UserStorageRoleAuthoritiesServiceTest {

    private UserStorageRoleAuthoritiesService authoritiesService;

    @BeforeEach
    void setUp() {
        authoritiesService = new UserStorageRoleAuthoritiesServiceImpl();
    }

    private static Stream<Arguments> params_canModifyStorage_hasAuthority_true() {
        return Stream.of(
                arguments(UserStorageRole.CREATOR),
                arguments(UserStorageRole.ADMIN),
                arguments(UserStorageRole.EDITOR)
        );
    }

    @ParameterizedTest
    @MethodSource("params_canModifyStorage_hasAuthority_true")
    void canModifyStorage_hasAuthority_true(UserStorageRole role) {
        assertThat(authoritiesService.canModifyStorage(role)).isTrue();
    }

    @Test
    void canModifyStorage_noAuthority_false() {
        assertThat(authoritiesService.canModifyStorage(UserStorageRole.VIEWER)).isFalse();
    }

    private static Stream<Arguments> params_canBeCreatedAndModifiedBy_hasAuthority_true() {
        return Stream.of(
                arguments(UserStorageRole.ADMIN, UserStorageRole.CREATOR),
                arguments(UserStorageRole.EDITOR, UserStorageRole.CREATOR),
                arguments(UserStorageRole.VIEWER, UserStorageRole.CREATOR),
                arguments(UserStorageRole.ADMIN, UserStorageRole.ADMIN),
                arguments(UserStorageRole.EDITOR, UserStorageRole.ADMIN),
                arguments(UserStorageRole.VIEWER, UserStorageRole.ADMIN)
        );
    }

    @ParameterizedTest
    @MethodSource("params_canBeCreatedAndModifiedBy_hasAuthority_true")
    void canBeCreatedBy_hasAuthority_true(UserStorageRole who, UserStorageRole by) {
        assertThat(authoritiesService.canBeCreatedBy(who, by)).isTrue();
    }

    private static Stream<Arguments> params_canBeCreatedAndModifiedBy_noAuthority_false() {
        return Stream.of(
                arguments(UserStorageRole.CREATOR, UserStorageRole.CREATOR),
                arguments(UserStorageRole.CREATOR, UserStorageRole.ADMIN),
                arguments(UserStorageRole.CREATOR, UserStorageRole.EDITOR),
                arguments(UserStorageRole.ADMIN, UserStorageRole.EDITOR),
                arguments(UserStorageRole.EDITOR, UserStorageRole.EDITOR),
                arguments(UserStorageRole.VIEWER, UserStorageRole.EDITOR),
                arguments(UserStorageRole.CREATOR, UserStorageRole.VIEWER),
                arguments(UserStorageRole.ADMIN, UserStorageRole.VIEWER),
                arguments(UserStorageRole.EDITOR, UserStorageRole.VIEWER),
                arguments(UserStorageRole.VIEWER, UserStorageRole.VIEWER)
        );
    }

    @ParameterizedTest
    @MethodSource("params_canBeCreatedAndModifiedBy_noAuthority_false")
    void canBeCreatedBy_noAuthority_false(UserStorageRole who, UserStorageRole by) {
        assertThat(authoritiesService.canBeCreatedBy(who, by)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("params_canBeCreatedAndModifiedBy_hasAuthority_true")
    void canBeModifiedBy_hasAuthority_true(UserStorageRole who, UserStorageRole by) {
        assertThat(authoritiesService.canBeModifiedBy(who, by)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("params_canBeCreatedAndModifiedBy_noAuthority_false")
    void canBeModifiedBy_noAuthority_false(UserStorageRole who, UserStorageRole by) {
        assertThat(authoritiesService.canBeModifiedBy(who, by)).isFalse();
    }
}
