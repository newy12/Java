package com.ilgusi.notice.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.notice.model.dao.NoticeDao;
import com.ilgusi.notice.model.vo.Notice;
import com.ilgusi.notice.model.vo.NoticePageData;

@Service
public class NoticeService {
	@Autowired
	private NoticeDao dao;

	public NoticePageData selectNoticeList(int reqPage, String keyword) {
		NoticePageData npd = new NoticePageData();
		
		int numPerPage = 12;
		int end = reqPage*numPerPage;
		int start = (end-numPerPage) +1;
		
		int totalCount = dao.totalCount();
		System.out.println("공지사항 토탈카운트:"+totalCount);
		
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		//페이지 네비
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize +1;
		String pageNavi = "<ul class='pagination justify-content-center'>";
		
		//이전 버튼 
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/noticeList.do?reqPage="+(pageNo-1)+"&keyword="+keyword+"'>pre</a></li>";
		}
		//페이지 네비 버튼
		for(int i =0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<li class='page-item'>"
						+ "<span class='page-link selected'>"+pageNo+"</span></li>";
			}else {
				pageNavi += "<li class='page-item'>"
						+ "<a class='page-link' href='/noticeList.do?reqPage="+(pageNo)+"&keyword="+keyword+"'>"+pageNo+"</a></li>";
			}
			pageNo++;
			
			if(pageNo > totalPage) {
				break;
			}
		}
		
		if(reqPage <= (totalPage/pageNaviSize)) {
			pageNavi += "<li class='page-item'>"
					+ "<a class='page-link' href='/noticeList.do?reqPage="+(pageNo+1)+"&keyword="+keyword+"'> next </a></li>";
		}
		
		if(totalCount <= numPerPage) {
			pageNavi = "</ul>";
		}
	
		ArrayList<Notice> list = dao.selectNoticeList(start,end,keyword);
		npd.setList(list);
		npd.setPageNavi(pageNavi);
		
		return npd;
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
