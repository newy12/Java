<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 찜 리스트</title>
</head>

<body>
	<!--전체 wrap-->
	<div id="chat-wrap">

		<!------------------------ [chat-side]------------------------------------->
		<jsp:include page="/WEB-INF/views/chat/chat-side.jsp" />
		<!------------------------ [chat-side]------------------------------------->

		<!------------------------ content 시작 ------------------------------------->
		<div class="content">

			<!-- 찜한 서비스 시작-->
			<div class="content-heart">
				<div id="content-title">내가 찜한 서비스</div>
				<div id="heart-list">
					<ul>

						<c:forEach items="${heartList }" var="f">
							<li class="list">
								<div class="list-wrap">
									<div id="service-preview">
										<p id="name">서비스: ${f.STitle }</p>
										<p id="preview">제공자: ${f.MId }</p>
										<p id="preview">가격: ${f.SPrice } 평점: ${f.SRate }</p>
										<span class="start-chat"><a
											href="/startChat.do?serviceNo=${f.SNo }&myId=${sessionScope.loginMember.MId }&yourId=${f.MId}"><u>
													문의하기</u></a></span>
													<!-- 문의하기 버튼 누르면 사라지게 -->
									</div>
								</div>
							</li>
						</c:forEach>

					</ul>
				</div>
			</div>
			<!-- 찜한 서비스 끝-->

		</div>
		<!------------------------ content 끝 ------------------------------------->

	</div>
	<!-- chat-wrap 끝-->


</body>
</html>