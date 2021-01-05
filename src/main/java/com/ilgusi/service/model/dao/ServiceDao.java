package com.ilgusi.service.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Join;

@Repository
public class ServiceDao {
	@Autowired
	private SqlSessionTemplate session;

	public Join selectOneMember(Join join) {
		return session.selectOne("service.selectOneMember",join);
	}

	
}
