<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 거래 후기 확인</title>
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
	.wrap{
		padding : 20px;
	}
	h2{
		color:rgb(49, 76, 131);
		margin:-10px 0 5px;
	}

	#gray-text{
		font-size:12px;
		font-weight:bold;
		color:rgb(158, 158, 158);
	}
	.heart-box{
		height:37px;
		pointer-events: none;
		margin-bottom:15px;
		background-color:#e8e8e8;
		border:1px solid rgb(158, 158, 158);
	}
	.heart-box>div{
		width:100%;
		height:100%;
		text-align:center;
	}
	.heart-box>div>img{
		width:30px;
		height:30px;
		margin-top:5px;
	}
	.heart-box>div>div{
		line-height:35px;
		float:right;
		
	}
	.sub-title{
		font-size:17px;
		font-weight: bold;
		color:rgb(49, 76, 131);
		margin-bottom:10px;
	}
	.btn-wrap{
		margin-top:20px;
		text-align: center;
	}
    .btn2{
        width: 100px;
        height: 31px;
        border-radius: 5px;
    }
    .btn2:hover{
        border: 2px solid black;
    }
    .change{
        color: white;
        border: 1px solid rgb(49, 76, 131);
        background-color: rgb(49, 76, 131);
    }
    .delete{
        color: rgb(49, 76, 131);
        border: 1px solid rgb(49, 76, 131);
        background-color: white;
    }
    .write-box{
    	height:25px;
		text-align:right;
		margin-bottom:15px;
		background-color:#e8e8e8;
		border:1px solid rgb(158, 158, 158);
    }
    #review{
    	background-color: #e8e8e8;
    }
</style>
<body>
	<div class="wrap">
		<h2>리뷰 확인하기</h2>
		<div id="gray-text">작성하신 리뷰를 확인해보세요. 또는 수정하시거나 삭제하실 수 있습니다.</div>
		<hr>
		<div class="sub-title">리뷰 작성일</div>
		<div class="write-box">${review.writeDate }</div>
		<div class="sub-title">서비스 만족도</div>
		<div class="heart-box">
			<div>
				<div id="rate">${review.RRate}/5점</div>
			</div>
		</div>
		<div class="sub-title">작성한 리뷰 내용</div>
		<div>
			<textarea rows="8" cols="60" id="review" maxlength="300" readonly="readonly">${review.RContent }</textarea>
		</div>
		<div class="btn-wrap">
			<input type="text" style="display:none" value="${review.TNo}" id="tNo">
			<input type="text" style="display:none" value="${review.RNo}" id="rNo">
			<input type="text" style="display:none" value="${review.SNo}" id="sNo">
			<button class="btn2 delete" id="delete_btn">삭제</button>
        	<button class="btn2 change" id="update_btn">수정</button>
        	<button class="btn2 delete" id="close_btn">닫기</button>
        </div>
		
	</div>
	
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			var rateTxt = $("#rate").html().split("/");
			var rate = rateTxt[0];
			var str = "";
			for(var i=0; i<5; i++){
				if(i<rate){
					str += "<img src='/img/icon/heart_orange.png' onclick='heart_click(this)'  value='"+(i+1)+"/fill'>";
				}else{
					str +="<img src='/img/icon/heart_orange_line.png' onclick='heart_click(this)'  value='"+(i+1)+"/empty'>";
				}
			}
			str+= "<div id='rate'>"+rate+"/5점</div>";
			$(".heart-box").children().html(str);
		 });
	
		function heart_click(obj){
			var data = $(obj).attr('value').split("/");
			var index = data[0];
			var value = data[1];
			var count = 0;
			if(value == "empty"){
				for(var i=1; i<=index;i++){
					$(obj).parent().children().eq(i-1).attr('src','/img/icon/heart_orange.png');
					$(obj).parent().children().eq(i-1).attr('value', i+"/fill");
					count++;
				}	
				$("#rate").html(count+"/5점");
			}else{
				for(var i=5; i>=index; i--){
					$(obj).parent().children().eq(i-1).attr('src','/img/icon/heart_orange_line.png');
		            $(obj).parent().children().eq(i-1).attr('value', i+"/empty");
		            count++;
				}
				$("#rate").html((5-count)+"/5점");
			}
		}
		
		$("#close_btn").click(function(){
			window.close();
	    });
		
		$("#update_btn").click(function(){
			if($("#update_btn").html() == '수정'){
				$("#update_btn").html('저장');
				$(".heart-box").css({'background-color':'white','pointer-events':'auto'});
				$("#review").css({'background-color':'white'}).attr('readonly', false);
			}else{
				var rateTxt = $("#rate").html().split("/");
				var rNo = $("#rNo").val();
				var sNo = $("#sNo").val();
				var rate = rateTxt[0];
				var content = $("#review").val();
				location.href="/serviceReviewUpdate.do?rNo="+rNo+"&rRate="+rate+"&rContent="+content+"&sNo="+sNo;
			}
		});
		
		$("#delete_btn").click(function(){
			var rNo = $("#rNo").val();
			var tNo = $("#tNo").val();
			var sNo = $("#sNo").val();
			location.href="/serviewReviewDelete.do?rNo="+rNo+"&tNo="+tNo+"&sNo="+sNo;
		});
	</script>
</body>
</html>