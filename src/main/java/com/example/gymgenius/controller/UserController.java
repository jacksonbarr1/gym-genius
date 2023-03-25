package com.example.gymgenius.controller;

import com.example.gymgenius.dto.AuthenticationRequest;
import com.example.gymgenius.dto.AuthenticationResponse;
import com.example.gymgenius.dto.UserDTO;
import com.example.gymgenius.entity.GymGeniusUser;
import com.example.gymgenius.security.JWTUtils;
import com.example.gymgenius.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final JWTUtils jwtUtils;

    @ResponseBody
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));
        final GymGeniusUser user = (GymGeniusUser) userDetailsService.loadUserByUsername(request.getEmail());
        if (user != null) {
            return ResponseEntity.ok(new AuthenticationResponse(jwtUtils.generateToken(user)));
        }
        return ResponseEntity.status(400).body(new AuthenticationResponse());
    }

    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        UserDTO userDTO = new UserDTO(request.getEmail(), request.getPassword());
        GymGeniusUser user = userService.register(userDTO);
        return authenticate(request);
    }

    @ResponseBody
    @PostMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("GODO");
    }
}
