<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 프리랜서 거래 내역</title>
 <!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">

<style>
.noList{
    margin-top: 5px;
    text-align: center;
   	line-height: 185px;
   	color :rgb(224, 224, 224);
   	font-size:20px;
   	font-weight: bold; 
}
.page-wrap {
	width: 1104px;
	height: 1100px;
	margin: 0 auto;
	margin-top: 50px;
}

.board-wrap {
	width: 800px;
	margin: 0 auto;
	margin-top: 40px;
	margin-left: 30px;
	float: left;
}

.board-box {
	height: 45px;
	border: 1px solid rgb(224, 224, 224);
}

.container-box {
	margin-top: 20px;
	width: 800px;
	height: 1000px;
}

.board-box>span {
	font-size: 18px;
	font-weight: bold;
	color: rgb(49, 76, 131);
	line-height: 45px;
	margin-left: 30px;
}

.container-box>div:first-child {
	height: 37px;
	border-top: 1px solid rgb(190, 190, 190);
	border-bottom: 1px solid rgb(190, 190, 190);
	background-color: rgb(224, 224, 224);
}

.container-box>div>ul {
	padding: 0;
	list-style: none;
}

.container-box>div>ul>li {
	float: left;
	text-align: center;
	margin-top: 8px;
}

.content-box {
	height: 185px;
	margin-top: 5px;
	border: 1px solid rgb(224, 224, 224);
}

.content-box>div:first-child {
	width: 160px;
	height: 184px;
	float: left;
	padding-top: 35px;
	text-align: center;
	border-right: 1px solid rgb(224, 224, 224);
}

.content-box>div:nth-child(2) {
	width: 400px;
	height: 184px;
	float: left;
	border-right: 1px solid rgb(224, 224, 224);
}

.content-box>div:nth-child(2)>div>div {
	margin-top: 10px;
	margin-left: 15px;
	margin-right: 15px;
	color: rgb(51, 51, 51);
	font-size: 14px;
	float: left;
}

.content-box>div:nth-child(2)>div>div:nth-child(2) {
	margin-top: 40px;
}

.content-box>div:nth-child(2)>div>div>div>div {
	margin-top: 5px;
	margin-right: 10px;
	float: left;
}

.content-box>div:nth-child(2)>div>div>div>div>a>img {
	width: 25px;
	height: 25px;
}

.content-box>div:nth-child(2)>div>div>div>div>a {
	color: rgb(51, 51, 51);
	line-height: 25px;
}

.content-box>div:nth-child(2)>div>div:last-child>a {
	font-size: 15px;
	margin-bottom: 10px;
	text-overflow: ellipsis;
	color: rgb(51, 51, 51);
	overflow: hidden;
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
}

.content-box>div:nth-child(3) {
	width: 15%;
	height: 184px;
	float: left;
	padding-top: 35px;
	text-align: center;
	border-right: 1px solid rgb(224, 224, 224);
}

.content-box>div:last-child {
	width: 14%;
	float: left;
	color: rgb(224, 224, 224);
	font-size: 16px;
	font-weight: bold;
	text-align: center;
	margin-top: 55px;
}

.review-btn {
	width: 100px;
	height: 26px;
	font-size: 13px;
	font-weight: bold;
	line-height:24px;
	margin-top: 20px;
	margin-left: 4px;
	border-radius:5px;
	color: rgb(49, 76, 131);
	background-color: white;
	border: 1px solid rgb(49, 76, 131);
}

.review-btn:hover {
	color: white;
	background-color: rgb(49, 76, 131);
	border: 1px solid rgb(49, 76, 131);
}
/*블랙컨슈머 모달*/
#modal {
	display:none;
	width:100%;
	height:100%;
	z-index:10000;
}

#modal .modal_content {
	width:430px;
	height:170px;
	padding:15px 30px;
	background:#fff;
	border-radius: 10px;
	border: 2px solid #314C83;
	box-shadow: 0px 0px 10px 0px #314C83;
}

#modal .modal_layer {
  position:fixed;
  top:0;
  left:0;
  width:100%;
  height:100%;
  background:rgba(0, 0, 0, 0.5);
  z-index:-1;
}
.modal_content ul{
	list-style: none;
	margin:0;
	padding:0;
	margin-top: 15px;
}
.modal_content ul>li{
	text-align: center;
}
</style>
</head>
<body>
	<div class="header">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
	<div class="page-wrap">
		<!-- 사이드메뉴 -->
        <jsp:include page="/WEB-INF/views/freelancer/freelancer-side.jsp" />

		<div class="board-wrap">
			<div class="board-box">
				<span>거래 내역</span>
			</div>
			<div class="container-box">
				<div>
					<ul>
						<li style="width: 70%">거래 내역</li>
						<li style="width: 15%">진행 상태</li>
						<li style="width: 15%">작업 완료</li>
					</ul>
				</div>

				<c:if test="${tradeList.size() == 0}">
					<div class="noList">진행한 거래가 없습니다</div>
				</c:if>
				<c:if test="${tradeList.size() != 0 }">
					<c:forEach items="${tradeList}" var="t" varStatus="status">
						<div class="content-box">
							<div>
								<div style="color: rgb(51, 51, 51); font-size: 15px;">거래번호</div>
								<div style="color: rgb(51, 51, 51); font-size: 15px; margin-top: 5px; font-weight: bold;">
									<a href="javascript:void(0)" onclick="trade_open(this)">${t.TNo}</a>
								</div>
								<div style="color: rgb(51, 51, 51); font-size: 15px; margin-top: 20px;">거래금액</div>
								<div style="color: rgb(255, 143, 63); font-size: 20px; margin-top: 5px; font-weight: bold;">${t.TPriceTxt }</div>
							</div>
							<div>
								<div>
									<div>
										<a href="/serviceView.do?sNo=${serviceList[status.index].SNo}&reqPage=1&mNo=${loginMember.MNo}"><img src="/upload/service/${ serviceList[status.index].SImg}" width="175px" height="103px" style="border-radius:5px;"></a>
									</div>
									<div style="color: #8a8a8a;">
										사용자 ID
										<div>
											<div>
												<c:choose>
													<c:when test="${t.MId == null }">
														<a href="#" style="text-decoration:none">탈퇴한 회원</a>
													</c:when>
													<c:otherwise>
														<a href="#" style="text-decoration:none">${t.MId }</a>
													</c:otherwise>
												</c:choose>
												
												<div></div>
											</div>
										</div>
									</div>
									<div>
										<a href="/serviceView.do?sNo=${serviceList[status.index].SNo }&reqPage=1&mNo=${loginMember.MNo}">${serviceList[status.index].STitle }</a>
									</div>
								</div>
							</div>
							<div>
								<c:choose>
									<c:when test="${t.TStatus == 0 }">
										<div style="color: rgb(255, 143, 63); font-size: 20px; margin-top: 5px; font-weight: bold;">결제 전</div>
										<div style="color: rgb(51, 51, 51); font-size: 15px;"></div>
										<div style="color: rgb(51, 51, 51); font-size: 15px; margin-top: 20px;">예상 마감일</div>
										<div style="color: rgb(51, 51, 51); font-size: 15px; margin-top: 5px; font-weight: bold;">${t.endDate }</div>
									</c:when>
									<c:when test="${t.TStatus == 1 }">
										<div style="color: rgb(255, 143, 63); font-size: 20px; margin-top: 5px; font-weight: bold;">결제 완료</div>
										<div style="color: rgb(51, 51, 51); font-size: 15px;">${payDate[status.index] }</div>
										<div style="color: rgb(51, 51, 51); font-size: 15px; margin-top: 20px;">예상 마감일</div>
										<div style="color: rgb(51, 51, 51); font-size: 15px; margin-top: 5px; font-weight: bold;">${t.endDate }</div>
									</c:when>
									<c:otherwise>
										<div style="color: rgb(224, 224, 224); font-size: 20px; margin-top: 5px; font-weight: bold;">작업 완료</div>
										<div style="color: rgb(51, 51, 51); font-size: 15px;">${payDate[status.index] }</div>
										<div style="color: rgb(51, 51, 51); font-size: 15px; margin-top: 20px;">작업 종료일</div>
										<div style="color: rgb(51, 51, 51); font-size: 15px; margin-top: 5px; font-weight: bold;">${t.endDate }</div>
									</c:otherwise>
								</c:choose>
							</div>
							<div>
								<c:choose>
									<c:when test="${t.TStatus == 0 }">작업전</c:when>
									<c:when test="${t.TStatus == 1 }">
										<input type="hidden" value="${t.MNo}" />
										<input id="tnoId" type="hidden" value="${t.TNo}" />
										<button class="review-btn " onclick="workDone_btn(this)">작업 완료</button>
									</c:when>
									<c:otherwise>작업 완료</c:otherwise>
								</c:choose>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	
	<!-- 블랙컨슈머 등록 -->
	<div id="modal">
	    <div class="modal_content">
			<h4><strong>거래 의뢰자</strong>는 어떠셨나요?</h4>
			<div style="margin-top:-10px"">의뢰자에 대한 평가는 다른 프리랜서들에게 도움이 됩니다.</div>
			<ul>
			 	<li>
			 		
			 		<a href="#hate" style="color:#d10606; ">다신 보고싶지 않아요</a>
			 	</li>
			 	<li><a href="#close" class="modal_close" style="color:#807e7e; ">그저 그랬어요</a></li>
			 	<li><a href=#close" class="modal_close"  style="color:#00cc25; ">매우 좋았어요</a></li>
			</ul>
	    </div>
	    <div class="modal_layer"></div>
	</div>
</body>


<script>
	$(document).ready(function(){
		//메뉴 고정
		 $(".menu").children().eq(3).find('a').css({'margin-left':'5px', 'font-weight':'bold'});
		 $(".menu").children().eq(3).find('img').css({'display':'inline'});
		 
		 var containerHeight = $(".board-wrap").height();
		 $(".page-wrap").height(containerHeight+200);
	});
	
	//거래 세부 내역 확인
	function trade_open(obj){
		var tNo = $(obj).html();
		var _left = Math.ceil(( window.screen.width - 473 )/2);
		window.open('/serviceTradeView.do?tNo='+tNo, '거래 내용 확인', 'width=473, height=480, left='+_left+', top=50, scrollbars=no, location=no, resizable=no');
		return false;
	}
	
	//작업 완료 번튼 누르면
	 $('.review-btn').click(function(event){
		 var tNo = $(this).prev().val(); //tno값
		 var mNo = $(this).prev().prev().val(); //mNo값
		 var heigh = event.pageY;
		 console.log(tNo+"/"+mNo+"/"+heigh)

		 $.ajax({
			type : "post",
			url : "/updateTStatus.do",
			data : {tNo : tNo},
			error : function() {
				alert("실패");
			},
			success : function(result) {			
				$(".modal_content").append("<input type='hidden' value="+tNo+" id='modal_tNo'>");
				$(".modal_content").append("<input type='hidden' value="+mNo+" id='modal_mNo'>");
				
				var _left = Math.ceil(( window.screen.width - 400 )/2);
				var _top = event.pageY-200;
				$("#modal").css({'position':'absolute','top':_top,'left':_left}).toggle('slow');
			}
		});
	 });
	 
	//다신 만나기 싫어요 외 다른거 누르면
	 $('.modal_close').click(function(event){
		 alert('소중한 의견 감사합니다.');
		 $("#modal").toggle('slow');
		 location.reload();
	 });
	 
	//다신 만나기 싫어요 버튼 누르면
	 $('a[href="#hate"]').click(function(event){
		var tNo = $('#modal_tNo').val();
		var mNo = $("#modal_mNo").val();
		alert('소중한 의견 감사합니다.');
		location.href="/updateWarningCount.do?mNo="+mNo;
	 });
	
	//거래상세보기 창에서 서비스 상세보기를 누르면
	function gotoServiceView(sNo,mNo){
		location.href="/serviceView.do?sNo="+sNo+"&reqPage=1&mNo="+mNo;
	}
		
</script>
</html>