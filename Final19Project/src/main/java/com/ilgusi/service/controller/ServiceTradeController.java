package com.ilgusi.service.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilgusi.member.model.vo.Member;
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
		
		//천원단위로 수정
		DecimalFormat df = new DecimalFormat("###,###");
		for(int i=0; i<tradeList.size();i++) {
			tradeList.get(i).setTPriceTxt(df.format(tradeList.get(i).getTPrice())+"원");
		}
		
		model.addAttribute("tradeList", tradeList);
		model.addAttribute("serviceList",serviceList);
		model.addAttribute("payDate",payDate);
		return "member/userTradeHistory";
	}

	
	//(문정)사용자 마이페이지 - 거래 세부 내용 불러오기
	@RequestMapping("/serviceTradeView.do")
	public String serviceTradeView(int tNo, Model model) {
		ServiceTrade trade = service.serviceTradeView(tNo);
		if(trade!= null) {
			Service s = service.selectOneService(trade.getSNo());
			if(s!=null) {
				
				//천원단위로 수정
				DecimalFormat df = new DecimalFormat("###,###");
				trade.setTPriceTxt(df.format(trade.getTPrice())+"원");
				
				model.addAttribute("trade", trade);
				model.addAttribute("service",s);
			}
		}
		return "service/serviceTradeView";
	}

	// (영재)프리렌서 마이페이지-거래내역 불러오기
	@RequestMapping("/freelancerTradeHistory.do")
	public String freelancerHeartList(int mNo, Model model) {
		System.out.println("회원 번호 : " + mNo);
		ArrayList<ServiceTrade> tradeList = service.selectTradeList2(mNo); //거래내역 불러옴
		ArrayList<Service> serviceList = service.selectServiceList2(mNo);  //거래에 해당하는 서비스 불러옴
		ArrayList<String> payDate = service.selectPayDateList2(mNo);       //결제 날짜 불러옴
		
		//천원단위로 수정
		DecimalFormat df = new DecimalFormat("###,###");
		for(int i=0; i<tradeList.size();i++) {
			tradeList.get(i).setTPriceTxt(df.format(tradeList.get(i).getTPrice())+"원");
		}
		
		model.addAttribute("tradeList", tradeList);
		model.addAttribute("serviceList",serviceList);
		model.addAttribute("payDate",payDate);
		System.out.println(tradeList.size()+"/"+serviceList.size()+"/"+payDate.size());
		return "freelancer/freelancerTradeHistory";
	}
		
	//(영재) 작업완료버튼누르면 t_status 변경( 1-->2)
	@ResponseBody
	@RequestMapping("/updateTStatus.do")
	public void updateTStatus(int tNo, Model model) {
		System.out.println("controller tno값"+tNo);
		int result = service.updateTStatus(tNo);
		System.out.println("controller result값"+result);
	}

	//(영재) warningCount 변경 	
	@RequestMapping("/updateWarningCount.do")
	public String updateWarningCount(int mNo, Model model, HttpServletRequest req) {
		int result = service.updateWarningCount(mNo);
		
		HttpSession session = req.getSession();
		Member m = (Member)session.getAttribute("loginMember");
		return "redirect:/freelancerTradeHistory.do?mNo="+m.getMNo();
	}
	
	//(문정) 팝업창 닫고 페이지 이동만
	@RequestMapping("/goToPage.do")
	public String goToPage(int sNo) {
		return "redirect:/serviceView.do?sNo="+sNo+"&reqPage=1";
	}
}
