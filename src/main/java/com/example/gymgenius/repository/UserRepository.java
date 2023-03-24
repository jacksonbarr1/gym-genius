package com.example.gymgenius.repository;

import com.example.gymgenius.entity.GymGeniusUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<GymGeniusUser, Integer> {
    GymGeniusUser findGymGeniusUserByEmail(String email);
}
