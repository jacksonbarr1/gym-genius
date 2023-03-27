package com.example.gymgenius.controller;

import com.example.gymgenius.entity.GymGeniusUser;
import com.example.gymgenius.entity.Workout;
import com.example.gymgenius.service.UserService;
import com.example.gymgenius.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout")
@CrossOrigin
@RequiredArgsConstructor
public class WorkoutController {
    private final WorkoutService workoutService;
    private final UserService userService;

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout, Authentication authentication) throws Exception {
        Workout newWorkout = workoutService.createWorkout(workout);
        userService.addWorkoutById(userService.getIdFromAuthentication(authentication), newWorkout);
        return ResponseEntity.ok(newWorkout);
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<Workout>> getUserWorkouts(Authentication authentication) {
        int id = userService.getIdFromAuthentication(authentication);
        GymGeniusUser user = userService.findById(id);
        List<Workout> workouts = user.getWorkouts();
        return ResponseEntity.ok(workouts);
    }

}
