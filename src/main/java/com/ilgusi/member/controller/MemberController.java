package com.ilgusi.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilgusi.member.model.service.MemberService;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Join;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;

	public MemberController() {
		super();
		System.out.println("Member컨트롤러 생성 완료");
	}

	// (도현) 회원가입 페이지 이동
	@RequestMapping("/join.do")
	public String joinFrm() {
		System.out.println("join.do 접속");
		return "member/joinFrm";
	}

	// (도현) 회원가입 기능
	@RequestMapping("/register.do")
	public String register(Member m,Model model) {
		System.out.println("register.do 접속");
		System.out.println("아아ㅣ디: "+m.getMId());
		int result = service.registerMember(m);
		
		if(result > 0) {
			model.addAttribute("msg", "회원가입 성공! 로그인 해주세요!");
			model.addAttribute("loc", "/");			
		}else {
			model.addAttribute("msg", "회원가입 실패!");
			model.addAttribute("loc", "/join.do");			
		}
		return "common/msg";
	}

	// (도현) 로그인
	@RequestMapping("/login.do")
	public String login(HttpServletRequest req, String id, String pw, Model model) {
		System.out.println("로그인 시도");
		System.out.println("id" + id + " pw:" + pw);
		Member m = service.loginMember(id, pw);
		System.out.println("이름 " + m.getMName());
		if (m != null) {
			HttpSession session = req.getSession();
			session.setAttribute("loginMember", m);
		}
		model.addAttribute("msg", "로그인 성공");
		model.addAttribute("loc", "/");
		return "common/msg";
	}

	// (도현) 로그아웃
	@RequestMapping("/logout.do")
	public String login(HttpServletRequest req, Model model) {
		System.out.println("로그아웃 시도");
		HttpSession session = req.getSession();
		if (session.getAttribute("loginMember") != null) {
			session.setAttribute("loginMember", null);
		}
		model.addAttribute("msg", "로그아웃 성공");
		model.addAttribute("loc", "/");
		return "common/msg";
	}

	// (문정)사용자 마이페이지 이동
	@RequestMapping("/userMypage.do")
	public String userMypage() {
		return "member/userMypage";
	}

	// (문정)사용자 마이페이지-찜한 리스트 이동
	@RequestMapping("/userHeartList.do")
	public String userHeartList() {
		return "member/userHeartList";
	}

	// (문정)사용자 마이페이지-거래내역
	@RequestMapping("/userTradeHistory.do")
	public String userTradeHistory() {
		return "member/userTradeHistory";
	}
	
	//(문정)사용자 마이페이지-이메일, 폰번호 변경
	@ResponseBody
	@RequestMapping("/changeMypage.do")
	public String changeMypage(String mId, String mPw, String data, String object, HttpServletRequest req) {
		int result = service.changeMypage(mId, data, object);
		if(result>0) {
			Member m = service.loginMember(mId, mPw);
			if (m != null) {
				HttpSession session = req.getSession();
				session.setAttribute("loginMember", m);
			}
		}
		return "";
	}
	
	//(문정)사용자 마이페이지-비밀번호 변경
	@RequestMapping("/changePw.do")
	public String changePw(String mId, String mPw, String data, String object, HttpServletRequest req) {
		int result = service.changeMypage(mId, data, object);
		if(result>0) {
			Member m = service.loginMember(mId, data);
			if (m != null) {
				HttpSession session = req.getSession();
				session.setAttribute("loginMember", m);
			}
		}
		return "member/userMypage";
	}

	//(문정)사용자 마이페이지 - 회원탈퇴
	@RequestMapping("/deleteMember.do")
	public String deleteMember(String mId, String mPw, HttpServletRequest req, Model model) {
		int result = service.deleteMember(mId,mPw);
		if(result>0) {
			HttpSession session = req.getSession();
			session.setAttribute("loginMember", null);
			model.addAttribute("msg", "탈퇴 되었습니다.");
			model.addAttribute("loc", "/");
		}else {
			model.addAttribute("msg", "탈퇴 실패");
			model.addAttribute("loc", "/member/userMypage");
		}
		return "common/msg";
	}
}
