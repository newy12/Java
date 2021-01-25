<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 나의 서비스</title>

<style>
.page-wrap {
	width: 1104px;
	height: 1000px;
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

.ListContainer {
	margin-top: 20px;
	width: 795px;
	height: 1000px;
}

.board-box>span {
	font-size: 18px;
	font-weight: bold;
	color: rgb(49, 76, 131);
	line-height: 45px;
	margin-left: 30px;
}

.array {
	width: 100px;
	height: 31px;
	float: right;
	margin-top: 7px;
	margin-right: 10px;
	border: 1px solid rgb(204, 204, 204);
}

.ListContainer>div {
	width: 230px;
	height: 270px;
	float: left;
	margin: 10px;
}

.title-img {
	position: relative;
	top: 0;
	left: 0;
}

.back-img>img:hover {
	transform: scale(1.1);
	overflow: hidden;
}

.title-img>img {
	position: absolute;
}

.back-img {
	width: 224px;
	height: 133px;
	overflow: hidden;
	position: absolute;
	border-radius: 5px;
}

.back-img>img {
	transform: scale(1);
	transition: transform 0.5s linear;
}

.heart-btn {
	top: 10px;
	left: 180px;
	z-index: 1000;
}

.empty {
	width: 199px;
	height: 135px;
}

.heart-btn:hover {
	background-image: url(heart_navy.png);
	background-repeat: no-repeat;
}

.title {
	color: rgb(127, 127, 127);
	font-size: 13px;
	margin-top: 5px;
	"
}

.content {
	font-size: 15px;
	margin-top: 10px;
	margin-bottom: 10px;
	text-overflow: ellipsis;
	color: rgb(51, 51, 51);
	/* overflow: hidden; */
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
}

.price {
	float: right;
	font-size: 17px;
	font-weight: bold;
	color: rgb(51, 51, 51);
}

.ListContainer>div>a {
	text-decoration: none;
}

.rate {
	float: right;
}

.rate>span {
	color: rgb(51, 51, 51);
	font-size: 13px;
}

.rate>span:last-child {
	font-size: 17px;
	color: rgb(241, 196, 15);
}

.inner {
	width: 1200px;
	margin: 0 auto;
	padding-left: 30px;
	padding-right: 30px;
}

.noList {
	margin-top: 5px;
	text-align: center;
	line-height: 185px;
	color: rgb(224, 224, 224);
	font-size: 20px;
	font-weight: bold;
}
.cate{
	margin-top : 10px;
	font-size: 9pt;
	color: gray;
	font-weight: bold;
}

.del-btn {
	height: 30px;
	width: 100px;
	border: 2px solid #314C83;
	background-color: transparent;
	color: #314C83;
	font-weight: bold;
	border-radius: 5px;
	float: right;
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

		<input type="hidden" value="${loginMember.MName }" class="memberId">
		<div class="board-wrap">
			<div class="board-box">
				<span>서비스 내역</span>
				<select class="selectBox form-control array" id="search-subject" style="width: 150px;">
					<option value="agree">승인된 서비스</option>
					<option value="refuse">미등록 서비스</option>
				</select>
			</div>
			<div class="inner">
				<div class="ListContainer">
					<c:choose>
						<c:when test="${j.serviceList.size() == 0}">
							<div class="noList" style="width: 800px">등록된 서비스가 없습니다.</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${list}" var="s" begin="0" end="4">
								<div>
									<div>
										<input type="hidden" value="${s.SNo }" id="sNo"> <a href="/serviceView.do?sNo=${s.SNo}&reqPage=1&mNo=${loginMember.MNo }">
											<div class="title-img">
												<div class="back-img">
													<img src="/upload/service/${s.SImg}" width="225x" height="133px">
												</div>
											</div>
										</a>

									</div>
									<div class="empty"></div>
									<div class="cate">
										${s.mainCategoryName } / ${s.subCategoryName }
									</div>
									<div class="title"><a href="/serviceView.do?sNo=${s.SNo}&reqPage=1&mNo=${loginMember.MNo }"> ${s.STitle} </a></div>
									<a href="#">
										<div class="content" style="font-size: 12pt; float: right; width: 200px; text-align: right;"  >${s.SPriceTxt }원~</div>
									</a>
									<button class="del-btn" value="삭제하기" onclick="del();">삭제하기</button>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
		</div>
	</div>

	<br>
	<br>
	<br>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />


	<script>
		$(document).ready(function(){
			//메뉴 고정
			 $(".menu").children().eq(2).find('a').css({'margin-left':'5px', 'font-weight':'bold'});
			 $(".menu").children().eq(2).find('img').css({'display':'inline'});
			 
			var order = "${order}";
			var id = "${loginMember.MId}";
			console.log("order : "+order);
			console.log("memberId : "+ id);
			
			if(order == "agree"){
				$(".array").val("agree").prop("select","seleted");
			}else if ( order == "refuse")
				$(".array").val("refuse").prop("select","seleted");
			
			$(".array").change(function () {
				var order = $(".array").val();
				location.href="/freelancerServiceList.do?mId="+id+"&order="+order;
			})
			 
		});

		
 	   function del(){
    		var sNo = $("#sNo").val();
    		console.log("sNo :"+sNo);
    		var confirm_test = confirm("해당 서비스를 삭제할까요?");
    		var id = "${loginMember.MId}";
    		
    		if(confirm_test == true){
    			location.href = "/delService.do?sNo="+sNo+"&mId="+id;
    		}
    		
    	};
    	
    	</script>
   
</body>
</html>