package com.ilgusi.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ilgusi.chat.model.service.ChatService;

@Controller
public class ChatController {
	@Autowired
	private ChatService service;
	
	@RequestMapping("/startChat.do")
	public String startChat() {
		return "chat/chatContent";
	}
	
	@RequestMapping("/quotationFrm.do")
	public String quotationFrm() {
		return "chat/quotation";
	}
	
	@RequestMapping("/chatList.do")
	public String chatList() {
		return "chat/chatList";
	}
	
	@RequestMapping("/heartList.do")
	public String heartList() {
		return "chat/heartList";
	}
}
