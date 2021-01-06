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
	public String introduceFrm(String id, Model model) {
		Join j = service.selectOneMember(id);
		model.addAttribute("j",j);
		return "freelancer/introduce";
	}
	@RequestMapping("/serviceJoinFrm.do")
	public String serviceJoinFrm() {
		return "freelancer/servicejoin";
	}
	@RequestMapping("/serviceJoin.do")
	public String serviceJoin(Join join, Model model) {
		int result = service.insertService(join);
		if(result>0) {
			model.addAttribute("msg","서비스등록완료");
		}else {
			model.addAttribute("msg","서비스등록실패");
		}
		model.addAttribute("loc","/");
		return "common/msg";
	}
}
