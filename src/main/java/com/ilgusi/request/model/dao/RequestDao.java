package com.ilgusi.request.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.request.model.vo.Request;

@Repository
public class RequestDao {
	@Autowired
	private SqlSessionTemplate session;

	public int requestInsert(Request req) {
		return session.insert("request.insertBoard", req);
	}
}
