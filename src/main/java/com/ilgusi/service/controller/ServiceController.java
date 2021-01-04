package com.ilgusi.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ilgusi.service.model.service.ServiceService;

@Controller
public class ServiceController {
	@Autowired
	private ServiceService service;
	@RequestMapping("/introduceFrm.do")
	public String introduceFrm() {
		return "freelancer/introduce";
	}
}
