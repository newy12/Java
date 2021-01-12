package com.ilgusi.question.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ilgusi.member.model.vo.Member;
import com.ilgusi.question.model.service.QuestionService;
import com.ilgusi.question.model.vo.Question;
import com.ilgusi.service.model.vo.ServiceFile;

import common.FileNameOverlap;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService service;

	// (도현) qna 페이지 접속
	@RequestMapping("qna.do")
	public String qna(Model model) {

		List<Question> list = service.selectQuestionList();
		System.out.println(list);
		model.addAttribute("questionList", list);
		return "/question/qna";
	}

	// (도현) qna 상세보기 페이지 접속
	@RequestMapping("questionView.do")
	public String questionView(int qNo, Model model) {
		Question q = service.selectOneQuestion(qNo);
		if (q == null) {
			model.addAttribute("msg", "글이 존재하지 않습니다.");
			model.addAttribute("loc", "qna.do");
			return "/common/msg";
		}
		model.addAttribute("question", q);
		return "/question/questionView";
	}

	// (도현) question 작성 페이지 접속
	@RequestMapping("questionFrm.do")
	public String questionFrm() {
		return "/question/registerQuestionFrm";
	}

	// (도현) question 작성 기능
	@RequestMapping("registerQuestion.do")
	public String registerQuestion(HttpServletRequest req, MultipartFile file, Model model) {

		Question q = new Question();

		String qTitle = req.getParameter("qTitle");
		String qContent = req.getParameter("qContent");
		if (qTitle == null || qTitle.equals("") == true || qContent == null || qContent.equals("") == true) {
			model.addAttribute("msg", "등록 실패!");
			model.addAttribute("loc", "/qna.do");
			return "/common/msg";
		}
		q.setQTitle(qTitle);
		q.setQContent(qContent);

		String root = req.getSession().getServletContext().getRealPath("upload");
		String path = root + "/question/";
		String filename = file.getOriginalFilename();
		String filepath = new FileNameOverlap().rename(path, filename);
		System.out.println("filename : " + filename);
		System.out.println("filepath : " + filepath);
		System.out.println(path);
		try {
			byte[] bytes = file.getBytes();
			File upFile = new File(path + filepath);
			FileOutputStream fos = new FileOutputStream(upFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(bytes);
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		q.setFilePath(filepath);
		HttpSession sess = req.getSession();
		int result = 0;
		if (sess.getAttribute("loginMember") != null) {
			q.setMNo(((Member) sess.getAttribute("loginMember")).getMNo());
			result = service.insertQuestion(q);
		}

		if (result > 0) {
			model.addAttribute("msg", "등록 성공!");
		} else {
			model.addAttribute("msg", "등록 실패!");
		}
		model.addAttribute("loc", "/qna.do");
		return "/common/msg";
	}
}
