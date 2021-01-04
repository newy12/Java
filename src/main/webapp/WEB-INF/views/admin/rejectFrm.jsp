<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시(관리자) :: 서비스 승인 거절 이유</title>
</head>
<style>
	.r-wrap {
		text-align: center;
	}

	fieldset {
		text-align: left;
		padding: 15px;
		padding-left: 80px;
	}
</style>

<body>
<div class="r-wrap">
		<img src="img/logo/logo_white.png" width="200px;">
		<fieldset>
			<b>이유 :</b><br>
			<input type="radio" name="reason"> 카테고리 선택이 잘못되었습니다.<br>
			<input type="radio" name="reason"> 포트폴리오 내용이 부족합니다.<br>
			<input type="radio" name="reason"> 부적절한 서비스입니다. <br>
			기타 : <input id="etc" type="text" name="reason">
		</fieldset><br>
		위의 이유로 서비스 등록 승인을 보류합니다.<br><br>
		<button id="submit">확인</button> <button id="close">닫기</button>
	</div>
</body>
</html>