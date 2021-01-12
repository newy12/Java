package com.ilgusi.member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Service;
import com.ilgusi.service.model.vo.ServiceTrade;

@Repository
public class AdminDao {

	@Autowired
	private SqlSessionTemplate session;

	// (소현)관리자-전체회원조회
	public ArrayList<Member> selectAllMember() {
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

	// (소현)서비스 승인
	public int acceptService(int sNo) {
		return session.update("service.acceptService", sNo);
	}

	// (소현)관리자-서비스거절창에 서비스정보보내기
	public ArrayList<Service> selectService(int sNo) {
		List<Service> list = session.selectList("service.selectService", sNo);
		return (ArrayList<Service>) list;
	}

	// (소현)서비스 등록 거절
	public void rejectService(int sNo) {
		session.update("service.rejectService", sNo);
	}

	// (소현)서비스 삭제
	public int deleteService(int sNo) {
		return session.delete("service.deleteService", sNo);
	}

	// (소현)작업내역 조회
	public ArrayList<ServiceTrade> workingCount(int sNo) {
		List<ServiceTrade> history = session.selectList("service.tradeHistory", sNo);
		return (ArrayList<ServiceTrade>) history;
	}

	// (소현)이용내역 조회
	public ArrayList<ServiceTrade> useCount(int mNo) {
		List<ServiceTrade> history = session.selectList("service.userHistory", mNo);
		return (ArrayList<ServiceTrade>) history;
	}

}
