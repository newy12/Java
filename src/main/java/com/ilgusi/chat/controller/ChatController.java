package com.ilgusi.chat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilgusi.chat.model.service.ChatService;
import com.ilgusi.chat.model.vo.Chat;
import com.ilgusi.chat.model.vo.ChatContent;
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
	public HashMap<String, Integer> makeRoom(int sNo, String userId, String freeId, int mNo) {
		HashMap<String, Object> room = new HashMap<String, Object>();
		room.put("sNo", sNo);
		room.put("userId", userId);
		room.put("freeId", freeId);

		// 이미 생성된 방인지 확인
		Chat oneChat = service.selectOneRoom(room);

		// 이미 있는 방이면
		if (oneChat != null) {
			// 이미 있는 방일때 return값
			HashMap<String, Integer> result = new HashMap<String, Integer>();
			int cNo = oneChat.getCNo();
			result.put("cNo", cNo);
			
			// 관리자가 메세지 보낼때
			if (sNo == 0) {
				return result;
			} else {
				// 회원이 프리랜서에게 문의버튼 눌렀는데 원래 있는 방일때
			}
		}else {

		// 방이 아직 없으면 생성
		service.createChat(room);
		Chat chat=service.selectOneRoom(room);
		int cNo = chat.getCNo();
		
		// 생성한 방에 기본메세지 insert
		String msg = "채팅방생성!";
		
		room.put("cNo", cNo);
		room.put("welcomeMsg", msg);
		service.insertChat(room);

		// 관리자와의 채팅방일경우
		if (sNo == 0) {
			HashMap<String, Integer> result = new HashMap<String, Integer>();
			result.put("cNo", cNo);
			return result;
		}

		// 찜한 서비스에서 문의시작 했을경우
		// 채팅방 만든 서비스는 찜한 서비스에서 삭제
		Favorite oneFavorite = new Favorite();
		oneFavorite.setMNo(mNo);
		oneFavorite.setSNo(sNo);
		service.deleteOneFavorite(oneFavorite);}
		return null;
	}

	// (소현)채팅 시작
	@RequestMapping("/startChat.do")
	public String startChat(Model model, int sNo, String userId, String freeId) {
		// 문의하려는 서비스정보 가져오기
		ArrayList<Service> serviceList = service.selectService(sNo);
		Service oneService = serviceList.get(0);

		// 채팅방 정보
		HashMap<String, Object> room = new HashMap<String, Object>();
		room.put("sNo", sNo);
		room.put("userId", userId);
		room.put("freeId", freeId);

		// 만든 방 가져오기
		Chat oneRoom = service.selectOneRoom(room);
		int cNo = oneRoom.getCNo();

		model.addAttribute("service", oneService);
		model.addAttribute("userId", userId);
		model.addAttribute("freeId", freeId);
		model.addAttribute("cNo", cNo);
		return "chat/chatContent";
	}

	// (소현)채팅내용 db저장
	@ResponseBody
	@RequestMapping("/insertChat.do")
	public void insertChat(int cNo, String myId, String date, String time, String content) {
		HashMap<String, Object> message = new HashMap<String, Object>();
		message.put("cNo", cNo);
		message.put("myId", myId);
		message.put("date", date);
		message.put("time", time);
		message.put("content", content);
		service.insertChat(message);
	}

	// (소현)채팅방 리스트 불러오기
	@RequestMapping("/chatList.do")
	public String chatList(Model model, String mId) {
		ArrayList<Chat> roomList = service.selectRoomList(mId);
		ArrayList<Map<String, Object>> roomInfo = new ArrayList<Map<String, Object>>();

		// 채팅방의 chat_content 불러와서 마지막 채팅내용,시간 조회
		for (int i = 0; i < roomList.size(); i++) {
			Chat oneRoom = roomList.get(i);
			int cNo = oneRoom.getCNo(); // 방번호
			int sNo = oneRoom.getSNo();
			ArrayList<ChatContent> content = service.chatContentList(cNo);
			int last = content.size() - 1;
			ChatContent lastMsg = content.get(last);
			HashMap<String, Object> room = new HashMap<String, Object>();
			room.put("cNo", cNo);
			room.put("sNo", sNo);
			room.put("userId", mId);
			room.put("freeId", oneRoom.getFreelancerId());
			room.put("lastMsg", lastMsg.getCContent());
			room.put("lastTime", lastMsg.getCTime());
			roomInfo.add(room);
		}

		model.addAttribute("room", roomInfo);
		model.addAttribute("chatList", roomList);
		return "chat/chatList";
	}

	// (소현)채팅방 입장
	@RequestMapping("/enterRoom.do")
	public String enterRoom(Model model, int cNo, int sNo, String freeId) {
		// 채팅방 번호로 채팅내용 불러오기
		ArrayList<ChatContent> content = service.chatContentList(cNo);

		// 서비스 정보
		ArrayList<Service> serviceList = service.selectService(sNo);
		Service oneService = serviceList.get(0);

		// 상대가 보낸 메세지 읽음처리 readStatus=0
		// 보낸사람이 내 아이디가 아닌 메세지 찾기

		model.addAttribute("cNo", cNo);
		model.addAttribute("freeId", freeId);
		model.addAttribute("content", content);
		model.addAttribute("service", oneService);
		return "chat/chatContent";
	}

	// (소현)채팅방 삭제
	@RequestMapping("/deleteChat.do")
	public String deleteChat(Model model, int cNo, String myId) {
		service.deleteChat(cNo);
		return "chat/chatList";
	}

	@RequestMapping("/quotationFrm.do")
	public String quotationFrm(Model model, String sTitle, String userId, String freeId) {
		model.addAttribute("sTitle", sTitle);
		model.addAttribute("userId", userId);
		model.addAttribute("freeId", freeId);
		return "chat/quotation";
	}

}
