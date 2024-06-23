package com.bewell.ms.users.service;

import com.bewell.ms.users.dto.RegisterDto;
import com.bewell.ms.users.dto.UserUpdateRequest;
import com.bewell.ms.users.entity.Role;
import com.bewell.ms.users.entity.RoleEnum;
import com.bewell.ms.users.entity.User;
import com.bewell.ms.users.repository.RoleRepository;
import com.bewell.ms.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
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
                .gender(input.getGender())
                .role(userRole.get())
                .build();

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setBirthDate(request.getBirthDate());
            user.setGender(request.getGender());
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public Boolean verifyCurrentUser(Integer userId) {
        if (userId != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                User user = getUserByEmail(userDetails.getUsername());
                if (user != null) {
                    return Objects.equals(user.getId(), userId) || user.getRole().getName().equals(RoleEnum.ADMIN);
                }
            }
        }
        return null;
    }

}
