package com.ilgusi.category.controller;

import java.text.DecimalFormat;
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
import com.ilgusi.category.model.vo.CategoryRank;
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
		System.out.println("에이잭스");
		Gson gson = new Gson();
		List<Category> list = service.selectCategoryList();
		String json = gson.toJson(list);
		return json;
	}
	
	// (도현) 카테고리별 프리랜서랭킹 ajax로 불러오는거
		@RequestMapping(value = "/rankAjax.do", produces = "text/json; charset=utf-8")
		@ResponseBody
		// cateNum : 랭킹 조회할 메인 카테고리의 번호
		public String rankAjax(int cateNum) {
			System.out.println("에이잭스 랭킹");
			DecimalFormat formatter = new DecimalFormat("###,###");
			Gson gson = new Gson();
			List<CategoryRank> list = service.selectCategoryRankList(cateNum);
			for(int i =0;i<list.size();i++) {
				list.get(i).setSumPrice(formatter.format(Integer.parseInt(list.get(i).getSumPrice())));
			}
			String json = gson.toJson(list);
			return json;
		}
	
	//테스트용 인덱스 페이지 접속
	@RequestMapping(value = "/indexTest.do")
	public String index() {
		return "/indexTest";
	}
	
}
