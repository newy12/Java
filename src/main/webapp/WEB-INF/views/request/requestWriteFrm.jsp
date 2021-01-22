<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 의뢰 작성</title>
<script type="text/javascript" src="/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
</head>
<style>
   .page-wrap{
        width: 911px;
        height:1000px;
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
    .back-btn{
        width: 103px;
        height: 40px;
        float: right;
        margin-top: 20px;
        margin-left : 15px;
        color: rgb(49, 76, 131);
        background-color: white;
        border-radius: 5px;
        border: 2px solid rgb(49, 76, 131);
    }
    .back-btn:hover{
        border: 3px solid black;
    }
    .board-box{
        margin-top: 30px;
        border-bottom: 2px solid rgb(49, 76, 131);
    }

    .board-box>div:first-child{
        height: 500px;
        padding: 0 0px 20px 20px;
    }
    .title-box{
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
    #title{
        width: 100%;
        height: 31px;
        border-radius: 3px;
        border: 1px solid rgb(204, 204, 204);
        background-color: white;
    }
    .save{
        color: white;
        border-radius: 5px;
        background-color:  rgb(49, 76, 131);
        border: 2px solid rgb(49, 76, 131);
    }
    .save:hover{
        border: 3px solid black;
    }
</style>
<body>
	<div class="header">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
	<div class="page-wrap">
        <div class="text-box">
            <div>홈 > 게시판 > 의뢰게시판</div>
            <div>고객이 직접<br>원하는 의뢰를<br>작성할 수 있습니다</div>
        </div>
        
        <div class="board-box">
        <form id="reqForm" action="/requestInsert.do" method="post" enctype="multipart/form-data">
            <table class="table title-box">
                <tr>
                    <th style="width: 10%;border-top: 2px solid rgb(49, 76, 131);text-align: center;line-height: 31px;">제목</th>
                    <th style="width: 75%;border-top: 2px solid rgb(49, 76, 131);">
                    	<input type="hidden" name="position" value="${position }">
                    	<input type="text" name="mId" value="${loginMember.MId }" style="display:none;">
                        <input type="text" name="reqTitle" id="title" placeholder="제목을 입력해주세요.(30글자 이내)" maxlength="33">
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
            </form>
            <div>
            	<button class="back-btn save" id="savebutton">작성완료</button>
            	<button class="back-btn" onclick="history.back()">목록으로</button>
            </div>
        </div>
    </div>
    
    <br><br><br>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    
    
    <script type="text/javascript">
	    var oEditors = [];
	    nhn.husky.EZCreator.createInIFrame({ 
	    	oAppRef : oEditors, elPlaceHolder : "smartEditor", 
	    	sSkinURI : "SE2/SmartEditor2Skin.html", 
	    	fCreator : "createSEditor2", 
	    	htParams : { 
	    		bUseToolbar : true, 
	    		bUseVerticalResizer : false, 
	    		bUseModeChanger : false 
	    		} 
	    }); 
	    
	    $(function() { 
	    	$("#savebutton").click(function() { 
	    		oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []); 
	    		//textarea의 id를 적어줍니다. 
	    		var title = $("#title").val(); 
	    		var content = document.getElementById("smartEditor").value; 
	    		if (title == null || title == "") { 
	    			alert("제목을 입력해주세요."); 
	    			$("#title").focus(); 
	    			return; 
	    			} 
	    		if(content == "" || content == null || content == '&nbsp;' || content == '<br>' || content == '<br/>' || content == '<p>&nbsp;</p>'){ 
	    			alert("본문을 작성해주세요."); 
	    			oEditors.getById["smartEditor"].exec("FOCUS"); 
	    			return; 
	    			} 
	    		if(getByteB(content) > 2000){
	    			alert("입력할 수 있는 글자수를 초과되었습니다.\n현재 본문크기 : "+getByteB(content)+"byte, 제한크기 : 2000byte");
	    			return;
	    		}
	    		//이 부분은 스마트에디터 유효성 검사 부분이니 참고하시길 바랍니다. 
	    		var result = confirm("등록 하시겠습니까?"); 
	    		if(result){
	    			$("#reqForm").submit(); 
	   			}
	    		else{ 
	    			return; 
	    			} 
	    		}); 
	    	

	    	})
	    	
	 //글자수 검사
	function getByteB(str) {
		var byte = 0;
		for (var i=0; i<str.length; ++i) {
			// 기본 한글 3바이트 처리
			(str.charCodeAt(i) > 127) ? byte += 3 : byte++ ;
		}
			return byte;
		}
    </script>

</body>
</html>