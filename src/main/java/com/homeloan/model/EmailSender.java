package com.homeloan.model;



import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class EmailSender {

	
		private String fromEmail;
		public String toEmail;
		private String testBody;
		private String subject;
		
			
		}