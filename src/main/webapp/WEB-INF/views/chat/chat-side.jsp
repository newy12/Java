<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.js"></script>

<style>
div {
	/*	border: 0.5px solid gray;*/
	box-sizing: border-box;
}

a {
	text-decoration: none;
}

ul {
	list-style: none;
	padding: 0;
}

#chat-wrap {
	overflow: hidden;
	/*width: 100%;*/
	min-width: 500px;
	max-width: 500px;
	height: 100vh;
	min-height: 600px;
	max-height: 600px;
}

/*---------------------- side css 시작 -------------------------*/
#side {
	overflow: hidden;
	float: left;
	width: 25%;
	height: 100%;
	background: #2c3e50;
	color: #f5f5f5;
	position: relative;
}

#side a {
	color: white;
}

#side-profile {
	width: 100%;
	margin: 10px auto;
	text-align: center;
}

#expanded {
	display: block;
	display: none;
}

.switch {
	font-size: 13px;
	position: absolute;
	transition-duration: 0.5s;
}

.active {
	transform: scaleY(-1);
}

#account {
	padding: 5px;
	border: 0.5px solid white;
	border-radius: 30px;
}

#account li {
	text-align: center;
}

#side-menu ul li {
	text-align: center;
	display: block;
	padding: 10px;
	cursor: pointer;
}

#side-menu ul li:hover {
	background: #435f7a;
	font-weight: bold;
}

#side-bottom {
	position: absolute;
	width: 100%;
	height: 7%;
	bottom: 0;
}

#side-bottom button {
	float: left;
	border: none;
	width: 50%;
	height: 100%;
	padding: 10px 0;
	background: #32465a;
	color: #f5f5f5;
	cursor: pointer;
}

#side-bottom button:focus {
	outline: none;
}

#side-bottom button:nth-child(1) {
	border-right: 1px solid #2c3e50;
}

#side-bottom button:hover {
	background: #435f7a;
}

/*---------------------- side css 끝 -------------------------*/

/*----------------------  content(chatting-list, heart-list) css 시작 -------------------------*/
.content {
	overflow: hidden;
	float: left;
	width: 75%;
	height: 100%;
}

.content-list, .content-heart {
	height: 100%;
}

#content-title {
	width: 100%;
	height: 10%;
	text-align: right;
	padding: 15px;
	font-size: 20px;
	font-weight: bold;
	background-color: #2c3e50;
	color: ivory;
}

#chat-list, #heart-list {
	width: 100%;
	height: 90%;
	overflow-y: scroll;
	overflow-x: hidden;
	border-bottom: 0.5px solid gray;
	border-right: 0.5px solid gray;
}

#heart-list .list {
	padding: 15px;
}

#chat-list::-webkit-scrollbar, #heart-list::-webkit-scrollbar {
	width: 6px;
	/*background: #2c3e50;*/
}

#chat-list::-webkit-scrollbar-thumb, #heart-list::-webkit-scrollbar-thumb
	{
	background-color: #FF8F3F;
}

.list {
	border-bottom: 0.3px solid gray;
	padding-bottom: 10px;
	font-size: 0.9em;
	cursor: pointer;
}

.list:hover {
	background-color: lightgray;
}

.list-wrap {
	width: 100%;
	margin: 0 auto;
	overflow: hidden;
}

.new {
	float: left;
	width: 15px;
	height: 15px;
	border-radius: 50%;
	background: transparent;
	margin-top: 25px;
}

#chat-preview, #service-preview {
	float: left;
	width: 95%;
}

#time {
	float: right;
	font-size: 0.4em;
	font-weight: normal;
}

#name {
	font-weight: bold;
	font-size: 15px;
	margin: 9px;
}

#preview {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	opacity: .5;
	margin: 10px 0 10px 15px;
}

/*----------------------  content(chatting-list, heart-list) css 끝 -------------------------*/

/*----------------------  content(chatting-content) css 시작 -------------------------*/
.content-chat {
	height: 100%;
	width: 100%;
	border-right: 0.5px solid gray;
	border-bottom: 0.5px solid gray;
}

#chat-profile {
	width: 100%;
	height: 10%;
	padding: 15px;
	font-size: 20px;
	font-weight: bold;
	background-color: #2c3e50;
	color: ivory;
	overflow: hidden;
}

#chat-profile a {
	color: white;
}

#option-box {
	float: right;
	font-size: 12px;
}

.messages {
	width: 100%;
	height: 80%;
	overflow-y: scroll;
	overflow-x: hidden;
	font-size: 0.9em;
	display: flex;
	flex-direction: column;
	padding-top: 10px;
	padding-bottom: 5px;
	border-bottom: 0.5px solid gray;
}

.messages::-webkit-scrollbar {
	width: 6px;
	/*background: #2c3e50;*/
}

.messages::-webkit-scrollbar-thumb {
	background-color: #FF8F3F;
}

.messages>p {
	display: inline-block;
	padding: 10px 15px;
	margin-top: 5px;
	margin-bottom: 5px;
	border-radius: 20px;
	max-width: 200px;
	line-height: 110%;
	float: left;
	text-align: justify;
}

.replies {
	background: #435f7a;
	color: #f5f5f5;
	align-self: flex-start;
	margin-left: 10px;
}

.sent {
	background: #f5f5f5;
	align-self: flex-end;
	margin-right: 10px;
}

.chat-time {
	font-size: 0.3em;
	opacity: 0.6;
	float: right;
}

#message-input {
	width: 100%;
	height: 10%;
}

#message-input .wrap {
	width: 100%;
	height: 100%;
}

textarea {
	float: left;
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

textarea::-webkit-scrollbar {
	width: 3px;
	/*background: #2c3e50;*/
}

textarea::-webkit-scrollbar-thumb {
	background-color: #FF8F3F;
}

.submit {
	float: right;
	border: none;
	width: 15%;
	height: 100%;
	cursor: pointer;
	background: #32465a;
	color: #f5f5f5;
}

.submit:hover {
	background: #435f7a;
}

.submit:focus {
	outline: none;
}

/*----------------------  content(chatting-content) css 끝 -------------------------*/

/*----------------------  content(empty-page) css 시작 -------------------------*/
.empty-page {
	height: 100%;
	width: 100%;
	text-align: center;
}

.empty-page a {
	color: gray;
}

#empty-content {
	height: 90%;
	border-bottom: 0.5px solid gray;
	border-right: 0.5px solid gray;
}

/*----------------------  content(empty-page) css 끝 -------------------------*/

/*----------------------  content(heart-list) css 시작 -------------------------*/
.start-chat {
	float: right;
}

/*----------------------  content(heart-list) css 끝 -------------------------*/
</style>

</head>
<!-- 일반고객-프리랜서 전환 -->
<script>
	$(function() {
		$("#account ul").hide();
		$(".expanded").prev().append('<span class="switch"> ▼</span>');
		$(".switch").click(function(event) {
			$(".expanded").slideToggle();
			if ($(this).attr("class") == "switch") {
				$(this).addClass("active");
			} else {
				$(this).removeClass("active");
			}
			event.stopPropagation();
		});
	})
</script>

<body>
	<div id="side">
		<div id="side-profile">
			<div class="wrap">
				<img src="/img/logo/logo-chat_navy.png" width="120px"> <b>${ sessionScope.m.memberName }</b>
				<div>
					<ul id="account">
						<li><a href="#">일반 고객 </a>
							<ul class="expanded">
								<li><a href="#">프리랜서</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="side-menu">
			<ul>
				<li><a href="/chatList.do"><img
						src="/img/icon/chat_white.png" width="45px;"><br>문의</a></li>
				<li><a href="/heartList.do"><img
						src="/img/icon/heart_white.png" width="40px;"><br>찜</a></li>
				<!-- 고객일때 -->
				<!-- 프리랜서일때 <li>서비스</li>-->
			</ul>
		</div>
		<div id="side-bottom">
			<button id="mypageBtn">mypage</button>
			<button id="closeBtn">닫기</button>
		</div>
	</div>
</body>
</html>