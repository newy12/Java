<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 거래 내용 확인</title>
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
	.text-box{
		height:33px;
		margin-bottom:15px;
		border-radius:5px;
		background-color:#e8e8e8;
		border:1px solid rgb(158, 158, 158);
	}
	.text-box>div{
		line-height:33px;
		font-weight:bold;
		font-size:17px;
		text-align:right;
		margin-right:10px;
		color:rgb(64, 64, 64);
	}
	.sub-title{
		font-size:17px;
		font-weight: bold;
		color:rgb(49, 76, 131);
		margin-bottom:10px;
	}
	.btn-wrap{
		margin-top:15px;
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
    .delete{
        color: white;
        background-color:rgb(49, 76, 131);
        border: 1px solid rgb(49, 76, 131);
    }
</style>
<body>
	<div class="wrap">
		<h2>거래 확인</h2>
		<div id="gray-text">프리랜서와 협의한 거래 내용을 확인해보세요.</div>
		<hr>
		<div class="sContent">
			<div><img src="/upload/service/${ service.SImg}" width="100%" height="80px" style="border-radius:5px;" alt="${sImg }"></div>
			<div><a href="javascript:goServiceView(${trade.SNo },${loginMember.MNo })">${service.STitle }</a></div>
		</div>
		<div class="sub-title">서비스 가격</div>
		<div class="text-box">
			<div id="price">${trade.TPriceTxt }</div>
		</div>
		<div class="sub-title">작업 시작 날짜</div>
		<div class="text-box">
			<div>${trade.startDate }</div>
		</div>
		<div class="sub-title">작업 마감 날짜</div>
		<div class="text-box">
			<div>${trade.endDate }</div>
		</div>
		<div class="btn-wrap">
        	<button class="btn2 delete" id="close_btn">닫기</button>
        </div>
		
	</div>
	
	<script>
		$("#close_btn").click(function(){
			window.close();
	    });
		
		//부모창에서 서비스 상세보기 뜸
		function goServiceView(sNo,mNo){
			opener.parent.gotoServiceView(sNo,mNo);
		}
		
	</script>
</body>
</html>