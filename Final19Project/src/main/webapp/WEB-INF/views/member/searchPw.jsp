<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>19시 :: pw찾기</title>
    <!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <style>
        section {
            width: 480px;
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

        input:not([type=submit]):focus {
            border: 1px solid #314C83;
        }

        input[type=submit] {
            border: none;
            background-color: lightgray;
            color: white;
            font-weight: bold;
            
        }
        input[type=submit]:hover{
            cursor:not-allowed;
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
            //submit 버튼 비활성화
            $("input:submit").prop("disabled", true);
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
                checkAllValidation();
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
                checkAllValidation();
            }));
            $("#pw2").on("blur", function () {
                blurEvt($(this), $("#pw2_validation"));
            });
            //모든 유효성 검사가 완료되었을 시 버튼 클릭 가능하게 바꿈.
            function checkAllValidation() {
                if ($("#pw1").val() == '' || $("#pw2").val() == '') {
                    $("input:submit").prop("disabled", true);
                    $("input:submit").removeAttr("style");
                    console.log("if");
                } else if ($("#pw1_validation").css("display") == "block" ||
                    $("#pw2_validation").css("display") == "block") {
                    $("input:submit").prop("disabled", true);
                    $("input:submit").removeAttr("style");
                    console.log("elseif");
                } else {
                    $("input:submit").prop("disabled", false);
                    $("input:submit").css("background-color", "#314C83");
                    $("input:submit").css("color", "white");
                    $("input:submit").css("cursor", "pointer");

                    console.log("else");
                }

            }
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