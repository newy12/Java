<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <style>
        section{
            width: 1200px;
            margin: 0 auto;
        }
        .join-container{
            width: fit-content;
            margin: 0 auto;
        }
        
        .join-center>div:not(.phone-form) input{
            width: 300px;
            height: 35px;
            margin: 5px 0;
        }
        .join-center>div:last-child input{
            width: 80px;
            height: 35px;
        }
        .terms-checkbox{
            width: 300px;
            height: 200px;
            border: 1px solid gray;
        }

        .terms-top{
            width: 90%;
            margin: 0 auto;
            border-bottom: 1px solid black;
        }
    </style>
</head>

<body>
    <jsp:include page="/WEB-INF/views/common/headerSimple.jsp" />

    <section>
        <div class="join-container">
            <div class="content-title">
                <h3>회원가입</h3>
            </div>
            <div class="join-center">
                <div class="id-form">
                    <span>아이디</span><br>
                    <input type="text" name="id" id="id" placeholder="아이디를 입력해주세요">
                </div>
                <div class="pw-form">
                    <span>비밀번호</span><br>
                    <input type="password" name="pw1" id="pw1" placeholder="비밀번호를 입력해주세요"><br>
                    <input type="password" name="pw2" id="pw2" placeholder="비밀번호를 확인">
                </div>
                <div class="name-form">
                    <span>이름</span><br>
                    <input type="text" name="name" id="name" placeholder="이름을 입력해주세요">
                </div>
                <div class="email-form">
                    <span>이메일</span><br>
                    <input type="text" name="email" id="email" placeholder="이메일을 입력해주세요">
                </div>
                <div class="phone-form">
                    <span>전화번호</span><br>
                    <input type="text" name="phone" id="phone1"> -
                    <input type="text" name="phone" id="phone2"> -
                    <input type="text" name="phone" id="phone3">
                </div>
            </div>
            <div class="join-bottom">
                <div class="terms-form">
                    <span>약관동의</span>
                    <div class="terms-checkbox">
                        <div class="terms-top">
                            <input type="checkbox" name="allchk" id="allchk">
                            <label for="allchk">모두 동의합니다.</label>
                        </div>
                        <div class="terms-bottom">
                            <input type="checkbox" name="allchk" id="allchk">
                            <label for="allchk"><input type="button" value="서비스 이용약관">에 동의합니다.(필수)</label>
                            <input type="checkbox" name="allchk" id="allchk">
                            <label for="allchk"><input type="button" value="개인정보 수집·이용">에 동의합니다.(필수)</label>
                        </div>
                    </div>
                </div>
                <div class="submit">
                    <button>버튼만 누르면 가입완료!</button>
                </div>
            </div>
        </div>
    </section>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

</html>