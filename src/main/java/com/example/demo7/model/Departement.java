package com.example.demo7.model;


import javax.persistence.*;

@Entity
@Table(name="departement")
public class Departement{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long departement_id;

    @Column(name="departement_name")
    private String departement_name;



    public String getDepartement_name() {
        return departement_name;
    }

    public void setDepartement_name(String departement_name) {
        this.departement_name = departement_name;
    }

    public long getDepartement_id() {
        return departement_id;
    }

    public void setDepartement_id(long departement_id) {
        this.departement_id = departement_id;
    }
}
