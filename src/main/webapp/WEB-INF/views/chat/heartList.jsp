<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						<li class="list">
							<div class="list-wrap">
								<div id="service-preview">
									<p id="name">프리랜서 이름</p>
									<p id="preview">서비스 내용 서비스 내용 서비스 내용 서비스 내용 서비스 내용</p>
									<span class="start-chat"><a href="/startChat.do"><u> > 문의하기</u></a></span>
								</div>
							</div>
						</li>

						<li class="list">
							<div class="list-wrap">
								<div id="service-preview">
									<p id="name">프리랜서 이름</p>
									<p id="preview">서비스 내용 서비스 내용 서비스 내용 서비스 내용 서비스 내용</p>
									<span class="start-chat"><a href="/startChat.do"><u> > 문의하기</u></a></span>
								</div>
							</div>
						</li>

						<li class="list">
							<div class="list-wrap">
								<div id="service-preview">
									<p id="name">프리랜서 이름</p>
									<p id="preview">서비스 내용 서비스 내용 서비스 내용 서비스 내용 서비스 내용</p>
									<span class="start-chat"><a href="/startChat.do"><u> > 문의하기</u></a></span>
								</div>
							</div>
						</li>

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