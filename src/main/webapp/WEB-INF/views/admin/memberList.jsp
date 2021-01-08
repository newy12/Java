<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시(관리자) :: 회원관리</title>
<style>
        @font-face {
            font-family: 'Arita-dotum-Medium';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Arita-dotum-Medium.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }

        * {
            font-family: 'Arita-dotum-Medium';
        }
        .header {
            margin: 0 auto;
            width: 1200px;
            height: 100px;
        }

        .header>img {
            width: 200px;
            float: left;
        }

        .name {
            float: right;
            margin-right: 50px;
        }

        .sideNavi {
            height: 800px;
            width: 200px;
            background-color: #314C83;
            border-top-left-radius: 15px;
            padding-top: 20px;
            float:left;
        }

        .sideNavi>ul {
            margin: 0;
            padding: 0;
            list-style-type: none;
        }
        .sideNavi li{
            margin: 0;
        }
        .sideNavi>ul>li>a {
            margin-left: 40px;
            padding: 10px;
            display: block;
            height: 30px;
            line-height:30px;
            text-decoration: none;
            color: white;
        }
        .navi-link:hover{
            background-color: #304582;
        }
        
        .adminContent{
        float:left;}
        
    </style>


</head>
<body>
	<div class="header">
        <img src="/img/logo/logo_white.png" onclick="location='/'">
        <div class="name">관리자</div>
    </div>
    <div class="sideNavi">
        <ul>
            <li class="navi-link"><a href="/manageMember.do">MEMBER</a></li>
            <li class="navi-link"><a href="/manageService.do">SERVICE</a></li>
            <li class="navi-link"><a href="">NOTICE</a></li>
            <li class="navi-link"><a href="">FAQ</a></li>
        </ul>
    </div>
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
		</tr>
		<c:forEach items="${memberList }" var="m">
		<tr>
			<td>${m.enrollDate }</td>
			<td>${m.MNo }</td>
			<td>${m.MId} </td>
			<td>${m.MName }</td>
			<td>
			<c:if test="${m.MGrade==1 }">일반 회원</c:if>
			<c:if test="${m.MGrade==2 }">프리랜서</c:if> 
			</td>
			<td>
			
			<c:forEach items="${useHistory }" var="h">
				<c:if test="${h.key eq m.MNo }">
					${h.value }
				</c:if>
			</c:forEach>
			</td><!-- map값 불러오는 방법?? -->
			<td> ${m.warningCount } </td>
		</tr>
		</c:forEach>
	</table>
	</div>
	

</body>
</html>