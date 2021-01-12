package com.ilgusi.member.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilgusi.member.model.service.AdminService;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Service;
import com.ilgusi.service.model.vo.ServiceTrade;

@Controller
public class AdminController {

	@Autowired
	private AdminService service;

	// (소현)전체회원조회
	@RequestMapping("/manageMember.do")
	public String manageMember(Model model) {
		// 전체회원리스트
		ArrayList<Member> memberList = service.selectAllMember();

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

	// (소현)전체서비스조회
	@RequestMapping("/manageService.do")
	public String selectAllService(Model model) {
		ArrayList<Service> list = service.selectAllService();
		model.addAttribute("serviceList", list);
		return "admin/serviceList";
	}

	// (소현)서비스승인
	@ResponseBody
	@RequestMapping("/acceptService.do")
	public int acceptService(int sNo) {
		int result = service.acceptService(sNo);
		return result;
	}

	// (소현)서비스거절창에 서비스정보보내기
	@RequestMapping("/rejectFrm.do")
	public String rejectFrm(Model model, int sNo) {
		ArrayList<Service> serviceList = service.selectService(sNo);
		Service oneService = serviceList.get(0);
		String freeId = oneService.getMId();
		ArrayList<Member> memberList = service.selectAllMember();
		int mNo = 0;
		for (int i = 0; i < memberList.size(); i++) {
			Member oneMember = memberList.get(i);
			if (freeId == oneMember.getMId()) {
				mNo = oneMember.getMNo();
			}
		}
		model.addAttribute("mNo",mNo);
		model.addAttribute("service", oneService);
		return "admin/rejectFrm";
	}
	
	//(소현)서비스 등록 거절
	@ResponseBody
	@RequestMapping("rejectService.do")
	public void rejectService(int sNo) {
		service.rejectService(sNo);
	}

	// (소현)서비스 삭제
	@ResponseBody
	@RequestMapping("deleteService.do")
	public int deleteService(int sNo) {
		int result = service.deleteService(sNo);
		return result;
	}

	// (소현)프리랜서의 작업내역 조회
	@RequestMapping("workingHistory.do")
	public String workingCount(Model model, int sNo) {
		ArrayList<ServiceTrade> history = service.workingCount(sNo);
		model.addAttribute("history", history);
		return "/admin/serviceHistory";
	}

	// (소현)회원의 서비스 이용내역 조회
	@RequestMapping("userHistory.do")
	public String useCount(Model model, int mNo) {
		ArrayList<ServiceTrade> history = service.useCount(mNo);
		for (int i = 0; i < history.size(); i++) {
			System.out.println(history.get(i).getSNo());
		}
		model.addAttribute("history", history);
		return "/admin/userHistory";
	}

	// (소현)회원에게 메세지보내기 창 열기
	@RequestMapping("adminMessage.do")
	public String adminMessage(Model model, int mNo) {
		ArrayList<Member> memberList = service.selectAllMember();
		Member oneMember = new Member();
		for (int i = 0; i < memberList.size(); i++) {
			if (mNo == memberList.get(i).getMNo()) {
				oneMember = memberList.get(i);
			}
		}
		model.addAttribute("member", oneMember);
		return "/admin/adminMessage";
	}

}
