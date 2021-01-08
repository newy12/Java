<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <style>
        section {
            margin: 0 auto;
            width: 1200px;
            padding: 30px 0;
        }

        .search-form-container {
            margin: 0 auto;
            margin-top: 50px;
            width: 400px;
            height: 600px;
        }

        .search-top {
            height: 10%;
        }

        .search-top .main-title {
            font-size: 23px;
            font-weight: bold;
        }

        .search-center {}

        .search-center .search-id-form,
        .search-center .search-pw-form {
            width: 100%;
            height: 230px;
            border: 1px solid lightgray;
            border-radius: 5px;
        }

        .search-center .search-id-form {
            margin-bottom: 40px;
        }

        .sub-title {
            width: 100%;
            height: 15%;
            background-color: rgb(226, 226, 226);
            line-height: 35px;
            font-size: small;
            font-weight: bold;
            padding-left: 10px;
            margin-bottom: 20px;
        }

        .search-bottom {
            height: 20%;
        }

        .search-contents input {
            display: block;
            margin: 10px auto;
            width: 80%;
            height: 40px;
            border-radius: 4px;
            outline: none;
            border: 1px solid lightgray;
        }

        .search-contents input[type=submit] {
            margin-top: 20px;
            background-color: #314C83;
            color: white;
            border: none;
            font-weight: bold;

        }
    </style>
</head>

<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <script>
        $(document).ready(function () {
            $("#btnPw").on("click", function () {
                $("#mIdHidden").val($("#mId").val());
                $("#mPhoneHidden").val($("#mPhone2").val());
                let left = Math.ceil((window.screen.width-400)/2);
                let top = Math.ceil((window.screen.height-350)/2);
                window.open('', 'popup', 'width=400px, height=350px, left='+left+', top='+top)
                $("#pop-form").submit();
            });
        });
    </script>
    <section>
        <div class="search-form-container">
            <div class="search-top">
                <div class="main-title">
                    아이디/비밀번호 찾기
                </div>
            </div>
            <div class="search-center">
                <div class="search-id-form">
                    <div class="sub-title">
                        아이디 찾기
                    </div>
                    <div class="search-contents">
                        <form action="/searchIdPw.do" method="post">
                            <input type="text" name="mName" id="mName" placeholder="이름을 입력해주세요.">
                            <input type="text" name="mPhone" id="mPhone" placeholder="전화번호를 입력해주세요.">
                            <input type="submit" value="아이디 찾기">
                        </form>
                    </div>
                </div>
                <div class="search-pw-form">
                    <div class="sub-title">
                        비밀번호 찾기
                    </div>
                    <div class="search-contents">
                            <form id="pop-form" action="searchPw.do" method="post" target="popup">
                                <input type="hidden" name="mId" id="mIdHidden">
                                <input type="hidden" name="mPhone" id="mPhoneHidden">
                            </form>
                            <input type="text" name="mId" id="mId" placeholder="아이디를 입력해주세요.">
                            <input type="text" name="mPhone" id="mPhone2" placeholder="전화번호를 입력해주세요.">
                            <input type="button" id="btnPw" target="_self" value="비밀번호 찾기">
                    </div>
                </div>
            </div>
            <div class="search-bottom">
                <a href="/">로그인</a>
                <a href="/join.do">회원가입</a>
            </div>
        </div>
    </section>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

</html>