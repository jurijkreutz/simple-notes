package com.codecool.simplenotes.config;

import com.codecool.simplenotes.model.User;
import com.codecool.simplenotes.model.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userFromDatabase = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user with provided email found."));
        return UserDetailsImplementation.build(userFromDatabase);
    }

}
