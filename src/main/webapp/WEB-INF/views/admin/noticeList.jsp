<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 	<!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>

    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- slick CSS -->
    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />

    <!-- slick JS -->
    <script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<style>
 .noticeContainer div{
	
}

.noticeContainer{
	width: 980px;
	float: left;
}

.noticeTitle{
	border-left: 5px solid #304582;
	width:150px;
	height: 40px;
	line-height : 40px;
	font-size: 15pt;
	margin: 30px;
	text-indent: 20px;
	display: block;
	font-weight: bold;
	
}
.table{
	margin-left: 20px;
}
.searchBox{
	width: 300px;
	float: left;
	
}

.search-btn{
	width: 50px;
	height: 34px;
	border: 2px solid #304582;
	color: #304582;
	border-radius: 5px;
	background-color: transparent;
	font-weight: bold;
	margin-left: 15px;
}
.search-btn:hover{
	/* background-color: #304582;
	color: white; */
	font-size: 1.11em;
}


.container-bottom{
	margin: 0 auto;
	width:400px; 
}

.write-wrap {
	 width: 800px;
 }
 
.write-btn{
	float: right;
	margin-right: 15px;
	width: 80px;
	height: 34px;
	background-color: #304582;
	color: white;
	border: 1px solid #304582;
	border-radius: 5px;
}
.write-btn:hover{
	border: 2px solid #304582;
	color: #304582;
	background-color: transparent;
	font-weight: bold;
	
}

</style>


</head>
<body>

	<jsp:include page="/WEB-INF/views/admin/adminMainpage.jsp" />
	
	<div class="noticeContainer">
		<div class="noticeTitle"> 공지사항 </div>
		<table class="table table-striped table-hover">
			<tr>
				<th width="120px;"> 글번호 </th> <th> 글제목 </th> <th width="120px;"> 작성자 </th> <th  width="180px;"> 작성일 </th>
			</tr>
			<c:forEach items="${list }" var="n">
				<tr> 
					<td>${n.NNo } </td>
                	<td><a href="/noticeView.do?nNo=${n.NNo }" class = "table-title">${n.NTitle } </a> </td>
                	<td>일.구.시 </td>
                	<td> ${n.writeDate } </td>
				</tr>
			
			</c:forEach>
		</table>
		
		<div class="pageNavi" style="width: 100%; height: 40px;">
			${pageNavi }
		</div>
		
		<div class="content-bottom write-wrap" style="float: right;">
			<input type="button" class="button write-btn" value="글쓰기">
		</div>
		<div class="container-bottom content-bottom ">
			<input type="text" class="form-control searchBox" id="keyword"> 
			<input type="button" class="search-btn " value="검색">
		</div>
	</div>
	
	
	
	
	<script>
		$(function () {
			$(".search-btn").click(function() {
				var keyword = $("#keyword").val();
				location.href = "/adminNoticeList.do?reqPage=1&keyword="+keyword;
			})
			
			$(".button").click(function () {
				location.href="/noticeWriteFrm.do";
			})
		})
	
	</script>
	
</body>
</html>




































