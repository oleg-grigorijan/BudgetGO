package com.godev.budgetgo.service.merger.implementations;

import com.godev.budgetgo.dto.UserPatchesDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.service.merger.UsersMerger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class UsersMergerImpl implements UsersMerger {
    private final PasswordEncoder passwordEncoder;

    public UsersMergerImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User merge(UserPatchesDto dto, User eOld) {
        User e = eOld.clone();
        if (dto.getLogin() != null) e.setLogin(dto.getLogin());
        if (dto.getEmail() != null) e.setEmail(dto.getEmail());
        if (dto.getName() != null) e.setName(dto.getName());
        if (dto.getSurname() != null) e.setSurname(dto.getSurname());
        if (dto.getPassword() != null) e.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        if (dto.getEmailPublic() != null) e.setEmailPublic(dto.getEmailPublic());
        return e;
    }
}
