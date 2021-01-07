package com.ilgusi.service.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.service.ServiceService;
import com.ilgusi.service.model.vo.Join;
import com.ilgusi.service.model.vo.ServiceFile;

import common.FileNameOverlap;

@Controller
public class ServiceController {
	@Autowired
	private ServiceService service;
	@RequestMapping("/introduceFrm.do")
	public String introduceFrm(String id, Model model) {
		Join j = service.selectOneMember(id);
		model.addAttribute("j",j);
		return "freelancer/introduce";
	}
	@RequestMapping("/serviceJoinFrm.do")
	public String serviceJoinFrm() {
		return "freelancer/servicejoin";
	}
	@RequestMapping("/serviceJoin.do")
	public String serviceJoin(Join join, Model model, MultipartFile[] files, HttpServletRequest request) {
		int result = service.insertService(join);
		if(result>0) {
			model.addAttribute("msg","서비스등록완료");
		}else {
			model.addAttribute("msg","서비스등록실패");
		}
		model.addAttribute("loc","/");
		return "common/msg";
	}
<<<<<<< HEAD
	//프리랜서 마이페이지 이동
	@RequestMapping("/freelancerMypage.do")
		public String selectfreelancerMypage(int MNo, Model model)  {
		Member m = service.selectOneMember(MNo);
		System.out.println("MNo>>>>>"+m.getMNo()+MNo);
		model.addAttribute("m",m);
		System.out.println("소개값>>>>>>>>>"+m.getIntroduce());
		return "freelancer/freelancerMypage";
	}
	//프리랜서 마이페이지 -> 서비스 리스트 이동
	@RequestMapping("/freelancerServiceList.do")
		public String freelancerServiceList() {
		return "freelancer/freelancerServiceList";
	}
	//프리랜서 마이페이지 -> 거래내역
	@RequestMapping("/freelancerTradeHistory.do")
	public String freelancerTradeHistory() {
	return "freelancer/freelancerTradeHistory";
}	//프리랜서 마이페이지 정보수정(소개글,연락가능시간,브랜드명 추가)
 	@RequestMapping("/updateFreelancer.do")
 		public String updateFreelancer(Member m,Model model) {
 			int result = service.updateFreelancer(m);
 			if(result>0){
 				model.addAttribute("msg","수정되었습니다.");
 			}else {
 				model.addAttribute("msg","수정실패하였습니다.");
 			}
 			model.addAttribute("loc","/");
 			return "common/msg";
 			
 	}
=======
	
	@RequestMapping("/serviceList.do")
	public String serviceList() {
		return "service/serviceList";
	}
	
	@RequestMapping("/serviceView.do")
	public String serviceView() {
		return "service/serviceView";
	}
>>>>>>> 5a5d18f8bb6024054d25673bfd5205104be5121f
}
