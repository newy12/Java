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
    .form-control{
        float: left;
        margin-top: 13px;
        margin-right: 20px;
    }
    .btns{
        width:80px;
        height: 30px;
    	float:right;
    	margin-left:10px;
    	border-radius:5px;
    }
    .btn-back, .btn-delete{
        color: rgb(49, 76, 131);
        border: 1px solid rgb(49, 76, 131);
    }
    .btn-update{
        color: white;
        background-color:rgb(49, 76, 131);
        border: 1px solid rgb(49, 76, 131);
    }
    .btns:hover{
    	border: 2px solid black;
    }
    .board-box{
        margin-top: 30px;
    }

    .reqContent{
        padding: 0 20px 20px 20px;
        margin-bottom: 20px;
        border-bottom: 2px solid rgb(49, 76, 131);
    }
    .title{
        background-color: rgba(224, 224, 224, 0.5);
    }
    .contact-user{
        width: 124px;
        height: 26px;
        font-size: 12px;
        color: rgb(49, 76, 131);
        border-radius: 5px;
        border: 2px solid rgb(49, 76, 131);
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
            <div>홈 > 게시판 > 의뢰게시판 > 상세보기</div>
            <div>고객의 작성한<br>의뢰를 확인해보세요</div>
        </div>
        
        <div class="board-box">
            <table class="table title">
                <tr>
                    <th style="width: 10%;border-top: 2px solid rgb(49, 76, 131);text-align: center;">제목</th>
                    <th style="width: 75%;border-top: 2px solid rgb(49, 76, 131);">${req.reqTitle }</th>
                    <th style="width: 15%;border-top: 2px solid rgb(49, 76, 131);text-align: center;">${req.writeDate }</th>
                </tr>
                <tr>
                    <th style="border-top: 0px;text-align: center;">작성자</th>
                    <th style="border-top: 0px">${req.MId } &nbsp;&nbsp;&nbsp;<button class="contact-user">고객에게 연락하기</button></th>
                    <th style="border-top: 0px"></th>
                </tr>
                <tr>
                    <th style="border-top: 0px;text-align: center;">첨부파일</th>
                    <th style="border-top: 0px">
                    <c:if test="${req.filepath==null }">
                    	첨부파일이 없습니다.
                    </c:if>
                    <c:if test="${req.filepath!=null }">
                    	<a href="#">${req.filename }</a>
                    </c:if>
                    </th>
                    <th style="border-top: 0px"></th>
                </tr>
            </table>
            <div class="reqContent">${req.reqContent }</div>
            <div class="btn-box">
            	<button class="btns btn-back" onclick="history.back()">목록으로</button>
            	<button class="btns btn-delete">삭제하기</button>
            	<button class="btns btn-update">수정하기</button>
            </div>
            <div>
            	
            </div>
        </div>
    </div>
    
	<br><br><br>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    
    <script>
    	$(document).ready(function(){
    		var reqContent = $(".reqContent").height();
    		$(".reqContent").height(reqContent);
    		$(".page-wrap").height(reqContent+600);
    	});
    </script>
</body>
</html>