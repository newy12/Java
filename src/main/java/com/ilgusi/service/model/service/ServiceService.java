package com.ilgusi.service.model.service;

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

		public Join selectOneMember(String id) {
		return dao.selectOneMember(id);
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

		public List<ServiceReview> selectReviewList(String id) {
			List<ServiceReview> j = dao.selectReviewList(id);
			return j;
		}



	
}
