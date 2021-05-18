package com.example.demo7.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo7.model.Contrat;
import com.example.demo7.model.Employees;
import com.example.demo7.model.Reseautransfert;
import com.example.demo7.model.Sous_agent;
import com.example.demo7.service.ContratService;
import com.example.demo7.service.DepartementService;
import com.example.demo7.service.PlancomptableService;
import com.example.demo7.service.ReseautransfertService;
import com.example.demo7.service.Sous_agentService;
import com.example.demo7.service.UserService;

@Controller
public class Sous_agentController {
	

    @Autowired
    private Sous_agentService sous_agent_services;
    
    
    @Autowired
    private UserService userService;
    
    
    @Autowired
    private ContratService contratservice;
    
    @Autowired
    private PlancomptableService plancomptableService;
    
    
    @Autowired
    private ReseautransfertService reseautransfertServices;
	
	
	 @GetMapping("/get_all_sous_agent")
	    public String find_all_sous_agent(Model model){

	        model.addAttribute("list_des_sous_agents",  sous_agent_services.getAllSous_agent());


	        return "mazer/afficher_sous_agent";
	    }
	 
	 
	 @GetMapping("/ligne_sous_agent")
	    public String ligne_sous_agent_(Model model){


	        return "mazer/website";
	    } 
	 
	 
	
	 @GetMapping("/creer_contrat/sous_agent/{sous_agent_id}/reseautransfert/{reseautransfertid}")
		 public String creer_contrat_(@PathVariable("sous_agent_id") int sous_agent_id,  @PathVariable("reseautransfertid") int reseautransfertid, Model model) {
		 
		
		 Sous_agent sous_agents = sous_agent_services.getSous_agentById(sous_agent_id);
		 
		 Reseautransfert reseautransferts = reseautransfertServices.getReseautransfertById(reseautransfertid);
		 
		 Contrat contrats = new Contrat();
		 contrats.setSous_agent(sous_agents);
		 contrats.setReseautransfert(reseautransferts);
		 
		 model.addAttribute("contrats",contrats);
		 model.addAttribute("sous_agents",sous_agents);
		 model.addAttribute("reseautransferts",reseautransferts);
		 
		 model.addAttribute("list_des_contrats",  contratservice.findBysous_agent_reseau_transfert(sous_agents, reseautransferts));

	        return "mazer/creer_contrat";
	    } 
	 
	 @GetMapping("/creer_paiement/sous_agent/{sous_agent_id}/reseautransfert/{reseautransfertid}/contrat_mode_paiement/{contrat_mode_paiement_valeur}")
	 public String creer_paiement_(@PathVariable("sous_agent_id") int sous_agent_id,  @PathVariable("reseautransfertid") int reseautransfertid,  @PathVariable("contrat_mode_paiement_valeur") int contrat_mode_paiement_valeur, Model model) {
	 
	
	 Sous_agent sous_agents = sous_agent_services.getSous_agentById(sous_agent_id);
	 
	 Reseautransfert reseautransferts = reseautransfertServices.getReseautransfertById(reseautransfertid);
	 
	 Contrat contrats = new Contrat();
	 contrats.setSous_agent(sous_agents);
	 contrats.setReseautransfert(reseautransferts);
	 contrats.setContrat_mode_paiement(contrat_mode_paiement_valeur);
	 
	 model.addAttribute("contrats",contrats);
	 model.addAttribute("sous_agents",sous_agents);
	 model.addAttribute("reseautransferts",reseautransferts);
	 
	 model.addAttribute("list_des_contrats",  contratservice.findBysous_agent_reseau_transfert(sous_agents, reseautransferts));

        return "mazer/creer_paiement";
    }  
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 @PostMapping("/save_contrat")
	 public String saveContrat(@ModelAttribute("contrats") @Valid Contrat contrat, BindingResult bindingResult){


        if (bindingResult.hasErrors()) {
            return "mazer/creer_contrat";
        }
        
        
    	
        contratservice.saveContrat(contrat);


      
        

        return "redirect:/creer_contrat/sous_agent/"+contrat.getSous_agent().getSous_agent_id()+"/reseautransfert/"+contrat.getReseautransfert().getReseautransfertid();
    }
	 
	 
	 @PostMapping("/save_contrat1")
	 public String saveContrat1_(@ModelAttribute("contrats") @Valid Contrat contrat, BindingResult bindingResult){


        if (bindingResult.hasErrors()) {
            return "mazer/creer_paiement";
        }
        
        
    	
        contratservice.saveContrat(contrat);


      
        

        return "redirect:/creer_paiement/sous_agent/"+contrat.getSous_agent().getSous_agent_id()+"/reseautransfert/"+contrat.getReseautransfert().getReseautransfertid()+"/contrat_mode_paiement/"+contrat.getContrat_mode_paiement();
    }
	 
	 
	 
	 
	 
	 
	 
	 @GetMapping("/create_sous_agent")
	    public String create_new_sous_agent(Model model){
		    Sous_agent sous_agents = new Sous_agent();
		 
		  String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        String pwd = RandomStringUtils.random(5, characters );
	        System.out.println( pwd );
	        sous_agents.setSous_agent_mot_de_passe(pwd);
	        

	          model.addAttribute("liste_plancomptables",plancomptableService.getAllPlancomptable());
	          model.addAttribute("liste_users",userService.getAllUser());
		
	        model.addAttribute("sous_agents",sous_agents);
	        model.addAttribute("liste_reseautransferts",reseautransfertServices.getAllReseautransfert());
	       
	     
	      
	        
	        return  "mazer/creer_sous_agent";

	    }
 
	 
	 @PostMapping("/save_sous_agent")
		 public String saveReseautransfert(@ModelAttribute("sous_agents") @Valid Sous_agent sous_agent, BindingResult bindingResult, Model model){


	        if (bindingResult.hasErrors()) {
	            model.addAttribute("liste_plancomptables",plancomptableService.getAllPlancomptable());
		          model.addAttribute("liste_users",userService.getAllUser());
	            return "mazer/creer_sous_agent";
	            
	        }
	        
	        
	        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	    	Date date = new Date();
	    	String date1 = (format.format(date));
	    	
	        sous_agent_services.saveSous_agent(sous_agent);



	        return "redirect:/get_all_sous_agent";
	    }
	 
	 
	  @GetMapping("/update_sous_agent/{id}")
	    public String update_a_sous_agent(@PathVariable(value="id") long sous_agent_id, Model model){

		  Sous_agent sous_agents = sous_agent_services.getSous_agentById(sous_agent_id);
		  model.addAttribute("liste_reseautransferts",reseautransfertServices.getAllReseautransfert());
	        model.addAttribute("sous_agents",sous_agents);
	        model.addAttribute("liste_users",userService.getAllUser());
	        model.addAttribute("liste_plancomptables",plancomptableService.getAllPlancomptable());
	          model.addAttribute("liste_users",userService.getAllUser());
	      
	        return  "mazer/modifier_sous_agent";

	    }
	  
	  @GetMapping("/delete_sous_agent/{id}")
	    public String delete_sous_agents(@PathVariable(value="id") long id){

	         this.sous_agent_services.deleteSous_agentById(id);;
	         return "redirect:/get_all_sous_agent";

	    }
	 
	 
	  @GetMapping("/delete_contrat/sous_agent/{sous_agent_id}/reseautransfert/{reseautransfertid}")
		  
		  public String delete_contrats(@PathVariable("sous_agent_id") int sous_agent_id,  @PathVariable("reseautransfertid") int reseautransfertid, Model model) {
				   
		  
	    
	         
			 Sous_agent sous_agents = sous_agent_services.getSous_agentById(sous_agent_id);
			 
			 Reseautransfert reseautransferts = reseautransfertServices.getReseautransfertById(reseautransfertid);
			 
		     this.contratservice.deleteBysous_agent_reseau_transfert(sous_agents, reseautransferts);
	         
		     return "redirect:/creer_contrat/sous_agent/"+sous_agent_id+"/reseautransfert/"+reseautransfertid;

	    }

	

}
