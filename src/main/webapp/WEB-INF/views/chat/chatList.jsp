<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 문의하기</title>
</head>

<body>
	<!--전체 wrap-->
	<div id="chat-wrap">

		<!------------------------ [chat-side]------------------------------------->
		<jsp:include page="/WEB-INF/views/chat/chat-side.jsp" />
		<!------------------------ [chat-side]------------------------------------->

		<!------------------------ content 시작 ------------------------------------->
		<div class="content">

			<!-- 문의 리스트 -->
			<!-- 새로운 메세지 표시 span- color:orange 로 변경-->
			<div class="content-list">
				<div id="content-title">문의 리스트</div>
				<div id="chat-list">
					<ul>
						<li class="list">
							<div class="list-wrap">
								<div class="new"></div>
								<div id="chat-preview">
									<p id="name">
										채팅 상대<span id="time">오후 03:20</span>
									</p>
									<p id="preview">안녕하세요! 번역 서비스 의뢰하려고 하는데요. 분야 상관 없나요?</p>
								</div>
							</div>
						</li>
						<li class="list">
							<div class="list-wrap">
								<div class="new"></div>
								<div id="chat-preview">
									<p id="name">
										채팅 상대<span id="time">오후 03:20</span>
									</p>
									<p id="preview">안녕하세요! 번역 서비스 의뢰하려고 하는데요. 분야 상관 없나요?</p>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<!-- 문의리스트 끝 -->

			<!-- 문의 리스트 없으면 -->
			<!-- empty-page 시작 -->
			<!--		<div class="empty-page">
				<div id="content-title">안녕하세요 회원님!</div>
				<div id="empty-content">
					<br><br><br><br><br><br>
					<img src="/img/icon/exclamation_black.png">
					<h3>아직 문의 내역이 <br>없습니다!</h3>
					<a href="#"><u>나에게 맞는 서비스 검색 ></u></a>
				</div>
			</div>-->
			<!-- empty-page 끝 -->

		</div>
		<!------------------------ content 끝 ------------------------------------->

	</div>
	<!-- chat-wrap 끝-->


</body>
</html>