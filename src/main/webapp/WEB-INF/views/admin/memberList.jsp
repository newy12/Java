<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시(관리자) :: 회원관리</title>
<style>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/admin/adminMainpage.jsp" />

	<div class="serviceNavi">
		<ul>
			<li class="tab">전체회원</li>
			<li class="tab">프리랜서</li>
			<li class="tab">블랙리스트</li>
		</ul>
	</div>

	<div class="adminContent">
		<div>
			<h1>전체회원</h1>
			<table class="all" border=1>
				<tr>
					<th>가입일</th>
					<th>회원번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>등급</th>
					<th>서비스 이용횟수</th>
					<th>신고횟수</th>
					<th colspan="2">최근 메세지</th>

				</tr>
				<c:forEach items="${memberList }" var="m">
					<!-- 조건 추가 -->
					<c:if test="${m.MId != 'admin' }">
						<tr>
							<td>${m.enrollDate }</td>
							<td>${m.MNo }</td>
							<td><a href="/adminMessage.do?mNo=${m.MNo }"
								onClick="window.open(this.href, '', 'width=800, height=400, left=1000, scrollbars=no,location=no, resizable=no'); return false;">${m.MId}</a>
							</td>
							<td>${m.MName }</td>
							<td><c:if test="${m.MGrade==1 }">일반 회원</c:if> <c:if
									test="${m.MGrade==2 }">
									프리랜서
								</c:if></td>
							<td><c:forEach items="${useHistory }" var="h">
									<!-- map값 불러오는 방법?? -->
									<c:if test="${h.key eq m.MNo }">
									<c:if test="${h.value ne 0}">
										<a href="/tradeHistory.do?sNo=-1&mNo=${m.MNo }"
											onClick="window.open(this.href, '', 'width=800, height=400, left=1000, scrollbars=no,location=no, resizable=no'); return false;">${h.value }</a>
									</c:if>
									<c:if test="${h.value eq 0}">-</c:if>
									</c:if>
								</c:forEach></td>
							<td>${m.warningCount }</td>
							<td>
							<c:forEach items="${adminMsg }" var="msg">
									<!-- map값 불러오는 방법?? -->
									<c:if test="${msg.key eq m.MId }">
									<a href="">${msg.value[0].CContent}	</a>
									</c:if>
								</c:forEach>
							
						</td>
							<td>
								<c:forEach items="${adminMsg }" var="msg">
									<!-- map값 불러오는 방법?? -->
									<c:if test="${msg.key eq m.MId }">
									<c:if test="${msg.value[0].readStatus eq 0}">읽음</c:if>
									<c:if test="${msg.value[0].readStatus eq 1}">안읽음</c:if>
									</c:if>
								</c:forEach>
							
							</td>
						</tr>
					</c:if>
					<!-- 조건 추가 -->
				</c:forEach>
			</table>
		</div>

		<div>
			<h1>프리랜서</h1>
			<table class="free" border=1>
				<tr>
					<th>가입일</th>
					<th>회원번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>등급</th>
					<th>서비스 이용횟수</th>
					<th>신고횟수</th>
				</tr>
				<c:forEach items="${memberList }" var="m">
					<c:if test="${m.MGrade eq 2 }">
						<c:if test="${m.MId != 'admin' }">
							<tr>
								<td>${m.enrollDate }</td>
								<td>${m.MNo }</td>
								<td><a href="/adminMessage.do?mNo=${m.MNo }"
									onClick="window.open(this.href, '', 'width=800, height=400, left=1000, scrollbars=no,location=no, resizable=no'); return false;">${m.MId}</a>
								</td>
								<td>${m.MName }</td>
								<td><c:if test="${m.MGrade==1 }">일반 회원</c:if> <c:if
										test="${m.MGrade==2 }">
										<a href="#">프리랜서</a>
									</c:if></td>
									<td><c:forEach items="${useHistory }" var="h">
									<!-- map값 불러오는 방법?? -->
									<c:if test="${h.key eq m.MNo }">
									<c:if test="${h.value ne 0}">
										<a href="/tradeHistory.do?sNo=-1&mNo=${m.MNo }"
											onClick="window.open(this.href, '', 'width=800, height=400, left=1000, scrollbars=no,location=no, resizable=no'); return false;">${h.value }</a>
									</c:if>
									<c:if test="${h.value eq 0}">-</c:if>
									</c:if>
								</c:forEach></td>
								<td>${m.warningCount }</td>
							</tr>
						</c:if>
					</c:if>
				</c:forEach>
			</table>
		</div>

		<div>
			<h1>블랙리스트</h1>
			<table class="black" border=1>
				<tr>
					<th>가입일</th>
					<th>회원번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>등급</th>
					<th>서비스 이용횟수</th>
					<th>신고횟수</th>
					<th>처리</th>
				</tr>
				<c:forEach items="${memberList }" var="m">
					<c:if test="${m.warningCount gt 3 }">
						<c:if test="${m.MId != 'admin' }">
							<tr>
								<td>${m.enrollDate }</td>
								<td>${m.MNo }</td>
								<td><a href="/adminMessage.do?mNo=${m.MNo }"
									onClick="window.open(this.href, '', 'width=800, height=400, left=1000, scrollbars=no,location=no, resizable=no'); return false;">${m.MId}</a>
								</td>
								<td>${m.MName }</td>
								<td><c:if test="${m.MGrade==1 }">일반 회원</c:if> <c:if
										test="${m.MGrade==2 }">
										<a href="#">프리랜서</a>
									</c:if></td>
									<td><c:forEach items="${useHistory }" var="h">
									<!-- map값 불러오는 방법?? -->
									<c:if test="${h.key eq m.MNo }">
									<c:if test="${h.value ne 0}">
										<a href="/tradeHistory.do?sNo=-1&mNo=${m.MNo }"
											onClick="window.open(this.href, '', 'width=800, height=400, left=1000, scrollbars=no,location=no, resizable=no'); return false;">${h.value }</a>
									</c:if>
									<c:if test="${h.value eq 0}">-</c:if>
									</c:if>
								</c:forEach></td>
								<td>${m.warningCount }</td>
								<td><button>탈퇴</button></td>
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
	</script>

</body>
</html>