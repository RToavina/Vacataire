package com.itu.vacataire.services;

import com.itu.vacataire.model.Emargement;
import com.itu.vacataire.repositories.EmargementRepository;
import com.itu.vacataire.services.Interfaces.IEmargementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EmargementService implements IEmargementService {

    @Autowired
    EmargementRepository emargementRepository;

    @Override
    public List<Emargement> findAllByMonth(Date date) {
        return null;
    }

    @Override
    public List<Emargement> findAll() {
        return emargementRepository.findAllByDoneIsTrue();
    }


}
