<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 작성한 의뢰글</title>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
</head>
<style>
    .page-wrap {
        width: 1104px;
        height:800px;
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
    
 

    .board-box>span {
        font-size: 18px;
        font-weight: bold;
        color: rgb(49, 76, 131);
        line-height: 45px;
        margin-left: 30px;
    }
    .container-box {
        margin-top: 20px;
        width: 800px;
    }
    .table{
        text-align: center;
        border-bottom: 1px solid rgb(230, 230, 230);
    }
    .table>th{
    	text-align: center;
    	
    }
     .pagination{
    	justify-content: center;
    }
    
    .pagination > li > a{
    	color : black;
    }
    .target{
    	color : white;
    }
	.noList{
	    margin-top: 5px;
	    text-align: center;
	   	line-height: 185px;
	   	color :rgb(224, 224, 224);
	   	font-size:20px;
	   	font-weight: bold; 
	}
	.btn-custom{
		margin:8px 8px;
		float:right;
    	width:60px;
        height: 25px;
        border: 1px solid rgb(224, 224, 224);
    }
    .btn-custom:hover{
    	background-color:rgb(224, 224, 224);
    }
</style>

<body>
	<div class="header">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
    <div class="page-wrap">
        <!-- 사이드메뉴 -->
        <jsp:include page="/WEB-INF/views/member/mypage-side.jsp" />
        
        <div class="board-wrap">
            <div class="board-box">
                <span>내가 작성한 의뢰 글</span>
                <button class="btn-custom" onclick="location.href='/requestWriteFrm.do?position=mypage'">글쓰기</button>
            </div>
            <div class="container-box">
				<table class="table table-hover">
	                <tr style="background-color: rgba(224, 224, 224, 0.5);">
	                    <th style="width: 100px;text-align: center">번호</th>
	                    <th style="text-align: center">제목</th>
	                    <th style="width: 100px;text-align: center">진행상태</th>
	                    <th style="width: 200px;text-align: center">작성일</th>
	                </tr>
	                <c:if test="${list.size() != 0 }">
		                <c:forEach items="${list }" var="r" varStatus="status">
		                <c:if test="${r.MId == loginMember.MId }">
							<tr>
			               		<td>${status.count }</td>
			               		<td><a href="/requestDetail.do?reqNo=${r.reqNo }&position=mypage" class = "table-title">${r.reqTitle } </a></td>
			               		<td>
			               		<c:choose>
			               			<c:when test="${r.reqStatus == 0 }">모집 중</c:when>
			               			<c:when test="${r.reqStatus == 1 }">진행 중</c:when>
			               			<c:when test="${r.reqStatus == 2 }">마감</c:when>
			               		</c:choose>
			               		</td>
			               		<td> ${r.writeDate } </td>
			               	</tr>
		                </c:if>
		                </c:forEach>
	                </c:if>
	            </table>
	            <div>
            		<br>
           		</div>
            	<br><br>
            	<div class="text-center" style="width:100%; margin:0 auto;">
					<ul class="pagination" ></ul>
             	</div>
           	<div></div>
        </div>
	</div>
	</div>
	<br><br><br>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    
    <script>
	 document.addEventListener('DOMContentLoaded', function() {
		 //메뉴 고정
		 $(".menu").children().eq(3).find('a').css({'margin-left':'5px', 'font-weight':'bold'});
		 $(".menu").children().eq(3).find('img').css({'display':'inline'});
	 });
    </script>
</body>
</html>