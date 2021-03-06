package com.sda.se.covidtracking.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request class for ContactPersonByTypeRequest
 * 
 * @author amru1
 *
 */

@Data
@NoArgsConstructor
public class ContactPersonByTypeRequest {

	private String userId;
	private String type;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date searchDate;
	private String searchPerson;
}
