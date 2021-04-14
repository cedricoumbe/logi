package com.example.demo7.repository;

import com.example.demo7.model.Reseautransfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReseautransfertRepository extends JpaRepository<Reseautransfert, Long> {
}
