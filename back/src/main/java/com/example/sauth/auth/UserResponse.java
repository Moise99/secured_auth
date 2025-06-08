package com.example.sauth.auth;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserResponse {

    private String email;
    private String name;
    private String firstname;
    private String lastname;

}
