package com.example.demo7.controller;
import java.time.LocalDateTime;
import java.sql.Date;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.example.demo7.model.Caisse;
import com.example.demo7.model.Contrat;
import com.example.demo7.model.Etat_caisse;
import com.example.demo7.model.Historique;
import com.example.demo7.model.Operation;
import com.example.demo7.model.Plancomptable;
import com.example.demo7.model.Reseautransfert;
import com.example.demo7.model.Sous_agent;
import com.example.demo7.service.CaisseService;
import com.example.demo7.service.ContratService;
import com.example.demo7.service.Etat_caisseService;
import com.example.demo7.service.HistoriqueService;
import com.example.demo7.service.OperationService;
import com.example.demo7.service.PlancomptableService;
import com.example.demo7.service.RapprochementService;
import com.example.demo7.service.ReseautransfertService;
import com.example.demo7.service.Sous_agentService;
import com.example.demo7.service.UserService;

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
	    private PlancomptableService plan_comptable_services;
	   
	   
	   
	   
	   @Autowired
	    private RapprochementService rapprochement_services;
	   
	   
	   @Autowired
	    private CaisseService caisseService;
	    


	    @Autowired
	    private Etat_caisseService  etat_caisseServices;
	    
	    
	    @Autowired
	    private HistoriqueService historiqueServices;
	    
	    
	    @Autowired
	    private UserService userService;
	   
	   
		  
			// create a java calendar instance
			  Calendar calendar = Calendar.getInstance();

			  // get a java date (java.util.Date) from the Calendar instance.
			  // this java date will represent the current date, or "now".
			  java.util.Date currentDate = calendar.getTime();

			  // now, create a java.sql.Date from the java.util.Date
			  java.sql.Date date1 = new java.sql.Date(currentDate.getTime());
			  
			  
			  
	
			  
				
 @GetMapping("/historique_comptable")
public String listes_historique(Model model){
				  
	  Historique historiques = new Historique();
	  historiques.setHistorique_date_debut(date1);
	  historiques.setHistorique_date_fin(date1);
	  historiques.setHistorique_credit(0);
	  historiques.setHistorique_debit(0);
	  historiques.setHistorique_nom("eee");
	  historiques.setHistorique_numero("eeee");
	  historiques.setHistorique_date_cre(date1);
		
	    
	 
   
     
  
     List<Plancomptable> list_plancomptables = new ArrayList<Plancomptable>();
     
	   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
     
     
	   list_plancomptables.add(sous_agents.getPlancomptables());
     
     
     for(Reseautransfert list_reseau_transfert:sous_agents.getReseautransferts()){
    	 
    	 
    	 
    	 list_plancomptables.add(list_reseau_transfert.getPlancomptables());
    	 
    	 
     }
     
     
	 User users = userService.getUserById(AccueilConttoller.users_id);
     
	 list_plancomptables.add(users.getCaisses().getPlancomptables());
     
     model.addAttribute("list_plancomptables",list_plancomptables);
	 
     model.addAttribute("historiques",historiques); 
				  
				  
 return "mazer/afficher_historique";  
}
 
 
	
@GetMapping("/get_journal")
public String listes_journal(Model model){
	  
Historique historiques = new Historique();
historiques.setHistorique_date_debut(date1);
historiques.setHistorique_date_fin(date1);
historiques.setHistorique_credit(0);
historiques.setHistorique_debit(0);
historiques.setHistorique_nom("eee");
historiques.setHistorique_numero("eeee");
historiques.setHistorique_date_cre(date1);














model.addAttribute("list_plancomptables",plan_comptable_services.getAllPlancomptable());

model.addAttribute("historiques",historiques); 
	  
	  
return "mazer/afficher_journal";  
}
 
 
 
 
 
 
 
 
 
 
 
 
 
			  
			  
	
	 @GetMapping("/get_operations")
	    public String listes_opertion(Model model){
		 
		 
		 User users = userService.getUserById(AccueilConttoller.users_id);
		 
		 if(users.getCaisses() == null) {
	        	
        	 return "redirect:/accueil";
        	
        }
        
	        Caisse caisses2 = caisseService.getCaisseById(users.getCaisses().getCaisse_id());
	        
	       
	 	   
      	   
      	   

	      	   
	      		Etat_caisse etat_caisses = etat_caisseServices.find_by_caisse_id_activer_Etat_caisse(caisses2);
	 	    
	         if(date1.getDay() != etat_caisses.getEtat_caisse_date_cre().getDay() ) {
	        	 
	        	 
	        	 return "redirect:/fermer_caisse";
	        	 
	         }
	        
	        
	        
	        
	        
	        
	        
		 
		   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
		   model.addAttribute("sous_agents",sous_agents); 
		   
		 
	      model.addAttribute("liste_operations",operation_services.find_all_aujourdhui_operation_By_Sous_agent(sous_agents,date1));
	      
	     
	      
	      float somme_comissions=0;
	      float somme_comissions_rapprocher=0;
	      
	      for(Reseautransfert list_reseau_transfert:sous_agents.getReseautransferts()){
	    	  
	    	  
	    	  Long total_paiement = operation_services.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, list_reseau_transfert, date1, date1);
	    	 
	    	  
	    	  Long total_paiement_rapprocher = operation_services.find_all_rapprocher_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, list_reseau_transfert, date1, date1);
		    	 
	    	  
	    	  Contrat contrats = contratService.findBysous_agent_reseau_transfert(sous_agents,list_reseau_transfert);
	    	  
	    	  if(contrats != null){
	    		  
	    			 float comission = contrats.getContrat_pourcentage();
	    		  
                    float gain = total_paiement*comission;
                    
                    float gain_rapprocher = total_paiement_rapprocher*comission;
			      
			      somme_comissions=somme_comissions+gain;
			      somme_comissions_rapprocher=somme_comissions_rapprocher+gain_rapprocher;
	    		  
	    	  }
	    	  
	    	  
	      }
	      
	      
	      
	      
	 
	
	  
	      
	      
	      
	    model.addAttribute("total_somme_comissions",somme_comissions); 
	      
	    model.addAttribute("total_somme_comissions_rapprocher",somme_comissions_rapprocher);    
	      
	      
	      
	      
	      
	      model.addAttribute("liste_rapprochements",rapprochement_services.find_all_rapprochement_By_Sous_agent(sous_agents));
	      
	      
	      model.addAttribute("nombre_operations",operation_services.find_all_sum_aujourdhui_operation_By_Sous_agent(sous_agents,date1));
	      
	      
	      model.addAttribute("nombre_operations_rapprocher",operation_services.find_all_rapprocher_sum_aujourdhui_operation_By_Sous_agent(sous_agents,date1));
		  
	     // float total = 0;
			
	      
	     /// float total = historiqueServices.find_all_historique_By_date_today_Plancomptable(caisses2.getPlancomptables(), date1);	
	        
	   //  model.addAttribute("total_solde",total);
	      
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
	        
	     
	      	   

	       
	       if(etat_caisses.getEtat_caisse_ouvert_fermer() == 1) {
	    	   return "mazer/afficher_operation";
	    	   
	       }else {
	    	   
	    	   return "redirect:/ouvrir_caisse";
	       }
		 
		
		 
		 
		 
		 
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
	 
	 
	 @GetMapping("/create_approvisionnement")
	    public String creer_approvisionnement(Model model){
		 
		 
			User users = userService.getUserById(AccueilConttoller.users_id);
			
		    Caisse caisses2 = caisseService.getCaisseById(users.getCaisses().getCaisse_id());
		
			Historique historiques = new Historique();
	     
	     	historiques.setHistorique_debit(0);
	     	historiques.setHistorique_date_cre(date1);
	     	historiques.setHistorique_nom("Approvisionnement en caisse");
	     	historiques.setPlancomptables(caisses2.getPlancomptables());
	     	historiques.setHistorique_numero(caisses2.getPlancomptables().getPlancomptable_numero());
	   
	     	  model.addAttribute("historiques",historiques);
	     	  

			   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
			   model.addAttribute("sous_agents",sous_agents); 
	     	  
	     	  
	     	  model.addAttribute("liste_historiques",historiqueServices.find_all_historique_By_Plancomptable(caisses2.getPlancomptables()));
		 return "mazer/afficher_approvisionement";
	 } 
	 
	 
	 @GetMapping("/create_decaissement")
	    public String creer_decaissement(Model model){
		 
		 
			User users = userService.getUserById(AccueilConttoller.users_id);
			
		    Caisse caisses2 = caisseService.getCaisseById(users.getCaisses().getCaisse_id());
		    
		    Etat_caisse etat_caisses = etat_caisseServices.find_by_caisse_id_activer_Etat_caisse(caisses2);
		
			Historique historiques = new Historique();
	     
	     	historiques.setHistorique_credit(0);
	     	historiques.setHistorique_date_cre(date1);
	     	historiques.setHistorique_nom("Decaissement en caisse");
	     	historiques.setPlancomptables(caisses2.getPlancomptables());
	     	historiques.setHistorique_numero(caisses2.getPlancomptables().getPlancomptable_numero());
	   
	     	  model.addAttribute("historiques",historiques);
	     	  
	     	  float total = historiqueServices.find_all_historique_By_date_debut_date_fin_today_Plancomptable(caisses2.getPlancomptables(), date1,etat_caisses.getEtat_caisse_date_cre());	
	          	
	     	   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
			   model.addAttribute("sous_agents",sous_agents); 
	 	     
	           model.addAttribute("total_solde",total);
	     	  
	     	  model.addAttribute("liste_historiques",historiqueServices.find_all_historique_By_Plancomptable(caisses2.getPlancomptables()));
		 return "mazer/afficher_decaissement";
	 }
	 
	 
	 
	 @PostMapping("/get_journal_periode")
	    public String tout_les_periodes_par_date(@ModelAttribute("historiques") @Valid Historique historique,Model model, BindingResult bindingResult){
	
		 
    
		
		 
		 if (bindingResult.hasErrors()){
	        	
	
	   
	      return "mazer/afficher_journal";
	      
	      
     }
		 
		 

	     
	     
	     model.addAttribute("list_plancomptables",plan_comptable_services.getAllPlancomptable());
	     
	     
	     
	     
	     
	     Historique historiques = new Historique();
	     historiques.setHistorique_date_debut(historique.getHistorique_date_debut());
	     historiques.setHistorique_date_fin(historique.getHistorique_date_fin());
	     historiques.setPlancomptables(historique.getPlancomptables());
	     
	     historiques.setHistorique_credit(0);
		  historiques.setHistorique_debit(0);
		  historiques.setHistorique_nom("eee");
		  historiques.setHistorique_numero("eeee");
		  historiques.setHistorique_date_cre(date1);
			
	     
		 
	     model.addAttribute("historiques",historiques); 
		 
		 
		 
	     model.addAttribute("liste_historiques",historiqueServices.find_all_historique_By_date_debut_date_fin__Plancomptable(historique.getPlancomptables(), historique.getHistorique_date_debut(), historique.getHistorique_date_fin())); 
		 
		 
		 
		 
			   
		 return "mazer/afficher_tout_journal_par_date";
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 @PostMapping("/get_historique_periode")
	    public String tout_les_opertions_par_date(@ModelAttribute("historiques") @Valid Historique historique,Model model, BindingResult bindingResult){
	
		 
       
		
		 
		 if (bindingResult.hasErrors()){
	        	
   	
   	   
   	      return "mazer/afficher_historique";
   	      
   	      
        }
		 
		 

	     List<Plancomptable> list_plancomptables = new ArrayList<Plancomptable>();
	     
		   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
	     
	     
		   list_plancomptables.add(sous_agents.getPlancomptables());
	     
	     
	     for(Reseautransfert list_reseau_transfert:sous_agents.getReseautransferts()){
	    	 
	    	 
	    	 
	    	 list_plancomptables.add(list_reseau_transfert.getPlancomptables());
	    	 
	    	 
	     }
	     
	     
		 User users = userService.getUserById(AccueilConttoller.users_id);
	     
		 list_plancomptables.add(users.getCaisses().getPlancomptables());
	     
	     model.addAttribute("list_plancomptables",list_plancomptables);
	     
	     
	     
	     
	     
	     Historique historiques = new Historique();
	     historiques.setHistorique_date_debut(historique.getHistorique_date_debut());
	     historiques.setHistorique_date_fin(historique.getHistorique_date_fin());
	     historiques.setPlancomptables(historique.getPlancomptables());
	     
	     historiques.setHistorique_credit(0);
		  historiques.setHistorique_debit(0);
		  historiques.setHistorique_nom("eee");
		  historiques.setHistorique_numero("eeee");
		  historiques.setHistorique_date_cre(date1);
			
	     
		 
	     model.addAttribute("historiques",historiques); 
		 
		 
		 
	     model.addAttribute("liste_historiques",historiqueServices.find_all_historique_By_date_debut_date_fin__Plancomptable(historique.getPlancomptables(), historique.getHistorique_date_debut(), historique.getHistorique_date_fin())); 
		 
		 
		 
		 
			   
		 return "mazer/afficher_tout_historique_par_date";
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
		 
		      
		      
		      
		      float somme_comissions=0;
		      
		      
		      for(Reseautransfert list_reseau_transfert:operation.getSous_agent().getReseautransferts()){
		    	  
		    	  
		    	  Long total_paiement = operation_services.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(operation.getSous_agent(), list_reseau_transfert, operation.getOperation_date_debut(), operation.getOperation_date_fin());
		    	  Contrat contrats = contratService.findBysous_agent_reseau_transfert(operation.getSous_agent(),list_reseau_transfert);
		    	  
		    	  if(contrats != null){
		    		  
		    			 float comission = contrats.getContrat_pourcentage();
		    		  
	                    float gain = total_paiement*comission;
				      
				      somme_comissions=somme_comissions+gain;
		    		  
		    	  }
		    	  
		    	  
		      }
		      
		      
		      
		      
		      
		      
			     model.addAttribute("total_somme_comissions",somme_comissions); 
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
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
		 
			   
		 return "mazer/afficher_tout_operation_par_date2";
	 }
	 
	 
	 
	 @PostMapping("/get_operation_periode_par_sous_agent")
	    public String tout_les_opertions_par_date_par_sous_agent(@ModelAttribute("operations") @Valid Operation operation,Model model, BindingResult bindingResult){
	
		 if (bindingResult.hasErrors()){
   	       return "mazer/afficher_tout_operation_par_date";
         }
		 
		   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
		 
	System.out.println(sous_agents+"---"+operation.getReseautransfert()+"---"+operation.getOperation_date_debut()+"---"+operation.getOperation_date_fin());
   
	
	 List<Operation> liste_operations= operation_services.find_all_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, operation.getReseautransfert(),operation.getOperation_date_debut(), operation.getOperation_date_fin());
	
	 
	 System.out.println(liste_operations+"eeeeeeeeeee");
	    if(liste_operations.size()==0) {
	 	   return "redirect:/get_operations";
	    	
	    	
	    }
	    	
	    	
		 
		      model.addAttribute("liste_operations",liste_operations);
		 
		     
		      
		      model.addAttribute("nombre_operations",operation_services.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents,operation.getReseautransfert(), operation.getOperation_date_debut(), operation.getOperation_date_fin()));
			    
		      model.addAttribute("nombre_operations_rapprocher",operation_services.find_all_rapprocher_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents,operation.getReseautransfert(), operation.getOperation_date_debut(), operation.getOperation_date_fin()));
				 
		      Long total_paiement_rapprocher = operation_services.find_all_rapprocher_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents,operation.getReseautransfert(), operation.getOperation_date_debut(), operation.getOperation_date_fin());
		    	  
		      
		      
		      Long nbre = operation_services.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents,operation.getReseautransfert(), operation.getOperation_date_debut(), operation.getOperation_date_fin());
		      
		      Contrat contrats = contratService.findBysous_agent_reseau_transfert(sous_agents,operation.getReseautransfert());
		      
		  	System.out.println(contrats.getContrat_pourcentage()+"99999999");
		      if(contrats != null) {
		    	  
		    	   float comission = contrats.getContrat_pourcentage();
				      
				      float gain = nbre*comission;
				      
				      model.addAttribute("gain",gain);

					     model.addAttribute("total_somme_comissions",gain);  
					     
					     
					     float gain_rapprocher = total_paiement_rapprocher*comission;
					     
					     model.addAttribute("total_somme_comissions_rapprocher",gain_rapprocher);  
					      
					  
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
		 
		
			 
			
			    if(liste_operations.size()==0) {
			 	   return "redirect:/get_all_operations";
			    	
			    	
			    }
		   
		   Long nbre = operation_services.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, reseautransferts, datedebut, datefin);
			 
		   System.out.println(liste_operations+"zzzzzzzz");
		   if(nbre == null) {
			   
			  	 return "mazer/afficher_tout_operation_par_date";
		   
		   }
		   
		      model.addAttribute("liste_operations",operation_services.find_all_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, reseautransferts, datedebut, datefin));
		    		  
		    		  
		    model.addAttribute("nombre_operations",operation_services.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, reseautransferts, datedebut, datefin));
			 
		    /*
		    
		  
		    Contrat contrats = contratService.findBysous_agent_reseau_transfert(sous_agents,reseautransferts);
		      
			 System.out.println(nbre+"rrrrrrrr");
		      if(contrats != null){
		    	  
		    	   float comission = contrats.getContrat_pourcentage();
				      
				      float gain = nbre*comission;
				      
				      
				 	 System.out.println(nbre+"rrrrrrrr"+comission);
				      
				      model.addAttribute("gain",gain); 
		    
		      }
		    
*/
		    float somme_comissions=0;
		      
		      
		  
		    	  
		    	  
		    	  Long total_paiement = operation_services.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, reseautransferts, datedebut, datefin);
		    	  Contrat contrats = contratService.findBysous_agent_reseau_transfert(sous_agents,reseautransferts);
		    	  
		    	  if(contrats != null){
		    		  
		    			 float comission = contrats.getContrat_pourcentage();
		    		  
	                    float gain = total_paiement*comission;
				      
				      somme_comissions=somme_comissions+gain;
		    		  
		    	  }
		    	  
		    	  
	
		    
		    
		    
		    
		    
		    
		    
		
		      
		      
		      
		    model.addAttribute("total_somme_comissions",somme_comissions);  
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
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
			   model.addAttribute("reseautransferts",reseautransferts); 
			   return "mazer/afficher_tout_operation_par_date";
	 }
	 
	 
	 
	 
	 
	 @GetMapping("/valider_operation/sous_agent/{sous_agent_id}/date_debut/{datedebut}/date_fin/{datefin}/reseau_transfert/{reseautransfertid}/operation_id/{operationid}")
	    public String validation_operation(@PathVariable("sous_agent_id") int sous_agent_id,  @PathVariable("operationid") int operationid,  @PathVariable("reseautransfertid") int reseautransfertid,  @PathVariable("datedebut") Date datedebut,  @PathVariable("datefin") Date datefin, Model model){
	
		 
		 Operation operation_valider = operation_services.getOperationById(operationid);
		 operation_valider.setOperation_valider(1);
		 operation_services.saveOperation(operation_valider);
		 
		 
		   //je credite le compte du reseau de transfert
	    	Historique historiques = new Historique();
	     	historiques.setHistorique_credit(operation_valider.getOperation_montant_decaisser());
	     	historiques.setHistorique_debit(0);
	     	historiques.setHistorique_date_cre(date1);
	     	historiques.setHistorique_nom("compense du sous agent "+operation_valider.getSous_agent().getSous_agent_nom()+" pour le paiement du reseau de transfert"+operation_valider.getReseautransfert().getReseautransfertnom());
	     	historiques.setPlancomptables(operation_valider.getSous_agent().getPlancomptables());
	     	historiques.setHistorique_numero(operation_valider.getSous_agent().getPlancomptables().getPlancomptable_numero());
	     	historiqueServices.saveHistorique(historiques);
	     	
	     	
	     	  Contrat contrats2 = contratService.findBysous_agent_reseau_transfert(operation_valider.getSous_agent(),operation_valider.getReseautransfert());
	    	  
	     	 float comission2 = 0;
	     	 
	     	float gain2 = 0;
	     	  
	    	  if(contrats2 != null){
	    		  
	    		   comission2 = contrats2.getContrat_pourcentage();
	    		  
                   gain2 = operation_valider.getOperation_montant_decaisser()*comission2;
			      
		
	    		  
	    	  }
	    	  
	     	
		 
		 
	    	Historique historiques2 = new Historique();
	     	historiques2.setHistorique_credit(gain2);
	     	historiques2.setHistorique_debit(0);
	     	historiques2.setHistorique_date_cre(date1);
	     	historiques2.setHistorique_nom("paiement des comissions du sous agent "+operation_valider.getSous_agent().getSous_agent_nom()+" pour le paiement du reseau de transfert "+operation_valider.getReseautransfert().getReseautransfertnom());
	     	historiques2.setPlancomptables(operation_valider.getSous_agent().getPlancomptables());
	     	historiques2.setHistorique_numero(operation_valider.getSous_agent().getPlancomptables().getPlancomptable_numero());
	     	historiqueServices.saveHistorique(historiques2);
		 
		 
		 
		 
		 Sous_agent sous_agents = sous_agent_services.getSous_agentById(sous_agent_id);
		 
		 
		 Reseautransfert reseautransferts = reseautransfertService.getReseautransfertById(reseautransfertid);
		 
	
		 
		
		 
		   model.addAttribute("liste_sous_agents",sous_agent_services.getAllSous_agent()); 
		   
			 
		   List<Operation> liste_operations = operation_services.find_all_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, reseautransferts, datedebut, datefin);
		 
		
			 
			
			    if(liste_operations.size()==0) {
			 	   return "redirect:/get_all_operations";
			    	
			    	
			    }
		   
		   Long nbre = operation_services.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, reseautransferts, datedebut, datefin);
			 
		   System.out.println(liste_operations+"zzzzzzzz");
		   if(nbre == null) {
			   
			  	 return "mazer/afficher_tout_operation_par_date";
		   
		   }
		   
		      model.addAttribute("liste_operations",operation_services.find_all_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, reseautransferts, datedebut, datefin));
		    		  
		    		  
		    model.addAttribute("nombre_operations",operation_services.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, reseautransferts, datedebut, datefin));
			 
		    /*
		    
		  
		    Contrat contrats = contratService.findBysous_agent_reseau_transfert(sous_agents,reseautransferts);
		      
			 System.out.println(nbre+"rrrrrrrr");
		      if(contrats != null){
		    	  
		    	   float comission = contrats.getContrat_pourcentage();
				      
				      float gain = nbre*comission;
				      
				      
				 	 System.out.println(nbre+"rrrrrrrr"+comission);
				      
				      model.addAttribute("gain",gain); 
		    
		      }
		    
*/
		    float somme_comissions=0;
		      
		      
		  
		    	  
		    	  
		    	  Long total_paiement = operation_services.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agents, reseautransferts, datedebut, datefin);
		    	  Contrat contrats = contratService.findBysous_agent_reseau_transfert(sous_agents,reseautransferts);
		    	  
		    	  if(contrats != null){
		    		  
		    			 float comission = contrats.getContrat_pourcentage();
		    		  
	                    float gain = total_paiement*comission;
				      
				      somme_comissions=somme_comissions+gain;
		    		  
		    	  }
		    	  
		    	  
	
		    
		    
		    
		    
		    
		    
		    
		
		      
		      
		      
		    model.addAttribute("total_somme_comissions",somme_comissions);  
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
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
			   model.addAttribute("reseautransferts",reseautransferts); 
			   return "mazer/afficher_tout_operation_par_date";
	 }
	 
	 
	 
	 
	 
	 
	 @GetMapping("/create_operation/{id}")
	    public String create_new_sous_agent(@PathVariable(value="id") long id,Model model){
		 
		 
		 User users = userService.getUserById(AccueilConttoller.users_id);
	        Caisse caisses2 = caisseService.getCaisseById(users.getCaisses().getCaisse_id());
	        
	        
      		Etat_caisse etat_caisses = etat_caisseServices.find_by_caisse_id_activer_Etat_caisse(caisses2);
      		
      		float total = historiqueServices.find_all_historique_By_date_debut_date_fin_today_Plancomptable(caisses2.getPlancomptables(), date1,etat_caisses.getEtat_caisse_date_cre());	
          	
            
     
            model.addAttribute("total_solde",total);
		
		
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
	        model.addAttribute("sous_agents",sous_agents);
	        model.addAttribute("reseautransferts",reseautransferts);
	
	        
	      
	        
	        return  "mazer/creer_operation";

	    }
	 
	 @PostMapping("/save_operation")
	 public String saveOperation(@ModelAttribute("operations") @Valid Operation operation, BindingResult bindingResult,Model model){

		 
		 User users = userService.getUserById(AccueilConttoller.users_id);
	        Caisse caisses2 = caisseService.getCaisseById(users.getCaisses().getCaisse_id());

        if (bindingResult.hasErrors()){
        	
   
               float total = historiqueServices.find_all_historique_By_date_today_Plancomptable(caisses2.getPlancomptables(), date1);	
  
               model.addAttribute("total_solde",total);
        	
        	
        	   Reseautransfert reseautransferts = reseautransfertService.getReseautransfertById(operation.getReseautransfert().getReseautransfertid());
        	   model.addAttribute("reseautransferts",reseautransferts);
        	   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
			   model.addAttribute("sous_agents",sous_agents); 
        	   return "mazer/creer_operation";
        }
        Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
     
        
        
        
        
        float total = historiqueServices.find_all_historique_By_date_today_Plancomptable(caisses2.getPlancomptables(), date1);	
    	if(total < operation.getOperation_montant_decaisser()) {
    		
    	
    		float total1 = historiqueServices.find_all_historique_By_date_today_Plancomptable(caisses2.getPlancomptables(), date1);	
    		  
    		model.addAttribute("total_solde",total1);
    		

     	   Reseautransfert reseautransferts = reseautransfertService.getReseautransfertById(operation.getReseautransfert().getReseautransfertid());
     	   model.addAttribute("reseautransferts",reseautransferts);
    		
    		  model.addAttribute("message_solde","Le solde de la caisse doit etre superieur au montant decaissé");
    		   model.addAttribute("sous_agents",sous_agents); 
    		 
    		   return "mazer/creer_operation";
    	}
    	
    	
    	   Operation operation2 = operation_services.find_by_sous_agent_reseautransfert_operation_numero_transfert_Sous_agent(sous_agents, operation.getReseautransfert(), operation.getOperation_numero_transfert_operateur());
           
           

    	
    	 if(operation2 != null){
    		 float total1 = historiqueServices.find_all_historique_By_date_today_Plancomptable(caisses2.getPlancomptables(), date1);	
   		  
     		model.addAttribute("total_solde",total1);

			   model.addAttribute("sous_agents",sous_agents); 

      	   Reseautransfert reseautransferts = reseautransfertService.getReseautransfertById(operation.getReseautransfert().getReseautransfertid());
      	   model.addAttribute("reseautransferts",reseautransferts);
         	
       	  model.addAttribute("message_numero_transfert","ce numero de transfert existe déjà");
       	
       	 return "mazer/creer_operation";
       	
       }
       
        
        
        //je credite le compte du reseau de transfert
    	Historique historiques = new Historique();
     	historiques.setHistorique_credit(operation.getOperation_montant_decaisser());
     	historiques.setHistorique_debit(0);
     	historiques.setHistorique_date_cre(date1);
     	historiques.setHistorique_nom("Payement retrait Numero"+operation.getOperation_numero_transfert_operateur()+" du reseau de transfert"+operation.getReseautransfert().getReseautransfertnom());
     	historiques.setPlancomptables(operation.getReseautransfert().getPlancomptables());
     	historiques.setHistorique_numero(operation.getReseautransfert().getPlancomptables().getPlancomptable_numero());
     	historiqueServices.saveHistorique(historiques);
        
        

       	
     	
      //je debite le compte de la caisse
    	Historique historique2s = new Historique();
    	historique2s.setHistorique_debit(operation.getOperation_montant_decaisser());
    	historique2s.setHistorique_credit(0);
    	historique2s.setHistorique_date_cre(date1);
    	historique2s.setHistorique_nom("Payement retrait Numero"+operation.getOperation_numero_transfert_operateur()+" du reseau de transfert"+operation.getReseautransfert().getReseautransfertnom());
    	historique2s.setPlancomptables(caisses2.getPlancomptables());
    	historique2s.setHistorique_numero(caisses2.getPlancomptables().getPlancomptable_numero());
     	historiqueServices.saveHistorique(historique2s);
        
        
        
        
        
    	
        operation_services.saveOperation(operation);



        return "redirect:/get_operations";
    }
	 
	 
	 @PostMapping("/save_approvisionnement")
	 public String saveOperation(@ModelAttribute("historiques") @Valid Historique historique, BindingResult bindingResult,Model model){

    	
	        historiqueServices.saveHistorique(historique);
	        
         User users = userService.getUserById(AccueilConttoller.users_id);
			
		    Caisse caisses2 = caisseService.getCaisseById(users.getCaisses().getCaisse_id());
		
			Historique historiques = new Historique();
	     
	     	historiques.setHistorique_debit(0);
	     	historiques.setHistorique_date_cre(date1);
	     	historiques.setHistorique_nom("Approvisionnement en caisse");
	     	historiques.setPlancomptables(caisses2.getPlancomptables());
	     	historiques.setHistorique_numero(caisses2.getPlancomptables().getPlancomptable_numero());
	   
	     	  model.addAttribute("historiques",historiques);
	     	  
	     	  
	     	  model.addAttribute("messages","Enregistrement effectuée avec succès");
	     	  
	     	  model.addAttribute("liste_historiques",historiqueServices.find_all_historique_By_Plancomptable(caisses2.getPlancomptables()));
	
	        
	     	   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
			   model.addAttribute("sous_agents",sous_agents); 
	        
	        
	        


	        return "mazer/afficher_approvisionement";
       // return "redirect:/create_approvisionnement";
    }
	 
	 
	 
	 @PostMapping("/save_decaissement")
	 public String savedecaissement(@ModelAttribute("historiques") @Valid Historique historique, BindingResult bindingResult,Model model){

    	
	   
	        
	   	 User users = userService.getUserById(AccueilConttoller.users_id);
	        Caisse caisses2 = caisseService.getCaisseById(users.getCaisses().getCaisse_id());
	        
	    
	      	   
	      Etat_caisse etat_caisses = etat_caisseServices.find_by_caisse_id_activer_Etat_caisse(caisses2);
	      
	      
	      float total = historiqueServices.find_all_historique_By_date_today_Plancomptable(caisses2.getPlancomptables(), date1);	
	    	
	      
	      if(total < historique.getHistorique_debit()){
	    		
	    	
	    		float total1 = historiqueServices.find_all_historique_By_date_today_Plancomptable(caisses2.getPlancomptables(), date1);	
	    		  
	    		model.addAttribute("total_solde",total1);
	    		
	    		  model.addAttribute("liste_historiques",historiqueServices.find_all_historique_By_Plancomptable(caisses2.getPlancomptables()));
	    			
	  	        
	    		   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
				   model.addAttribute("sous_agents",sous_agents); 
	    		
	    		  model.addAttribute("message_solde","Le solde de la caisse doit etre superieur au montant decaissé");
	    		 
	    		  
	    		   return "mazer/afficher_decaissement";
	    	}
	      
	      
	      
	      
	      
	      historiqueServices.saveHistorique(historique);
	      
	      
	      
	      
	      
	      
	        

			Historique historiques = new Historique();
	     
	     	historiques.setHistorique_credit(0);
	     	historiques.setHistorique_date_cre(date1);
	     	historiques.setHistorique_nom("Decaissement en caisse");
	     	historiques.setPlancomptables(caisses2.getPlancomptables());
	     	historiques.setHistorique_numero(caisses2.getPlancomptables().getPlancomptable_numero());
	   
	     	  model.addAttribute("historiques",historiques);
	     	  
	     	  
	     	  model.addAttribute("messages","Enregistrement effectuée avec succès");
	     	  
	     	  model.addAttribute("liste_historiques",historiqueServices.find_all_historique_By_Plancomptable(caisses2.getPlancomptables()));
	
	        
	     	    
		      float total3 = historiqueServices.find_all_historique_By_date_today_Plancomptable(caisses2.getPlancomptables(), date1);	
		    	
		      
	  		
	     
	           model.addAttribute("total_solde",total3);
	        
	        
	           
	     	   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
			   model.addAttribute("sous_agents",sous_agents); 


	        return "mazer/afficher_decaissement";
	        
	        
       // return "redirect:/create_approvisionnement";
    }
	 
	 
	 
	 
	 
 

}
