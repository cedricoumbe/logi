package com.example.demo7.controller;


import com.example.demo7.model.Departement;
import com.example.demo7.model.Employees;
import com.example.demo7.service.CompetenceService;
import com.example.demo7.service.DepartementService;
import com.example.demo7.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartementService departementService;

    @Autowired
    private CompetenceService competenceService;

    @GetMapping("/ss")
    public String viewHomePage(Model model){

        model.addAttribute("listEmployees",employeeService.getAllEmployee());


        return "index";
    }



    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){

        Employees employees = new Employees();
        model.addAttribute("employees",employees);

       
        model.addAttribute("liste_departements",departementService.getAllDepartement());
        model.addAttribute("liste_competences",competenceService.getAllCompetence());
        return  "new_employee";

    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employees") Employees employees, @Valid Employees employees_valid, BindingResult bindingResult){



        if (bindingResult.hasErrors()) {
            return "new_employee";
        }
        employeeService.saveEmployee(employees);



        return "redirect:/ss";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value="id") long id, Model model){

        Employees employees = employeeService.getEmployeeById(id);
        model.addAttribute("liste_departements",departementService.getAllDepartement());
        model.addAttribute("employees",employees);
        model.addAttribute("liste_competences",competenceService.getAllCompetence());
        return  "update_employee";

    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value="id") long id){

         this.employeeService.deleteEmployeeById(id);
        return "redirect:/ss";

    }
}
