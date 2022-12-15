package com.homeloan.serviceimp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeloan.model.CustomerDetails;
import com.homeloan.model.Enquiry;
import com.homeloan.repository.EnquiryRepository;
import com.homeloan.service.EnquiryService;

@Service
public class EnqiryServiceImp implements EnquiryService {

	@Autowired EnquiryRepository er;

	@Override
	public Enquiry saveEnquiry(Enquiry enq) {
		return er.save(enq);
	}

	@Override
	public List<Enquiry> viewEnquiry() {
		return er.findAll();
	}
	
	@Override
	public Integer checkCibil(Integer id) {
		
		Integer min=300;
		Integer max=900;
		Integer b=(int) (Math.random()*(max-min+1)+min);
		if(b>750) {
			
			Optional<Enquiry> data=er.findById(id);
			if(data.isPresent()) {
				
				Enquiry enq=data.get();
				enq.setCibil(b);
				enq.setEligiblity("Eligible");
				er.save(enq);
			}
			return 780;
		}
		else {
			Optional<Enquiry> data=er.findById(id);
			if(data.isPresent()) {
				Enquiry enq=data.get();
				enq.setCibil(b);
				enq.setEligiblity("Not Eligible");
				er.save(enq);
				
			}
		}
		return 650;}

	@Override
	public Enquiry findEnq(int id) {
		Optional<Enquiry> o= er.findById(id);
		if(o.isPresent()) {
			return o.get();
			
		}
		else {
		return null;
		}
	}

	}
