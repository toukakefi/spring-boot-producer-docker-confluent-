package com.example.producer.repository;


import com.example.producer.entity.Enseignant;


import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepo extends JpaRepository<Enseignant,Long> {

}
