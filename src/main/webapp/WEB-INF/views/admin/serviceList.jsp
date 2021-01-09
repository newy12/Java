<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시(관리자) :: 서비스관리</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>

<style>
th, td {
	padding: 5px;
}

.serviceNavi ul {
	list-style-type: none;
	overflow: hidden;
	padding: 0px;
	margin: 0px;
}

.tab {
	float: left;
	width: 150px;
	height: 60px;
	text-align: center;
	line-height: 60px;
	color: white;
	background-color: #314C83;
	font-weight: bold;
	box-sizing: border-box;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	border: 2px solid #314C83;
	border-bottom: none;
}

.adminContent div {
	margin: 30px;
	text-align: left;
	display: none;
}

.select {
	color: #314C83;
	background-color: white;
	font-weight: bold;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/admin/adminMainpage.jsp" />

	<div class="serviceNavi">
		<ul>
			<li class="tab">미등록서비스</li>
			<li class="tab">등록서비스</li>
		</ul>
	</div>
	<div class="adminContent">
		<div>
			<h1>미등록 서비스 관리</h1>
			<table border=1 id="waiting">
				<tr>
					<th colspan='7'>승인 대기 중 서비스</th>
				</tr>
				<tr>
					<th>등록일</th>
					<th>서비스번호</th>
					<th>아이디</th>
					<th>카테고리</th>
					<th>서비스명</th>
					<th>처리</th>
				</tr>
				<c:forEach items="${serviceList }" var="s">
					<c:if test="${s.adminApproval eq 'n'.charAt(0)}">
						<tr>
							<td>${s.writeDate }</td>
							<td>${s.SNo }</td>
							<td>${s.MId }<c:if test="${s.MId eq null}">탈퇴한회원</c:if></td>
							<td>메인:${ s.mainCategory}서브:${s.subCategory }</td>
							<td><a href=#>${s.STitle }</a></td>
							<td>
								<button id="acceptBtn" onclick="acceptService(${s.SNo});">승인</button>
								<button id="reject">
									<a href="/rejectFrm.do?sNo=${s.SNo }"
										onClick="window.open(this.href, '', 'width=800, height=400, left=1000, scrollbars=no,location=no, resizable=no'); return false;">거절</a>
								</button>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>

			<table border=1 id="deleted">
				<tr>
					<th colspan='7'>거절/삭제된 서비스</th>
				</tr>
				<tr>
					<th>등록일</th>
					<th>서비스번호</th>
					<th>아이디</th>
					<th>카테고리</th>
					<th>서비스명</th>
					<th>작업수</th>
				</tr>
				<c:forEach items="${serviceList }" var="s">
					<c:if test="${s.deleteStatus eq 'y'.charAt(0)}">
						<tr>
							<td>${s.writeDate }</td>
							<td>${s.SNo }</td>
							<td>${s.MId }<c:if test="${s.MId eq null}">탈퇴한회원</c:if></td>
							<td>메인:${ s.mainCategory}서브:${s.subCategory }</td>
							<td><a href=#>${s.STitle }</a></td>
							<td><a href="">${s.workingCount }</a></td>

						</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>

		<div>
			<h1>등록 서비스 관리</h1>
			<table border=1 id="approved">
				<tr>
					<th colspan='7'>승인된 서비스</th>
				</tr>
				<tr>
					<th>등록일</th>
					<th>서비스번호</th>
					<th>아이디</th>
					<th>카테고리</th>
					<th>서비스명</th>
					<th>작업수</th>
					<th>처리</th>
				</tr>
				<c:forEach items="${serviceList }" var="s">
					<c:if test="${s.adminApproval eq 'y'.charAt(0)}">
						<c:if test="${s.deleteStatus eq 'n'.charAt(0)}">
							<tr>
								<td>${s.writeDate }</td>
								<td>${s.SNo }</td>
								<td>${s.MId }<c:if test="${s.MId eq null}">탈퇴한회원</c:if></td>
								<td>메인:${ s.mainCategory}서브:${s.subCategory }</td>
								<td><a href=#>${s.STitle }</a></td>
								<td><a href="/workingHistory.do?sNo=${s.SNo }"
									onClick="window.open(this.href, '', 'width=800, height=400, left=1000, scrollbars=no,location=no, resizable=no'); return false;">${s.workingCount }</a></td>
								<td>
									<button id="delete" onclick="deleteService(${s.SNo});">삭제</button>
								</td>
							</tr>
						</c:if>
					</c:if>
				</c:forEach>
			</table>
		</div>

	</div>
	<script>
	
	$(function() {
        $(".adminContent>div").eq(0).show();
        $(".tab").eq(0).addClass("select");
     });
	
	 $(".tab").click(function() {
         var tabIdx = $(this).index();
         var content = $(".adminContent");
         for (var i = 0; i <= tabIdx; i++) {
            $(".adminContent>div").css("display", "none");
            $(".tab").removeClass("select");
         }
         $(".adminContent>div").eq(tabIdx).css("display", "block");
         $(".tab").eq(tabIdx).addClass("select");

      });
	
	
	
		function acceptService(sNo){
			$.ajax({
				url : "/acceptService.do",
				type : "post",
				data : {
					sNo : sNo,
				},
				success : function(data) {
					console.log("승인 성공!");
					location.reload();
			},
			  error : function(){
                  console.log("승인 실패!");
               }
			});
		}
	
		
		function deleteService(sNo){
			check=confirm("서비스를 삭제합니다")
			if(check){
			$.ajax({
				url : "/deleteService.do",
				type : "post",
				data : {
					sNo : sNo,
				},
				success : function(data) {
					console.log("삭제 성공!");
					location.reload();
			},
			  error : function(){
                  console.log("삭제 실패!");
               }
			});
		}}
		
		
		
	</script>
</body>
</html>