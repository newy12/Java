package com.ilgusi.chat.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.favorite.model.vo.Favorite;
import com.ilgusi.service.model.vo.Service;

@Repository
public class ChatDao {
	@Autowired
	private SqlSessionTemplate session;

	// 채팅창에서 찜한 목록 불러오기
	public ArrayList<Favorite> selectAllFavorite(int memberNo) {
		List<Favorite> list = session.selectList("favorite.selectAllFavorite", memberNo);
		return (ArrayList<Favorite>) list;
	}

	// 서비스 정보 불러오기
	public Service selectOneService(int serviceNo) {
		return session.selectOne("service.selectOneService", serviceNo);
	}

	// 채팅방 생성
	public void createChat(HashMap<String, Object> room) {
		session.insert("chat.createChat", room);
	}

	//채팅방 번호 불러오기
	public int selectOneRoom(HashMap<String, Object> room) {
		return session.selectOne("chat.selectOneRoom",room);
	}

	//보낸메세지 저장
	public void insertChat(HashMap<String, Object> message) {
		session.insert("chat.insertChat",message);
		
	}

}
