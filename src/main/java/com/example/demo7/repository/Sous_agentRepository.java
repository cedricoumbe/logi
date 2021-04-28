package com.example.demo7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo7.controller.User;
import com.example.demo7.model.Sous_agent;



@Repository
public interface Sous_agentRepository extends JpaRepository<Sous_agent, Long> {
	
	
	  @Query("SELECT u FROM Sous_agent u WHERE u.sous_agent_email=:sous_agent_email and u.sous_agent_mot_de_passe=:sous_agent_mot_de_passe")
	    Sous_agent findByUsernameOrEmail(String sous_agent_email,String sous_agent_mot_de_passe);


	  @Query("SELECT u FROM Sous_agent u WHERE u.users=:users")
	    Sous_agent findByUserid(User users);
	  
	  
}