<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
</head>
<style>
	body{
		text-align: center;
		margin:0 auto;
	}
	h2{
		margin-top:150px;
	}
	#cancel-btn{
		width:200px;
		height:100px;
		border-radius:10px;
		background-color: #801f1f;
		font-size:20px;
		color:white;
	}
	#cancel-btn:hover{
		border:1px solid red;
		background-color: white;
		color:black;
	}
</style>
<body>
<h2>리뷰가 등록되었습니다</h2>
<button id="cancel-btn">창 닫기</button>
<script>
	$("#cancel-btn").click(function(){
		window.close();
	});
</script>
</body>
</html>