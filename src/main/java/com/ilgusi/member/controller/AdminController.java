package com.ilgusi.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilgusi.chat.model.vo.ChatContent;
import com.ilgusi.member.model.service.AdminService;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.question.model.vo.Question;
import com.ilgusi.service.model.vo.Service;
import com.ilgusi.service.model.vo.ServiceInfo;
import com.ilgusi.service.model.vo.TradeHistory;

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

		// 관리자가 회원에게 보낸 메세지 리스트
		HashMap<String, ArrayList<ChatContent>> adminMsg = new HashMap<String, ArrayList<ChatContent>>();

		for (int i = 0; i < memberList.size(); i++) {
			Member oneMember = memberList.get(i);
			// 서비스 이용횟수
			int mNo = oneMember.getMNo();
			int use = service.countHistory(mNo);
			useHistory.put(mNo, use);
			// 관리자가 보낸 메세지
			String mId = oneMember.getMId();
			ArrayList<ChatContent> msgList = service.selectAdminMsg(mId);
			adminMsg.put(mId, msgList);
		}

		model.addAttribute("memberList", memberList);
		model.addAttribute("useHistory", useHistory);
		model.addAttribute("adminMsg", adminMsg);
		return "admin/memberList";
	}

	// (소현)전체서비스조회
	@RequestMapping("/manageService.do")
	public String selectAllService(Model model) {
		ArrayList<ServiceInfo> serviceList = service.selectAllService();

		// 전체회원리스트
		ArrayList<Member> memberList = service.selectAllMember();
		HashMap<String, Integer> mIdandmNo = new HashMap<String, Integer>();

		for (int i = 0; i < serviceList.size(); i++) {
			String mId = serviceList.get(i).getMId();
			for (int j = 0; j < memberList.size(); j++) {
				if (mId.equals(memberList.get(j).getMId())) {
					int mNo = memberList.get(j).getMNo();
					mIdandmNo.put(mId, mNo);
				}
			}
		}
		model.addAttribute("mIdandmNo", mIdandmNo);
		model.addAttribute("serviceList", serviceList);
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
		ArrayList<ServiceInfo> serviceList = service.selectService(sNo);
		ServiceInfo oneService = serviceList.get(0);
		String freeId = oneService.getMId();

		Member oneUser = service.selectOneMember(freeId);
		int mNo = oneUser.getMNo();

		model.addAttribute("mNo", mNo);
		model.addAttribute("service", oneService);
		return "admin/rejectFrm";
	}

	// (소현)서비스 등록 거절
	@ResponseBody
	@RequestMapping("rejectService.do")
	public void rejectService(int sNo) {
		service.rejectService(sNo);
	}

	// (소현)서비스 삭제
	@ResponseBody
	@RequestMapping("deleteService.do")
	public void deleteService(int sNo) {
		service.deleteService(sNo);
	}

	// (소현)작업내역 조회
	@RequestMapping("tradeHistory.do")
	public String workingCount(Model model, int sNo, int mNo) {
		// 서비스의 작업내역조회
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		ArrayList<TradeHistory> history = new ArrayList<TradeHistory>();
		if (mNo == -1) {
			map.put("sNo", sNo);
			history = service.tradeHistory(map);
			model.addAttribute("history", history);
			return "/admin/serviceHistory";

		} else {
			// 사용자의 이용내역조회
			map.put("mNo", mNo);
			history = service.tradeHistory(map);
			model.addAttribute("history", history);
			return "/admin/userHistory";
		}
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
	
	// (도현) qna 관리자페이지 내에서 접속
		@RequestMapping("manageQnA.do")
		public String qna(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
				@RequestParam(value = "list_num", defaultValue = "10") int listNum,
				@RequestParam(value = "qna_keyword", required = false) String keyword,
				@RequestParam(value = "qna_type", defaultValue = "1") int type) {
			// 검색기능 type--> 1: 제목 , 2:작성자 아이디
			// 네비 기능
			int listPerPage = listNum;
			int maxListCount;
			if (keyword == null)
				maxListCount = service.selectQuestionCount();
			else
				maxListCount = service.selectQuestionCount(type, keyword);

			List<Question> list = service.selectQuestionList(maxListCount - ((page) * listPerPage) + 1,
					maxListCount - ((page - 1) * listPerPage), type, keyword);
			int maxPrintPageCount = 5;
			int maxPageCount = service.selectMaxPageCount(listPerPage, maxListCount);
			int begin = maxPrintPageCount * (page / maxPrintPageCount) + 1; // 네비 시작
			int end = (begin + 4) < maxPageCount ? begin + 4 : maxPageCount; // 네비 끝
			model.addAttribute("questionList", list);
			model.addAttribute("begin", begin);
			model.addAttribute("end", end);
			
			return "/admin/qnaList";
		}
	
}
