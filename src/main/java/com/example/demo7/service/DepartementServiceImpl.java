package com.example.demo7.service;

import com.example.demo7.model.Departement;
import com.example.demo7.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementServiceImpl implements DepartementService {

    @Autowired
    private DepartementRepository departementRepository;


    @Override
    public List<Departement> getAllDepartement() {

        return departementRepository.findAll();
    }

    @Override
    public void saveDepartement(Departement departements) {

        this.departementRepository.save(departements);

    }

    public Departement getDepartementById(long id){

        Optional<Departement> optional = departementRepository.findById(id);
        Departement departements = null;
        if(optional.isPresent()){
            departements = optional.get();
        }else{
            throw new RuntimeException("Departement not found for id ::"+ id);
        }
        return departements;
    }

    public void deleteDepartementById(long id){

        this.departementRepository.deleteById(id);


    }
}