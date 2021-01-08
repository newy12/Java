<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="header">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
	 <div class="page-wrap">
        <div class="text-box">
            <div>홈 > 게시판 > 공지사항 > 글쓰기</div>
            <div>공지사항</div>
        </div>
         <div class="board-box">
        <form id="reqForm" action="/requestInsert.do" method="post" enctype="multipart/form-data">
            <table class="table title-box">
                <tr>
                    <th style="width: 10%;border-top: 2px solid rgb(49, 76, 131);text-align: center;line-height: 31px;">제목</th>
                    <th style="width: 75%;border-top: 2px solid rgb(49, 76, 131);">
                    	<input type="text" name="mId" value="${loginMember.MId }" style="display:none;">
                        <input type="text" name="reqTitle" id="title" placeholder="제목을 입력해주세요.">
                    </th>
                    <th style="width: 15%;border-top: 2px solid rgb(49, 76, 131);text-align: center;"></th>
                </tr>
                <tr>
                    <th style="border-top: 0px;text-align: center;">첨부파일</th>
                    <th style="border-top: 0px">
                    	<input type="file" name="filename" value="찾기" multiple>
                    </th>
                    <th style="border-top: 0px"></th>
                </tr>
            </table>
            <div>
				<table style="width:100%;height:500px;">
			        <tr>
			            <td>
			                <textarea id="smartEditor" name="reqContent" style=" height:500px; "></textarea>
			            </td>
			        </tr>
				</table>
            </div>
         
            <div>
            	<button class="back-btn save" id="savebutton">작성완료</button>
            	<button class="back-btn">목록으로</button>
            </div>
            </form>
        </div>
         <br><br><br>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
        
        
	</div>


</body>
</html>