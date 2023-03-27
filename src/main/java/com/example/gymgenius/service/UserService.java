package com.example.gymgenius.service;

import com.example.gymgenius.dto.UserDTO;
import com.example.gymgenius.entity.GymGeniusUser;
import com.example.gymgenius.entity.Workout;
import com.example.gymgenius.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public GymGeniusUser register(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        GymGeniusUser user = GymGeniusUser.gymGeniusUserFactory(userDTO);
        userRepository.save(user);

        logger.info("New user registration: " + user);
        return user;
    }

    public GymGeniusUser updateById(Integer id, UserDTO userDTO) throws Exception {
        GymGeniusUser oldUser = userRepository.findById(id).orElseThrow(() -> new Exception("Resource not found"));
        logger.info("User updating in progress: " + oldUser);
        if (userDTO.getEmail() != null && !Objects.equals(userDTO.getEmail(), oldUser.getEmail())) {
            oldUser.setEmail(userDTO.getEmail());
        }

        if (userDTO.getFirstName() != null && !Objects.equals(userDTO.getFirstName(), oldUser.getFirstName())) {
            oldUser.setFirstName(userDTO.getFirstName());
        }

        if (userDTO.getLastName() != null && !Objects.equals(userDTO.getLastName(), oldUser.getLastName())) {
            oldUser.setLastName(userDTO.getLastName());
        }
        logger.info("User updated: " + oldUser);

        return userRepository.save(oldUser);
    }

    public GymGeniusUser addWorkoutById(Integer id, Workout workout) throws Exception {
        GymGeniusUser oldUser = userRepository.findById(id).orElseThrow(() -> new Exception("Resource not found"));
        oldUser.getWorkouts().add(workout);
        userRepository.save(oldUser);
        logger.info("Workout " + workout.getName() + " added to user: " + oldUser);
        return oldUser;
    }


    public GymGeniusUser findById(Integer id) {
        logger.info("Searching for user with id: " + id);
        GymGeniusUser result = userRepository.findById(id).orElse(null);
        if (result == null) {
            logger.debug("No user with id: " + id);
        } else {
            logger.info("User found: " + result);
        }

        return result;
    }

    public boolean idExists(Integer id) {
        return findById(id) != null;
    }

    public void deleteById(Integer id) {
        logger.info("Deleting user with id: " + id);
        if (findById(id) != null) {
            userRepository.deleteById(id);
            logger.info("Deleted user with id: " + id);
        } else {
            logger.debug("Could not delete user");
        }

    }

    public int getIdFromAuthentication(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        GymGeniusUser user = userRepository.findGymGeniusUserByEmail(userDetails.getUsername());
        return user.getId();
    }
}
