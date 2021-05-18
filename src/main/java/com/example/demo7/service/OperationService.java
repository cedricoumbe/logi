package com.example.demo7.service;

import java.sql.Date;
import java.util.List;

import com.example.demo7.model.Operation;
import com.example.demo7.model.Reseautransfert;
import com.example.demo7.model.Sous_agent;



public interface OperationService {

	

	   List<Operation> getAllOperation();
	    void saveOperation(Operation operation);
	    Operation getOperationById(long id);
	    void deleteOperationById(long id);
	    

	    List<Operation> find_all_operation_By_Sous_agent(Sous_agent sous_agent);
	    
	    List<Operation>  find_all_operation_By_date_debut_date_fin_Sous_agent(Sous_agent sous_agent,Date date, Date date2);
	    List<Operation>   find_all_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(Sous_agent sous_agent,Reseautransfert reseautransfert,Date datedebut, Date datefin);
	    
	    Operation  find_by_sous_agent_reseautransfert_operation_numero_transfert_Sous_agent(String operation_numero_transfert_operateur);
	    
	    List<Operation>  find_all_aujourdhui_operation_By_Sous_agent(Sous_agent sous_agent,java.util.Date date_debut);
	    
		  Long  find_all_sum_operation_By_date_debut_date_fin_Sous_agent(Sous_agent sous_agent,java.util.Date date_debut, java.util.Date date_fin);
			 Long find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(Sous_agent sous_agent,Reseautransfert reseautransfert,java.util.Date date_debut, java.util.Date date_fin);
			 public  Long  find_all_sum_aujourdhui_operation_By_Sous_agent(Sous_agent sous_agent,java.util.Date date_debut);
	   // Operation  find_by_sous_agent_reseautransfert_operation_numero_transfert_Sous_agent(Sous_agent sous_agent,Reseautransfert reseautransfert,String operation_numero_transfert_operateur);
	    
}
