<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>

    <!-- favicon -->
    <link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
    <link rel="manifest" href="/site.webmanifest">
    <title>Insert title here</title>

    <style>
        .dh-header-container {
            width: 100%;
            border-bottom: 1px solid gray;
        }

        header.dh-header {
            width: 1200px;
            height: 175px;
            margin: 0 auto;

        }

        .dh-header .header-top {
            width: 100%;
            height: 65%;
        }

        .dh-header .header-top-left {
            float: left;
            width: 15%;
        }

        .dh-header .header-top-left img {
            width: 100%;
            margin-top: 10px;
        }

        .dh-header .header-top-center {
            float: left;
            width: 60%;
            text-align: center;
        }

        .dh-header .header-top-center>.header-search {
            margin-top: 50px;
        }

        .dh-header .header-top-right {
            float: left;
            width: 25%;
            height: 100%;
            text-align: center;
        }

        .dh-header .header-top-right>.header-menu {
            margin-top: 50px;
        }

        .dh-header .header-bottom {
            width: 100%;
            height: 35%;
        }

        .dh-header .header-bottom>.nav {
            width: 100%;
            height: 100%;
        }

        .dh-header .header-bottom>.nav>ul {
            width: 100%;
            height: 100%;
            list-style: none;
        }

        .dh-header .header-bottom>.nav>ul>li {
            float: left;
        }

        .dh-header .header-bottom>.nav>ul>li>a {
            display: block;
            text-decoration: none;
            color: black;
            font-size: large;
            padding: 16px 30px;
        }

        .dh-header .header-bottom>.nav>ul>li>a:hover {
            border-bottom: 4px solid #314C83;
            color: black;
        }

        .header-menu span {
            font-size: 15px;
        }

        .header-menu span:hover {
            text-decoration: underline;
            cursor: pointer;
        }
    </style>
</head>

<body>
    <div class="dh-header-container">
        <header class="dh-header">
            <div class="header-top">
                <div class="header-top-left">
                    <a href="/"><img src="/img/logo/logo_white.png" alt=""></a>
                </div>
                <div class="header-top-center">
                    <div class="header-search">
                        <input type="text" name="searach" id="search">
                        <button>검색</button>
                    </div>
                </div>
                <div class="header-top-right">
                    <div class="header-menu">
                        <span>고객센터</span>
                        <span id="login">로그인</span>
                        <a href="/join.do"><span>무료회원가입</span></a>
                    </div>
                </div>
            </div>
            <div class="header-bottom">
                <div class="nav">
                    <ul>
                        <li><a href="#">디자인</a></li>
                        <li><a href="#">IT프로그래밍</a></li>
                        <li><a href="#">영상사진음향</a></li>
                        <li><a href="#">레슨실무교육</a></li>
                        <li><a href="#">문서글쓰기</a></li>
                        <li><a href="#">비즈니스 컨설팅</a></li>
                        <li><a href="#">주문제작</a></li>
                    </ul>
                </div>
            </div>
        </header>
    </div>
</body>

</html>