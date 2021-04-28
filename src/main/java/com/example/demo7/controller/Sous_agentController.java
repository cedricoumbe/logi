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

import com.example.demo7.model.Employees;
import com.example.demo7.model.Reseautransfert;
import com.example.demo7.model.Sous_agent;
import com.example.demo7.service.DepartementService;
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
    private ReseautransfertService reseautransfertServices;
	
	
	 @GetMapping("/get_all_sous_agent")
	    public String find_all_sous_agent(Model model){

	        model.addAttribute("list_des_sous_agents",  sous_agent_services.getAllSous_agent());


	        return "mazer/afficher_sous_agent";
	    }
	 
	 @GetMapping("/create_sous_agent")
	    public String create_new_sous_agent(Model model){
		    Sous_agent sous_agents = new Sous_agent();
		 
		  String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        String pwd = RandomStringUtils.random(5, characters );
	        System.out.println( pwd );
	        sous_agents.setSous_agent_mot_de_passe(pwd);

		
	        model.addAttribute("sous_agents",sous_agents);
	        model.addAttribute("liste_reseautransferts",reseautransfertServices.getAllReseautransfert());
	        model.addAttribute("liste_users",userService.getAllUser());
	     
	      
	        
	        return  "mazer/creer_sous_agent";

	    }
 
	 
	 @PostMapping("/save_sous_agent")
		 public String saveReseautransfert(@ModelAttribute("sous_agents") @Valid Sous_agent sous_agent, BindingResult bindingResult){


	        if (bindingResult.hasErrors()) {
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
	      
	        return  "mazer/modifier_sous_agent";

	    }
	  
	  @GetMapping("/delete_sous_agent/{id}")
	    public String delete_sous_agents(@PathVariable(value="id") long id){

	         this.sous_agent_services.deleteSous_agentById(id);;
	         return "redirect:/get_all_sous_agent";

	    }
	 
	 
	 

	

}
