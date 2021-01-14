package com.ilgusi.request.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.request.model.vo.Request;
import com.ilgusi.service.model.vo.Service;

@Repository
public class RequestDao {
	@Autowired
	private SqlSessionTemplate session;

	//(문정) 의뢰글 작성
	public int requestInsert(Request req) {
		return session.insert("request.insertBoard", req);
	}

	//(문정) 의뢰게시판 총 개수
	public int totalCount(String subject, String keyword) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("subject", subject);
		map.put("keyword", keyword);
		return session.selectOne("request.totalCount", map);
	}

	//(문정) 페이징 리스트 
	public ArrayList<Request> selectRequestList(int start, int end, String order, String subject, String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("order", order);
		map.put("subject", subject);
		map.put("keyword", keyword);
		List<Request> list = session.selectList("request.selectRequestList",map );
		return (ArrayList<Request>)list;
	}

	//(문정) 의뢰게시판 상세보기
	public Request selectOneRequest(int reqNo) {
		return session.selectOne("request.selectOneRequest", reqNo);
	}

	//(문정) 의뢰 수정
	public int requestUpdate(Request req) {
		return session.update("request.requestUpdate",req);
	}

	//(문정) 의뢰글 삭제
	public int requestDeleteOne(int reqNo) {
		return session.delete("request.requestDeleteOne", reqNo);
	}

	//(문정) 판매자의 리스트
	public ArrayList<Service> selectList(String freeId) {
		List<Service> list = session.selectList("request.selectList", freeId );
		return (ArrayList<Service>)list;
	}
}
