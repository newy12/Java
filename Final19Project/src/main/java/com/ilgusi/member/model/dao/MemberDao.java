package com.ilgusi.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ilgusi.member.model.vo.Member;

@Repository
public class MemberDao {

	@Autowired
	private SqlSessionTemplate session;

	// (도현)메인페이지에 판매진행,구매진행중 정보 전달
	public int selectBuyingCount(int mNo) {
		return session.selectOne("member.selectBuyingCount", mNo);
	}
	public int selectSellingCount(String mId) {
		return session.selectOne("member.selectSellingCount", mId);
	}
	
	// (도현) 로그인 기능
	public Member loginMember(String id, String pw) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		return session.selectOne("member.loginMember", map);
	}

	// (도현) 회원가입 기능
	public int registerMember(Member m) {
		return session.insert("member.registerMember", m);
	}

	// (도현) 아이디 중복검사 기능
	public Member checkId(String id) {
		return session.selectOne("member.checkId",id);
	}

	// (도현) 아이디/비밀번호 찾기 기능
	public Member searchIdPw(Member m) {
		return session.selectOne("member.searchIdPw", m);
	}

	// (도현)비밀번호 찾기 후 - 비밀번호 변경
	public int changePw(Member m) {
		return session.update("member.changePw", m);
	}

	// (문정)사용자 마이페이지-이메일, 폰번호, 비번 변경
	@Transactional
	public int changeMypage(String mId, String data, String object) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", mId);
		map.put("data", data);
		map.put("object", object);
		return session.update("member.changeMypage", map);
	}

	// (문정)사용자 마이페이지-회원탈퇴
	public int deleteMember(String mId) {
		return session.delete("member.deleteMember", mId);
	}

	//(문정) 마이페이지에서 사용자-프리랜서 전환
	public int changeGrade(String mId, int grade) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mId", mId);
		map.put("grade", grade);
		return session.update("member.changeGrade", map);
	}

	//(문정) 로그인하면 grade를 1로 셋팅
	public int settingMemberGrade(Member m) {
		return session.update("member.settingMemberGrade",m);
	}


	//(문정) 거래중인 서비스가 있는지 확인
	public int tradeStatus(int mNo) {
		return session.selectOne("member.tradeStatus",mNo);
	}

	//(문정)탈퇴하기전 delete_status = 'y' 셋팅
	public int setDeleteStatusY(String mId) {
		return session.update("member.setDeleteStatusY",mId);
	}


}
