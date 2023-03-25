package com.example.gymgenius.service;

import com.example.gymgenius.entity.Workout;
import com.example.gymgenius.repository.ExerciseRepository;
import com.example.gymgenius.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }
}
