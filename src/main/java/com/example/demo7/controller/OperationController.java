package com.example.demo7.controller;
import java.time.LocalDateTime;
import java.sql.Date;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo7.model.Contrat;
import com.example.demo7.model.Operation;
import com.example.demo7.model.Plancomptable;
import com.example.demo7.model.Reseautransfert;
import com.example.demo7.model.Sous_agent;
import com.example.demo7.service.ContratService;
import com.example.demo7.service.OperationService;
import com.example.demo7.service.RapprochementService;
import com.example.demo7.service.ReseautransfertService;
import com.example.demo7.service.Sous_agentService;

@Controller
public class OperationController {
	
	   @Autowired
	    private ReseautransfertService reseautransfertService;
	   
	   
	   @Autowired
	    private ContratService contratService;
	
	   @Autowired
	    private Sous_agentService sous_agent_services;
	   

	   @Autowired
	    private OperationService operation_services;
	   
	   
	   @Autowired
	    private RapprochementService rapprochement_services;
	   
	   
		  
			// create a java calendar instance
			  Calendar calendar = Calendar.getInstance();

			  // get a java date (java.util.Date) from the Calendar instance.
			  // this java date will represent the current date, or "now".
			  java.util.Date currentDate = calendar.getTime();

			  // now, create a java.sql.Date from the java.util.Date
			  java.sql.Date date1 = new java.sql.Date(currentDate.getTime());
	
	
	 @GetMapping("/get_operations")
	    public String listes_opertion(Model model){
		 
		   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
		   model.addAttribute("sous_agents",sous_agents); 
		   
		 
	      model.addAttribute("liste_operations",operation_services.find_all_aujourdhui_operation_By_Sous_agent(sous_agents,date1));
	      
	      model.addAttribute("liste_rapprochements",rapprochement_services.find_all_rapprochement_By_Sous_agent(sous_agents));
	      
	      
	      model.addAttribute("nombre_operations",operation_services.find_all_sum_aujourdhui_operation_By_Sous_agent(sous_agents,date1));
		  
	      
	         Operation operations = new Operation();
	      
			 operations.setOperation_montant_decaisser(10);
			 operations.setOperation_montant_payer(10);
			 operations.setOperation_nom_beneficiaire("eeeeeeee");
			 operations.setOperation_numero_transfert_operateur("eeeee");
			 operations.setSous_agent(sous_agents);
			 operations.setOperation_date_debut(date1);
		      operations.setOperation_date_fin(date1);
	      model.addAttribute("operations",operations);
		 
	        model.addAttribute("sous_agent_exist",AccueilConttoller.sous_agent_id);
		 
		 return "mazer/afficher_operation";
	 }
	 
	 @GetMapping("/get_all_operations")
	    public String tout_les_opertion(Model model){
		 
		  Operation operations = new Operation();
	
		    

		 

		   model.addAttribute("liste_sous_agents",sous_agent_services.getAllSous_agent()); 
		   
		 
	      model.addAttribute("liste_operations",operation_services.getAllOperation());
	      
	   
	 	 
			 operations.setOperation_montant_decaisser(10);
			 operations.setOperation_date_debut(date1);
		      operations.setOperation_date_fin(date1);
			 operations.setOperation_montant_payer(10);
			 operations.setOperation_nom_beneficiaire("eeeeeeee");
			 operations.setOperation_numero_transfert_operateur("eeeee");
	      
	      model.addAttribute("operations",operations);
		 
	     
		 return "mazer/afficher_tout_operation";
	 }
	 
	 
	 @PostMapping("/get_operation_periode")
	    public String tout_les_opertions_par_date(@ModelAttribute("operations") @Valid Operation operation,Model model, BindingResult bindingResult){
	
		 
            //   System.out.println(operation.getOperation_date_debut()+"eeeeeee"+operation.getOperation_date_fin());
		
		 
		 if (bindingResult.hasErrors()){
	        	
      	
      	   
      	 return "mazer/afficher_tout_operation_par_date";
      }
      
		 
		 
		 
		 
		 
		   model.addAttribute("liste_sous_agents",sous_agent_services.getAllSous_agent()); 
		   
		 Long aaa = operation_services.find_all_sum_operation_By_date_debut_date_fin_Sous_agent(operation.getSous_agent(), operation.getOperation_date_debut(), operation.getOperation_date_fin());
		   
		model.addAttribute("nombre_operations",aaa);
		   
			 
		      model.addAttribute("liste_operations",operation_services.find_all_operation_By_date_debut_date_fin_Sous_agent(operation.getSous_agent(), operation.getOperation_date_debut(), operation.getOperation_date_fin()));
		 
		      Operation operations = new Operation();
		      operations.setOperation_date_debut(operation.getOperation_date_debut());
		      operations.setOperation_date_fin( operation.getOperation_date_fin());
		      operations.setSous_agent(operation.getSous_agent());
		      operations.setOperation_montant_decaisser(11);
		      operations.setOperation_nom_beneficiaire("zzzz");
		      operations.setOperation_numero_transfert_operateur("eeee");
		      operations.setOperation_montant_payer(11);
		      
		      
		      
			   Sous_agent sous_agents = sous_agent_services.getSous_agentById(operation.getSous_agent().getSous_agent_id());
			   model.addAttribute("sous_agents",sous_agents); 
			   model.addAttribute("operations",operations); 
		 
		 return "mazer/afficher_tout_operation_par_date";
	 }
	 
	 
	 
	 @PostMapping("/get_operation_periode_par_sous_agent")
	    public String tout_les_opertions_par_date_par_sous_agent(@ModelAttribute("operations") @Valid Operation operation,Model model, BindingResult bindingResult){
	
		 if (bindingResult.hasErrors()){
   	       return "mazer/afficher_tout_operation_par_date";
         }
		 
		   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
		 
	System.out.println(sous_agents+"---"+operation.getReseautransfert()+"---"+operation.getOperation_date_debut()+"---"+operation.getOperation_date_fin());
   
		      model.addAttribute("liste_operations",operation_services.find_all_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, operation.getReseautransfert(),operation.getOperation_date_debut(), operation.getOperation_date_fin()));
		 
		     
		      
		      model.addAttribute("nombre_operations",operation_services.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents,operation.getReseautransfert(), operation.getOperation_date_debut(), operation.getOperation_date_fin()));
			    
		      
		      Long nbre = operation_services.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents,operation.getReseautransfert(), operation.getOperation_date_debut(), operation.getOperation_date_fin());
		      
		      Contrat contrats = contratService.findBysous_agent_reseau_transfert(sous_agents,operation.getReseautransfert());
		      
		  	System.out.println(contrats.getContrat_pourcentage()+"99999999");
		      if(contrats != null) {
		    	  
		    	   float comission = contrats.getContrat_pourcentage();
				      
				      float gain = nbre*comission;
				      
				      model.addAttribute("gain",gain);
		      }
		   
				 
		      
		      
		      Operation operations = new Operation();
		      operations.setOperation_date_debut(operation.getOperation_date_debut());
		      operations.setOperation_date_fin( operation.getOperation_date_fin());
		      operations.setSous_agent(operation.getSous_agent());
		      operations.setReseautransfert(operation.getReseautransfert());
		      operations.setOperation_montant_decaisser(11);
		      operations.setOperation_nom_beneficiaire("zzzz");
		      operations.setOperation_numero_transfert_operateur("eeee");
		      operations.setOperation_montant_payer(11);
		      
		      
		      
		   
			   model.addAttribute("sous_agents",sous_agents); 
			   model.addAttribute("operations",operations); 
			   model.addAttribute("liste_rapprochements",rapprochement_services.find_all_rapprochement_By_Sous_agent(sous_agents));
			      
			      
		 
		 return "mazer/afficher_operation";
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 @PostMapping("/get_operation_periode2")
	    public String tout_les_opertions_par_date2(@ModelAttribute("operations") @Valid Operation operation,Model model, BindingResult bindingResult){
	
	
         //   System.out.println(operation.getOperation_date_debut()+"eeeeeee"+operation.getOperation_date_fin());
		
		 
		 if (bindingResult.hasErrors()){
	        	
   	
   	   
   	 return "mazer/afficher_tout_operation_par_date";
   }
   
		 
		 
		 
		 
		 
		   model.addAttribute("liste_sous_agents",sous_agent_services.getAllSous_agent()); 
		   
			 
		      model.addAttribute("liste_operations",operation_services.find_all_operation_By_date_debut_date_fin_Sous_agent(operation.getSous_agent(), operation.getOperation_date_debut(), operation.getOperation_date_fin()));
		 
		      Operation operations = new Operation();
		      operations.setOperation_date_debut(operation.getOperation_date_debut());
		      operations.setOperation_date_fin( operation.getOperation_date_fin());
		      operations.setSous_agent(operation.getSous_agent());
		      
		      
		      
			   Sous_agent sous_agents = sous_agent_services.getSous_agentById(operation.getSous_agent().getSous_agent_id());
			   model.addAttribute("sous_agents",sous_agents); 
			   model.addAttribute("operations",operations); 
		 
		 return "mazer/afficher_tout_operation_par_date";
	 }
	 
	 @GetMapping("/get_operation_par_sous_agent/sous_agent/{sous_agent_id}/date_debut/{datedebut}/date_fin/{datefin}/reseau_transfert/{reseautransfertid}")
	    public String tout_les_opertions_par_date_get(@PathVariable("sous_agent_id") int sous_agent_id,  @PathVariable("reseautransfertid") int reseautransfertid,  @PathVariable("datedebut") Date datedebut,  @PathVariable("datefin") Date datefin, Model model){
	
		 Sous_agent sous_agents = sous_agent_services.getSous_agentById(sous_agent_id);
		 
		 
		
		 
		 
		 Reseautransfert reseautransferts = reseautransfertService.getReseautransfertById(reseautransfertid);
		 
	
		 
		
		 
		   model.addAttribute("liste_sous_agents",sous_agent_services.getAllSous_agent()); 
		   
			 
		   List<Operation> liste_operations = operation_services.find_all_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, reseautransferts, datedebut, datefin);
		   Long nbre = operation_services.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, reseautransferts, datedebut, datefin);
			 
		   System.out.println(liste_operations+"zzzzzzzz");
		   if(nbre == null) {
			   
			  	 return "mazer/afficher_tout_operation_par_date";
		   
		   }
		   
		      model.addAttribute("liste_operations",operation_services.find_all_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, reseautransferts, datedebut, datefin));
		    		  
		    		  
		    model.addAttribute("nombre_operations",operation_services.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, reseautransferts, datedebut, datefin));
			 
		    
		    
		  
		    Contrat contrats = contratService.findBysous_agent_reseau_transfert(sous_agents,reseautransferts);
		      
			 System.out.println(nbre+"rrrrrrrr");
		      if(contrats != null){
		    	  
		    	   float comission = contrats.getContrat_pourcentage();
				      
				      float gain = nbre*comission;
				      
				      
				 	 System.out.println(nbre+"rrrrrrrr"+comission);
				      
				      model.addAttribute("gain",gain); 
		    
		      }
		    
		    
		    
		    
		    
		    
		      Operation operations = new Operation();
		      operations.setOperation_date_debut(datedebut);
		      operations.setOperation_date_fin(datefin);
		      operations.setSous_agent(sous_agents);
		      operations.setOperation_montant_decaisser(11);
		      operations.setOperation_nom_beneficiaire("zzzz");
		      operations.setOperation_numero_transfert_operateur("eeee");
		      operations.setOperation_montant_payer(11);
		      
		      
	
			   model.addAttribute("sous_agents",sous_agents); 
			   model.addAttribute("operations",operations); 
		 
			   return "mazer/afficher_tout_operation_par_date";
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
	    	operations.setOperation_date_cre2(date1);
	    	operations.setOperation_date_debut(date1);
	    	operations.setOperation_date_fin(date1);
		    
		    
		
	        model.addAttribute("operations",operations);
	        model.addAttribute("reseautransferts",reseautransferts);
	
	        
	      
	        
	        return  "mazer/creer_operation";

	    }
	 
	 @PostMapping("/save_operation")
	 public String saveOperation(@ModelAttribute("operations") @Valid Operation operation, BindingResult bindingResult,Model model){


        if (bindingResult.hasErrors()){
        	
        	   Reseautransfert reseautransferts = reseautransfertService.getReseautransfertById(operation.getReseautransfert().getReseautransfertid());
        	   model.addAttribute("reseautransferts",reseautransferts);
        	   
        	   return "mazer/creer_operation";
        }
        
        
    	
        operation_services.saveOperation(operation);



        return "redirect:/get_operations";
    }
 

}
