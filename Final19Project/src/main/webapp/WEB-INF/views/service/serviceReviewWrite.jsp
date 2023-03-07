<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 거래 후기 작성</title>
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
	.sContent{
		height:100px;
	}
	.sContent>div{
		float:left;
	}
	.sContent>div:first-child{
		width:30%;
	}
	.sContent>div:last-child {
		width:65%;
		height:35px;
		font-size:13px;
		overflow: hidden;	
		margin:20px 0  0 10px;
	}
	.heart-box{
		height:37px;
		margin-bottom:15px;
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
        width: 135px;
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
</style>
<body>
	<div class="wrap">
		<h2>리뷰쓰기</h2>
		<div id="gray-text">거래 완료 후 리뷰를 쓰시면 다른 구매자의 이용에 도움이 됩니다.</div>
		<hr>
		<div class="sContent">
			<div><img src="/upload/service/${sImg }" width="100%" height="80px" alt="${sImg }" style="border-radius:5px;"></div>
			<div>[서비스 제목] ${sTitle }</div>
		</div>
		<div class="sub-title">서비스 만족도</div>
		<div class="heart-box">
			<div>
				<img src="/img/icon/heart_orange_line.png" onclick="heart_click(this)"  value="1/empty">
				<img src="/img/icon/heart_orange_line.png" onclick="heart_click(this)"  value="2/empty">
				<img src="/img/icon/heart_orange_line.png" onclick="heart_click(this)"  value="3/empty">
				<img src="/img/icon/heart_orange_line.png" onclick="heart_click(this)"  value="4/empty">
				<img src="/img/icon/heart_orange_line.png" onclick="heart_click(this)"  value="5/empty">
				<div id="rate">0/5점</div>
			</div>
		</div>
		<div class="sub-title">리뷰 작성하기</div>
		<div>
			<textarea rows="8" cols="60" id="review" maxlength="300"></textarea>
		</div>
		<div class="btn-wrap">
			<input type="text" style="display:none" value="${tNo}" id="tNo">
			<input type="text" style="display:none" value="${sNo}" id="sNo">
			<input type="text" style="display:none" value="${mId}" id="mId">
        	<button class="btn2 change" id="confirm_btn">리뷰 등록</button>
        	<button class="btn2 delete" id="close_btn">닫기</button>
        </div>
		
	</div>
	
	<script>
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
		
		$("#confirm_btn").click(function(){
			var rateTxt = $("#rate").html().split("/");
			var tNo = $("#tNo").val();
			var sNo = $("#sNo").val();
			var mId = $("#mId").val();
			console.log(mId);
			if(mId == null){
				console.log("널이다.. 왜?");
			}else{
			var rate = rateTxt[0];
			var content = $("#review").val();
			location.href="/serviceReviewInsert.do?tNo="+tNo+"&sNo="+sNo+"&mId="+mId+"&rRate="+rate+"&rContent="+content;
			}
			});
	</script>
</body>
</html>