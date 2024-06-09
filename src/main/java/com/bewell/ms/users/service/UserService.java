package com.bewell.ms.users.service;

import com.bewell.ms.users.dto.RegisterDto;
import com.bewell.ms.users.entity.User;

import java.util.List;

public interface UserService {

    List<User> allUsers();

    User signup(RegisterDto input);
}
