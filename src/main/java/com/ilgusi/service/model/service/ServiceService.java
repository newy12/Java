package com.ilgusi.service.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.category.model.vo.Category;
import com.ilgusi.favorite.model.vo.Favorite;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.dao.ServiceDao;
import com.ilgusi.service.model.vo.Join;
import com.ilgusi.service.model.vo.ReviewJoin;
import com.ilgusi.service.model.vo.ReviewPageData;
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
		if (result > 0) {
			int serviceNo = dao.selectServiceNo();
			for (ServiceFile sf : join.getFileList()) {
				result = dao.insertServiceFile(serviceNo, sf.getFilename(), sf.getFilepath());
			}
		}

		return result;
	}

	// 프리랜서 마이페이지 정보 수정
	public int updateFreelancer(Member m) {
		return dao.updateFreelancer(m);
	}

	public Member selectOneMember(int MNo) {
		return dao.selectOneMember(MNo);
	}

	public Join selectReviewList(String mId, int reqPage) {
		int numPerPage = 4;
		int end = reqPage * numPerPage;// 1보내면 end=4
		int start = end - numPerPage + 1;//
		List<ReviewJoin> j = dao.selectReviewList(mId, start, end);
		int totalCount = dao.totalCount(mId);
		System.out.println("end<<>>>>>>>>>>>>>" + end);
		System.out.println("totalCount<<>>>>>>>>>>>>>" + totalCount);
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		String pageNavi = "";
		if (pageNo != 1) {
			pageNavi += "<a href='/introduceFrm.do?mId=" + mId + "&reqPage=" + (pageNo - 1) + "'><</a>";
		}
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo != reqPage) {
				pageNavi += "<a href='/introduceFrm.do?mId=" + mId + "&reqPage=" + pageNo + "'>" + pageNo + "</a>";
				System.out.println(">>>>>>>>>>>>>" + pageNo);
			} else {
				pageNavi += "<span class='selectedPage'>" + pageNo + "</span>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		if (pageNo <= totalPage) {
			pageNavi += "<a href='/introduceFrm.do?mId=" + mId + "&reqPage=" + pageNo + "'>></a>";
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

	// (문정) 마이페이지 - 서비스 후기 등록
	public int serviceReviewInsert(ServiceReview sr) {
		return dao.serviceReviewInsert(sr);
	}

	// (문정) 마이페이지에서 후기 등록하면 tStatus 바꿔줌(리뷰 작성완료로 : 3)
	public int serviceReviewSuccess(int tNo) {
		return dao.serviceReviewSuccess(tNo);
	}

	// (문정) 마이페이지 - 거래 후기 작성한거 확인
	public ServiceReview serviceReviewView(ServiceReview data) {
		return dao.serviceReviewView(data);
	}

	// (문정) 마이페이지 - 거래 후기 업데이트
	public int serviceReviewUpdate(ServiceReview review) {
		return dao.serviceReviewUpdate(review);
	}

	// (문정) 리뷰 삭제
	public int serviceReviewDelete(int rNo) {
		return dao.serviceReviewDelete(rNo);
	}

	// (문정) 리뷰 삭제하면 tStatus 수정해줘야함
	public int serviceTradeStatusUpdate(int tNo) {
		return dao.serviceTradeStatusUpdate(tNo);
	}

	// (다솜) 카테고리 리스트 불러오기
	public ArrayList<Category> categoryList(int cNo) {
		return dao.categoryList(cNo);
	}

	// (다솜)서비스 리스트
	public ServicePageData servicePageList(HashMap<String, Object> map, int reqPage, int cNo, String order) {
		ArrayList<com.ilgusi.service.model.vo.Service> list = dao.selectServiceList(map);

		String keyword = String(map.get("keyword"));
		int numPerPage = 12;
		int totalCount = dao.serviceTotalCount(map);

		System.out.println("totalCount : " + totalCount);

		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}
		// 페이지 네비 길이
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1; // 페이지 네비가 시작하는 페이지 번호

		// 페이지 네비 작성
		String pageNavi = "<ul class='pagination justify-content-center'>";

		// 이전버튼 생성
		if (pageNo != 1) {
			if (keyword == null) {
				pageNavi += "<li class='page=item'><a class='pageNavi page-link' href='/serviceList.do?cNo=" + cNo
						+ "&reqPage=" + (pageNo - 1) + "&order=" + order + "&keyword='> pre </a></li>";
			} else {
				pageNavi += "<li class='page=item'><a class='pageNavi page-link' href='/serviceList.do?cNo=" + cNo
						+ "&reqPage=" + (pageNo - 1) + "&order=" + order + "&keyword=" + keyword + "'> pre </a></li>";
			}

		}
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo != reqPage) {
				if (keyword == null) {
					pageNavi += "<li class='page=item'><a class='pageNavi page-link' href='/serviceList.do?cNo=" + cNo
							+ "&reqPage=" + (pageNo) + "&order=" + order + "&keyword='>" + pageNo + "</a></li>";
				} else {
					pageNavi += "<li class='page=item'><a class='pageNavi page-link' href='/serviceList.do?cNo=" + cNo
							+ "&reqPage=" + (pageNo) + "&order=" + order + "&keyword=" + keyword + "'>" + pageNo
							+ "</a></li>";
				}

			} else {
				pageNavi += "<li class='page=item'><span class='selectedPage pageNavi page-link'>" + pageNo
						+ "</span></li>";
			}
			pageNo++;

			if (pageNo > totalPage) {
				break;
			}
		}

		// 다음 버튼
		if (pageNo <= totalPage) {
			if (keyword == null) {
				pageNavi += "<li class='page=item'><a href='/serviceList.do?cNo=" + cNo + "&reqPage=" + pageNo
						+ "&order=" + order + "&keyword='> next </a></li>";
			}
			pageNavi += "<li class='page=item'><a href='/serviceList.do?cNo=" + cNo + "&reqPage=" + pageNo + "&order="
					+ order + "&keyword=" + keyword + "'> next </a></li>";
		}

		pageNavi += "</ul>";

		ServicePageData spd = new ServicePageData();
		spd.setList(list);
		spd.setPageNavi(pageNavi);

		return spd;
	}

	private String String(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	// (다솜) Servie View 불러오기
	public com.ilgusi.service.model.vo.Service selectServiceView(int sNo) {

		com.ilgusi.service.model.vo.Service s = dao.selectServiceView(sNo);

		if (s != null) {
			System.out.println("service.sNo : " + s.getSNo());
		} else {
			System.out.println("service : s가 null이얌");
		}

		return s;
	}

	public List<ServiceReview> reviewListSize(String mId) {
		return dao.reviewListSize(mId);
	}

	public float sRateAVG(String mId) {
		return dao.sRateAVG(mId);
	}

	public Member selectMemberName(String memberId) {
		return dao.selectMemberName(memberId);
	}

	public ReviewPageData selectReviewList(int sNo, int reqPage, int mNo) {
		// 보여줄 리스트 개수
		int numPerPage = 5;
		// 시작번호 끝번호
		// reqPage 1 -> start : 1 end :5;
		// reqPage 2 -> start : 6 end :10;
		int end = reqPage * numPerPage;
		int start = (end - numPerPage) + 1;
		// 해당 서비스의 리뷰 총 개수
		int totalCount = dao.totalReviewCount(sNo);
		System.out.println("review_totalCount : " + totalCount);
		// 총 리뷰 갯수
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}

		// 페이지 네비
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		String pageNavi = "<ul class='pagination'>";

		if (mNo == -1) {
			// 이전 버튼
			if (pageNo != 1) {
				pageNavi += "<li class='page-item'><a class='page-link' href='/serviceView.do?sNo=" + sNo + "&reqPage="
						+ (pageNo - 1) + ">pre</a></li>";
			}
			// 페이지 네비 버튼
			for (int i = 0; i < pageNaviSize; i++) {
				if (reqPage == pageNo) {
					pageNavi += "<li class='page-item'><span class='page-link selected'>" + pageNo + "</span></li>";
				} else {
					pageNavi += "<li class='page-item'><a class='page-link' href='/serviceView.do?sNo=" + sNo
							+ "&reqPage=" + (pageNo) + "'>" + pageNo + "</a></li>";
				}
				pageNo++;

				if (pageNo > totalPage) {
					break;
				}
			}

			if (reqPage <= (totalPage / pageNaviSize)) {
				pageNavi += "<li class='page-item'><a class='page-link' href='/serviceView2.do?sNo=" + sNo + "&reqPage="
						+ (pageNo + 1) + "'> next </a></li>";
			}
		} else {
			// 이전 버튼
			if (pageNo != 1) {
				pageNavi += "<li class='page-item'><a class='page-link' href='/serviceView2.do?sNo=" + sNo + "&reqPage="
						+ (pageNo - 1) + ">pre</a></li>";
			}
			// 페이지 네비 버튼
			for (int i = 0; i < pageNaviSize; i++) {
				if (reqPage == pageNo) {
					pageNavi += "<li class='page-item'><span class='page-link selected'>" + pageNo + "</span></li>";
				} else {
					pageNavi += "<li class='page-item'><a class='page-link' href='/serviceView2.do?sNo=" + sNo
							+ "&reqPage=" + (pageNo) + "'>" + pageNo + "</a></li>";
				}
				pageNo++;

				if (pageNo > totalPage) {
					break;
				}
			}

			if (reqPage <= (totalPage / pageNaviSize)) {
				pageNavi += "<li class='page-item'><a class='page-link' href='/serviceView.do?sNo=" + sNo + "&reqPage="
						+ (pageNo + 1) + "&mNo=" + mNo + "'> next </a></li>";
			}
		}

		if (totalCount <= numPerPage) {
			pageNavi = "</ul>";
		}

		ArrayList<ServiceReview> list = dao.serviceViewReviewList(sNo, start, end);

		ReviewPageData rpd = new ReviewPageData();
		rpd.setList(list);
		rpd.setPageNavi(pageNavi);

		return rpd;
	}

	// (다솜)해당유저가 등록한 서비스 목록
	public ArrayList<com.ilgusi.service.model.vo.Service> userService(String memberId) {
		return dao.userService(memberId);
	}

	// (다솜)해당서비스에 등록된 파일 불러오기
	public ArrayList<ServiceFile> fileList(int sNo) {
		// TODO Auto-generated method stub
		return dao.fileList(sNo);
	}

	// (문정) 결제 진행
	public int insertServicePay(ServicePay pay) {
		return dao.insertServicePay(pay);
	}

	// (문정) trade status 변경
	public int updateTradeStatus(int tNo) {
		return dao.updateTradeStatus(tNo);
	}

	// (문정) 리뷰 작성하면 서비스테이블 s_rate에 평점 넣어줌
	public int serviceUpdateSRate(int sNo) {
		return dao.serviceUpdateSRate(sNo);
	}

	// (도현) search service
	public List<com.ilgusi.service.model.vo.Service> searchService(int begin, int end, java.lang.String keyword) {
		return dao.searchService(begin, end, keyword);
	}

	// (도현) search serviceCount
	public int selectServiceCount(java.lang.String keyword) {
		return dao.selectServiceCount(keyword);
	}

	public int selectMaxPageCount(int numPerPage, int listCount) {
		int maxPageCount = listCount / numPerPage;

		if (listCount % numPerPage > 0)
			maxPageCount++;

		return maxPageCount;
	}

	// 프리랜서 마이페이지 서비스 삭제
	public int deleteService(int sNo) {
		return dao.deleteService(sNo);
	}

	public ArrayList<com.ilgusi.service.model.vo.Service> selectMyList(java.lang.String mId, java.lang.String order) {
		return dao.selectServiceList(mId, order);
	}

	// (문정) 프리랜서가 등록한 총 서비스 개수
	public int selectFreeServiceCount(String mId) {
		return dao.selectFreeServiceCount(mId);
	}

	public Favorite searchFavorite(int mNo, int sNo) {
		return dao.searchFavorite(mNo, sNo);
	}

}
