package com.sda.se.covidtracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.se.covidtracking.model.ContactPersonByTypeRequest;
import com.sda.se.covidtracking.model.PrimaryContacts;
import com.sda.se.covidtracking.repository.CovidTrackRepo;

@Service
public class CovidTrackService {

	@Autowired
	CovidTrackRepo covidTrackerRepo;

	public PrimaryContacts addPrimaryContact(PrimaryContacts contact) {
		return covidTrackerRepo.save(contact);
	}

	public PrimaryContacts updateLocation(PrimaryContacts contact) {
		PrimaryContacts contactPerson = covidTrackerRepo.getById(contact.getContactId());
		contactPerson.setLocation(contact.getLocation());
		return covidTrackerRepo.save(contactPerson);
	}

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

	public List<PrimaryContacts> findAllContacts(String userId) {
		return covidTrackerRepo.findAllByUserId(userId);

	}
}
