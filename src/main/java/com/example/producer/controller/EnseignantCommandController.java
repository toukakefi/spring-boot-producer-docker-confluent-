package com.example.producer.controller;


import com.example.producer.entity.Enseignant;
import com.example.producer.service.EnsEvent;
import com.example.producer.service.EnseignantCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/enseignants")
public class EnseignantCommandController {

    @Autowired
    private EnseignantCommandService commandService;

    @PostMapping
    public Enseignant createEns(@RequestBody EnsEvent ens) {
        return commandService.createEns(ens);
    }

    @PutMapping("/{id}")
    public Enseignant updateEns(@PathVariable Long id, @RequestBody EnsEvent ens) {
        return commandService.updateEns(id, ens);
    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        commandService.delete(id);
//    }
}
