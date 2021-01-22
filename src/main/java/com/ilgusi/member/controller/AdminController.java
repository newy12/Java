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
import com.ilgusi.member.model.vo.MemberViewData;
import com.ilgusi.question.model.vo.Question;
import com.ilgusi.service.model.vo.Service;
import com.ilgusi.service.model.vo.ServiceInfo;
import com.ilgusi.service.model.vo.ServiceViewData;
import com.ilgusi.service.model.vo.TradeHistory;

@Controller
public class AdminController {

	@Autowired
	private AdminService service;

	// (소현)전체회원조회-검색,정렬,페이징
	@RequestMapping("/manageMember.do")
	public String manageMember(Model model, int reqPage, String grade, String keyword, String order) {
		// 조건
		// keyword는 id/brandname검색,order는 이용횟수 내림차순, 신고 내림차순

		// request 페이징 코드 참고
		// 1. 한 페이지에 보여줄 리스트 개수
		int numPerPage = 13;

		// 2. 쿼리에서 시작-끝 번호로 리스트결과 가져옴
		int end = reqPage * numPerPage;
		int start = (end - numPerPage) + 1;
		MemberViewData mvd = new MemberViewData(grade, keyword, order, start, end);

		// 조건에 맞는 회원리스트
		ArrayList<Member> memberList = service.selectMemberListPaging(start, end, mvd);

		// 3. 의뢰글 총 몇개?
		int totalCount = service.totalMemberCount(grade, keyword);

		// 4. 페이지가 총 몇 개?
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}

		// 5. 페이지 네비 몇 개까지 보여줄 건지?
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		// 6. 페이지 네비
		String pageNavi = "";

		// 이전 버튼
		if (pageNo != 1) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/manageMember.do?reqPage=" + (pageNo - 1)
					+ "&grade=" + grade + "&keyword=" + keyword + "&order=" + order + "'><<</a></li>";
		}

		// 네비게이션 숫자
		for (int i = 0; i < pageNaviSize; i++) {
			if (reqPage == pageNo) {
				pageNavi += "<li class='page-item'><a class='page-link target' href='#' style='color:white'>" + pageNo
						+ "</a></li>";
			} else {
				pageNavi += "<li class='page-item'><a class='page-link' href='/manageMember.do?reqPage=" + (pageNo)
						+ "&grade=" + grade + "&keyword=" + keyword + "&order=" + order + "'>" + pageNo + "</a></li>";
			}
			pageNo++;

			if (pageNo > totalPage) {
				break;
			}
		}

		// 다음 버튼
		if (reqPage <= (totalPage / pageNaviSize)) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/manageMember.do?reqPage=" + pageNo
					+ "&grade=" + grade + "&keyword=" + keyword + "&order=" + order + "'>>></a></li>";
		}

		// 페이지 할게 없으면
		if (totalCount <= numPerPage) {
			pageNavi = "";
		}

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

		String page = "";
		if (grade.equals("all")) {
			page = "all";
		}
		if (grade.equals("free")) {
			page = "free";
		}
		if (grade.equals("black")) {
			page = "black";
		}

		model.addAttribute("page", page);
		model.addAttribute("keyword", keyword);
		model.addAttribute("order", order);
		model.addAttribute("pageNavi", pageNavi);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("memberList", memberList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("useHistory", useHistory);
		model.addAttribute("adminMsg", adminMsg);
		return "admin/memberList";
	}

	// (소현)전체서비스조회-검색,정렬,페이징
	@RequestMapping("/manageService.do")
	public String selectAllService(Model model, int reqPage, String status, String keyword1, String keyword2,
			String order) {

		// 조건
		// grade:승인대기중,등록,거절/삭제
		// keyword1: service title / m_id인지 정하는값
		// keyword2: 검색어
		// order는 작업수 내림차순, 신고 내림차순

		// 1. 한 페이지에 보여줄 리스트 개수
		int numPerPage = 13;

		// 2. 쿼리에서 시작-끝 번호로 리스트결과 가져옴
		int end = reqPage * numPerPage;
		int start = (end - numPerPage) + 1;
		ServiceViewData svd = new ServiceViewData(status, keyword1, keyword2, order, start, end);

		// 조건에 맞는 서비스리스트
		ArrayList<ServiceInfo> serviceList = service.selectServiceListPaging(start, end, svd);

		// 3. 의뢰글 총 몇개?
		int totalCount = service.totalServiceCount(status, keyword1, keyword2);

		// 4. 페이지가 총 몇 개?
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}

		// 5. 페이지 네비 몇 개까지 보여줄 건지?
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		// 6. 페이지 네비
		String pageNavi = "";

		// 이전 버튼
		if (pageNo != 1) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/manageService.do?reqPage=" + (pageNo - 1)
					+ "&status=" + status + "&keyword1=" + keyword1 + "&keyword2=" + keyword2 + "&order=" + order
					+ "'><<</a></li>";
		}

		// 네비게이션 숫자
		for (int i = 0; i < pageNaviSize; i++) {
			if (reqPage == pageNo) {
				pageNavi += "<li class='page-item'><a class='page-link target' href='#' style='color:white'>" + pageNo
						+ "</a></li>";
			} else {
				pageNavi += "<li class='page-item'><a class='page-link' href='/manageService.do?reqPage=" + (pageNo)
						+ "&status=" + status + "&keyword1=" + keyword1 + "&keyword2=" + keyword2 + "&order=" + order
						+ "'>" + pageNo + "</a></li>";
			}
			pageNo++;

			if (pageNo > totalPage) {
				break;
			}
		}

		// 다음 버튼
		if (reqPage <= (totalPage / pageNaviSize)) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/manageService.do?reqPage=" + pageNo
					+ "&status=" + status + "&keyword1=" + keyword1 + "&keyword2=" + keyword2 + "&order=" + order
					+ "'>>></a></li>";
		}

		// 페이지 할게 없으면
		if (totalCount <= numPerPage) {
			pageNavi = "";
		}

		String page = "";
		if (status.equals("waiting")) {
			page = "waiting";
		}
		if (status.equals("approved")) {
			page = "approved";
		}
		if (status.equals("deleted")) {
			page = "deleted";
		}

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

		model.addAttribute("page", page);
		model.addAttribute("keyword1", keyword1);
		model.addAttribute("keyword2", keyword2);
		model.addAttribute("order", order);
		model.addAttribute("pageNavi", pageNavi);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("mIdandmNo", mIdandmNo);
		model.addAttribute("serviceList", serviceList);
		return "admin/serviceList";
	}

	// (소현)서비스승인
	@ResponseBody
	@RequestMapping("/acceptService.do")
	public int acceptService(String mId, int sNo) {
		// 회원의 전체서비스
		ArrayList<Service> serviceList = service.selectServiceList(mId);
		// 승인된 서비스
		int approved = 0;
		for (int i = 0; i < serviceList.size(); i++) {
			char adminApproval = serviceList.get(i).getAdminApproval();
			char deleteStatus = serviceList.get(i).getDeleteStatus();
			if (adminApproval == 'y' && deleteStatus == 'n') {
				approved++;
			}
		}

		if (approved < 5) {
			int result = service.acceptService(sNo);
			System.out.println(result);
			return result;
		} else {// 5개 미만이면 승인 아니면 return -1
			return -1;
		}

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
		//날짜형식 변경
				for(int i =0;i<list.size();i++) {
					list.get(i).setWriteDate(list.get(i).getWriteDate().replace("/", "-"));
					if(list.get(i).getAnswerDate() != null && list.get(i).getAnswerDate().equals(""))
					list.get(i).setAnswerDate(list.get(i).getAnswerDate().replace("/", "-"));
				}
		int maxPrintPageCount = 5;
		int maxPageCount = service.selectMaxPageCount(listPerPage, maxListCount);
		int begin = maxPrintPageCount * (page / (maxPrintPageCount + 1)) + 1; // 네비 시작
		int end = (begin + 4) < maxPageCount ? begin + 4 : maxPageCount; // 네비 끝
		model.addAttribute("questionList", list);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("maxPageCount", maxPageCount);
		return "/admin/qnaList";
	}

}
