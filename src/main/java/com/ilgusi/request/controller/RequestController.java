package com.ilgusi.request.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ilgusi.request.model.service.RequestService;
import com.ilgusi.request.model.vo.Request;
import com.ilgusi.request.model.vo.RequestPageData;

import common.FileNameOverlap;

@Controller
public class RequestController {
	@Autowired
	private RequestService service;
	
	//(문정)의뢰게시판 리스트
	@RequestMapping("/requestList.do")
	public String requestList(int reqPage, Model model) {
		RequestPageData rpd = service.selectRequestList(reqPage);
		model.addAttribute("list", rpd.getList());
		model.addAttribute("pageNavi", rpd.getPageNavi());
		model.addAttribute("totalCount", rpd.getTotalCount());
		return "request/requestList";
	}
	
	//(문정)작성폼으로 이동
	@RequestMapping("/requestWriteFrm.do")
	public String requestWriteFrm() {
		return "request/requestWriteFrm";
	}
	
	//(문정) 의뢰 insert
	@RequestMapping("/requestInsert.do")
	public String requestInsert(Model model, MultipartHttpServletRequest mtfRequest, HttpServletRequest request) {
		
		String root = request.getSession().getServletContext().getRealPath("/");
	    String path = root+"upload/request/";
	    System.out.println("경로는 : "+path);
	    
	    //파일 이름 처리
	    String filename = "";
	    String filepath = "";
	    MultipartFile file = mtfRequest.getFile("filename");
	    if(file.isEmpty()) {
	    	filename = null;
	    	filepath = null;
	    }else {
	    	filename = file.getOriginalFilename();
		    filepath = new FileNameOverlap().rename(path, filename);
		    
		    byte[] bytes;
			try {
				bytes = file.getBytes();
				File upFile = new File(path+filepath);
			    FileOutputStream fos = new FileOutputStream(upFile);
			    BufferedOutputStream bos = new BufferedOutputStream(fos);
			    bos.write(bytes);
			    bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

		Request req = new Request();
		req.setFilename(filename);
		req.setFilepath(filepath);
		req.setMId(request.getParameter("mId"));
		req.setReqContent(request.getParameter("reqContent"));
		req.setReqTitle(request.getParameter("reqTitle"));
		int result = service.requestInsert(req);
      if(result>0) {
	         model.addAttribute("msg","등록 성공");
	      }else {
	         model.addAttribute("msg","실패");
	      }
	      model.addAttribute("loc","/");
	      return "common/msg";
	}
	
	//(문정) 의뢰게시판 상세보기
	@RequestMapping("/requestDetail.do")
	public String requestDetail(int reqNo, Model model) {
		System.out.println("들어완싸"+reqNo);
		Request req = service.selectOneRequest(reqNo);
		model.addAttribute("req", req);
		return "request/requestDetail";
	}
}
