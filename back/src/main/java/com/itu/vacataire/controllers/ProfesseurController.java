package com.itu.vacataire.controllers;

import com.itu.vacataire.model.Professeur;
import com.itu.vacataire.payload.response.ProfesseurInfoResponse;
import com.itu.vacataire.services.Interfaces.IProfesseurService;
import com.itu.vacataire.services.ProfesseurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProfesseurController {

    private IProfesseurService professeurService;

    public ProfesseurController(ProfesseurService professeurService) {
        this.professeurService = professeurService;
    }

    //Recuperer la liste de tout les pofesseurs
    @GetMapping(value = "/professeur", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProfesseurInfoResponse>> all() {
        return new ResponseEntity<>(professeurService.getAllProfesseur().stream().map(p ->
                new ProfesseurInfoResponse(p)).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    //Recuperer un professeur selon son id
    @GetMapping(value = "/professeur/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProfesseurInfoResponse> findById(@PathVariable String id) {
        return new ResponseEntity<>(new ProfesseurInfoResponse(professeurService.getProfesseurById(id)), HttpStatus.OK);
    }

    //Supprimer un professeur
    @DeleteMapping(value = "/professeur/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity delete(@PathVariable String id) {
        professeurService.deleteProfesseurById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
