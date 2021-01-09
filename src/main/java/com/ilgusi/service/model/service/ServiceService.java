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

		public List<ServiceReview> selectReviewList(String mId) {
			List<ServiceReview> j = dao.selectReviewList(mId);
			return j;
		}

		public List<com.ilgusi.service.model.vo.Service> serviceList(String mId) {
			List<com.ilgusi.service.model.vo.Service> j = dao.serviceList(mId); 
			return j;
		}
}
