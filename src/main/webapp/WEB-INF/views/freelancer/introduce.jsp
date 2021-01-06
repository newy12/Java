<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<br><br><br>
<div class="content">
      <div class="inner">
        <div class="title">
         <div class="title2"></div>
         <p class="tt">${j.MName}</p>
         <p class="tt">★★★★★ ${j.RRate}.0/ n개의 평가</p>
         <form action="#">
         <button type="submit" style="float: right; margin-right: 50px; width: 200px; height: 40px; margin-top:20px;";>문의하기</button>
        </form>     
        </div>
        <div class="menu">
            <ul>
               <li style="height: 50px;"><a href="#one" style="text-decoration: none;">소개</a></li> 
               <li style="height: 50px;"><a href="#two" style="text-decoration: none;">평가</a></li>
               <li style="height: 50px;"><a href="#three" style="text-decoration: none;">서비스</a></li>
            </ul>
        </div> 
        <hr>
        <div class="middleleft">
          <div id="one">
          <h2>소개</h2>
        </div>
          <br><br><br>
          <p>${j.introduce}</p>
        </div>
        <div class="middleright">
          
          <div class="box">
            <div class="boxtext">
            <h2>활동정보</h2> 
            <br>
            <p>총 작업 수&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              ${j.workingCount}개</p>
              <br><br>
            <p>만족도&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;100%
              </p>
              <br><br>
              <p>평균 응답 시간&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                1시간
                </p>
                <br>
                <h2>전문가 정보</h2>
                <br>
                <p>서비스 지역&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  &nbsp;&nbsp;&nbsp;&nbsp;
                 ${j.SArea}</p>
                 <br><br>
                 <p>연락처&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  &nbsp;&nbsp;
                 ${j.MPhone}</p>
                 <br><br>
                 <p>이메일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  &nbsp;&nbsp;
                 
                 ${j.MEmail}</p>
          </div>
          </div>
        </div>
        <div class="middle2left">
          <div id="two">
        <h2>평가</h2>
      </div>
        <br>
         <p>★★★★★ ${j.RRate}.0/ n개의 평가</p>
         <br><br>
         <p>실제 구매한 이용자들이 남긴 평가입니다.</p>
         <br>
         <hr>
         <div class="middle2list">
           <br><br><br>
           <p>★${j.RRate}.0 / 2020-12-29 / 김**</p>
           <br><br>
           <p>얼음이 있을 뿐이다 그들에게 생명을 불어 넣는 것은 따뜻한 봄바람이다 풀밭에 속잎나고 가지에 싹이 트고 꽃 피고 새 우는 봄날의 천지는 얼마나 기쁘며 얼마나 
             아름다우냐? 이것을 얼음 속에서 불러 내는 것이 따뜻한</p>
             <br><br><br><br><br><br><br><br>
             <hr>
         </div>
         <div class="middle2list">
          <br><br><br>
          <p>★5.0 / 2020-12-29 / 김**</p>
          <br><br>
          <p>얼음이 있을 뿐이다 그들에게 생명을 불어 넣는 것은 따뜻한 봄바람이다 풀밭에 속잎나고 가지에 싹이 트고 꽃 피고 새 우는 봄날의 천지는 얼마나 기쁘며 얼마나 
            아름다우냐? 이것을 얼음 속에서 불러 내는 것이 따뜻한</p>
            <br><br><br><br><br><br><br><br>
             <hr>
        </div>
        <div class="middle2list">
          <br><br><br>
          <p>★5.0 / 2020-12-29 / 김**</p>
          <br><br>
          <p>얼음이 있을 뿐이다 그들에게 생명을 불어 넣는 것은 따뜻한 봄바람이다 풀밭에 속잎나고 가지에 싹이 트고 꽃 피고 새 우는 봄날의 천지는 얼마나 기쁘며 얼마나 
            아름다우냐? 이것을 얼음 속에서 불러 내는 것이 따뜻한</p>
            <br><br><br><br><br><br><br><br>
             <hr>
             <br><br><br><br><br><br><br><br>
        </div>
        </div>
        <div class="middle2right"></div>
        <div class="middle3">
          <br><br><br><br><br>
          <div id="three">
        <h2>서비스(n개)</h2>
      </div>
      <div class="serviceadd">
      		${j.SImg}
      </div>
      </div> 
      <div class="middle4">
        <h2>전문분야 및 상세 분야</h2>
        <br><br>
        <p>마케팅</p>
        <br><br>
        <h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;포털마케팅</h5>
      </div> 
      <div class="middle4">
        <h2>보유 기술</h2>
        <br><br>
        <h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;검색최적화
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;웹사이트
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;쇼핑몰 마케팅
          
        </h5>
      </div>   
      </div>
      </div>
     <a id="topBtn" href="#" style="width:100px; height:100px; line-height: 100px;
      background-color: gray; border-radius: 25%; 
      text-decoration: none; text-align: center;">Top</a>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />      
</body>

<script>
$(function(){
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
});
</script>
<style>
.content{
height:3600px;}
body,hr,p,ul{
    margin:0;
}
p.tt{
    margin-left:100px;
}
a{
    color:black;
}
li{
    list-style: none;
    float:left;
    margin-left:-30px;
    margin-right:80px;
    font-size:20px;
}
div.inner{
    width:1200px;
    margin: 0 auto;
}
div.title{
    width: 100%;
    height: 200px;
}
div.title2{
    width:100%;
    height:90px;
}
div.menu{
    width:100%;
    height:50px;
}
div.middleleft{
    width:70%;
    height:750px;
    float:left;
}
div.middleright{
    width:30%;
    height:750px;
    float:left;
}
div.boxinner{
    width:280px;
    margin:0 auto;
}
div.box{
    width:300px;
    height:550px;
    border: 1px solid black;
    margin-left:60px;
    margin-top:180px; 
}
div.boxtext{
    margin-left:30px;
}
div.middle2left{
    width:70%;
    float:left;
}
div.middle2list{
    width:100%;
    height:300px;
}
div.middle2right{
    width:30%;
    height:1150px;
    float:left;
}
div.middle3{
    width:70%;
    height:600px;
    float:left;
}
div.middle4{
    width:70%;
    height:300px;
    float:left;
}
#topBtn{
    position:fixed;
    right:50px;
    bottom:100px;
    z-index:9;
}
</style>
</body>
</html>