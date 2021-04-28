package com.example.demo7.service;

import java.util.List;

import com.example.demo7.controller.User;
import com.example.demo7.model.Employees;
import com.example.demo7.model.Sous_agent;

public interface Sous_agentService {



    List<Sous_agent> getAllSous_agent();
    void saveSous_agent(Sous_agent sous_agent);
    Sous_agent getSous_agentById(long id);
    void deleteSous_agentById(long id);
    
    Sous_agent findByUsernameOrEmail(String sous_agent_email,String sous_agent_mot_de_passe);
    
    Sous_agent findByUserid(User users);
    
    
}
