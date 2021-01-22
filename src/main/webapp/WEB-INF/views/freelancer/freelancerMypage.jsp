<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 프리랜서 프로필</title>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
<style>
    .page-wrap{
        width: 1104px;
        margin: 0 auto;
        margin-top:50px;
    }
    .board-box{
        width: 800px;
        height:750px;
        float: left;
        margin-top: 40px;
        margin-left: 30px;
        border: 1px solid rgb(224, 224, 224);
    }
    .board-box>p{
        font-size: 18px;
        font-weight: bold;
        color: rgb(49, 76, 131);
        margin-top: 20px;
        margin-left: 30px;
        float:left;
        height:20px;
    }
    .board-box>div{
        margin-top: 30px;
        margin-left: 50px;
    }
    .board-box>div>div{
        margin-bottom: 40px;
    }
    .btn-box{
        text-align: center;
    }
    .form-box{
        width: 300px;
        height: 31px;
        border-radius: 3px;
        border: 1px solid rgb(204, 204, 204);
    }
    .form-text{
    	border-radius: 3px;
    	border: 1px solid rgb(204, 204, 204);
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
    .form-group{
    	margin-top: 40px;
    }
    .noticeTxt{
    	color:#c22517;
    	font-size: 12px;
    }
    .brand{
    	margin-top: 70px;
    }
    .introduce-btn:focus { 
    	outline:none; 
    }
    .introduce-btn{
        width: 150px;
        height: 27px;
    	float:right;
    	font-size:12px;
    	line-height:27px;
    	font-weight:bold;
    	color:rgb(49, 76, 131);
    	margin : 15px 15px 0 0;
    	background-color: white;
    	border-radius:15px;
    	border: 1px solid rgb(49, 76, 131);
    }
    .introduce-btn:hover{
    	box-shadow: 1px 1px 5px 1px #e8e8e8;
    }
</style>
</head>
<body>
	<div class="header">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
    <div class="page-wrap">

        <!-- 사이드메뉴 -->
        <jsp:include page="/WEB-INF/views/freelancer/freelancer-side.jsp" />

        <div class="board-box">
            <p>프리랜서의 프로필 <span class="noticeTxt">※아래 정보를 입력해야 서비스를 등록할 수 있습니다.</span></p>
            <c:if test="${loginMember.brandName != null and serviceCount != 0 }">
            	<button class="introduce-btn">나의 소개페이지 보기</button>
            </c:if>
            <div>
            <form action="/updateFreelancer.do?" method="post">
                <div class="form-group brand">
                    <label for="tel-label">브랜드 명</label><br>
                    <input type="hidden" value="${loginMember.MId }" name="mId" id="mId">
                    <input type="hidden" value="${loginMember.MPw }" name="mPw">
                    <input type="text" class="form-box" placeholder="브랜드명을 입력해주세요." value="${loginMember.brandName }" name="brandName" maxlength="10" required="required">
                </div>
                <div class="form-group">
                    <label for="tel-label">연락 가능한 시간</label><br>
                    <input type="text" class="form-box" placeholder="ex)16:00~20:00" name="contactTime" value="${loginMember.contactTime}"  maxlength="20" required="required">
                </div>
                <div class="form-group">
                    <label for="tel-label">소개</label><br>
                    <textarea rows="17" cols="93" class="form-text" name="introduce"  maxlength="1333" required="required">${loginMember.introduce}</textarea>
                </div>
	            <div class="btn-box">
	            	<button class="btn2 change" type="submit">
	            	<c:choose>
	            		<c:when test="${loginMember.brandName == null }">프로필 등록하기</c:when>
	            		<c:otherwise>수정하기</c:otherwise>
	            	</c:choose>
	                </button>
	            </div>
            </form>
        </div>
    </div>
</div>
	
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

<script>
	document.addEventListener('DOMContentLoaded', function() {
		//메뉴 고정
		 $(".menu").children().eq(0).find('a').css({'margin-left':'5px', 'font-weight':'bold'});
		 $(".menu").children().eq(0).find('img').css({'display':'inline'});
		 
		 //높이 조절
		 var containerHeight = $(".board-box").height();
		 $(".page-wrap").height(containerHeight+300);
	});
	
	//소개글로 이동
	$(".introduce-btn").click(function(){
		var mId = $("#mId").val();
		location.href = "/introduceFrm.do?mId="+mId+"&reqPage=1";
	});
		

</script>

</body>
</html>