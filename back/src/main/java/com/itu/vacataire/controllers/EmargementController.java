package com.itu.vacataire.controllers;

import com.itu.vacataire.model.Emargement;
import com.itu.vacataire.payload.request.EmargementRequest;
import com.itu.vacataire.services.EmargementService;
import com.itu.vacataire.services.Interfaces.IEmargementService;
import com.itu.vacataire.utils.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmargementController {

    private IEmargementService emargementService;

    public EmargementController(EmargementService emargementService) {
        this.emargementService = emargementService;
    }

    @GetMapping("/emargement")
    ResponseEntity<List<Emargement>> getEmargementByProfesseurUsername(@RequestParam String username) {
        return new ResponseEntity<>(this.emargementService.findAllByProfesseur(username), HttpStatus.OK);
    }

    @GetMapping("/emargement/all")
    ResponseEntity<List<Emargement>> getAll() {
        return new ResponseEntity<>(this.emargementService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/emargement")
    ResponseEntity<Emargement> saveEmargement(@RequestBody EmargementRequest emargement) throws HttpException {
        return new ResponseEntity<>(this.emargementService.saveEmargement(emargement), HttpStatus.OK);
    }

}
