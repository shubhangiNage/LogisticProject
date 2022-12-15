package com.homeloan.service;


import java.util.List;
import java.util.Optional;

import com.homeloan.model.CustomerDetails;
import com.homeloan.model.Enquiry;

public interface EnquiryService {

	public Enquiry saveEnquiry(Enquiry enq);
	public List<Enquiry> viewEnquiry();
	public Integer checkCibil(Integer id);
	public Enquiry findEnq(int id);
}
