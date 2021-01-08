<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시(관리자) :: 서비스관리</title>

<style>
@font-face {
	font-family: 'Arita-dotum-Medium';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Arita-dotum-Medium.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

* {
	font-family: 'Arita-dotum-Medium';
}

.header {
	margin: 0 auto;
	width: 1200px;
	height: 100px;
}

.header>img {
	width: 200px;
	float: left;
}

.name {
	float: right;
	margin-right: 50px;
}

.sideNavi {
	height: 800px;
	width: 200px;
	background-color: #314C83;
	border-top-left-radius: 15px;
	padding-top: 20px;
	float: left;
}

.sideNavi>ul {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

.sideNavi li {
	margin: 0;
}

.sideNavi>ul>li>a {
	margin-left: 40px;
	padding: 10px;
	display: block;
	height: 30px;
	line-height: 30px;
	text-decoration: none;
	color: white;
}

.navi-link:hover {
	background-color: #304582;
}

.adminContent {
	float: left;
}
</style>

</head>
<body>
	<div class="header">
		<img src="/img/logo/logo_white.png" onclick="location='/'">
		<div class="name">관리자</div>
	</div>
	<div class="sideNavi">
		<ul>
			<li class="navi-link"><a href="/manageMember.do">MEMBER</a></li>
			<li class="navi-link"><a href="/manageService.do">SERVICE</a></li>
			<li class="navi-link"><a href="">NOTICE</a></li>
			<li class="navi-link"><a href="">FAQ</a></li>
		</ul>
	</div>

	<div class="adminContent">
		<h1>서비스 관리</h1>

		<table border=1>
			<tr>
				<th>서비스번호</th>
				<th>아이디</th>
				<th>카테고리</th>
				<th>서비스명</th>
				<th>등록일</th>
				<th>승인여부</th>
				<th>처리</th>
			</tr>
			<c:forEach items="${serviceList }" var="s">
				<tr>
					<td>${s.SNo }</td>
					<td>${s.MId }</td>
					<td>메인:${ s.mainCategory}서브:${s.subCategory }</td>
					<td><a href=#>${s.STitle }</a></td>
					<td>${s.writeDate }</td>
					<td>${s.adminApproval }</td>
					<td>
						<!-- 아직 승인안된 서비스  --> <c:if
							test="${s.adminApproval eq 'n'.charAt(0)}">
							<button id="accept" onclick="acceptService(${s.SNo});">승인</button>
							<button id="reject" onclick="rejectService(${s.SNo});">거절</button>
						</c:if> <!-- 승인된 서비스 --> <c:if test="${s.adminApproval eq 'y'.charAt(0)}">
							<button id="delete" onclick="deleteService(${s.SNo});">삭제</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script>
	
		function acceptService(sNo){
			$.ajax({
				url : "/acceptService.do",
				type : "post",
				dataType:"json",
				data : {
					sNo : sNo,
				},
				success : function(response) {
					if(response.status!=0){
	
				}
			}
			});
		}
		
		function rejectService(sNo){
			
		}
		
		function deleteService(sNo){
			
		}
		
	</script>
</body>
</html>