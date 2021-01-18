package com.ilgusi.category.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ilgusi.category.model.vo.Category;
import com.ilgusi.category.model.vo.CategoryRank;
import com.ilgusi.member.model.vo.Member;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSessionTemplate session;

	public List<Category> selectCategoryList() {
		return session.selectList("category.selectCategoryList");
	}

	public List<CategoryRank> selectCategoryRankList(int cateNum) {
		return session.selectList("category.selectCategoryRankList",cateNum);
	}

}
