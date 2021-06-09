package com.example.demo7.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo7.model.Operation;
import com.example.demo7.model.Rapprochement;
import com.example.demo7.model.Reseautransfert;
import com.example.demo7.model.Sous_agent;


@Repository 
public interface OperationRepository extends JpaRepository<Operation, Long> {
	
	 @Query("SELECT u FROM Operation u WHERE u.sous_agent=:sous_agent ")
	  List<Operation>  find_all_operation_By_Sous_agent(Sous_agent sous_agent);
	 
	 
	 @Query("SELECT u FROM Operation u WHERE u.sous_agent=:sous_agent and u.operation_date_cre2 = :date_debut ORDER BY u.operation_id DESC ")
	  List<Operation>  find_all_aujourdhui_operation_By_Sous_agent(Sous_agent sous_agent,java.util.Date date_debut);
	 
	 
	// @Query("SELECT u FROM Operation u WHERE u.sous_agent=:sous_agent and u.operation_date_cre2 = :date_debut ORDER BY u.operation_id DESC  limit :limit", nativeQuery = true)
	 
	
	 

	 
	 @Query(nativeQuery = true, value = "SELECT u FROM Operation u WHERE u.sous_agent=:sous_agent and u.operation_date_cre2 = :date_debut ORDER BY u.operation_id DESC LIMIT 20")
	 List<Operation> find_all_dernier_aujourdhui_operation_By_Sous_agent(@Param("sous_agent") Sous_agent sous_agent,@Param("date_debut") java.util.Date date_debut);
	 
	 
	 
	 @Query("SELECT SUM(u.operation_montant_decaisser) FROM Operation u WHERE u.sous_agent=:sous_agent and u.operation_date_cre2 = :date_debut")
	 public  Long  find_all_sum_aujourdhui_operation_By_Sous_agent(Sous_agent sous_agent,java.util.Date date_debut);
	 
	 

	 @Query("SELECT SUM(u.operation_montant_decaisser) FROM Operation u WHERE u.sous_agent=:sous_agent and u.operation_date_cre2 = :date_debut and u.operation_rapprocher=1")
	 public  Long  find_all_rapprocher_sum_aujourdhui_operation_By_Sous_agent(Sous_agent sous_agent,java.util.Date date_debut);
	 
	 
	 
	 @Query("SELECT SUM(u.operation_montant_decaisser) FROM Operation u WHERE u.sous_agent=:sous_agent and u.operation_date_cre2 = :date_debut and u.reseautransfert =:reseautransfert")
	 public  Long  find_all_sum_reseau_trasfert_aujourdhui_operation_By_Sous_agent(Sous_agent sous_agent,java.util.Date date_debut,Reseautransfert reseautransfert);
	 
	 

	 @Query("SELECT u FROM Operation u WHERE u.sous_agent=:sous_agent and operation_date_cre2 >= :date_debut and operation_date_cre2 <= :date_fin")
	  List<Operation>  find_all_operation_By_date_debut_date_fin_Sous_agent(Sous_agent sous_agent,java.util.Date date_debut, java.util.Date date_fin);
	 
	 



	

	 @Query("SELECT SUM(u.operation_montant_decaisser) FROM Operation u WHERE u.sous_agent=:sous_agent and operation_date_cre2 >= :date_debut and operation_date_cre2 <= :date_fin")
	 public  Long  find_all_sum_operation_By_date_debut_date_fin_Sous_agent(Sous_agent sous_agent,java.util.Date date_debut, java.util.Date date_fin);
	 
	 

	 
	 
	 
	 

	 @Query("SELECT u FROM Operation u WHERE u.sous_agent=:sous_agent and operation_date_cre2 >= :date_debut and operation_date_cre2 <= :date_fin  and u.reseautransfert=:reseautransfert")
	  List<Operation>  find_all_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(Sous_agent sous_agent,Reseautransfert reseautransfert,java.util.Date date_debut, java.util.Date date_fin);
	 
	 
	 
	 
	 
	 
	 

	 @Query("SELECT SUM(u.operation_montant_decaisser) FROM Operation u WHERE u.sous_agent=:sous_agent and operation_date_cre2 >= :date_debut and operation_date_cre2 <= :date_fin  and u.reseautransfert=:reseautransfert")
	 public  Long   find_all_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(Sous_agent sous_agent,Reseautransfert reseautransfert,java.util.Date date_debut, java.util.Date date_fin);
	 
	 
	 
	 
	 

	 @Query("SELECT SUM(u.operation_montant_decaisser) FROM Operation u WHERE u.sous_agent=:sous_agent and MONTH(operation_date_cre2) = :month")
	 public  Long   find_all_sum_operation_By_reseau_transfert_month_Sous_agent(Sous_agent sous_agent,int month);
	 
	 
	 
	 
	 
	 
	 

	 @Query("SELECT SUM(u.operation_montant_decaisser) FROM Operation u WHERE u.sous_agent=:sous_agent and operation_date_cre2 >= :date_debut and operation_date_cre2 <= :date_fin  and u.reseautransfert=:reseautransfert and u.operation_rapprocher=1")
	 public  Long   find_all_rapprocher_sum_operation_By_reseau_transfert_date_debut_date_fin_Sous_agent(Sous_agent sous_agent,Reseautransfert reseautransfert,java.util.Date date_debut, java.util.Date date_fin);
	 
	 
	 
	 
	 
	 @Query("SELECT u FROM Operation u WHERE u.operation_numero_transfert_operateur = ?1")
	 Operation find_by_sous_agent_reseautransfert_operation_numero_transfert_Sous_agent1(String operation_numero_transfert_operateur);
	 
	 
	 
	 @Query("SELECT u FROM Operation u WHERE u.sous_agent = ?1 and u.reseautransfert = ?2 and u.operation_numero_transfert_operateur = ?3")
     Operation find_by_sous_agent_reseautransfert_operation_numero_transfert_Sous_agent(Sous_agent sous_agent,Reseautransfert reseautransfert,String operation_numero_transfert_operateur);
	 
	 

}
