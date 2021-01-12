<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 | 의뢰게시판</title>
<style>
    .page-wrap{
        width: 911px;
        margin: 0 auto;
        margin-top: 45px;
    }
	.text-box{
		height : 332px;
        padding-left: 30px;
        background-image: url(/img/icon/request_back.jpg);
        overflow: hidden;
        background-repeat: no-repeat;
        background-size: 911px ;
	}
    .text-box>div:first-child{
        height: 30px;
        padding-top: 30px;
    }
    .text-box>div:last-child{
        padding-top: 70px;
        font-size: 30px;
    }
    .search-box{
		height : 65px;
        margin-top: 40px;
        line-height: 60px;
        border: 1px solid rgb(51, 51, 51);
    }
    .container>span{
        float: left;
        margin-right: 20px;
    }
    .form-control{
        float: left;
        margin-top: 13px;
        margin-right: 20px;
    }
    .btn-custom{
    	width:120px;
        height: 35px;
        color: white;
        border-radius:5px;
        background-color:rgb(49, 76, 131);
        border: 1px solid rgb(49, 76, 131);
    }
    .btn-custom:hover{
    	background-color:#30539c;
    	border: 1px solid black;
    }
    .board-box{
        height: 40px;
        margin-top: 30px;
    }
    .board-box>p{
        float: left;
        line-height: 40px;
    }
    .board-box>div{
        float: right;
        margin-top: -13px;
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
</style>
</head>
<body>
	<div class="header">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
	<div class="page-wrap">
        <div class="text-box">
            <div>홈 > 게시판 > 의뢰게시판</div>
            <div>고객이 직접<br>원하는 의뢰를<br>작성할 수 있습니다</div>
        </div>

        <div class="search-box">
            <div class="container" style="width:81%;">
                <span>검색구분</span>
                <select name="subject" class="form-control subject" style="width:145px;">
                    <option value="all">전체</option>
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                </select>
                <input type="text" class="form-control word" placeholder="검색어를 입력해주세요" name="word" style="width:377px;">
                <input type="submit" class="btn btn-primary btn-md" value="검색">
            </div>
        </div>
        
        <div class="board-box">
            <p>총 &nbsp;&nbsp;</p><p style="color:rgb(255, 143, 63);font-size: 18px;">${totalCount }건</p><p>의 의뢰글이 있습니다.</p>
            <div>
                <select name="array" class="form-control subject">
                    <option value="new">최신순</option>
                    <option value="title">이름순</option>
                    <option value="status">진행순</option>
                </select>
            </div>
            <table class="table table-hover">
                <tr style="background-color: rgba(224, 224, 224, 0.5);">
                    <th style="width: 100px;text-align: center">번호</th>
                    <th style="text-align: center">제목</th>
                    <th style="width: 100px;text-align: center">진행상태</th>
                    <th style="width: 200px;text-align: center">작성일</th>
                </tr>
                <c:forEach items="${list }" var="r">
               	<tr>
               		<td>${r.reqNo } </td>
               		<td><a href="/requestDetail.do?reqNo=${r.reqNo }" class = "table-title">${r.reqTitle } </a></td>
               		<td>
               		<c:choose>
               			<c:when test="${r.reqStatus == 0 }">모집 중</c:when>
               			<c:when test="${r.reqStatus == 1 }">진행 중</c:when>
               			<c:when test="${r.reqStatus == 2 }">마감</c:when>
               		</c:choose>
               		</td>
               		<td> ${r.writeDate } </td>
               	</tr>
                </c:forEach>
            </table>
            <div>
            	<c:if test="${loginMember.MId != null }">
            		<button class="btn-custom" onclick="location.href='/requestWriteFrm.do'">글쓰기</button>
            	</c:if>
            </div>
            <br><br>
            <div class="text-center" style="width:100%; margin:0 auto;">
			<ul class="pagination" >${pageNavi }</ul>
             </div>

            <div>
            </div>
        </div>
    </div>
    
    <br><br><br>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    
    <script>
    	$(document).ready(function(){
    		var containerHeight = $(".board-box").height();
			$(".page-wrap").height(containerHeight+1000);
    	});
    </script>
</body>
</html>