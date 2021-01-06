package com.ilgusi.chat.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.chat.model.dao.ChatDao;
import com.ilgusi.chat.model.vo.ChatRoom;
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
	public com.ilgusi.service.model.vo.Service selectOneService(int serviceNo) {
		return dao.selectOneService(serviceNo);
	}

	// 채팅방 생성
	public void createChat(ChatRoom room) {
		dao.createChat(room);
	}

}
