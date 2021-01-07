<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>19시 :: 일 구하고 시퍼</title>

	<style>
		.background-screen {
			display: none;
			position: absolute;
			width: 100%;
			height: 100%;
			background-color: black;
			opacity: 0.2;
		}

		.login-form-container {
			display: none;
			position: absolute;
			top: calc(50% - 250px);
			left: calc(50% - 325px);
			width: 650px;
			height: 500px;
			background-color: white;
			border-radius: 5px;
			border: 2px solid #314C83;
			box-shadow: 1px 1px 12px 1px gray;
		}

		.login-form-close {
			text-align: center;
			width: 20px;
			height: 20px;
			float: right;
		}

		.login-form-close:hover {
			cursor: pointer;
		}

		.login-form {
			margin: 0 auto;
			width: 90%;
			height: 100%;
			text-align: center;
		}

		.login-form .login-form-top {
			height: 20%;
			margin: 15px 0;
		}

		.login-form .login-form-top>img {
			height: 100%;
		}

		.login-title {
			font-size: x-large;
			font-weight: bold;
		}

		.login-inputs {}

		.login-inputs input {
			margin: 7px 0;
			width: 300px;
			height: 40px;
			border: 1px solid gray;
			border-radius: 5px;

		}

		.login-inputs input[type=submit] {
			background-color: #314C83;
			color: white;
			border-color: #314C83;
			font-weight: bold;
		}

		.login-inputs a {
			display: block;
			text-decoration: none;
			color: #282828;
			font-weight: bold;
			margin-top: 15px;
		}

		.login-form-center {
			height: 53%;
			border-bottom: 1px solid gray;
		}

		.login-form-bottom {
			height: 27%;
		}

		.login-form-bottom>a {
			margin: 0 auto;
			margin-top: 30px;
			display: block;
			border: 2px solid #314C83;
			border-radius: 5px;
			width: 300px;
			height: 40px;
			line-height: 40px;
			color: #314C83;
			font-weight: bold;
			text-decoration: none;

		}
	</style>
	<!-- favicon -->
	<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
	<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
	<link rel="manifest" href="/site.webmanifest">

</head>

<body>
	<div class="background-screen"></div>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<script>
		$(document).ready(function () {

			//헤더의 로그인 버튼 누르면 로그인 창 show
			$(".header-menu #login").on("click", function () {
				$(".background-screen").show();
				$(".login-form-container").show();
			});
			// 뒷 배경 누르면 ,로그인 창, 뒷 배경 Hide
			$(".background-screen").on("click", function () {
				$(".background-screen").hide();
				$(".login-form-container").hide();
			});
			// 로그인 창 x버튼 누르면 ,로그인 창, 뒷 배경 Hide
			$(".login-form-close").on("click", function () {
				$(".background-screen").hide();
				$(".login-form-container").hide();
			});
		});
	</script>

	<div class="login-form-container">
		<div class="login-form-close">
			<span>x</span>
		</div>
		<div class="login-form">
			<div class="login-form-top">
				<img src="/img/logo/logo_white.png" alt="">
			</div>
			<div class="login-form-center">
				<div class="login-title">
					<span>로그인</span>
				</div>
				<div class="login-inputs">
					<form action="/login.do" method="post">
						<input type="text" name="id" id="id" placeholder="아이디를 입력해주세요"><br>
						<input type="password" name="pw" id="pw" placeholder="비밀번호를 입력해주세요."><br>
						<input type="submit" value="로그인">
						<a href="/forgot_pwd.do">아이디·비밀번호 찾기</a>
					</form>
				</div>
			</div>
			<div class="login-form-bottom">
				<a href="/join.do">회원가입 하기</a>
			</div>
		</div>
	</div>
	<h1>여러분 화이팅~~!!~</h1>
	<h2>해피뉴이어~~</h2>
	<hr>
	<h3>본인 페이지 테스트</h3>
	소현
	<a href="/chatList.do"
		onClick="window.open(this.href, '', 'width=530, height=630, left=1000, scrollbars=no,location=no, resizable=no'); return false;">채팅하기</a>
	<br>
	영재
	<a href="/introduceFrm.do">프리랜서소개글</a>
	<a href="/serviceJoinFrm.do">서비스등록</a>
	<br>
	문정
	<a href="/requestList.do">의뢰게시판 리스트 </a> / <a href="/userMypage.do">사용자 마이페이지</a>
	<br>
	다솜
	<a href="/noticeList.do">공지사항 게시판</a> / <a href="/serviceList.do">서비스리스트</a>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

</html>