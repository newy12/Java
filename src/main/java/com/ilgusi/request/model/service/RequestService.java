package com.ilgusi.request.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.request.model.dao.RequestDao;
import com.ilgusi.request.model.vo.Request;
import com.ilgusi.request.model.vo.RequestPageData;

@Service
public class RequestService {
	@Autowired
	private RequestDao dao;

	//(문정) 의뢰글 작성 insert
	public int requestInsert(Request req) {
		return dao.requestInsert(req);
	}

	//(문정) 의뢰 리스트 + ㅍ ㅔ이징 처리
	public RequestPageData selectRequestList(int reqPage, String order, String subject, String keyword) {
		//1. 한 페이지에 보여줄 리스트 개수
		int numPerPage = 10;
		
		//2. 쿼리에서 시작-끝 번호로 리스트결과 가져옴
		int end = reqPage*numPerPage;
		int start = (end - numPerPage) + 1;
		ArrayList<Request> list = dao.selectRequestList(start, end, order, subject, keyword);
		
		//3. 의뢰글 총 몇개?
		int totalCount = dao.totalCount(subject, keyword);
		
		//4. 페이지가 총 몇 개?
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		
		//5. 페이지 네비 몇 개까지 보여줄 건지?
		int pageNaviSize = 10;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		//6. 페이지 네비
		String pageNavi = "";
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/requestList.do?reqPage="+(pageNo-1)+"&order="+order+"&subject="+subject+"&keyword="+keyword+"'><<</a></li>";
		}
		
		//네비게이션 숫자
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<li class='page-item'><a class='page-link target' href='#' style='background-color:rgb(49, 76, 131)'>"+pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'><a class='page-link' href='/requestList.do?reqPage="+(pageNo)+"&order="+order+"&subject="+subject+"&keyword="+keyword+"'>"+pageNo+"</a></li>";
			}
			pageNo++;
			
			if(pageNo > totalPage) {
				break;
			}
		}
		
		//다음 버튼
		if(reqPage <= (totalPage/pageNaviSize)) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/requestList.do?reqPage="+pageNo+"&order="+order+"&subject="+subject+"&keyword="+keyword+"'>>></a></li>";
		}
		
		//페이지 할게 없으면
		if(totalCount <= numPerPage) {
			pageNavi = "";
		}
		
		//마지막) 객체에 값 넣어줌
		RequestPageData rpd = new RequestPageData();
		rpd.setList(list);
		rpd.setPageNavi(pageNavi);
		rpd.setTotalCount(totalCount);
		return rpd;
	}

	//(문정) 의뢰게시판 상세보기
	public Request selectOneRequest(int reqNo) {
		return dao.selectOneRequest(reqNo);
	}

	//(문정) 의뢰 게시판 수정하기
	public int requestUpdate(Request req) {
		return dao.requestUpdate(req);
	}

	//(문정) 의뢰글 삭제
	public int requestDeleteOne(int reqNo) {
		return dao.requestDeleteOne(reqNo);
	}

	//(문정) 판매자의 서비스 리스트
	public ArrayList<com.ilgusi.service.model.vo.Service> serviceList(String freeId) {
		return dao.selectList(freeId);
	}
	
}
