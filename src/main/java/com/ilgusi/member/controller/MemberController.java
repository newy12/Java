package com.ilgusi.member.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilgusi.member.model.service.MemberService;
import com.ilgusi.member.model.vo.Member;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;

	public MemberController() {
		super();
		System.out.println("Member컨트롤러 생성 완료");
	}

	// (도현) 아이디/비번 찾기 페이지 이동
	@RequestMapping("/forgot_pwd.do")
	public String searchIdPwFrm() {
		System.out.println("forgot_pwd.do 접속");
		return "member/searchIdPw";
	}

	// (도현) 아이디 찾기 기능
	@RequestMapping("/searchId.do")
	public String searchId(Member m, Model model) {
		System.out.println("searchId.do 접속");
		System.out.println("m: " + m.getMName() + " p:" + m.getMPhone());
		Member result = service.searchIdPw(m);
		System.out.println("result: " + result);

		if (result == null) {
			model.addAttribute("msg", "못찾음");
			model.addAttribute("loc", "/forgot_pwd.do");
		} else if (m.getMName() != null) {
			model.addAttribute("msg", "아이디: " + result.getMId());
			model.addAttribute("loc", "/forgot_pwd.do");
		}
		return "common/msg";
	}

	// (도현) 비번 찾기 페이지에서 클릭
	@RequestMapping("/searchPw.do")
	public String searchPw(HttpServletRequest req, Member m, Model model) {
		System.out.println("searchPw.do 접속");
		System.out.println("m: " + m.getMId() + " p:" + m.getMPhone());

		Member result = service.searchIdPw(m);
		System.out.println("result: " + result);

		HttpSession session = req.getSession();
		if (result == null) {
			model.addAttribute("msg", "못찾음");
			model.addAttribute("exit", true);
			return "common/msg2";
		} else {
			session.setAttribute("searchPwObj", result);
			return "member/searchPw";
		}
	}

	// (도현) 비번 찾기 기능 (비번 변경)
	@RequestMapping("/searchChangePw.do")
	public String searchPw(HttpServletRequest req, String mPw, Model model) {
		System.out.println("searchId.do 접속");
		HttpSession session = req.getSession();
		Member m = (Member) session.getAttribute("searchPwObj");
		m.setMPw(mPw);
		int result = service.changePw(m);
		System.out.println("result: " + result);

		if (result > 0) {
			model.addAttribute("msg", "변경 완료.");
			model.addAttribute("exit", true);
			session.setAttribute("searchPwObj", null);
		} else {
			model.addAttribute("msg", "변경 실패.");
			model.addAttribute("exit", true);
		}
		return "common/msg2";
	}

	// (도현) 회원가입 페이지 이동
	@RequestMapping("/join.do")
	public String joinFrm() {
		System.out.println("join.do 접속");
		return "member/joinFrm";
	}

	// (도현) 회원가입 기능
	@RequestMapping("/register.do")
	public String register(Member m, Model model) {
		System.out.println("register.do 접속");
		System.out.println("아아ㅣ디: " + m.getMId());
		int result = service.registerMember(m);

		if (result > 0) {
			model.addAttribute("msg", "회원가입 성공! 로그인 해주세요!");
			model.addAttribute("loc", "/");
		} else {
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

	// (문정)사용자 마이페이지-거래내역
	@RequestMapping("/userTradeHistory.do")
	public String userTradeHistory() {
		return "member/userTradeHistory";
	}

	// (문정)사용자 마이페이지-이메일, 폰번호 변경
	@ResponseBody
	@RequestMapping("/changeMypage.do")
	public String changeMypage(String mId, String mPw, String data, String object, HttpServletRequest req) {
		int result = service.changeMypage(mId, data, object);
		if (result > 0) {
			Member m = service.loginMember(mId, mPw);
			if (m != null) {
				HttpSession session = req.getSession();
				session.setAttribute("loginMember", m);
			}
		}
		return "";
	}

	// (문정)사용자 마이페이지-비밀번호 변경
	@RequestMapping("/changePw.do")
	public String changePw(String mId, String mPw, String data, String object, HttpServletRequest req) {
		int result = service.changeMypage(mId, data, object);
		if (result > 0) {
			Member m = service.loginMember(mId, data);
			if (m != null) {
				HttpSession session = req.getSession();
				session.setAttribute("loginMember", m);
			}
		}
		return "member/userMypage";
	}

	// (문정)사용자 마이페이지 - 회원탈퇴
	@RequestMapping("/deleteMember.do")
	public String deleteMember(String mId, String mPw, HttpServletRequest req, Model model) {
		int result = service.deleteMember(mId, mPw);
		if (result > 0) {
			HttpSession session = req.getSession();
			session.setAttribute("loginMember", null);
			model.addAttribute("msg", "탈퇴 되었습니다.");
			model.addAttribute("loc", "/");
		} else {
			model.addAttribute("msg", "탈퇴 실패");
			model.addAttribute("loc", "/member/userMypage");
		}
		return "common/msg";
	}

	// (소현)관리자-전체회원조회
	@RequestMapping("/manageMember.do")
	public String manageMember(Model model) {
		// 전체회원리스트
		ArrayList<Member> memberList = service.manageMember();

		// 회원별서비스이용횟수 리스트
		HashMap<Integer, Integer> useHistory = new HashMap<Integer, Integer>();
		for (int i = 0; i < memberList.size(); i++) {
			Member oneMember = memberList.get(i);
			int mNo = oneMember.getMNo();
			int use = service.countHistory(mNo);
			useHistory.put(mNo, use);
		}

		model.addAttribute("memberList", memberList);
		model.addAttribute("useHistory", useHistory);

		return "admin/memberList";
	}

}
