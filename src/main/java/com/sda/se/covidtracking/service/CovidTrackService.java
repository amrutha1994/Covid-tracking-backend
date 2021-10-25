package com.sda.se.covidtracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.se.covidtracking.model.ContactPersonByTypeRequest;
import com.sda.se.covidtracking.model.PrimaryContacts;
import com.sda.se.covidtracking.repository.CovidTrackRepo;

/**
 * Service layer for Covid tracker
 * 
 * @author amrutha
 *
 */

@Service
public class CovidTrackService {

	@Autowired
	CovidTrackRepo covidTrackerRepo;

	/**
	 * Method to add primary contact
	 * 
	 * @param contact
	 * @return
	 */
	public PrimaryContacts addPrimaryContact(PrimaryContacts contact) {
		return covidTrackerRepo.save(contact);
	}
	
	/**
	 * Method to update location
	 * 
	 * @param contact
	 * @return
	 */
	public PrimaryContacts updateLocation(PrimaryContacts contact) {
		PrimaryContacts contactPerson = covidTrackerRepo.getById(contact.getContactId());
		contactPerson.setLocation(contact.getLocation());
		return covidTrackerRepo.save(contactPerson);
	}

	/**
	 * Method to find contact person by type
	 * 
	 * @param ContactPersonByTypeRequest
	 * @return
	 */
	
	public List<PrimaryContacts> findByType(ContactPersonByTypeRequest requestParams) {
		List<PrimaryContacts> contactsFound = null;
		switch (requestParams.getType()) {
		case "date": {
			contactsFound = covidTrackerRepo.findAllByUserIdAndDate(requestParams.getUserId(),
					requestParams.getSearchDate());
			break;
		}
		case "name": {
			contactsFound = covidTrackerRepo.findAllByUserIdAndContactName(requestParams.getUserId(),
					requestParams.getSearchPerson());
			break;
		}
		}
		return contactsFound;
	}

	/**
	 * Method to find all contact persons
	 * 
	 * @param userId
	 * @return
	 */
	public List<PrimaryContacts> findAllContacts(String userId) {
		return covidTrackerRepo.findAllByUserId(userId);

	}
}
