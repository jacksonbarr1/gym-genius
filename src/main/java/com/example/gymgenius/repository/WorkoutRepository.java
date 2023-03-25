package com.example.gymgenius.repository;

import com.example.gymgenius.entity.Workout;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Integer> {
}
