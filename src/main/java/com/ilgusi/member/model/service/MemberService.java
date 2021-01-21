package com.ilgusi.member.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.member.model.dao.MemberDao;
import com.ilgusi.member.model.vo.Member;

import common.SHA256Util;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;
	private SHA256Util enc = new SHA256Util();

	// (도현) 비번 암호화 기능
	public String encPw(String pw) {
		// 비번 암호화
		try {
			pw = enc.encPw(pw);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pw;
	}
	// (도현)메인페이지에 판매진행,구매진행중 정보 전달
	public int selectBuyingCount(int mNo) {
		return dao.selectBuyingCount(mNo);
	}
	public int selectSellingCount(String mId) {
		return dao.selectSellingCount(mId);
	}
	// (도현) 로그인기능
	public Member loginMember(String id, String pw) {
		// 비번 암호화
//		pw = encPw(pw);

		return dao.loginMember(id, pw);
	}

	// (도현) 회원가입 기능
	public int registerMember(Member m) {
		// 비번 암호화
//		m.setMPw(encPw(m.getMPw()));

		return dao.registerMember(m);
	}

	// (도현) 아이디 중복검사 기능
	public Member checkId(String id) {
		return dao.checkId(id);
	}

	// (도현) 아이디/비밀번호 찾기 기능
	public Member searchIdPw(Member m) {
		return dao.searchIdPw(m);
	}

	// (도현)비밀번호 찾기 후 - 비밀번호 변경
	public int changePw(Member m) {
		return dao.changePw(m);
	}

	// (문정)사용자 마이페이지-이메일, 폰번호, 비번 변경
	public int changeMypage(String mId, String data, String object) {
		return dao.changeMypage(mId, data, object);
	}

	// (문정)사용자 마이페이지-회원탈퇴
	public int deleteMember(String mId) {
		return dao.deleteMember(mId);
	}
	
	//(문정) 마이페이지에서 사용자-프리랜서 전환
	public int changeGrade(String mId,  int grade) {
		return dao.changeGrade(mId, grade);
	}

	//(문정) 로그인하면 grade를 1로 셋팅
	public int settingMemberGrade(Member m) {
		return dao.settingMemberGrade(m);
	}
	
	//(문정)거래중인 서비스가 있는지 확인
	public int tradeStatus(int mNo) {
		return dao.tradeStatus(mNo);
	}
	
	//(문정)탈퇴하기전 delete_status = 'y' 셋팅
	public int setDeleteStatusY(String mId) {
		return dao.setDeleteStatusY(mId);
	}


}