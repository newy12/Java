package com.ilgusi.category.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ilgusi.category.model.service.CategoryService;
import com.ilgusi.category.model.vo.Category;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Join;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService service;

	public CategoryController() {
		super();
		System.out.println("Categoryt컨트롤러 생성 완료");
	}

	// (도현) 카테고리 ajax로 불러오는거
	@RequestMapping(value = "/categoryAjax.do", produces = "text/json; charset=utf-8")
	@ResponseBody
	public String categoryAjax(HttpServletResponse resp) {
		System.out.println("아작스");
		
		Gson gson = new Gson();
		
		List<Category> list = service.selectCategoryList();
		
		String json = gson.toJson(list);
		
		return json;
	}
	
}
