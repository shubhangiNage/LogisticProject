package com.homeloan.serviceimp;

import java.util.List;








import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.homeloan.model.CustomerDetails;
import com.homeloan.repository.HomeLoanRepository;
import com.homeloan.service.HomeLoanService;


@Service
public class HomeLoanServiceImp implements HomeLoanService {
	@Autowired HomeLoanRepository hlr;
	
	
	
	@Override
	public CustomerDetails saveCustomer(CustomerDetails cust) {
		return hlr.save(cust);}
	
	

	@Override
	public List<CustomerDetails> viewCustomers() {
		return hlr.findAll();}
	

	@Override
	public Optional<CustomerDetails> searchEmployeee(Integer cid) {
		// TODO Auto-generated method stub
		          Optional<CustomerDetails> cust= hlr.findById(cid);
		          return cust;}
	
	

	@Override
	public CustomerDetails findCust(Integer id) {
		Optional<CustomerDetails> o=hlr.findById(id);
		if(o.isPresent()) {
			return o.get();
		}
		else
		{
		return null;
		}


	}}
	
	
