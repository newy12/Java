<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시::확인</title>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
</head>
<style>
     @font-face {
         font-family: 'Arita-dotum-Medium';
         src:
             url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Arita-dotum-Medium.woff') format('woff');
         font-weight: normal;
         font-style: normal;
     }
    * {
        font-family: 'Arita-dotum-Medium';
    }
	body{
		text-align: center;
		margin:0 auto;
	}
	h2{
		margin-top:150px;
	}
	#cancel-btn{
		width:100px;
		height:50px;
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
<h2>${msg }</h2>
<button id="cancel-btn">창 닫기</button>
<script>
	$("#cancel-btn").click(function(){
		//현재 창 닫고 부모 창 새로고침
		opener.document.location.reload();
		self.close();
	});
</script>
</body>
</html>