package com.example.inventory_backend.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_username", columnNames = "username"),
                @UniqueConstraint(name = "uk_users_email", columnNames = "email")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String username;

    @Column(nullable = false, length = 120)
    private String email;

    // Store ONLY bcrypt hash
    @Column(nullable = false, length = 200)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void onCreate() {
        if (createdAt == null) createdAt = Instant.now();
        if (role == null) role = Role.USER;
    }
}
