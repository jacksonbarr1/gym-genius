package com.example.gymgenius.service;

import com.example.gymgenius.dto.UserDTO;
import com.example.gymgenius.entity.GymGeniusUser;
import com.example.gymgenius.entity.Workout;
import com.example.gymgenius.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public GymGeniusUser register(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        GymGeniusUser user = GymGeniusUser.gymGeniusUserFactory(userDTO);
        return userRepository.save(user);
    }

    public GymGeniusUser updateById(Integer id, UserDTO userDTO) throws Exception {
        GymGeniusUser oldUser = userRepository.findById(id).orElseThrow(() -> new Exception("Resource not found"));
        if (userDTO.getEmail() != null && !Objects.equals(userDTO.getEmail(), oldUser.getEmail())) {
            oldUser.setEmail(userDTO.getEmail());
        }

        if (userDTO.getFirstName() != null && !Objects.equals(userDTO.getFirstName(), oldUser.getFirstName())) {
            oldUser.setFirstName(userDTO.getFirstName());
        }

        if (userDTO.getLastName() != null && !Objects.equals(userDTO.getLastName(), oldUser.getLastName())) {
            oldUser.setLastName(userDTO.getLastName());
        }

        return userRepository.save(oldUser);
    }

    public GymGeniusUser addWorkoutById(Integer id, Workout workout) throws Exception {
        GymGeniusUser oldUser = userRepository.findById(id).orElseThrow(() -> new Exception("Resource not found"));
        oldUser.getWorkouts().add(workout);
        return userRepository.save(oldUser);
    }


    public GymGeniusUser findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean idExists(Integer id) {
        return findById(id) != null;
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public int getIdFromAuthentication(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        GymGeniusUser user = userRepository.findGymGeniusUserByEmail(userDetails.getUsername());
        return user.getId();
    }
}
