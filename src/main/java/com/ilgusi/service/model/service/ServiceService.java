package com.ilgusi.service.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.dao.ServiceDao;
import com.ilgusi.service.model.vo.Join;

@Service
public class ServiceService {
	@Autowired
	private ServiceDao dao;

		public Join selectOneMember(String id) {
		return dao.selectOneMember(id);
	}

		public int insertService(Join join) {	
			return dao.insertService(join);
		}

	
}
