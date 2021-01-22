<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>19시(관리자) :: 서비스관리</title>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180"
	href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="favicon_io/favicon-16x16.png">
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
			<li class="tab">거절/삭제된서비스</li>
		</ul>
	</div>
	<div class="adminContent">
		<c:if test="${not empty serviceList }">
			<div>
				<%-- <h1 style="margin-bottom: 0;">
					<c:if test="${page eq 'waiting' }">미등록 서비스</c:if>
					<c:if test="${page eq 'approved' }">등록 서비스</c:if>
					<c:if test="${page eq 'deleted' }">거절/삭제된 서비스</c:if>
				</h1> --%>
				<div
					style="float: right; margin-right: 10%; margin-top: 0; margin-bottom: 20px;">

					<select>
						<c:choose>
							<c:when test="${keyword1 eq 'title' }">
								<option value="title" selected>서비스명</option>
							</c:when>
							<c:otherwise>
								<option value="title">서비스명</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${keyword1 eq 'free' }">
								<option value="free" selected>id</option>
							</c:when>
							<c:otherwise>
								<option value="free">id</option>
							</c:otherwise>
						</c:choose>
					</select> <input type="text" placeholder="검색" class="keyword2">
					<button class="btn search" onclick="search('${page}');">검색</button>
					<br>
					<div style="margin: 0 auto; margin-top: 5px; font-size: 15px;">
						<b>정렬 : </b>
						<c:choose>
							<c:when test="${order eq 'old' }">
								<input type="radio" name="order" value="old" checked>등록일↑</c:when>
							<c:otherwise>
								<input type="radio" name="order" value="old">등록일↑</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${order eq 'new' }">
								<input type="radio" name="order" value="new" checked>등록일↓</c:when>
							<c:otherwise>
								<input type="radio" name="order" value="new">등록일↓</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${order eq 'working' }">
								<input type="radio" name="order" value="working" checked>작업수↓</c:when>
							<c:otherwise>
								<input type="radio" name="order" value="working">작업수↓</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${order eq 'cate' }">
								<input type="radio" name="order" value="cate" checked>카테고리↓</c:when>
							<c:otherwise>
								<input type="radio" name="order" value="cate">카테고리↓</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${order eq 'id' }">
								<input type="radio" name="order" value="id" checked>id↓</c:when>
							<c:otherwise>
								<input type="radio" name="order" value="id">id↓</c:otherwise>
						</c:choose>

					</div>
				</div>
				<c:if test="${not empty keyword1}">
					<div>
						<c:if test="${keyword1 eq 'title'}">
							<b>서비스명 : </b>
						</c:if>
						<c:if test="${keyword1 eq 'free'}">
							<b>프리랜서 : </b>
						</c:if>

						[${keyword2 }] 의<b> 검색 결과</b>
					</div>
				</c:if>
				<table class="serviceTable" style="width: 90%;">

					<tr>
						<th>등록일</th>
						<th>서비스번호</th>
						<th>아이디</th>
						<th>카테고리</th>
						<th>서비스명</th>
						<c:if test="${page eq 'waiting'}">
							<th>처리</th>
						</c:if>
						<c:if test="${page ne 'waiting'}">
							<th>작업수</th>
						</c:if>
						<c:if test="${page eq 'approved'}">
							<th>처리</th>
						</c:if>
						<c:if test="${page eq 'deleted'}">
							<th>상태</th>
						</c:if>
					</tr>
					<c:forEach items="${serviceList }" var="s">

						<c:if test="${s.SNo ne 0 }">
							<tr>
								<td>${s.writeDate }</td>
								<td>${s.SNo }</td>
								<td>${s.MId }<c:if test="${s.MId eq null}">탈퇴한회원</c:if></td>
								<td style="text-align: left; padding-left: 15px;"><b>[${ s.MCatName}]</b>${s.SCatName }</td>
								<td style="text-align: left; padding-left: 15px;"><a href="/serviceView2.do?sNo=${s.SNo}&reqPage=1" onClick="window.open(this.href, '', 'width=1200, height=1800, left=1000, scrollbars=no,location=no, resizable=no'); return false;">${s.STitle }</a></td>
								<!-- 미등록서비스 -->
								<c:if test="${page eq 'waiting'}">
									<td><c:forEach items="${mIdandmNo }" var="m">
											<c:if test="${m.key eq s.MId }">
												<button class="btn acceptBtn"
													onclick="acceptService('${m.value }','${s.MId }','${s.SNo}','${s.STitle }');">승인</button>
											</c:if>
										</c:forEach>
										<button class="btn rejectBtn">
											<a href="/rejectFrm.do?sNo=${s.SNo }"
												onClick="window.open(this.href, '', 'width=800, height=400, left=1000, scrollbars=no,location=no, resizable=no'); return false;">거절</a>
										</button></td>
								</c:if>
								<!-- 미등록서비스 -->

								<!-- 등록된 서비스 -->
								<c:if test="${page ne 'waiting'}">
									<td>
										<!-- 작업수가 0이 아닐때 클릭가능--> <c:if test="${s.workingCount ne 0}">
											<a href="/tradeHistory.do?sNo=${s.SNo }&mNo=-1"
												onClick="window.open(this.href, '', 'width=800, height=400, left=1000, scrollbars=no,location=no, resizable=no'); return false;">
												${s.workingCount }</a>
										</c:if> <c:if test="${s.workingCount eq 0}">${s.workingCount}</c:if>
									</td>
									<c:if test="${page eq 'approved'}">
										<td><c:forEach items="${mIdandmNo }" var="m">
												<c:if test="${m.key eq s.MId }">
													<button class="btn deleteBtn"
														onclick="deleteService('${m.value }','${s.MId }','${s.SNo}','${s.STitle }');">삭제</button>
												</c:if>
											</c:forEach></td>
									</c:if>
								</c:if>
								<!-- 등록된 서비스 -->


								<!-- 거절/삭제된 서비스 -->
								<c:if test="${page eq 'deleted'}">
									<td>
										<!-- 승인 n&삭제y -> 거절 --> <c:if
											test="${s.adminApproval eq 'n'.charAt(0)}">거절</c:if> <!-- 승인 y&삭제y -> 삭제 -->
										<c:if test="${s.adminApproval eq 'y'.charAt(0)}">삭제</c:if>
									</td>
								</c:if>
								<!-- 거절/삭제된 서비스 -->



							</tr>
						</c:if>


					</c:forEach>
				</table>
				<div style="width: 100%; margin: 10px; text-align: center;">
					<ul class="pageNavi">${pageNavi }</ul>
				</div>
			</div>
		</c:if>
		<c:if test="${empty serviceList }">
			<div>
				<br> <br> <br> <br>
				<div
					style="float: right; margin-right: 10%; margin-top: 0; margin-bottom: 20px;">

					<select>
						<c:choose>
							<c:when test="${keyword1 eq 'title' }">
								<option value="title" checked>서비스명</option>
							</c:when>
							<c:otherwise>
								<option value="title">서비스명</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${keyword1 eq 'free' }">
								<option value="free" checked>프리랜서</option>
							</c:when>
							<c:otherwise>
								<option value="free">프리랜서</option>
							</c:otherwise>
						</c:choose>
					</select> <input type="text" placeholder="검색" class="keyword2">
					<button class="btn search" onclick="search('${page}');">검색</button>
					<br>
					<div style="margin: 0 auto; margin-top: 5px; font-size: 15px;">
						<b>정렬 : </b>
						<c:choose>
							<c:when test="${order eq 'old' }">
								<input type="radio" name="order" value="old" checked>최신순↑</c:when>
							<c:otherwise>
								<input type="radio" name="order" value="old">최신순↑</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${order eq 'new' }">
								<input type="radio" name="order" value="new" checked>최신순↓</c:when>
							<c:otherwise>
								<input type="radio" name="order" value="new">최신순↓</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${order eq 'working' }">
								<input type="radio" name="order" value="working" checked>작업수↓</c:when>
							<c:otherwise>
								<input type="radio" name="order" value="working">작업수↓</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${order eq 'cate' }">
								<input type="radio" name="order" value="cate" checked>카테고리↓</c:when>
							<c:otherwise>
								<input type="radio" name="order" value="cate">카테고리↓</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${order eq 'id' }">
								<input type="radio" name="order" value="id" checked>id↓</c:when>
							<c:otherwise>
								<input type="radio" name="order" value="id">id↓</c:otherwise>
						</c:choose>

					</div>
				</div>
				<c:if test="${not empty keyword1}">
					<div>
						<c:if test="${keyword1 eq 'title'}">
							<b>서비스명 : </b>
						</c:if>
						<c:if test="${keyword1 eq 'free'}">
							<b>프리랜서 : </b>
						</c:if>

						[${keyword2 }] 의<b> 검색 결과</b>
					</div>
				</c:if>
			</div>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<img src="/img/icon/exclamation.png">
			<br>
			<h1>검색 결과가 없습니다!</h1>
	</div>
	</c:if>

	</div>
	<script>
	
	$(function() {
		var page = "${page}";
		if (page == 'waiting') {
			$(".tab").eq(0).addClass("select");
		}
		if (page == 'approved') {
			$(".tab").eq(1).addClass("select");
		}
		if (page == 'deleted') {
			$(".tab").eq(2).addClass("select");
		}

	
	});

	$(".tab").eq(0).click(function() {
		locationFunc('waiting', '', '','old');
	});

	$(".tab").eq(1).click(function() {
		locationFunc('approved', '', '','new');
	});

	$(".tab").eq(2).click(function() {
		locationFunc('deleted', '', '','new');
	});

	function locationFunc(status, keyword1,keyword2,order) {
		location.href = "/manageService.do?reqPage=1&status=" + status
				+ "&keyword1=" + keyword1 + "&keyword2=" + keyword2+ "&order=" + order;
	}

	function search(page) {
		
		var keyword1 = $("option:selected").val();
		console.log(keyword1);
		var keyword2 = $(".keyword2").val();
		console.log(keyword2);
		locationFunc(page, keyword1,keyword2, ''); 
	}

	$("input[type=radio]").change(function() {
		var status = "${page}";
		var keyword1 = "${keyword1}";
		var keyword2 = "${keyword2}";
		var order = $("input[type=radio]:checked").val()
		locationFunc(status, keyword1,keyword2, order);

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
					mId:mId,
					sNo : sNo
				},
				success : function(data) {
					if (data == -1) {
						alert("5개 이상 등록할수 없습니다!");
					} else{
						console.log("승인 성공!");
						var content ="서비스 <b> ["+sTitle+"]</b>가 <b>등록</b>되었습니다.";
						sendMsg(mNo,mId,content);
						location.reload(); }
									
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