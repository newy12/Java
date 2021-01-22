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

import com.google.gson.Gson;
import com.ilgusi.member.model.service.MemberService;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.request.model.service.RequestService;
import com.ilgusi.request.model.vo.RequestPageData;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;

	@Autowired
	private RequestService reqService;
	
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
			model.addAttribute("msg", "일치하는 회원 정보가 없습니다.");
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
			model.addAttribute("msg", "일치하는 회원 정보가 없습니다.");
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

	// (도현) 아이디 중복검사 ajax
	@RequestMapping(value = "/checkId.do", produces = "text/json; charset=utf-8")
	@ResponseBody
	public String checkId(String id) {
		System.out.println("중복검사 아이디:" + id);
		Member m = service.checkId(id);
		String json;
		if (m != null) {
			json = "{\"result\":\"true\"}"; // 중복임
		} else {
			json = "{\"result\":\"false\"}";
			; // 중복아님
		}
		return json;
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
	public String login(HttpServletRequest req, String id, String pw, Model model, String loc) {
		System.out.println("로그인 시도");
		System.out.println("id" + id + " pw:" + pw);
		Member m = service.loginMember(id, pw);

		if (m != null) {
			m.setBuyingCount(service.selectBuyingCount(m.getMNo()));
			m.setSellingCount(service.selectSellingCount(m.getMId()));
			if (m.getMGrade() != 0)
				m.setMGrade(1);
			HttpSession session = req.getSession();
			session.setAttribute("loginMember", m);
			model.addAttribute("msg", "로그인 성공");
		} else {
			model.addAttribute("msg", "로그인 실패");
		}
		model.addAttribute("loc", loc);
		if (m != null && m.getMGrade() == 0)
			model.addAttribute("loc", "/manageMember.do?reqPage=1&grade=all&keyword=&order=new");

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
	public String userMypage(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Member m = (Member) session.getAttribute("loginMember");
		if (m.getMGrade() == 1) {
			return "member/userMypage";
		} else if (m.getMGrade() == 2) {
			return "redirect:/freelancerMypage.do?MNo=" + m.getMNo();
		}
		return "";
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

	// (문정)사용자 마이페이지 - 회원탈퇴(아이디로만)
	@RequestMapping("/deleteMember.do")
	public String deleteMember(String mId, HttpServletRequest req, Model model, String admin, String mNo) {
		if (mNo == null) {	
			// 회원번호 받아서 거래중인 것이 있는지 확인 -> 없어야만 삭제
			HttpSession session = req.getSession();
			Member m = (Member) session.getAttribute("loginMember");
			int tradeStatus = service.tradeStatus(m.getMNo());

			// 탈퇴 진행
			if (tradeStatus == 0) {
				int result = service.setDeleteStatusY(mId); // delete_status = 'y'로 바꿈
				result = service.deleteMember(mId);
				System.out.println("사용자 탈퇴성공");
				model.addAttribute("loc", "/userMypage.do");
				session.setAttribute("loginMember", null);
				model.addAttribute("msg", "탈퇴 되었습니다.");
				model.addAttribute("loc", "/");
			}
			// 거래중인 서비스가 있어서 탈퇴 거절
			else {
				model.addAttribute("msg", "거래 중인 서비스가 있기 때문에 탈퇴하실 수 없습니다.");
				model.addAttribute("loc", "/userMypage.do");
			}
			return "common/msg";
		} else {// 관리자가 삭제할때
			int mNoInt = Integer.parseInt(mNo);
			int tradeStatus = service.tradeStatus(mNoInt);
			// 탈퇴 진행
			if (tradeStatus == 0) {
				int result = service.setDeleteStatusY(mId); // delete_status = 'y'로 바꿈
				result = service.deleteMember(mId);
				System.out.println("관리자 탈퇴");
				model.addAttribute("msg", "탈퇴 되었습니다.");
				model.addAttribute("loc", "manageMember.do?reqPage=1&grade=black&keyword=&order=new");
			}
			// 거래중인 서비스가 있어서 탈퇴 거절
			else {
				model.addAttribute("msg", "거래 중인 서비스가 있기 때문에 탈퇴하실 수 없습니다.");
				model.addAttribute("loc", "/manageMember.do?reqPage=1&grade=black&keyword=&order=new");
			}
			return "common/msg";

		}
	}

	// (문정) 마이페이지에서 사용자-프리랜서 전환
	@RequestMapping("/changeGrade.do")
	public String changeGrade(String mId, String mPw, int grade, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Member m = (Member) session.getAttribute("loginMember");

		// 프리랜서로 전환한 적이 없으면 -> db에 2를 넣어줌
		if (grade == 1) {
			int result = service.changeGrade(mId, grade);
			if (result > 0)
				System.out.println("프리랜서로 잘 바꿈");
			m.setMGrade(2);
			session.setAttribute("loginMember", m);
			return "redirect:/freelancerMypage.do?MNo=" + m.getMNo();
		} else {
			// 프리랜서 -> 사용자로 전환하면(session만 바꿔줌)
			System.out.println("사용자로 sessio만 바꿈");
			m.setMGrade(1);
			session.setAttribute("loginMember", m);
			return "member/userMypage";
		}
	}
	
	//(문정) 마이페이지 - 의뢰내역 확인하기
	@RequestMapping("/userRequestHistory.do")
	public String userRequestHistory( int reqPage, Model model) {
		RequestPageData rpd = reqService.selectRequestList(reqPage, "new", "all", null,"mypage");
		model.addAttribute("list", rpd.getList());
		model.addAttribute("pageNavi", rpd.getPageNavi());
		model.addAttribute("totalCount", rpd.getTotalCount());
		return "member/userRequestHistory";
	}
}
