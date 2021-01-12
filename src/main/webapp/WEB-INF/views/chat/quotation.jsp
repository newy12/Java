<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 견적서</title>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180"
	href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="favicon_io/favicon-16x16.png">
</head>
<style>
.q-wrap {
	text-align: center;
	margin: 10px auto;
	width: 60%;
	height: 80%;
}

#title {
	font-size: 50px;
	margin: 0 auto;
}

table {
	margin: 30px;
}

tr, th {
	padding: 20px;
	padding-top: 10px;
	padding-bottom: 10px;
}
</style>
<body>
	<div class="q-wrap">
		<img src="img/logo/logo_white.png">
		<p id="title">견적서</p>
		<form action="a.html" method="post">
			<table border="1">
				<tr>
					<th>서비스명</th>
					<td>${STitle }</td>
				</tr>
				<tr>
					<th>제공자</th>
					<td>${freeId }</td>
				</tr>
				<tr>
					<th>의뢰인</th>
					<td>${userId }</td>
				</tr>
				<tr>
					<th>기간</th>
					<td><input type="date"> ~ <input type="date"></td>
				</tr>
				<tr>
					<th>금액</th>
					<td><input type="text"> 원</td>
				</tr>
			</table>
			<h5>2021년 01월 03일</h5>
			<h3>
				위 내용은 수정할 수 없으며,<br> 제출 시 의뢰인에게 전달됩니다.
			</h3>
			<button type="submit">제출</button>
			<button>닫기</button>
		</form>
	</div>
</body>
</html>