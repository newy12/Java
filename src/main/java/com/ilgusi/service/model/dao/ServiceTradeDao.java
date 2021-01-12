package com.ilgusi.service.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
