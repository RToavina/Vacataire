package com.itu.vacataire.controllers;

import com.itu.vacataire.model.Professeur;
import com.itu.vacataire.services.Interfaces.IProfesseurService;
import com.itu.vacataire.services.ProfesseurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProfesseurController {

    private IProfesseurService professeurService;

    public ProfesseurController(ProfesseurService professeurService) {
        this.professeurService = professeurService;
    }

    //Recuperer la liste de tout les pofesseurs
    @GetMapping(value = "/professeur",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Professeur>> all(){
        return new ResponseEntity<>(professeurService.getAllProfesseur(), HttpStatus.OK);
    }

    //Recuperer un professeur selon son id
    @GetMapping(value = "/professeur/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Professeur> findById(@PathVariable String id){
        return new ResponseEntity<>(professeurService.getProfesseurById(id),HttpStatus.FOUND);
    }

    //Enregistrer un professeur
    @PostMapping(value = "/professeur",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Professeur> post(@RequestBody Professeur professeur){
        return new ResponseEntity<>(professeurService.addProfesseur(professeur), HttpStatus.CREATED);
    }

    //Supprimer un professeur
    @DeleteMapping(value="/professeur/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity delete(@PathVariable String id){
        professeurService.deleteProfesseurById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }






}
