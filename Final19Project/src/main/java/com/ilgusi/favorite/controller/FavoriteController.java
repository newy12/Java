package com.ilgusi.favorite.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilgusi.favorite.model.service.FavoriteService;
import com.ilgusi.service.model.vo.Service;

@Controller
public class FavoriteController {
	@Autowired
	private FavoriteService service;
	
	// (문정)사용자 마이페이지-찜한 리스트 이동(정렬)
	@RequestMapping("/userHeartList.do")
	public String userHeartList(int mNo, String order, Model model) {
		ArrayList<Service> list = service.selectHeartList(mNo, order);
		ArrayList<String> brandnameList = service.selectBrandName(mNo, order);
		
		//천원단위
		DecimalFormat df = new DecimalFormat("###,###");
		for(int i=0; i<list.size(); i++) {
			list.get(i).setSPriceTxt(df.format(list.get(i).getSPrice())+"원");
		}
		
		model.addAttribute("list", list);
		model.addAttribute("brandList", brandnameList);
		model.addAttribute("order", order);
		System.out.println("크기 : " + brandnameList.size());
		for(int i=0; i<brandnameList.size();i++) {
			System.out.println(brandnameList.get(i));
		}
		return "member/userHeartList";
	}
	
	//(문정)사용자 마이페이지 - 찜한거 삭제
	@ResponseBody
	@RequestMapping("/deleteHeart.do")
	public String deleteHeart(int sNo, int mNo) {
		System.out.println("컨트롤러 들어왔다?");
		int result = service.deleteHeart(sNo, mNo);
		if(result>0) {
			System.out.println("성공");
		}else{
			System.out.println("실패");
		}
		return "";
	}
	
	//(문정)사용자마이페이지 - 찜한 목록에 추가
	@ResponseBody
	@RequestMapping("/insertHeart.do")
	public String insertHeart(int sNo, int mNo) {
		int result = service.insertHeart(sNo, mNo);
		return "";
	}
	
	
}
