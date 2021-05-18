package com.example.demo7.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="operation")
public class Operation{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long operation_id;

   
    @Column(name="operation_numero_transfert")
    private Integer operation_numero_transfert;

    @NotEmpty
    @Column(name="operation_nom_beneficiaire",nullable = false)
    private String operation_nom_beneficiaire;
	
    @NotEmpty
    @Column(name="operation_numero_transfert_operateur",nullable = false)
    private String operation_numero_transfert_operateur;
	
    @NotNull
    @Column(name="operation_montant_payer",nullable = false)
    private Integer operation_montant_payer;
    
    @NotNull
	 @Column(name="operation_montant_decaisser",nullable = false)
    private Integer operation_montant_decaisser;
    

    @Column(name="operation_date_cre")
    private Timestamp operation_date_cre;

    @Column(name="operation_date_mod")
    private Date operation_date_mod;
    
    @Column(name="operation_code_unique")
    private String operation_code_unique;
    
    
    @Column(name="operation_date_cre2")
    private Date operation_date_cre2;

    
    
    @NotNull
    private Date operation_date_debut;
    
    @NotNull
    private Date operation_date_fin;
    
    
    @OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sous_agent_id")
	private Sous_agent sous_agent;
    

    @OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reseautransfertid")
	private Reseautransfert reseautransfert;
    

	public long getOperation_id() {
		return operation_id;
	}

	public void setOperation_id(long operation_id) {
		this.operation_id = operation_id;
	}

	public Integer getOperation_numero_transfert() {
		return operation_numero_transfert;
	}

	public void setOperation_numero_transfert(Integer operation_numero_transfert) {
		this.operation_numero_transfert = operation_numero_transfert;
	}

	public String getOperation_nom_beneficiaire() {
		return operation_nom_beneficiaire;
	}

	public void setOperation_nom_beneficiaire(String operation_nom_beneficiaire) {
		this.operation_nom_beneficiaire = operation_nom_beneficiaire;
	}

	public String getOperation_numero_transfert_operateur() {
		return operation_numero_transfert_operateur;
	}

	public void setOperation_numero_transfert_operateur(String operation_numero_transfert_operateur) {
		this.operation_numero_transfert_operateur = operation_numero_transfert_operateur;
	}

	public Integer getOperation_montant_payer() {
		return operation_montant_payer;
	}

	public void setOperation_montant_payer(Integer operation_montant_payer) {
		this.operation_montant_payer = operation_montant_payer;
	}

	public Integer getOperation_montant_decaisser() {
		return operation_montant_decaisser;
	}

	public void setOperation_montant_decaisser(Integer operation_montant_decaisser) {
		this.operation_montant_decaisser = operation_montant_decaisser;
	}

	public Sous_agent getSous_agent() {
		return sous_agent;
	}

	public void setSous_agent(Sous_agent sous_agent) {
		this.sous_agent = sous_agent;
	}

	public Reseautransfert getReseautransfert() {
		return reseautransfert;
	}

	public void setReseautransfert(Reseautransfert reseautransfert) {
		this.reseautransfert = reseautransfert;
	}

	public Timestamp getOperation_date_cre() {
		return operation_date_cre;
	}

	public void setOperation_date_cre(Timestamp operation_date_cre) {
		this.operation_date_cre = operation_date_cre;
	}

	

	public Date getOperation_date_cre2() {
		return operation_date_cre2;
	}

	public void setOperation_date_cre2(Date operation_date_cre2) {
		this.operation_date_cre2 = operation_date_cre2;
	}

	public Date getOperation_date_debut() {
		return operation_date_debut;
	}

	public void setOperation_date_debut(Date datedebut) {
		this.operation_date_debut = datedebut;
	}

	public Date getOperation_date_fin() {
		return operation_date_fin;
	}

	public void setOperation_date_fin(Date operation_date_fin) {
		this.operation_date_fin = operation_date_fin;
	}


	

  
	

  
}
