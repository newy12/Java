package com.ilgusi.service.model.dao;

import java.util.ArrayList;
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

	
	/*
	 * public ArrayList<Category> selectCategory(int cNO) { List<Category> list =
	 * session.selectList("") return null; }
	 */

}
