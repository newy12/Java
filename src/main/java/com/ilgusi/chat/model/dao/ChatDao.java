package com.ilgusi.chat.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.chat.model.vo.Chat;
import com.ilgusi.chat.model.vo.ChatContent;
import com.ilgusi.favorite.model.vo.Favorite;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Service;
import com.ilgusi.service.model.vo.ServiceInfo;
import com.ilgusi.service.model.vo.ServiceTrade;

@Repository
public class ChatDao {
	@Autowired
	private SqlSessionTemplate session;

	// (소현)찜한 서비스 불러오기
	public ArrayList<Service> chatHeartList(int mNo) {
		List<Service> list = session.selectList("favorite.chatHeartList", mNo);
		return (ArrayList<Service>) list;
	}

	// (소현)서비스 정보 불러오기
	public ArrayList<ServiceInfo> selectService(int sNo) {
		List<ServiceInfo> list = session.selectList("service.selectServiceInfo", sNo);
		return (ArrayList<ServiceInfo>) list;
	}

	// (소현)채팅방 생성
	public void createChat(HashMap<String, Object> room) {
		session.insert("chat.createChat", room);
	}

	// (소현)일반회원아이디로 채팅방 불러오기
	public ArrayList<Chat> selectRoomList(HashMap<String, String> myInfo) {
		List<Chat> list = session.selectList("chat.roomList", myInfo);
		return (ArrayList<Chat>) list;
	}

	// (소현)채팅방 불러오기
	public Chat selectOneRoom(HashMap<String, Object> room) {
		return session.selectOne("chat.selectOneRoom", room);
	}

	// (소현)보낸메세지 저장
	public void insertChat(HashMap<String, Object> message) {	
		System.out.println(message.get("content"));
		session.insert("chat.insertChat", message);
	}

	// (소현)채팅방 삭제
	public void deleteChat(int cNo) {
		session.delete("chat.deleteChat", cNo);
	}

	// (소현)문의 시작한 서비스 찜리스트에서 삭제
	public void deleteOneFavorite(Favorite oneFavorite) {
		session.delete("favorite.deleteHeart", oneFavorite);
	}

	// (소현)해당 번호 채팅방에서 대화 불러오기
	public ArrayList<ChatContent> chatContentList(int roomNo) {
		List<ChatContent> list = session.selectList("chat.chatContent", roomNo);
		return (ArrayList<ChatContent>) list;
	}

	// (소현)아이디로 회원 불러오기
	public Member selectOneMember(String userId) {
		return session.selectOne("member.selectAllMember", userId);
	}

	// (소현)serviceTrade에 insert
	public void startTrade(HashMap<String, Object> tradeInfo) {
		session.insert("trade.insertTrade", tradeInfo);
	}

	// (소현)service working_conut 1증가
	public void updateWorkingCount(int sNo) {
		session.update("service.updateCount", sNo);
	}

	// (소현)일반회원-프리랜서 전환
	public void switchAccount(HashMap<String, Object> map) {
		session.update("member.switchAccount",map);
	}

	//상대가 보낸 메세지 읽음 처리
	public void updateReadStatus(HashMap<String, Object> roomAndId) {
		session.update("chat.updateReadStatus",roomAndId);
	}

	//거래내역 불러와서 견적서 작성여부 확인
	public ArrayList<ServiceTrade> tradeList(HashMap<String, Integer> tradeInfo) {
		List<ServiceTrade> list = session.selectList("trade.tradeList", tradeInfo);
		return (ArrayList<ServiceTrade>) list;
	}

	//(소현)메세지 삭제
	public void deleteMsg(int ccNo) {
		session.delete("chat.deleteMsg",ccNo);
	}

}
