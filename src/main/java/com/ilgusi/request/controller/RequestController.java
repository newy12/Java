package com.ilgusi.request.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ilgusi.request.model.service.RequestService;

@Controller
public class RequestController {
	@Autowired
	private RequestService service;
	
	//(문정)의뢰게시판 리스트
	@RequestMapping("/requestList.do")
	public String requestList() {
		return "request/requestList";
	}
	
	//(문정)작성폼으로 이동
	@RequestMapping("/requestWriteFrm.do")
	public String requestWriteFrm() {
		return "request/requestWriteFrm";
	}
}
