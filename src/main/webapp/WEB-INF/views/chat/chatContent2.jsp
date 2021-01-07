<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 문의하기</title>
<style>
.messages .time {
	text-align: center;
	background-color: #FF8F3F;
	color: white;
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
					<span>${yourId } - ${service.STitle}</span>
					<div id="option-box">
						<a href="">대화 나가기</a><br> <a href="/quotationFrm.do">견적서
							작성</a>
					</div>
				</div>

				<div class="messages">

					<p class="sent">
						번역 문의드려요 <br> <span class="chat-time">오후 03:40</span>
					</p>
					<p class="replies">
						네 안녕하세요 <br> <span class="chat-time">오후 03:40</span>
					</p>

					<!-- 이전 대화내용 db에서 불러오기 -->

				</div>

				<div id="message-input">
					<div class="wrap">
						<!-- 메세지 db저장 -->
						<!--  <form action="insertChat.do" method="post">-->
						<input type="hidden" name="cNo" value="${roomNo }"> <input
							type="hidden" name="myId" value="${sessionScope.loginMember.MId}">
						<input type="hidden" name="time" value="time">
						<textarea class="message" name="content"></textarea>

						<button class="submit" onclick="sendMsg();">전송</button>
						<!-- </form> -->
					</div>
				</div>

			</div>
			<!-- 문의내용 끝-->

		</div>
		<!------------------------ content 끝 ------------------------------------->

	</div>
	<!-- chat-wrap 끝-->

	<script>
		var ws;
		//웹소켓 연결 설정
		ws = new WebSocket("ws://127.0.0.1/chat.do");
		ws.onopen = onOpen; //웹소켓 연결 성공 시 진행할 함수
		ws.onmessage = onMessage; //서버에서 메세지가 왔을 때 수행할 함수
		ws.onclose = onClose; //웹소켓 연결이 끊어졌을 때 동작할 함수

		function onOpen() {
			console.log("서버 접속 완료");
			var msg = {
				type : "register",
				data : "${sessionScope.loginMember.MId}"//"${sessionScope.m.memberId}"
			}
			//웹소켓은 데이터를 String으로 주고받음
			//위의 JSON을 String형으로 바꿔서 웹소켓에 전송
			ws.send(JSON.stringify(msg));

			// 접속했을때 날짜 
			$(".messages").append(
					"<p class='time'>" + year + "년 " + month + "월 " + day
							+ "일 " + "</span></p>");

		}

		//e -> textMessage 타입
		function onMessage(e) {
			$(".messages").append(e.data);
		}

		function onClose() {
			console.log("서버 접속 종료");
		}

		var date = new Date(); //접속시점 시간
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var day = date.getDate();

		// 엔터 누르면 전송 
		$(".message").keyup(function(e) {
			if (e.keyCode == 13)
				sendMsg();
		});

		function sendMsg() {

			/* 현재 시간 구하기 */
			var hour = date.getHours();
			var minute = date.getMinutes();
			var ampm;
			if (hour < 13) {
				ampm = "오전 ";
				hour = hour;
				if (hour == 12) {
					ampm = "오후 ";
					hour = hour;
				}
			} else {
				ampm = "오후 ";
				hour = (hour - 12);
			}

			var sendMsg = $(".message").val();
			if (sendMsg != '') {
				var sendData = "<p class='replies'>" + sendMsg
						+ "<br><span class='chat-time'>" + ampm + hour + ":"
						+ minute + "</span></p>";
				var msg = {
					type : "chat",
					data : sendData
				}
				ws.send(JSON.stringify(msg));
				//내가 보낸 메세지는 바로 출력
				$(".messages").append(
						"<p class='sent'>" + sendMsg
								+ "<br><span class='chat-time'>" + ampm + hour
								+ ":" + minute + "</span></p>");
				/* 	$(".message").val('');  */
				$(".messages").scrollTop($(".messages")[0].scrollHeight);
			}
		}
	</script>

</body>
</html>