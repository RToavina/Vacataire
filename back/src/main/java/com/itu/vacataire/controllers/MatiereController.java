package com.itu.vacataire.controllers;

import com.itu.vacataire.model.Matiere;
import com.itu.vacataire.services.Interfaces.IMatiereService;
import com.itu.vacataire.services.MatiereService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MatiereController {

    private IMatiereService matiereService;

    public MatiereController(MatiereService matiereService) {this.matiereService = matiereService;}

    @GetMapping(value = "/matiere/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Matiere> findMatiereById(@PathVariable Long id){
        return new ResponseEntity<>(matiereService.getMatierebyId(id),HttpStatus.OK);
    }

    @PostMapping(value = "/matiere", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Matiere> post(@RequestBody Matiere matiere){
        return new ResponseEntity<>(matiereService.addMatiere(matiere), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/matiere/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity deleteById(@PathVariable Long id){
        try {
            matiereService.deleteMatiere(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
