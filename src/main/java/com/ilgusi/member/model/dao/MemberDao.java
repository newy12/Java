package com.ilgusi.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.service.model.vo.Join;

@Repository
public class MemberDao {

	@Autowired
	private SqlSessionTemplate session;

	
	
}
