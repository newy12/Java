<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시(관리자) :: 메세지보내기(${member.MId })</title>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180"
	href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="favicon_io/favicon-16x16.png">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
</head>
<style>
@font-face {
	font-family: 'Arita-dotum-Medium';
	src:url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Arita-dotum-Medium.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

* {
	font-family: 'Arita-dotum-Medium';
}

.wrap {
	text-align: center;
}

#input {
	border-radius: 5px;
	background-color: #314C83;
	width: 250px;
	height: 100px;
	margin: 0 auto;
	padding: 10px;
	margin: 0 auto;
	margin-top: 10px;
	margin-bottom: 10px;
}

textarea {
	border: none;
	width: 80%;
	height: 75%;
	padding: 7px;
	font-size: 0.9em;
	color: #32465a;
	resize: none;
	overflow-y: scroll;
	margin: 0 auto;
}

textarea:focus {
	outline: none;
}

.btn {
	border: 2px solid #314C83;
	border-radius: 30px;
	width: 80px;
	height: 30px;
	font-size: 15px;
	border: 2px solid #314C83;
}

.submit {
	background-color: #314C83;
	color: white;
}

.close {
	background-color: white;
	color: #314C83;
}
</style>
<body>
	<div class="wrap">
		<div id="title">
			<img src="/img/logo/logo-chat_navy.png" width=130px;><br>
			회원 <b>${member.MId }</b>에게 알림
			</h1>
		</div>

		<div id="input">
			<textarea class="message"></textarea>
		</div>

		<div id="button">
			<button class="btn submit" onclick="sendMsg();">전송</button>
			<button class="btn close" onclick="close();">닫기</button>
		</div>
	</div>

	<script>
		function close() {
			window.close();
		}
		
		function sendMsg() {
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

			var date = year + "년 " + month + "월 " + day + "일";
			var time = ampm + hour + ":" + minute; //보낸 시간
			var content = $(".message").val(); //메세지 내용
			var mNo = "${member.MNo}";
			mNo = Number(mNo);
			var userId = "${member.MId }";
			var freeId = "admin";

			console.log(date);
			console.log(time);
			console.log(content);
			console.log(mNo);
			console.log(userId);
			console.log(freeId);

			//admin과 회원간의 채팅방 생성
			$.ajax({
				url : "/makeRoom.do",
				type : "post",
				async : false,
				data : {
					sNo : 0,
					userId : userId,
					freeId : freeId,
					mNo : mNo
				},
				success : function(data) {
					var cNo = data.cNo; //방번호 
					cNo = Number(cNo);
					console.log("방만들기 성공");
					console.log(freeId);
					console.log(date);
					console.log(time);
					console.log(content);
					location.href = "/insertChat.do?cNo=" + cNo + "+&myId="
							+ freeId + "&date=" + date + "&time=" + time
							+ "&content=" + content;
					console.log("메세지보내기 성공");
					opener.parent.location.reload();
					
					 /* window.close();  */

				},
				error : function() {

				}
			});

		}
	</script>
</body>
</html>