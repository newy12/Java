package com.ilgusi.chat.conroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ilgusi.chat.model.service.ChatService;

@Controller
public class ChatController {
	@Autowired
	private ChatService service;
}
