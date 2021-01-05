<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 일 구하고 시퍼</title>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180"
	href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="favicon_io/favicon-16x16.png">
<link rel="manifest" href="/site.webmanifest">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<h1>여러분 화이팅~~!!~</h1>
	<h2>해피뉴이어~~</h2>
	<hr>
	<h3>본인 페이지 테스트</h3>
	소현
	<a href="/chatList.do"
		onClick="window.open(this.href, '', 'width=530, height=630, left=1000, scrollbars=no,location=no, resizable=no'); return false;">채팅하기</a>
	영재
	<a href="/introduceFrm.do">프리랜서소개글</a>
	<a href="/serviceJoinFrm.do">서비스등록</a>
	문정
	<a href="/introduceFrm.do">프리랜서소개글</a> 문정
	<a href="/requestList.do">의뢰게시판 리스트</a>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>