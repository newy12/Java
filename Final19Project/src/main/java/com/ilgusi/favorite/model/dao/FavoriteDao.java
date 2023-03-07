package com.ilgusi.favorite.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.favorite.model.vo.Favorite;
import com.ilgusi.favorite.model.vo.FavoriteOrder;
import com.ilgusi.service.model.vo.Service;

@Repository
public class FavoriteDao {
	@Autowired
	private SqlSessionTemplate session;
	
	//(문정)사용자 마이페이지-찜한 목록 불러오기 (정렬)
	public ArrayList<Service> selectHeartList(int mNo, String order) {
		FavoriteOrder fo = new FavoriteOrder();
		fo.setMNo(mNo);
		fo.setOrder(order);
		List<Service> list = session.selectList("favorite.selectHeartList", fo);
		return (ArrayList<Service>)list;
	}

	//(문정)사용자 마이페이지-찜한 목록 중 브랜드 이름 가져오기
	public ArrayList<String> selectBrandName(int mNo, String order) {
		FavoriteOrder fo = new FavoriteOrder();
		fo.setMNo(mNo);
		fo.setOrder(order);
		List<String> brandnameList = session.selectList("favorite.selectBrandName", fo);
		return (ArrayList<String>)brandnameList;
	}

	//(문정)사용자 마이페이지 - 찜한거 삭제
	public int deleteHeart(int sNo, int mNo) {
		Favorite f = new Favorite();
		f.setSNo(sNo);
		f.setMNo(mNo);
		return session.delete("favorite.deleteHeart",f);
	}

	//(문정)사용자마이페이지 - 찜한 목록에 추가
	public int insertHeart(int sNo, int mNo) {
		Favorite f = new Favorite();
		f.setSNo(sNo);
		f.setMNo(mNo);
		return session.insert("favorite.insertHeart",f);
	}
}
