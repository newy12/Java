<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 찜한 리스트</title>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
<style>
    .page-wrap {
        width: 1104px;
        margin: 0 auto;
        margin-top:50px;
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

    .container-box {
        margin-top: 20px;
        width: 800px;
        height: 1000px;
    }
    
.board-box>span {
    font-size: 18px;
    font-weight: bold;
    color: rgb(49, 76, 131);
    line-height: 45px;
    margin-left: 30px;
}

.array {
    width: 120px;
    height: 31px;
    float: right;
    margin-top: 7px;
    margin-right: 10px;
    border: 1px solid rgb(204, 204, 204);
}

.container-box>div {
    width: 225px;
    height: 270px;
    float: left;
    margin: 0px 20px 40px 20px;
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
    width: 225px;
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
    overflow: hidden;
    display: -webkit-box;
    -webkit-line-clamp: 2; 
    -webkit-box-orient: vertical;

}
.price{
	width:100%;
    text-align:right;
    font-size: 17px;
    font-weight: bold;
    color: rgb(51, 51, 51);
}
.container>div>a{
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
.noList{
    margin-top: 5px;
    text-align: center;
   	line-height: 185px;
   	color :rgb(224, 224, 224);
   	font-size:20px;
   	font-weight: bold; 
}
</style>
</head>
<body>
	<div class="header">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
    <div class="page-wrap">
        <!-- 사이드메뉴 -->
        <jsp:include page="/WEB-INF/views/member/mypage-side.jsp" />
        
	     <div class="board-wrap">
	        <div class="board-box">
	            <span>찜한 서비스</span>
	            <input type="text" style="display:none" value="${loginMember.MNo }" id="mNo">
	            <input type="text" style="display:none" value="${order }" id="order">
	            <select name="array" class="array" id="array-select">
	                <option value="all">전체</option>
	                <option value="priceDown">가격낮은순</option>
	                <option value="priceUp">가격높은순</option>
	                <option value="new">서비스최신순</option>
	            </select>
	        </div>
	
	        <div class="container-box">
			<c:choose>
				<c:when test="${list.size() == 0}">
					<div class="noList" style="width:800px">찜한 서비스가 없습니다</div>
				</c:when>
				<c:otherwise>
					<!-- 여기부터 반복 -->
		            <c:forEach items="${list }" var="s" varStatus="status">
		            <div>
		                <div>
		                    <a href="/serviceView.do?sNo=${s.SNo }&reqPage=1&mNo=${loginMember.MNo}">
		                        <div class="title-img">
		                            <div class="back-img">
		                            	<img src="/upload/service/${s.SImg }" width="225x" height="133px">
		                            </div>
		                        </div>
		                    </a>
		                    <div class="title-img heart-btn">
		                    	<img id="service1" src="/img/icon/heart_orange.png" width="31px" height="31px" onclick="heart_click(this)" value="fill">
	                            <input type="text" style="display:none" value="${s.SNo }" >
	                            <input type="text" style="display:none" value="${loginMember.MNo }">
		                    	</div>
		                </div>
		                <div class="empty"></div>
		                <div class="title">${brandList[status.index] }</div>
		                <a href="/serviceView.do?sNo=${s.SNo }&reqPage=1&mNo=${loginMember.MNo}" id="serviceContent">
		                    <div class="content">${s.STitle } </div>
		                    <div class="price">${s.SPriceTxt }</div>
		                    <div class="rate"><span>평점 </span><span>${s.SRate }</span><span>.0점</span><span>★★★★★</span>
		                    </div>
		                </a>
		            </div>
		            </c:forEach>
				</c:otherwise>
			</c:choose>
	        </div>
	    </div>
	</div>

	<br><br><br>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />


    <script>
        function heart_click(obj){
        	var sNo = $(obj).next().val();
            var mNo = $(obj).next().next().val();
            if($(obj).attr('value') == "fill"){
                $.ajax({
                	url : "/deleteHeart.do",
                	type : "get",
                	data : {"sNo":sNo, "mNo":mNo},
                	success : function(data){
                		$(obj).attr('src','/img/icon/heart_navy.png');
                        $(obj).attr('value', "empty");
                	},
                	error : function(){
                		console.log("오류");
                	}
                });
            }else{
            	$.ajax({
            		url : "/insertHeart.do",
            		type : "get",
            		data : {"sNo":sNo, "mNo":mNo},
            		success : function(data){
            			$(obj).attr('src','/img/icon/heart_orange.png');
                        $(obj).attr('value', "fill");
            		},
            		error : function(){
            			console.log('오류');
            		}
            	});
               
            }

        }
        
        $(document).ready(function(){
        	//메뉴 고정
	   		 $(".menu").children().eq(1).find('a').css({'margin-left':'5px', 'font-weight':'bold'});
	   		 $(".menu").children().eq(1).find('img').css({'display':'inline'});
   		 
        	$('.rate').each(function(index){
       			var rate = $('.rate').eq(index).children().eq(1).html();
       			var rateText = "";
       			for(var i =5; i>0; i--){
      	   			 if(i>rate){
      	   				 rateText = rateText + "☆";
      	   			 }else{
      	   				 rateText = rateText + "★"; 
      	   			 }
      	   		 } 
       			$('.rate').eq(index).children().eq(3).html(rateText);
       		 });
        	
        	var order = $("#order").val();
	        	if(order == "priceDown"){
	        		$("#array-select").val("priceDown").prop("selected", true);
	        	}else if(order == "priceUp"){
	        		$("#array-select").val("priceUp").prop("selected", true);
	        	}else if(order == "new"){
	        		$("#array-select").val("new").prop("selected", true);
	        	}else{
	        		$("#array-select").val("all").prop("selected", true);
	        	}
	        	
	        	//높이 동적으로 늘려줌
	        	var containerHeight = $(".container-box").height();
				$(".page-wrap").height(containerHeight+400);
				
        });
        
        $("#array-select").change(function(){
        	var order = $("#array-select").val();
        	var mNo = $("#mNo").val();
        	location.href = "/userHeartList.do?mNo="+mNo+"&order="+order;
        	console.log(order+"/"+mNo);
        });
    </script>
</body>
</html>