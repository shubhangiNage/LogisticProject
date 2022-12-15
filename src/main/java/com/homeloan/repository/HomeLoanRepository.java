package com.homeloan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;


import com.homeloan.model.CustomerDetails;
import com.homeloan.model.Enquiry;



@Repository
public interface HomeLoanRepository extends JpaRepository<CustomerDetails, Integer>{

	public Optional<CustomerDetails> findById(CustomerDetails cust);
	public Optional<CustomerDetails> findById(Integer id);
}