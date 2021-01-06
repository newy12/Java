<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>

<style>
    .page-wrap{
        width: 1104px;
        height:700px;
        margin: 0 auto;
        margin-top:50px;
    }
	.profile-box{
        width: 250px;
        float: left;
	}
    .profile-box>div{
        text-align: center;
        margin: 0 auto;
    }
    .board-box{
        width: 800px;
        height: 531px;
        float: left;
        margin-top: 40px;
        margin-left: 30px;
        border: 1px solid rgb(224, 224, 224);
    }
    .switch{
        height: 31px;
        margin-bottom: 30px;
        background-color: white;
        color: rgb(49, 76, 131);
        border: 1px solid rgb(49, 76, 131);
    }
    .switch:hover{
        background-color: rgb(49, 76, 131);
        color: white;
        border: 1px solid rgb(49, 76, 131);
    }
    .menu{
        padding: 0;
        list-style: none;
    }
    .menu>li{
        margin-bottom: 20px;
    }
    .menu>li>img{
        display: none;
    }
    .menu>li:hover>img{
        display: inline;
    }
    .menu>li:hover>a{
        margin-left: 5px;
        font-weight: bold; 
    }
    .menu>li>a{
        text-decoration: none;
        color: rgb(51, 51, 51);
    }
    .profile-box>p{
        font-size: 18px;
        font-weight: bold;
        color: rgb(49, 76, 131);
    }
    .board-box>p{
        font-size: 18px;
        font-weight: bold;
        color: rgb(49, 76, 131);
        margin-top: 20px;
        margin-left: 30px;
    }
    .board-box>div{
        margin-top: 30px;
        margin-left: 50px;
    }
    .board-box>div>div{
        margin-bottom: 40px;
    }
    .board-box>div:last-child{
        text-align: center;
    }
    .form-box{
        width: 300px;
        height: 31px;
        border-radius: 3px;
        border: 1px solid rgb(204, 204, 204);
        background-color: rgba(224, 224, 224, 0.4);
    }
    .tel{
        width: 90px;
    }
    .btn2{
        width: 135px;
        height: 31px;
        border-radius: 5px;
    }
    .btn2:hover{
        border: 2px solid black;
    }
    .change{
        color: white;
        border: 1px solid rgb(49, 76, 131);
        background-color: rgb(49, 76, 131);
    }
    .delete{
        color: rgb(49, 76, 131);
        border: 1px solid rgb(49, 76, 131);
        background-color: white;
    }
</style>
</head>
<body>
	<div class="header">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
    <div class="page-wrap">
        <div class="profile-box">
            <div><img src="/img/icon/mypage_person.png" style="width: 147px; height: 147px"></div>
            <div style="margin-top: 10px;">낑짱꽁님</div>
            <div style="margin-top: 5px;"><button class="switch">프리랜서로 전환</button></div>
            <p>MY PAGE</p>
            <hr>
            <ul class="menu">
                <li><img src="/img/icon/circle_navy.png" style="display: inline;"><a href="#" style="margin-left: 5px; font-weight: bold; ">나의 프로필</a></li>
                <li><img src="/img/icon/circle_navy.png"><a href="/userHeartList.do">찜한 내역</a></li>
                <li><img src="/img/icon/circle_navy.png"><a href="/userTradeHistory.do">거래 내역</a></li>
            </ul>
        </div>
        
        <div class="board-box">
            <p>나의 프로필</p>
            <div>
                <div class="form-group">
                    <label for="id-label">아이디</label><br>
                    <input type="text" class="form-box" id="id-label" value="abv123" readonly>
                </div>
                <div class="form-group">
                    <label for="name-label">이름</label><br>
                    <input type="text" class="form-box" id="name-label" value="최문정" readonly>
                </div>
                <div class="form-group">
                    <label for="email-label">이메일</label><br>
                    <input type="email" class="form-box" id="email-label" value="ctj123@djasdf.com" readonly>&nbsp;&nbsp;<a href="#">수정</a>
                </div>
                <div class="form-group">
                    <label for="tel-label">연락처</label><br>
                    <input type="tel" class="form-box tel" id="tel-label" value="010"  readonly> - 
                    <input type="tel" class="form-box tel"  value="0100" readonly> - 
                    <input type="tel" class="form-box tel"  value="0101" readonly>&nbsp;&nbsp;<a href="#">수정</a>
                </div>
            </div>
            <div>
                <button class="btn2 change">비밀번호 변경하기</button>&nbsp;&nbsp;<button class="btn2 delete">회원 탈퇴하기</button>
            </div>
        </div>
    </div>

	<br><br><br>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>