package com.ilgusi.request.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ilgusi.request.model.service.RequestService;
import com.ilgusi.request.model.vo.Request;
import com.ilgusi.request.model.vo.RequestPageData;
import com.ilgusi.service.model.service.ServiceService;
import com.ilgusi.service.model.vo.Service;

import common.FileNameOverlap;

@Controller
public class RequestController {
	@Autowired
	private RequestService service;

	
	//(문정)의뢰게시판 리스트
	@RequestMapping("/requestList.do")
	public String requestList(int reqPage, String order, String subject, String keyword, Model model) {
		if(keyword == "") {
			keyword = "null";
		}
		RequestPageData rpd = service.selectRequestList(reqPage, order, subject, keyword, null);
		model.addAttribute("order",order);
		model.addAttribute("subject", subject);
		model.addAttribute("keyword",keyword);
		model.addAttribute("list", rpd.getList());
		model.addAttribute("pageNavi", rpd.getPageNavi());
		model.addAttribute("totalCount", rpd.getTotalCount());
		return "request/requestList";
	}
	
	//(문정)작성폼으로 이동
	@RequestMapping("/requestWriteFrm.do")
	public String requestWriteFrm( String position, Model model) {
		model.addAttribute("position", position);
		return "request/requestWriteFrm";
	}
	
	//(문정) 의뢰 insert
	@RequestMapping("/requestInsert.do")
	public String requestInsert(Model model, MultipartHttpServletRequest mtfRequest, HttpServletRequest request) {
		String position = request.getParameter("position");
		String root = request.getSession().getServletContext().getRealPath("/");
	    String path = root+"upload/request/";
	    System.out.println("포지션은 :"+position+"/경로는 : "+path);
	    
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
	         model.addAttribute("msg","의뢰를 등록하였습니다.");
	      }else {
	         model.addAttribute("msg","실패");
	      }
      	if(position.equals("mypage")) {
      		model.addAttribute("loc","/userRequestHistory.do?reqPage=1");
      	}else {
      		model.addAttribute("loc","/requestList.do?reqPage=1&order=new&subject=all&keyword=");
      	}
	      return "common/msg";
	}
	
	//(문정) 의뢰게시판 상세보기
	@RequestMapping("/requestDetail.do")
	public String requestDetail(int reqNo, String position, Model model) {
		Request req = service.selectOneRequest(reqNo);
		model.addAttribute("req", req);
		model.addAttribute("position",position);
		return "request/requestDetail";
	}
	
	//(문정) 의뢰글 frm
	@RequestMapping("/requestUpdateFrm.do")
	public String requestUpdateFrm(int reqNo, String position, Model model) {
		Request req = service.selectOneRequest(reqNo);
		model.addAttribute("req", req);
		model.addAttribute("position",position);
		return "request/requestUpdateFrm";
	}
	
	//(문정) 의뢰글 수정하기
	@RequestMapping("/requestUpdate.do")
	public String requestUpdate(Request req, Model model, MultipartHttpServletRequest mtfRequest, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("/");
		String path = root + "upload/request/";
		
		String filename="";
		String filepath ="";
		MultipartFile file = mtfRequest.getFile("file_name");
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
		
		req.setFilename(filename);
		req.setFilepath(filepath);
		String position = request.getParameter("position");
		int result = service.requestUpdate(req);
		if(result>0) {
			model.addAttribute("msg", "수정되었습니다.");
		}
      	if(position.equals("mypage")) {
      		model.addAttribute("loc","/userRequestHistory.do?reqPage=1");
      	}else {
      		model.addAttribute("loc","/requestList.do?reqPage=1&order=new&subject=all&keyword=");
      	}
		return "common/msg";
	}
	
	//(문정) 의뢰 삭제
	@RequestMapping("/requestDeleteOne.do")
	public String requestDeleteOne(int reqNo, String filepath, String position, Model model, HttpServletRequest request, HttpServletResponse response) {
		int result = service.requestDeleteOne(reqNo);
		//파일 삭제
		if(filepath != null) {
			String root = request.getSession().getServletContext().getRealPath("/");
			String path = root + "upload/request/";
			File delFile = new File(path+filepath);
			boolean bool = delFile.delete();
		}
		if(result>0) {
			model.addAttribute("msg", "삭제되었습니다.");
		}
      	if(position.equals("mypage")) {
      		model.addAttribute("loc","/userRequestHistory.do?reqPage=1");
      	}else {
      		model.addAttribute("loc","/requestList.do?reqPage=1&order=new&subject=all&keyword=");
      	}
		return "common/msg";
	}
	
	//(문정) 의뢰자에게 연락하기 전 서비스 고르는 팝업창 띄움
	@RequestMapping("/requestSendPopup.do")
	public String requestSendPopup(String userId, String freeId, Model model) {
		ArrayList<Service> list = service.serviceList(freeId);
		model.addAttribute("userId", userId);
		model.addAttribute("list",list);
		return "request/requestSendPopup";
	}
	
	//(문정) 파일 다운로드
	@RequestMapping("/requestFileDownload.do")
	public void requestFileDownload(String filepath, HttpServletRequest req, HttpServletResponse res) {
		String root = req.getSession().getServletContext().getRealPath("/");
	    String path = root+"upload/request/";
		System.out.println(path+filepath);
	    try {
			FileInputStream fis = new FileInputStream(path+filepath);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ServletOutputStream sos = res.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(sos);
			//윈도우 익스플로어일때 대비
			String resFilename = "";
			boolean bool = req.getHeader("user-agent").indexOf("MSIE") != -1 
					|| req.getHeader("user-agent").indexOf("Trident") != -1;
			if(bool) {   //사용자의 브라우저가 IE인 경우
				resFilename = URLEncoder.encode(filepath, "UTF-8");
				resFilename = resFilename.replaceAll("\\\\", "%20");
			}else {
				resFilename = new String(filepath.getBytes("UTF-8"), "ISO-8859-1");
			}
			//파일 다운로드를 위한 HTTP헤더 설정
			res.setContentType("application/octer-stream");
			res.setHeader("Content-Disposition", "attachment;filename="+resFilename+";filename*= UTF-8");     //로드 되는 파일 이름
			System.out.println(resFilename);
			//파일 전송
			int read = -1;
			while((read=bis.read()) != -1) {
				bos.write(read);
			}
			bos.close();
			bis.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
