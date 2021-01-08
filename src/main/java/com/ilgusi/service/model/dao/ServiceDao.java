package com.ilgusi.service.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Join;
import com.ilgusi.service.model.vo.ServiceFile;
import com.ilgusi.service.model.vo.ServiceReview;

@Repository
public class ServiceDao {
	@Autowired
	private SqlSessionTemplate session;

	public Join selectOneMember(String id) {
		return session.selectOne("service.selectOneMember",id);
	}

	public int insertService(Join join) {
		return session.insert("service.insertService",join);
	}

	public int updateFreelancer(Member m) {
		return session.update("service.updateFreelancer",m);
	}

	public Member selectOneMember(int MNO) {
		return session.selectOne("service.freelancerOneMember",MNO);
	}
	public List<ServiceReview> selectReviewList(String id) {
		List<ServiceReview> j = session.selectList("service.selectListReview",id);
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
		return session.insert("service.insertServiceFile",serviceFile);
	}


	

	
}

