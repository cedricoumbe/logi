package com.example.demo7.service;


import com.example.demo7.model.Competence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface CompetenceService {

    Set<Competence> getAll();

    List<Competence> getAllCompetence();

    void saveCompetence(Competence competence);

    Competence getCompetenceById(long id);

    void deleteCompetenceById(long id);
}

