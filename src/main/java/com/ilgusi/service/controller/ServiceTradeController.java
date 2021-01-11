package com.ilgusi.service.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ilgusi.service.model.service.ServiceTradeService;
import com.ilgusi.service.model.vo.Service;
import com.ilgusi.service.model.vo.ServiceTrade;

@Controller
public class ServiceTradeController {
	@Autowired
	private ServiceTradeService service;
	
	// (문정)사용자 마이페이지-거래내역 불러오기
	@RequestMapping("/userTradeHistory.do")
	public String userHeartList(int mNo, Model model) {
		ArrayList<ServiceTrade> tradeList = service.selectTradeList(mNo); //거래내역 불러옴
		ArrayList<Service> serviceList = service.selectServiceList(mNo);  //거래에 해당하는 서비스 불러옴
		ArrayList<String> payDate = service.selectPayDateList(mNo);       //결제 날짜 불러옴
		model.addAttribute("tradeList", tradeList);
		model.addAttribute("serviceList",serviceList);
		model.addAttribute("payDate",payDate);
		return "member/userTradeHistory";
	}
}
