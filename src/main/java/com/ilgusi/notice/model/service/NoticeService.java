package com.ilgusi.notice.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.notice.model.dao.NoticeDao;
import com.ilgusi.notice.model.vo.Notice;

@Service
public class NoticeService {
	@Autowired
	private NoticeDao dao;

	public ArrayList<Notice> selectNoticeList() {
		ArrayList<Notice> list = dao.selectNoticeList();
		return list;
	}

	public int insertNotice(Notice n) {
		return dao.insertNotice(n);
	}

	public Notice selectNoticeView(int nNo) {
		return dao.selectNoticeView(nNo);
	}

	public int deleteNotice(int nNo) {
		return dao.deleteNotice(nNo);
	}

	public int updateNotice(Notice n) {
		System.out.println("updateNotice : service 들어옴");
		return dao.updateNotice(n);
	}
}
