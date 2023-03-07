<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
   .profile-box {
        width: 250px;
        float: left;
    }

    .profile-box>div {
        text-align: center;
        margin: 0 auto;
    }
    .switch {
    	width:125px;
        height: 31px;
        margin-bottom: 30px;
        color: rgb(49, 76, 131);
        background-color:white;
        border: 1px solid rgb(49, 76, 131);
        border-radius: 3px;
    }

    .switch:hover {
        background-color: rgb(49, 76, 131);
        color: white;
        border: 1px solid rgb(49, 76, 131);
    }
    .switch:focus{
    	outline: none;
    }
    .menu{
        padding: 0;
        list-style: none;
    }
    .menu>li{
        margin-bottom: 20px;
    }
    .menu>li>img{
        display: none;
    }
    .menu>li:hover>img{
        display: inline;
    }
    .menu>li:hover>a{
        margin-left: 5px;
        font-weight: bold; 
    }
    .menu>li>a{
        text-decoration: none;
        color: rgb(51, 51, 51);
    }

    .profile-box>p {
        font-size: 18px;
        font-weight: bold;
        color: rgb(49, 76, 131);
    }
</style>
<body>
	<!-- 마이페이지 사이드 메뉴 -->
	<div class="profile-box">
	    <div>
	    	<img src="/img/icon/mypage_person.png" style="width: 147px; height: 147px">
	    </div>
	    <div style="margin-top: 10px;">${loginMember.MId }님</div>
	    <div style="margin-top: 5px;">
	    	<input type="hidden" value="${loginMember.MPw }">
	    	<input type="hidden" value="${loginMember.MId }">
	    	<input type="hidden" value="${loginMember.MGrade }">
	    	<button class="switch">프리랜서로 전환</button>
	    </div>
	    <p>MY PAGE</p>
	    <hr>
	    <ul class="menu">
	        <li><img src="/img/icon/circle_navy.png"><a href="/userMypage.do">나의 프로필</a></li>
	        <li><img src="/img/icon/circle_navy.png"><a href="/userHeartList.do?mNo=${loginMember.MNo }&order=all">찜한 내역</a></li>
	        <li><img src="/img/icon/circle_navy.png"><a href="/userTradeHistory.do?mNo=${loginMember.MNo }">거래 내역</a></li>
	        <li><img src="/img/icon/circle_navy.png"><a href="/userRequestHistory.do?reqPage=1">작성한 의뢰글</a></li>
	    </ul>
	</div>
	<script>
		$(".switch").click(function(){
			var grade = $(this).prev().val();
			var mId = $(this).prev().prev().val();
			var mPw = $(this).prev().prev().prev().val();
			
			//post로 넘기기 
			var form = document.createElement("form");
			form.action = "/changeGrade.do";
			form.method = "post";
			
			//id 추가
			var input_mId = document.createElement("input");
			input_mId.setAttribute("type", "hidden");
			input_mId.setAttribute("name", "mId");
			input_mId.setAttribute("value", mId);
	        form.appendChild(input_mId);
			//mPw 추가
			var input_mPw = document.createElement("input");
			input_mPw.setAttribute("type", "hidden");
			input_mPw.setAttribute("name", "mPw");
			input_mPw.setAttribute("value", mPw);
	        form.appendChild(input_mPw);
	        //grade 추가
	        var input_grade = document.createElement("input");
			input_grade.setAttribute("type", "hidden");
			input_grade.setAttribute("name", "grade");
			input_grade.setAttribute("value", grade);
	        form.appendChild(input_grade);

			document.body.appendChild(form);
			form.submit();
		});
		
		function errorMsg(){
			alert('프리랜서 프로필을 작성해주세요');
		}
	</script>
</body>
</html>