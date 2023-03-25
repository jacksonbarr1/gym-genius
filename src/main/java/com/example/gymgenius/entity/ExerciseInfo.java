package com.example.gymgenius.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class ExerciseInfo {
    @Enumerated(EnumType.STRING)
    private ExerciseType exerciseType;


}
