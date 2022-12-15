package com.homeloan.service;


import com.homeloan.model.EmailSender;
import org.springframework.web.multipart.MultipartFile;




public interface EmailSenderService {
	public void sendEmail(EmailSender e);
	public void sendattachement(EmailSender e, MultipartFile file);
	


	
	
}
