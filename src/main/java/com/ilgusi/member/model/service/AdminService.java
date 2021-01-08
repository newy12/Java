package com.ilgusi.member.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.member.model.dao.AdminDao;
import com.ilgusi.member.model.vo.Member;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao dao;
	
	// (소현)관리자-전체회원조회
	public ArrayList<Member> manageMember() {
		return dao.manageMember();
	}

	// (소현)회원별 서비스 이용횟수
	public int countHistory(int mNo) {
		return dao.countHistory(mNo);
	}
	
	//(소현)관리자-전체서비스불러오기
	public ArrayList<com.ilgusi.service.model.vo.Service> selectAllService() {
		return dao.selectAllService();
	}

}
