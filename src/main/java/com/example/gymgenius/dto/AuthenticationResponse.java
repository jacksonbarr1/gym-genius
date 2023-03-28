package com.example.gymgenius.dto;

import com.example.gymgenius.entity.GymGeniusUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private GymGeniusUser user;
    private String token;
}
