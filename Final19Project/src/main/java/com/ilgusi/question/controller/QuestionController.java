package com.ilgusi.question.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
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
	public String qna(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "list_num", defaultValue = "5") int listNum,
			@RequestParam(value = "qna_keyword", required = false) String keyword,
			@RequestParam(value = "qna_type", defaultValue = "1") int type) {

		// 검색기능 type--> 1: 제목 , 2:작성자 아이디
		// 네비 기능
		int listPerPage = listNum;
		int maxListCount;
		if (keyword == null)
			maxListCount = service.selectQuestionCount();
		else
			maxListCount = service.selectQuestionCount(type, keyword);

		List<Question> list = service.selectQuestionList(maxListCount - ((page) * listPerPage) + 1,
				maxListCount - ((page - 1) * listPerPage), type, keyword);
		//날짜형식 변경
		for(int i =0;i<list.size();i++) {
			list.get(i).setWriteDate(list.get(i).getWriteDate().replace("/", "-"));
			if(list.get(i).getAnswerDate() != null && list.get(i).getAnswerDate().equals(""))
			list.get(i).setAnswerDate(list.get(i).getAnswerDate().replace("/", "-"));
		}
		int maxPrintPageCount = 5;
		int maxPageCount = service.selectMaxPageCount(listPerPage, maxListCount);
		int begin = maxPrintPageCount * (page / (maxPrintPageCount+1)) + 1; // 네비 시작
		int end = (begin + 4) < maxPageCount ? begin + 4 : maxPageCount; // 네비 끝
		model.addAttribute("questionList", list);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("maxPageCount", maxPageCount);

		return "/question/qna";
	}

	// (도현) qna 상세보기 페이지 접속
	@RequestMapping("questionView.do")
	public String questionView(int qNo, Model model) {
		Question q = service.selectOneQuestion(qNo);
		System.out.println(q.getAnswerContent());
		if (q == null) {
			model.addAttribute("msg", "글이 존재하지 않습니다.");
			model.addAttribute("loc", "qna.do");
			return "/common/msg";
		}
		model.addAttribute("question", q);
		return "/question/questionView";
	}

	// (도현) qna 상세보기 페이지 ajax로
	@RequestMapping(value="questionViewAjax.do", produces = "text/json; charset=utf-8")
	@ResponseBody
	public String questionViewAjax(int qNo, Model model) {
		Question q = service.selectOneQuestion(qNo);
		Gson gson = new Gson();
		String json = gson.toJson(q);
		return json;
	}

	// (도현) question 작성 페이지 접속
	@RequestMapping("questionFrm.do")
	public String questionFrm(Model model, @RequestParam(value = "q_No", defaultValue = "-1") int qNo) {

		if (qNo != -1) {
			Question q = service.selectOneQuestion(qNo);
			if (q == null) {
				model.addAttribute("msg", "잘못된 접근!");
				model.addAttribute("loc", "/");
				return "/common/msg";
			}
			model.addAttribute("question", q);
		}
		return "/question/registerQuestionFrm";
	}

	//	(도현) 파일다운 기능
	@GetMapping("questionDown.do")
	public void download(HttpServletRequest req,HttpServletResponse resp,String fileName) {
        // saveFileName을 만든다.
        String root = req.getSession().getServletContext().getRealPath("upload");
        String path = root + "/question/";
        String saveFileName = path+fileName;
 
        File file = new File(saveFileName);
        long fileLength = file.length();
 
        // reponse의 Header에 세팅
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        resp.setHeader("Content-Transfer-Encoding", "binary"); 
        resp.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
        resp.setHeader("Content-Length", "" + fileLength);
        resp.setHeader("Pragma", "no-cache;");
        resp.setHeader("Expires", "-1;");
        
        // saveFileName을 파라미터로 넣어 inputStream 객체를 만들고 
        // response에서 파일을 내보낼 OutputStream을 가져옴  
        try (FileInputStream fis = new FileInputStream(saveFileName); OutputStream out = resp.getOutputStream();) {
            int readCount = 0;
            // 파일 읽을 만큼 크기의 buffer를 생성
            byte[] buffer = new byte[3096];
            while ((readCount = fis.read(buffer)) != -1) {
                out.write(buffer, 0, readCount);
            }
        } catch (Exception ex) {
            throw new RuntimeException("file Load Error");
        }
	}
	// (도현) question 삭제 기능
	@RequestMapping("deleteQuestion.do")
	public String deleteQuestion(Model model,int qNo,String loc) {
		int result = service.deleteQuestion(qNo);
		
		if(result > 0 ){
			model.addAttribute("msg", "삭제성공");
		}else {
			model.addAttribute("msg", "삭제실패");
		}
		model.addAttribute("loc", loc);
		return "/common/msg";
		
	}
	// (도현) question 작성 기능
	@RequestMapping("registerQuestion.do")
	public String registerQuestion(HttpServletRequest req, MultipartFile file, Model model,
			@RequestParam(value = "answer_No", defaultValue = "-1") int answerNo,
			@RequestParam(value = "q_No", defaultValue = "-1") int qNo,
			@RequestParam(value="loc",required = false) String loc,
			@RequestParam(value="isPrivacy",required = false) String isPrivacy) {

		Question q = new Question();
		String qTitle = req.getParameter("qTitle");
		String qContent = req.getParameter("qContent");
		System.out.println(isPrivacy +"비밀여부");
		if(isPrivacy != null && isPrivacy.equals("on"))
			q.setSecretStatus(1);
		if (answerNo == -1
				&& (qTitle == null || qTitle.equals("") == true || qContent == null || qContent.equals("") == true)) {
			model.addAttribute("msg", "등록 실패!");
			if(loc == null || loc.equals(""))
				model.addAttribute("loc", "/qna.do");
			else
				model.addAttribute("loc", loc);
			return "/common/msg";
		}
		if (qNo != -1)
			q.setQNo(qNo);
		else
			q.setQNo(answerNo); // 관리자가 답변하는경우 -1이 아닌 답변할 질문 번호값이 들어감.

		q.setQTitle(qTitle);
		q.setQContent(qContent);

		if (file != null) {

			String root = req.getSession().getServletContext().getRealPath("upload");
			String path = root + "/question/";
			String filename = file.getOriginalFilename();
			if (!filename.equals("")) {
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
			}
		}
		HttpSession sess = req.getSession();

		int result = 0;
		if (sess.getAttribute("loginMember") != null) {
			q.setMNo(((Member) sess.getAttribute("loginMember")).getMNo());
			// 만약 qNo가 -1이 아니라면 관리자가 답변하는것.
			if (answerNo != -1)
				result = service.updateAnswer(q);
			else if (qNo != -1)
				result = service.updateQuestion(q);
			else
				result = service.insertQuestion(q);
		}

		if (result > 0) {
			model.addAttribute("msg", "등록 성공!");
		} else {
			model.addAttribute("msg", "등록 실패!");
		}
		if(loc == null || loc.equals(""))
			model.addAttribute("loc", "/qna.do");
		else
			model.addAttribute("loc", loc);
		return "/common/msg";
	}
}
