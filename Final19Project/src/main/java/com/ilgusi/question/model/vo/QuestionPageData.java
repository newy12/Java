package com.ilgusi.question.model.vo;

import java.util.ArrayList;

import lombok.Data;
@Data
public class QuestionPageData {
	private ArrayList<Question> list;
	private String pageNavi;
}
