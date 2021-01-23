<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 거래 내역</title>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
<style>
    .page-wrap {
        width: 1104px;
        margin: 0 auto;
        margin-top:50px;
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
    
 

    .board-box>span {
        font-size: 18px;
        font-weight: bold;
        color: rgb(49, 76, 131);
        line-height: 45px;
        margin-left: 30px;
    }
    .container-box {
        margin-top: 20px;
        width: 800px;
        height: 1000px;
    }
    .container-box>div:first-child{
        height: 37px;
        border-top: 1px solid rgb(190, 190, 190);
        border-bottom: 1px solid rgb(190, 190, 190);
        background-color: rgb(224, 224, 224);
    }
    .container-box>div>ul{
        padding: 0;
        list-style: none;
    }
    .container-box>div>ul>li{
        float: left;
        text-align: center;
        margin-top: 8px;
    }
    .content-box{
        height: 185px;
        margin-top: 5px;
        border: 1px solid rgb(224, 224, 224);
    }
    .content-box>div:first-child{
        width: 160px;
        height: 184px;
        float: left;
        padding-top: 35px;
        text-align: center;
        border-right: 1px solid rgb(224, 224, 224);
    }
    .content-box>div:nth-child(2){
        width: 400px;
        height: 184px;
        float: left;
        border-right: 1px solid rgb(224, 224, 224);
    }
    .content-box>div:nth-child(2)>div>div{
        margin-top: 10px;
        margin-left: 15px;
        margin-right: 15px;
        color: rgb(51, 51, 51);
        font-size: 14px;
        float: left;
    }
    .content-box>div:nth-child(2)>div>div:nth-child(2){
        margin-top: 40px;
    }
    .content-box>div:nth-child(2)>div>div>div>div{
        margin-right: 10px;
        float: left;
    }
    .content-box>div:nth-child(2)>div>div>div>div>a>img{
        width: 20px;
        height: 20px;
    }
    .content-box>div:nth-child(2)>div>div>div>div>a{
        color: rgb(51, 51, 51);
        line-height: 25px;
    }
    .content-box>div:nth-child(2)>div>div:last-child>a{
    	width:368px;
        font-size: 15px;
        margin-bottom: 10px;
        text-overflow: ellipsis;
        color: rgb(51, 51, 51);
        overflow: hidden;
        display: -webkit-box;
        -webkit-line-clamp: 2; 
        -webkit-box-orient: vertical;
    }
    .content-box>div:nth-child(3){
        width: 15%;
        height: 184px;
        float: left;
        padding-top: 35px;
        text-align: center;
        border-right: 1px solid rgb(224, 224, 224);
    }
    .content-box>div:last-child{
    	width: 14%;
    	float: left;
        color: rgb(224, 224, 224);
        font-size: 16px;
        font-weight: bold; 
        text-align: center;
        margin-top: 55px;
    }
    .review-btn:focus, .reviewCheck-btn:focus{
    	outline: none;
    }
    .review-btn{
        width: 100px;
        height: 26px;
        font-size:13px;
        font-weight: bold;
        margin-top: 20px;
        margin-left:4px;
        line-height:26px;
        color: white;
        border-radius: 5px;
        background-color:  rgb(49, 76, 131);
        border: 1px solid rgb(49, 76, 131);
    }
    .pay-btn{
        width: 80px;
        height: 26px;
        color:white;
        line-height:26px;
        font-size:13px;
        font-weight: bold;
        border-radius: 5px;
        background-color: rgb(255, 143, 63);
        border: 1px solid rgb(255, 143, 63);
    }
    .pay-btn:hover{
    	transition-duration:1s;
    	background-color: #f77740;
    }
    .review-btn:hover{
        background-color: #203154;
        transition-duration:1s;
        border: 1px solid rgb(49, 76, 131);
    }
    .noTrade{
    	text-align: center;
    	line-height: 185px;
    	color :rgb(224, 224, 224);
    	font-size:20px;
    	font-weight: bold; 
    }
    .reviewCheck-btn{
    	width: 100px;
        height: 26px;
        font-size:13px;
        font-weight: bold;
        margin-top: 20px;
        margin-left:4px;
        line-height: 26px;
        border-radius: 5px;
        color: rgb(49, 76, 131);
        background-color: white;
        border: 1px solid rgb(49, 76, 131);
    }
</style>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
	<div class="header">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
    <div class="page-wrap">
        <!-- 사이드메뉴 -->
        <jsp:include page="/WEB-INF/views/member/mypage-side.jsp" />
        
        <div class="board-wrap">
            <div class="board-box">
                <span>거래 내역</span>
            </div>

            <div class="container-box">
                <div>
                    <ul>
                        <li style="width: 70%">거래 내역</li>
                        <li style="width: 15%">진행 상태</li>
                        <li style="width: 15%">후기 작성</li>
                    </ul>
                </div>

                <!--여기부터 반복됨/ 여기서부터 for문 돌리면 될 듯-->
                <c:if test="${tradeList.size() == 0 }">
                	<div class="content-box noTrade">
                		진행한 거래가 없습니다
                	</div>
                </c:if>
                <c:if test="${tradeList.size() != 0 }">
				<c:forEach items="${tradeList }" var="t" varStatus="status">
					<div class="content-box">
	                    <div>
	                        <div style="color: rgb(51, 51, 51);font-size: 15px;">거래 번호</div>
	                        <div style="color: rgb(51, 51, 51);font-size: 15px;margin-top: 5px;font-weight: bold;">
	                        	<a href="javascript:void(0)" onclick="trade_open(this)">${t.TNo }</a>
	                        </div>
	                        <div style="color: rgb(51, 51, 51);font-size: 15px;margin-top: 20px;">거래 금액</div>
	                        <div style="color: rgb(255, 143, 63);font-size: 20px;margin-top: 5px;font-weight: bold;">${t.TPriceTxt }</div>
	                    </div>
	                    <div>
	                        <div>
	                            <div><a href="/serviceView.do?sNo=${serviceList[status.index].SNo}&reqPage=1&mNo=${loginMember.MNo}"><img src="/upload/service/${ serviceList[status.index].SImg}" width="175px" height="103px" style="border-radius:5px;"></a></div>
	                            <div style="color:#8a8a8a;">
	                                사용자 ID
	                                <div>
	                                    <div>
	                                    <c:if test="${empty serviceList[status.index].MId}">(탈퇴한 회원)</c:if>
	                                    <c:if test="${not empty serviceList[status.index].MId}">
	                                    	<a href="/introduceFrm.do?mId=${serviceList[status.index].MId}&reqPage=1">${serviceList[status.index].MId }</a>
	                                	 </c:if>
	                                    </div>
	                                    <c:if test="${not empty serviceList[status.index].MId}">
	                                    <div><a href="/introduceFrm.do?mId=${serviceList[status.index].MId}&reqPage=1"><img src="/img/icon/home.png"></a></div>
	                                    <div><a href="#" onclick="startChat('${serviceList[status.index].SNo}','${sessionScope.loginMember.MId }','${serviceList[status.index].MId}','${sessionScope.loginMember.MNo }','${sessionScope.loginMember.MGrade}');"><img src="/img/icon/message.png"></a></div>
	                                	 </c:if>
	                                </div>
	                            </div>
	                            <div><a href="/serviceView.do?sNo=${serviceList[status.index].SNo}&reqPage=1&mNo=${loginMember.MNo}">${serviceList[status.index].STitle }</a></div>
	                        </div>
	                    </div>
	                    <div>
	                    	<c:choose>
	                    		<c:when test="${t.TStatus == 0 }">
	                    			<div style="color: rgb(255, 143, 63);font-size: 20px;margin-top: 5px;font-weight: bold;">결제 전</div>
			                        <div>
			                        	<input type="text" style="display:none" value="${serviceList[status.index].STitle }">
			                        	<input type="text" style="display:none" value="${loginMember.MNo }">
			                        	<input type="text" style="display:none" value="${loginMember.MName }">
			                        	<input type="text" style="display:none" value="${loginMember.MPhone }">
			                        	<input type="text" style="display:none" value="${loginMember.MEmail }">
			                        	<input type="text" style="display:none" value="${t.TNo }">
	                    				<input type="text" style="display:none" value="${t.TPrice }">
	                    				<button class="pay-btn">결제하기</button>
	                    			</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;margin-top: 20px;">예상 마감일</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;margin-top: 5px;font-weight: bold;">${t.endDate }</div>
	                    		</c:when>
	                    		<c:when test="${t.TStatus == 1 }">
	                    			<div style="color: rgb(255, 143, 63);font-size: 20px;margin-top: 5px;font-weight: bold;">결제 완료</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 13px;">${payDate[status.index] }</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;margin-top: 20px;">예상 마감일</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;margin-top: 5px;font-weight: bold;">${t.endDate }</div>
	                    		</c:when>
	                    		<c:when test="${t.TStatus == 2 || t.TStatus == 3}">
	                    			<div style="color: rgb(224, 224, 224);font-size: 20px;margin-top: 5px;font-weight: bold;">작업 완료</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 13px;">${payDate[status.index] }</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;margin-top: 20px;">작업 종료일</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;margin-top: 5px;font-weight: bold;">${t.endDate }</div>
	                    		</c:when>
	                    	</c:choose>
	                        
	                    </div>
	                    <div>
	                    <c:choose>
	                    	<c:when test="${t.TStatus == 2 }">
	                    		<input type="text" style="display:none" value="${serviceList[status.index].STitle }">
	                    		<input type="text" style="display:none" value="${serviceList[status.index].SImg }">
	                    		<input type="text" style="display:none" value="${t.TNo }">
	                    		<input type="text" style="display:none" value="${t.SNo }">
	                    		<input type="text" style="display:none" value="${loginMember.MId }">
	                    		<button class="review-btn">후기 작성하기</button>
	                    	</c:when>
	                    	<c:when test="${t.TStatus == 3 }">
	                    		<input type="text" style="display:none" value="${t.TNo }">
	                    		<input type="text" style="display:none" value="${t.SNo }">
	                    		<input type="text" style="display:none" value="${loginMember.MId }">
	                    		<button class="reviewCheck-btn">후기 확인하기</button>
	                    	</c:when>
	                    	<c:otherwise>
	                    		 아직 후기를 <br>작성할 수 없습니다
	                    	</c:otherwise>
	                    </c:choose>
	                    </div>
	                </div>
                </c:forEach>
                </c:if>
            </div>
        </div>
	</div>
	<br><br><br>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
	
	<script>
		$(document).ready(function(){
			//페이지 높이 동적 적용
			var containerHeight = $(".board-wrap").height();
			$(".page-wrap").height(containerHeight+200);
			
			//메뉴 고정
	   		 $(".menu").children().eq(2).find('a').css({'margin-left':'5px', 'font-weight':'bold'});
	   		 $(".menu").children().eq(2).find('img').css({'display':'inline'});
		});

		
		$(".review-btn").click(function(){
			// 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
		    var _left = Math.ceil(( window.screen.width - 473 )/2);
			
			var mId = $(this).prev().val();
			var sNo = $(this).prev().prev().val();
			var tNo = $(this).prev().prev().prev().val();
			var sImg = $(this).prev().prev().prev().prev().val();
			var sTitle = $(this).prev().prev().prev().prev().prev().val();
			console.log("아이디"+sTitle);
			window.open('/serviceReviewWrite.do?tNo='+tNo+'&sNo='+sNo+'&mId='+mId+'&sImg='+sImg+'&sTitle='+sTitle,'거래후기 작성', 'width=430, height=510, left='+ _left + ', top=50, scrollbars=no,location=no, resizable=no');
			return false;
		});
		
		$(".reviewCheck-btn").click(function(){
			var _left = Math.ceil(( window.screen.width - 473 )/2);
		    var mId = $(this).prev().val();
			var sNo = $(this).prev().prev().val();
			var tNo = $(this).prev().prev().prev().val();
		    window.open('/serviceReviewView.do?tNo='+tNo+'&sNo='+sNo+'&mId='+mId, '거래 후기 확인', 'width=433, height=490, left='+_left+', top=50, scrollbars=no, location=no, resizable=no');
			return false;
		});
		
		function trade_open(obj){
			var tNo = $(obj).html();
			var _left = Math.ceil(( window.screen.width - 473 )/2);
			window.open('/serviceTradeView.do?tNo='+tNo, '거래 내용 확인', 'width=473, height=480, left='+_left+', top=50, scrollbars=no, location=no, resizable=no');
			return false;
		}
		
		$(".pay-btn").click(function(){
			var tPrice = $(this).prev().val();
			var tNo = $(this).prev().prev().val();
			var mEmail = $(this).prev().prev().prev().val();
			var mPhone = $(this).prev().prev().prev().prev().val();
			var mName = $(this).prev().prev().prev().prev().prev().val();
			var mNo = $(this).prev().prev().prev().prev().prev().prev().val();
			var sTitle = $(this).prev().prev().prev().prev().prev().prev().prev().val();
			var d = new Date();
			var date = d.getFullYear()+''+(d.getMonth()+1)+''+d.getDate()+''+d.getHours()+''+d.getMinutes()+''+d.getSeconds();
			
			//할 수 있으면 jQuery 모달로 하기
			var result = confirm("거래 내용을 충분히 확인하셨나요? \n'확인'버튼을 누르시면 결제가 진행됩니다.");
			if(result){

		  		//여기""안에 내정보-가맹점 식별코드 넣기
		  		IMP.init("imp45748539");
		  		IMP.request_pay({
		  			merchant_uid : date, //거래ID(고유)
		  			name : sTitle, //결제이름(거래번호)
		  			amount : tPrice, //결제금액
		  			buyer_email : mEmail,//구매자email
		  			buyer_name : mName, //구매자이름
		  			buyer_tel : mPhone //구매자 전화번호
		  		}, function(rsp) {
		  			if(rsp.success) { //결제가 성공한 경우 -> 나중에 이거 DB에 넣으면 됨!
		  				console.log("성공");
		  				location.href = "/insertServicePay.do?pNo="+mNo+"&tNo="+tNo+"&pPrice="+tPrice;
		  			} else {	//결제가 실패한 경우
		  				console.log("실패");
		  			}
		  		});
				
			}
		});
		
		//거래상세보기 창에서 서비스 상세보기를 누르면
		function gotoServiceView(sNo,mNo){
			location.href="/serviceView.do?sNo="+sNo+"&reqPage=1&mNo="+mNo;
		}
		
	    function startChat(sNo, userId, freeId, mNo, mGrade) {
			$.ajax({
				url : "/makeRoom.do",
				type : "post",
				async : false,
				data : {
					sNo : sNo,
					userId : userId,
					freeId : freeId,
					mNo : mNo,
					mGrade : mGrade
				},
				success : function(data) {
					/* location.href = "/enterRoom.do?cNo=-1&sNo=" + sNo
							+ "&myId=" + userId + "&yourId=" + freeId
							+ "&mGrade=" + mGrade */
					var loc="/enterRoom.do?cNo=-1&sNo=" + sNo + "&myId="+ userId + "&yourId=" + freeId+"&mGrade="+mGrade
					var _left = Math.ceil(( window.screen.width - 530 )/2);
					window.open(loc, '', 'width=530, height=630, left='+_left+', top=50, location=no,scrollbars=no,location=no, resizable=no'); 
				},
				error : function() {

				}
			});
		}
	</script>

</body>
</html>