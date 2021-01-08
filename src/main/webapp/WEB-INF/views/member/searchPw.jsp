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
    <style>
        section {
            width: 370px;
            height: 320px;
        }

        .title {
            width: 120px;
            margin: 0 auto;
            padding-top: 30px;
            font-weight: bold;
            font-size: medium;

        }

        .form-container {
            width: 250px;
            margin: 0 auto;
            padding-top: 50px;
        }

        input {
            margin-top: 5px;
            display: block;
            width: 250px;
            height: 35px;
            box-sizing: border-box;
            outline: none;
            border-radius: 4px;
            border: 1px solid lightgray;
        }

        input[type=submit] {
            border: none;
            background-color: #314C83;
            color: white;
            font-weight: bold;
        }

        #pw1_validation,
        #pw2_validation {
            display: none;
            font-size: 11px;
            color: red;
            margin: 0;
            margin-bottom: 10px;
        }
    </style>
</head>

<body>
    <script>
        $(document).ready(function () {

            function blurEvt($inputTarget, $validationTarget) {
                if ($inputTarget.val() != '')
                    $inputTarget.trigger("keyup");
                else {
                    $inputTarget.removeAttr("style");
                    $validationTarget.hide();
                }
            }
            //pw 정규식
            $("#pw1").on("keyup", (function (e) {
                let regExp = /^[a-zA-z0-9_!@#$%^]{4,}$/;
                if (regExp.exec($(this).val())) {
                    console.log("비번조건 통과함: " + $(this).val())
                    $("#pw1_validation").hide();
                    $(this).removeAttr("style");
                } else {
                    $("#pw1_validation").show();
                    $(this).css("border-color", "red");
                }

            }));
            $("#pw1").on("blur", function () {
                blurEvt($(this), $("#pw1_validation"));
                blurEvt($("#pw2"), $("#pw2_validation"));
            });
            //pw 재확인
            $("#pw2").on("keyup", (function (e) {
                if ($("#pw1").val() == $(this).val()) {
                    console.log("비번 확인조건 통과함: " + $(this).val())
                    $("#pw2_validation").hide();
                    $(this).removeAttr("style");
                } else {
                    $("#pw2_validation").show();
                    $(this).css("border-color", "red");
                }

            }));
            $("#pw2").on("blur", function () {
                blurEvt($(this), $("#pw2_validation"));
            });
        });
    </script>
    <section>
        <div class="title">
            비밀번호 변경
        </div>
        <div class="form-container">
            <form action="/searchChangePw.do" method="post">
                <input type="password" name="mPw" id="pw1" placeholder="비밀번호를 입력해주세요.">
                <div id="pw1_validation">* 비밀번호는 4글자 이상 입력해주세요.</div>
                <input type="password" id="pw2" placeholder="비밀번호 재확인.">
                <div id="pw2_validation">* 입력하신 비밀번호와 동일하게 입력해주세요.</div>
                <input type="submit" value="변경">
            </form>
        </div>
    </section>
</body>

</html>