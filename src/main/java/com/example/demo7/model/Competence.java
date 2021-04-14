package com.example.demo7.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="competence")
public class Competence{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competence_id")
    private long competence_id;

    @Column(name="competence_name")
    private String competence_name;


    public String getCompetence_name() {
        return competence_name;
    }

    public void setCompetence_name(String competence_name) {
        this.competence_name = competence_name;
    }

    public long getCompetence_id() {
        return competence_id;
    }

    public void setCompetence_id(long competence_id) {
        this.competence_id = competence_id;
    }
}
