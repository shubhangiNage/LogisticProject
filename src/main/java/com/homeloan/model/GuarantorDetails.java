package com.homeloan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuarantorDetails {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	  private Integer guarantorId;
//	  private String guarantorName;
//	  private String guarantorDateOfBirth;
//	  private String guarantorRelationshipwithCustomer;
//	  private Integer guarantorMobileNumber;
//	  private Integer guarantorAdharCardNo;
//	  private String guarantorMortgageDetails;
//	  private String guarantorJobDetails;
//	  private String guarantorLoaclAddress;
//	  private String guarantorPermanentAddress;
	
	
	  private String guarantorName;
	  private String guarantorEmailId;
	  private long guarantorMobileNo;
	  private String guarantorAddress;
}

