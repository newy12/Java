<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시(관리자) :: 메세지보내기(${member.MId })</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
</head>
<body>
	<h1>${member.MId }에게 메세지 보내기</h1>
	<textarea class="message"></textarea>
	<button class="submit" onclick="sendMsg();">전송</button>
	<button onclick="sendMsg();">닫기</button>
	<script>
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
			var mNo="${member.MNo}";
			mNo=Number(mNo);
			var userId="${member.MId }";
			var freeId="admin";
			
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
					userId :userId,
					freeId : freeId,
					mNo : mNo
				},
				success : function(data) {
					var cNo =data.cNo; //방번호 
					cNo = Number(cNo);
					console.log("방만들기 성공");
					console.log(freeId);
					console.log(date);
					console.log(time);
					console.log(content);
					location.href = "/insertChat.do?cNo="+cNo+"+&myId="+freeId+"&date="+date+"&time="+time+"&content="+content;   
					console.log("메세지보내기 성공");
					opener.parent.location.reload();  
					/* 창닫기하면 메세지저장x */
					/* window.close(); */
				 	
				},
				error : function() {

				}
			});

			
		}
	</script>
</body>
</html>