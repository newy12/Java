package com.ilgusi.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.chat.model.vo.ChatContent;
import com.ilgusi.member.model.dao.AdminDao;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.member.model.vo.MemberViewData;
import com.ilgusi.question.model.vo.Question;
import com.ilgusi.service.model.vo.ServiceViewData;
import com.ilgusi.service.model.vo.TradeHistory;

@Service
public class AdminService {

	@Autowired
	private AdminDao dao;

	// (소현)관리자-전체회원조회
	public ArrayList<Member> selectAllMember() {
		return dao.selectAllMember();
	}

	// (소현)회원별 서비스 이용횟수
	public int countHistory(int mNo) {
		return dao.countHistory(mNo);
	}

	// (소현)관리자-전체서비스불러오기
	public ArrayList<com.ilgusi.service.model.vo.ServiceInfo> selectAllService() {
		return dao.selectAllService();
	}

	// (소현)서비스 승인
	public int acceptService(int sNo) {
		return dao.acceptService(sNo);
	}

	// (소현)관리자-서비스거절창에 서비스정보보내기
	public ArrayList<com.ilgusi.service.model.vo.ServiceInfo> selectService(int sNo) {
		return dao.selectService(sNo);
	}

	// (소현)서비스 삭제
	public void deleteService(int sNo) {
		dao.deleteService(sNo);
	}

	// (소현)서비스 등록 거절
	public void rejectService(int sNo) {
		dao.rejectService(sNo);
	}

	// (소현)관리자가 회원에게 보낸 메세지 리스트
	public ArrayList<ChatContent> selectAdminMsg(String mId) {
		return dao.selectAdminMsg(mId);
	}

	// (소현)아이디로 회원 불러오기
	public Member selectOneMember(String freeId) {
		return dao.selectOneMember(freeId);
	}

	// (소현)작업내역 조회
	public ArrayList<TradeHistory> tradeHistory(HashMap<String, Integer> map) {
		return dao.tradeHistory(map);
	}

	public int selectQuestionCount() {
		return dao.selectQuestionCount();
	}

	public int selectQuestionCount(int type, String keyword) {
		return dao.selectQuestionCount(type, keyword);
	}

	public List<Question> selectQuestionList(int begin, int end, int type, String keyword) {
		return dao.selectQuestionList(begin, end, type, keyword);
	}

	public int selectMaxPageCount(int numPerPage, int listCount) {
		int maxPageCount = listCount / numPerPage;

		if (listCount % numPerPage > 0)
			maxPageCount++;

		return maxPageCount;
	}

	// (소현)조건에 만족하는 회원리스트 불러오기 - 페이징전
	/*
	 * public ArrayList<Member> selectAllMember2(MemberViewData mvd) { return
	 * dao.selectAllMember2(mvd); }
	 */

	// (소현)회원리스트 페이징 추가
	public ArrayList<Member> selectMemberListPaging(int start, int end, MemberViewData mvd) {
		ArrayList<Member> list = dao.selectMemberListPaging(start, end, mvd);
		return list;
	}

	// (소현)member total count
	public int totalMemberCount(String grade, String keyword) {
		return dao.totalMemberCount(grade, keyword);
	}

	// (소현)관리자-서비스리스트 페이징추가
	public ArrayList<com.ilgusi.service.model.vo.ServiceInfo> selectServiceListPaging(int start, int end,
			ServiceViewData svd) {
		ArrayList<com.ilgusi.service.model.vo.ServiceInfo> list = dao.selectServiceListPaging(start, end, svd);
		return list;
	}

	// (소현)service total count
	public int totalServiceCount(String status, String keyword1, String keyword2) {
		return dao.totalServiceCount(status, keyword1, keyword2);
	}
	
	//회원의 서비스개수 구하기
	public ArrayList<com.ilgusi.service.model.vo.Service> selectServiceList(String mId) {
		return dao.selectServiceList(mId);
	}

}
