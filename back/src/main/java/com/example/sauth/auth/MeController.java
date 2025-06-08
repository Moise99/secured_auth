package com.example.sauth.auth;

import com.example.sauth.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class MeController {

    @GetMapping("/me")
    public UserResponse getCurrentUser(@AuthenticationPrincipal User user) {
        return new UserResponse(user.getEmail(), user.getRole().name(), user.getFirstname(), user.getLastname());
    }
}