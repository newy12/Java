<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 나의 서비스</title>
</head>
<body>
<!--전체 wrap-->
	<div id="chat-wrap">

		<!------------------------ [chat-side]------------------------------------->
		<jsp:include page="/WEB-INF/views/chat/chat-side.jsp" />
		<!------------------------ [chat-side]------------------------------------->

		<!------------------------ content 시작 ------------------------------------->
		<div class="content">


			<!-- serviceListt가 not null -->
			<c:if test="${not empty heartList }">
				<!-- 서비스리스트 시작-->
				<div class="content-heart">
					<div id="content-title">나의 서비스</div>
					<div id="service-list">
						<ul>
							<%-- <c:forEach items="${ }" var="s"> --%>
					
					<!-- 등록 대기중인 서비스 -->
					<!-- 등록된 서비스 -->
					<!-- 거래진행중인 서비스 -->
					<!-- 삭제된 서비스 -->
					<!-- 서비스별 작업내역 -->
					
							<%-- </c:forEach> --%>
						</ul>
					</div>
				</div>
			</c:if>
			<!-- serviceList가 not null -->

			<!-- serviceList가 null -->
			<c:if test="${empty heartList}">
				<div class="empty-page">
					<div id="content-title">나의 서비스</div>
					<div id="empty-content">
						<br> <br> <br> <br> <br> <br> <img
							src="/img/icon/exclamation_black.png">
						<h3>
							아직 등록한 서비스가 <br>없습니다!
						</h3>
						<a href="#"><u>서비스 등록하러 가기 ></u></a>
					</div>
				</div>
			</c:if>
			<!-- serviceList가 null -->

			<!-- 서비스 리스트 끝-->
		</div>
		<!------------------------ content 끝 ------------------------------------->

	</div>
	<!-- chat-wrap 끝-->
</body>
</html>