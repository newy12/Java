package com.ilgusi.member.model.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ilgusi.member.model.vo.Member;

@Repository
public class MemberDao {

	@Autowired
	private SqlSessionTemplate session;

	public Member loginMember(String id, String pw) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		return session.selectOne("member.loginMember",map);
	}
	
	//(문정)사용자 마이페이지-이메일, 폰번호, 비번 변경
	@Transactional
	public int changeMypage(String mId, String data, String object) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", mId);
		map.put("data", data);
		map.put("object", object);
		return session.update("member.changeMypage", map);
	}

	//(문정)사용자 마이페이지-회원탈퇴
	public int deleteMember(String mId, String mPw) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", mId);
		map.put("pw", mPw);
		return session.delete("member.deleteMember", map);
	}

	public int registerMember(Member m) {
		return session.insert("member.registerMember",m);
	}
}
