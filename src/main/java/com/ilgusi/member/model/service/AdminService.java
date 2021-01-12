package com.ilgusi.member.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.member.model.dao.AdminDao;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.ServiceTrade;

@Service
public class AdminService {

	@Autowired
	private AdminDao dao;

	// (소현)관리자-전체회원조회
	public ArrayList<Member> selectAllMember() {
		return dao.selectAllMember();
	}

	// (소현)회원별 서비스 이용횟수
	public int countHistory(int mNo) {
		return dao.countHistory(mNo);
	}

	// (소현)관리자-전체서비스불러오기
	public ArrayList<com.ilgusi.service.model.vo.Service> selectAllService() {
		return dao.selectAllService();
	}

	// (소현)서비스 승인
	public int acceptService(int sNo) {
		return dao.acceptService(sNo);
	}

	// (소현)관리자-서비스거절창에 서비스정보보내기
	public ArrayList<com.ilgusi.service.model.vo.Service> selectService(int sNo) {
		return dao.selectService(sNo);
	}

	// (소현)서비스 삭제
	public int deleteService(int sNo) {
		return dao.deleteService(sNo);
	}

	// (소현)작업내역 조회
	public ArrayList<ServiceTrade> workingCount(int sNo) {
		return dao.workingCount(sNo);
	}

	// (소현)이용내역 조회
	public ArrayList<ServiceTrade> useCount(int mNo) {
		return dao.useCount(mNo);
	}

	//(소현)서비스 등록 거절
	public void rejectService(int sNo) {
		dao.rejectService(sNo);
	}

}
