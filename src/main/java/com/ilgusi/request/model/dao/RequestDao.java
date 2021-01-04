package com.ilgusi.request.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RequestDao {
	@Autowired
	private SqlSessionTemplate session;
}
