package com.godev.budgetgo.authentication;

import com.godev.budgetgo.data.UsersDataService;
import com.godev.budgetgo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersDataService usersDataService;

    public UserDetailsServiceImpl(UsersDataService usersDataService) {
        this.usersDataService = usersDataService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User entity = usersDataService.getByLogin(s);
        return new UserDetailsImpl(entity);
    }
}
