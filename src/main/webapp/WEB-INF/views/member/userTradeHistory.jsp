<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>거래 내역</title>

<style>
    .page-wrap {
        width: 1104px;
        margin: 0 auto;
        margin-top:50px;
    }

    .profile-box {
        width: 250px;
        float: left;
    }

    .profile-box>div {
        text-align: center;
        margin: 0 auto;
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

    .switch {
        height: 31px;
        margin-bottom: 30px;
        color: rgb(49, 76, 131);
        background-color:white;
        border: 1px solid rgb(49, 76, 131);
    }

    .switch:hover {
        background-color: rgb(49, 76, 131);
        color: white;
        border: 1px solid rgb(49, 76, 131);
    }

    .menu{
        padding: 0;
        list-style: none;
    }
    .menu>li{
        margin-bottom: 20px;
    }
    .menu>li>img{
        display: none;
    }
    .menu>li:hover>img{
        display: inline;
    }
    .menu>li:hover>a{
        margin-left: 5px;
        font-weight: bold; 
    }
    .menu>li>a{
        text-decoration: none;
        color: rgb(51, 51, 51);
    }

    .profile-box>p {
        font-size: 18px;
        font-weight: bold;
        color: rgb(49, 76, 131);
    }

    .board-box>span {
        font-size: 18px;
        font-weight: bold;
        color: rgb(49, 76, 131);
        line-height: 45px;
        margin-left: 30px;
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
        margin-top: 5px;
        margin-right: 10px;
        float: left;
    }
    .content-box>div:nth-child(2)>div>div>div>div>a>img{
        width: 25px;
        height: 25px;
    }
    .content-box>div:nth-child(2)>div>div>div>div>a{
        color: rgb(51, 51, 51);
        line-height: 25px;
    }
    .content-box>div:nth-child(2)>div>div:last-child>a{
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
    .review-btn{
        width: 100px;
        height: 26px;
        font-size:13px;
        font-weight: bold;
        margin-top: 20px;
        margin-left:4px;
        color: white;
        background-color:  rgb(49, 76, 131);
        border: 1px solid rgb(49, 76, 131);
    }
    .review-btn:hover{
        color: rgb(49, 76, 131);
        background-color: white;
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
        color: rgb(49, 76, 131);
        background-color: white;
        border: 1px solid rgb(49, 76, 131);
    }
</style>
</head>
<body>
	<div class="header">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
    <div class="page-wrap">
        <div class="profile-box">
            <div><img src="/img/icon/mypage_person.png" style="width: 147px; height: 147px"></div>
            <div style="margin-top: 10px;">${loginMember.MId }님</div>
            <div style="margin-top: 5px;"><button class="switch">프리랜서로 전환</button></div>
            <p>MY PAGE</p>
            <hr>
            <ul class="menu">
                <li><img src="/img/icon/circle_navy.png"><a href="/userMypage.do?MNo=${loginMember.MNo}">나의 프로필</a></li>
                <li><img src="/img/icon/circle_navy.png"><a href="/userHeartList.do?mNo=${loginMember.MNo }&order=all">찜한 내역</a></li>
                <li><img src="/img/icon/circle_navy.png" style="display: inline;"><a href="#"  style="margin-left: 5px; font-weight: bold; ">거래 내역</a></li>
            </ul>
        </div>
        
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
	                            <div><a href="#"><img src="/img/icon/img.jpg" width="175px" height="103px"></a></div>
	                            <div style="color:#8a8a8a;">
	                                사용자 ID
	                                <div>
	                                    <div><a href="#">${serviceList[status.index].MId }</a></div>
	                                    <div><a href="#"><img src="/img/icon/home.png"></a></div>
	                                    <div><a href="#"><img src="/img/icon/message.png"></a></div>
	                                </div>
	                            </div>
	                            <div><a href="#">${serviceList[status.index].SContent }</a></div>
	                        </div>
	                    </div>
	                    <div>
	                    	<c:choose>
	                    		<c:when test="${t.TStatus == 0 }">
	                    			<div style="color: rgb(255, 143, 63);font-size: 20px;margin-top: 5px;font-weight: bold;">결제 전</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;"></div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;margin-top: 20px;">예상 마감일</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;margin-top: 5px;font-weight: bold;">${t.endDate }</div>
	                    		</c:when>
	                    		<c:when test="${t.TStatus == 1 }">
	                    			<div style="color: rgb(255, 143, 63);font-size: 20px;margin-top: 5px;font-weight: bold;">결제 완료</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;">${payDate[status.index] }</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;margin-top: 20px;">예상 마감일</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;margin-top: 5px;font-weight: bold;">${t.endDate }</div>
	                    		</c:when>
	                    		<c:when test="${t.TStatus == 2 || t.TStatus == 3}">
	                    			<div style="color: rgb(224, 224, 224);font-size: 20px;margin-top: 5px;font-weight: bold;">작업 완료</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;">${payDate[status.index] }</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;margin-top: 20px;">작업 종료일</div>
			                        <div style="color: rgb(51, 51, 51);font-size: 15px;margin-top: 5px;font-weight: bold;">${t.endDate }</div>
	                    		</c:when>
	                    	</c:choose>
	                        
	                    </div>
	                    <div>
	                    <c:choose>
	                    	<c:when test="${t.TStatus == 2 }">
	                    		<input type="text" style="display:none" value="${serviceList[status.index].SContent }">
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
			var containerHeight = $(".container-box").height();
			$(".page-wrap").height(containerHeight+400);
		});

		
		$(".review-btn").click(function(){
			// 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
		    var _left = Math.ceil(( window.screen.width - 473 )/2);
			
			var mId = $(this).prev().val();
			var sNo = $(this).prev().prev().val();
			var tNo = $(this).prev().prev().prev().val();
			var sImg = $(this).prev().prev().prev().prev().val();
			var sContent = $(this).prev().prev().prev().prev().prev().val();
			window.open('/serviceReviewWrite.do?tNo='+tNo+'&sNo='+sNo+'&mId='+mId+'&sImg='+sImg+'&sContent='+sContent,'거래후기 작성', 'width=473, height=510, left='+ _left + ', top=50, scrollbars=no,location=no, resizable=no');
			return false;
		});
		
		$(".reviewCheck-btn").click(function(){
			var _left = Math.ceil(( window.screen.width - 473 )/2);
		    var mId = $(this).prev().val();
			var sNo = $(this).prev().prev().val();
			var tNo = $(this).prev().prev().prev().val();
		    window.open('/serviceReviewView.do?tNo='+tNo+'&sNo='+sNo+'&mId='+mId, '거래 후기 확인', 'width=473, height=480, left='+_left+', top=50, scrollbars=no, location=no, resizable=no');
			return false;
		});
		
		function trade_open(obj){
			var tNo = $(obj).html();
			var _left = Math.ceil(( window.screen.width - 473 )/2);
			window.open('/serviceTradeView.do?tNo='+tNo, '거래 내용 확인', 'width=473, height=480, left='+_left+', top=50, scrollbars=no, location=no, resizable=no');
			return false;
		}
	</script>

</body>
</html>