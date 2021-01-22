package com.ilgusi.service.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.category.model.vo.Category;
import com.ilgusi.favorite.model.vo.Favorite;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Join;
import com.ilgusi.service.model.vo.ReviewJoin;
import com.ilgusi.service.model.vo.ServiceFile;
import com.ilgusi.service.model.vo.ServicePay;
import com.ilgusi.service.model.vo.ServiceReview;

@Repository
public class ServiceDao {
	@Autowired
	private SqlSessionTemplate session;

	public Join selectOneMember(String mId) {
		return session.selectOne("service.selectOneMember", mId);
	}

	public int insertService(Join join) {
		return session.insert("service.insertService", join);
	}

	// 프리랜서 마이페이지 정보 수정
	public int updateFreelancer(Member m) {
		return session.update("service.updateFreelancer", m);
	}

	public Member selectOneMember(int MNO) { // 프리랜서 한명의 개인페이지 가져오는거
		return session.selectOne("service.freelancerOneMember", MNO);
	}

	public List<ReviewJoin> selectReviewList(String mId, int start, int end) {
		Join join = new Join();
		join.setMId(mId);
		join.setMainCategory(start);
		join.setSubCategory(end);
		List<ReviewJoin> j = session.selectList("service.selectListReview", join);
		return j;
	}

	public int selectServiceNo() {
		return session.selectOne("service.selectServiceNo");
	}

	public int insertServiceFile(int serviceNo, String filename, String filepath) {
		ServiceFile serviceFile = new ServiceFile();
		serviceFile.setSNo(serviceNo);
		serviceFile.setFilename(filename);
		serviceFile.setFilepath(filepath);
		return session.insert("service.insertServiceFile", serviceFile);
	}

	public List<com.ilgusi.service.model.vo.Service> serviceList(String mId) {
		List<com.ilgusi.service.model.vo.Service> j = session.selectList("service.selectServiceList", mId);
		return j;
	}

	public int totalCount(String mId) {
		if (session.selectOne("service.selectTotalCount", mId) == null) {
			return 0;
		} else {
			return session.selectOne("service.selectTotalCount", mId);
		}
	}

	// (문정) 마이페이지 - 서비스 후기 등록
	public int serviceReviewInsert(ServiceReview sr) {
		return session.insert("review.serviceReviewInsert", sr);
	}

	// (문정) 마이페이지에서 후기 등록하면 tStatus 바꿔줌(리뷰 작성완료로 : 3)
	public int serviceReviewSuccess(int tNo) {
		return session.update("review.serviceReviewSuccess", tNo);
	}

	// (문정) 마이페이지 - 거래 후기 작성한거 확인
	public ServiceReview serviceReviewView(ServiceReview data) {
		return session.selectOne("review.serviceReviewView", data);
	}

	// (문정) 거래 후기 수정
	public int serviceReviewUpdate(ServiceReview review) {
		return session.update("review.serviceReviewUpdate", review);
	}

	// (문정) 거래 삭제
	public int serviceReviewDelete(int rNo) {
		return session.delete("review.serviceReviewDelete", rNo);
	}

	// (문정) 거래 삭제했을때 tStatus 수정
	public int serviceTradeStatusUpdate(int tNo) {
		return session.update("review.serviceTradeStatusUpdate", tNo);
	}

	// (다솜) 서비스 리스트 - 카테고리 불러오기
	public ArrayList<Category> categoryList(int cNo) {
		List<Category> list = session.selectList("category.categoryList", cNo);
		return (ArrayList<Category>) list;
	}

	// (다솜)서비스 리스트 페이징
	public ArrayList<com.ilgusi.service.model.vo.Service> selectServiceList(HashMap<String, Object> map) {
		List<com.ilgusi.service.model.vo.Service> list = session.selectList("service.serviceListPage", map);
		return (ArrayList<com.ilgusi.service.model.vo.Service>) list;
	}

	// (다솜)서비스 토탈 카운트
	public int serviceTotalCount(HashMap<String, Object> map) {
		return session.selectOne("service.selectServiceTotalCount", map);
	}

	// (다솜)서비스 상세보기
	public com.ilgusi.service.model.vo.Service selectServiceView(int sNo) {
		com.ilgusi.service.model.vo.Service s = new com.ilgusi.service.model.vo.Service();
		s = session.selectOne("service.selectServiceView", sNo);
		return s;
	}

	// (다솜)서비스 리뷰 불러오기
	public ArrayList<ServiceReview> serviceViewReviewList(int sNo, int start, int end) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("sNo", sNo);

		List<ServiceReview> list = session.selectList("review.serviceReviewList", map);
		return (ArrayList<ServiceReview>) list;
	}

	// (다솜) 리뷰 페이징을 위한 토탈카운트
	public int totalReviewCount(int sNo) {
		return session.selectOne("review.totalRiviewCount", sNo);
	}

	// (다솜) 전문가 정보 불러오기
	public Member selectMemberName(String memberId) {
		return session.selectOne("member.selectBrand", memberId);
	}

	// (다솜) 다른 서비스 불러오기
	public ArrayList<com.ilgusi.service.model.vo.Service> userService(String memberId) {
		List<com.ilgusi.service.model.vo.Service> list = session.selectList("service.userServiceList", memberId);
		return (ArrayList<com.ilgusi.service.model.vo.Service>) list;
	}

	// (다솜) 서비스파일 리스트
	public ArrayList<ServiceFile> fileList(int sNo) {
		List<ServiceFile> list = session.selectList("service.fileList", sNo);
		return (ArrayList<ServiceFile>) list;
	}

	// (영재)review총 갯수 구하기
	public List<ServiceReview> reviewListSize(String mId) {
		List<ServiceReview> list = session.selectList("service.reviewListSize", mId);
		return list;
	}

	public float sRateAVG(String mId) {
		// List<com.ilgusi.service.model.vo.Service> list =
		// session.selectList("service.sRateAVG",mId);
		if (session.selectOne("service.sRateAVG", mId) == null) {
			return 0.0f;
		} else {
			return session.selectOne("service.sRateAVG", mId);
		}
	}

	// (문정) 결제 진행
	public int insertServicePay(ServicePay pay) {
		return session.insert("servicePay.insertSerivcePay", pay);
	}

	// (문정) tradeStatus 변경
	public int updateTradeStatus(int tNo) {
		return session.update("trade.updateTradeStatus", tNo);
	}

	// (도현) search service
	public List<com.ilgusi.service.model.vo.Service> searchService(int begin, int end, String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("keyword", keyword);
		return session.selectList("service.searchService", map);
	}

	// (도현) search serviceCount
	public int selectServiceCount(String keyword) {
		return session.selectOne("service.selectServiceCount", keyword);
	}

	// 프리랜서마이페이지 서비스 삭제
	public int deleteService(int sNo) {
		return session.update("service.delService", sNo);
	}

	public ArrayList<com.ilgusi.service.model.vo.Service> selectServiceList(String mId, String order) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", mId);
		map.put("order", order);
		List<com.ilgusi.service.model.vo.Service> list = session.selectList("service.selectMyList", map);
		return (ArrayList<com.ilgusi.service.model.vo.Service>) list;
	}

	// (문정) 리뷰 작성하면 서비스테이블 s_rate에 평점 넣어줌
	public int serviceUpdateSRate(int sNo) {
		return session.update("review.serviceUpdateSRate", sNo);
	}

	// (문정) 프리랜서가 등록한 총 서비스 개수
	public int selectFreeServiceCount(String mId) {
		return session.selectOne("service.selectFreeServiceCount", mId);
	}

	public Favorite searchFavorite(int mNo, int sNo) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("mNo", mNo);
		map.put("sNo", sNo);
		return session.selectOne("favorite.searchMyFavorite", map);
	}

}
