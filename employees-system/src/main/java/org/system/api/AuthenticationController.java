package org.system.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @GetMapping("/auth")
    public String authenticate(Authentication authentication) {
        String username = authentication.getName();
        String roles = authentication.getAuthorities().toString();
        return roles;
    }
}