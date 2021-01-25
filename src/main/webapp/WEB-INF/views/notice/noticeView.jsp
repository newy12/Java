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
            width: 911px;
            height:1200px;
            margin: 0 auto;
            margin-top: 45px;
        }

        .text-box {
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


        .btn {
            background-color: black;
        }

        .board-box {
            height: 40px;
           	margin-top: 30px;
           	
       		
        }

        .btn-right {
            float: right;
            margin-bottom: 100px;
           
        }
        .btn-left{
        
        	float: left;
        }
        
        .table {
            text-align: left;
            text-indent: 20px;
            border-bottom: 2px solid rgb(49, 76, 131);
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
        

        
        .btn-emp {
            width: 120px;
            height: 35px;
            color: rgb(49, 76, 131);
            border-radius: 5px;
            background-color : transparent;
            border: 2px solid rgb(49, 76, 131);
            font-weight: bold;
            display: block;
            text-align: center;
        	line-height: 35px;
        }
        
        
        .btn-file{
            color: white;
            border-radius: 5px;
            background-color: rgb(49, 76, 131);
            border: 1px solid rgb(49, 76, 131);
            font-weight: bold;
            font-size: 10pt;
            padding: 5px;
            margin-left: 10px;
        }

        .btn-custom:hover {
            border: 2px solid rgb(49, 76, 131);
            background-color: transparent;
            color: rgb(49, 76, 131);
            font-weight: bold;
        }
        
        .btn-emp:hover{
        	text-decoration: none;
        	color: white;
            background-color: rgb(49, 76, 131);
            border: 1px solid rgb(49, 76, 131);
        	
        }
        
        
        .write-box{
            border-top: 2px solid rgb(49, 76, 131);
        }
        
        .noticeContent{
        	height: 500px;
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

        <div class="board-box">
            <br><br>
            <table class="table write-box">
                <tr style="background-color: rgba(224, 224, 224, 0.5);">
                    <th style="width: 100px;" > 제목 </th>
                    <td style="width: 570px;"> ${n.NTitle }</td>
                    <th style="width: 100px;"> 작성일 </th>
                    <td> ${n.writeDate } </td>
                </tr>
                <tr style="background-color: rgba(224, 224, 224, 0.5);" >
                    <th style="height: 30px; line-height: 40px;" > 첨부파일 </th>
                    <td colspan="3" style="line-height: 30px;"> 
					
							<c:if test="${n.filename != null}">
								<p > ${n.filename } </p>
							</c:if> 
							<c:if test="${n.filename == null }">
								<p> 첨부파일 없음 </p>
							</c:if>
						
                    </td>
                </tr>
                <tr>
                   <th class="noticeContent" >내용</th>
                   <td style="text-align: center;" colspan="3" >
                   		<c:if test="${n.filename != null }">
                   			<img src="/upload/notice/${n.filename }" width="500px;">
                   		</c:if>
                      <p> ${n.NContent } </p>
                   </td>     
                </tr>
            </table>
            
            <div class="btn-left ">
            	<a class="btn-emp" href="/noticeList.do?reqPage=1&keyword=">목록으로</a>
            </div>
            <div class="btn-right">
            	<c:if test="${loginMember.MGrade == 0 }">
            		<button class="btn btn-custom " onclick="location='/deleteNotic.do?nNo=${n.NNo}'">삭제하기</button>
            		<button class="btn btn-custom " onclick="location='/updateNoticeFrm.do?nNo=${n.NNo}'">수정하기</button>
            	</c:if>
            </div>
           
        </div>
    </div>
    <br>
    <br>
    <br>
    <br>

	 <footer>
    	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
    </footer>

</body>
</html>