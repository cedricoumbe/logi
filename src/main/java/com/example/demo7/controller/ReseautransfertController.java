package com.example.demo7.controller;


import com.example.demo7.model.Departement;
import com.example.demo7.model.Operation;
import com.example.demo7.model.Reseautransfert;
import com.example.demo7.model.Sous_agent;
import com.example.demo7.service.PlancomptableService;
import com.example.demo7.service.ReseautransfertService;
import com.example.demo7.service.Sous_agentService;
import com.example.demo7.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

/**
 * voici la classe qui peremet de gerer les reseaux de transfert
 * @author cedric   
 *
 */
@Controller
public class ReseautransfertController {

	@Autowired

	ServletContext context;

    @Autowired
    private ReseautransfertService reseautransfertService;
    
    
    @Autowired
    private UserService userService;
    
    


    @Autowired
    private PlancomptableService plancomptableService;
    
    

    @Autowired
    private Sous_agentService sous_agent_services;
   
   // public  String absolutePath = context.getRealPath("resources/assets/images/logo");
   
    private static final String UPLOAD_DIRECTORY ="/images";  
    
    
    
    
    /**
     * 
     * @param model
     * @return
     */
    @GetMapping("/get_reseautransfert")
    public String listes_reseautransfert(Model model){
    	
    	
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String name = authentication.getName();	
   	User users = userService.getUserByUserName(name);
   	Sous_agent sous_agent2s =null;
 	Iterator<Sous_agent> list_users = users.getSous_agents().iterator();
   	
   	while(list_users.hasNext()){
   		
   		sous_agent2s = list_users.next();
   		break;
   		
   	}
   	
   	
   	
   	/*
   	
   	
   	
   	List<Sous_agent> list_sous_agents = sous_agent_services.getAllSous_agent();
   	
   	for(Sous_agent list_sous_agent:list_sous_agents) {
   		
   		for(User list_user:list_sous_agent.getUsers()) {
   			
   		        if(list_user.getUsername().equals(users.getUsername())) {
   		        	
   		        	sous_agent2s =  list_sous_agent;
   		        	
   		        }
   		}
   		
   		
   		
   	}
   	*/
   	
   	
   	
//System.out.println(users.getSous_agents().toArray().toString());
   	
   	
   	
   	
    	
//Sous_agent sous_agents = sous_agent_services.findByUserid(users.getId());
    	
    	
    	//System.out.println(sous_agents);
    	 model.addAttribute("name1",name);
    	
    if(sous_agent2s != null) {
    		
    		model.addAttribute("sous_agents",sous_agent2s);

           AccueilConttoller.sous_agent_id = sous_agent2s.getSous_agent_id();
       
       
           model.addAttribute("sous_agent_exist",AccueilConttoller.sous_agent_id);
       
    	   return "mazer/accueil";
    	   
    	   
    	   
    		
    	}
    	
    	

    	
    	
    	DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    	Date date = new Date();
    	String date1 = (format.format(date));
    
    	
    	
    	 Reseautransfert reseautransferts = new Reseautransfert();
    	 reseautransferts.setReseautransfertdatecre(date1);
    	  model.addAttribute("reseautransferts",reseautransferts);
    	  
    
    	  
    	

          model.addAttribute("liste_reseautransferts",reseautransfertService.getAllReseautransfert());
     
          model.addAttribute("liste_plancomptables",plancomptableService.getAllPlancomptable());
        return "mazer/afficher_reseautransfert";
        
        
        
        
    }





    @PostMapping("/save_reseautransfert")
    public String saveReseautransfert(@RequestParam("file") MultipartFile file, RedirectAttributes attributes,@ModelAttribute("reseautransferts") @Valid Reseautransfert reseautransferts, BindingResult bindingResult,Model model){

    	if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");

            model.addAttribute("liste_plancomptables",plancomptableService.getAllPlancomptable());
             return "mazer/afficher_reseautransfert";
          }
    	
    	
    	if (bindingResult.hasErrors()) {
    		 return "mazer/afficher_reseautransfert";
        }
    	
     
        
        
        
        try {
        	String absolutePath = "/photos/";
        

        	   byte[] bytes = file.getBytes();
               Path path = Paths.get(absolutePath + file.getOriginalFilename());
               Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        
    	
    	reseautransferts.setReseautransfertnomimage(file.getOriginalFilename());
        reseautransfertService.saveReseautransfert(reseautransferts);
        return "redirect:/get_reseautransfert";
       
    }
    
    

    @PostMapping("/update_reseautransfert")
    public String updateReseautransfert(RedirectAttributes attributes,@ModelAttribute("reseautransferts") @Valid Reseautransfert reseautransferts, BindingResult bindingResult){

    	
    	
    	if (bindingResult.hasErrors()) {
    		 return "mazer/afficher_reseautransfert";
        }
    	
     
        
        
        reseautransfertService.saveReseautransfert(reseautransferts);
        return "redirect:/get_reseautransfert";
       
    } 
    
    
    
    
    
    
    
    



    @GetMapping("/modifier_reseautransfert/{id}")
    public String showFormForUpdate(@PathVariable(value="id") long id, Model model){
    	
    	
    	DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    	Date date = new Date();
    	String date1 = (format.format(date));

        Reseautransfert reseautransferts = reseautransfertService.getReseautransfertById(id);
        reseautransferts.setReseautransfertdatemod(date1);
        reseautransferts.setReseautransfertdatecre(reseautransferts.getReseautransfertdatecre());
        model.addAttribute("reseautransferts",reseautransferts);
        model.addAttribute("liste_reseautransferts",reseautransfertService.getAllReseautransfert());
        model.addAttribute("liste_plancomptables",plancomptableService.getAllPlancomptable());
        return  "mazer/modifier_reseautransfert";

    }
    
    
    @GetMapping("/supprimer_reseautransfert/{id}")
    public String deleteEmployee(@PathVariable(value="id") long id){

         this.reseautransfertService.deleteReseautransfertById(id);
         return "redirect:/get_reseautransfert";

    }


}
