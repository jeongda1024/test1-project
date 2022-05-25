package com.care.quiz.membership.service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.quiz.membership.dao.IMemberDAO;
import com.care.quiz.membership.dto.AllDTO;
import com.care.quiz.membership.dto.LoginDTO;
import com.care.quiz.membership.dto.MemberDTO;
import com.care.quiz.membership.dto.PostcodeDTO;

@PropertySource("classpath:adminAccount.properties")
@Service
public class MemberServiceImpl implements IMemberService {
	@Autowired private IMemberDAO memberDAO;
	@Autowired private HttpSession session;
	
	//login 아이디 중복확인
	@Override
	public String isExistId(String id) {
		if(id==null || id.isEmpty()) {
			return "아이디를 입력하세요.";
		}
		int count = memberDAO.isExistId(id);
		if(count==1) {
			return "중복 아이디입니다.";
		}
		return "사용 가능한 아이디입니다.";
	}
	
	//select에서 selectId >> 로그인
	public int selectId(MemberDTO member) {
		if(member.getId().isEmpty() || member.getPw().isEmpty()) {
			return 2;
		}
		
		MemberDTO check = memberDAO.selectId(member.getId());
		
		//암호화된 패스워드 일치시키기 작업!
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(check!=null && encoder.matches(member.getPw(), check.getPw())) {
			session.setAttribute("id", check.getId());
			session.setAttribute("email", check.getEmail());
			session.setAttribute("gender", check.getGender());
			return 1;
		}
		
		return 2;
	}

	

	@Override
	public String MemberProc(MemberDTO member, PostcodeDTO post) {
		LoginDTO login = member;
		
		//아이디 빈칸
		if(login.getId()==null|| login.getId().isEmpty()) {
			return "아이디를 입력하세요.";
		}
		
		//비밀번호 빈칸
		if(login.getPw()==null|| login.getPw().isEmpty()) {
			return "비밀번호를 입력하세요.";
		}
		
		//아이디 중복 확인
		if(memberDAO.isExistId(login.getId())>0) {
			return "중복 아이디 입니다.";
		}
		
		//이메일 인증 여부
		Boolean authStatus = (Boolean)session.getAttribute("authStatus");
		if(authStatus==null || authStatus!=true) {
			return "이메일 인증 후 가입할 수 있습니다.";
		}
		
		
		//비밀번호 암호화
		//1. 인코더 불러오기
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//2.memberDTO 안에 있는 getPw()불러와서 인코더로 String 변환 값가져오기
		String securePw = encoder.encode(login.getPw());
		//3.memberDTO 안에 변환값 저장하기
		login.setPw(securePw);
			
		memberDAO.insertLogin(login);
		
		//가입인증 성별
		if("m".equals(member.getGender()) || "w".equals(member.getGender())|| "n".equals(member.getGender())) {
			memberDAO.insertMember(member);
		}
		
		//우편번호
		if(!("".equals(post.getZipcode()))) {
			memberDAO.insertPost(post);
		}
		
		return "가입 완료";
		
		
		
		
//		//세션에 authStatus(이메일인증)의 값이 있는지 없는지 ->4:이메일 인증 후 회원가입을 다시 시도하세요
//		Boolean authStatus = (Boolean)session.getAttribute("authStatus");
//		if(authStatus==null || authStatus!=true) {
//			return 4;
//		}
//		
//		MemberDTO check = memberDAO.selectId(member.getId());
//		if(check==null) {
//			member = new MemberDTO();
//			
//			//DB에 insert
//			int result = memberDAO.insert(member);
//			
//			//1:회원가입 성공
//			if(result==1) {
//				memberDAO.insertPost(post);
//			}
//			session.invalidate();
//			return result;
//		}else {
//			//3:중복된 아이디입니다
//			return 3;
//		}
	}

	@Override
	public void memberList(int currentPage, String select, String search) {
		int pageBlock = 3; //한 화면에 보여줄 데이터 수
		int totalCount = memberDAO.memberCount(); //총 데이터의 수
		int end = currentPage * pageBlock; // 데이터의 끝 번호
		int begin = end+1 - pageBlock; //데이터의 시작 번호
		
		ArrayList<MemberDTO> list = memberDAO.memberList(begin, end, select, search);
		session.setAttribute("list", list);
		String url = "/quiz/memberListProc?currentPage=";
		session.setAttribute("page", PageService.getNavi(currentPage, pageBlock, totalCount, url));
	}
	

	@Override
	public AllDTO userInfo(String id) {
		//AllDTO user = memberDAO.userAll(id);
		//return user;
		MemberDTO member = memberDAO.userInfo(id);
		PostcodeDTO post = memberDAO.postInfo(id);
		AllDTO user = new AllDTO();
		if(member!=null) {
			user.setId(member.getId());
			user.setEmail(member.getEmail());
			user.setGender(member.getGender());
		}
		
		if(post!=null) {
			user.setZipcode(post.getZipcode());
			user.setAddr1(post.getAddr1());
			user.setAddr2(post.getAddr2());
		}
		return user;
	}
	
	public boolean modifyCheckProc(LoginDTO check) {
		if(check.getPw().equals(check.getConfirmPw())==false) {
			return false;
		}
		//세션 아이디로 비밀번호 확인(일반사용자 또는 관리자 계정)
		String id = (String)session.getAttribute("id");
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		LoginDTO login = memberDAO.userPassword(id);
		if(login==null||encoder.matches(check.getPw(), login.getPw())==false) {
			return false;
		}
		
		//수정자 정보 담아 memberModifyForm.jsp 출력하기 위한 데이터
		String modidfyId = (String)session.getAttribute("modifyId");
		AllDTO user = userInfo(modidfyId);
		user.setPw(login.getPw());
		session.setAttribute("user", user);
		return true;

	}

	/*
	 * ADMIN : 외부 파일 변수 
	 * test1 : 실패시 기본 값
	 */
	@Value("${ADMIN:test1}") private String adminAccount;
	
	@Override
	public String memberModifyProc(AllDTO user) {
		BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
		
		System.out.println("getPw : "+user.getPw());
		System.out.println("getConfirmPw : "+user.getConfirmPw());
		
		if(user.getPw().equals(user.getConfirmPw())==false) {
			return "두 비밀번호가 일치하지 않습니다.";
		}
		
		if(user.getPw()!="") {
			LoginDTO login = user;
			String tmp = encoder.encode(user.getPw());
			login.setPw(tmp);
			memberDAO.updateLogin(login);
		}
		
		//바로 위 modifyCheckProc()에서 입력한 회원 정보.
		AllDTO oldUserInfo = (AllDTO)session.getAttribute("user");
		if(user.getEmail()!="" && user.getEmail().equals(oldUserInfo.getEmail())==false) {
			MemberDTO member = user;
			memberDAO.updateMember(member);
		}
		
		if(user.getZipcode()!="" && user.getZipcode().equals(oldUserInfo.getZipcode())==false) {
			PostcodeDTO post = new PostcodeDTO();
			post.setId(user.getId());
			post.setZipcode(user.getZipcode());
			post.setAddr1(user.getAddr1());
			post.setAddr2(user.getAddr2());
			memberDAO.updatePost(post);
		}
		
		//관리자 계정이 아니라면 세션의 정보는 모두 삭제
		if(adminAccount.equals(session.getAttribute("id"))==false) {
			session.invalidate();
		}
		return "회원 수정 완료";
	}
	
	
	@Override
	public boolean deleteAndCheckProc(LoginDTO check) {
		if(check.getPw().equals(check.getConfirmPw())==false) {
			return false;
		}
		
		// 세션 아이디로 비밀번호 확인(일반사용자 또는 관리자 계정)
		String id = (String) session.getAttribute("id");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		LoginDTO login = memberDAO.userPassword(id);
		if (login == null || encoder.matches(check.getPw(), login.getPw()) == false) {
		return false;
		}
		// 계정 삭제
		String modifyId = (String)session.getAttribute("modifyId");
		memberDAO.deleteLogin(modifyId);
		
		// 관리자 계정과 로그인된 계정이 다르거나 관리자 계정과 수정계정이 같으면 세션 삭제
		
		if(adminAccount.equals(id) == false || adminAccount.equals(modifyId))
			session.invalidate();
			return true;
		}
	

}
