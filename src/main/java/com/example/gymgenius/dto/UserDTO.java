package com.example.gymgenius.dto;

import com.example.gymgenius.entity.Workout;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private List<Workout> workouts;
}
