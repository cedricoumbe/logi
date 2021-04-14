package com.example.demo7.controller;
import java.time.LocalDateTime;
import java.sql.Timestamp;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo7.model.Operation;
import com.example.demo7.model.Reseautransfert;
import com.example.demo7.model.Sous_agent;
import com.example.demo7.service.OperationService;
import com.example.demo7.service.ReseautransfertService;
import com.example.demo7.service.Sous_agentService;

@Controller
public class OperationController {
	
	   @Autowired
	    private ReseautransfertService reseautransfertService;
	
	   @Autowired
	    private Sous_agentService sous_agent_services;
	   

	   @Autowired
	    private OperationService operation_services;
	
	
	 @GetMapping("/get_operations")
	    public String listes_opertion(Model model){
		 
		   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
		   model.addAttribute("sous_agents",sous_agents); 
		   
		 
	      model.addAttribute("liste_operations",operation_services.find_all_operation_By_Sous_agent(sous_agents));
		 
		 
		 return "mazer/afficher_operation";
	 }
	 
	 
	 
	 @GetMapping("/create_operation/{id}")
	    public String create_new_sous_agent(@PathVariable(value="id") long id,Model model){
		
		
		    Operation operations = new Operation();
		    
		    Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
		 
		    Reseautransfert reseautransferts = reseautransfertService.getReseautransfertById(id);
		    
		    operations.setSous_agent(sous_agents);
		    operations.setReseautransfert(reseautransferts);
		    
		    
			
		    LocalDateTime now = LocalDateTime.now();
		    Timestamp sqlNow = Timestamp.valueOf(now);
		    
		    
		    
		   // System.out.println(sqlNow+"fffffffff");
	    	operations.setOperation_date_cre(sqlNow);
		    
		    
		
	        model.addAttribute("operations",operations);
	        model.addAttribute("reseautransferts",reseautransferts);
	
	        
	      
	        
	        return  "mazer/creer_operation";

	    }
	 
	 @PostMapping("/save_operation")
	 public String saveOperation(@ModelAttribute("operations") @Valid Operation operation, BindingResult bindingResult,Model model){


        if (bindingResult.hasErrors()) {
        	
        	   Reseautransfert reseautransferts = reseautransfertService.getReseautransfertById(operation.getReseautransfert().getReseautransfertid());
        	   model.addAttribute("reseautransferts",reseautransferts);
        	   
        	   return "mazer/creer_operation";
        }
        
        
    	
        operation_services.saveOperation(operation);



        return "redirect:/get_operations";
    }
 

}
