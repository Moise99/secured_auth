package com.example.sauth.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private UserResponse userResponse;
    private String token;

    public AuthResponse(String token, UserResponse user) {
    this.token = token;
    this.userResponse = user;
}
    
}

