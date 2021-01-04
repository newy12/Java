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

			<!-- 문의내용-->
			<div class="content-chat">

				<div id="chat-profile">
					<span>채팅상대 - 서비스정보</span>
					<div id="option-box">
						<a href="">대화 나가기</a><br> <a href="/quotationFrm.do">견적서 작성</a>
					</div>
				</div>

				<div class="messages">

					<p class="sent">
						번역 문의드려요 <br> <span class="chat-time">오후 03:40</span>
					</p>
					<p class="replies">
						네 안녕하세요 <br> <span class="chat-time">오후 03:40</span>
					</p>
					<p class="sent">
						분야에 상관없이 번역 가능한가요? 전문용어가 많이 나오는데 잘 번역 해주시겠죠?? <br> <span
							class="chat-time">오후 03:40</span>
					</p>

				</div>

				<div id="message-input">
					<div class="wrap">
						<textarea class="message"></textarea>
						<button class="submit"
							onclick="sendMsg('${sessionScope.m.memberId}');">전송</button>
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
				data : "memberId"//"${sessionScope.m.memberId}"
			}
			//웹소켓은 데이터를 String으로 주고받음
			//위의 JSON을 String형으로 바꿔서 웹소켓에 전송
			ws.send(JSON.stringify(msg));
		}

		//e -> textMessage 타입
		function onMessage(e) {
			$(".messages").append(e.data);
		}

		function onClose() {
			console.log("서버 접속 종료");
		}

		/* 엔터 누르면 전송 */
		$(".message").keyup(function(e) {
			if (e.keyCode == 13)
				sendMsg();
		});

		function sendMsg(memberId) {
			/* 현재 날짜,시간 구하기 */
			var sendDay = new Date(); //전송시점 시간
			var sendM = sendDay.getMonth() + 1;
			var sendD = sendDay.getDate();
			var hour = sendDay.getHours();
			var minute = sendDay.getMinutes();
			var ampm;
			if (hour < 13) {
				ampm = "오전 ";
				hour = "0" + hour;
			} else {
				ampm = "오후 "
				hour = "0" + (hour - 12);
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
				/* 전 메세지의 보낸날짜와 다르면 날짜 출력 */
				$(".messages").append(
						"<p class='sent'>" + sendMsg
								+ "<br><span class='chat-time'>" + ampm + hour
								+ ":" + minute + "</span></p>");
				$(".message").val('');
				$(".messages").scrollTop($(".messages")[0].scrollHeight);
			}
		}
	</script>

</body>
</html>