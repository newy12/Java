package com.ilgusi.service.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.category.model.vo.Category;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.notice.model.vo.NoticePageData;
import com.ilgusi.service.model.dao.ServiceDao;
import com.ilgusi.service.model.vo.Join;
import com.ilgusi.service.model.vo.ServiceFile;
import com.ilgusi.service.model.vo.ServicePageData;
import com.ilgusi.service.model.vo.ServicePay;
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
			System.out.println("end<<>>>>>>>>>>>>>"+end);
			System.out.println("totalCount<<>>>>>>>>>>>>>"+totalCount);
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
				pageNavi += "<a href='/introduceFrm.do?mId="+mId+"&reqPage="+(pageNo-1)+"'><</a>";
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
				pageNavi += "<a href='/introduceFrm.do?mId="+mId+"&reqPage="+pageNo+"'>></a>";
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

		//(문정) 마이페이지에서 후기 등록하면 tStatus 바꿔줌(리뷰 작성완료로 : 3)
		public int serviceReviewSuccess(int tNo) {
			return dao.serviceReviewSuccess(tNo);
		}

		//(문정) 마이페이지 - 거래 후기 작성한거 확인
		public ServiceReview serviceReviewView(ServiceReview data) {
			return dao.serviceReviewView(data);
		}

		//(문정) 마이페이지 - 거래 후기 업데이트
		public int serviceReviewUpdate(ServiceReview review) {
			return dao.serviceReviewUpdate(review);
		}

		//(문정) 리뷰 삭제
		public int serviceReviewDelete(int rNo) {
			return dao.serviceReviewDelete(rNo);
		}
		
		//(문정) 리뷰 삭제하면 tStatus 수정해줘야함
		public int serviceTradeStatusUpdate(int tNo) {
			return dao.serviceTradeStatusUpdate(tNo);
		}

		/*
		 * public ArrayList<Category> selectCategory(int cNO) { return
		 * dao.selectCategory(cNO); }
		 */


		
		//(다솜) 카테고리 리스트 불러오기
		public ArrayList<Category> categoryList(int cNo) {
			return dao.categoryList(cNo);
		}

		/*
		 * //(다솜) 서비스 리스트 불러오기 public ArrayList<com.ilgusi.service.model.vo.Service>
		 * selectServiceList(HashMap<String, Integer> map) {
		 * ArrayList<com.ilgusi.service.model.vo.Service>list =
		 * dao.selectServiceList(map); return list; }
		 */
		
		//(다솜)서비스 리스트 
		public ServicePageData servicePageList(HashMap<String, Integer> map) {
			
			ArrayList<com.ilgusi.service.model.vo.Service>list = dao.selectServiceList(map);
			
			int numPerPage = 16;
			int totalCount = dao.serviceTotalCount(map);
			
			System.out.println("totalCount : " + totalCount);
			
			int totalPage = 0;
			if(totalCount%numPerPage == 0 ) {
				totalPage = totalCount/numPerPage;
			}else {
				totalPage = totalCount/numPerPage+1;
			}
			
			int reqPage = map.get("reqPage");
			
			//페이지 네비 길이
			int pageNaviSize = 5;
			int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize +1; //페이지 네비가 시작하는 페이지 번호
			
			//페이지 네비 작성
			String pageNavi = "<ul class='pagination justify-content-center'>";
			
			int cNo = map.get("cNo");
			
			//이전버튼 생성
			if(pageNo != 1) {
				pageNavi += "<li class='page=item'><a class='pageNavi page-link' href='/serviceList.do?cNo="+cNo+"&reqPage="+(pageNo-1)+"'> pre </a></li>";
			}
			for(int i=0; i<pageNaviSize; i++) {
				if(pageNo != reqPage) {
					pageNavi += "<li class='page=item'><a class='pageNavi page-link' href='/serviceList.do?cNo="+cNo+"&reqPage="+(pageNo)+"'>"+pageNo+"</a></li>";
				}else {
					pageNavi += "<li class='page=item'><span class='selectedPage pageNavi page-link'>"+pageNo+"</span></li>";
				}
				pageNo++;
				
				if(pageNo > totalPage) {
					break;
				}
			}
			
			//다음 버튼 
			if(pageNo <= totalPage) {
				pageNavi += "<li class='page=item'><a href='/serviceList.do?cNo="+cNo+"&reqPage="+pageNo+"'> next </a></li>";
			}
			
			pageNavi += "</ul>";
			
			ServicePageData spd = new ServicePageData(list,pageNavi);
			
			return spd;
		}
		
		
		//(다솜) 브랜드 이름 불러오기
		public ArrayList<String> brandList(com.ilgusi.service.model.vo.Service s) {
			return dao.brandList(s);
		}
		

		public List<ServiceReview> reviewListSize(String mId) {
			return dao.reviewListSize(mId);
		}

		public List<com.ilgusi.service.model.vo.Service> sRateAVG(String mId) {
			return dao.sRateAVG(mId);
		}

		//(문정) 결제 진행
		public int insertServicePay(ServicePay pay) {
			return dao.insertServicePay(pay);
		}

		//(문정) trade status 변경
		public int updateTradeStatus(int tNo) {
			return dao.updateTradeStatus(tNo);
		}

		

		

		

}
