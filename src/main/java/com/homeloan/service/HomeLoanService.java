package com.homeloan.service;

import java.util.List;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.homeloan.model.CustomerAllDocument;
import com.homeloan.model.CustomerDetails;
import com.homeloan.model.EmailSender;





public interface HomeLoanService {

	
	public CustomerDetails saveCustomer(CustomerDetails cust);
	public List<CustomerDetails> viewCustomers();
	public Optional<CustomerDetails> searchEmployeee(Integer cid);
	public CustomerDetails findCust(Integer id);
	
	

}
