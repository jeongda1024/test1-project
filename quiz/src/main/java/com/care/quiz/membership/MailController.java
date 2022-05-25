package com.care.quiz.membership;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.quiz.membership.service.MailService;

@Controller
public class MailController {
	@Autowired MailService mailService;
	@Autowired HttpSession session;
	
	@ResponseBody
	@PostMapping(value="sendAuth", produces = "application/json; charset=UTF-8")
	public String sendString(@RequestBody(required=false) String email) {
		if(email!=null) {
			//mailService.sendMail(email, "[인증번호]", "12345");
			//6자리의 숫자로 구성된 인증번호를 만들어서 출력해주세요.
			// 자료형은 문자열, 구성된 문자는 0~9
			
			Random random = new Random();
			String number = String.format("%06d", random.nextInt(1000000));
			System.out.println("인증 번호 : "+number);
			mailService.sendMail(email, "[인증번호]", number);
			session.setAttribute("authNumber", number);
			return "인증 번호 전송";
		}else {
			return "이메일을 입력하세요";
		}
	}
	
	@ResponseBody
	@PostMapping(value="checkAuth", produces="application/json; charset=UTF-8")
	public String checkAuth(@RequestBody(required = false) Map<String, String> map) {
		System.out.println("고객이 입력한 인증번호 : "+map.get("authNumber"));
		
		//sendAuth 메소드에서 생성한 인증번호와 고객이 입력한 인증번호를 비교
		String sessionAuthNumber = (String)session.getAttribute("authNumber");
		String clientAuthNumber = map.get("authNumber");
		if(sessionAuthNumber==null) {
			return "인증 번호를 생성하세요";
		}
		if(clientAuthNumber.isEmpty()) {
			return "인증 번호를 생성하세요";
		}
		
		if(sessionAuthNumber.equals(clientAuthNumber)) {
			session.setAttribute("authStatus", true);
			return "인증 성공";
		}
		return "인증 실패";
	}
	
}
