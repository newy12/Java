package com.ilgusi.service.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ilgusi.category.model.vo.Category;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Join;
import com.ilgusi.service.model.vo.ServiceFile;
import com.ilgusi.service.model.vo.ServiceReview;
import com.ilgusi.service.model.vo.ServiceTrade;

@Repository
public class ServiceDao {
	@Autowired
	private SqlSessionTemplate session;

	public Join selectOneMember(String mId) {
		return session.selectOne("service.selectOneMember",mId);
	}
	public int insertService(Join join) {
		return session.insert("service.insertService", join);
	}

	public int updateFreelancer(Member m) {
		return session.update("service.updateFreelancer", m);
	}

	public Member selectOneMember(int MNO) { //프리랜서 한명의 개인페이지 가져오는거
		return session.selectOne("service.freelancerOneMember",MNO);
	}
	public List<ServiceReview> selectReviewList(String mId,int start,int end) {
		Join join = new Join();
		join.setMId(mId);
		join.setMainCategory(start);
		join.setSubCategory(end);
		List<ServiceReview> j = session.selectList("service.selectListReview",join);
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
		List<com.ilgusi.service.model.vo.Service> j = session.selectList("service.selectServiceList",mId);
		return j;
	}
	public int totalCount() {	
		return session.selectOne("service.selectTotalCount");
		
	}
	

	//(문정) 마이페이지 - 서비스 후기 등록
	public int serviceReviewInsert(ServiceReview sr) {
		return session.insert("review.serviceReviewInsert", sr);
	}
	
	//(문정) 마이페이지에서 후기 등록하면 tStatus 바꿔줌(리뷰 작성완료로 : 3)
	public int serviceReviewSuccess(int tNo) {
		return session.update("review.serviceReviewSuccess", tNo);
	}
	
	//(문정) 마이페이지 - 거래 후기 작성한거 확인
	public ServiceReview serviceReviewView(ServiceReview data) {
		return session.selectOne("review.serviceReviewView", data);
	}
	
	//(문정) 거래 후기 수정
	public int serviceReviewUpdate(ServiceReview review) {
		return session.update("review.serviceReviewUpdate",review);
	}
	
	//(문정) 거래 삭제
	public int serviceReviewDelete(int rNo) {
		return session.delete("review.serviceReviewDelete", rNo);
	}
	
	//(문정) 거래 삭제했을때 tStatus 수정
	public int serviceTradeStatusUpdate(int tNo) {
		return session.update("review.serviceTradeStatusUpdate",tNo);
	}
	
	//(다솜) 서비스 리스트 - 카테고리 불러오기
	public ArrayList<Category> categoryList(int cNo) {
		List<Category> list = session.selectList("category.categoryList",cNo);
		return (ArrayList<Category>)list;
	}
	//(다솜)서비스 리스트 - 서비스 불러오기
	/*
	 * public ArrayList<com.ilgusi.service.model.vo.Service>
	 * serviceList(com.ilgusi.service.model.vo.Service s) {
	 * List<com.ilgusi.service.model.vo.Service> list =
	 * session.selectList("service.serviceList",s); return
	 * (ArrayList<com.ilgusi.service.model.vo.Service>)list; }
	 */
	public ArrayList<String> brandList(com.ilgusi.service.model.vo.Service s) {
		List<String> list = session.selectList("service.brandList",s);
		return (ArrayList<String>) list;
	}
	//(다솜)서비스 리스트 페이징
	public ArrayList<com.ilgusi.service.model.vo.Service> selectServiceList(HashMap<String, Integer> map) {
		System.out.println("dao들어옴");
		List<com.ilgusi.service.model.vo.Service> list = session.selectList("service.serviceListPage",map);
		System.out.println("dao_main : " + map.get("main"));
		return (ArrayList<com.ilgusi.service.model.vo.Service>) list;
	}
	//(다솜)서비스 데이터 카운트
	public int serviceTotalCount(HashMap<String, Integer> map) {
		
		return session.selectOne("service.selectServiceTotalCount",map);
	}
	//(다솜)서비스 상세보기
	public com.ilgusi.service.model.vo.Service selectServiceView(int sNo) {
		com.ilgusi.service.model.vo.Service s = new com.ilgusi.service.model.vo.Service();
		s = session.selectOne("service.selectServiceView",sNo);
		return s;
	}
	public ArrayList<ServiceReview> serviceViewReviewList(int sNo, int start, int end) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("sNo", sNo);
		
		List<ServiceReview> list = session.selectList("review.serviceReviewList",map);
		return (ArrayList<ServiceReview>)list;
	}
	
	public int totalReviewCount(int sNo) {
		return session.selectOne("review.totalRiviewCount",sNo);
	}
	


	/*
	 * public ArrayList<Category> selectCategory(int cNO) { List<Category> list =
	 * session.selectList("") return null; }
	 */
	//(영재)review총 갯수 구하기
	public List<ServiceReview> reviewListSize(String mId) {
		List<ServiceReview> list = session.selectList("service.reviewListSize",mId);
		return list;
	}
	public List<com.ilgusi.service.model.vo.Service> sRateAVG(String mId) {
		List<com.ilgusi.service.model.vo.Service> list = session.selectList("service.sRateAVG",mId);
		return list;
	}
	
	public Member selectMemberName(String memberId) {
		return session.selectOne("member.selectBrand",memberId);
	}
	public ArrayList<com.ilgusi.service.model.vo.Service> userService(String memberId) {
		List<com.ilgusi.service.model.vo.Service> list = session.selectList("service.userServiceList",memberId);
		return (ArrayList<com.ilgusi.service.model.vo.Service>)list;
	}
	
	
	

	
	

}
