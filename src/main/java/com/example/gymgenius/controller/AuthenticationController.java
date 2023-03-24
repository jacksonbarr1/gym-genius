package com.example.gymgenius.controller;

import com.example.gymgenius.dto.AuthenticationRequest;
import com.example.gymgenius.entity.GymGeniusUser;
import com.example.gymgenius.security.JWTUtils;
import com.example.gymgenius.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    private final JWTUtils jwtUtils;

    @ResponseBody
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));
        final GymGeniusUser user = (GymGeniusUser) userDetailsService.loadUserByUsername(request.getEmail());
        if (user != null) {
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("Authentication error");
    }

    @ResponseBody
    @PostMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("GODO");
    }
}
