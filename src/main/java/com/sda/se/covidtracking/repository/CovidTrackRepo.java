package com.sda.se.covidtracking.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sda.se.covidtracking.model.PrimaryContacts;

@Repository
public interface CovidTrackRepo extends JpaRepository<PrimaryContacts, Integer>{

	
	 List<PrimaryContacts> findAllByUserIdAndContactName(String userId,String name);
	 
	 List<PrimaryContacts> findAllByUserIdAndDate(String userId,Date date);
	 
	 List<PrimaryContacts> findAllByUserId(String userId);
}
