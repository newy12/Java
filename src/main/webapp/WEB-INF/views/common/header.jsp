<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>

    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- slick CSS -->

    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />

    <!-- slick JS -->

    <script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>


    <!-- favicon -->
    <link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
    <link rel="manifest" href="/site.webmanifest">

    <!-- loginFrm -->
    <link rel="stylesheet" href="/css/member/loginFrm.css">
    <title>Insert title here</title>

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
        .dh-header-container {
            position: relative;
            width: 100%;
            border-bottom: 1px solid lightgray;
            z-index: 100;
            font-family: "Arita-dotum-Medium";
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
            text-align: right;
        }

        .dh-header .header-top-center>.header-search input {
            transition: 0.3s;
            border: 1px solid lightgray;
            height: 40px;
            width: 200px;
            border-radius: 4px;
        }
        .dh-header .header-top-center>.header-search input:focus {
            width: 310px;
        }
        .dh-header .header-top-center>.header-search button{
            height: 40px;
            width: 50px;
            background-color: #314c83;
            color: white;
            border-radius: 4px;
            outline: none;
            border: none;
            box-sizing: border-box;
        }
        .dh-header .header-top-right {
            float: right;
            width: fit-content;
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
            margin: 0;
            padding: 0;
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
            padding: 16px 35px;
        }

        .dh-header .header-bottom>.nav>ul>li>a:hover,
        .dh-header .header-bottom>.nav>ul>li:hover>a {
            border-bottom: 4px solid #314C83;
            color: black;
        }

        .dh-header .header-bottom>.nav>ul ul {
            display: none;
            position: absolute;
            border: 1px solid lightgray;
            border-top-color: gray;
            box-shadow: 1px 1px 6px 0px lightgray;
            background-color: white;
            list-style: none;
            padding: 0;
        }

        .dh-header .header-bottom>.nav>ul ul>li {}

        .dh-header .header-bottom>.nav>ul ul>li>a {
            display: block;
            text-decoration: none;
            color: black;
            font-size: medium;
            padding: 0 35px;
            margin-top: 20px;
        }

        .dh-header .header-bottom>.nav>ul ul>li>a:last-child {
            margin-bottom: 20px;
        }

        .dh-header .header-bottom>.nav>ul ul>li>a:hover {
            text-decoration: underline;
        }

        .dh-header .header-bottom>.nav>ul>li:hover ul {
            display: block;
        }

        .header-menu {
            margin-left: 50px;
            line-height: 40px;
        }

        .header-menu span {
            font-size: 15px;
        }

        .header-menu span:hover {
            cursor: pointer;
        }

        .btn-deepblue {
            outline: none;
            background-color: #314C83;
            border: none;
            color: white;
            border-radius: 5px;
        }

        #join-btn {
            display: inline-block;
            width: 110px;
            height: 35px;
            line-height: 35px;
            margin-left: 20px;
            text-decoration: none;
        }

        #join-btn:hover {
            color: lightgray;
            background-color: #15377c;
        }

        #c-center {
            display: block;
            float: left;
        }

        .ul-user {
            float: left;
            position: relative;
            display: block;
            padding: 0;
            margin: 0;
            margin-left: 20px;
            margin-right: 20px;
            list-style: none;
        }

        #login {
            display: block;
            float: left;
            margin-left: 10px;
        }

        .ul-user:hover ul {
            display: block;
        }

        .ul-user ul {
            display: none;
            position: absolute;
            width: 120px;
            border: 1px solid lightgray;
            border-radius: 5px;
            box-shadow: 1px 1px 6px 0px lightgray;
            background-color: white;
            list-style: none;
            padding: 0;
            margin-right: 0;
            left: -40px;
        }

        .ul-user li a {
            display: block;
            text-decoration: none;
            color: #282828;
            font-size: medium;
            padding: 10px 10px;
        }

        .ul-user>li a:hover {
            background-color: whitesmoke;
        }

        #user_name {
            font-size: 15px;
        }

        .chat-remocon {
            position: fixed;
            right: 50px;
            bottom: 50px;
            box-shadow: 1px 1px 7px 0px gray;
            border-radius: 5px;
        }

        .chat-remocon>a {
            text-align: center;
            line-height: 46px;
            text-decoration: none;
            color: white;
            background-color: #FF8F3F;
            font-size: 18px;
            font-weight: bold;
            display: block;
            width: 130px;
            height: 45px;
            border-radius: 5px;

        }
       
    </style>
</head>

<script>
    $(document).ready(function () {
        //카테고리 목록 불러오는거
        $.ajax({
            url: '/categoryAjax.do',
            dataType: 'json',
            success: function (data) {
                let $navUl = $(".nav>ul");

                for (let i = 0; i < data.length; i++) {
                    if (data[i].cDivision == 'm') {
                        $navUl.append("<li><a href='/serviceList.do?cNo=" + data[i].cNo +
                            "&reqPage=1&order=new&keyword='>" + data[i].cName +
                            "</a> <ul></ul></li>")

                    }
                    if (data[i].cDivision == 's') {
                        $(".nav>ul>li:eq(" + (parseInt(data[i].cNo / 10) - 1) + ")>ul").append(
                            "<li><a href='/serviceList.do?cNo=" + data[i].cNo +
                            "&reqPage=1&order=new&keyword='>" + data[i].cName + "</a></li>")  
                    }
                }
            }
        })
        //스크롤 막기 true : 막기 , false : 풀기
        function blockScroll(toggle) {
            if (toggle) {
                $("body").css('height', '100vh');
                $("body").css('overflow', 'hidden');
            } else {
                $("body").removeAttr('style');
            }
        }

        //헤더의 로그인 버튼 누르면 로그인 창 show
        $(".header-menu #login").on("click", function () {
            $(".background-screen").show();
            $(".background-screen").css("top", window.scrollY);
            $(".login-form-container").show();
            blockScroll(true);
        });
        // 뒷 배경 누르면 ,로그인 창, 뒷 배경 Hide
        $(".background-screen").on("click", function () {
            $(".background-screen").hide();
            $(".login-form-container").hide();
            blockScroll(false);
        });
        // 로그인 창 x버튼 누르면 ,로그인 창, 뒷 배경 Hide
        $(".login-form-close").on("click", function () {
            $(".background-screen").hide();
            $(".login-form-container").hide();
            blockScroll(false);
        });
        //submit 버튼 비활성화
        $("#btnLogin").prop("disabled", true);

        function blurEvt($inputTarget, $validationTarget) {
            if ($inputTarget.val() != '')
                $inputTarget.trigger("keyup");
            else {
                $inputTarget.removeAttr("style");
                $validationTarget.hide();
            }
        }
        let idAllowed = false;
        //id 정규식
        $("#id").on("keyup", (function (e) {
            // m_id             VARCHAR2(20)
            let regExp = /^\w{3,20}$/;
            if (regExp.exec($(this).val())) {
                console.log("조건 통과함: " + $(this).val())
                $("#id_validation").hide();
                $(this).removeAttr("style");
                idAllowed = true;
            } else {
                $("#id_validation").show();
                $(this).css("border-color", "red");
                idAllowed = false;
            }
            checkAllValidation();
        }));
        $("#id").on("blur", function () {
            blurEvt($(this), $("#id_validation"));
        });
        let pwAllowed = false;
        //pw 정규식
        $("#pw").on("keyup", (function (e) {
            let regExp = /^[a-zA-z0-9_!@#$%^]{4,}$/;
            if (regExp.exec($(this).val())) {
                console.log("비번조건 통과함: " + $(this).val())
                $("#pw1_validation").hide();
                $(this).removeAttr("style");
                pwAllowed = true;
            } else {
                $("#pw1_validation").show();
                $(this).css("border-color", "red");
                pwAllowed = false;
            }
            checkAllValidation();
        }));
        $("#pw").on("blur", function () {
            blurEvt($(this), $("#pw1_validation"));
        });

        //모든 유효성 검사가 완료되었을 시 버튼 클릭 가능하게 바꿈.
        function checkAllValidation() {
            if ($("#id").val() == '' || $("#pw").val() == '') {
                $("#btnLogin").prop("disabled", true);
                $("#btnLogin").removeAttr("style");
                console.log("if");
            } else if (idAllowed == false || pwAllowed == false) {
                $("#btnLogin").prop("disabled", true);
                $("#btnLogin").removeAttr("style");
                console.log("elseif");
            } else {
                $("#btnLogin").prop("disabled", false);
                $("#btnLogin").css("background-color", "#314C83");
                $("#btnLogin").css("color", "white");
                $("#btnLogin").css("cursor", "pointer");

                console.log("else");
            }

        }
        $("#user_name").on("hover", function (e) {
            $(".ul-user").css("display", "block");
        })
        $(".ul-user").on("hover", function (e) {
            $(".ul-user").css("display", "block");
        })

        // 로그인 후 원래 있던 페이지로 돌아가기 위한 코드
        $("#login-loc").val(location.href);
    });
</script>
<div class="background-screen"></div>
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
                    <input type="submit" id="btnLogin" value="로그인">
                    <a href="/forgot_pwd.do">아이디·비밀번호 찾기</a>
                    <input type="hidden" name="loc" id="login-loc">
                </form>
            </div>
        </div>
        <div class="login-form-bottom">
            <a href="/join.do">회원가입 하기</a>
        </div>
    </div>
</div>
<div class="dh-header-container">
    <c:if test="${not empty loginMember}">
    <c:if test="${loginMember.MId ne 'admin'}">
        <div class="chat-remocon">
            <a href="/chatList.do?mGrade=${loginMember.MGrade}&mId=${loginMember.MId}"
                onClick="window.open(this.href, '', 'width=530, height=630, left=1000'); return false;">문의하기</a>
        </div>
         </c:if>
    </c:if>
    <header class="dh-header">
        <div class="header-top">
            <div class="header-top-left">
                <a href="/"><img src="/img/logo/logo_white.png" alt=""></a>
            </div>
            <div class="header-top-center">
                <div class="header-search">
                    <form action="/serviceSearch.do" method="get">
                        <input type="text" name="keyword" id="h-search" placeholder="키워드를 입력해주세요.">
                        <button>검색</button>
                    </form>
                </div>
            </div>
            <div class="header-top-right">
                <div class="header-menu">
                    <a href="/noticeList.do?reqPage=1&keyword="><span id="c-center">공지사항</span></a>
                    <c:if test="${empty loginMember}">
                        <span id="login">로그인</span>
                        <a href="/join.do" id="join-btn" class="btn-deepblue"><span>무료 회원가입</span></a>
                    </c:if>
                    <c:if test="${not empty loginMember}">
                        <ul class="ul-user">
                            <a href="${loginMember.MGrade != 0 ? '/userMypage.do' : '/manageMember.do?reqPage=1&grade=all&keyword=&order=new'}" id="user_name">${loginMember.MName}</a>
                            <li>
                                <ul>
                                    <li>
                                        <a href="${loginMember.MGrade != 0 ? '/userMypage.do' : '/manageMember.do?reqPage=1&grade=all&keyword=&order=new'}">${loginMember.MGrade != 0 ? '마이페이지' : '관리페이지'}</a>
                                    </li>
                                    <li>
                                        <a href="qna.do?page=1">1:1 문의</a>
                                    </li>
                                    <li>
                                        <span id="logout"><a href="/logout.do">로그아웃</a></span>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="header-bottom">
            <div class="nav">
                <ul>
                </ul>
            </div>
        </div>
    </header>
</div>
</html>