<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>19시 :: 회원가입</title>
        <!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">

    <style>
        body{
            font-family: "Arita-dotum-Medium";
        }
        section {
            width: 1200px;
            margin: 0 auto;
        }

        .join-container {
            width: fit-content;
            margin: 0 auto;
        }

        .join-center div {
            margin-top: 5px;
        }

        .form-title {
            font-weight: bold;
            color: #282828;
        }

        .join-center>div:not(.phone-form) input {
            display: block;
            width: 350px;
            height: 45px;
            margin: 5px 0;
            border-radius: 5px;
            border: 1px solid lightgray;
            outline: none;
            font-size: medium;
        }

        .join-center>div:not(.phone-form) input:focus {
            border-color: #314C83;
        }

        .join-center>div:last-child input {
            width: 100px;
            height: 35px;
            border-radius: 5px;
            border: 1px solid lightgray;
            outline: none;
            font-size: medium;
        }

        .join-center>div:last-child input:focus {
            border-color: #314C83;
        }

        .terms-checkbox {
            width: 350px;
            height: 200px;
            border: 1px solid lightgray;
            border-radius: 4px;
        }

        .terms-top {
            width: 90%;
            margin: 5px auto;
            border-bottom: 1px solid black;
        }

        .terms-bottom {
            width: 90%;
            margin: 5px auto;
        }

        .terms-bottom label {
            font-size: small;
        }

        .terms-bottom label>input[type=button] {
            border: none;
            outline: none;
            background-color: white;
            font-weight: bold;
            text-decoration: underline;
            padding: 0;
        }

        .terms-bottom label>input[type=button]:hover {
            cursor: pointer;
        }

        .submit>button {
            margin-top: 10px;
            width: 350px;
            height: 45px;
            border: none;
            border-radius: 5px;
            background-color: rgba(128, 128, 128, 0.151);
            color: gray;
            outline: none;
            font-weight: bold;
        }

        .submit>button:hover {
            cursor: not-allowed;
        }

        .background-screen {
            display: none;
            position: absolute;
            width: 100vw;
            height: 100vh;
            background-color: black;
            opacity: 0.2;
        }

        .service-terms-container,
        .privacy-terms-container {
            padding: 0;
            display: none;
            position: absolute;
            left: calc(50% - 275px);
            top: calc(50% - 350px);
            width: 550px;
            height: 600px;
            background-color: white;
            border-radius: 5px;
            box-shadow: 2px 2px 12px 1px gray;
            border: 2px solid #314C83;
        }

        .terms-title {
            position: relative;
            top: -2px;
            width: 100%;
            height: 30px;
            border-top-left-radius: 5px;
            border-top-right-radius: 5px;
            background-color: #314C83;
            color: white;
            text-align: center;
            padding-left: 20px;
            box-sizing: border-box;
            font-size: small;
            font-weight: bold;
            line-height: 30px;
        }

        .terms-close {
            float: right;
            width: 20px;
            height: 100%;
        }

        .terms-close:hover {
            cursor: pointer;
        }

        .service-terms-bottom,
        .privacy-terms-bottom {
            font-size: smaller;
            padding: 10% 20%;
            overflow: auto;
            height: calc(100% - 30px);
            box-sizing: border-box;
        }

        .service-terms-bottom>pre,
        .privacy-terms-bottom>pre {
            white-space: pre-wrap;
        }

        /* validation box(유효성 검사) 부분  */
        #id_validation,
        #pw1_validation,
        #pw2_validation,
        #name_validation,
        #email_validation,
        #phone_validation {
            display: none;
            font-size: 11px;
            color: red;
            margin: 0;
            margin-bottom: 10px;
        }
    </style>
</head>

<body>
    <jsp:include page="/WEB-INF/views/common/headerSimple.jsp" />
    <script>
        function blurEvt($inputTarget, $validationTarget) {
            if ($inputTarget.val() != '')
                $inputTarget.trigger("keyup");
            else {
                $inputTarget.removeAttr("style");
                $validationTarget.hide();
            }
        }
        $(document).ready(function () {
            //submit 버튼 비활성화
            $(".submit>button").prop("disabled", true);
            //id 정규식
            $(".id-form input").on("keyup", (function (e) {
                // m_id             VARCHAR2(20)
                let regExp = /^\w{3,20}$/;
                if (regExp.exec($(this).val())) {
                    console.log("조건 통과함: " + $(this).val())
                    $("#id_validation").hide();
                    $(this).removeAttr("style");
                } else {
                    $("#id_validation").show();
                    $(this).css("border-color", "red");
                }
                checkAllValidation();
            }));
            $(".id-form input").on("blur", function () {
                blurEvt($(this), $("#id_validation"));
            });
            //pw 정규식
            $(".pw-form #pw1").on("keyup", (function (e) {
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
            $(".pw-form #pw1").on("blur", function () {
                blurEvt($(this), $("#pw1_validation"));
                blurEvt($(".pw-form #pw2"), $("#pw2_validation"));
            });
            //pw 재확인
            $(".pw-form #pw2").on("keyup", (function (e) {
                if ($(".pw-form #pw1").val() == $(this).val()) {
                    console.log("비번 확인조건 통과함: " + $(this).val())
                    $("#pw2_validation").hide();
                    $(this).removeAttr("style");
                } else {
                    $("#pw2_validation").show();
                    $(this).css("border-color", "red");
                }
                checkAllValidation();
            }));
            $(".pw-form #pw2").on("blur", function () {
                blurEvt($(this), $("#pw2_validation"));
            });
            //name 정규식
            $(".name-form input").on("keyup", (function (e) {
                // m_name           VARCHAR2(20)
                let regExp = /^[가-힣]{1,6}$/;
                if (regExp.exec($(this).val())) {
                    console.log("조건 통과함: " + $(this).val())
                    $("#name_validation").hide();
                    $(this).removeAttr("style");
                } else {
                    $("#name_validation").show();
                    $(this).css("border-color", "red");
                }
                checkAllValidation();
            }));
            $(".name-form input").on("blur", function () {
                blurEvt($(this), $("#name_validation"));
            });
            //email 정규식
            $(".email-form input").on("keyup", (function (e) {
                // m_email          VARCHAR2(30)
                let regExp = /^\w+@(\w+\.\w+){1,}$/;
                if($(this).val().length >30){
                    console.log("초과");
                    $("#email_validation").show();
                    $(this).css("border-color", "red");
                }
                else if (regExp.exec($(this).val())) {
                    console.log("이메일조건 통과함: " + $(this).val())
                    $("#email_validation").hide();
                    $(this).removeAttr("style");
                }else {
                    $("#email_validation").show();
                    $(this).css("border-color", "red");
                }
                checkAllValidation();
            }));
            $(".email-form input").on("blur", function () {
                blurEvt($(this), $("#email_validation"));
            });
            //phone 정규식
            $(".phone-form input").on("keyup", (function (e) {
                let valid = true;
                $.each($(".phone-form input:text"), function (index, value) {
                    let regExp1 = /^[0-9]{3}$/;
                    let regExp2 = /^[0-9]{4}$/;
                    if (index == 0 && regExp1.exec($(value).val())) {
                        console.log("폰1 조건 통과함: " + $(value).val());
                    } else if ((index == 1 || index == 2) && regExp2.exec($(value).val())) {
                        console.log("폰2 조건 통과함: " + $(value).val());
                    } else {
                        valid = false;
                    }
                });
                if (valid) {
                    $("#phone_validation").hide();
                    $(".phone-form input").removeAttr("style");
                    let phoneStr = $("#phone1").val() +"-"+ $("#phone2").val() +"-"+ $("#phone3").val();
                    $("[name=mPhone]").val(phoneStr);
                    console.log("phoneStr : " + phoneStr);
                } else {
                    $("#phone_validation").show();
                    $(".phone-form input").css("border-color", "red");
                }
                checkAllValidation();
            }));
            $(".phone-form input").on("blur", function () {
                blurEvt($(".phone-form input"), $("#phone_validation"));
            });

            // 약관 관련 기능
            //약관 모두 체크 클릭 시 기능
            $("#allchk").on("change", function () {
                console.log($(this).prop("checked"));
                if ($(this).prop("checked")) {
                    $("#service").prop("checked", true);
                    $("#privacy").prop("checked", true);
                } else {
                    console.log("flase");
                    $("#service").prop("checked", false);
                    $("#privacy").prop("checked", false);
                }
                checkAllValidation();
            });
            $("#service").on("change", function () {
                if (!$(this).prop("checked")) {
                    $("#allchk").prop("checked", false);
                } else {
                    if ($("#privacy").prop("checked"))
                        $("#allchk").prop("checked", true);
                }
                checkAllValidation();
            });
            $("#privacy").on("change", function () {
                if (!$(this).prop("checked")) {
                    $("#allchk").prop("checked", false);
                } else {
                    if ($("#service").prop("checked"))
                        $("#allchk").prop("checked", true);
                }
                checkAllValidation();
            });
            //스크롤 막기 true : 막기 , false : 풀기
            function blockScroll(toggle) {
                if (toggle) {
                    $("body").css('height', '100vh');
                    $("body").css('overflow', 'hidden');
                } else {
                    $("body").removeAttr('style');
                }
            }
            // 약관 눌렀으면 모달창 띄우기
            $(".terms-bottom label input[type=button]:eq(0)").on("click", function () {
                $(".background-screen").show();
                $(".background-screen").css("top", window.scrollY);
                $(".service-terms-container").show();
                blockScroll(true);
            });
            $(".terms-bottom label input[type=button]:eq(1)").on("click", function () {
                $(".background-screen").show();
                $(".background-screen").css("top", window.scrollY);
                $(".privacy-terms-container").show();
                blockScroll(true);
            });
            // 회색 배경 누르면 약관 꺼지게
            $(".background-screen").on("click", function () {
                $(this).hide();
                $(".service-terms-container").hide();
                $(".privacy-terms-container").hide();
                blockScroll(false);
            });
            // 약관 x 표시 누르면 꺼지게
            $(".terms-close").on("click", function () {
                $(".background-screen").hide();
                $(".service-terms-container").hide();
                $(".privacy-terms-container").hide();
                blockScroll(false);

            });

            //모든 유효성 검사가 완료되었을 시 가입완료 버튼 클릭 가능하게 바꿈.
            function checkAllValidation() {
                if ($(".id-form input").val() == '' || $(".pw-form #pw1").val() == '' || $(".pw-form #pw2")
                    .val() == '' ||
                    $(".name-form input").val() == '' || $(".email-form input").val() == '' || $(
                        ".phone-form input:eq(0)").val() == '' ||
                    $(".phone-form input:eq(1)").val() == '' || $(".phone-form input:eq(2)").val() == '') {
                    $(".submit>button").prop("disabled", true);
                    $(".submit>button").removeAttr("style");
                    console.log("if");
                } else if ($("#id_validation").css("display") == "block" || $("#pw1_validation").css(
                        "display") == "block" ||
                    $("#pw2_validation").css("display") == "block" || $("#name_validation").css("display") ==
                    "block" ||
                    $("#email_validation").css("display") == "block" || $("#phone_validation").css("display") ==
                    "block") {
                    $(".submit>button").prop("disabled", true);
                    $(".submit>button").removeAttr("style");
                    console.log("elseif");
                } else if (!$("#service").prop("checked") || !$("#privacy").prop("checked")) {
                    $(".submit>button").prop("disabled", true);
                    $(".submit>button").removeAttr("style");
                    console.log("elseif2");
                } else {
                    $(".submit>button").prop("disabled", false);
                    $(".submit>button").css("background-color", "#314C83");
                    $(".submit>button").css("color", "white");
                    $(".submit>button").css("cursor", "pointer");

                    console.log("else");
                }

            }
            // sumit 이벤트 발생 시 ajax 로 아이디 중복검사
            $("#reg-form").on("submit", function (e) {
                $.ajax({
                    type: "post",
                    url: "/checkId.do",
                    async:false,
                    data: {
                        id: $("#id").val()
                    },
                    dataType: "json",
                    success: function (response) { // result : true -> 중복 , false -> 중복아님
                        if (response.result == "true"){
                            alert("아이디가 중복되었습니다.");
                            $("#id").val('');
                            $("#id").trigger("keyup");
                            $("#id").trigger("blur");
                            e.preventDefault();
                        }
                    }
                });
            });
        });
    </script>
    <div class="background-screen">
    </div>
    <div class="service-terms-container">
        <div class="service-terms-top">
            <div class="terms-title">
                <span>서비스 이용약관</span>
                <div class="terms-close">
                    <span>x</span>
                </div>
            </div>

        </div>
        <div class="service-terms-bottom">
            <pre>
제1조 (목적)

이 약관은 주식회사 19시(이하 “회사”라고 합니다)이 온라인 마켓플레이스(http://www..com, 이하 "19시 사이트”라고 합니다)와 스마트폰 등 이동통신기기를 통해 제공되는 모바일 애플리케이션(이하 “모바일 19시”이라고 합니다)을 통하여 제공하는 중개 서비스 및 기타 정보서비스(이하 "서비스"라고 합니다)와 관련하여 회사와 회원 간의 권리와 의무, 책임 사항 및 회원의 서비스 이용 절차에 관한 사항을 규정함을 목적으로 합니다.



제2조 (약관의 명시, 효력 및 변경)

가. 회사는 본 약관의 내용과 상호, 회사소재지, 대표자의 성명, 사업자등록번호, 연락처 등을 서비스를 이용하고자 하는 자와 회원이 알 수 있도록 서비스가 제공되는 19시 사이트의 초기화면(이하 연결화면 포함)에 게시합니다.

나. 회사가 이 약관을 개정하는 경우에는 개정된 약관의 적용일자 및 개정사유를 명시하여 현행 약관과 함께 그 적용일자 7일(다만, 제5조 가. 2)의 회원에게 불리한 내용으로 변경하는 경우에는 30일) 이전부터 적용일자 전일까지 위 가항의 방법으로 공지합니다. 개정된 약관은 개정약관에서 소급효를 부여하지 않는 이상 적용일자 이전으로 소급하여 적용되지 아니합니다.

다. 이 약관은 회사와 회원간에 성립되는 서비스이용계약의 기본약정입니다. 회사는 필요한 경우 특정 서비스에 관하여 적용될 사항(이하 "개별약관"이라고 합니다)을 정하여 미리 공지할 수 있습니다. 회원이 이러한 개별약관에 동의하고 특정 서비스를 이용하는 경우에는 개별약관이 우선적으로 적용되고, 이 약관은 보충적인 효력을 갖습니다. 개별약관의 게시·변경에 관해서는 위 가항, 나항을 준용합니다. 단, 회사는 개별약관을 19시 사이트의 판매관리 화면에 게시할 수 있습니다.

라. 회사는 특정 전문가와 개별적으로 이 약관 및 개별약관(이하 “약관 등”이라고 합니다)에 규정된 내용과 다른 내용의 계약(이하 “개별계약”이라고 합니다)을 체결할 수 있습니다. 이 경우 개별계약이 약관 등에 우선하여 적용됩니다. 회사는 개별계약을 체결한 전문가에게 계약내용을 서면(전자문서 포함)교부하거나 판매관리 화면에서 확인할 수 있도록 합니다.

            </pre>
        </div>
    </div>
    <div class="privacy-terms-container">
        <div class="privacy-terms-top">
            <div class="terms-title">
                <span>개인정보 처리방침</span>
                <div class="terms-close">
                    <span>x</span>
                </div>
            </div>

        </div>
        <div class="privacy-terms-bottom">
            <pre>
l 제1조(총칙)

㈜19시(이하 '회사'라고 함)은 회원의 정보를 소중하게 생각하고 안전하게 보호하기 위하여 최선의 노력을 다하고 있으며 개인정보보호법 및 정부의 가이드라인을 준수합니다.

회사는 회원의 개인정보를 필요한 시점에 따라 최소한으로 수집하고 수집한 정보는 사전 고지한 범위 내에서만 사용하며, 사전 동의 없이 그 범위를 초과하여 이용하거나 외부에 공개하지 않습니다.

다만, 법령의 규정에 의거하거나 수사기관 및 행정기관이 수사 또는 조사의 목적으로 법령에 정해진 절차와 방법에 따라 요청한 경우에는 내부 절차를 거쳐 최소한의 정보가 동의없이 제공될 수 있습니다.

회사는 ‘개인정보처리방침’을 제정하고 이를 준수하여 회원의 개인정보의 이용 및 보호에 대한 조치를 알리며, 관련법규를 준수하고 있습니다.

회사의 ‘개인정보처리방침’은 관련법률 및 지침의 변경 또는 내부 운영방침의 변경 등으로 인하여 개정될 수 있습니다.

‘개인정보처리방침’이 변경될 경우 변경사항은 공지사항 또는 이메일 등의 연락수단을 통해 공지해드립니다.

변경사항에 의문이 있으신 경우에는 언제라도 담당부서에 문의하시거나 홈페이지를 방문하여 확인하실 수 있습니다.

개인정보 처리방침과 이용 약관의 개인정보 관련한 내용의 경우 이용약관이 우선 순위를 갖습니다.

 

l 제2조(개인정보의 수집∙이용 및 보유)

회사는 최적화된 서비스를 제공하기 위하여 아래와 같은 목적으로 개인정보를 수집 및 이용하고 있으며, 동의하신 목적 이외에 다른 용도로 이를 이용하거나 동의없이 제3자에게 이를 제공하지 않습니다.

회사는 전자상거래 등에서의 소비자보호에 관한 법률, 국세기본법, 전자금융거래법 등 관련법령에 따라 주민등록번호 및 은행계좌번호의 수집∙보관이 불가피한 경우에는 이용자에게 사전 고지하여 정보를 수집할 수 있습니다.

회사는 이용자의 기본적 인권을 침해할 우려가 있는 인종, 출신지, 본적지, 사상, 정치적 성향, 범죄기록, 건강상태 등의 정보는 이용자의 동의 또는 법령의 규정에 의한 경우 이외에는 수집하지 않습니다.

만 14세 미만 아동의 개인정보는 법정대리인의 동의를 얻은 경우에 수집·이용할 수 있습니다. 이 경우 회사는 법정대리인의 이름, 생년월일, 휴대폰 번호, 이메일 주소, 본인확인 값(CI, DI)을 추가로 수집할 수 있습니다.

 

 1. 개인정보의 수집 방법

회사는 다음과 같은 방법으로 개인정보를 수집할 수 있습니다.

- 홈페이지, 모바일 어플리케이션, 모바일 웹페이지, 서면, 팩스, 전화, 고객센터 문의하기, 이벤트 응모

- 생성정보 수집 툴을 통한 자동 수집

            </pre>
        </div>
    </div>

    <section>
        <form action="/register.do" method="post" id="reg-form">
            <div class="join-container">
                <div class="content-title">
                    <h2>회원가입</h2>
                </div>
                <div class="join-center">
                    <div class="id-form">
                        <span class="form-title">아이디</span><br>
                        <input type="text" name="mId" id="id" placeholder="아이디를 입력해주세요">
                        <div id="id_validation">* 영어,숫자,밑줄로 구성된 3~20글자 사이로 입력해주세요.</div>
                    </div>
                    <div class="pw-form">
                        <span class="form-title">비밀번호</span><br>
                        <input type="password" name="mPw" id="pw1" placeholder="비밀번호를 입력해주세요(4글자 이상)">
                        <div id="pw1_validation">* 비밀번호는 4글자 이상 입력해주세요.</div>
                        <input type="password" id="pw2" placeholder="비밀번호 확인">
                        <div id="pw2_validation">* 입력하신 비밀번호와 동일하게 입력해주세요.</div>
                    </div>
                    <div class="name-form">
                        <span class="form-title">이름</span><br>
                        <input type="text" name="mName" id="name" placeholder="이름을 입력해주세요">
                        <div id="name_validation">* 이름은 1~6글자 한글로 입력해주세요.</div>
                    </div>
                    <div class="email-form">
                        <span class="form-title">이메일 (30자 이하)</span><br>
                        <input type="text" name="mEmail" id="email" placeholder="이메일을 입력해주세요">
                        <div id="email_validation">* 이메일 형식이 유효하지 않습니다.</div>
                    </div>
                    <div class="phone-form">
                        <span class="form-title">전화번호</span><br>
                        <input type="text" id="phone1" placeholder="0XX"> -
                        <input type="text" id="phone2" placeholder="1234"> -
                        <input type="text" id="phone3" placeholder="5678">
                        <input type="hidden" name="mPhone">
                        <div id="phone_validation">* 전화번호 형식이 유효하지 않습니다.</div>
                    </div>
                </div>
                <div class="join-bottom">
                    <div class="terms-form">
                        <span class="form-title">약관동의</span>
                        <div class="terms-checkbox">
                            <div class="terms-top">
                                <input type="checkbox" id="allchk">
                                <label for="allchk">모두 동의합니다.</label>
                            </div>
                            <div class="terms-bottom">
                                <input type="checkbox" id="service">
                                <label for="service"><input type="button" value="서비스 이용약관">에 동의합니다.(필수)</label><br>
                                <input type="checkbox" id="privacy">
                                <label for="privacy"><input type="button" value="개인정보 수집·이용">에 동의합니다.(필수)</label>
                            </div>
                        </div>
                    </div>
                    <div class="submit">
                        <button>버튼만 누르면 가입완료!</button>
                    </div>
                </div>
            </div>
        </form>
    </section>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

</html>