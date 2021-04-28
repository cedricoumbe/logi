package com.example.demo7.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
import com.example.demo7.model.Sous_agent;
import com.example.demo7.repository.PasswordResetTokenRepository;
import com.example.demo7.service.EmailService;
import com.example.demo7.service.ReseautransfertService;
import com.example.demo7.service.Sous_agentService;
import com.example.demo7.service.UserService;

@Controller
public class AccueilConttoller {
	

	 public static long sous_agent_id=0 ;

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
