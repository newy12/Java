package com.ilgusi.favorite.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.favorite.model.dao.FavoriteDao;

@Service
public class FavoriteService {
	@Autowired
	private FavoriteDao dao;
	
	//(문정)사용자 마이페이지-찜한 목록 불러오기 (정렬)
	public ArrayList<com.ilgusi.service.model.vo.Service> selectHeartList(int mNo, String order) {
		return dao.selectHeartList(mNo, order);
	}

	//(문정)사용자 마이페이지-찜한 목록 중 브랜드 이름 가져오기
	public ArrayList<String> selectBrandName(int mNo, String order) {
		return dao.selectBrandName(mNo,order);
	}

	//(문정)사용자 마이페이지 - 찜한거 삭제
	public int deleteHeart(int sNo, int mNo) {
		return dao.deleteHeart(sNo, mNo);
	}

	//(문정)사용자마이페이지 - 찜한 목록에 추가
	public int insertHeart(int sNo, int mNo) {
		return dao.insertHeart(sNo, mNo);
	}
}
