package com.example.demo7.service;

import com.example.demo7.model.Reseautransfert;
import com.example.demo7.repository.ReseautransfertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReseautransfertServiceImpl implements ReseautransfertService {

    @Autowired
    private ReseautransfertRepository reseautransfertRepository;


    @Override
    public List<Reseautransfert> getAllReseautransfert() {

        return reseautransfertRepository.findAll();
    }

    @Override
    public void saveReseautransfert(Reseautransfert reseautransferts) {

        this.reseautransfertRepository.save(reseautransferts);

    }

    public Reseautransfert getReseautransfertById(long id){

        Optional<Reseautransfert> optional = reseautransfertRepository.findById(id);
        Reseautransfert reseautransferts = null;
        if(optional.isPresent()){
            reseautransferts = optional.get();
        }else{
            throw new RuntimeException("Reseautransfert not found for id ::"+ id);
        }
        return reseautransferts;
    }

    public void deleteReseautransfertById(long id){

        this.reseautransfertRepository.deleteById(id);


    }
}