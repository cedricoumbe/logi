package com.example.demo7.service;


import com.example.demo7.model.Reseautransfert;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReseautransfertService {

    List<Reseautransfert> getAllReseautransfert();

    void saveReseautransfert(Reseautransfert reseautransfert);

    Reseautransfert getReseautransfertById(long id);

    void deleteReseautransfertById(long id);
}
