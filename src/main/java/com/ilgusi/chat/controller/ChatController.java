package com.ilgusi.chat.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ilgusi.chat.model.service.ChatService;
import com.ilgusi.chat.model.vo.ChatRoom;
import com.ilgusi.favorite.model.vo.Favorite;
import com.ilgusi.service.model.vo.Service;

@Controller
public class ChatController {
	@Autowired
	private ChatService service;

	// 찜한 목록 불러오기
	@RequestMapping("/heartList.do")
	public String heartList(Model model, int memberNo) {
		ArrayList<Favorite> list = service.selectAllFavorite(memberNo);
		model.addAttribute("heartList", list);
		return "chat/heartList";
	}

	// 채팅 시작
	@RequestMapping("/startChat.do")
	public String startChat(Model model, int serviceNo, String myId, String yourId) {
		// 문의하려는 서비스정보 가져오기
		Service oneService = service.selectOneService(serviceNo);

		// 채팅방 생성
		ChatRoom room=new ChatRoom(serviceNo,myId,yourId);
		service.createChat(room);
		
		model.addAttribute("service", oneService);
		model.addAttribute("yourId", yourId);
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

}
