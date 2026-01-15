package com.example.inventory_backend.auth;

public record AuthResponse (
    String token,
    String username,
    String role
) {}
