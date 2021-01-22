<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 찜 리스트</title>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180"
   href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
   href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
   href="favicon_io/favicon-16x16.png">
<style>
#name {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

.startChat, .deleteFavorite {
	color: white;
	display: inline-block;
	width: 70px;
	height: 30px;
	text-align: center;
	line-height: 30px;
	border-radius: 10px;
}

.navy {
	background-color: #314C83;
}

.orange {
	background-color: #FF8F3F;
}
</style>
</head>
<body>
	<!--전체 wrap-->
	<div id="chat-wrap">

		<!------------------------ [chat-side]------------------------------------->
		<jsp:include page="/WEB-INF/views/chat/chat-side.jsp" />
		<!------------------------ [chat-side]------------------------------------->

		<!------------------------ content 시작 ------------------------------------->
		<div class="content">


			<!-- heartList가 not null -->
			<c:if test="${not empty heartList }">
				<!-- 찜한 서비스 시작-->
				<div class="content-heart">
					<div id="content-title">내가 찜한 서비스</div>
					<div id="heart-list">
						<ul>
							<c:forEach items="${heartList }" var="s">
								<!-- 삭제된 서비스는 안보이게 -->
								<c:if test="${s.deleteStatus eq 'n'.charAt(0)}">
									<li class="list">
										<div class="list-wrap">
											<div id="service-preview">

												<p id="name">[${s.brandName}] ${s.STitle }</p>
												<p id="preview">
													제공자:
													<c:if test="${empty s.MId }">탈퇴한 회원</c:if>
													<c:if test="${not empty s.MId }">${s.MId }</c:if>
												</p>
												<%-- <p id="preview">내용: ${s.SContent}</p> --%>
												<p id="preview">가격: ${s.SPrice } 평점: ${s.SRate }</p>
												<div style="float: right;">
													<c:if test="${not empty s.MId }">
														<a class="startChat navy" href="#"
															onclick="startChat('${s.SNo }','${sessionScope.loginMember.MId }','${s.MId}','${sessionScope.loginMember.MNo }','${sessionScope.loginMember.MGrade}');">문의하기</a>
													</c:if>
													<a class="deleteFavorite orange" href="#"
														onclick="deleteFavorite('${s.SNo }','${sessionScope.loginMember.MNo }');">삭제하기</a>
												</div>
											</div>
										</div>
									</li>
								</c:if>
								<!-- 삭제된 서비스는 안보이게 -->
							</c:forEach>
						</ul>
					</div>
				</div>
			</c:if>
			<!-- heartList가 not null -->

			<!-- heartList가 null -->
			<c:if test="${empty heartList}">
				<div class="empty-page">
					<div id="content-title">내가 찜한 서비스</div>
					<div id="empty-content">
						<br> <br> <br> <br> <br> <br> <img
							src="/img/icon/exclamation_black.png">
						<h3>
							아직 찜한 서비스가 <br>없습니다!
						</h3>
						<a href="#" class="goMain"><u>나에게 맞는 서비스 검색 ></u></a>
					</div>
				</div>
			</c:if>
			<!-- heartList가 null -->

			<!-- 찜한 서비스 끝-->
		</div>
		<!------------------------ content 끝 ------------------------------------->

	</div>
	<!-- chat-wrap 끝-->

	<script>
		function deleteFavorite(sNo,mNo) {
			$.ajax({
				url : "/deleteHeart.do",
				type : "get",
				data : {
					sNo : sNo,
					mNo : mNo
				},
				success : function(data) {
					location.reload();
				},
				error : function() {
					console.log("오류");
				}
			});
		}

		$(".goMain").click(function() {
			opener.parent.location.href = "/";
			window.close();
		});

		function startChat(sNo, userId, freeId, mNo, mGrade) {
			$.ajax({
				url : "/makeRoom.do",
				type : "post",
				async : false,
				data : {
					sNo : sNo,
					userId : userId,
					freeId : freeId,
					mNo : mNo,
					mGrade : mGrade
				},
				success : function(data) {
					location.href = "/enterRoom.do?cNo=-1&sNo=" + sNo
							+ "&myId=" + userId + "&yourId=" + freeId
							+ "&mGrade=" + mGrade
				},
				error : function() {

				}
			});
		}
	</script>
</body>
</html>