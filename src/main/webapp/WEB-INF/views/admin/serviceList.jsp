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
						<c:if test="${s.deleteStatus eq 'n'.charAt(0)}">
							<c:if test="${s.SNo ne 0 }">
								<tr>
									<td>${s.writeDate }</td>
									<td>${s.SNo }</td>
									<td>${s.MId }<c:if test="${s.MId eq null}">탈퇴한회원</c:if></td>
									<td><b>[${ s.MCatName}]</b>${s.SCatName }</td>
									<td><a href=#>${s.STitle }</a></td>
									<td><c:forEach items="${mIdandmNo }" var="m">
											<c:if test="${m.key eq s.MId }">
												<button id="acceptBtn"
													onclick="acceptService('${m.value }','${s.MId }','${s.SNo}','${s.STitle }');">승인</button>
											</c:if>
										</c:forEach>
										<button id="reject">
											<a href="/rejectFrm.do?sNo=${s.SNo }"
												onClick="window.open(this.href, '', 'width=800, height=400, left=1000, scrollbars=no,location=no, resizable=no'); return false;">거절</a>
										</button></td>
								</tr>
							</c:if>
						</c:if>
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
					<th>상태</th>
				</tr>
				<c:forEach items="${serviceList }" var="s">
					<c:if test="${s.deleteStatus eq 'y'.charAt(0)}">
						<tr>
							<td>${s.writeDate }</td>
							<td>${s.SNo }</td>
							<td>${s.MId }<c:if test="${s.MId eq null}">탈퇴한회원</c:if></td>
							<td><b>[${ s.MCatName}]</b>${s.SCatName }</td>
							<td><a href=#>${s.STitle }</a></td>
							<td>
								<!-- 작업수가 0이 아닐때 클릭가능-->
								<c:if test="${s.workingCount ne 0}">
								<a href="/tradeHistory.do?sNo=${s.SNo }&mNo=-1"
									onClick="window.open(this.href, '', 'width=800, height=400, left=1000, scrollbars=no,location=no, resizable=no'); return false;">
									${s.workingCount }</a>
									</c:if>
									<c:if test="${s.workingCount eq 0}">${s.workingCount}</c:if>
									</td>
							<td>
								<!-- 승인 n&삭제y -> 거절 --> 
								<c:if test="${s.adminApproval eq 'n'.charAt(0)}">거절</c:if> 
								<!-- 승인 y&삭제y -> 삭제 -->
								<c:if test="${s.adminApproval eq 'y'.charAt(0)}">삭제</c:if>
							</td>
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
								<td><b>[${ s.MCatName}]</b>${s.SCatName }</td>
								<td><a href=#>${s.STitle }</a></td>
								<td>
								<!-- 작업수가 0이 아닐때 클릭가능-->
								<c:if test="${s.workingCount ne 0}">
								<a href="/tradeHistory.do?sNo=${s.SNo }&mNo=-1"
									onClick="window.open(this.href, '', 'width=800, height=400, left=1000, scrollbars=no,location=no, resizable=no'); return false;">
									${s.workingCount }</a>
									</c:if>
									<c:if test="${s.workingCount eq 0}">${s.workingCount}</c:if>
									</td>
								<td><c:forEach items="${mIdandmNo }" var="m">
										<c:if test="${m.key eq s.MId }">
											<button id="delete"
												onclick="deleteService('${m.value }','${s.MId }','${s.SNo}','${s.STitle }');">삭제</button>
										</c:if>
									</c:forEach></td>
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
	
	  //메세지 보내기
		function sendMsg(mNo,mId,content) {
			// 현재 시간 구하기
			var now = new Date();
			var year = now.getFullYear();
			var month = now.getMonth() + 1;
			if (month < 10) {
				month = "0" + month;
			}
			var day = now.getDate();
			if (day < 10) {
				day = "0" + day;
			}
			var hour = now.getHours();
			var minute = now.getMinutes();
			if (minute < 10 & minute > 0) {
				minute = "0" + minute;
			}
			var ampm;
			if (hour < 12) {
				ampm = "오전 ";
				if (hour < 10) {
					hour = "0" + hour;
				}
			} else {
				ampm = "오후 ";
				if (hour > 12) {
					hour = hour - 12;
					if (hour < 10) {
						hour = "0" + hour;
					}
				}
			}

			
			var date = year + "년 " + month + "월 " + day + "일";
			var time = ampm + hour + ":" + minute; //보낸 시간
			var freeId = "admin";
			var mNo=Number(mNo);
			var mId=mId;

			//admin과 회원간의 채팅방 생성
			//메세지보내기
			$.ajax({
				url : "/makeRoom.do",
				type : "post",
				async : false,
				data : {
					sNo : 0,
					userId : mId,
					freeId : freeId,
					mNo : mNo
				},
				success : function(data) {
					console.log("111111");
					//방만들기 성공했을때
					var cNo = data.cNo; //방번호 
					cNo = Number(cNo);

					//메세지
					$.ajax({
						url : "/insertChat.do",
						type : "post",
						async : false,
						data : {
							cNo:cNo,
							myId:freeId,
							date:date,
							time:time,
							content:content
						},
						success : function(data) {
							console.log("메세지전송성공!");
						},
						error : function() {

						}
					}); 
				},
				error : function() {

				}
			}); 
		} 
		
	
	 
	 /* 서비스승인 */
		function acceptService(mNo,mId,sNo,sTitle){
			var mNo=Number(mNo);
			var mId=mId;
			var sNo=Number(sNo);
			var sTitle=sTitle;
			$.ajax({
				url : "/acceptService.do",
				type : "post",
				async : false,
				data : {
					sNo : sNo,
				},
				success : function() {
					console.log("승인 성공!");
					var content ="서비스 <b> ["+sTitle+"]</b>가 <b>등록</b>되었습니다.";
					sendMsg(mNo,mId,content);
					location.reload(); 
					
			},
			  error : function(){
                  console.log("승인 실패!");
               }
			});
		}
		
	 /* 서비스삭제 */
		function deleteService(mNo,mId,sNo,sTitle){
			console.log("삭제!!!!!");
			var mNo=Number(mNo);
			var mId=mId;
			var sNo=Number(sNo);
			var sTitle=sTitle;
			check=confirm("서비스를 삭제합니다")
			if(check){
			$.ajax({
				url : "/deleteService.do",
				type : "post",
				async : false,
				data : {
					sNo : sNo,
				},
				success : function() {
					console.log("삭제 성공!");
					var content ="서비스 <b> ["+sTitle+"]</b>가 <b>삭제</b>되었습니다.";
					sendMsg(mNo,mId,content);
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