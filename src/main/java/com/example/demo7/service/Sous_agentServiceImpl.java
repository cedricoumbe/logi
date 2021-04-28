package com.example.demo7.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo7.controller.User;
import com.example.demo7.model.Sous_agent;
import com.example.demo7.repository.Sous_agentRepository;



@Service
public class Sous_agentServiceImpl implements Sous_agentService{
	
	
	@Autowired
	private Sous_agentRepository sous_agentRepository;

	@Override
	public List<Sous_agent> getAllSous_agent() {
		// TODO Auto-generated method stub
		return sous_agentRepository.findAll();
	}

	@Override
	public void saveSous_agent(Sous_agent sous_agent) {
		// TODO Auto-generated method stub
		this.sous_agentRepository.save(sous_agent);
		
	}

	@Override
	public Sous_agent getSous_agentById(long id) {
	
		 Optional<Sous_agent> optional = sous_agentRepository.findById(id);
		 Sous_agent sous_agents = null;
		 if(optional.isPresent()){
			 sous_agents = optional.get();
	        }else{
	            throw new RuntimeException("Sous not found for id ::"+ id);
	        }
	        return sous_agents;
		
		
	}

	@Override
	public void deleteSous_agentById(long id) {
		// TODO Auto-generated method stub
		this.sous_agentRepository.deleteById(id);
		
	}

	
		@Override
		public Sous_agent findByUsernameOrEmail(String sous_agent_email, String sous_agent_mot_de_passe) {
			 
			
			Sous_agent sous_agent = null;
			try {
				sous_agent = sous_agentRepository.findByUsernameOrEmail(sous_agent_email,sous_agent_mot_de_passe);
			} catch (Exception e) {
				throw e;
			}
			return sous_agent;
		}

		@Override
		public Sous_agent findByUserid(User users) {
			// TODO Auto-generated method stub
			Sous_agent sous_agent = null;
			try {
				sous_agent = sous_agentRepository.findByUserid(users);
			} catch (Exception e) {
				throw e;
			}
			return sous_agent;
		}
	}


