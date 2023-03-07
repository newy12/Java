package com.ilgusi.service.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.dao.ServiceTradeDao;
import com.ilgusi.service.model.vo.ServiceTrade;

@Service
public class ServiceTradeService {
	@Autowired
	private ServiceTradeDao dao;

	//(문정)사용자 마이페이지-거래내역 불러오기
	public ArrayList<ServiceTrade> selectTradeList(int mNo) {
		return dao.selectTradeList(mNo);
	}
	
	//(문정)사용자 마이페이지-거래에 해당하는 서비스 불러오기
	public ArrayList<com.ilgusi.service.model.vo.Service> selectServiceList(int mNo) {
		return dao.selectServiceList(mNo);
	}
	
	//(문정)사용자 마이페이지-결제시간 불러오기 
	public ArrayList<String> selectPayDateList(int mNo) {
		return dao.selectPayDateList(mNo);
	}

	//(문정)사용자 마이페이지 - 거래 세부 내용 불러오기
	public ServiceTrade serviceTradeView(int tNo) {
		return dao.serviceTradeView(tNo);
	}
	
	//(문정)사용자 마이페이지 - 거래 세부 내용에 해당하는 서비스 불러오기
	public com.ilgusi.service.model.vo.Service selectOneService(int sNo) {
		return dao.selectOneService(sNo);
	}
	//
	
	
	//(영재)프리랜서 마이페이지-거래내역 불러오기
	public ArrayList<ServiceTrade> selectTradeList2(int mNo) {
		return dao.selectTradeList2(mNo);
	}
	
	//(영재)프리랜서 마이페이지-거래에 해당하는 서비스 불러오기
	public ArrayList<com.ilgusi.service.model.vo.Service> selectServiceList2(int mNo) {
		return dao.selectServiceList2(mNo);
	}
	
	//(영재)프리랜서 마이페이지-결제시간 불러오기 
	public ArrayList<String> selectPayDateList2(int mNo) {
		return dao.selectPayDateList2(mNo);
	}
	

	public int updateTStatus(int tNo) {
		System.out.println("service단 tno"+tNo);
		return dao.updateTStatus(tNo);
	}

	public int updateWarningCount(int mNo) {
		System.out.println("mNo service>>>>>>>>>>>>>>>"+mNo);
		return dao.updateWarningCount(mNo);
	}

}
