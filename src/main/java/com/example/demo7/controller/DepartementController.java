package com.example.demo7.controller;


import com.example.demo7.model.Departement;
import com.example.demo7.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DepartementController {



    @Autowired
    private DepartementService departementService;

    @GetMapping("/liste_departement")
    public String listes_departement(Model model){

        model.addAttribute("liste_departements",departementService.getAllDepartement());
        return "afficher_departement";
    }



    @GetMapping("/creer_departement")
    public String creer_departement(Model model){

        Departement departements = new Departement();
        model.addAttribute("departements",departements);
        model.addAttribute("liste_departements",departementService.getAllDepartement());
     // return "mazer/index";
   return  "creer_departement";

    }


    @PostMapping("/save_departement")
    public String saveDepartement(@ModelAttribute("departements") Departement departements){

        departementService.saveDepartement(departements);
        return "redirect:/liste_departement";
       // return "mazer/index";
    }



    @GetMapping("/modifier_departement/{id}")
    public String showFormForUpdate(@PathVariable(value="id") long id, Model model){

        Departement departements = departementService.getDepartementById(id);
        model.addAttribute("departements",departements);
        return  "modifier_departement";

    }


}
