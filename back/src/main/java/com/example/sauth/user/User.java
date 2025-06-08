package com.example.sauth.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    @Column(name = "email", length = 100)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
