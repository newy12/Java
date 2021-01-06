package com.ilgusi.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping("/join.do")
	public String joinFrm() {
		System.out.println("join.do 접속");
		return "member/joinFrm";
	}

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
}
