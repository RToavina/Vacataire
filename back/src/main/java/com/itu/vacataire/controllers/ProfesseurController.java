package com.itu.vacataire.controllers;

import com.itu.vacataire.payload.response.ProfesseurDto;
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
    @GetMapping(value = "/professeurs", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProfesseurDto>> all() {
        return new ResponseEntity<>(professeurService.getAllProfesseur().stream().map(p ->
                new ProfesseurDto(p)).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    //Recuperer un professeur selon son id
    @GetMapping(value = "/professeur/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProfesseurDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(new ProfesseurDto(professeurService.getProfesseurById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/professeur", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProfesseurDto> findById(@RequestParam String username) {
        return new ResponseEntity<>(new ProfesseurDto(professeurService.findByUsername(username)), HttpStatus.OK);
    }


        //Supprimer un professeur
    @DeleteMapping(value = "/professeur/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity delete(@PathVariable Long id) {
        professeurService.deleteProfesseurById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
