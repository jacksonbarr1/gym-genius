package com.example.gymgenius.controller;

import com.example.gymgenius.dto.UserDTO;
import com.example.gymgenius.entity.GymGeniusUser;
import com.example.gymgenius.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @ResponseBody
    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Hello GymGenius");
    }





}
