package com.ilgusi.chat.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.chat.model.dao.ChatDao;
import com.ilgusi.chat.model.vo.Chat;
import com.ilgusi.favorite.model.vo.Favorite;

@Service
public class ChatService {
	@Autowired
	private ChatDao dao;

	// (소현)찜한 서비스 불러오기
	public ArrayList<com.ilgusi.service.model.vo.Service> chatHeartList(int mNo) {
		return dao.chatHeartList(mNo);
	}

	// (소현)찜한 서비스 정보 불러오기
	public ArrayList<com.ilgusi.service.model.vo.Service> selectService(int sNo) {
		return dao.selectService(sNo);
	}

	// (소현)채팅방 생성
	public void createChat(HashMap<String, Object> room) {
		dao.createChat(room);
	}

	// (소현)채팅방 번호 불러오기
	public int selectOneRoom(HashMap<String, Object> room) {
		return dao.selectOneRoom(room);
	}

	// (소현)채팅방 불러오기
	public ArrayList<Chat> selectRoomList(String mId) {
		return dao.selectRoomList(mId);
	}

	// (소현)보낸메세지 저장
	public void insertChat(HashMap<String, Object> message) {
		dao.insertChat(message);
	}

	// (소현)채팅방삭제
	public void deleteChat(int cNo) {
		dao.deleteChat(cNo);
	}

	//(소현)문의 시작한 서비스 찜리스트에서 삭제
	public void deleteOneFavorite(Favorite oneFavorite) {
		dao.deleteOneFavorite(oneFavorite);
	}

	

}
