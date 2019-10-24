package com.godev.budgetgo.service.merger.implementations;

import com.godev.budgetgo.dto.UserPatchesDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.service.merger.UsersMerger;
import org.springframework.stereotype.Service;

@Service
class UsersMergerImpl implements UsersMerger {
    @Override
    public void merge(UserPatchesDto dto, User e) {
        if (dto.getLogin() != null) e.setLogin(dto.getLogin());
        if (dto.getEmail() != null) e.setEmail(dto.getEmail());
        if (dto.getName() != null) e.setName(dto.getName());
        if (dto.getPassword() != null) e.setSurname(dto.getPassword());
        if (dto.getEmailPublic() != null) e.setEmailPublic(dto.getEmailPublic());
    }
}
