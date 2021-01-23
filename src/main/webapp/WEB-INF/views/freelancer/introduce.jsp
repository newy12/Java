<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: [${j.brandName}] 소개</title>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180"
   href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
   href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
   href="favicon_io/favicon-16x16.png">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<br>
	<br>
	<br>
	<div class="content">
		<div class="inner" >
			<div class="title"><br><br><br><br><br><br>
				<p class="tt"><h1>${j.brandName}<span style="font-size:30px;">(${j.MId})</span></h1></p>
				<p class="tt" id="listSize">${list}</p>
				<p class="tt" id="sRate">평균 평점 : ${avg }</p>

			</div>
			<div class="menu">
				<ul>
					<li class="ac" style="height: 50px;"><a href="#one"
						style="text-decoration: none;">소개</a></li>
					<li class="ac" style="height: 50px;"><a href="#two"
						style="text-decoration: none;">평가</a></li>
					<li class="ac" style="height: 50px;"><a href="#three"
						style="text-decoration: none;">서비스</a></li>
				</ul>
			</div>
			<hr>
			<div class="middleleft">
				<br>
				<div id="one">
					<br>
					<h2>소개</h2>
				</div>
				<br>
				<p style="margin-left:20px;">${j.introduceBr}</p>
			</div>

			<div class="middleright">

				<h2>활동정보</h2>
				<br> <span style="float: left;"><b>등록된 서비스</b></span> <span
					style="float: right; padding-right: 40px;"><b>${j.serviceList.size()}</b>개</span>

				<br> <br> <span style="float: left;"><b>평균 평점</b></span> <span
					style="float: right; padding-right: 40px;">${avg }</span> <br>
				<br>
				<h2>전문가 정보</h2>

				<br> <span style="float: left;"><b>연락처</b></span> <span
					style="float: right; padding-right: 40px;">${j.MPhone }</span> <br>
				<br> <span style="float: left;"><b>연락가능시간</b></span> <span
					style="float: right; padding-right: 40px;">${j.contactTime }</span>
				<br> <br> <span style="float: left;"><b>이메일</b></span> <span
					style="float: right; padding-right: 40px;">${j.MEmail }</span>

			</div>

			<div class="middle2left">
				<br> <br>
				<div id="two">
					<br>
					<h2>평가</h2>
				</div>
				<br>
				<p class="tt"  id="sRate2">
					<c:choose>
						<c:when test="${avg  == 5}">
							<p>
								★★★★★ <b> ${avg }</b> /5.0점
							</p>
						</c:when>
						<c:when test="${avg  gt 4}">
							<p>
								★★★★☆ <b> ${avg }</b> /5.0점
							</p>
						</c:when>
						<c:when test="${avg  gt 3}">
							<p>
								★★★☆☆ <b> ${avg }</b> /5.0점
							</p>
						</c:when>
						<c:when test="${avg  gt 2}">
							<p>
								★★☆☆☆ <b> ${avg }</b> /5.0점
							</p>
						</c:when>
						<c:when test="${avg  gt 1}">
							<p>
								★☆☆☆☆ <b> ${avg }</b> /5.0점
							</p>
						</c:when>
						<c:when test="${avg ==0}">
							<p>
								☆☆☆☆☆ <b> ${avg }</b> /5.0점
							</p>
						</c:when>
					</c:choose>


				</p>
				<p class="tt" id="listSize2"></p>
				<br>
				<p>실제 구매한 이용자들이 남긴 평가입니다.</p>
				<br>
				<hr>
				<c:choose>
					<c:when test="${j.reviewList.size()==0}">
						<div class="noList" style="width: 800px">등록된 평가가 없습니다.</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${j.reviewList}" var="r">
							<div class="reviewBox">

								<div id="reviewStar">
									<img src="/img/test/icon_userProfile.svg" width="60px;">
									<p class="date">${r.writeDate}</p>
									<c:if test="${r.RRate ==0}">
										<p>☆☆☆☆☆</p>
										<p class="rate">평점 :${r.RRate}.0</p>
									</c:if>
									<c:if test="${r.RRate ==1}">
										<p>★☆☆☆☆</p>
										<p class="rate">평점 :${r.RRate}.0</p>
									</c:if>
									<c:if test="${r.RRate ==2}">
										<p>★★☆☆☆</p>
										<p class="rate">평점 :${r.RRate}.0</p>
									</c:if>
									<c:if test="${r.RRate ==3}">
										<p>★★★☆☆</p>
										<p class="rate">평점 :${r.RRate}.0</p>
									</c:if>
									<c:if test="${r.RRate ==4}">
										<p>★★★★☆</p>
										<p class="rate">평점 :${r.RRate}.0</p>
									</c:if>
									<c:if test="${r.RRate ==5}">
										<p>★★★★★</p>
										<p class="rate">평점 :${r.RRate}.0</p>
									</c:if>
									<hr>
								</div>
								<div id="reviewContentBox">
									<p class="userId">
										<b>작성자 :</b> ${r.MId}
									</p>
									<p class="serviceName">
										<b>서비스 : </b>[${r.STitle}]
									</p>
									<hr>
									<br>
									<p class="reviewText">${r.RContent}</p>
								</div>

							</div>
						</c:forEach>
						<div class="pageNavi"
							style="text-align: center; color: gray; letter-spacing: 10px; font-size: 15px; margin-top: 45px;">${pageNavi}</div>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="middle2right"></div>
			<br> <br>
			<div class="middle3"">
				<br> <br>
				<div id="three">
					<br>
					<h2>서비스(${j.serviceList.size()}개)</h2>
				</div>
				<br> 
				<div class="tabContent" style=";">
					<c:choose>
						<c:when test="${j.serviceList.size()==0}">
							<div class="noList" style="width: 800px">등록된 서비스가 없습니다.</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${j.serviceList}" var="r">
								<div class="serviceBox">
									<img src="/upload/service/${r.SImg}" alt="없는이미지"
										style="width: 150px; height: 150px;"><br> <span
										class="preName">[<b>${r.STitle }</b>]
									</span><br>
									<p class="serviceCon" style="float: right;">${r.SPrice }원~</p>
									<br>
									<span style="float: right;">
									<c:choose>
									<c:when test="${r.SRate gt 4.9}">
										<p class="score">
											평점 ${r.SRate}점<span class="star"> ★★★★★</span>
										</p>
									</c:when>
									<c:when test="${r.SRate gt 3.9}">
										<p class="score">
											평점 ${r.SRate}점<span class="star"> ★★★★☆</span>
										</p>
									</c:when>
									<c:when test="${r.SRate gt 2.9}">
										<p class="score">
											평점 ${r.SRate}점<span class="star"> ★★★☆☆</span>
										</p>
									</c:when>
									<c:when test="${r.SRate gt 1.9}">
										<p class="score">
											평점 ${r.SRate}점<span class="star"> ★★☆☆☆</span>
										</p>
									</c:when>
									<c:when test="${r.SRate gt 0.9}">
										<p class="score">
											평점 ${r.SRate}점<span class="star"> ★☆☆☆☆</span>
										</p>
									</c:when>
									<c:when test="${r.SRate ==0}">
										<p class="score">
											평점 ${r.SRate}점<span class="star"> ☆☆☆☆☆</span>
										</p>
									</c:when>
									</c:choose>
									
									</span>
									
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<input type="hidden" id="haha" value="${j.MId}">
	</div>
	<a id="topBtn" href="#"
		style="width: 50px; height: 50px; line-height: 50px; background-color: orange; border-radius: 25%; text-decoration: none; text-align: center;">
		TOP
		</a>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

<script>
	$(function() {
		var mId = $('#haha').val();
		$(window).scroll(function() {
			if ($(this).scrollTop() > 200) {
				$('#topBtn').fadeIn();
			} else {
				$('#topBtn').fadeOut();
			}
		});
		$('#topBtn').click(function() {
			$('html, body').animate({
				scrollTop : 0
			}, 300);
			return false;
		});

		//새 jsp(reviewListSize.jsp)파서 값 불러내고 다시 불러오는 방식 ajax 평가 개수구하기
		$.ajax({
			type : "get",
			url : "/reviewListSize.do",
			data : {
				mId : mId
			},
			success : function(result) {
				console.log(result);
				if(result=='[null]'){
					result=[0];
				}
				$('#listSize').html(result + "개의 평가").css("color", "gray");
				$('#listSize2').html(result + "개의 평가").css("color", "gray")
						.css("margin-left", "0px");
			}
		});
		//평점 평균 구하기 ajax
		/* $.ajax({
			type : "get",
			url : "/sRateAVG.do",
			data : {
				mId : mId
			},
			success : function(result) {
				console.log(result);
				if (result = 'null') {
					$('#sRate').html("평점0.0").css("color", "gray");
					$('#sRate2').html("평점0.0").css("color", "gray");
				} else {
					$('#sRate').html("평점" + result).css("color", "gray");
					$('#sRate2').html("평점" + result).css("color", "gray");
				}

			}
		}); */

	});
</script>
<style>
/* div {
	border: 0.5px solid black;
} */
.tabcontent {
	width: 100%;
	height: 100%;
	text-align: left;
	padding: 20px;
	font-size: 8pt;
}

div.tabContent .serviceBox {
	width: 150px;
	/* height: 300px; */
	float: left;
	margin-left: 15px;
}

.noList {
	margin-top: 5px;
	text-align: center;
	line-height: 185px;
	color: rgb(224, 224, 224);
	font-size: 20px;
	font-weight: bold;
}

.content {
	height: 2500px;
}

body, hr, p, ul {
	margin: 0;
}

p.tt {
	margin-left: 100px;
	font-size:18px;
}

a {
	color: black;
}

li.ac {
	list-style: none;
	float: left;
	width: 100px;
	line-height:50px;
	text-align:center;
	margin-left: -30px;
	margin-right: 30px;
	font-size: 20px;
	/* background-color: #314C83; */
	font-weight: bold;
	box-sizing: border-box;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	border: 2px solid #314C83;
	border-bottom: none;
}

li.ac a{
color: #314C83;
display:block;}

div.inner {
	width: 1200px;
	margin: 0 auto;
}

div.title {
	width: 100%;
	height: 300px;
	margin-bottom:30px;
	padding:30px;
	text-align: right;
	background-image: url("img/free/free_banner.jpeg");
	background-size:cover;
	background-repeat: no-repeat;
}


div.menu {
	width: 100%;
	height: 50px;
}

div.middleleft {
	width: 70%; /* 
	height: 750px; */
	float: left;
}

div.middleright {
	float: left;
	width: 300px;
	/* border: 1px solid black; */
	margin-left: 60px;
	margin-top: 80px;
	padding-left: 20px;
	padding-bottom: 50px;
	background-color:#f5f5f5;
}

div.boxinner {
	width: 280px;
	margin: 0 auto;
}

div.box {
	/* width: 300px;
	/* height: 550px; */
	border: 1px solid black;
	margin-left: 60px;
	margin-top: 120px;
	*/
}

div.middle2left {
	width: 70%;
	float: left;
}

div.middle2list {
	width: 100%; /* 
	height: 300px; */
}

div.middle2right {
	width: 30%; /* 
	height: 1150px; */
	float: left;
}

div.middle3 {
	width: 70%;;
	float: left;
	height:600px;
}

div.middle4 {
	width: 70%;
	/* height: 300px; */
	float: left;
}

#topBtn {
	position: fixed;
	right: 90px;
	bottom: 13%;
	z-index: 9;
	color:white;
	font-weight: bold;
}

.reviewBox {
	overflow: hidden;
	width: 100%;
	height: 100%;
	/* background-color: yellow; */
}

#reviewStar {
	/* background-color: blue; */
	width: 10%;
	height: 100%;
	float: left;
}

#reviewContentBox {
	/* background-color: green; */
	margin-left: 20px;
	padding-left: 10px;
	margin-top: 15px;
	width: 80%;
	height: 100%;
	float: left;
}
</style>
</body>
</html>