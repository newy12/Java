package com.ilgusi.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.chat.model.vo.ChatContent;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.member.model.vo.MemberViewData;
import com.ilgusi.question.model.vo.Question;
import com.ilgusi.service.model.vo.Service;
import com.ilgusi.service.model.vo.ServiceInfo;
import com.ilgusi.service.model.vo.TradeHistory;
import com.ilgusi.service.model.vo.ServiceTrade;
import com.ilgusi.service.model.vo.ServiceViewData;

@Repository
public class AdminDao {

	@Autowired
	private SqlSessionTemplate session;

	// (소현)관리자-전체회원조회
	public ArrayList<Member> selectAllMember() {
		List<Member> list = session.selectList("member.selectAllMember");
		return (ArrayList<Member>) list;
	}

	// (소현)회원별 서비스 이용횟수
	public int countHistory(int mNo) {
		return session.selectOne("member.countHistory", mNo);
	}

	// (소현)관리자-전체서비스불러오기
	public ArrayList<ServiceInfo> selectAllService() {
		List<ServiceInfo> list = session.selectList("service.selectServiceInfo");
		return (ArrayList<ServiceInfo>) list;
	}

	// (소현)서비스 승인
	public int acceptService(int sNo) {
		return session.update("service.acceptService", sNo);
	}

	// (소현)관리자-서비스거절창에 서비스정보보내기
	public ArrayList<ServiceInfo> selectService(int sNo) {
		List<ServiceInfo> list = session.selectList("service.selectServiceInfo", sNo);
		return (ArrayList<ServiceInfo>) list;
	}

	// (소현)서비스 등록 거절
	public void rejectService(int sNo) {
		session.update("service.rejectService", sNo);
	}

	// (소현)서비스 삭제
	public void deleteService(int sNo) {
		session.delete("service.deleteService", sNo);
	}

	// (소현)관리자가 회원에게 보낸 메세지 리스트
	public ArrayList<ChatContent> selectAdminMsg(String mId) {
		List<ChatContent> msg = session.selectList("chat.selectAdminMsg", mId);
		return (ArrayList<ChatContent>) msg;
	}

	// (소현)아이디로 회원 불러오기
	public Member selectOneMember(String freeId) {
		return session.selectOne("member.selectAllMember", freeId);
	}

	// (소현)작업내역조회
	public ArrayList<TradeHistory> tradeHistory(HashMap<String, Integer> map) {
		List<TradeHistory> list = session.selectList("trade.tradeHistory", map);
		return (ArrayList<TradeHistory>) list;
	}

	public int selectQuestionCount() {
		return session.selectOne("question.selectCount");
	}

	public int selectQuestionCount(int type, String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("keyword", keyword);
		return session.selectOne("question.selectCount", map);
	}

	public List<Question> selectQuestionList(int begin, int end, int type, String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("type", type);
		map.put("keyword", keyword);
		return session.selectList("question.selectQuestionList", map);
	}

	// (소현)조건에 만족하는 회원리스트 불러오기 - 페이징 전
	/*
	 * public ArrayList<Member> selectAllMember2(MemberViewData mvd) { List<Member>
	 * list = session.selectList("member.selectAllMember2", mvd); return
	 * (ArrayList<Member>) list; }
	 */

	// (소현)회원리스트 페이징
	public ArrayList<Member> selectMemberListPaging(int start, int end, MemberViewData mvd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("grade", mvd.getGrade());
		map.put("keyword", mvd.getKeyword());
		map.put("order", mvd.getOrder());
		List<Member> list = session.selectList("member.selectAllMemberPaging", map);
		return (ArrayList<Member>) list;
	}

	// (소현) member totalCount
	public int totalMemberCount(String grade, String keyword) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("grade", grade);
		map.put("keyword", keyword);
		return session.selectOne("member.totalCount", map);
	}

	// (소현)관리자-서비스리스트 페이징추가
	public ArrayList<ServiceInfo> selectServiceListPaging(int start, int end, ServiceViewData svd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("status", svd.getStatus());
		map.put("keyword1", svd.getKeyword1());
		map.put("keyword2", svd.getKeyword2());
		map.put("order", svd.getOrder());
		List<ServiceInfo> list = session.selectList("service.selectAllServicePaging", map);
		return (ArrayList<ServiceInfo>) list;
	}

	// (소현)service total count
	public int totalServiceCount(String status, String keyword1, String keyword2) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("status", status);
		map.put("keyword1", keyword1);
		map.put("keyword2", keyword2);
		return session.selectOne("service.totalServiceCount", map);
	}

	//회원의 서비스개수 구하기
	public ArrayList<Service> selectServiceList(String mId) {
		List<Service> serviceList=session.selectList("service.selectServiceList",mId);
		return (ArrayList<Service>)serviceList;
	}

}
