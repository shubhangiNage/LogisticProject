package com.homeloan.serviceimp;






import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.homeloan.model.EmailSender;
import com.homeloan.service.EmailSenderService;

@Service
public class EmailSenderImp implements EmailSenderService{
	@Autowired
	JavaMailSender jms;
	
	

	//sendsimplemailbyid
	@Override
	public void sendEmail(EmailSender e) {
		try {
	
		SimpleMailMessage smm=new SimpleMailMessage();
		
		
		smm.setFrom(e.getFromEmail());
		smm.setTo(e.getToEmail());
		smm.setSubject(e.getSubject());
		smm.setText(e.getTestBody());
		
		System.out.println(smm.getTo());
		
		jms.send(smm);
		System.out.println("Mail Sent Successfully...!");
	}
	catch(Exception e2) {
		
		System.out.println("Not Sent");
		e2.printStackTrace();
		}
		
		
	}


		//Send Email With Attachment
		@Override
		public void sendattachement(EmailSender e,MultipartFile onSelectedFile1) {
			
	              try {
				
	            	  SimpleMailMessage smm=new SimpleMailMessage();
				MimeMessage mm= jms.createMimeMessage();
				MimeMessageHelper mmh= new MimeMessageHelper(mm,true);
				mmh.setFrom(e.getFromEmail());
				mmh.setTo(e.getToEmail());
				 //cant send blank data type 
				mmh.setText(e.getTestBody());
				mmh.setSubject(e.getSubject());
				mmh.addAttachment(onSelectedFile1.getOriginalFilename(), onSelectedFile1);
				jms.send(mm);
				 smm.setText("Hiiii");
				System.out.println("Email sent");
				
			} catch (Exception e1) {
				
				System.out.println("Not sent");
				e1.printStackTrace();
			}
		}


		
	}

		
