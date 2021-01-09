<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        @font-face {
            font-family: 'Arita-dotum-Medium';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Arita-dotum-Medium.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }

        * {
            font-family: 'Arita-dotum-Medium';
        }
        .header {
            margin: 0 auto;
            width: 1200px;
            height: 100px;
        }

        .header>img {
            width: 200px;
            float: left;
        }

        .name {
            float: right;
            margin-right: 50px;
        }

        .sideNavi {
            height: 800px;
            width: 200px;
            background-color: #314C83;
            border-top-left-radius: 15px;
            padding-top: 20px;
            
        }

        .sideNavi>ul {
            margin: 0;
            padding: 0;
            list-style-type: none;
        }
        .sideNavi li{
            margin: 0;
        }
        .sideNavi>ul>li>a {
            margin-left: 40px;
            padding: 10px;
            display: block;
            height: 30px;
            line-height:30px;
            text-decoration: none;
            color: white;
        }
        .navi-link:hover{
            background-color: #304582;
        }
        
    </style>


</head>
<body>
	<div class="header">
        <img src="/img/logo_white.png" onclick="location='/'">
        <div class="name">관리자</div>
    </div>
    <div class="sideNavi">
        <ul>
            <li class="navi-link"><a href="">MEMBER</a></li>
            <li class="navi-link"><a href="">SERVICE</a></li>
            <li class="navi-link"><a href="">NOTICE</a></li>
            <li class="navi-link"><a href="">FAQ</a></li>
        </ul>
    </div>
	

</body>
</html>