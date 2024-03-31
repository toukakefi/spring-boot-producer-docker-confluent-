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



    @GetMapping("/{id}")
    public ResponseEntity<Enseignant> getEnseignantById(@PathVariable Long id) {
        Optional<Enseignant> enseignant = enseignantCommandService.getEnseignantById(id);
        return enseignant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Enseignant> createEnseignant(@RequestBody Enseignant enseignant) {
        Enseignant createdEnseignant = enseignantCommandService.saveEnseignant(enseignant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEnseignant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enseignant> updateEnseignant(@PathVariable Long id, @RequestBody Enseignant enseignant) {
        if (!enseignantCommandService.getEnseignantById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        enseignant.setId(id);
        Enseignant updatedEnseignant = enseignantCommandService.saveEnseignant(enseignant);
        return ResponseEntity.ok(updatedEnseignant);
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