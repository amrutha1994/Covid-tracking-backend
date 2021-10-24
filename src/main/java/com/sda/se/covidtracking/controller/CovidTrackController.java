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

@RestController
@RequestMapping(value = "/covidtrack")
@CrossOrigin(origins = "*")
public class CovidTrackController {

	@Autowired
	private CovidTrackService trackerService;

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	/**
	 * Method to get the word analysis report
	 * 
	 * @param inputData
	 * @return ResponseEntity
	 */
	@PostMapping(value = "/add")
	public ResponseEntity<?> addPrimaryContact(@RequestBody PrimaryContacts contactData) {

		LOG.info("******** Controller layer Entry********");

//		ResponseObject wordReport = null
		PrimaryContacts contactAdded = null;
		String wordReport = "";
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

	@PutMapping(value = "/update")
	public ResponseEntity<?> updateLocation(@RequestBody PrimaryContacts contactData) {

		LOG.info("******** Controller layer Entry********");

//	ResponseObject wordReport = null
		PrimaryContacts contactAdded = null;
		String wordReport = "";
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

	@PostMapping(value = "/getByType")
	public ResponseEntity<?> findContactPersonByType(@RequestBody ContactPersonByTypeRequest requestByType) {

		LOG.info("******** Controller layer Entry********");
		Optional<ContactPersonByTypeRequest> contactPersonByTypeRequest = Optional.of(requestByType);
//	ResponseObject wordReport = null
		List<PrimaryContacts> contactsFiltered = null;
		String wordReport = "";
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

	@GetMapping(value = "/getall/{userid}")
	public ResponseEntity<?> findAllContacts(@PathVariable("userid") String userId) {
		LOG.info("******** Controller layer Entry********");
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
