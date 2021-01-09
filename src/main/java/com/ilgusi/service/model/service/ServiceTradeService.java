package com.ilgusi.service.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
