<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .page-wrap{
        width: 911px;
        height:1000px;
        margin: 0 auto;
        margin-top: 45px;
    }
    .btn{
        background-color:black;
    }
    .board-box{
        height: 40px;
        margin-top: 30px;
    }
    .board-box>div{
        float: right;
        margin-top: -13px;
    }
    .table{
        text-align: center;
    }
</style>
</head>
<body>
	<div class="header">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
	<div class="page-wrap">
        <div class="text-box">
            <div>홈 > 게시판 > 의뢰게시판 > 글쓰기</div>
            <div>고객의 작성한<br>의뢰를 확인해보세요</div>
        </div>
        
        <div class="board-box">
            <div>
                <select name="array" class="form-control subject">
                    <option value="new">최신순</option>
                    <option value="title">이름순</option>
                    <option value="status">진행순</option>
                </select>
            </div>
            <table class="table">
                <tr style="background-color: rgba(224, 224, 224, 0.5);">
                    <th style="width: 100px;">순서</th>
                    <th>제목</th>
                    <th style="width: 100px;">진행상태</th>
                    <th style="width: 200px;">작성일</th>
                </tr>
            </table>
            <div>
            	<button class="btn btn-primary">글쓰기</button>
            </div>
            <div>
            	
            </div>
        </div>
    </div>
    
	<br><br><br>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>