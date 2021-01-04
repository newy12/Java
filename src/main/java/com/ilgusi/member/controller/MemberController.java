package com.ilgusi.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@RequestMapping("/join.do")
	public String joinFrm() {
		System.out.println("join.do 접속");
		return "member/joinFrm";
	}
	@RequestMapping("/introduceFrm.do")
	public String introduceFrm(String mName, Model model) {
		Member m = service.selectOneMember(mName);
		model.addAttribute("m",m);
		return "freelancer/introduce";
	}
}
