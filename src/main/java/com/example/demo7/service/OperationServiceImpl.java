package com.example.demo7.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo7.model.Operation;
import com.example.demo7.model.Reseautransfert;
import com.example.demo7.model.Sous_agent;
import com.example.demo7.repository.OperationRepository;


@Service
public class OperationServiceImpl implements OperationService{
	
	
	  @Autowired
	    private OperationRepository operationRepository;

	@Override
	public List<Operation> getAllOperation() {
		// TODO Auto-generated method stub
		return operationRepository.findAll();
	}

	@Override
	public void saveOperation(Operation operation) {
		// TODO Auto-generated method stub
		this.operationRepository.save(operation);
		
	}

	@Override
	public Operation getOperationById(long id) {
		  Optional<Operation> optional = operationRepository.findById(id);
	        Operation operations = null;
	        if(optional.isPresent()){
	            operations = optional.get();
	        }else{
	            throw new RuntimeException("Operation not found for id ::"+ id);
	        }
	        return operations;
	}

	@Override
	public void deleteOperationById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Operation> find_all_operation_By_Sous_agent(Sous_agent sous_agent) {
		// TODO Auto-generated method stub
		
		
		
		List<Operation> list_operations = new ArrayList<Operation>();
		operationRepository.find_all_operation_By_Sous_agent(sous_agent).forEach(list_operations::add);
		
		
		
		return list_operations;
	}

	@Override
	public Operation find_by_sous_agent_reseautransfert_operation_numero_transfert_Sous_agent(String operation_numero_transfert_operateur) {
		// TODO Auto-generated method stub
	     Operation operations =	operationRepository.find_by_sous_agent_reseautransfert_operation_numero_transfert_Sous_agent1(operation_numero_transfert_operateur);
		return operations;
	}

	@Override
	public List<Operation> find_all_operation_By_date_debut_date_fin_Sous_agent(Sous_agent sous_agent, Date date_debut,
			Date date_fin) {
		List<Operation> list_operations = new ArrayList<Operation>();
		operationRepository.find_all_operation_By_date_debut_date_fin_Sous_agent(sous_agent, date_debut, date_fin).forEach(list_operations::add);
		
		
		
		return list_operations;
	}

	@Override
	public List<Operation> find_all_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(Sous_agent sous_agent,Reseautransfert reseautransfert, Date date_debut, Date date_fin) {
		
		List<Operation> list_operations = new ArrayList<Operation>();
        operationRepository.find_all_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agent,reseautransfert, date_debut, date_fin).forEach(list_operations::add);
		
		
		
		return list_operations;
	}

	@Override
	public Long find_all_sum_operation_By_date_debut_date_fin_Sous_agent(Sous_agent sous_agent,
			java.util.Date date_debut, java.util.Date date_fin) {
		
		
		
		return  operationRepository.find_all_sum_operation_By_date_debut_date_fin_Sous_agent(sous_agent, date_debut, date_fin);
	}

	@Override
	public Long find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(Sous_agent sous_agent,
			Reseautransfert reseautransfert, java.util.Date date_debut, java.util.Date date_fin) {
	
		
		Long total = operationRepository.find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agent, reseautransfert, date_debut, date_fin);
		
		
		if(total == null) {
			 
			return (long) 0;
		}else {
			
			return total;
		}
	
	}

	@Override
	public List<Operation> find_all_aujourdhui_operation_By_Sous_agent(Sous_agent sous_agent,
			java.util.Date date_debut) {
		List<Operation> list_operations = new ArrayList<Operation>();
        operationRepository.find_all_aujourdhui_operation_By_Sous_agent(sous_agent, date_debut).forEach(list_operations::add);
		
		
		
		return list_operations;
	}

	@Override
	public Long find_all_sum_aujourdhui_operation_By_Sous_agent(Sous_agent sous_agent, java.util.Date date_debut) {
		// TODO Auto-generated method stub
	
		Long total =   operationRepository.find_all_sum_aujourdhui_operation_By_Sous_agent(sous_agent,date_debut);
		
		if(total == null) {
			 
			return (long) 0;
		}else {
			
			return  operationRepository.find_all_sum_aujourdhui_operation_By_Sous_agent(sous_agent,date_debut);
		}
		
		
		
		
		
		
	}



	@Override
	public Operation find_by_sous_agent_reseautransfert_operation_numero_transfert_Sous_agent(Sous_agent sous_agent,
			Reseautransfert reseautransfert, String operation_numero_transfert_operateur) {
		// TODO Auto-generated method stub
	     Operation operations =	operationRepository.find_by_sous_agent_reseautransfert_operation_numero_transfert_Sous_agent(sous_agent, reseautransfert, operation_numero_transfert_operateur);
		return operations;
	}

	@Override
	public Long find_all_sum_reseau_trasfert_aujourdhui_operation_By_Sous_agent(Sous_agent sous_agent,
			java.util.Date date_debut, Reseautransfert reseautransfert) {
		
		Long total = operationRepository.find_all_sum_reseau_trasfert_aujourdhui_operation_By_Sous_agent(sous_agent, date_debut, reseautransfert);
		
		if(total == null) {
			 
			return (long) 0;
		}else {
			
			return operationRepository.find_all_sum_reseau_trasfert_aujourdhui_operation_By_Sous_agent(sous_agent, date_debut, reseautransfert);
		}
		
		
	}

	@Override
	public List<Operation> find_all_dernier_aujourdhui_operation_By_Sous_agent(Sous_agent sous_agent,
			java.util.Date date_debut) {
		List<Operation> list_operations = new ArrayList<Operation>();
        operationRepository.find_all_dernier_aujourdhui_operation_By_Sous_agent(sous_agent, date_debut).forEach(list_operations::add);
		
		
		
		return list_operations;
	}

	@Override
	public Long find_all_rapprocher_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(
			Sous_agent sous_agent, Reseautransfert reseautransfert, java.util.Date date_debut,
			java.util.Date date_fin) {
		// TODO Auto-generated method stub
Long total = operationRepository.find_all_rapprocher_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(sous_agent, reseautransfert, date_debut, date_fin);
		
		
		if(total == null) {
			 
			return (long) 0;
		}else {
			
			return total;
		}
	}

	@Override
	public Long find_all_rapprocher_sum_aujourdhui_operation_By_Sous_agent(Sous_agent sous_agent,
			java.util.Date date_debut) {
		// TODO Auto-generated method stub
		return  operationRepository.find_all_rapprocher_sum_aujourdhui_operation_By_Sous_agent(sous_agent,date_debut);
	}

	@Override
	public Long find_all_sum_operation_By_reseau_transfert_month_Sous_agent(Sous_agent sous_agent,int month) {
		
		
       Long total = operationRepository.find_all_sum_operation_By_reseau_transfert_month_Sous_agent(sous_agent, month);
		
		
		if(total == null) {
			 
			return (long) 0;
		}else {
			
			return total;
		}
		
	}

	


	
	

}
