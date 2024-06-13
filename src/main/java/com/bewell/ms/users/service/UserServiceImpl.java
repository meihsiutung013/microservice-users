package com.bewell.ms.users.service;

import com.bewell.ms.users.dto.RegisterDto;
import com.bewell.ms.users.entity.Role;
import com.bewell.ms.users.entity.RoleEnum;
import com.bewell.ms.users.entity.User;
import com.bewell.ms.users.repository.RoleRepository;
import com.bewell.ms.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User register(RegisterDto input) {

        Optional<Role> userRole = roleRepository.findByName(RoleEnum.USER);

        if (userRole.isEmpty()) {
            return null;
        }

        User user = User.builder()
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .birthDate(input.getBirthDate())
                .gender(input.getGender())
                .role(userRole.get())
                .build();

        return userRepository.save(user);
    }
}
