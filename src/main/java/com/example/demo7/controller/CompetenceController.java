package com.example.demo7.controller;


import com.example.demo7.model.Competence;
import com.example.demo7.service.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CompetenceController {



    @Autowired
    private CompetenceService competenceService;

    @GetMapping("/liste_competence")
    public String listes_competence(Model model){

        model.addAttribute("liste_competences",competenceService.getAllCompetence());
        return "afficher_competence";
    }



    @GetMapping("/creer_competence")
    public String creer_competence(Model model){

        Competence competences = new Competence();
        model.addAttribute("competences",competences);
        return  "creer_competence";

    }


    @PostMapping("/save_competence")
    public String saveCompetence(@ModelAttribute("competences") Competence competences){

        competenceService.saveCompetence(competences);
        return "redirect:/liste_competence";
    }



    @GetMapping("/modifier_competence/{id}")
    public String showFormForUpdate(@PathVariable(value="id") long id, Model model){

        Competence competences = competenceService.getCompetenceById(id);
        model.addAttribute("competences",competences);
        return  "modifier_competence";

    }


}
