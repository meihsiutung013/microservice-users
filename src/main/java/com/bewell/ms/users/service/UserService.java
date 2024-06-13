package com.bewell.ms.users.service;

import com.bewell.ms.users.dto.RegisterDto;
import com.bewell.ms.users.entity.User;

public interface UserService {

    User register(RegisterDto input);

    User getUserByEmail(String email);
}
