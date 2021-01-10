package com.ilgusi.chat.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.chat.model.vo.Chat;
import com.ilgusi.favorite.model.vo.Favorite;
import com.ilgusi.service.model.vo.Service;

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
	public ArrayList<Service> selectService(int sNo) {
		List<Service> list = session.selectList("service.selectService", sNo);
		return (ArrayList<Service>) list;
	}

	// (소현)채팅방 생성
	public void createChat(HashMap<String, Object> room) {
		session.insert("chat.createChat", room);
	}

	// (소현)채팅방 불러오기
	public ArrayList<Chat> selectRoomList(String mId) {
		List<Chat> list = session.selectList("chat.roomList", mId);
		return (ArrayList<Chat>) list;
	}

	// (소현)채팅방 번호 불러오기
	public int selectOneRoom(HashMap<String, Object> room) {
		return session.selectOne("chat.selectOneRoom", room);
	}

	// (소현)보낸메세지 저장
	public void insertChat(HashMap<String, Object> message) {
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

}
