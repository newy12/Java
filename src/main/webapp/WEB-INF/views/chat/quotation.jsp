<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 견적서</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180"
	href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="favicon_io/favicon-16x16.png">
</head>
<style>
@font-face {
	font-family: 'Arita-dotum-Medium';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Arita-dotum-Medium.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

* {
	font-family: 'Arita-dotum-Medium';
}
.q-wrap {
	text-align: center;
	margin: 10px auto;
	width: 80%;
	height: 80%;
}

#title {
	font-size: 50px;
	margin: 0 auto;
}

.table {
	background-color: #314C83;
	margin-top:10px;
	margin-bottom:10px;
	padding:10px;
	width: 100%;
	border-radius:10px;
}

table {
	margin: 0 auto;
	background-color: white;
	border-radius:10px;
}

th {
	width: 150px;
}

tr, th {
	padding: 20px;
	padding-top: 10px;
	padding-bottom: 10px;
	border-bottom:0.5px solid lightgray;
}

td {
	width: 700px;
	font-size: 15px;
	border-bottom:0.5px solid lightgray;
	border-left:0.5px solid lightgray;
}

.btn {
	text-align: center;
	width: 50px;
	border: 3px solid #314C83;
	background-color: transparent;
	color: #314C83;
	border-radius: 5px;
	height: 35px;
	font-size: 10.5pt;
	font-weight: bold;
}

.color {
	background-color: #314C83;
	color: white;
}
</style>
<body>
	<div class="q-wrap">
		<img src="img/logo/logo_white.png">
		<p id="title">견적서</p>
		<div class="table">
			<table>
				<tr>
					<th>서비스명</th>
					<td>${sTitle }</td>
				</tr>
				<tr>
					<th>제공자</th>
					<td>${freeId }</td>
				</tr>
				<tr>
					<th>의뢰인</th>
					<td>${userId }</td>
				</tr>
				<tr>
					<th>기간</th>
					<td><input type="date" id="d1"> ~ <input type="date"
						id="d2"></td>
				</tr>
				<tr>
					<th>금액</th>
					<td><input type="text" id="price" maxlength="8"
						oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
						원</td>
				</tr>
			</table>
		</div>
		<h4 id="date"></h4>
		<h3>
			위 내용은 수정할 수 없으며,<br> 제출 시 의뢰인에게 전달됩니다.
		</h3>
		<button class="btn color" type="submit" id="send">제출</button>
		<button class="btn empty" onclick="window.close();">닫기</button>
	</div>

	<script>
	
	$(function(){
		todayIs();
	});
	function todayIs() {
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
		date = year + '년 ' + month + '월 ' + day + '일 ';
		document.getElementById("date").innerHTML = date;
	}

		
		$("#send").click(function() {
			//견적서 작성되었다고 알림, trade테이블에 insert, 작업수 1 늘려주기
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
			if (minute < 10 & minute > 0) {
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
			
			//기간,금액이 모두 작성되었는지 확인
			var d1=$("#d1").val();
			var d2=$("#d2").val();
			var price=$("#price").val();
			
			if(d1=='' || d2==''||price==''){
				alert("기간과 금액 모두 작성해주세요");
				return;
			}
			
			//시작일 종료일 비교	
			var n1=d1.split("-");
			n1=n1[0]+n1[1]+n1[2];
			n1=Number(n1);
			
			n2=d2.split("-");
			n2=n2[0]+n2[1]+n2[2];
			n2=Number(n2);
			
			if(n1>n2){
				alert("종료일은 시작일 이후로 설정해주세요.");
				return;
			}
			
			//시작일과 오늘 날짜 비교
			var today=year+month+day;
			if(n1<today){
				alert("시작일은 오늘 이후로 설정해주세요.");
				return;
			}

			var date = year + "년 " + month + "월 " + day + "일";
			var time = ampm + hour + ":" + minute; //보낸 시간
			var mNo = "${mNo}";
			mNo = Number(mNo);
			var userId = "${userId }";
			var freeId = "admin";
			var sTitle="${sTitle }";
			var freelancer="${freeId }";
			//선택한 이유
			var content ="서비스 <b> ["+sTitle+"("+freelancer+")]</b>의 견적서가 작성되었습니다.<br>마이페이지에서 확인 후 <b>결제</b>해주세요.";
			
			//admin과 회원간의 채팅방 생성
			//메세지보내기
			//trade테이블 insert
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
					console.log("111111");
					//방만들기 성공했을때
					var cNo = data.cNo; //방번호 
					cNo = Number(cNo);
					console.log(freeId);
					console.log(date);
					console.log(time);
					console.log(content);
					//알림
					$.ajax({
						url : "/insertChat.do",
						type : "post",
						async : false,
						data : {
							cNo:cNo,
							myId:freeId,
							date:date,
							time:time,
							content:content
						},
						success : function(data) {
							//trade테이블 insert
							console.log("22222222");
							var sNo = "${sNo}";
							sNo = Number(sNo);
										
						 	$.ajax({
								url : "/startTrade.do",
								type : "post",
								async : false,
								data : {
									sNo:sNo,
									mNo:mNo,
									price:price,
									start:d1,
									end:d2
								},
								success : function(data) {
									
									opener.parent.location.reload();
									window.close();   
								/* 	//작업수 1 증가
									$.ajax({
										url : "/updateCount.do",
										type : "post",
										async : false,
										data : {
											sNo:sNo,
										},
										success : function(data) {
											console.log("working_count update");
											opener.parent.location.reload();
											window.close();    
										},
										error : function() {
											console.log("update실패");
										}
									});   */
	   
								},
								error : function() {

								}
							});  
						},
						error : function() {

						}
					}); 
				},
				error : function() {

				}
			}); 
			

		});
	</script>
</body>
</html>