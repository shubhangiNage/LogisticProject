package com.homeloan.controller;

import java.io.IOException;





import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.homeloan.model.CustomerAllDocument;
import com.homeloan.model.CustomerBankAccountDetails;
import com.homeloan.model.CustomerDetails;
import com.homeloan.model.CustomerLocalAddress;
import com.homeloan.model.CustomerPermanentAddress;
import com.homeloan.model.Enquiry;
import com.homeloan.model.GuarantorDetails;
import com.homeloan.service.HomeLoanService;




@CrossOrigin("*")
@RestController
public class HomeLoanController {

	@Autowired HomeLoanService hls;

	@PostMapping(value ="/setCustomerAllDetail",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String saveCustomer(@RequestPart(value = "panCopy") MultipartFile doc1,
			@RequestPart(value = "uidCopy") MultipartFile doc2,
			@RequestPart(value = "bankPassBookCopy") MultipartFile doc3,
			@RequestPart(value = "photo") MultipartFile doc4,
			@RequestPart(value = "signature") MultipartFile doc5,
			@RequestPart(value = "cancelledCheck") MultipartFile doc6,
			@RequestPart(value = "salarySlips") MultipartFile doc7,
			@RequestPart(value = "sanctionLetter") MultipartFile doc8,
			@RequestPart(value = "document1") String document1) throws IOException
	
	{
		ObjectMapper om=new ObjectMapper();
		CustomerDetails c=om.readValue(document1, CustomerDetails.class);
        
        CustomerAllDocument cad=new CustomerAllDocument();
        cad.setPanCopy(doc1.getBytes());
        cad.setUidCopy(doc2.getBytes());
        cad.setBankPassBookCopy(doc3.getBytes());
        cad.setPhoto(doc4.getBytes());
        cad.setSignature(doc5.getBytes());
        cad.setCancelledCheck(doc6.getBytes());
        cad.setSalarySlip(doc7.getBytes());
        cad.setSanctionLetter(doc8.getBytes());
        
        
        CustomerBankAccountDetails cbd=new CustomerBankAccountDetails();
        cbd.setAccountNumber(c.getCustomerBankAccountDetails().getAccountNumber());
        cbd.setIfscCode(c.getCustomerBankAccountDetails().getIfscCode());
        cbd.setBankName(c.getCustomerBankAccountDetails().getBankName());
        cbd.setAddress(c.getCustomerBankAccountDetails().getAddress());
      
        
        CustomerLocalAddress cla=new CustomerLocalAddress();
        cla.setPincode(c.getCustomerlocalAddress().getPincode());
        cla.setAreaName(c.getCustomerlocalAddress().getAreaName());
        cla.setCityName(c.getCustomerlocalAddress().getCityName());
        cla.setDistrict(c.getCustomerlocalAddress().getDistrict());
        cla.setState(c.getCustomerlocalAddress().getState());
        
        
        CustomerPermanentAddress cpa=new CustomerPermanentAddress();
        cpa.setPincode(c.getCustomerPermanentAddress().getPincode());
        cpa.setAreaName(c.getCustomerPermanentAddress().getAreaName());
        cpa.setCityName(c.getCustomerPermanentAddress().getCityName());
        cpa.setDistrict(c.getCustomerPermanentAddress().getDistrict());
        cpa.setState(c.getCustomerPermanentAddress().getState());
  	  

  	  
        GuarantorDetails gd=new GuarantorDetails();
          gd.setGuarantorName(c.getGuarantorDetails().getGuarantorName());
      gd.setGuarantorEmailId(c.getGuarantorDetails().getGuarantorEmailId());
      gd.setGuarantorMobileNo(c.getGuarantorDetails().getGuarantorMobileNo());
      gd.setGuarantorAddress(c.getGuarantorDetails().getGuarantorAddress());
    
       
        
        CustomerDetails customer=new CustomerDetails();
        customer.setCustomerName(c.getCustomerName());
        customer.setCustomerMobileno(c.getCustomerMobileno());
        customer.setCustomerDOB(c.getCustomerDOB());
        customer.setCustomerEmailId(c.getCustomerEmailId());
        customer.setCustomerPanNo(c.getCustomerPanNo());
        customer.setCustomerAadharNo(c.getCustomerAadharNo());
        customer.setCustomerGender(c.getCustomerGender());
        customer.setCustomerIncome(c.getCustomerIncome());
        
        
       
       customer.setLoanStatus("Pending");
        customer.setVerificationn("Pending");
        
        customer.setCustomerAllDocument(cad);
        customer.setCustomerBankAccountDetails(cbd);
        customer.setCustomerlocalAddress(cla);
        customer.setCustomerPermanentAddress(cpa);
        customer.setGuarantorDetails(gd);
     
        
                    hls.saveCustomer(customer);
                    return "Form Submitted SuccessFully";
	}
	
      
	@GetMapping("/getallgetData")
	public ResponseEntity<List<CustomerDetails>> getEmployee(){
		
		List<CustomerDetails> list=hls.viewCustomers();
	
		         if(list.isEmpty()) {
		        	 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		         }
		 return  new ResponseEntity<List<CustomerDetails>>(list,HttpStatus.OK);
	}
	

	//getEmployeeById
	@GetMapping("/getdataByid/{id}")
	public Optional<CustomerDetails> searchCustomer(@PathVariable Integer id){
		 Optional<CustomerDetails> e= hls.searchEmployeee(id);
		 return e;
}

	
	//Document Approved
	@PutMapping(value = "/AcceptCustomer/{id}")
	public String approveStatus(@PathVariable("id")Integer id,CustomerDetails loanStatus) {
	
		System.out.println("In approveStatus controller " + id);
		CustomerDetails st= hls.findCust(id);
		st.setLoanStatus("Approved");
		   hls.saveCustomer(st);
		   System.err.println(loanStatus);
		   return "redirect:/getallgetData/" + loanStatus;
	}
		 

	//Document Rejected
	@PutMapping(value = "/RejectCustomer/{id}")
	public String rejectStatus(@PathVariable("id")Integer id,String loanStatus){
		System.out.println("In approveStatus controller " + id);
		CustomerDetails st= hls.findCust(id);
		st.setLoanStatus("Rejected");
		           hls.saveCustomer(st);
		   System.err.println(loanStatus);
		return "redirect:/getallgetData/" + loanStatus;
	}
	
	//Document Varified
		@PutMapping(value = "/VarifyCust/{id}")
		public String varifiedStatus(@PathVariable("id")Integer id,String verificationn ){
			System.out.println("In approveStatus controller " + id);
			CustomerDetails st= hls.findCust(id);
			st.setVerificationn("Varified");
			       hls.saveCustomer(st);
			   System.err.println(verificationn);
			
			return "redirect:/getallgetData/" + verificationn;
		}
		
		//Document Unvarified
		@PutMapping(value = "/Unvarifiedcust/{id}")
		public String Unvarified(@PathVariable("id")Integer id,String verificationn){
			System.out.println("In approveStatus controller " + id);
			CustomerDetails st= hls.findCust(id);
			st.setVerificationn("UnVarified");
			     hls.saveCustomer(st);
			   System.err.println(verificationn);
			return "redirect:/getallgetData/" + verificationn;
		}
		
}
		
			
		

