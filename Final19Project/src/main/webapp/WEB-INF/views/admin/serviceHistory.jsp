<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시(관리자) :: 서비스 작업내역</title>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180"
	href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="favicon_io/favicon-16x16.png">
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

.historyTable a {
	text-decoration: none;
}

.historyTable th {
	background-color: #314C83;
	text-align: center;
	padding: 10px;
	color: white;
}

.historyTable th:first-child {
	border-top-left-radius: 10px;
}

.historyTable th:last-child {
	border-top-right-radius: 10px;
}

.historyTable td {
	text-align: center;
	padding: 5px;
	border-bottom: 0.5px solid gray;
}
</style>
</head>
<body>
	<table class="historyTable"
		style="width: 90%; margin-top: 10px; text-align: center;">
		<tr>
			<th>사용자아이디</th>
			<th>진행상태</th>
			<th>가격</th>
			<th>시작일</th>
			<th>종료일</th>
		</tr>
		<c:forEach items="${history }" var="h">
			<tr>
				<td>${h.MId }</td>
				<td><c:if test="${h.TStatus eq 0}">결제전</c:if> <c:if
						test="${h.TStatus eq 1}">진행중</c:if> <c:if test="${h.TStatus gt 1.9}">진행완료</c:if></td>
				<td>${h.TPrice}</td>
				<td>${h.startDate}</td>
				<td>${h.endDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>