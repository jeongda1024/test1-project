package com.care.quiz.membership.service;

import java.util.ArrayList;

import com.care.quiz.membership.dto.AllDTO;
import com.care.quiz.membership.dto.LoginDTO;
import com.care.quiz.membership.dto.MemberDTO;
import com.care.quiz.membership.dto.PostcodeDTO;

public interface IMemberService {

	//중복확인
	public String isExistId(String id);
	
	//회원가입
	public String MemberProc(MemberDTO member, PostcodeDTO post);
//	
//	//인증번호 사용자 이메일로 송신
//	public void sendAuth(String email);
//	
//	//사용자 입력 인증 값 확인
//	public String authConfirm(String authNum);
//
//	
	//회원목록
	public void memberList(int currentPage, String select, String search);

	//회원 정보
	public AllDTO userInfo(String id);
	
	//수정 전 비밀번호 체크
	public boolean modifyCheckProc(LoginDTO check);

	//회원 정보 수정
	public String memberModifyProc(AllDTO user);

	
	//삭제 및 비밀번호 체크
	boolean deleteAndCheckProc(LoginDTO check);


	
}
