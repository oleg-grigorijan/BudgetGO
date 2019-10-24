package com.godev.budgetgo.service.data;

import com.godev.budgetgo.entity.User;

public interface UsersDataService extends DataService<User, Long> {
    User getByEmail(String email);

    User getByLogin(String login);
}
