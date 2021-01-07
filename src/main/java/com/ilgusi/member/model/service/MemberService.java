package com.ilgusi.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.member.model.dao.MemberDao;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Join;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;

	public Member loginMember(String id, String pw) {
		return dao.loginMember(id,pw);
	}

	//(문정)사용자 마이페이지-이메일, 폰번호, 비번 변경
	public int changeMypage(String mId, String data, String object) {
		return dao.changeMypage(mId, data,object );
	}

	//(문정)사용자 마이페이지-회원탈퇴
	public int deleteMember(String mId, String mPw) {
		return dao.deleteMember(mId, mPw);
	}

	public int registerMember(Member m) {
		return dao.registerMember(m);
	}
}