package com.bewell.ms.users.bootstrap;

import com.bewell.ms.users.entity.Role;
import com.bewell.ms.users.entity.User;
import com.bewell.ms.users.entity.RoleEnum;
import com.bewell.ms.users.repository.RoleRepository;
import com.bewell.ms.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadData();
    }

    private void loadData() {
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.USER, RoleEnum.ADMIN };
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
                RoleEnum.ADMIN, "Administrator role",
                RoleEnum.USER, "Default user role"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);
            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = Role.builder().name(roleName).description(roleDescriptionMap.get(roleName)).build();
                roleRepository.save(roleToCreate);
            });
        });

        Optional<Role> adminRole = roleRepository.findByName(RoleEnum.ADMIN);
        if (adminRole.isPresent()) {
            User user = User.builder().firstName("admin").lastName("admin").email("admin@gmail.com").password(passwordEncoder.encode("12345678")).role(adminRole.orElse(null)).build();
            userRepository.save(user);
        }
    }
}