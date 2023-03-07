<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<style>
		.main-top-info-container {
			margin-top: 40px;
			padding: 20px 0;
			width: 1200px;
			overflow: hidden;
			background-color: #F2F3F7;
		}

		.top-info-left {
			width: 20%;
			float: left;
		}
		.top-info-left-title{
			margin: 0 auto;
			margin-top: 20px;
			margin-bottom: 30px;
			width: 200px;
			font-size: 16px;
		}
		.top-info-left-title>span{
			font-weight: bold;
		}
		

		.main-top-info-container .bannerArrow {
			/* height: 200px; */
		}

		.main-top-info-container>.banner-container {
			margin: 0;
			width: 60%;
			float: left;
			border-radius: 5px;
		}

		.main-top-info-container .banner-container img {
			height: 250px;
			border-radius: 5px;
		}

		.main-top-info-container .banner-prev,
		.main-top-info-container .banner-next {
			line-height: 250px;
		}

		.main-top-info-container>.member-info-container {
			width: 18%;
			height: 230px;
			float: left;
		}

		.member-info-container {
			margin-top: 15px;
			margin-left: 20px;
			padding-right: 20px;
		}

		.member-info-name {
			font-size: large;
		}

		.member-info-name>span {
			font-weight: bold;
		}
		.member-info-buying{
			font-size: 14px;
			margin: 10px 0;
		}
		.member-info-buying>span,
		.member-info-selling>span{
			display: inline-block;
			float: right;
		}
		
		.member-info-buying>span>span,
		.member-info-selling>span>span{
			color: #314C83;
			font-weight: bold;
			font-size: 15px;
		}
		.request-container {
			text-align: left;
			padding-left: 15px;
			margin: 0 auto;
			border-radius: 5px;
			width: 200px;
			height: 60px;
			line-height: 60px;
			font-size: 15px;
			font-weight: bold;
			background-color: white;
		}

		.request-container>a {
			display: inline-block;
			width: 100%;
			height: 100%;
			text-decoration: none;
			color: #282828;
		}

		.single-banner1{
			margin-top: 80px;
			width: 100%;
			/* height: 300px; */
		}
		.single-banner1>img{
			width: 100%;
			height: 190px;
			object-fit:cover;
			object-position: 50% 10%;
		}
	</style>
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<script>
		$(document).ready(function () {
			// 배너 슬라이더 부분
			$('.banner-slider').slick({
				infinite: true,
				slidesToShow: 1,
				slidesToScroll: 1,
				draggable: false,
				autoplay: true,
				autoplaySpeed: 5000,
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
					cateNum: 20
				},
				dataType: "json",
				success: function (response) {
					console.log(response);
					$(".rank-category-title:eq(0)").text("IT·프로그래밍");
					$(".sumPrice:eq(0)").empty
					console.log(response["sump"]);
					$(".sumPrice:eq(0)").text(response[0].sumPrice + "원");
					$(".free-id:eq(0)").append("<a href='/introduceFrm.do?mId=" + response[0].mId +
						"&reqPage=1'>" +
						response[0].mId + "</a>");
					for (let i = 1; i < response.length; i++) {
						$(".rank-list:eq(0)").find(".rank-col-2:eq(" + (i-1) + ")").text(response[i]
							.sumPrice + "원");
						$(".rank-list:eq(0) .rank-col-3:eq(" + (i-1) + ")").append(
							"<a href='/introduceFrm.do?mId=" + response[i].mId +
							"&reqPage=1'>" +
							response[i].mId + "</a>");
					}
				}
			});
			$.ajax({
				type: "post",
				url: "/rankAjax.do",
				data: {
					cateNum: 40
				},
				dataType: "json",
				success: function (response) {
					console.log(response);
					$(".rank-category-title:eq(1)").text("교육");
					$(".sumPrice:eq(1)").empty
					console.log(response["sump"]);
					$(".sumPrice:eq(1)").text(response[0].sumPrice + "원");
					$(".free-id:eq(1)").append("<a href='/introduceFrm.do?mId=" + response[0].mId +
						"&reqPage=1'>" +
						response[0].mId + "</a>");
					for (let i = 1; i < response.length; i++) {
						$(".rank-list:eq(1)").find(".rank-col-2:eq(" + (i-1) + ")").text(response[i]
							.sumPrice + "원");
						$(".rank-list:eq(1) .rank-col-3:eq(" + (i-1) + ")").append(
							"<a href='/introduceFrm.do?mId=" + response[i].mId +
							"&reqPage=1'>" +
							response[i].mId + "</a>");
					}
				}
			});
			$.ajax({
				type: "post",
				url: "/rankAjax.do",
				data: {
					cateNum: 70
				},
				dataType: "json",
				success: function (response) {
					console.log(response);
					$(".rank-category-title:eq(2)").text("주문제작");
					$(".sumPrice:eq(2)").empty
					console.log(response["sump"]);
					$(".sumPrice:eq(2)").text(response[0].sumPrice + "원");
					$(".free-id:eq(2)").append("<a href='/introduceFrm.do?mId=" + response[0].mId +
						"&reqPage=1'>" +
						response[0].mId + "</a>");
					for (let i = 1; i < response.length; i++) {
						$(".rank-list:eq(2)").find(".rank-col-2:eq(" + (i-1) + ")").text(response[i]
							.sumPrice + "원");
						$(".rank-list:eq(2) .rank-col-3:eq(" + (i-1) + ")").append(
							"<a href='/introduceFrm.do?mId=" + response[i].mId +
							"&reqPage=1'>" +
							response[i].mId + "</a>");
					}
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
		<c:if test="${not empty loginMember}">
			<div class="main-top-info-container">
				<div class="top-info-left">
					<div class="top-info-left-title">
						<span>다양한 방법으로</span><br>
						전문가를 만나보세요!
					</div>
					<div class="request-container">
						<a href="/requestList.do?reqPage=1&order=new&subject=all&keyword=">
							<img src="img/index/banner/custom.png" width="50px" alt="">
							맞춤 견적
						</a>
					</div>
				</div>
				<div class="banner-container">
					<div class="bannerArrow">
						<span class="banner-prev" id="arrow_prev">&lt;</span>
						<span class="banner-next" id="arrow_next">&gt;</span>
					</div>
					<div class="banner-slider">
						<img src="img/index/banner/banner1.png" alt="">
						<img src="img/index/banner/banner2.png" alt="">
						<img src="img/index/banner/banner3.png" alt="">
					</div>
				</div>
				<div class="member-info-container">
					<div class="member-info-name">
						반가워요<br><span>${loginMember.MId}</span>님!
					</div>
					<div class="member-info-buying">
						구매 진행 중<span><span>${loginMember.buyingCount}</span> 건</span>
					</div>
					<div class="member-info-selling">
						판매 진행 중<span><span>${loginMember.sellingCount}</span> 건</span>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${empty loginMember}">
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
		</c:if>
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
						<a href="/serviceList.do?cNo=11&reqPage=1">
							<img src="img/index/carousel/category-11.png" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">로고·브랜딩</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=13&reqPage=1">
							<img src="img/index/carousel/category-13.png" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">캘리그라피·폰트</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=14&reqPage=1">
							<img src="img/index/carousel/category-14.png" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">일러스트·캐리커쳐</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=16&reqPage=1">
							<img src="img/index/carousel/category-16.png" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">이벤트·상세 페이지</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=15&reqPage=1">
							<img src="img/index/carousel/category-15.png" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">간판·시공</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=12&reqPage=1">
							<img src="img/index/carousel/category-12.png" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">인쇄·홍보물·배너</div>
						</a>
					</div>
				</div>
			</div>
			<div class="famous-category-slider">
				<div class="slider-top">
					<div class="slider-title">
						<span>IT·프로그래밍 카테고리</span>에서 인기있어요!
					</div>
					<div class="arrowSlider">
						<span class="prev" id="arrow2_prev">&lt;</span>
						<span class="next" id="arrow2_next">&gt;</span>
					</div>
				</div>
				<div class="famous-category-list">
					<div class="category-item">
						<a href="/serviceList.do?cNo=21&reqPage=1">
							<img src="img/index/carousel/category-21.png" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">워드프레스</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=22&reqPage=1">
							<img src="img/index/carousel/category-22.png" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">웹사이트개발</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=23&reqPage=1">
							<img src="img/index/carousel/category-23.jpg" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">프로그램개발</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=24&reqPage=1">
							<img src="img/index/carousel/category-24.png" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">데이터베이스</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=25&reqPage=1">
							<img src="img/index/carousel/category-25.png" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">게임개발</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=27&reqPage=1">
							<img src="img/index/carousel/category-27.jpg" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">파일변환</div>
						</a>
					</div>
				</div>
			</div>
			<div class="famous-category-slider">
				<div class="slider-top">
					<div class="slider-title">
						<span>비즈니스컨설팅 카테고리</span>에서 인기있어요!
					</div>
					<div class="arrowSlider">
						<span class="prev" id="arrow3_prev">&lt;</span>
						<span class="next" id="arrow3_next">&gt;</span>
					</div>
				</div>
				<div class="famous-category-list">
					<div class="category-item">
						<a href="/serviceList.do?cNo=61&reqPage=1">
							<img src="img/index/carousel/category-61.jpg" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">사업계획서·투자제안서</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=62&reqPage=1">
							<img src="img/index/carousel/category-62.jpg" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">인사·노무</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=63&reqPage=1">
							<img src="img/index/carousel/category-63.jpg" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">창업컨설팅</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=64&reqPage=1">
							<img src="img/index/carousel/category-64.jpg" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">법률법무</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=65&reqPage=1">
							<img src="img/index/carousel/category-65.jpg" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">업무지원·cs</div>
						</a>
					</div>
					<div class="category-item">
						<a href="/serviceList.do?cNo=60&reqPage=1">
							<img src="img/index/carousel/category-60.jpg" alt="">
							<div class="category-title-background"></div>
							<div class="category-title">컨설팅</div>
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="single-banner1">
			<img src="img/index/banner/banner4.png" alt="">
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
						<div class="rank-category-title"></div>
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
						<div class="rank-col-2 r-col"></div>
						<div class="rank-col-3 r-col"></div>
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
						<div class="rank-col-1 r-col">5위</div>
						<div class="rank-col-2 r-col"></div>
						<div class="rank-col-3 r-col"></div>
					</div>
				</div>
				<div class="rank-list">
					<div class="rank-top-background">
						<div class="rank-category-title"></div>
						<div class="rank-1th-container">
							<div class="rank-1th-col1 r-col">1위</div>
							<div class="rank-1th-col2 r-col">
								<div>
									<div>총 판매 금액</div>
									<div class="sumPrice">154,997,581원</div>
									<div class="free-id">
										
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="rank-row">
						<div class="rank-col-1 r-col">2위</div>
						<div class="rank-col-2 r-col"></div>
						<div class="rank-col-3 r-col"></div>
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
						<div class="rank-col-1 r-col">5위</div>
						<div class="rank-col-2 r-col"></div>
						<div class="rank-col-3 r-col"></div>
					</div>
				</div>
				<div class="rank-list">
					<div class="rank-top-background">
						<div class="rank-category-title">주문제작</div>
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
						<div class="rank-col-2 r-col"></div>
						<div class="rank-col-3 r-col"></div>
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
						<div class="rank-col-1 r-col">5위</div>
						<div class="rank-col-2 r-col"></div>
						<div class="rank-col-3 r-col"></div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

</html>