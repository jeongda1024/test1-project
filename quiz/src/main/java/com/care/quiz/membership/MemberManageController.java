package com.care.quiz.membership;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.quiz.membership.dto.AllDTO;
import com.care.quiz.membership.dto.LoginDTO;
import com.care.quiz.membership.dto.MemberDTO;
import com.care.quiz.membership.service.IMemberService;

@PropertySource("classpath:adminAccount.properties")
@Controller
public class MemberManageController {
	@Autowired private IMemberService memberService;
	
	@RequestMapping(value="memberListProc")
	public String memberList(Model model,
		@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage,
			String select, String search){
		memberService.memberList(currentPage, select, search);
		return "forward:/index?formpath=memberList";
	}
	
	@Value("${ADMIN:test1}") private String adminAccount;
	//memberListForm.jsp에서 사용자의 아이디를 클릭시 요청을 받음.
	@RequestMapping(value="userInfoProc") //memberListForm.jsp에서 사용자의 아이디를 클릭시 요청을 받음.
	public String userInfo(String id, HttpSession session, Model model) {
		String sessionId = (String)session.getAttribute("id");
		if(id==""||id==null ||sessionId==""|| sessionId==null) {
			return "redirect:memberListProc";
		}
		
		// 로그인 계정은 user12, 회원목록에서 클릭한 아이디 user12
		// 관리자 계정은 test1, 로그인된 계정이 test1
		if(sessionId.equals(id)||adminAccount.equals(sessionId)) {
			model.addAttribute("user", memberService.userInfo(id));
			return "forward:index?formpath=userInfo";
		}
		return "redirect:memberListProc";
	}
	
	
	//수정 전 비밀번호 체크
	@RequestMapping(value="modifyCheckProc")
	public String modifyCheckProc(LoginDTO check, Model model, HttpSession session) {
		boolean b = memberService.modifyCheckProc(check);
		if(b==false) {
			return "forward:/index?formpath=modifyCheck?modifyId="+session.getAttribute("modifyId");
		}
		
		return "redirect:/index?formpath=memberModify";
	}
	
	//수정 정보 저장
	@RequestMapping(value="memberModifyProc")
	public String memberModifyProc(AllDTO user, HttpSession session) {
		String modifyId = (String)session.getAttribute("modifyId");
		user.setId(modifyId);
		String msg = memberService.memberModifyProc(user);
		System.out.println(msg);
		if(msg.equals("회원 수정 완료")) {
			return "redirect:memberListProc";
		}
		return "redirect:/index?formpath=memberModify";
	}
	
	
	//비밀번호 체크 후 삭제
	@RequestMapping(value = "deleteAndCheckProc")
	public String deleteAndCheckProc(LoginDTO check, HttpSession session) {
		boolean b = memberService.deleteAndCheckProc(check);
		if(b == false) {
			return "forward:/index?formpath=memberDelete?modifyId="+session.getAttribute("modifyId");}
		return "redirect:/";
	}

	
}
