<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
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
	float:left
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
	margin-left:50px;
	
}
.serviceNavi ul {
	list-style-type: none;
	overflow: hidden;
	padding: 0px;
	margin: 0px;
}

.tab {
	float: left;
	width: 150px;
	height: 60px;
	text-align: center;
	line-height: 60px;
	color: white;
	background-color: #314C83;
	font-weight: bold;
	box-sizing: border-box;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	border: 2px solid #314C83;
	border-bottom: none;
}

.adminContent div {
	margin: 30px;
	text-align: left;
	display: none;
}

.select {
	color: #314C83;
	background-color: white;
	font-weight: bold;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
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
			<li class="navi-link"><a href="/manageQnA.do?list_num=10">FAQ</a></li>
		</ul>
	</div>


</body>
</html>