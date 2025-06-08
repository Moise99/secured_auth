package com.example.sauth.auth;

import com.example.sauth.user.Role;
import com.example.sauth.user.User;
import com.example.sauth.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user.getEmail());
        UserResponse userResponse = new UserResponse(user.getEmail(), user.getRole().name(), user.getFirstname(), user.getLastname());
        return new AuthResponse(token, userResponse);
    }

    public AuthResponse login(LoginRequest request) {
        var auth = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authManager.authenticate(auth);

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        String token = jwtService.generateToken(user.getEmail());
        UserResponse userResponse = new UserResponse(user.getEmail(), user.getRole().name(), user.getFirstname(), user.getLastname());
        return new AuthResponse(token, userResponse);
    }
}