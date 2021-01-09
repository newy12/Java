package com.ilgusi.chat.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.chat.model.dao.ChatDao;
import com.ilgusi.favorite.model.vo.Favorite;

@Service
public class ChatService {
	@Autowired
	private ChatDao dao;

	// 채팅창에서 찜한 목록 불러오기
	public ArrayList<Favorite> selectAllFavorite(int memberNo) {
		return dao.selectAllFavorite(memberNo);
	}

	// 찜한 서비스 정보 불러오기
	public ArrayList<com.ilgusi.service.model.vo.Service> selectService(int sNo) {
		return dao.selectService(sNo);
	}

	// 채팅방 생성
	public void createChat(HashMap<String, Object> room) {
		dao.createChat(room);
	}

	//채팅방 번호 불러오기
	public int selectOneRoom(HashMap<String, Object> room) {
		return dao.selectOneRoom(room);
	}

	//보낸메세지 저장
	public void insertChat(HashMap<String, Object> message) {
		dao.insertChat(message);
		
	}

}
