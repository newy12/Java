package com.ilgusi.service.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.ilgusi.service.model.vo.ReviewPageData;
import com.ilgusi.service.model.vo.Service;
import com.ilgusi.service.model.vo.ServiceFile;
import com.ilgusi.service.model.vo.ServicePageData;
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
	//(영재) 리뷰갯수 구하기
	@RequestMapping("/reviewListSize.do")
	public void reviewListSize(String mId,Model model) {
		List<ServiceReview> list = service.reviewListSize(mId);
		System.out.println("mid>>>>>"+mId);
		model.addAttribute("list",list);
		System.out.println("list>>>>>>평점"+list);
	}
	  //(영재) 평점 평균 구하기
	  
	  @RequestMapping("/sRateAVG.do") 
	  public void sRateAVG(String mId,Model model) {
		  System.out.println("midRate>>>>>>>>전>"+mId);
	  List<Service> list = service.sRateAVG(mId);
	  System.out.println("midRate>>>>>>>>>"+mId);
	  model.addAttribute("list",list);
	  System.out.println("list>>>>>>평균점수"+list);
	  }
	  //(영재) 
	  
	 
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
	public String freelancerServiceList(String mId,Model model) {
		Join j = new Join();
		System.out.println("mid : "+mId);
		j.setServiceList(service.serviceList(mId));
		model.addAttribute("j",j);
		System.out.println("test"+j.getServiceList().size());
		return "freelancer/freelancerServiceList";
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


	//(문정)사용자 마이페이지 - 거래후기 쓰기
	@RequestMapping("/serviceReviewWrite.do")
	public String serviceReviewWrite( int tNo, int sNo, String mId, String sImg, String sContent, Model model) {
		model.addAttribute("tNo", tNo);
		model.addAttribute("sNo", sNo);
		model.addAttribute("mId", mId);
		model.addAttribute("sImg", sImg);
		model.addAttribute("sContent", sContent);
		return "service/serviceReviewWrite";
	}
	
	//(문정) 마이페이지 - 서비스 후기 등록
	@RequestMapping("/serviceReviewInsert.do")
	public String serviceReviewInsert( ServiceReview data , Model model) {
		int result = service.serviceReviewInsert(data);
		if(result>0) {
			result = service.serviceReviewSuccess(data.getTNo());
			if(result>0) {
				model.addAttribute("msg","리뷰를 등록하였습니다.");
			}
		}
		return "/service/reviewDone";
	}

	//(문정) 마이페이지 - 거래 후기 작성한거 확인
	@RequestMapping("/serviceReviewView.do")
	public String serviceReviewView(ServiceReview data , Model model) {
		ServiceReview sr = service.serviceReviewView(data);
		model.addAttribute("review",sr);
		return "/service/serviceReviewUpdate";
	}
	
	//(문정) 서비스 리뷰 수정
	@RequestMapping("/serviceReviewUpdate.do")
	public String serviceReviewUpdate(ServiceReview review, Model model) {
		int result = service.serviceReviewUpdate(review);
		if(result>0) {
			model.addAttribute("msg","리뷰를 수정하였습니다.");
		}
		return "/service/reviewDone";
	}
	
	//(문정) 서비스 리뷰 삭제
	@RequestMapping("/serviewReviewDelete.do")
	public String serviewReviewDelete(int rNo, int tNo, Model model) {
		int result = service.serviceReviewDelete(rNo);
		if(result>0) {
			result = service.serviceTradeStatusUpdate(tNo);
			if(result>0) {
				model.addAttribute("msg", "리뷰를 삭제했습니다.");
			}
		}
		return "/service/reviewDone";
	}
	
	//(다솜)serviceList 
	@RequestMapping("/serviceList.do")
	public String serviceList (int cNo, int reqPage, String order, Model model) { 
		
		int numPerPage = 12;
		int end = reqPage * numPerPage;
		int start = end-numPerPage+1;
		
		Service s = new Service();
		System.out.println("cNo : " + cNo);
		int maincateNum = 0;
		int subNo = 0;
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		if(cNo%10 == 0 ) {
			maincateNum = cNo;
			s.setSubCategory(subNo);
			map.put("sub", 0);
			
		}else {
			maincateNum = (cNo/10)*10;
			subNo = cNo;
			map.put("sub", subNo);
		}
		
		map.put("main", maincateNum);
		map.put("start", start);
		map.put("end", end);
		map.put("reqPage", reqPage);
		map.put("cNo",cNo);
		/* map.put("order", order); */
		
		s.setMainCategory(maincateNum);
		s.setSubCategory(subNo);
		System.out.println("메인카테고리 : " + maincateNum);
		System.out.println("서브카테고리 : " + subNo);
		
		//카테고리 리스트 불러오기
		ArrayList<Category> catList = service.categoryList(maincateNum);
		System.out.println("카테고리 리스트 사이즈 : " + catList.size());
		System.out.println("catList(1)값 : " + catList.get(1));
		
		//서비스 리스트 불러오기			
		
		
		//서비스 리스트 불러오기+페이징 
		ServicePageData spd = service.servicePageList(map);
		ArrayList<Service> serList = spd.getList();
		
		DecimalFormat formatter = new DecimalFormat("###,###");
		
		for (int i = 0; i<serList.size(); i++) {
			serList.get(i).setSPriceTxt(formatter.format(serList.get(i).getSPrice()));
		}
		
		System.out.println("천단위 콤마 확인 : " + serList.get(0).getSPrice());
		System.out.println("천단위 콤마 확인 : " + serList.get(0).getSPriceTxt());
		
		
		//맵 확인용 ArrayList 
		ArrayList<HashMap<String, Integer>> mapList = new ArrayList<HashMap<String,Integer>>();
		mapList.add(map);
		
		if(mapList.size() != 0) {
			System.out.println("mapList(0) : " + mapList.get(0));
		}
		
		
		if(cNo%10 == 0) {
			for(int i = 0; i < serList.size(); i++) {
				serList.get(i).setSubCategory(0);
			}
		}

		if(serList.size() > 0 ) {
			System.out.println("serList 사이즈 : " + serList.size());
			System.out.println("serList.get(0) : "+ serList.get(0));
			model.addAttribute("serviceList", spd.getList());
		}else {
			System.out.println("serList 사이즈 : "+ serList.size());
			model.addAttribute("noServiceList", "noServiceList");
		}
		
		if(cNo%10 == 0) {
			model.addAttribute("c_no",serList.get(0).getMainCategory());
		}else {
			model.addAttribute("c_no",serList.get(0).getSubCategory());
		}
		
		 
		
		
		ArrayList<String> brandName = service.brandList(s);
		switch(maincateNum) {
			case 10: model.addAttribute("mainCate", "디자인");
				break;
			case 20: model.addAttribute("mainCate", "ITㆍ프로그래밍");
				break;
			case 30: model.addAttribute("mainCate", "영상ㆍ사진ㆍ음향");
				break;
			case 40: model.addAttribute("mainCate", "교육");
				break;
			case 50: model.addAttribute("mainCate", "문서ㆍ글쓰기");
				break;
			case 60: model.addAttribute("mainCate", "비즈니스컨설팅");
				break;
			case 70: model.addAttribute("mainCate", "주문제작");
				break;
		}
	
		model.addAttribute("catList",catList);
		model.addAttribute("brandName", brandName);
		model.addAttribute("pageNavi", spd.getPageNavi());
		
		return "/service/serviceList";
	}
	
	//(다솜) serviceView 페이지 이동 
		@RequestMapping("/serviceView.do")
		public String serviceView(int sNo, Model model,int reqPage) {
			System.out.println("서비스 컨트롤러-serviceView");
			System.out.println("서비스 상세보기 sNo: " + sNo);
			
			//해당 서비스 정보 불러오기
			Service s = service.selectServiceView(sNo);
			
			DecimalFormat formatter = new DecimalFormat("###,###");
			s.setSPriceTxt(formatter.format(s.getSPrice()));
	
			System.out.println("메인카테고리 이름 : " + s.getMainCategoryName());
			System.out.println("서브카테고리 이름 : " + s.getSubCategoryName());
			
			if( s != null) {
				model.addAttribute("s", s);
			}
			
			//브랜드 정보 불러오기 
			String memberId = s.getMId();
			
			Member m = service.selectMemberName(memberId);
			model.addAttribute("m", m);
			
			//해당 유저가 등록한 다른서비스 불러오기 
			ArrayList<Service> sList = service.userService(memberId);
			model.addAttribute("sList", sList);
			
			//리뷰 리스트 불러오기 + 페이징
			ReviewPageData rpd = service.selectReviewList(sNo,reqPage);
			System.out.println(rpd.getPageNavi());
			model.addAttribute("reviewList",rpd.getList());
			model.addAttribute("pageNavi", rpd.getPageNavi());
			
			return "/service/serviceView";
		}
		
	
}
