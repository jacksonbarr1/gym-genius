package com.example.gymgenius.repository;

import com.example.gymgenius.entity.GymGeniusUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<GymGeniusUser, Integer> {
    GymGeniusUser findByUsername(String username);
}
