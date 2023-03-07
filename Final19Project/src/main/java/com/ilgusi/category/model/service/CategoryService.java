package com.ilgusi.category.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.category.model.dao.CategoryDao;
import com.ilgusi.category.model.vo.Category;
import com.ilgusi.category.model.vo.CategoryRank;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Join;

import common.SHA256Util;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao dao;

	public List<Category> selectCategoryList() {
		return dao.selectCategoryList();
	}

	public List<CategoryRank> selectCategoryRankList(int cateNum) {
		return dao.selectCategoryRankList(cateNum);
	}

}