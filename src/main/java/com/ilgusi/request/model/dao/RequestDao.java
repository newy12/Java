package com.ilgusi.request.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.request.model.vo.Request;

@Repository
public class RequestDao {
	@Autowired
	private SqlSessionTemplate session;

	//(문정) 의뢰글 작성
	public int requestInsert(Request req) {
		return session.insert("request.insertBoard", req);
	}

	//(문정) 의뢰게시판 총 개수
	public int totalCount() {
		return session.selectOne("request.totalCount");
	}

	//(문정) 페이징 리스트 
	public ArrayList<Request> selectRequestList(int start, int end) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		List<Request> list = session.selectList("request.selectRequestList",map );
		return (ArrayList<Request>)list;
	}

	//(문정) 의뢰게시판 상세보기
	public Request selectOneRequest(int reqNo) {
		return session.selectOne("request.selectOneRequest", reqNo);
	}
}
