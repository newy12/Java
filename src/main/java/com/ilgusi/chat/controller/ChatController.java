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
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Service;
import com.ilgusi.service.model.vo.ServiceTrade;

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
		} else {

			// 방이 아직 없으면 생성
			service.createChat(room);
			Chat chat = service.selectOneRoom(room);
			int cNo = chat.getCNo();

			// 관리자와의 채팅방일경우
			if (sNo == 0) {
				HashMap<String, Integer> result = new HashMap<String, Integer>();
				result.put("cNo", cNo);
				return result;
			} else {
				// 관리자와의 방이 아닐때
				// 생성한 방에 기본메세지 insert
				String msg = "문의를 시작합니다!";

				room.put("cNo", cNo);
				room.put("welcomeMsg", msg);
				service.insertChat(room);
			}

			// 찜한 서비스에서 문의시작 했을경우
			// 채팅방 만든 서비스는 찜한 서비스에서 삭제
			Favorite oneFavorite = new Favorite();
			oneFavorite.setMNo(mNo);
			oneFavorite.setSNo(sNo);
			service.deleteOneFavorite(oneFavorite);
		}
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
		System.out.println("메세지 저장 완료");
	}

	// (소현)채팅방 리스트 불러오기
	@RequestMapping("/chatList.do")
	public String chatList(Model model, String mGrade, String mId) {

		ArrayList<Chat> roomList = new ArrayList<Chat>();
		ArrayList<Map<String, Object>> roomInfo = new ArrayList<Map<String, Object>>();

		// 일반회원일때
		if (mGrade.equals("1")) {
			// 일반회원인지 프리랜서인지 저장
			HashMap<String, String> myInfo = new HashMap<String, String>();
			myInfo.put("user", mId);
			roomList = service.selectRoomList(myInfo);

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
		} else if (mGrade.equals("2")) {
			HashMap<String, String> myInfo = new HashMap<String, String>();
			myInfo.put("free", mId);
			roomList = service.selectRoomList(myInfo);

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
				room.put("freeId", oneRoom.getUserId());
				room.put("userId", mId);
				room.put("lastMsg", lastMsg.getCContent());
				room.put("lastTime", lastMsg.getCTime());
				roomInfo.add(room);
			}
		}

		model.addAttribute("room", roomInfo);
		model.addAttribute("chatList", roomList);
		return "chat/chatList";
	}

	// (소현)채팅방 입장
	@RequestMapping("/enterRoom.do")
	public String enterRoom(Model model, int cNo, int sNo, String yourId) {
		// 채팅방 번호로 채팅내용 불러오기
		ArrayList<ChatContent> content = service.chatContentList(cNo);

		// 서비스 정보
		ArrayList<Service> serviceList = service.selectService(sNo);
		Service oneService = serviceList.get(0);

		// 상대가 보낸 메세지 읽음처리 readStatus=0
		// 보낸사람이 내 아이디가 아닌 메세지 찾기

		//견적서 작성여부
		Member oneUser = service.selectOneMember(yourId);
		int mNo = oneUser.getMNo();
		HashMap<String, Integer> tradeInfo = new HashMap<String, Integer>();
		//리스트로 불러와야함
		
		
		model.addAttribute("cNo", cNo);
		model.addAttribute("freeId", yourId);
		model.addAttribute("content", content);
		model.addAttribute("service", oneService);
		return "chat/chatContent";
	}

	// (소현)알림 메세지 room입장
	@RequestMapping("/selectNotification.do")
	public String selectNotification(Model model, String mId) {
		// mId와 관리자 room 찾기
		HashMap<String, Object> room = new HashMap<String, Object>();
		room.put("sNo", 0);
		room.put("userId", mId);
		room.put("freeId", "admin");

		Chat noti = service.selectOneRoom(room);
		int cNo = 0;
		// 이미 있는 방이면
		if (noti != null) {
			cNo = noti.getCNo();
		} else {
			cNo = -1;
		}

		// 채팅방 번호로 채팅내용 불러오기
		ArrayList<ChatContent> content = service.chatContentList(cNo);

		ArrayList<Service> serviceList = service.selectService(0);
		Service oneService = serviceList.get(0);

		model.addAttribute("cNo", cNo);
		model.addAttribute("freeId", "admin");
		model.addAttribute("content", content);
		model.addAttribute("service", oneService);
		return "chat/chatContent";
	}

	// (소현)채팅방 삭제--------------------------------------------------------
	@RequestMapping("/deleteChat.do")
	public String deleteChat(Model model, int cNo, String myId) {
		service.deleteChat(cNo);
		return "chat/chatList";
	}

	// (소현)견적서 작성
	@RequestMapping("/quotationFrm.do")
	public String quotationFrm(Model model, String sNo, String sTitle, String userId, String freeId) {
		// 회원userId의 mNo
		Member oneUser = service.selectOneMember(userId);
		int mNo = oneUser.getMNo();
		model.addAttribute("mNo", mNo);
		model.addAttribute("sNo", sNo);
		model.addAttribute("sTitle", sTitle);
		model.addAttribute("userId", userId);
		model.addAttribute("freeId", freeId);
		return "chat/quotation";
	}

	// (소현)serviceTrade에 insert
	@ResponseBody
	@RequestMapping("/startTrade.do")
	public void startTrade(int sNo, int mNo, String price, String start, String end) {
		HashMap<String, Object> tradeInfo = new HashMap<String, Object>();
		tradeInfo.put("sNo", sNo);
		tradeInfo.put("mNo", mNo);
		tradeInfo.put("price", price);
		tradeInfo.put("start", start);
		tradeInfo.put("end", end);
		service.startTrade(tradeInfo);
	}
	
	//(소현)service working_conut 1증가
	@ResponseBody
	@RequestMapping("/updateCount.do")
	public void updateWorkingCount(int sNo) {
		service.updateWorkingCount(sNo);
	}

	// (소현)프리랜서의 서비스 보여주기
	@RequestMapping("/chatServiceList.do")
	public String chatServiceList(Model model) {

		return "chat/chatServiceList";
	}

}
