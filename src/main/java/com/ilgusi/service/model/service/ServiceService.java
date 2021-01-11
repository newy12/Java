package com.ilgusi.service.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.dao.ServiceDao;
import com.ilgusi.service.model.vo.Join;
import com.ilgusi.service.model.vo.ServiceFile;
import com.ilgusi.service.model.vo.ServiceReview;

@Service
public class ServiceService {
	@Autowired
	private ServiceDao dao;

		public Join selectOneMember(String mId) {
		return dao.selectOneMember(mId);
	}

		public int insertService(Join join) {
			int result = dao.insertService(join);
			if(result>0) {
				int serviceNo = dao.selectServiceNo();
				for(ServiceFile sf: join.getFileList()) {
					result = dao.insertServiceFile(serviceNo,sf.getFilename(),sf.getFilepath());
				}
			}
			
			
			
			return result;
		}

		public int updateFreelancer(Member m) {
			return dao.updateFreelancer(m);
		}

		public Member selectOneMember(int MNo) {
			return dao.selectOneMember(MNo);
		}

		public Join selectReviewList(String mId,int reqPage) {
			int numPerPage = 4;
			int end = reqPage * numPerPage;//1보내면 end=4
			int start = end - numPerPage + 1;//
			List<ServiceReview> j = dao.selectReviewList(mId,start,end);
			int totalCount = dao.totalCount();
			int totalPage = 0;
			if(totalCount%numPerPage ==0) {
				totalPage = totalCount/numPerPage;
			}else {
				totalPage = totalCount/numPerPage+1;
			}
			int pageNaviSize = 5;
			int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
			String pageNavi = "";
			if(pageNo != 1) {
				pageNavi += "<a href='/introduceFrm.do?mId="+mId+"&reqPage="+(pageNo-1)+"'>[이전]</a>";
			}
			for(int i=0;i<pageNaviSize;i++) {
				if(pageNo != reqPage) {
					pageNavi += "<a href='/introduceFrm.do?mId="+mId+"&reqPage="+pageNo+"'>"+pageNo+"</a>";
					System.out.println(">>>>>>>>>>>>>"+pageNo);
				}else {
					pageNavi += "<span class='selectedPage'>"+pageNo+"</span>";
				}
				pageNo++;
				if(pageNo > totalPage) {
					break;
				}
			}
			if(pageNo <= totalPage) {
				pageNavi += "<a href='/introduceFrm.do?mId="+mId+"&reqPage="+pageNo+"'>[다음]</a>";
			}
			Join join = new Join();
			join.setReviewList(j);
			join.setPageNavi(pageNavi);
			return join;
		}

		public List<com.ilgusi.service.model.vo.Service> serviceList(String mId) {
			List<com.ilgusi.service.model.vo.Service> j = dao.serviceList(mId); 
			return j;
		}

		//(문정) 마이페이지 - 서비스 후기 등록
		public int serviceReviewInsert(ServiceReview sr) {
			return dao.serviceReviewInsert(sr);
		}
}
