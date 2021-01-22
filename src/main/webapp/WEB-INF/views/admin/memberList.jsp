<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시(관리자) :: 회원관리</title>

<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180"
	href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="favicon_io/favicon-16x16.png">
<style>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/admin/adminMainpage.jsp" />

	<div class="serviceNavi">
		<ul>
			<li class="tab">전체회원</li>
			<li class="tab">프리랜서</li>
			<li class="tab">블랙컨슈머</li>
		</ul>
	</div>

	<div class="adminContent">
		<c:if test="${not empty memberList }">
			<div>
				<%-- <h1 style="margin-bottom: 0;">
					<c:if test="${page eq 'all' }">전체회원</c:if>
					<c:if test="${page eq 'free' }">프리랜서</c:if>
					<c:if test="${page eq 'black' }">블랙리스트</c:if>
				</h1> --%>
				<div
					style="float: right; margin-right: 10%; margin-top: 0; margin-bottom: 20px;">
					<input type="text" placeholder="id 또는 브랜드네임" class="keyword">
					<button class="btn search" onclick="search('${page}');">검색</button>
					<br>
					<div style="margin: 0 auto; margin-top: 5px; font-size: 15px;">
						<b>정렬 : </b>
						<c:choose>
							<c:when test="${order eq 'new' }">
								<input type="radio" name="order" value="new" checked>가입날짜↓</c:when>
							<c:otherwise>
								<input type="radio" name="order" value="new">가입날짜↓</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${order eq 'black' }">
								<input type="radio" name="order" value="black" checked>신고횟수↓</c:when>
							<c:otherwise>
								<input type="radio" name="order" value="black">신고횟수↓</c:otherwise>
						</c:choose>

					</div>
				</div>

				<c:if test="${not empty keyword}">
					<div>
						<b>아이디 또는 브랜드네임 : </b> [${keyword}] 의<b> 검색 결과</b>
					</div>
				</c:if>


				<table class="memberTable" style="width: 90%;">
					<tr>
						<th>가입일</th>
						<!-- <th>회원번호</th> -->
						<th>아이디</th>
						<th>이름</th>
						<c:if test="${page eq 'free'}">
							<th>브랜드네임</th>
						</c:if>
						<c:if test="${page ne 'free' }">
							<th>등급</th>
							<th>거래</th>
						</c:if>
						<th>신고</th>
						<th colspan="3">메세지</th>
						<c:if test="${page eq 'black' }">
							<th>처리</th>
						</c:if>
					</tr>
					<c:forEach items="${memberList }" var="m">
						<!-- 조건 추가 -->
						<c:if test="${m.MId != 'admin' }">
							<tr>
								<td>${m.enrollDate }</td>
								<%-- <td>${m.MNo }</td> --%>
								<td>${m.MId}</td>
								<td>${m.MName }</td>
								<c:if test="${page eq 'free'}">
									<td>${m.brandName }</td>
								</c:if>
								<c:if test="${page ne 'free' }">
									<td><c:if test="${m.MGrade==1 }">일반회원</c:if> <c:if
											test="${m.MGrade==2 }">
									프리랜서
								</c:if></td>
									<td><c:forEach items="${useHistory }" var="h">
											<!-- map값 불러오는 방법?? -->
											<c:if test="${h.key eq m.MNo }">
												<c:if test="${h.value ne 0}">
													<a href="/tradeHistory.do?sNo=-1&mNo=${m.MNo }"
														onClick="window.open(this.href, '', 'width=800, height=300, left=1000, scrollbars=no,location=no, resizable=no'); return false;">${h.value }</a>
												</c:if>
												<c:if test="${h.value eq 0}">-</c:if>
											</c:if>
										</c:forEach></td>
								</c:if>
								<td>${m.warningCount }</td>
								<td><a href="/adminMessage.do?mNo=${m.MNo }"
									onClick="window.open(this.href, '', 'width=300, height=350, left=1000, scrollbars=no,location=no, resizable=no'); return false;">
										<img src="/img/icon/chat.png">
								</a></td>
								<td class="msgtd" style="text-align: left;"><c:forEach
										items="${adminMsg }" var="msg">
										<!-- map값 불러오는 방법?? -->
										<c:if test="${msg.key eq m.MId }">
											${msg.value[0].CContent} 
										</c:if>
									</c:forEach></td>
								<td><c:forEach items="${adminMsg }" var="msg">
										<!-- map값 불러오는 방법?? -->
										<c:if test="${msg.key eq m.MId }">
											<c:if test="${msg.value[0].readStatus eq 0}">읽음</c:if>
											<c:if test="${msg.value[0].readStatus eq 1}">안읽음</c:if>
										</c:if>
									</c:forEach></td>
								<c:if test="${page eq 'black' }">
									<td><button class="btn deleteBtn"
											onclick="deleteMember('${m.MId}','${m.MNo}')">탈퇴</button></td>
								</c:if>
							</tr>
						</c:if>
						<!-- 조건 추가 -->
					</c:forEach>
				</table>

				<div style="width: 100%; margin: 10px; text-align: center">
					<ul class="pageNavi">${pageNavi }</ul>
				</div>
			</div>
		</c:if>
		<c:if test="${empty memberList }">
			<div style="text-align: center">
				<br> <br> <br> <br>
				<div
					style="float: right; margin-right: 10%; margin-top: 0; margin-bottom: 20px;">
					<input type="text" placeholder="id 또는 브랜드네임" class="keyword">
					<button class="btn search" onclick="search('${page}');">검색</button>
					<br>
					<div style="margin: 0 auto; margin-top: 5px; font-size: 15px;">
						<b>정렬 : </b>
						<c:choose>
							<c:when test="${order eq 'new' }">
								<input type="radio" name="order" value="new" checked>가입날짜↓</c:when>
							<c:otherwise>
								<input type="radio" name="order" value="new">가입날짜↓</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${order eq 'black' }">
								<input type="radio" name="order" value="black" checked>신고횟수↓</c:when>
							<c:otherwise>
								<input type="radio" name="order" value="black">신고횟수↓</c:otherwise>
						</c:choose>

					</div>
				</div>

				<c:if test="${not empty keyword}">
					<div>
						<b>아이디 또는 브랜드네임 : </b> [${keyword}] 의<b> 검색 결과</b>
					</div>
				</c:if>
				<br> <br> <br> <br> <br> <br> <br>
				<img src="/img/icon/exclamation.png"> <br>
				<h1>검색 결과가 없습니다!</h1>
			</div>
		</c:if>
	</div>
	<script>
		$(function() {
			var page = "${page}";
			if (page == 'all') {
				$(".tab").eq(0).addClass("select");
			}
			if (page == 'free') {
				$(".tab").eq(1).addClass("select");
			}
			if (page == 'black') {
				$(".tab").eq(2).addClass("select");
			}

		});

		$(".tab").eq(0).click(function() {
			locationFunc('all', '', 'new');
		});

		$(".tab").eq(1).click(function() {
			locationFunc('free', '', 'new');
		});

		$(".tab").eq(2).click(function() {
			locationFunc('black', '', 'new');
		});

		function locationFunc(grade, keyword, order) {
			location.href = "/manageMember.do?reqPage=1&grade=" + grade
					+ "&keyword=" + keyword + "&order=" + order;
		}

		function search(page) {
			var keyword = $(".keyword").val();
			locationFunc(page, keyword, '');
		}

		$("input[type=radio]").change(function() {
			var grade = "${page}";
			var keyword = "${keyword}";
			var order = $("input[type=radio]:checked").val()
			locationFunc(grade, keyword, order);

		});

		function deleteMember(mId,mNo) {
			check = confirm(mId + "님을 탈퇴시킵니다")
			if (check) {
				location.href="/deleteMember.do?mId="+mId+"&mNo="+mNo;
			}
		}
	</script>

</body>
</html>