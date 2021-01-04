<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    <title>Insert title here</title>

    <style>
        header.dh-header {
            width: 1200px;
            height: 175px;
            margin: 0 auto;
            border-bottom: 1px solid black;
        }

        .header-top {
            width: 100%;
            height: 65%;
        }

        .header-top-left {
            float: left;
            width: 15%;
        }

        .header-top-left>img {
            width: 100%;
            margin-top: 10px;
        }

        .header-top-center {
            float: left;
            width: 60%;
            text-align: center;
        }
        .header-top-center>.header-search{
            margin-top: 50px;
        }
        .header-top-right {
            float: left;
            width: 25%;
            height: 100%;
            text-align: center;
        }

        .header-top-right>.header-menu {
            margin-top: 50px;
        }

        .header-bottom {
            width: 100%;
            height: 35%;
        }

        .header-bottom>.nav {
            width: 100%;
            height: 100%;
        }

        .header-bottom>.nav>ul {
            width: 100%;
            height: 100%;
            list-style: none;
        }

        .header-bottom>.nav>ul>li {
            float: left;
        }

        .header-bottom>.nav>ul>li>a {
            display: block;
            text-decoration: none;
            color: black;
            font-size: large;
            padding: 16px 30px;
        }

        .header-bottom>.nav>ul>li>a:hover {
            border-bottom: 4px solid #314C83;
            color: black;
        }
    </style>
</head>

<body>
    <header class="dh-header">
        <div class="header-top">
            <div class="header-top-left">
                <img src="/img/logo/logo_white.png" alt="">
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
                    <span>로그인</span>
                    <span>무료회원가입</span>
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
</body>

</html>