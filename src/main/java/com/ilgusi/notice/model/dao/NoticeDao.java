package com.ilgusi.notice.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.notice.model.vo.Notice;

@Repository
public class NoticeDao {
	@Autowired
	private SqlSessionTemplate session;

	public ArrayList<Notice> selectNoticeList(int start, int end, String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("keyword", keyword);
		List<Notice> list = session.selectList("notice.noticeList",map);
		return (ArrayList<Notice>)list;
	}
	

	public int insertNotice(Notice n) {
		return session.insert("notice.insertNotice",n);
	}

	public Notice selectNoticeView(int nNo) {
		return session.selectOne("notice.noticeView",nNo);
	}

	public int deleteNotice(int nNo) {
		return session.delete("notice.deleteNotice",nNo);
	}

	public int updateNotice(Notice n) {
		System.out.println("updateNotice : dao 들어옴");
		int nNo = n.getNNo();
		String nTitle = n.getNTitle();
		System.out.println("dao 값확인 n_no:"+nNo);
		System.out.println("dao 값확인 n_Title:"+nTitle);
		
		System.out.println();
		return session.update("notice.updateNotice", n);
	}

	public int totalCount() {
		return session.selectOne("notice.noticeCount");
	}
}
