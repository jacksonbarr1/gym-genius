package com.example.gymgenius.controller;

import com.example.gymgenius.entity.Workout;
import com.example.gymgenius.service.UserService;
import com.example.gymgenius.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class WorkoutController {
    private final WorkoutService workoutService;
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout, Authentication authentication) throws Exception {
        Workout newWorkout = workoutService.createWorkout(workout);
        userService.addWorkoutById(userService.getIdFromAuthentication(authentication), newWorkout);
        return ResponseEntity.ok(newWorkout);
    }

}
