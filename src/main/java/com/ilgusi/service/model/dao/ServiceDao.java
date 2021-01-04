package com.ilgusi.service.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceDao {
	@Autowired
	private SqlSessionTemplate session;
}
