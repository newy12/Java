package com.ilgusi.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.member.model.dao.MemberDao;
import com.ilgusi.service.model.vo.Join;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;

	

	
	public Member loginMember(String id, String pw) {
		return dao.loginMember(id,pw);
	}
}