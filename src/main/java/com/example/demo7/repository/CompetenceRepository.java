package com.example.demo7.repository;

import com.example.demo7.model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {
}