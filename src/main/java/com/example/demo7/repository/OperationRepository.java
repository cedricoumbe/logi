package com.example.demo7.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo7.model.Operation;
import com.example.demo7.model.Sous_agent;


@Repository 
public interface OperationRepository extends JpaRepository<Operation, Long> {
	
	 @Query("SELECT u FROM Operation u WHERE u.sous_agent=:sous_agent")
	  List<Operation>  find_all_operation_By_Sous_agent(Sous_agent sous_agent);

}
