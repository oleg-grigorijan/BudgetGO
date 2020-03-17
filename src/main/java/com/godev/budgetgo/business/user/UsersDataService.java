package com.godev.budgetgo.business.user;

import com.godev.budgetgo.business.base.DataService;
import com.godev.budgetgo.domain.user.User;

public interface UsersDataService extends DataService<User, Long> {

    User getByEmail(String email);

    User getByLogin(String login);
}
