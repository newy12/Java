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
        height:1200px;
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
            <div style="margin-top: 10px;">${loginMember.MName }</div>
            <div style="margin-top: 5px;"><button class="switch">사용자로 전환</button></div>
            <p>MY PAGE</p>
            <hr>
            <ul class="menu">
                <li><img src="/img/icon/circle_navy.png" style="display: inline;"><a href="#" style="margin-left: 5px; font-weight: bold; ">나의 프로필</a></li>
                <li><img src="/img/icon/circle_navy.png"><a href="/freelancerServiceList.do?mId=${loginMember.MId}">서비스 내역</a></li>
                <li><img src="/img/icon/circle_navy.png"><a href="/freelancerTradeHistory.do?mNo=${loginMember.MNo}">거래 내역</a></li>
            </ul>
        </div>
        
        <div class="board-box">
            <p>나의 프로필</p>
            <div>
            <form action="/updateFreelancer.do" method="post">
                <div class="form-group">
                    <label for="id-label">아이디</label><br>
                    <input type="text" class="form-box" id="id-label" value="${loginMember.MId}" name="mId" readonly>
                </div>
                <div class="form-group">
                    <label for="name-label">프리랜서명</label><br>
                    <input type="text" class="form-box" id="name-label" value="${loginMember.MName}" name="mName" readonly>
                </div>
                <div class="form-group">
                    <label for="email-label">이메일</label><br>
                    <input type="email" class="form-box" id="email-label" value="${loginMember.MEmail}" name="mEmail" readonly>&nbsp;&nbsp;<a href="#"></a>
                </div>
     
      
                <div class="form-group">
                    <label for="tel-label">연락처</label><br>
                    <input type="tel" class="form-box tel" id="tel-label" style="width:300px;" value="${loginMember.MPhone}" name="mPhone"  readonly> 
                
                </div>
                
                	<label for="tel-label">브랜드 명</label><br>
                	<input type="text" placeholder="브랜드명을 입력해주세요." value="${m.brandName }" name="brandName"><br><br>
                	<label for="tel-label">연락 가능한 시간</label><br>
                	<input type="text" placeholder="ex)1시간 내외" name="contactTime" value="${m.contactTime}"><br><br>
               		 <label for="tel-label">소개</label><br>
              <%-- <textarea name="introduce" cols="82" rows="7" placeholder="50자내외" value="${m.introduce}"></textarea> --%>
                	<input type="text" style="width:635px ; height:325px;" name="introduce" value="${m.introduce}"><br><br>

            </div>
            <br><br><br><br><br><br><br><br><br><br>
            <div>
                <button class="btn2 change" type="submit">정보 수정하기</button></form>&nbsp;&nbsp;
            </div>
        </div>
    </div>
<br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>