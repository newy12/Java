<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 문의하기</title>
<style>
.messages .date {
	border: 1px solid orange;
	border-radius: 30px;
	width: 150px;
	align-self:center;
	text-align: center;
	padding-top:4px;
	padding-bottom:4px;
	margin-top:3px;
	margin-bottom:3px;
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
	align-self:center;
}

.adminBox .adminMsg {
	padding: 10px; 
	border-radius : 40px;
	border: 1px solid #FF8F3F;
	color: #282828;
	border-radius: 40px;
	margin:8px;
}

.button {
	width:100%;
	height:100%;
	background-color:orange;
	display:block;
	text-align:center;
	line-height:60px;
	color:white;
	font-size:20px;
	
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

			<!-- 문의내용-->
			<div class="content-chat">
				<div id="chat-profile">
					<c:if test="${service.SNo eq 0 }">
					19시 :: 알림
					</c:if>
					<c:if test="${service.SNo ne 0 }">
						<span>${freeId } - ${service.STitle}</span>
					</c:if>
					<div id="option-box">
						<a href="#" onclick="deleteChat();">대화 나가기</a><br>
						<c:if test="${sessionScope.loginMember.MGrade==2 }">
							<a
								href="/quotationFrm.do?sTitle=${service.STitle }&userId=${userId}&freeId=${freeId}"
								onClick="window.open(this.href, '', 'width=800, height=800, left=1000, scrollbars=no,location=no, resizable=no'); return false;">견적서
								작성</a>
						</c:if>
					</div>
				</div>

				<div class="messages">
					<!-- 이전 대화내용 db에서 불러오기 -->
					<c:forEach items="${content }" var="message" varStatus="status">
						<!-- 관리자와의 채팅방 -->
						<div class="adminBox">
							<c:if test="${service.SNo eq 0 }">
								<c:if test="${status.index ne 0 }">
									<c:if test="${message.CDate ne content.get(status.index-1).CDate }">
									<p class="date">${message.CDate }</p>
								</c:if>
									<p class="adminMsg">${message.CContent }</p>
								</c:if>
							</c:if>
						</div>
						<!-- 일반 문의 채팅방 -->
						<c:if test="${service.SNo ne 0 }">
							<c:if test="${status.index ne 0 }">
								<!-- 이전 메세지의 보낸날짜와 다르면 출력 -->
								<c:if
									test="${message.CDate ne content.get(status.index-1).CDate }">
									<div class="date">${message.CDate }</div>
								</c:if>

								<c:if test="${message.MId eq freeId }">
									<p class="replies">
										${message.CContent } <br> <span class="chat-time">${message.CTime }</span>

									</p>
								</c:if>
								<!-- 내가 보낸 메세지 -->
								<c:if test="${message.MId ne freeId }">
									<p class="sent">
										${message.CContent } <br> <span class="chat-time">${message.CTime }
											<c:if test="${message.readStatus eq 1}">
												<span class="read"></span>
											</c:if>
										</span>

									</p>
								</c:if>
							</c:if>
						</c:if>
					</c:forEach>

				</div>
				<!-- 일반 채팅 -->
				<c:if test="${service.SNo ne 0 }">
					<div id="message-input">
						<div class="wrap">
							<textarea class="message"></textarea>
							<button class="submit"
								onclick="sendMsg('${sessionScope.loginMember.MId}');">전송</button>
						</div>
					</div>
				</c:if>
				<!-- 관리자 -->
				<c:if test="${service.SNo eq 0 }">
					<div id="message-input">
						<div class="wrap">
							<!-- 문의사항페이지로 이동 -->
							<a class="button" href="">관리자에게 문의하기 </a>
						</div>
					</div>
				</c:if>
			</div>
			<!-- 문의내용 끝-->

		</div>
		<!------------------------ content 끝 ------------------------------------->

	</div>
	<!-- chat-wrap 끝-->

	<script>
		function sendMsg(myId, cNo) {
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
						$(".messages")
								.scrollTop($(".messages")[0].scrollHeight);
					}
				});
			}
		}

		/* // 엔터 누르면 전송 
		$(".message").keyup(function(e) {
			if (e.keyCode == 13)
				sendMsg();
		}); */

		function deleteChat() {
			check = confirm("대화내용이 모두 삭제됩니다")
			if (check) {
				location.href = "/deleteChat.do?cNo=" + ${cNo}+"&myId=" + ${sessionScope.loginMember.MId}
			}
		}
	</script>

</body>
</html>