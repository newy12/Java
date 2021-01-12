package com.ilgusi.service.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Service;
import com.ilgusi.service.model.vo.ServiceTrade;

@Repository
public class ServiceTradeDao {
	@Autowired
	private SqlSessionTemplate session;

	//(문정)사용자 마이페이지-거래내역 불러오기
	public ArrayList<ServiceTrade> selectTradeList(int mNo) {
		List<ServiceTrade> list = session.selectList("trade.selectTradeList", mNo);
		return (ArrayList<ServiceTrade>)list;
	}

	//(문정)사용자 마이페이지-거래에 해당하는 서비스 불러오기
	public ArrayList<Service> selectServiceList(int mNo) {
		List<Service> list = session.selectList("trade.selectServiceList", mNo);
		return (ArrayList<Service>)list;
	}

	//(문정)사용자 마이페이지-결제시간 불러오기 
	public ArrayList<String> selectPayDateList(int mNo) {
		List<String> list = session.selectList("trade.selectPayDateList", mNo);
		return (ArrayList<String>)list;
	}

	//(문정)사용자 마이페이지 - 거래 세부 내용 불러오기
	public ServiceTrade serviceTradeView(int tNo) {
		return session.selectOne("trade.serviceTradeView", tNo);
	}
	
	//(문정)사용자 마이페이지 - 거래 세부 내용에 해당하는 서비스 내용 불러오기
	public Service selectOneService(int sNo) {
		return session.selectOne("trade.selectOneService", sNo);
	}

//
	//(영재)프리랜서 마이페이지-거래내역 불러오기
	public ArrayList<ServiceTrade> selectTradeList2(int mNo) {
		List<ServiceTrade> list = session.selectList("trade.selectTradeList2", mNo);
		return (ArrayList<ServiceTrade>)list;
	}

	//(영재)프리랜서 마이페이지-거래에 해당하는 서비스 불러오기
	public ArrayList<Service> selectServiceList2(int mNo) {
		List<Service> list = session.selectList("trade.selectServiceList2", mNo);
		return (ArrayList<Service>)list;
	}

	//(영재)프리랜서 마이페이지-결제시간 불러오기 
	public ArrayList<String> selectPayDateList2(int mNo) {
		List<String> list = session.selectList("trade.selectPayDateList2", mNo);
		return (ArrayList<String>)list;
	}

	
	
	
	
	
	public int updateTStatus(int tNo) {
		System.out.println("DAO단 tNo"+tNo);
		return session.update("trade.updateTStatus",tNo);
	}

	public int updateWarningCount(int mNo) {
		return session.update("trade.updateWarningCount",mNo);
	}

}
