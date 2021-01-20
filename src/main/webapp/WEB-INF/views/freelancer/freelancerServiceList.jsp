<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찜한 리스트</title>

<style>
    .page-wrap {
        width: 1104px;
        height:1000px;
        margin: 0 auto;
        margin-top:50px;
    }

    .profile-box {
        width: 250px;
        float: left;
    }

    .profile-box>div {
        text-align: center;
        margin: 0 auto;
    }

    .board-wrap {
        width: 800px;
        margin: 0 auto;
        margin-top: 40px;
        margin-left: 30px;
        float: left;
    }

    .board-box {
        height: 45px;
        border: 1px solid rgb(224, 224, 224);
    }

    .ListContainer {
        margin-top: 20px;
        width: 795px;
        height: 1000px;
    }

    .switch {
        height: 31px;
        margin-bottom: 30px;
        color: rgb(49, 76, 131);
        background-color:white;
        border: 1px solid rgb(49, 76, 131);
        border-radius: 3px;
    }

    .switch:hover {
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

.profile-box>p {
    font-size: 18px;
    font-weight: bold;
    color: rgb(49, 76, 131);
}

.board-box>span {
    font-size: 18px;
    font-weight: bold;
    color: rgb(49, 76, 131);
    line-height: 45px;
    margin-left: 30px;
}

.array {
    width: 100px;
    height: 31px;
    float: right;
    margin-top: 7px;
    margin-right: 10px;
    border: 1px solid rgb(204, 204, 204);
}

.ListContainer>div {
    width: 230px;
    height: 270px;
    float: left;
    margin:10px;
}

.title-img {
    position: relative;
    top: 0;
    left: 0;
}

.back-img>img:hover {
    transform: scale(1.1);
    overflow: hidden;
}

.title-img>img {
    position: absolute;
}

.back-img {
    width: 224px;
    height: 133px;
    overflow: hidden;
    position: absolute;
    border-radius: 5px;
}

.back-img>img {
    transform: scale(1);
    transition: transform 0.5s linear;
}

.heart-btn {
    top: 10px;
    left: 180px;
    z-index: 1000;
}

.empty {
    width: 199px;
    height: 135px;
}

.heart-btn:hover {
    background-image: url(heart_navy.png);
    background-repeat: no-repeat;
}
.title{
     color: rgb(127, 127, 127);
    font-size: 13px;
    margin-top: 5px;"
}
.content{
    font-size: 15px;
    margin-top: 10px;
    margin-bottom: 10px;
    text-overflow: ellipsis;
    color: rgb(51, 51, 51);
    /* overflow: hidden; */
    display: -webkit-box;
    -webkit-line-clamp: 2; 
    -webkit-box-orient: vertical; 

}
.price{
    float: right;
    font-size: 17px;
    font-weight: bold;
    color: rgb(51, 51, 51);
}
.ListContainer>div>a{
    text-decoration: none;
}
.rate{
    float: right;
}
.rate>span{
    color: rgb(51, 51, 51);
    font-size: 13px;
}
.rate>span:last-child{
    font-size: 17px;
    color: rgb(241, 196, 15);
}
.inner{
	width:1200px;
	margin:0 auto;
	padding-left: 30px;
	padding-right: 30px;
}
.noList{
    margin-top: 5px;
    text-align: center;
   	line-height: 185px;
   	color :rgb(224, 224, 224);
   	font-size:20px;
   	font-weight: bold; 
}
.del-btn{
	height: 30px;
	width: 100px;
	border: 2px solid #314C83;
	background-color: transparent;
	color: #314C83;
	font-weight: bold;
	border-radius: 5px;
	float: right;
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
			<input type="hidden" class="memberId" value="${loginMember.MId }">
			<div style="margin-top: 10px;"> [${loginMember.MId}]님</div>
            <div style="margin-top: 5px;"><button class="switch">일반회원으로 전환</button></div>
            <p>MY PAGE</p>
            <hr>
            <ul class="menu">
                <li><img src="/img/icon/circle_navy.png" ><a href="/freelancerMypage.do?MNo=${loginMember.MNo}">나의 프로필</a></li>
                <li><img src="/img/icon/circle_navy.png" style="display: inline;"><a href="#" style="margin-left: 5px; font-weight: bold; ">서비스 내역</a></li>
                <li><img src="/img/icon/circle_navy.png"><a href="/freelancerTradeHistory.do?mNo=${loginMember.MNo}">거래 내역</a></li>
            </ul>
        </div>
        
	     <div class="board-wrap">
	        <div class="board-box">
	            <span>서비스 내역</span>
	            <input type="hidden" id="order" value="${order }">
	            <select class="selectBox form-control array" id="search-subject" style="width: 180px;">
	                <option value="agree">승인된 서비스</option>
	                <option value="refuse">승인 거절된 서비스</option>
	            </select>
	        </div>
			<div class="inner">
		        <div class="ListContainer">
		        <c:choose>
			        <c:when test="${j.serviceList.size() == 0}">
			        	<div class="noList" style="width:800px">등록된 서비스가 없습니다.</div>
			        </c:when>
			        <c:otherwise>
				        <c:forEach items="${list}" var ="s" begin="0" end="4" >
				            <div>
				                <div>
				                	<input type="hidden" value="${s.SNo }" id="sNo">
				                    <a href="#">
				                        <div class="title-img">
				                            <div class="back-img"><img src="/upload/service/${s.SImg}" width="225x" height="133px"></div>
				                        </div>
				                    </a>
				                   
				                </div>
				                <div class="empty"></div>
				                <div class="title"> ${s.STitle}</div>
				                <a href="#">
				                    <div class="content"> ${s.SContent } </div>
				                </a>
				                <button  class="del-btn" value="삭제하기" onclick="del();">삭제하기</button>
				            </div>
				         </c:forEach>
			        </c:otherwise>
		        </c:choose>
		        
		        </div>
	        </div>
	    </div>
	</div>

	<br><br><br>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />


    <script>
    
    $(document).ready(function () {
		var order = $(".array").val();
		var id = $(".memberId").val;
		console.log("order:"+order+"/id:"+m_id);
		
		if(order == "agree"){
			$(".array").val("agree").prop("selected",true);
		}else if ( order == "refuse")
			$(".array").val("refuse").prop("selected",true);
		
		$(".array").change(function () {
			var order = $(".array").val();
			console.log("mId:"+ mId);
			location.href="freelancerServiceList.do?mId="+mId+"&order="+order;
		})
		
		
    
    
    })
    
    function del(){
    		var sNo = $("#sNo").val();
    		console.log("sNo :"+sNo);
    		var confirm_test = confirm("해당 서비스를 삭제할까요?");
    		
    		if(confirm_test == true){
    			location.href = "/delService.do?sNo="+sNo;
    		}
    		
    	};
	
    	

    </script>
</body>
</html>