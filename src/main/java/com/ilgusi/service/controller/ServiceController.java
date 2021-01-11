package com.ilgusi.service.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ilgusi.category.model.vo.Category;
import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.service.ServiceService;
import com.ilgusi.service.model.vo.Join;
import com.ilgusi.service.model.vo.Service;
import com.ilgusi.service.model.vo.ServiceFile;
import com.ilgusi.service.model.vo.ServiceReview;

import common.FileNameOverlap;

@Controller
public class ServiceController {
	@Autowired
	private ServiceService service;

	@RequestMapping("/introduceFrm.do")
	public String introduceFrm(String mId,int reqPage, Model model) {
	System.out.println("ㅇㅇ"+reqPage);
		Join j = service.selectOneMember(mId);
		Join join = service.selectReviewList(mId, reqPage);
		j.setServiceList(service.serviceList(mId));
		j.setReviewList(join.getReviewList());
		model.addAttribute("pageNavi",join.getPageNavi());
		model.addAttribute("j", j);
		return "freelancer/introduce";
	}

	@RequestMapping("/serviceJoinFrm.do")
	public String serviceJoinFrm() {
		return "freelancer/servicejoin";
	}

	@RequestMapping("/serviceJoin.do")
	public String serviceJoin(Join join, Model model, MultipartFile[] ssImg, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("/");
		String path = root + "/upload/service/";
		System.out.println("경로는 : " + path);
		System.out.println("값러ㅏㅇㄴ러ㅏ : " + request.getParameter("sContent"));
		ArrayList<ServiceFile> fileList = new ArrayList<ServiceFile>();

		for (MultipartFile file : ssImg) { // 파일이 여러개라 반복문으로 처리
			String filename = file.getOriginalFilename();
			String filepath = new FileNameOverlap().rename(path, filename);
			System.out.println("filename : " + filename);
			System.out.println("filepath : " + filepath);

			try {
				byte[] bytes = file.getBytes();
				File upFile = new File(path + filepath);
				FileOutputStream fos = new FileOutputStream(upFile);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				bos.write(bytes);
				bos.close();

				ServiceFile f = new ServiceFile();
				f.setFilename(filename);
				f.setFilepath(filepath);
				fileList.add(f); // 데이터베이스 처리를 위해 객체화 해서 list에 추가
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		join.setFileList(fileList);
		join.setSImg(fileList.get(0).getFilepath());
		int result = service.insertService(join);
		if (result > 0) {
			model.addAttribute("msg", "서비스등록완료");
		} else {
			model.addAttribute("msg", "서비스등록실패");
		}
		model.addAttribute("loc", "/");
		return "common/msg";
	}

	// 프리랜서 마이페이지 이동
	@RequestMapping("/freelancerMypage.do")
	public String selectfreelancerMypage(int MNo, Model model) {
		Member m = service.selectOneMember(MNo);
		System.out.println("MNo>>>>>" + m.getMNo() + MNo);
		model.addAttribute("m", m);
		System.out.println("소개값>>>>>>>>>" + m.getIntroduce());
		return "freelancer/freelancerMypage";
	}

	// 프리랜서 마이페이지 -> 서비스 리스트 이동
	@RequestMapping("/freelancerServiceList.do")
	public String freelancerServiceList() {
		return "freelancer/freelancerServiceList";
	}

	// 프리랜서 마이페이지 -> 거래내역
	@RequestMapping("/freelancerTradeHistory.do")
	public String freelancerTradeHistory() {
		return "freelancer/freelancerTradeHistory";
	}

	// 프리랜서 마이페이지 정보수정(소개글,연락가능시간,브랜드명 추가)
	@RequestMapping("/updateFreelancer.do")
	public String updateFreelancer(Member m, Model model) {
		int result = service.updateFreelancer(m);
		if (result > 0) {
			model.addAttribute("msg", "수정되었습니다.");
		} else {
			model.addAttribute("msg", "수정실패하였습니다.");
		}
		model.addAttribute("loc", "/");
		return "common/msg";
	}

	@RequestMapping("/serviceList.do")
	public String serviceList() {
		return "service/serviceList";
	}

	@RequestMapping("/serviceView.do")
	public String serviceView() {
		return "service/serviceView";
	}
	
	/*
	 * @RequestMapping("/serviceListTest.do") public String serviceListTest(int cNo,
	 * Model model) { ArrayList<Service> list = service.selectServiceList();
	 * model.addAttribute("list", list); return "service/serviceList"; }
	 */

	/*
	 * //서비스 리스트 메뉴 불러오기
	 * 
	 * @RequestMapping("/serviceListTest.do") public String serviceListTest(int cNO)
	 * { ArrayList<Category> list = service.selectCategory(cNO); return ""; }
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
