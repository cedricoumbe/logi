package com.example.demo7.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo7.model.Operation;
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



	
	

}
