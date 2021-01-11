package com.ilgusi.notice.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.notice.model.vo.Notice;

@Repository
public class NoticeDao {
	@Autowired
	private SqlSessionTemplate session;

	public ArrayList<Notice> selectNoticeList() {
		List<Notice> list = session.selectList("notice.noticeList");
		return (ArrayList<Notice>)list;
	}

	public int insertNotice(Notice n) {
		return session.insert("notice.insertNotice",n);
	}

	public Notice selectNoticeView(int nNo) {
		return session.selectOne("notice.noticeView",nNo);
	}
}
