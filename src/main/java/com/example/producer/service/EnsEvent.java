package com.example.producer.service;

import com.example.producer.entity.Enseignant;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnsEvent {
    private String eventType;
    private Enseignant enseignant;


}
