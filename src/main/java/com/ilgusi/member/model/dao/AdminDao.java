package com.ilgusi.member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Service;

@Repository
public class AdminDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	// (소현)관리자-전체회원조회
	public ArrayList<Member> manageMember() {
		List<Member> list = session.selectList("member.selectAllMember");
		return (ArrayList<Member>) list;
	}

	// (소현)회원별 서비스 이용횟수
	public int countHistory(int mNo) {
		return session.selectOne("member.countHistory", mNo);
	}
	
	// (소현)관리자-전체서비스불러오기
	public ArrayList<Service> selectAllService() {
		List<Service> list = session.selectList("service.selectService");
		return (ArrayList<Service>) list;
	}

}
