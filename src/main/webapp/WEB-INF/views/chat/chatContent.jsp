<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 문의하기</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
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
						<a href="/deleteChat.do?cNo=${roomNo}&mId=${sessionScope.loginMember.MId}">대화 나가기</a><br> <a href="/quotationFrm.do">견적서
							작성</a>
					</div>
				</div>

				<div class="messages">
					<!-- 이전 대화내용 db에서 불러오기 -->
					<p class="sent">
						번역 문의드려요 <br> <span class="chat-time">오후 03:40</span>
					</p>
					<p class="replies">
						네 안녕하세요 <br> <span class="chat-time">오후 03:40</span>
					</p>
				</div>

				<div id="message-input">
					<div class="wrap">
						<textarea class="message"></textarea>
						<button class="submit"
							onclick="sendMsg('${sessionScope.loginMember.MId}');">전송</button>
					</div>
				</div>

			</div>
			<!-- 문의내용 끝-->

		</div>
		<!------------------------ content 끝 ------------------------------------->

	</div>
	<!-- chat-wrap 끝-->

	<script>

		function sendMsg(myId) {
			// 현재 시간 구하기
			var date = new Date();
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			var hour = date.getHours();
			var minute = date.getMinutes();
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

			var roomNo = ${roomNo}; //방번호
			roomNo = Number(roomNo);
			var time = date; //보낸 시간
			var content = $(".message").val(); //메세지 내용

			console.log("방번호: " + roomNo);
			console.log("보내는사람: " + myId);
			console.log("내용: " + content);
			console.log("년: " + year);
			console.log("월: " + month);
			console.log("일: " + day);
			console.log("시간: " + ampm + hour);
			console.log("분: " + minute);

			if (content != "") {
				$.ajax({
					url : "/insertChat.do",
					type : "post",
					async : false,
					data : {
						roomNo : roomNo,
						myId : myId,
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
		
	</script>

</body>
</html>