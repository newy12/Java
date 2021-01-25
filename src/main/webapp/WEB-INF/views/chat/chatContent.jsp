<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 문의하기</title>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180"
	href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="favicon_io/favicon-16x16.png">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<style>
.messages .date {
	border: 1px solid orange;
	border-radius: 30px;
	width: 150px;
	align-self: center;
	text-align: center;
	padding-top: 4px;
	padding-bottom: 4px;
	margin-top: 3px;
	margin-bottom: 3px;
}

.messages .read {
	width: 5px;
	height: 5px;
	background-color: orange;
	border-radius: 20px;
	display: inline-block;
	opacity: 1;
}

.messages .adminBox {
	text-align: center;
	align-self: center;
}

.adminBox .adminMsg {
	padding: 10px;
	border: 2px solid #6A85C3;
	border-radius: 10px;
	width: 80%;
	margin: 8px;
}

#message-input .wrap .bigBtn {
	width: 100%;
	height: 100%;
	background-color: orange;
	display: block;
	text-align: center;
	line-height: 60px;
	color: white;
	font-size: 20px;
}

.bigBtn {
	cursor: pointer;
}

.service-title {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	cursor: pointer;
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
			<div class="content-chat">

				<!-- 관리자 알림일때 -->
				<c:if test="${service.SNo eq 0 }">
					<!-- 알림 없을때 -->
					<c:if test="${empty content}">
						<div class="empty-page">
							<div id="content-title">19시 :: 알림</div>
							<div id="empty-content">
								<br> <br> <br> <br> <br> <br> <img
									src="/img/icon/exclamation_black.png">
								<h3>
									알림이 <br>없습니다!
								</h3>
								<a href="#"><u>관리자에게 문의하기 ></u></a>
							</div>
						</div>
					</c:if>

					<!-- 알림 있을때 -->
					<c:if test="${not empty content}">
						<!-- 메세지내용 -->
						<div id="content-title">19시 :: 알림</div>
						<div class="messages">
							<!-- 이전 대화내용 db에서 불러오기 -->
							<c:forEach items="${content }" var="message" varStatus="status">

								<!-- 관리자와의 채팅방 -->
								<c:if test="${service.SNo eq 0 }">
									<c:if test="${message.CContent ne '문의를 시작합니다!' }">
										<div class="adminBox">
											<br>
											<c:if test="${status.index eq 0 }">
												<div class="date"
													style="margin: 0 auto; margin-bottom: 10px;">${message.CDate }</div>
											</c:if>
											<c:if test="${status.index ne 0 }">
												<!-- 이전 메세지의 보낸날짜와 다르면 출력 -->
												<c:if
													test="${message.CDate ne content.get(status.index-1).CDate }">
													<br>
													<div class="date"
														style="margin: 0 auto; margin-bottom: 10px;">${message.CDate }</div>
												</c:if>
											</c:if>

											<p class="adminMsg" style="margin: 0 auto;">${message.CContent }</p>
										</div>
									</c:if>
								</c:if>
							</c:forEach>
						</div>
						<!-- 하단-->
						<div id="message-input">
							<div class="wrap">
								<!-- 알림창 -->
								<!-- 문의사항페이지로 이동 -->
								<a class="bigBtn qna">관리자에게 문의하기</a>
							</div>
						</div>
					</c:if>


				</c:if>
				<!-- 관리자 알림일때 -->

				<!-- 일반 채팅일때 -->
				<c:if test="${service.SNo ne 0 }">
					<div id="chat-profile">
						<div id="profile-info">
							<div class="service-title">
								<u>${service.STitle}</u>
							</div>
							<div>
							<c:if test="${freeId ne loginMember.MId }"> ${freeId }</c:if>
							<c:if test="${freeId eq loginMember.MId }"> ${userId }</c:if>
							
							<c:if test="${sessionScope.loginMember.MGrade==2 }">
									<c:if test="${not empty black }">
										<img src="/img/icon/warning.png" width="10px;">
									</c:if>
								</c:if>
							</div>
						</div>

						<div id="option-box">
							<!-- 아직 견적서 작성 전 -->
							<c:if test="${empty status }">

								<c:if test="${sessionScope.loginMember.MGrade==2 }">
								<!-- 의뢰인이 먼저 시작한 채팅 -->
									<c:if test="${freeId ne loginMember.MId }">
										<a href="/quotationFrm.do?sNo=${service.SNo}&sTitle=${service.STitle }&userId=${freeId}&freeId=${loginMember.MId}"
										onClick="window.open(this.href, '', 'width=800, height=800, left=1000, scrollbars=no,location=no, resizable=no'); return false;"> 견적서 작성</a><br>
									</c:if>
								<!-- 프리랜서가 먼저 시작한 채팅 -->
									<c:if test="${freeId eq loginMember.MId }">
										<a href="/quotationFrm.do?sNo=${service.SNo}&sTitle=${service.STitle }&userId=${userId}&freeId=${loginMember.MId}"
										onClick="window.open(this.href, '', 'width=800, height=800, left=1000, scrollbars=no,location=no, resizable=no'); return false;"> 견적서 작성</a><br>
									</c:if>
								</c:if>
								
								
								<a href="#" onclick="deleteChat();">대화 나가기</a>
							</c:if>
							<!-- 견적서 작성&결제 전 -->
							<c:if test="${status eq 0 }">
								<c:if test="${sessionScope.loginMember.MGrade==2 }"> 결제 대기 중 </c:if>
								<br>
								<a href="#" onclick="deleteChat();">대화 나가기</a>
							</c:if>
							<!-- 결제 완료&진행 중 -->
							<c:if test="${status eq 1 }">작업 진행중</c:if>
							<!-- 작업완료 -->
							<c:if test="${status eq 2 }">
							작업 완료<br>
								<a href="#" onclick="deleteChat();">대화 나가기</a>
							</c:if>
						</div>
					</div>

					<!-- 메세지내용 -->
					<div class="messages">

						<!-- 이전 대화내용 db에서 불러오기 -->
						<c:forEach items="${content }" var="message" varStatus="status">
							<!-- 일반 문의 채팅방 -->
							<c:if test="${status.index ne 0 }">
								<!-- 이전 메세지의 보낸날짜와 다르면 출력 -->
								<c:if
									test="${message.CDate ne content.get(status.index-1).CDate }">
									<br>
									<div class="date">${message.CDate }</div>
								</c:if>

								<!-- 받은 메세지 -->
								<c:if test="${message.MId ne sessionScope.loginMember.MId }">
									<p class="replies">
										${message.CContent } <br> <span class="chat-time">${message.CTime }</span>

									</p>
								</c:if>
								<!-- 내가 보낸 메세지 -->
								<c:if test="${message.MId eq sessionScope.loginMember.MId }">
									<p class="sent">
										${message.CContent }
										<c:if test="${message.readStatus eq 1}">
											<a href="#" onclick="deleteMsg('${message.ccNo }')">× </a>
										</c:if>
										<br> <span class="chat-time"> <c:if
												test="${message.readStatus eq 1}">
												<span class="read"></span>
											</c:if> ${message.CTime }
										</span>
									</p>
								</c:if>
							</c:if>
						</c:forEach>
					</div>

					<!-- 하단-->
					<div id="message-input">
						<div class="wrap">

							<!-- 일반채팅창 -->
							<c:if test="${service.SNo ne 0 }">
								<textarea class="message" autofocus="autofocus"></textarea>
								<button class="submit"
									onclick="sendMsg('${sessionScope.loginMember.MId}');">전송</button>
							</c:if>
							<!-- 알림창 -->
							<c:if test="${service.SNo eq 0 }">
								<!-- 문의사항페이지로 이동 -->
								<a class="bigBtn qna">관리자에게 문의하기</a>
							</c:if>
						</div>
					</div>
				</c:if>
				<!-- 일반 채팅일때 -->

			</div>
			<!-- 문의내용 끝-->



		</div>
		<!------------------------ content 끝 ------------------------------------->

	</div>
	<!--전체 wrap-->

	<script>
		$(function() {
			$(".messages").scrollTop($(".messages")[0].scrollHeight);

		});

		function deleteMsg(ccNo) {
			console.log("delete!");
			$.ajax({
				url : "/deleteMsg.do",
				type : "post",
				async : false,
				data : {
					ccNo : ccNo
				},
				success : function(data) {
					location.reload();
				}
			});
		}

		function sendMsg(myId, cNo) {
			
			/* 삭제된 서비스면 alert */
			var deleted = "${service.deleteStatus }";
			if (deleted == 'y'.charAt(0)) {
				alert("메세지를 보낼수 없습니다!");
			} else {
				
			// 현재 시간 구하기
			var now = new Date();
			var year = now.getFullYear();
			var month = now.getMonth() + 1;
			if (month < 10) {
				month = "0" + month;
			}
			var day = now.getDate();
			if (day < 10) {
				day = "0" + day;
			}
			var hour = now.getHours();
			var minute = now.getMinutes();
			if (minute < 10) {
				minute = "0" + minute;
			}
			var ampm;
			if (hour < 12) {
				ampm = "오전 ";
				if (hour < 10) {
					hour = "0" + hour;
				}
			} else {
				ampm = "오후 ";
				if (hour > 12) {
					hour = hour - 12;
					if (hour < 10) {
						hour = "0" + hour;
					}
				}
			}

			var cNo = ${cNo}; //방번호 
			cNo = Number(cNo);
			var date = year + "년 " + month + "월 " + day + "일";
			var time = ampm + hour + ":" + minute; //보낸 시간
			var content = $(".message").val(); //메세지 내용

			console.log("방번호: " + cNo);
			console.log("보내는사람: " + myId);
			console.log("날짜: " + date);
			console.log("시간: " + time);

			if (content != "") {
				$.ajax({
					url : "/insertChat.do",
					type : "post",
					async : false,
					data : {
						cNo : cNo,
						myId : myId,
						date : date,
						time : time,
						content : content
					},
					success : function(data) {
						$(".messages").append(
								"<p class='sent'>" + content
										+ "<br><span class='chat-time'>" + ampm
										+ hour + ":" + minute + "</span></p>");

						$(".message").val('');
						/* $(".messages").scrollTop($(".messages")[0].scrollHeight); */
						location.reload();
					}
				});
			}
			}
		}

		// 엔터 누르면 전송 
		$(".message").keyup(function(e) {
			var cNo = ${cNo}; //방번호 
			cNo = Number(cNo);
			if (e.keyCode == 13)
				sendMsg('${sessionScope.loginMember.MId}', cNo);
		});

		function deleteChat() {
			var cNo = ${cNo}; //방번호 
			cNo = Number(cNo);
			var mGrade = ${loginMember.MGrade};
			var mId = "${loginMember.MId}";
			check = confirm("대화내용이 모두 삭제됩니다");
			if (check) {

				$.ajax({
					url : "/deleteChat.do",
					type : "post",
					async : false,
					data : {cNo : cNo},
					success : function(data) {
						console.log("삭제성공");
						location.href = "/chatList.do?mGrade=" + mGrade
								+ "&mId=" + mId
					}
				});
			}
		}

		$(".service-title").click(
				function() {
					/* 삭제된 서비스면 alert */
					var deleted = "${service.deleteStatus }";
					if (deleted == 'y'.charAt(0)) {
						alert("삭제된 서비스입니다!");
					} else {
						console.log("click!");
						var sNo = ${service.SNo};
						sNo = Number(sNo);
						opener.parent.location.href = "/serviceView.do?sNo="
								+ sNo + "&reqPage=1&mNo=" + ${loginMember.MNo};
					}
				});

		$(".qna").click(function() {
			console.log("click!!");
			opener.parent.location.href = "/qna.do?page=1";
		});
	</script>

</body>
</html>