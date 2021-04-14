package com.example.demo7.service;

import com.example.demo7.model.Competence;
import com.example.demo7.repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompetenceServiceImpl implements CompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;


    @Override
    public Set<Competence> getAll() {
        Set<Competence> competences = new HashSet<>();
        competenceRepository.findAll().iterator().forEachRemaining(competences::add);
        return competences;
    }

    @Override
    public List<Competence> getAllCompetence() {

        return competenceRepository.findAll();
    }

    @Override
    public void saveCompetence(Competence competences) {

        this.competenceRepository.save(competences);

    }

    public Competence getCompetenceById(long id){

        Optional<Competence> optional = competenceRepository.findById(id);
        Competence competences = null;
        if(optional.isPresent()){
            competences = optional.get();
        }else{
            throw new RuntimeException("Competence not found for id ::"+ id);
        }
        return competences;
    }

    public void deleteCompetenceById(long id){

        this.competenceRepository.deleteById(id);


    }
}