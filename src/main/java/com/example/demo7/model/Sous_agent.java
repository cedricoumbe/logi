package com.example.demo7.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.example.demo7.controller.User;

@Entity
@Table(name="sous_agent")
public class Sous_agent implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sous_agent_id;

    @NotEmpty
    @Column(name="sous_agent_nom")
    private String sous_agent_nom;
    
    
   
    
    @NotNull
    @Size(min=2, max=30)
    @Column(name="sous_agent_mot_de_passe")
    private String sous_agent_mot_de_passe;
    
    
    @NotNull
    @Size(min=2, max=30)
    @Column(name="sous_agent_email")
    private String sous_agent_email;
    

    @NotNull
    @Size(min=2, max=30)
    @Column(name="sous_agent_adresse",nullable = false)
    private String sous_agent_adresse;
	
	 @NotNull
    @Size(min=2, max=30)
    @Column(name="sous_agent_nom_operateur",nullable = false)
    private String sous_agent_nom_operateur;
	
    @NotNull
    @Size(min=2, max=30)
    @Column(name="sous_agent_conctact_operateur",nullable = false)
    private String sous_agent_conctact_operateur;
    
	 @NotNull
    @Size(min=2, max=30)
    @Column(name="sous_agent_nom_commercial",nullable = false)
    private String sous_agent_nom_commercial;
    

    @Column(name="sous_agent_date_cre")
    private Date sous_agent_date_cre;

    @Column(name="sous_agent_date_mod")
    private Date sous_agent_date_mod;
    
    @Column(name="sous_agent_code_unique")
    private String sous_agent_code_unique;

    
    @NotNull
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "sousagent_reseautransfert",
            joinColumns = @JoinColumn(name = "sous_agent_id"),
            inverseJoinColumns = @JoinColumn(name = "reseautransfertid")
    )
    public Set<Reseautransfert> reseautransferts = new HashSet<>();
    
    
    
    @NotNull
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "sousagent_users",
            joinColumns = @JoinColumn(name = "sous_agent_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private Set<User> users = new HashSet<>();
    
    
  
    
    
    
    @OneToMany(mappedBy = "sous_agent")
    private List<Contrat> contrats;
    
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "plancomptable_id"), name = "plancomptable_id")
    private Plancomptable plancomptables;


	public long getSous_agent_id() {
		return sous_agent_id;
	}


	public void setSous_agent_id(long sous_agent_id) {
		this.sous_agent_id = sous_agent_id;
	}


	public String getSous_agent_nom() {
		return sous_agent_nom;
	}


	public void setSous_agent_nom(String sous_agent_nom) {
		this.sous_agent_nom = sous_agent_nom;
	}


	public String getSous_agent_adresse() {
		return sous_agent_adresse;
	}


	public void setSous_agent_adresse(String sous_agent_adresse) {
		this.sous_agent_adresse = sous_agent_adresse;
	}


	public String getSous_agent_nom_operateur() {
		return sous_agent_nom_operateur;
	}


	public void setSous_agent_nom_operateur(String sous_agent_nom_operateur) {
		this.sous_agent_nom_operateur = sous_agent_nom_operateur;
	}


	public String getSous_agent_conctact_operateur() {
		return sous_agent_conctact_operateur;
	}


	public void setSous_agent_conctact_operateur(String sous_agent_conctact_operateur) {
		this.sous_agent_conctact_operateur = sous_agent_conctact_operateur;
	}


	public String getSous_agent_nom_commercial() {
		return sous_agent_nom_commercial;
	}


	public void setSous_agent_nom_commercial(String sous_agent_nom_commercial) {
		this.sous_agent_nom_commercial = sous_agent_nom_commercial;
	}


	public Date getSous_agent_date_cre() {
		return sous_agent_date_cre;
	}


	public void setSous_agent_date_cre(Date sous_agent_date_cre) {
		this.sous_agent_date_cre = sous_agent_date_cre;
	}


	public Date getSous_agent_date_mod() {
		return sous_agent_date_mod;
	}


	public void setSous_agent_date_mod(Date sous_agent_date_mod) {
		this.sous_agent_date_mod = sous_agent_date_mod;
	}


	public String getSous_agent_code_unique() {
		return sous_agent_code_unique;
	}


	public void setSous_agent_code_unique(String sous_agent_code_unique) {
		this.sous_agent_code_unique = sous_agent_code_unique;
	}


	

	public Set<Reseautransfert> getReseautransferts() {
		return reseautransferts;
	}


	public void setReseautransferts(Set<Reseautransfert> reseautransferts) {
		this.reseautransferts = reseautransferts;
	}



	public String getSous_agent_mot_de_passe() {
		return sous_agent_mot_de_passe;
	}


	public void setSous_agent_mot_de_passe(String sous_agent_mot_de_passe) {
		this.sous_agent_mot_de_passe = sous_agent_mot_de_passe;
	}


	public String getSous_agent_email() {
		return sous_agent_email;
	}


	public void setSous_agent_email(String sous_agent_email) {
		this.sous_agent_email = sous_agent_email;
	}


	

	public List<Contrat> getContrats() {
		return contrats;
	}


	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}


	public Plancomptable getPlancomptables() {
		return plancomptables;
	}


	public void setPlancomptables(Plancomptable plancomptables) {
		this.plancomptables = plancomptables;
	}


	

  
}
