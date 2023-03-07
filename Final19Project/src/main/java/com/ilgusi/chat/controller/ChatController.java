package com.ilgusi.chat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilgusi.chat.model.service.ChatService;
import com.ilgusi.chat.model.vo.Chat;
import com.ilgusi.chat.model.vo.ChatContent;
import com.ilgusi.favorite.model.vo.Favorite;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Service;
import com.ilgusi.service.model.vo.ServiceInfo;
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

		if (oneChat != null) {// 이미 있는 방이면
			// 이미 있는 방일때 return값
			HashMap<String, Integer> result = new HashMap<String, Integer>();
			int cNo = oneChat.getCNo();
			result.put("cNo", cNo);

			// 관리자가 메세지 보낼때
			if (sNo == 0) {
				return result;
			} else {////////////////////////////////////////////////////////// 회원이 프리랜서에게 문의버튼 눌렀는데
					////////////////////////////////////////////////////////// 원래 있는 방일때
				return result;

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

	// (소현)채팅방 리스트 불러오기
	@RequestMapping("/chatList.do")
	public String chatList(HttpServletRequest req, Model model, String mGrade, String mId) {

		ArrayList<Chat> roomList = new ArrayList<Chat>();
		ArrayList<Map<String, Object>> roomInfo = new ArrayList<Map<String, Object>>();

		// 일반회원인지 프리랜서인지 저장
		HashMap<String, String> myInfo = new HashMap<String, String>();

		if (mGrade.equals("1")) {///////////////////////////////////////////////////// 일반회원일때
			System.out.println("나는 일반회원");
			System.out.println(mId);
			myInfo.put("user", mId);
			roomList = service.selectRoomList(myInfo);

			// 견적서 작성여부
			// yourId:상대 아이디, myId:내아이디
			HttpSession session = req.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");

			// 채팅방의 chat_content 불러와서 마지막 채팅내용,시간 조회
			for (int i = 0; i < roomList.size(); i++) {
				Chat oneRoom = roomList.get(i);
				int cNo = oneRoom.getCNo(); // 방번호
				int sNo = oneRoom.getSNo();
				System.out.println("방번호" + cNo);
				System.out.println("서비스번호" + sNo);
				if (sNo != 0) {
					ArrayList<ChatContent> content = service.chatContentList(cNo);
					int last = content.size() - 1;
					ChatContent lastMsg = content.get(last);
					String freeId = oneRoom.getFreelancerId();
					if(freeId==null) { //채팅하던 프리랜서가 탈퇴하면
						continue;
					}
					System.out.println("----------");
					System.out.println(freeId);
					Member oneMember = service.selectOneMember(freeId);
					String brandName = oneMember.getBrandName();
					ArrayList<ServiceInfo> serviceList = service.selectService(sNo);
					ServiceInfo oneService = serviceList.get(0);
					String serviceTitle = oneService.getSTitle();

					///////////////////////////////////////////////////////////////////////////////////////////////

					int status = -1;

					HashMap<String, Integer> tradeInfo = new HashMap<String, Integer>();

					// 내 회원번호로 견적서 작성 여부 확인
					int mNo = loginMember.getMNo();
					tradeInfo.put("sNo", sNo);
					tradeInfo.put("mNo", mNo);

					ArrayList<ServiceTrade> tradeList = service.tradeList(tradeInfo);
					if (tradeList.size() != 0) {
						if (tradeList.get(0) != null) { // 제일 최신 거래내역
							status = tradeList.get(0).getTStatus();
							System.out.println("나는 일반회원--거래상태 :" + status);
						}
					}
					/////////////////////////////////////////////////////////////////////////////////////////////////

					HashMap<String, Object> room = new HashMap<String, Object>();
					room.put("cNo", cNo);
					room.put("sNo", sNo);
					room.put("userId", mId);
					room.put("freeId", freeId);
					room.put("lastMsg", lastMsg.getCContent());
					room.put("lastDate", lastMsg.getCDate());
					room.put("lastTime", lastMsg.getCTime());
					room.put("read", lastMsg.getReadStatus());
					room.put("sender", lastMsg.getMId());
					room.put("brandName", brandName);
					room.put("serviceTitle", serviceTitle);
					room.put("status", status);
					roomInfo.add(room);
				}
			}
		} else if (mGrade.equals("2")) {////////////////////////////////////////////// 프리랜서일때
			System.out.println("나는 프리랜서");
			myInfo.put("free", mId);
			roomList = service.selectRoomList(myInfo);

			// 채팅방의 chat_content 불러와서 마지막 채팅내용,시간 조회
			for (int i = 0; i < roomList.size(); i++) {
				Chat oneRoom = roomList.get(i);
				int cNo = oneRoom.getCNo(); // 방번호
				int sNo = oneRoom.getSNo();
				if (sNo != 0) {
					ArrayList<ChatContent> content = service.chatContentList(cNo);
					int last = content.size() - 1;
					ChatContent lastMsg = content.get(last);
					/* String freeId = oneRoom.getFreelancerId(); */
					String userId = oneRoom.getUserId();
					String freeId = mId;
					Member oneMember = service.selectOneMember(freeId);
					String brandName = oneMember.getBrandName();
					ArrayList<ServiceInfo> serviceList = service.selectService(sNo);
					ServiceInfo oneService = serviceList.get(0);
					String serviceTitle = oneService.getSTitle();

					///////////////////////////////////////////////////////////////////////////////////////////////

					int status = -1;

					HashMap<String, Integer> tradeInfo = new HashMap<String, Integer>();

					// 프리랜서로 로그인했을때
					// 상대방번호,서비스번호로 거래 견적서 작성 여부 확인
					if (userId != null) {
						Member oneUser = service.selectOneMember(userId);
						int mNo = oneUser.getMNo();
						tradeInfo.put("sNo", sNo);
						tradeInfo.put("mNo", mNo);

						ArrayList<ServiceTrade> tradeList = service.tradeList(tradeInfo);
						if (tradeList.size() != 0) {
							if (tradeList.get(0) != null) { // 제일 최신 거래내역
								status = tradeList.get(0).getTStatus();
								System.out.println("나는 프리랜서--거래상태 :" + status);
							}
						}
					}
					/////////////////////////////////////////////////////////////////////////////////////////////////

					HashMap<String, Object> room = new HashMap<String, Object>();
					room.put("cNo", cNo);
					room.put("sNo", sNo);
					room.put("userId", mId);
					room.put("freeId", oneRoom.getUserId());
					room.put("lastMsg", lastMsg.getCContent());
					room.put("lastTime", lastMsg.getCTime());
					room.put("read", lastMsg.getReadStatus());
					room.put("sender", lastMsg.getMId());
					room.put("brandName", brandName);
					room.put("serviceTitle", serviceTitle);
					room.put("status", status);
					roomInfo.add(room);
				}
			}
		}

		model.addAttribute("room", roomInfo);
		// model.addAttribute("chatList", roomList);
		return "chat/chatList";
	}

	// (소현)채팅방 입장
	@RequestMapping("/enterRoom.do")
	public String enterRoom(HttpServletRequest req, Model model, int cNo, int sNo, String yourId, String myId,
			String mGrade) {

		if (mGrade == null) {

			if (myId == null) {/////////////////////////////////////////////////////////////// 채팅리스트에서 채팅방입장할때
				// 채팅방 번호로 채팅내용 불러오기
				ArrayList<ChatContent> content = service.chatContentList(cNo);

				// 서비스 정보
				ArrayList<ServiceInfo> serviceList = service.selectService(sNo);
				ServiceInfo oneService = serviceList.get(0);

				// 상대가 보낸 메세지 읽음으로 update
				HashMap<String, Object> roomAndId = new HashMap<String, Object>();
				roomAndId.put("mId", yourId);
				roomAndId.put("rNo", cNo);
				service.updateReadStatus(roomAndId);

				// 견적서 작성여부
				// yourId:상대 아이디, myId:내아이디
				HttpSession session = req.getSession();
				Member loginMember = (Member) session.getAttribute("loginMember");

				HashMap<String, Integer> tradeInfo = new HashMap<String, Integer>();

				// 프리랜서로 로그인했을때
				if (loginMember.getMGrade() == 2) {
					// 상대방번호,서비스번호로 거래 견적서 작성 여부 확인
					Member oneUser = service.selectOneMember(yourId);
					int mNo = oneUser.getMNo();
					tradeInfo.put("sNo", sNo);
					tradeInfo.put("mNo", mNo);

					// 의뢰인의 신고횟수가 4이상인지 확인
					int warningCount = oneUser.getWarningCount();
					System.out.println("myId_warningcount:" + warningCount);
					if (warningCount > 3) {
						model.addAttribute("black", "black");
					}

				} else {// 일반회원으로 로그인했을때
					// 내 회원번호로 견적서 작성 여부 확인
					int mNo = loginMember.getMNo();
					tradeInfo.put("sNo", sNo);
					tradeInfo.put("mNo", mNo);
				}

				ArrayList<ServiceTrade> tradeList = service.tradeList(tradeInfo);
				if (tradeList.size() != 0) {
					if (tradeList.get(0) != null) { // 제일 최신 거래내역
						int status = tradeList.get(0).getTStatus();
						System.out.println("거래상태 :" + status);
						model.addAttribute("status", status);
					}
				}

				model.addAttribute("cNo", cNo);
				model.addAttribute("freeId", yourId);
				model.addAttribute("content", content);
				model.addAttribute("service", oneService);
			}

			else if (sNo == 0) {///////////////////////////////////////////////////////// 알림 채팅방으로 입장할때
				// mId와 관리자 room 찾기
				HashMap<String, Object> room = new HashMap<String, Object>();
				room.put("sNo", sNo);
				room.put("userId", myId);
				room.put("freeId", yourId);

				Chat noti = service.selectOneRoom(room);
				int rNo = -1;
				// 이미 있는 방이면
				if (noti != null) {
					rNo = noti.getCNo();
				}

				// 상대가 보낸 메세지 읽음으로 update
				HashMap<String, Object> roomAndId = new HashMap<String, Object>();
				roomAndId.put("mId", "admin");
				roomAndId.put("rNo", rNo);
				service.updateReadStatus(roomAndId);

				// 채팅방 번호로 채팅내용 불러오기
				ArrayList<ChatContent> content = service.chatContentList(rNo);

				ArrayList<ServiceInfo> serviceList = service.selectService(0);
				ServiceInfo oneService = serviceList.get(0);

				model.addAttribute("cNo", rNo);
				model.addAttribute("freeId", yourId);
				model.addAttribute("content", content);
				model.addAttribute("service", oneService);

			}
		} else { // mGrade값을 주면

			// 문의하려는 서비스정보 가져오기
			ArrayList<ServiceInfo> serviceList = service.selectService(sNo);
			ServiceInfo oneService = serviceList.get(0);

			// 채팅방 정보
			HashMap<String, Object> room = new HashMap<String, Object>();
			room.put("sNo", sNo);
			room.put("userId", myId);
			room.put("freeId", yourId);

			// 만든 방 가져오기
			Chat oneRoom = service.selectOneRoom(room);
			int rNo = oneRoom.getCNo();

			HashMap<String, Object> roomAndId = new HashMap<String, Object>();
			if (mGrade.equals("1")) {
				// 상대가 보낸 메세지 읽음으로 update
				roomAndId.put("mId", yourId);
				roomAndId.put("rNo", rNo);
				service.updateReadStatus(roomAndId);

			} else if (mGrade.equals("2")) {///////////////////////////////////////// 프리랜서가 의뢰글보고 채팅시작할때
				// 상대가 보낸 메세지 읽음으로 update
				System.out.println("내아이디:"+yourId);
				System.out.println("의뢰인아이디:"+myId);
				roomAndId.put("mId", myId);
				roomAndId.put("rNo", rNo);
				service.updateReadStatus(roomAndId);
				// 의뢰인의 신고횟수가 4이상인지 확인
				Member oneMember = service.selectOneMember(myId);
				int warningCount = oneMember.getWarningCount();
				System.out.println("mId_warningcount:" + warningCount);

				if (warningCount > 3) {
					model.addAttribute("black", "black");
				}

			}

			// 채팅방 번호로 채팅내용 불러오기
			ArrayList<ChatContent> content = service.chatContentList(rNo);

			model.addAttribute("content", content);
			model.addAttribute("service", oneService);
			model.addAttribute("userId", myId);
			model.addAttribute("freeId", yourId);
			model.addAttribute("cNo", rNo);

		}
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

	// (소현)채팅방 삭제
	@ResponseBody
	@RequestMapping("/deleteChat.do")
	public String deleteChat(int cNo) {
		service.deleteChat(cNo);
		return "";
	}

	// (소현)메세지 삭제
	@ResponseBody
	@RequestMapping("/deleteMsg.do")
	public String deleteMsg(int ccNo) {
		service.deleteMsg(ccNo);
		return "";
	}

	// (소현)견적서 작성
	@RequestMapping("/quotationFrm.do")
	public String quotationFrm(Model model, int sNo, String sTitle, String userId, String freeId) {
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
		service.updateWorkingCount(sNo);

	}

	// (소현)일반회원-프리랜서 전환
	@ResponseBody
	@RequestMapping("switchAccount.do")
	public int switchAccount(HttpServletRequest req, String mId, int mGrade) {
		HttpSession session = req.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");

		// 아이디와 전환할 grade값 전달할 map
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mId", mId);

		if (mGrade == 1) {// 지금 일반회원일때
			// 프리랜서로 바꾼적이 있는지 확인 - 브랜드이름 유무O
			Member member = service.selectOneMember(mId);
			String brandName = member.getBrandName();

			if (brandName == null) { // 브랜드 이름 없으면
				return -1;
			} else {// 브랜드 이름 있으면

				// m_grade=2로 바꿔줌
				loginMember.setMGrade(2);
				session.setAttribute("loginMember", loginMember);

				return 2;
			}

		} else if (mGrade == 2) {// 지금 프리랜서일때

			// m_grade=1로 바꿔줌
			loginMember.setMGrade(1);
			session.setAttribute("loginMember", loginMember);

			return 1;
		}

		return 0;
	}

}
