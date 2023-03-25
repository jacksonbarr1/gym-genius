package com.example.gymgenius.controller;

import com.example.gymgenius.dto.AuthenticationRequest;
import com.example.gymgenius.dto.AuthenticationResponse;
import com.example.gymgenius.dto.RegistrationRequest;
import com.example.gymgenius.dto.UserDTO;
import com.example.gymgenius.entity.GymGeniusUser;
import com.example.gymgenius.security.JWTUtils;
import com.example.gymgenius.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<GymGeniusUser> register(@RequestBody RegistrationRequest request) {
        UserDTO userDTO = UserDTO.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        return ResponseEntity.ok(userService.register(userDTO));
    }

    @ResponseBody
    @PreAuthorize("#id == authentication.principal.id")
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<GymGeniusUser> getUserById(@PathVariable Integer id) throws Exception {
        if (!userService.idExists(id)) {
            throw new Exception("Existing user not found with id: " + id);
        }
        return ResponseEntity.ok(userService.findById(id));
    }

    @ResponseBody
    @PreAuthorize("#id == authentication.principal.id")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<GymGeniusUser> updateUserById(@PathVariable Integer id, @RequestBody UserDTO userDTO) throws Exception {
        if (!userService.idExists(id)) {
            throw new Exception("Existing user not found with id: " + id);
        }
        return ResponseEntity.ok(userService.updateById(id, userDTO));
    }

    @ResponseBody
    @PreAuthorize("#id == authentication.principal.id")
    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) throws Exception {
        if (!userService.idExists(id)) {
            throw new Exception("Existing user not found with id: " + id);
        }
        userService.deleteById(id);
        return ResponseEntity.ok("User deleted with id: " + id);
    }

    @ResponseBody
    @PostMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("GODO");
    }

}
