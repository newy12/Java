<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<br>
	<br>
	<br>
	<div class="content">
		<div class="inner">
			<div class="title">
				<div class="title2"></div>
				<p class="tt" >${j.brandName}<br> ${j.MName}</p>
				<p class="tt" id="sRate" ></p>
				<p class="tt" id="listSize">${list}</p>
				<form action="#">
					<button type="submit"
						style="float: right; margin-right: 50px; width: 200px; height: 40px; margin-top: 20px;";>문의하기</button>
				</form>
			</div>
			<div class="menu">
				<ul>
					<li class="ac" style="height: 50px;"><a href="#one"
						style="text-decoration: none;">소개</a></li>
					<li class="ac" style="height: 50px;"><a href="#two"
						style="text-decoration: none;">평가</a></li>
					<li class="ac" style="height: 50px;"><a href="#three"
						style="text-decoration: none;">서비스</a></li>
				</ul>
			</div>
			<hr>
			<div class="middleleft">
				<div id="one">
					<h2>소개</h2>
				</div>
				<br> <br> <br>
				<p>${j.introduce}</p>
			</div>
			<div class="middleright">

				<div class="box">
					<div class="boxtext">
						<h2>활동정보</h2>
						<br>
						<p>등록된 서비스&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${j.serviceList.size()}개</p>
						<br> <br>
						<p>만족도&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;100%</p>
						<br> <br>
						<p>평균 응답 시간&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${j.contactTime}</p>
						<br>
						<h2>전문가 정보</h2>
						<br>
						<p>서비스 지역&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; ㄹ</p>
						<br> <br>
						<p>연락처&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; ${j.MPhone }</p>
						<br> <br>
						<p>이메일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; ${j.MEmail }</p>
					</div>
				</div>
			</div>
			<div class="middle2left">
				<div id="two">
					<h2>평가</h2>
				</div>
				<br>
				<p class="tt" style="margin-left:-3px;" id="sRate2">★★★★★/ 0 </p>
				<p class="tt" id="listSize2"></p>
				<br> <br>
				<p>실제 구매한 이용자들이 남긴 평가입니다.</p>
				<br>
				<hr>
				<c:choose>
				 <c:when test="${j.reviewList.size()==0}">
				 	 <div class="noList" style="width:800px">등록된 평가가 없습니다.</div>
				 </c:when>
				 <c:otherwise>
				<c:forEach items="${j.reviewList}" var ="r">
				<div class="reviewBox">
					<div class="profile-img">
						<img src="/img/test/icon_userProfile.svg" width="100px;">
					</div>		
					<div class="reviewCon">
						<p class="date">${r.writeDate}</p>
						<c:if test="${r.RRate ==0}">
						<p>☆☆☆☆☆</p>
						<p class="rate">평점 :${r.RRate}.0</p>	
						</c:if>
						<c:if test="${r.RRate ==1}">
						<p>★☆☆☆☆</p>
						<p class="rate">평점 :${r.RRate}.0</p>	
						</c:if>
						<c:if test="${r.RRate ==2}">
						<p>★★☆☆☆</p>
						<p class="rate">평점 :${r.RRate}.0</p>	
						</c:if>
						<c:if test="${r.RRate ==3}">
						<p>★★★☆☆</p>
						<p class="rate">평점 :${r.RRate}.0</p>	
						</c:if>
						<c:if test="${r.RRate ==4}">
						<p>★★★★☆</p>
						<p class="rate">평점 :${r.RRate}.0</p>	
						</c:if>
						<c:if test="${r.RRate ==5}">
						<p>★★★★★</p>
						<p class="rate">평점 :${r.RRate}.0</p>	
						</c:if>		
						<hr><br>
						<p class="reviewText">${r.RContent}</p>
						<br><br>
						<hr>
						<p class="userId">작성자 : ${j.MName}</p>
						<br>
					</div>
				</div>
				</c:forEach>
				<div class="pageNavi" style="text-align:center; color:gray; letter-spacing:10px; font-size:15px; margin-top:45px;">${pageNavi}</div>
				</c:otherwise>
				</c:choose>
				<tr>
				
				
			</tr>
			</div>
			<div class="middle2right"></div>
			<div class="middle3">
				<br> <br> <br> <br> <br>
				<div id="three">
					<h2>서비스(${j.serviceList.size()}개)</h2>
				</div>
				<br><br><br>
				<div class="tabContent">
				<c:choose>
				 <c:when test="${j.serviceList.size()==0}">
				 	 <div class="noList" style="width:800px">등록된 서비스가 없습니다.</div>
				 </c:when>
				 <c:otherwise>
						<c:forEach items="${j.serviceList}" var ="r">
				  <div class="serviceBox" style="float:left; margin-left:30px;">
                                <img src="/upload/service/${r.SImg}" alt="없는이미지" style="width:150px;height:150px;"><br>
                                <span class="preName">제목 :${r.STitle }</span><br> 
                                <p class="serviceCon" style="float:left;">내용 :${r.SContent }</p><br>
                                <c:if test="${r.SRate ==0}">
                                <p class="score">평점 ${r.SRate}.0점<span class="star"> ☆☆☆☆☆</span></p>
                                </c:if>
                                <c:if test="${r.SRate ==1}">
                                <p class="score">평점 ${r.SRate}.0점<span class="star"> ★☆☆☆☆</span></p>
                                </c:if>
                                <c:if test="${r.SRate ==2}">
                                <p class="score">평점 ${r.SRate}.0점<span class="star"> ★★☆☆☆</span></p>
                                </c:if>
                                <c:if test="${r.SRate ==3}">
                                <p class="score">평점 ${r.SRate}.0점<span class="star"> ★★★☆☆</span></p>
                                </c:if>
                                <c:if test="${r.SRate ==4}">
                                <p class="score">평점 ${r.SRate}.0점<span class="star"> ★★★★☆</span></p>
                                </c:if>
                                <c:if test="${r.SRate ==5}">
                                <p class="score">평점 ${r.SRate}.0점<span class="star"> ★★★★★</span></p>
                                </c:if>        
                            </div>
                            </c:forEach>  
                            </c:otherwise>
                            </c:choose>             
				</div>	
				</div>
		</div>
		<input type="hidden" id="haha" value="${j.MId}">
	</div>
	<a id="topBtn" href="#"
		style="width: 100px; height: 100px; line-height: 100px; background-color: gray; border-radius: 25%; text-decoration: none; text-align: center;">Top</a>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
<style>
  .tabcontent {
            width: 100%;
            height: 800px;
            text-align: left;
            padding: 20px;
            font-size: 8pt;
        }

</style>
<script>
$(function(){
	var mId = $('#haha').val();
    $(window).scroll(function(){
      if($(this).scrollTop() > 200){
        $('#topBtn').fadeIn(); 
      }
      else{$('#topBtn').fadeOut();
    }
    });
    $('#topBtn').click(function(){
      $('html, body').animate({ scrollTop : 0}, 300);
      return false; 
    });
    
    
    //새 jsp(reviewListSize.jsp)파서 값 불러내고 다시 불러오는 방식 ajax 평가 개수구하기
    $.ajax({
    	type : "get",
    	url : "/reviewListSize.do",
    	data : {mId : mId},
    	success :  function(result){
    				console.log(result);
    			$('#listSize').html(result+"개의 평가").css("color","gray");
    			$('#listSize2').html(result+"개의 평가").css("color","gray").css("margin-left","0px");
    	} 	
    });
     //평점 평균 구하기 ajax
 /*    $.ajax({
    	type : "get",
    	url : "/sRateAVG.do",
    	data : {mId : mId},
    	success : function(result){
    		if(result='null'){
    			$('#sRate').html("평점0.0").css("color","gray");	
    			$('#sRate2').html("평점0.0").css("color","gray");
    		}else{
    			$('#sRate').html("평점"+result).css("color","gray");	
    			$('#sRate2').html("평점"+result).css("color","gray");
    		}
    				
    	}   	
    }); */
    
     
    
    
    
});





</script>
<style>
.noList{
    margin-top: 5px;
    text-align: center;
   	line-height: 185px;
   	color :rgb(224, 224, 224);
   	font-size:20px;
   	font-weight: bold; 
}
.content {
	height: 3400px;
}

body, hr, p, ul {
	margin: 0;
}

p.tt {
	margin-left: 100px;
}

a {
	color: black;
}

li.ac {
	list-style: none;
	float: left;
	margin-left: -30px;
	margin-right: 80px;
	font-size: 20px;
}

div.inner {
	width: 1200px;
	margin: 0 auto;
}

div.title {
	width: 100%;
	height: 200px;
}

div.title2 {
	width: 100%;
	height: 90px;
}

div.menu {
	width: 100%;
	height: 50px;
}

div.middleleft {
	width: 70%;
	height: 750px;
	float: left;
}

div.middleright {
	width: 30%;
	height: 750px;
	float: left;
}

div.boxinner {
	width: 280px;
	margin: 0 auto;
}

div.box {
	width: 300px;
	height: 550px;
	border: 1px solid black;
	margin-left: 60px;
	margin-top: 180px;
}

div.boxtext {
	margin-left: 30px;
}

div.middle2left {
	width: 70%;
	float: left;
}

div.middle2list {
	width: 100%;
	height: 300px;
}

div.middle2right {
	width: 30%;
	height: 1150px;
	float: left;
}

div.middle3 {
	width: 70%;;
	float: left;
}

div.middle4 {
	width: 70%;
	height: 300px;
	float: left;
}

#topBtn {
	position: fixed;
	right: 50px;
	bottom: 100px;
	z-index: 9;
}

</style>
</body>
</html>