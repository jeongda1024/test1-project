package com.care.quiz;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	//인덱스
	@RequestMapping(value="/")
	public String index(Model model) {
		model.addAttribute("formpath","home");
		return "index";
	}
	//http://localhost:8085/quiz/index?formpath=home
	@RequestMapping(value="index")//void이기때문에 value의 index로 간다
	public void index(String formpath, Model model, String modifyId, HttpSession session) {
		model.addAttribute("formpath",formpath);
		
		/*
		 * 수정버튼에서 보내준 아이디를 세션에 저장하여 modifyCheck(비밀번호) 요청 처리함.
		 * if("modifyCheck".equals(formpath)) {
		 * 
		 * 세션에 저장된 값이 없으면 동작, 담겨 있다면 동작되지 않음.(비밀번호가 틀린경우) A계정에서 B계정으로 변경해서 수정을 한다면 세션의
		 * ModifyId 변경 if(sessionId ==null || sessionId.equals(modifyId)==false) {
		 * 
		 * } }
		 */
		String sessionId = (String)session.getAttribute("modifyId");
		if("modifyCheck".equals(formpath)||"memberDelete".equals(formpath)) {
			if(sessionId==null || sessionId.equals(modifyId)==false) {
				session.setAttribute("modifyId", modifyId);
			}
		}
	}
	//index메소드, modifyCheck(패스워드입력), modifyCheckProc(패스워드체크),memberModify
	@RequestMapping(value="memberDelete")
	public String memberDelete() {
		return "member/memberDelete";
	}
	
	
	@RequestMapping(value="modifyCheck")
	public String modifyCheck() {
		return "member/modifyCheckForm";
	}
	
	@RequestMapping(value="memberModify")
	public String memberModify() {
		return "member/memberModifyForm";
	}
	
	
	@RequestMapping(value="home")
	public void home() {}
	
	//회원가입
	@RequestMapping(value="member")
	public String member() {
		return "member/memberForm";
	}
	
	
	
	//로그인
	@RequestMapping(value="login")
	public String login() {
		return "member/loginForm";
	}
	
	//회원목록
	@RequestMapping(value="memberList")
	public String memberList() {
		return "member/memberListForm";
	}
	
	
	//게시판
	@RequestMapping(value="board")
	public String board() {
		return "board/boardForm";
	}
	
	//유저정보
	@RequestMapping(value="userInfo")
	public String userInfo() {
		return "member/userInfoForm";
	}
	
	
	//스타벅스
	@RequestMapping(value="starbucks")
	public String starbucks() {
		return "starbucks";
	}
	
	
}
