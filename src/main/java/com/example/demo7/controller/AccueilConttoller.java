package com.example.demo7.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo7.model.Item;
import com.example.demo7.model.Mail;
import com.example.demo7.model.PasswordResetToken;
import com.example.demo7.model.Reseautransfert;
import com.example.demo7.model.Sous_agent;
import com.example.demo7.repository.PasswordResetTokenRepository;
import com.example.demo7.service.EmailService;
import com.example.demo7.service.OperationService;
import com.example.demo7.service.ReseautransfertService;
import com.example.demo7.service.Sous_agentService;
import com.example.demo7.service.UserService;

@Controller
public class AccueilConttoller {
	

	 public static long sous_agent_id=0 ;
	 public static long users_id=0 ;
	 
	   
	  
			// create a java calendar instance
			  Calendar calendar = Calendar.getInstance();

			  // get a java date (java.util.Date) from the Calendar instance.
			  // this java date will represent the current date, or "now".
			  java.util.Date currentDate = calendar.getTime();

			  // now, create a java.sql.Date from the java.util.Date
			  java.sql.Date date1 = new java.sql.Date(currentDate.getTime());
	

    @Autowired
    private Sous_agentService sous_agent_services;
    

    @Autowired
    private UserService userService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired 
    private EmailService emailService;
    
    @Autowired private PasswordResetTokenRepository tokenRepository;
    
    @Autowired
    private ReseautransfertService reseautransfertService;
    
    @Autowired
    private OperationService operation_services;
    
    
  
    
    @GetMapping("/accueil_admin")
    public String affiche_page_accueil2(Model model){
    	
    	
    	
    	  model.addAttribute("sous_agents",sous_agent_services.getAllSous_agent()); 
    	  
    	  
    	  HashMap m = new HashMap () ; 
		   
			for(Sous_agent sous_agents:sous_agent_services.getAllSous_agent()) {
				
				
				Long total_encaissement = operation_services.find_all_sum_aujourdhui_operation_By_Sous_agent(sous_agents, date1);
				
			  
			    
			    
			    
		
				
				m.put(sous_agents, total_encaissement);
				
			}
		   
			User users = userService.getUserById(AccueilConttoller.users_id);
			
			model.addAttribute("nom_user",users.getUsernom());  
    	  
    	  
			model.addAttribute("map_sous_agent",m);  
			
			
			   Map<String, Integer> graphData2 = new TreeMap<>();
			   
				for(Sous_agent sous_agents:sous_agent_services.getAllSous_agent()) {
					
					
					Long total_encaissement = operation_services.find_all_sum_aujourdhui_operation_By_Sous_agent(sous_agents, date1);
					
					
						   graphData2.put(sous_agents.getSous_agent_nom(), total_encaissement.intValue());
						
						
						
					}
			
			
			
			
			
			
			
			     model.addAttribute("chartData2", graphData2);
				   	
			
			
			

        return "mazer/accueil2";
    }
    
    
	
    @GetMapping("/login")
    public String affiche_page_login(Model model){

    	   Sous_agent sous_agents = new Sous_agent();
	        model.addAttribute("sous_agents",sous_agents);
        return "mazer/login";
    }
  
    
    @GetMapping("/forgot_password")
    public String forgot_password_(Model model){
    	
    	 User users = new User();
    	  
   	  model.addAttribute("users",users);

     return "mazer/forgot";
   
        
    }
  
    
  
    
    
    
	 @GetMapping("/accueil")
	    public String affiche_page_accueil(Model model){

		   model.addAttribute("liste_reseautransferts",reseautransfertService.getAllReseautransfert());
		   Sous_agent sous_agents = sous_agent_services.getSous_agentById(AccueilConttoller.sous_agent_id);
		   
		   
		   HashMap m = new HashMap () ; 
		   
			for(Reseautransfert list_reseau_transfert:sous_agents.getReseautransferts()) {
				
				
				Long total_encaissement = operation_services.find_all_sum_reseau_trasfert_aujourdhui_operation_By_Sous_agent(sous_agents, date1, list_reseau_transfert);
				
				
				
				m.put(list_reseau_transfert, total_encaissement);
				
			}
		   
		   
		   
		   
		      model.addAttribute("liste_operations",operation_services.find_all_aujourdhui_operation_By_Sous_agent(sous_agents, date1));
	    				   
		   
			  model.addAttribute("map_reseau",m);  
		   
			    model.addAttribute("sous_agent_exist",AccueilConttoller.sous_agent_id);
		   
		   model.addAttribute("sous_agents",sous_agents); 
		   
		   
		   
		   Map<String, Integer> graphData = new TreeMap<>();
		   
		   Map<String, Integer> graphData2 = new TreeMap<>();
		   
		  for(Reseautransfert list_reseau_transfert:sous_agents.getReseautransferts()) {
				
				
				Long total_encaissement = operation_services.find_all_sum_reseau_trasfert_aujourdhui_operation_By_Sous_agent(sous_agents, date1, list_reseau_transfert);
				
				   graphData2.put(list_reseau_transfert.getReseautransfertnom(), total_encaissement.intValue());
				
				
				
			}
		  String month = "";
		   
		   for(int i=1; i<=12; i++) {
			   
			   
			   
				Long total_encaissement = operation_services.find_all_sum_operation_By_reseau_transfert_month_Sous_agent(sous_agents, i);
				   
				/*if(i==1) {month="Jan"; }
				if(i==2) {month="Fev"; }
				if(i==3) {month="Mar"; }
				if(i==4) {month="Av"; }
				if(i==5) {month="Mai"; }
				if(i==6) {month="Jui"; }
				if(i==7) {month="Juil"; }
				if(i==8) {month="Aout"; }
				if(i==9) {month="sep"; }
				if(i==10) {month="Oct"; }
				if(i==11) {month="Nov"; }
				if(i==12) {month="Dec"; }*/
				
				
				
				graphData.put(i+"", total_encaissement.intValue());
			   
		   }
		   
		   
		   
		   
		   
		   
		   
	     
	        model.addAttribute("chartData", graphData);
	
		   
	        model.addAttribute("chartData2", graphData2);
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
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
	 
	 
	
	 @PostMapping("/recuperation_password")
	    public String recuperation_password_(RedirectAttributes attributes,@ModelAttribute("users") @Valid User users, BindingResult bindingResult,Model model,HttpServletRequest request){
	    	
			
	    	
	    	
		    User users1 = userService.getUserByUserName(users.getUsername());
    	 if(users1==null) {
 	    	
    		 String mess= "cet email n'est pas dans notre systeme";
    		 
    	    // System.out.println(mess+"sssssssss");
    		 
    		   model.addAttribute("message1", mess);
	    	 return "mazer/forgot";
	    	
	    }
	    	
	    	

	    
	   
	  	
    	users.setEnabled(true);
    	users.setPassword(users1.getPassword());
    	users.setUsernom(users1.getUsernom());
    	users.setRoles(users1.getRoles());
    	

	    
	    	
	    	
	   

	     
	    	
	    	 
	         
	    	  PasswordResetToken token = new PasswordResetToken();
	          token.setToken(UUID.randomUUID().toString());
	          token.setUser(users1);
	          token.setExpiryDate(3000);
	          tokenRepository.save(token);

	          Mail mail = new Mail();
	          mail.setFrom("no-reply@creditpopulairecm.com");
	          mail.setTo(users.getUsername());
	          mail.setSubject("Reinitialisation du mot de passe");

	          Map<String, Object> model2 = new HashMap<>();
	          model2.put("token", token);
	          model2.put("user", users);
	          model2.put("signature", "http://creditpopulairecm.com");
	          String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	          model2.put("resetUrl", url + "/registration/"+ token.getToken());
	          mail.setModel(model2);
	          emailService.sendEmail(mail);
	   	
	    
	          
	          model.addAttribute("message2","Vous avez recu un mail. connecter vous et reinitialiser votre mot de passe");
	       
	        
	     	 return "mazer/forgot";
	       
	    }
	    
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
