package com.example.demo7.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo7.model.Sous_agent;
import com.example.demo7.service.ReseautransfertService;
import com.example.demo7.service.Sous_agentService;

@Controller
public class AccueilConttoller {
	

	 public static long sous_agent_id=0 ;

    @Autowired
    private Sous_agentService sous_agent_services;
    
    @Autowired
    private ReseautransfertService reseautransfertService;
	
    @GetMapping("/login2")
    public String affiche_page_login(Model model){

    	   Sous_agent sous_agents = new Sous_agent();
	        model.addAttribute("sous_agents",sous_agents);
        return "mazer/login";
    }
    
    
    
	 @GetMapping("/accueil")
	    public String affiche_page_accueil(Model model){

		 model.addAttribute("liste_reseautransferts",reseautransfertService.getAllReseautransfert());
		
		  
		   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
		   model.addAttribute("sous_agents",sous_agents); 
	        return "mazer/accueil";
	    }
	 
	 
	 @PostMapping("/authentification")
	 public String verifiaction( Sous_agent sous_agent,Model model){
		 
		 Sous_agent sous_agents = sous_agent_services.findByUsernameOrEmail(sous_agent.getSous_agent_email(), sous_agent.getSous_agent_mot_de_passe());
		 

       if(sous_agents==null) {
    	   
    	   return "redirect:/login2";
       }

       
    
       model.addAttribute("sous_agents",sous_agents);

       AccueilConttoller.sous_agent_id = sous_agents.getSous_agent_id();
       System.out.println(AccueilConttoller.sous_agent_id +"sssssssss");
        return "mazer/accueil";
    }

}
