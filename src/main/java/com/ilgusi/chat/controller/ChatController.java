package com.ilgusi.chat.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilgusi.chat.model.service.ChatService;
import com.ilgusi.chat.model.vo.Chat;
import com.ilgusi.favorite.model.service.FavoriteService;
import com.ilgusi.favorite.model.vo.Favorite;
import com.ilgusi.service.model.vo.Service;

@Controller
public class ChatController {
	@Autowired
	private ChatService service;

	// (소현)찜한 서비스 불러오기
	@RequestMapping("/chatHeartList.do")
	public String heartList(Model model, int mNo) {
		ArrayList<Service> serviceList = service.chatHeartList(mNo);
		model.addAttribute("heartList", serviceList);
		return "chat/chatHeartList";
	}

	// (소현)채팅방 생성
	@ResponseBody
	@RequestMapping("/makeRoom.do")
	public void makeRoom(int sNo, String userId, String freeId, int mNo) {
		HashMap<String, Object> room = new HashMap<String, Object>();
		room.put("sNo", sNo);
		room.put("userId", userId);
		room.put("freeId", freeId);
		service.createChat(room);

		// 찜한 서비스에서 문의시작 했을경우
		// 채팅방 만든 서비스는 찜한 서비스에서 삭제
		Favorite oneFavorite = new Favorite();
		oneFavorite.setMNo(mNo);
		oneFavorite.setSNo(sNo);
		service.deleteOneFavorite(oneFavorite);

	}

	// (소현)채팅 시작
	@RequestMapping("/startChat.do")
	public String startChat(Model model, int sNo, String userId, String freeId) {
		// 문의하려는 서비스정보 가져오기
		ArrayList<Service> serviceList = service.selectService(sNo);
		Service oneService = serviceList.get(0);

		// 채팅방 생성
		HashMap<String, Object> room = new HashMap<String, Object>();
		room.put("sNo", sNo);
		room.put("userId", userId);
		room.put("freeId", freeId);
		// service.createChat(room);

		// 만든 방 가져오기
		int roomNo = service.selectOneRoom(room);
		model.addAttribute("service", oneService);
		model.addAttribute("yourId", freeId);
		model.addAttribute("roomNo", roomNo);
		return "chat/chatContent";
	}

	// (소현)채팅방 불러오기
	@RequestMapping("/chatList.do")
	public String chatList(Model model, String mId) {
		ArrayList<Chat> roomList = service.selectRoomList(mId);
		model.addAttribute("chatList", roomList);
		return "chat/chatList";
	}

	// (소현)채팅내용 db저장
	@ResponseBody
	@RequestMapping("/insertChat.do")
	public void insertChat(int roomNo, String myId, String time, String content) {
		HashMap<String, Object> message = new HashMap<String, Object>();
		message.put("cNo", roomNo);
		message.put("myId", myId);
		message.put("time", time);
		message.put("content", content);
		service.insertChat(message);
	}

	// (소현)채팅방 삭제
	@RequestMapping("/deleteChat.do")
	public String deleteChat(Model model, int cNo, String myId) {
		service.deleteChat(cNo);
		return "chat/chatList";
	}

	@RequestMapping("/quotationFrm.do")
	public String quotationFrm() {
		return "chat/quotation";
	}

}
