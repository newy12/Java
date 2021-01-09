package com.ilgusi.member.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ilgusi.member.model.service.AdminService;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Service;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	// (소현)관리자-전체회원조회
		@RequestMapping("/manageMember.do")
		public String manageMember(Model model) {
			// 전체회원리스트
			ArrayList<Member> memberList = service.manageMember();

			// 회원별서비스이용횟수 리스트
			HashMap<Integer, Integer> useHistory = new HashMap<Integer, Integer>();
			for (int i = 0; i < memberList.size(); i++) {
				Member oneMember = memberList.get(i);
				int mNo = oneMember.getMNo();
				int use = service.countHistory(mNo);
				useHistory.put(mNo, use);
			}

			model.addAttribute("memberList", memberList);
			model.addAttribute("useHistory", useHistory);

			return "admin/memberList";
		}
		
		//(소현)관리자-전체서비스불러오기
		@RequestMapping("/manageService.do")
		public String selectAllService(Model model) {
			ArrayList<Service> list =service.selectAllService();
			model.addAttribute("serviceList",list);
			return "admin/serviceList";
		}
		


}
