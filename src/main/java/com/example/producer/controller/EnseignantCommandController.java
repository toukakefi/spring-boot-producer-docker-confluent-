package com.example.producer.controller;

import com.example.producer.entity.Enseignant;
import com.example.producer.service.EnseignantCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enseignants")
public class EnseignantCommandController {

    @Autowired
    private EnseignantCommandService enseignantCommandService;





    @PostMapping
    public ResponseEntity<Enseignant> createEnseignant(@RequestBody Enseignant enseignant) {
        Enseignant createdEnseignant = enseignantCommandService.saveEnseignant(enseignant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEnseignant);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnseignant(@PathVariable Long id) {
        if (!enseignantCommandService.getEnseignantById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        enseignantCommandService.deleteEnseignant(id);
        return ResponseEntity.noContent().build();
    }
}