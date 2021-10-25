package com.sda.se.covidtracking.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for primary contacts
 * 
 * @author amru1
 *
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PrimaryContacts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int contactId;

	@NonNull
	private String userId;

	@NonNull
	private String contactName;

	@Nullable
	private String location;

	@NonNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date date;
}
