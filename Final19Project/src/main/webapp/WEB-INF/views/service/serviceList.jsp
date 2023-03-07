<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 서비스</title>
	<!-- favicon -->
	<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
	<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.js"></script>

<!-- 부트스트랩 페이징 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<style>
@font-face {
	font-family: 'Arita-dotum-Medium';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Arita-dotum-Medium.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

.contentWrap {
	width: 1200px;
	margin: 0 auto;
	height: 1600px;
}

.sideNavi {
	color: #282828;
	font-family: 'Arita-dotum-Medium';
	/*border: 1px solid lightgray;*/
	width: 200px;
	float: left;
}

.serviceList {
	width: 980px;
	float: left;
}

.naviTitle {
	padding-left: 10px;
	font-size: 15pt;
	margin: 15px;
	border-left: 3px solid #314C83;
}

.menu ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	text-align: left;
}

.menu li>a {
	margin-left: 15px;
	text-decoration: none;
	width: 200px;
	height: 40px;
	line-height: 40px;
	display: block;
	text-indent: 10px;
	color: #282828;
	font-size: 11pt;
}

a:hover {
	font-weight: bold;
}

.crumb {
	font-family: 'Arita-dotum-Medium';
	color: dimgray;
	font-size: 10pt;
	padding: 20px;
}

.selectBox {
	height: 30px;
	width: 70px;
}

.searchDiv {
	width: 978px;
	height: 50px;
	float: right;
}

.searchBox {
	font-family: 'Arita-dotum-Medium';	
}

.search-cover{
	float: right;

}


.search-cover>button {
	outline: none;
	border: 1px solid #314C83;
	background-color: #314C83;
	color: white;
	font-weight: bold;
	border-radius: 3px;
	height: 34px;
	width: 55px;
}

.tableContainer {
	margin: 0 auto;
}

.serviceBox {
	color: #282828;
	font-family: 'BBTreeGR';
	margin: 10px;
	/*border: 1px solid gray;*/
	padding: 10px;
	width: 225px;
	height: 340px;
	display: inline-block;
	float: left;
	overflow: hidden;
}

.serviceBox img {
	border-radius: 5px;
	width: 210px;
	height: 150px;
	transform: scale(1);
    transition: transform 0.1s linear;
}

.imgBox{
	overflow: hidden;
	width: 210px;
	height: 150px;
	border-radius: 5px;
}
.serviceBox img:hover{
	transform: scale(1.03);
    overflow: hidden;
    cursor: pointer;
}

.preName {
	text-align: left;
	font-size: 10pt;
	font-weight: bold;
	margin-bottom: px;
}

.serviceCon >a{
	font-size: 11pt;
	margin: 0;
	color: gray;
}
.serviceCon>a:hover{
	text-decoration: none;
}

.price {
	text-align: right;
	font-weight: bold;
	font-size: 12pt;
	margin: 0;
}

.score {
	font-size: 8pt;
	text-align: right;
	color: gray;
	margin: 0;
	height: 15px;
}

.star {
	color: rgb(241, 196, 15);
	font-size: 14pt;
}

.pageNaviBox {
	width: 1000px;
	height: 40px;
	margin: 0 auto;
	text-align: center;
}

.page-link : hover {
	
}

.selectedPage {
	color: rgb(255, 143, 63);
	font-weight: bold;
}

.pagination>li>span {
	color: transparent;
}
</style>

</head>
<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<div class="contentWrap">

		<div class="crumb">
			<div>홈 > 서비스</div>
		</div>

		<div class="sideNavi menu">
			<p class="naviTitle">${mainCate }</p>
			<input type="hidden" class="pageNo" value="${c_no }">

			<ul>
				<c:forEach items="${catList }" var="c">
					<li class="navi-item"><a
						href="/serviceList.do?cNo=${c.CNo }&reqPage=1&order=new"> ${c.CName } </a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div class="serviceList">
			<div class="searchDiv">
				<div class="searchBox">
					<select class="selectBox subject form-control array" id="search-subject" style="width: 120px; margin-right: 10px; float: left;">
						<option value="new" selected="selected">최신순</option>
						<option value="popular">인기순</option>
						<option value="review">평점순</option>
						<option value="rowPrice">낮은 가격순 </option>
					</select> 
					<div class="search-cover">
						<c:choose>
		                	<c:when test="${keyword == 'null' }">
		                		<input type="text" class="form-control keyword" placeholder="검색어를 입력해주세요" style="width:200px; float: left; margin-right: 10px;">
		                	</c:when>
		                	<c:otherwise>
		                		<input type="text" class="form-control keyword" style="width:200px; float: left; margin-right: 10px;" value="${keyword }">
		                	</c:otherwise>
		                </c:choose>
						<button type="submit" class="search-btn">검색</button>
					</div>
					
					
					
				</div>
			</div>
			<br> <br>
			
			
			<div class="tableContainer">

				<c:if test="${noserviceList != null }">
					<p class="noService">등록된 서비스가 없습니다.</p>
				</c:if>
				<c:if test="${serviceList != null }">
					<c:forEach items="${serviceList }" var="s" varStatus="status">
						<div class="serviceBox">
						
						<c:if test="${loginMember== null }"> 
							<div class="imgBox">
								<img src="upload/service/${s.SImg }" onclick="location.href='/serviceView2.do?sNo=${s.SNo}&reqPage=1'">
							</div>
							<br> 
							<span class="preName">${s.brandName }</span>
							<p class="serviceCon" style="height: 30px;">
								<a href="/serviceView2.do?sNo=${s.SNo}&reqPage=1"> ${s.STitle } </a>
							</p>
						</c:if>
						
						
						
						<c:if test="${loginMember != null }">
							<div class="imgBox">
								<img src="upload/service/${s.SImg }" onclick="location.href='/serviceView.do?sNo=${s.SNo}&reqPage=1&mNo=${loginMember.MNo }'">
							</div>
							<br> 
							<span class="preName">${s.brandName }</span>
							<p class="serviceCon" style="height: 30px;">
								<a href="/serviceView.do?sNo=${s.SNo}&reqPage=1&mNo=${loginMember.MNo }"> ${s.STitle } </a>
							</p>
						</c:if>
						
							<p class="price">${s.SPriceTxt }원~</p>
							<p class="score">
								평점 ${s.SRate }
							<c:choose>
								<c:when test="${s.SRate == 5}">
									<span class="star"> ★★★★★ </span>
								</c:when>
	
								<c:when test="${s.SRate gt 3.9}">
									<span class="star"> ☆★★★★ </span>
								</c:when>
								<c:when test="${s.SRate gt 2.9}">
									<span class="star"> ☆☆★★★ </span>
								</c:when>
								<c:when test="${s.SRate gt 1.9}">
									<span class="star"> ☆☆☆★★ </span>
								</c:when>
								<c:when test="${s.SRate gt 0.9}">
									<span class="star"> ☆☆☆☆★ </span>
								</c:when>
								<c:when test="${s.SRate == 0}">
									<span class="star"> ☆☆☆☆☆ </span>
								</c:when>
							</c:choose>
							</p>
						</div>
					</c:forEach>
				</c:if>

				
			</div>


		</div>
		<div class="pageNaviBox">${pageNavi }</div>

	</div>


	<jsp:include page="/WEB-INF/views/common/footer.jsp" />


	<script>
		$(document).ready(function() {
			var mNo = "${loginMember.MNo}";
			var memberNo ;
			if(mNo == 0){
				console.log("mNo:0");
				memberNo = 0;
			}else{
				console.log("mNo:"+mNo);
				membeNo = mNo;
			}
			

			var order = "${order}";
			var subject = $("#subject").val();
			var cNo = $(".pageNo").val();
			var keyword = $("keyword").val;
			
			if(order == "new"){
				$(".array").val("new").prop("selected",true);
			}else if (order == "popular"){
				$(".array").val("popular").prop("selected",true);
			}else if (order == "review"){
				$(".array").val("review").prop("selected",true);
			}else if (order == "rowPrice"){
				$(".array").val("rowPrice").prop("selected",true);
			}
			
			
			$(".array").change(function(){
				var order = $(".array").val();
				location.href = "/serviceList.do?cNo="+cNo+"&reqPage=1&order="+order+"&keyword=";			
				
			});
			
			$(".search-btn").click(function () {
				var keyword = $(".keyword").val();
				location.href="/serviceList.do?cNo="+cNo+"&reqPage=1&order=new&keyword="+keyword;
			})
	
		});
	</script>



</body>
</html>



















