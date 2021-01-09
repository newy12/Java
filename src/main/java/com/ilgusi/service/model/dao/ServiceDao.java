package com.ilgusi.service.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Join;
import com.ilgusi.service.model.vo.Service;
import com.ilgusi.service.model.vo.ServiceFile;
import com.ilgusi.service.model.vo.ServiceReview;

@Repository
public class ServiceDao {
	@Autowired
	private SqlSessionTemplate session;

<<<<<<< HEAD
	public Join selectOneMember(String mId) {
		return session.selectOne("service.selectOneMember",mId);
=======
	public Join selectOneMember(String id) {
		return session.selectOne("service.selectOneMember", id);
>>>>>>> 6e68c3b6613a9ab49c0a9d98131d34d6a7ee6378
	}

	public int insertService(Join join) {
		return session.insert("service.insertService", join);
	}

	public int updateFreelancer(Member m) {
		return session.update("service.updateFreelancer", m);
	}

<<<<<<< HEAD
	public Member selectOneMember(int MNO) { //프리랜서 한명의 개인페이지 가져오는거
		return session.selectOne("service.freelancerOneMember",MNO);
	}
	public List<ServiceReview> selectReviewList(String mId) {
		List<ServiceReview> j = session.selectList("service.selectListReview",mId);
=======
	public Member selectOneMember(int MNO) {
		return session.selectOne("service.freelancerOneMember", MNO);
	}

	public List<ServiceReview> selectReviewList(String id) {
		List<ServiceReview> j = session.selectList("service.selectListReview", id);
>>>>>>> 6e68c3b6613a9ab49c0a9d98131d34d6a7ee6378
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



}
