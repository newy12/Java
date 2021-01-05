package com.ilgusi.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ilgusi.service.model.service.ServiceService;
import com.ilgusi.service.model.vo.Join;
import com.ilgusi.service.model.vo.Service;

@Controller
public class ServiceController {
	@Autowired
	private ServiceService service;
	
	@RequestMapping("/introduceFrm.do")
	public String introduceFrm(Join join, Model model) {
		Join j = service.selectOneMember(join);
		model.addAttribute("j",j);
		return "freelancer/introduce";
	}
	@RequestMapping("/serviceJoinFrm.do")
	public String serviceJoinFrm() {
		return "freelancer/servicejoin";
	}
	@RequestMapping("/serviceJoin.do")
	public String serviceJoin() {
		
	}
}
