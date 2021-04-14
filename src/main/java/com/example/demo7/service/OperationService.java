package com.example.demo7.service;

import java.util.List;

import com.example.demo7.model.Operation;
import com.example.demo7.model.Sous_agent;



public interface OperationService {

	

	   List<Operation> getAllOperation();
	    void saveOperation(Operation operation);
	    Operation getOperationById(long id);
	    void deleteOperationById(long id);
	    

	    List<Operation> find_all_operation_By_Sous_agent(Sous_agent sous_agent);
}
