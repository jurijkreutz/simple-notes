package com.codecool.simplenotes.endpoint;

import com.codecool.simplenotes.config.jwt.JwtUtils;
import com.codecool.simplenotes.config.request.RegisterRequest;
import com.codecool.simplenotes.config.response.MessageResponse;
import com.codecool.simplenotes.config.response.UserInfoResponse;
import com.codecool.simplenotes.config.request.AuthenticationRequest;
import com.codecool.simplenotes.model.ERole;
import com.codecool.simplenotes.model.Role;
import com.codecool.simplenotes.model.User;
import com.codecool.simplenotes.model.repository.RoleRepository;
import com.codecool.simplenotes.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationEndpoint {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println(request);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                                            request.getPassword()));
        final UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        if (user != null) {
            List<String> roles = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            return ResponseEntity.ok().body(new UserInfoResponse(
                                            jwtUtils.generateToken(user),
                                            user.getUsername(),
                                            roles)
            );
        }
        return ResponseEntity.status(400).body("An error has occured.");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
