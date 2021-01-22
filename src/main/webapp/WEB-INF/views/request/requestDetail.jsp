<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 의뢰 상세</title>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
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
    	color:rgb(49, 76, 131);
        padding-top: 70px;
        font-size: 30px;
        line-height: 35px;
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
        height: 22px;
        font-size: 11px;
        line-height:18px;
        color: rgb(49, 76, 131);
        border-radius: 5px;
        border: 2px solid rgb(49, 76, 131);
    }
    .table{
        text-align: center;
    }
	.contact-user:hover{
		border: 2px solid black;
	}
	.th-text{
		text-align: center;
		color:rgb(49, 76, 131);
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
                    <th class="th-text" style="border-top: 2px solid rgb(49, 76, 131);">제목</th>
                    <th style="width: 75%;border-top: 2px solid rgb(49, 76, 131);">${req.reqTitle }</th>
                    <th style="width: 15%;border-top: 2px solid rgb(49, 76, 131);text-align: center;">${req.writeDate }</th>
                </tr>
                <tr>
                    <th style="border-top: 0px" class="th-text">작성자</th>
                    <th style="border-top: 0px">${req.MId } &nbsp;&nbsp;&nbsp;
                    	<c:if test="${not empty loginMember}">
                    		<c:if test="${loginMember.MId != req.MId and loginMember.MGrade == 2}">
                    			<button class="contact-user" value="${req.MId }">고객에게 연락하기</button>
                    		</c:if>
                    	</c:if>
                    </th>
                    <th style="border-top: 0px"></th>
                </tr>
                <tr>
                    <th style="border-top: 0px" class="th-text">첨부파일</th>
                    <th style="border-top: 0px">
                    <c:if test="${req.filepath==null }">
                    	첨부파일이 없습니다.
                    </c:if>
                    <c:if test="${req.filepath!=null }">
                    	<a href="/requestFileDownload.do?filepath=${req.filepath }">${req.filename }</a>
                    </c:if>
                    </th>
                    <th style="border-top: 0px"></th>
                </tr>
            </table>
            <div class="reqContent">${req.reqContent }</div>
            <div class="btn-box">
            	<input type="hidden" id="loginId" value="${loginMember.MId }">
            	<input type="hidden" id="reqNo" value="${req.reqNo }">
            	<input type="hidden" id="reqStatus" value="${req.reqStatus }">
            	<button class="btns btn-back" onclick="history.back()">목록으로</button>
            	<c:if test="${req.MId == loginMember.MId }">
            		<button class="btns btn-delete">삭제하기</button>
            		<button class="btns btn-update">수정하기</button>
            	</c:if>
            	
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
    	
    	$(".btn-update").click(function(){
    		var reqNo = $("#reqNo").val();
    		var position = '${position}';
    		location.href = "/requestUpdateFrm.do?reqNo="+reqNo+"&position="+position;
    	});
    	
    	$(".btn-delete").click(function(){
    		var position = '${position}';
    		var reqNo = $("#reqNo").val();
    		var filepath = "${req.filepath}";
    		var reqStatus = $("#reqStatus").val();
    		if(reqStatus == 1){  //진행중이면 삭제안되게
    			alert('진행중인 의뢰는 삭제하실 수 없습니다.');
    		}else{
    			var result = confirm('정말 삭제하시겠습니까?');
    			if(result){
    				location.href = "/requestDeleteOne.do?reqNo="+reqNo+"&filepath="+filepath+"&position="+position;
    			}
    		}
    	});
    	
    	$(".contact-user").click(function(){
    		var userId = $(".contact-user").val(); 
    		var freeId = $("#loginId").val();     
    		var _left = Math.ceil(( window.screen.width - 473 )/2);
    		window.open('/requestSendPopup.do?userId='+userId+'&freeId='+freeId, '의뢰자에게 보낼 서비스 선택하기', 'width=473, height=515, left='+_left+', top=50, scrollbars=yes, location=no, resizable=no');
			return false;
    	});
    </script>
</body>
</html>