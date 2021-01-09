<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시(관리자) :: 회원관리</title>

</head>
<body>
	<jsp:include page="/WEB-INF/views/admin/adminMainpage.jsp" />
	
    <div class="adminContent">
	<h1>회원관리</h1>
	<a href="#">블랙리스트 회원 보기 > </a>

	<table border=1>
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
		<tr>
			<td>${m.enrollDate }</td>
			<td>${m.MNo }</td>
			<td>${m.MId} 
			</td>
			<td>${m.MName }</td>
			<td>
			<c:if test="${m.MGrade==1 }">일반 회원</c:if>
			<c:if test="${m.MGrade==2 }"><a href="#">프리랜서</a></c:if> 
			</td>
			<td>
			
			<c:forEach items="${useHistory }" var="h">
				<c:if test="${h.key eq m.MNo }">
					<a href="/userHistory.do?mNo=${m.MNo }" onClick="window.open(this.href, '', 'width=800, height=400, left=1000, scrollbars=no,location=no, resizable=no'); return false;">${h.value }</a>
				</c:if>
			</c:forEach>
			</td><!-- map값 불러오는 방법?? -->
			<td> ${m.warningCount } </td>
			<td><button>탈퇴</button></td>
		</tr>
		</c:forEach>
	</table>
	</div>
	

</body>
</html>