package com.codecool.simplenotes.service;

import com.codecool.simplenotes.model.ERole;
import com.codecool.simplenotes.model.Role;
import com.codecool.simplenotes.model.User;
import com.codecool.simplenotes.model.repository.RoleRepository;
import com.codecool.simplenotes.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class InitService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public void createUserEntries() {
        Role userRole = new Role(1, ERole.ROLE_USER);
        Role adminRole = new Role(2, ERole.ROLE_ADMIN);
        User user = new User("user@umbrella.cool", passwordEncoder.encode("user"));
        User admin = new User("admin@umbrella.cool", passwordEncoder.encode("admin"));

        user.setRoles(Set.of(userRole));
        admin.setRoles(Set.of(adminRole));

        roleRepository.save(userRole);
        roleRepository.save(adminRole);
        userRepo.save(user);
        userRepo.save(admin);
    }

}
