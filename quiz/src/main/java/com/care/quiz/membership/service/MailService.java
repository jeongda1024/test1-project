package com.care.quiz.membership.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired JavaMailSender mailSender;
	public void sendMail(String email, String string, String number) {
		//메세지 만들기
		MimeMessage message = mailSender.createMimeMessage();
		
		try {													//도와줄 메세지객체,true,인코딩
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
			messageHelper.setSubject(string); //.setSubject = 이메일의 제목
			messageHelper.setText(number);	//.setText = 이메일의 본문
			messageHelper.setTo(email); //.setTo = 수신자
			mailSender.send(message);
		}catch(MessagingException e){
			e.printStackTrace();
		}
		
		
		
	}
}
