package com.example.producer.entity;




import jakarta.persistence.*;
import lombok.*;



@Data
@Entity
@Table(name="Enseignant_command")
@AllArgsConstructor
@NoArgsConstructor
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;
}
