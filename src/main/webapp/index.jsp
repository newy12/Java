<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

	<meta charset="UTF-8">
	<title>19시 :: 일 구하고 시퍼</title>
	<!-- favicon -->
	<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
	<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
	
	<link rel="stylesheet" href="css/index/indexStyle.css">

</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="test-section">
		<h1>여러분 화이팅~~!!~</h1>
		<h2>해피뉴이어~~</h2>
		<hr>
		<h3>본인 페이지 테스트</h3>
		소현
		<a href="/chatList.do?mGrade=${loginMember.MGrade}&mId=${loginMember.MId}"
		onClick="window.open(this.href, '', 'width=530, height=630, left=1000,location=no,scrollbars=no,location=no, resizable=no'); return false;">채팅하기</a>
		<a href="/manageMember.do?reqPage=1&grade=all&keyword=&order=new">관리자-회원관리+페이징+검색+정렬</a>
		<a href="/manageService.do?reqPage=1&status=waiting&keyword1=&keyword2=&order=old">관리자-서비스관리+페이징+검색+정렬</a>
		<br> 영재
		<a href="/introduceFrm.do?mId=${loginMember.MId}&reqPage=1">프리랜서소개글</a>
		<a href="/serviceJoinFrm.do?MId=${loginMember.MId}">서비스등록</a>
		<a href="/freelancerMypage.do?MNo=${loginMember.MNo}">프리랜서 마이페이지</a>
		<br> 문정
		<a href="/requestList.do?reqPage=1&order=new&subject=all&keyword=">의뢰게시판 리스트 </a> /
		<a href="/userMypage.do">사용자 마이페이지</a>
		<br> 다솜
		<a href="/noticeList.do?reqPage=1&keyword=">공지사항 게시판</a> /
		<a href="/serviceList.do?sNo=10&reqPage=1&order=new&keyword=">서비스리스트</a> /
		<a href="/serviceView.do?sNo=44">서비스 상세보기</a> <br>
		<br> 도현
		<a href="/indexTest.jsp">인덱스 페이지 테스트</a>
	</div>
	<script>
		$(document).ready(function () {
		  // 배너 슬라이더 부분
		  $('.banner-slider').slick({
			infinite: true,
			slidesToShow: 1,
			slidesToScroll: 1,
			draggable: false,
			autoplay: true,
			autoplaySpeed: 3000,
			prevArrow: $("#arrow_prev"),
			nextArrow: $("#arrow_next")
		  });
		  // 슬라이더 부분
		  $.each($('.famous-category-list'), function (index, valueOfElement) {
			$(valueOfElement).slick({
			  infinite: true,
			  slidesToShow: 5,
			  slidesToScroll: 1,
			  draggable: false,
			  prevArrow: $("#arrow" + (index + 1) + "_prev"),
			  nextArrow: $("#arrow" + (index + 1) + "_next")
			});
		  });
		  // 랭크 ajax 부분
		  $.ajax({
			type: "post",
			url: "/rankAjax.do",
			data: {
			  cateNum: 10
			},
			dataType: "json",
			success: function (response) {
			  console.log(response);
			  $(".rank-category-title:eq(0)").text("테스트 카테고리");
			  $(".sumPrice:eq(0)").empty
			  console.log(response["sump"]);
			  $(".sumPrice:eq(0)").text(response[0].sumPrice + "원");
			  $(".free-id:eq(0)").append("<a href='/introduceFrm.do?mId=" + response[0].mId + "&reqPage=1'>" +
				response[0].mId + "</a>");
			}
		  });
		  $(".category-title").hover(function (e) {
			$(this).parent().children("a").eq(1).children("div").addClass("cate-hover");
		  }, function (e) {
			$(this).parent().children("a").eq(1).children("div").removeClass("cate-hover");
		  })
		});
	  </script>
	  <section class="main-section">
		<div class="banner-container">
		  <div class="bannerArrow">
			<span class="banner-prev" id="arrow_prev">&lt;</span>
			<span class="banner-next" id="arrow_next">&gt;</span>
		  </div>
		  <div class="banner-slider">
			<div class="banner-item">
			  <img src="img/index/banner/banner1.png" alt="">
			</div>
			<img src="img/index/banner/banner2.png" alt="">
			<img src="img/index/banner/banner3.png" alt="">
		  </div>
		</div>
		<div class="slider-container">
		  <div class="famous-category-slider">
			<div class="slider-top">
			  <div class="slider-title">
				<span>디자인 카테고리</span>에서 인기있어요!
			  </div>
			  <div class="arrowSlider">
				<span class="prev" id="arrow1_prev">&lt;</span>
				<span class="next" id="arrow1_next">&gt;</span>
			  </div>
			</div>
			<div class="famous-category-list">
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-104.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-101.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">로고·브랜딩</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-107.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-101.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-113.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-107.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			</div>
		  </div>
		  <div class="famous-category-slider">
			<div class="slider-top">
			  <div class="slider-title">
				<span>디자인 카테고리</span>에서 인기있어요!
			  </div>
			  <div class="arrowSlider">
				<span class="prev" id="arrow2_prev">&lt;</span>
				<span class="next" id="arrow2_next">&gt;</span>
			  </div>
			</div>
			<div class="famous-category-list">
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-104.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-101.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">로고·브랜딩</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-107.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-101.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-113.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-107.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			</div>
		  </div>
		  <div class="famous-category-slider">
			<div class="slider-top">
			  <div class="slider-title">
				<span>디자인 카테고리</span>에서 인기있어요!
			  </div>
			  <div class="arrowSlider">
				<span class="prev" id="arrow3_prev">&lt;</span>
				<span class="next" id="arrow3_next">&gt;</span>
			  </div>
			</div>
			<div class="famous-category-list">
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-104.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-101.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">로고·브랜딩</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-107.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-101.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-113.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			  <div class="category-item">
				<a href="/serviceList.do?cNo=10&reqPage=1">
				  <img src="img/index/carousel/category-107.png" alt="">
				  <div class="category-title-background"></div>
				  <div class="category-title">캘리그라피·폰트</div>
				</a>
			  </div>
			</div>
		  </div>
		</div>
		<!-- 랭크 부분 -->
		<div class="rank-container">
		  <div class="rank-description">
			<div>TOP 전문가 랭킹</div>
			<div>19시에서 가장 많이 판매한 인기 전문가 랭킹 입니다.</div>
		  </div>
		  <div class="rank-content">
			<div class="rank-list">
			  <div class="rank-top-background">
				<div class="rank-category-title">IT.프로그래밍</div>
				<div class="rank-1th-container">
				  <div class="rank-1th-col1 r-col">1위</div>
				  <div class="rank-1th-col2 r-col">
					<div>
					  <div>총 판매 금액</div>
					  <div class="sumPrice"></div>
					  <div class="free-id">
					  </div>
					</div>
				  </div>
				</div>
			  </div>
			  <div class="rank-row">
				<div class="rank-col-1 r-col">2위</div>
				<div class="rank-col-2 r-col">123,123,123원</div>
				<div class="rank-col-3 r-col">프리랜서아이디</div>
			  </div>
			  <div class="rank-row">
				<div class="rank-col-1 r-col">3위</div>
				<div class="rank-col-2 r-col"></div>
				<div class="rank-col-3 r-col"></div>
			  </div>
			  <div class="rank-row">
				<div class="rank-col-1 r-col">4위</div>
				<div class="rank-col-2 r-col"></div>
				<div class="rank-col-3 r-col"></div>
			  </div>
			  <div class="rank-row">
				<div class="rank-col-1">5위</div>
				<div class="rank-col-2"></div>
				<div class="rank-col-3"></div>
			  </div>
			</div>
			<div class="rank-list">
			  <div class="rank-top-background">
				<div class="rank-category-title">IT.프로그래밍</div>
				<div class="rank-1th-container">
				  <div class="rank-1th-col1 r-col">1위</div>
				  <div class="rank-1th-col2 r-col">
					<div>
					  <div>총 판매 금액</div>
					  <div class="sumPrice">154,997,581원</div>
					  <div class="free-id">
						프리랜서아이디
					  </div>
					</div>
				  </div>
				</div>
			  </div>
			  <div class="rank-row">
				<div class="rank-col-1 r-col">2위</div>
				<div class="rank-col-2 r-col">123,123,123원</div>
				<div class="rank-col-3 r-col">프리랜서아이디</div>
			  </div>
			  <div class="rank-row">
				<div class="rank-col-1 r-col">3위</div>
				<div class="rank-col-2 r-col"></div>
				<div class="rank-col-3 r-col"></div>
			  </div>
			  <div class="rank-row">
				<div class="rank-col-1 r-col">4위</div>
				<div class="rank-col-2 r-col"></div>
				<div class="rank-col-3 r-col"></div>
			  </div>
			  <div class="rank-row">
				<div class="rank-col-1">5위</div>
				<div class="rank-col-2"></div>
				<div class="rank-col-3"></div>
			  </div>
			</div>
			<div class="rank-list">
			  <div class="rank-top-background">
				<div class="rank-category-title">IT.프로그래밍</div>
				<div class="rank-1th-container">
				  <div class="rank-1th-col1 r-col">1위</div>
				  <div class="rank-1th-col2 r-col">
					<div>
					  <div>총 판매 금액</div>
					  <div class="sumPrice">154,997,581원</div>
					  <div class="free-id">
						프리랜서아이디
					  </div>
					</div>
				  </div>
				</div>
			  </div>
			  <div class="rank-row">
				<div class="rank-col-1 r-col">2위</div>
				<div class="rank-col-2 r-col">123,123,123원</div>
				<div class="rank-col-3 r-col">프리랜서아이디</div>
			  </div>
			  <div class="rank-row">
				<div class="rank-col-1 r-col">3위</div>
				<div class="rank-col-2 r-col"></div>
				<div class="rank-col-3 r-col"></div>
			  </div>
			  <div class="rank-row">
				<div class="rank-col-1 r-col">4위</div>
				<div class="rank-col-2 r-col"></div>
				<div class="rank-col-3 r-col"></div>
			  </div>
			  <div class="rank-row">
				<div class="rank-col-1">5위</div>
				<div class="rank-col-2"></div>
				<div class="rank-col-3"></div>
			  </div>
			</div>
		  </div>
		</div>
	  </section>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

</html>