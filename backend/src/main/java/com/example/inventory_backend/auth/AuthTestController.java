package com.example.inventory_backend.auth;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class AuthTestController {

    @GetMapping("/me")
    public String me() {
        return "You are authenticated.";
    }
}
