package com.example.demo7.service;


import com.example.demo7.model.Departement;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DepartementService {

    List<Departement> getAllDepartement();

    void saveDepartement(Departement departement);

    Departement getDepartementById(long id);

    void deleteDepartementById(long id);
}
