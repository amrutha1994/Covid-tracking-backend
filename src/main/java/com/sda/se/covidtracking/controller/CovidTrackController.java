package com.sda.se.covidtracking.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sda.se.covidtracking.model.ContactPersonByTypeRequest;
import com.sda.se.covidtracking.model.PrimaryContacts;
import com.sda.se.covidtracking.service.CovidTrackService;

/**
 * Controller for Covid tracker
 * @author amrutha
 *
 */

@RestController
@RequestMapping(value = "/covidtrack")
@CrossOrigin(origins = "*")
public class CovidTrackController {

	@Autowired
	private CovidTrackService trackerService;

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	/**
	 * Method to add primary contacts
	 * 
	 * @param PrimaryContacts
	 * @return ResponseEntity
	 */
	@PostMapping(value = "/add")
	public ResponseEntity<?> addPrimaryContact(@RequestBody PrimaryContacts contactData) {

		LOG.info("******** Controller Add Primary contact layer Entry********");

		PrimaryContacts contactAdded = null;
		ResponseEntity<?> responseEntity = null;
		try {
			contactAdded = trackerService.addPrimaryContact(contactData);
			if (contactAdded != null) {
				responseEntity = new ResponseEntity<>(contactAdded, HttpStatus.OK);
			} else {
				responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		} catch (Exception ex) {
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return responseEntity;

	}

	/**
	 * Method to add primary contacts location
	 * 
	 * @param PrimaryContacts
	 * @return ResponseEntity
	 */
	@PutMapping(value = "/update")
	public ResponseEntity<?> updateLocation(@RequestBody PrimaryContacts contactData) {

		LOG.info("******** Controller updateLocation layer Entry********");

		PrimaryContacts contactAdded = null;
		ResponseEntity<?> responseEntity = null;
		try {
			contactAdded = trackerService.updateLocation(contactData);
			if (contactAdded != null) {
				responseEntity = new ResponseEntity<>(contactAdded, HttpStatus.OK);
			} else {
				responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		} catch (Exception ex) {
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return responseEntity;
	}
	
	/**
	 * Method to find contact person by type
	 * 
	 * @param ContactPersonByTypeRequest
	 * @return ResponseEntity
	 */
	@PostMapping(value = "/getByType")
	public ResponseEntity<?> findContactPersonByType(@RequestBody ContactPersonByTypeRequest requestByType) {

		LOG.info("******** Controller layer Entry********");
		Optional<ContactPersonByTypeRequest> contactPersonByTypeRequest = Optional.of(requestByType);
		List<PrimaryContacts> contactsFiltered = null;
		ResponseEntity<?> responseEntity = null;
		try {
			if (contactPersonByTypeRequest.isPresent()) {
				contactsFiltered = trackerService.findByType(requestByType);
			}
			if (contactsFiltered != null) {
				responseEntity = new ResponseEntity<>(contactsFiltered, HttpStatus.OK);
			} else {
				responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		} catch (Exception ex) {
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return responseEntity;

	}

	/**
	 * Method to get all contacts
	 * 
	 * @param ContactPersonByTypeRequest
	 * @return ResponseEntity
	 */
	@GetMapping(value = "/getall/{userid}")
	public ResponseEntity<?> findAllContacts(@PathVariable("userid") String userId) {
		LOG.info("******** Controller findAllContacts layer Entry********");
		List<PrimaryContacts> allContacts = null;
		ResponseEntity<?> responseEntity = null;
		try {
			if (userId != null) {
				allContacts = trackerService.findAllContacts(userId);
			}
			if (allContacts != null) {
				responseEntity = new ResponseEntity<>(allContacts, HttpStatus.OK);
			} else {
				responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		} catch (Exception ex) {
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return responseEntity;

	}

}
