package com.example.producer.service;

import com.example.producer.entity.Enseignant;
import com.example.producer.repository.EnseignantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EnseignantCommandService {

    @Autowired
    private EnseignantRepo enseignantRepo;

    @Autowired
    private KafkaTemplate<String, Enseignant> kafkaTemplate;

    @Value("${topic.enseignant-event}")
    private String enseignantEventTopic;

    public Optional<Enseignant> getEnseignantById(Long id) {
        return enseignantRepo.findById(id);
    }

    public Enseignant saveEnseignant(Enseignant enseignant) {
        Enseignant savedEnseignant = enseignantRepo.save(enseignant);

        // Envoi de l'événement à Kafka via Confluent Control Center
        try {
            kafkaTemplate.send(enseignantEventTopic, savedEnseignant);
        } catch (Exception e) {
            // Gérer l'exception
            e.printStackTrace(); // ou enregistrez le message d'erreur dans les journaux
            // Vous pouvez également lancer une nouvelle exception ou effectuer une autre action en cas d'erreur
        }

        return savedEnseignant;
    }

    public void deleteEnseignant(Long id) {
        enseignantRepo.deleteById(id);
    }
}
