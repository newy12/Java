<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 공지사항</title>

<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180"
   href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
   href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
   href="favicon_io/favicon-16x16.png">


<style>
	.page-wrap {
            width: 80%;
            height : 1000px;
            margin: 0 auto;
            padding-top: 20px;
        }

        .text-box {
        	margin: 0 auto;
        	width: 910px;
            height: 332px;
            padding-left: 30px;
            background-image: url(img/notice/notice.jpg);
            background-size: 910px;
            color: white;
        }

        .text-box>div:first-child {
            height: 30px;
            padding-top: 30px;
        }

        .text-box>div:last-child {
            padding-top: 70px;
            font-size: 30px;
        }

        .search-box {
       		width: 910px;
            height: 65px;
            margin: 0 auto;
            line-height: 60px;
            border: 1px solid rgb(51, 51, 51);
        }

        .searchInput {
            
        }

		.search-container{
			padding-right:250px;
			float: right;
		}
        .search-container>span {
            float: left;
            margin-right: 20px;
        }

        .form-control {
            float: left;
            margin-top: 13px;
            margin-right: 20px;
        }

        .btn-custom {
            width: 120px;
            height: 35px;
            color: white;
            border-radius: 5px;
            background-color: rgb(49, 76, 131);
            border: 1px solid rgb(49, 76, 131);
            font-weight: bold;
        }

        .btn-search {
            border-radius: 5px;
            background-color: rgb(49, 76, 131);
            border: 1px solid rgb(49, 76, 131);
            color: white;
            font-weight: bold;
        }

        .btn-custom:hover,
        .btn-search:hover {
            border: 2px solid rgb(49, 76, 131);
            background-color: transparent;
            color: rgb(49, 76, 131);
            font-weight: bold;

        }

        .board-box {
            height: 40px;
            margin-top: 30px;
        }

        .board-box>p {
            float: left;
            line-height: 40px;
        }

        .board-box>div {
            float: right;
            margin-top: -13px;
        }

        .table {
            text-align: left;
            width: 80%;
        }
        .table-title{
        	text-decoration: none;
        	color: #282828;
        }
         .table-title:hover{
        	text-decoration: none;
        	color: #282828;
        }
        

</style>

</head>
<body>
	<div class="header">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
	<div class="page-wrap">
        <div class="text-box">
            <div>홈 > 게시판 > 공지사항</div>
            <div>공지사항</div>
        </div> 
        <br>
        <br>

        <div class="search-box">
            <div class="search-container" style="margin: 0 auto;">
                <input type="text" class="form-control word searchInput keyword" placeholder="검색어를 입력해주세요" name="word" style="width:377px;">
                <input type="submit" class="btn btn-md btn-search" value="검색">
            </div>
        </div>

        <div class="board-box">
            <br>
            <br>
            <table class="table table-hover">
                <tr style="background-color: rgba(224, 224, 224, 0.5);">
                    <th style="width: 100px;">글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th style="width: 200px;">작성일</th>
                </tr>
                <c:forEach items="${list }" var="n">
                
                	<tr>
                		<td>${n.NNo } </td>
                		<td><a href="/noticeView.do?nNo=${n.NNo }" class = "table-title" style="display: block; height: 100%; width: 100%;">${n.NTitle } </a> </td>
                		<td>일.구.시 </td>
                		<td> ${n.writeDate } </td>
                	</tr>
                </c:forEach>
                <tr>
                	<td colspan="4" class="pageNavi" style="text-align: center;"> ${pageNavi } </td>
                </tr>
                
            </table>
            <div>
	            <c:if test="${loginMember.MGrade == 0 }">
	            	<button class="btn-custom" onclick="location.href='/noticeWriteFrm.do'">글쓰기</button>
	            </c:if>
            </div>
            <div>
            </div>
        </div>
    </div>
    
    <footer>
    	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
    </footer>
 
 <script>
 	$(function () {
		$(".btn-search").click(function() {
			var keyword = $(".keyword").val();
			location.href = "/noticeList.do?reqPage=1&keyword="+keyword;
		})
	})
 
 </script>
 
 
 
</body>
</html>

























