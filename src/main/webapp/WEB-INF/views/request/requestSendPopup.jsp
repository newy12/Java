<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 제공할 서비스 선택</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
</head>
<style>
     @font-face {
         font-family: 'Arita-dotum-Medium';
         src:
             url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Arita-dotum-Medium.woff') format('woff');
         font-weight: normal;
         font-style: normal;
     }
    * {
        font-family: 'Arita-dotum-Medium';
    }
	body{
		margin :0;
		padding:0;
	}
    .wrap{
    }
    .wrap>div:first-child{
    	padding:5px 15px 0;
    	text-align:left;
    	height:100px;
    	color:white;
    	background-color: rgb(49, 76, 131);
    }
    .wrap>div:first-child>div{
    	margin:12px 10px 0;
    	font-size: 23px;
    }
    table{
        width: 80%;
        border-bottom: 1px solid rgb(230, 230, 230);
    }
    .table-top{
        height: 20px;
        text-align: center;
    }
    .service-content>div{
        float:left;
    }
    .service-content>div:last-child>div{
        margin-left: 15px;
    }
    .title{
        color: gray;
        width: 250px;
        overflow: hidden;
        display: -webkit-box;
        -webkit-line-clamp: 1; 
        -webkit-box-orient: vertical;
    }
    .wrap>div{
        text-align: center;
    }
    .send-btn{
    	left:35%;
    	top:485px;
        width: 145px;
        height: 30px;
        color: white;
        font-size:11px;
        position:absolute;
        border-radius: 5px;
        line-height: 25px;
        background-color: rgb(49, 76, 131);
        border: 2px solid rgb(49, 76, 131);
    }
    .send-btn:hover{
        border: 2px solid black;
    }
    input[type=radio]{
        width: 20px;
        height: 20px;
    }
	.noList{
	    margin-top: 5px;
	    text-align: center;
	   	color :rgb(224, 224, 224);
	   	font-weight: bold; 
	}
</style>
<body>
	<div class="wrap">
        <div><div>의뢰자에게 제공할<br> 서비스를 선택해주세요</div></div>
        <table class="table table-hover">
            <tr style="background-color: rgba(224, 224, 224, 0.5);">
                <td class="table-top">순서</td>
                <td class="table-top">선택</td>
                <td class="table-top">서비스 내용</td>
            </tr>
            <c:if test="${list.size() == 0 }">
            	<tr><td colspan="3" class="noList">등록하신 서비스가 없습니다.</td></tr>
            </c:if>
            <c:if test="${list.size() != 0 }">
            <c:forEach items="${list }" var="s" varStatus="status">
				<tr>
	                <td style="text-align: center">${status.count }</td>
	                <td style="text-align: center">
	                	<input type="hidden" value="${s.MId}">
	                	<input type="hidden" value="${s.SNo }">
	                	<input type="radio" class="radio-check" name="chk">
	                </td>
	                <td>
	                    <div class="service-content">
	                        <div><img src="/upload/service/${s.SImg }" width="80px" height="50px" style="border-radius:10px;" alt="${s.SImg }"></div>
	                        <div>
	                            <div style="margin-top: 10px;font-weight: bold;font-size: 12px;">${s.mainCategoryName } > ${s.subCategoryName }</div>
	                            <div><a href="javascript:openService(${s.SNo }, ${loginMember.MNo });" class="title">${s.STitle }</a></div>
	                        </div>
	                    </div>
	                </td>
	            </tr>
            </c:forEach>
            </c:if>
        </table>
        <br>
        <input type="hidden" value="${userId }" id="userId">
        <div>
        	<c:if test="${list.size() == 0 }">
        		<button class="send-btn cancel" onclick="window.close();">닫기</button>
        	</c:if>
        	<c:if test="${list.size() != 0 }">
        		<button class="send-btn send">의뢰인에게 메시지 보내기</button>
        	</c:if>
        </div>
    </div>
    
    <script>
    	//소현언니~~~~~~~^__^
    	$(".send").click(function(){
    		var freeId = $("input[type=radio]:checked").prev().prev().val();
    		var sNo = $("input[type=radio]:checked").prev().val();
    		var userId = $("#userId").val();
    		var mGrade=${loginMember.MGrade};
    		console.log(freeId+"/"+sNo+"/"+userId+"/"+mGrade);
    		
    		$.ajax({
				url : "/makeRoom.do",
				type : "post",
				async : false,
				data : {
					sNo : sNo,
					userId : userId,
					freeId : freeId,
					mNo : -1,
					mGrade:mGrade
				},
				success : function(data) {
					var loc="/enterRoom.do?cNo=-1&sNo=" + sNo + "&myId="+ userId + "&yourId=" + freeId+"&mGrade="+mGrade
					var _left = Math.ceil(( window.screen.width - 530 )/2);
					window.open(loc, '', 'width=530, height=630, left='+_left+', top=50, location=no,scrollbars=no,location=no, resizable=no'); 
					window.close();
					
				},
				error : function() {

				}
			});
  
    	});
    	
    	//서비스 누르면 새 창으로 연결
    	function openService(sNo,mNo){
    		var win = window.open("/serviceView.do?sNo="+sNo+"&reqPage=1&mNo="+mNo, "serviceView", "width="+(window.screen.width)+",height="+(window.screen.height));
    	}
    </script>
</body>
</html>