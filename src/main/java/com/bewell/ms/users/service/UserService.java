package com.bewell.ms.users.service;

import com.bewell.ms.users.dto.RegisterDto;
import com.bewell.ms.users.dto.UserUpdateRequest;
import com.bewell.ms.users.entity.User;

public interface UserService {

    User register(RegisterDto input);

    User getUserById(Integer id);

    User getUserByEmail(String email);

    User updateUser(Integer id, UserUpdateRequest request);

    Boolean verifyCurrentUser(Integer id);

    Boolean deleteUser(Integer id);

    User getCurrentUser();
}
