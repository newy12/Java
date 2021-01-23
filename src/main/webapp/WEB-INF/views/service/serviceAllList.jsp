<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>19시 :: [${param.keyword}] 검색결과</title>
	<!-- favicon -->
	<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
	<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
	<style>
		@font-face {
			font-family: 'Arita-dotum-Medium';
			src:
				url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Arita-dotum-Medium.woff') format('woff');
			font-weight: normal;
			font-style: normal;
		}

		.search-section {
			font-family: 'Arita-dotum-Medium';
			width: 1200px;
			margin: 0 auto;
		}

		.search-container {
			width: 100%;
		}

		.search-description {
			margin-top: 20px;
			width: 100%;
			height: 40px;
			font-size: 22px;

		}

		.search-description #keyword {
			font-weight: bold;
		}

		.search-description #count {
			font-weight: bolder;
			font-size: 24px;
		}

		.search-list {
			width: 100%;
			/* border: 1px solid; */
			/* box-sizing: border-box; */
			overflow: hidden;
		}

		.search-list>a {
			display: inline-block;
			text-decoration: none;
		}

		.search-list .list-item {
			width: 220px;
			float: left;
			margin: 10px 0;
			margin-right: 15px;
			margin-bottom: 20px;
		}

		.list-item>.item-img {
			width: 100%;
			border-radius: 4px;
			border: 1px solid lightgray;
		}

		.list-item>.item-img>img {
			width: 100%;
			height: 160px;
			object-fit: cover;
			border-radius: 4px;
		}

		.list-item>.item-freeid {
			margin: 10px 0;
			color: gray;
		}

		.list-item>.item-title {
			margin: 10px 0;
			color: #282828;
		}

		.list-item>.item-price {
			color: #282828;
			font-size: large;
			font-weight: bold;
			text-align: right;
		}

		.list-item>.item-rate {
			color: gray;
			text-align: right;
			font-size: small;
		}

		/* 페이징 관련 */
		.paging {
			text-align: center;
			margin: 30px 0;
		}

		.page-num {
			display: inline-block;
			width: 25px;
			height: 25px;
			line-height: 25px;
			text-align: center;
			border-radius: 4px;
			color: #282828;
			font-weight: bold;
			margin-bottom: 10px;
		}
		.page-next:hover,
        .page-prev:hover {
            text-decoration: none;
            color: #282828;
            background-color: #ff8f3f85;
        }

        .page-next,
        .page-prev {
            display: inline-block;
            width: 50px;
            height: 25px;
            line-height: 25px;
            text-align: center;
            border-radius: 4px;
            color: #282828;
            font-weight: bold;
            margin-bottom: 10px;
        }
		.page-num:not(.page-selected):hover {
			text-decoration: none;
			color: #282828;
			background-color: #ff8f3f85;
		}

		.page-selected {
			background-color: #FF8F3F;
			color: white;
		}

		.page-selected:hover {
			color: white;
			text-decoration: none;
		}

		.star {
			color: rgb(241, 196, 15);
			font-size: 14pt;
		}
	</style>
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<section class="search-section">
		<div class="search-container">
			<div class="search-description">
				<span id="keyword">'${param.keyword}'</span>에 대한 검색결과 <span id="count">${maxListCount}개</span>
			</div>
			<div class="search-list">
				<c:if test="${not empty list}">
					<c:forEach var="item" items="${list}">
						<a href="/serviceView2.do?sNo=${item.SNo}&reqPage=1">
							<div class="list-item">
								<div class="item-img">
									<img src="upload/service/${item.SImg}" alt="">
								</div>
								<div class="item-freeid">
									${item.MId}
								</div>
								<div class="item-title">
									${item.STitle}
								</div>
								<div class="item-price">
									${item.SPriceTxt}원
								</div>
								<div class="item-rate">
									${item.SRate}점
									<c:choose>
										<c:when test="${item.SRate == 5}">
											<span class="star"> ★★★★★ </span>
										</c:when>

										<c:when test="${item.SRate gt 3.9}">
											<span class="star"> ☆★★★★ </span>
										</c:when>
										<c:when test="${item.SRate gt 2.9}">
											<span class="star"> ☆☆★★★ </span>
										</c:when>
										<c:when test="${item.SRate gt 1.9}">
											<span class="star"> ☆☆☆★★ </span>
										</c:when>
										<c:when test="${item.SRate gt 0.9}">
											<span class="star"> ☆☆☆☆★ </span>
										</c:when>
										<c:when test="${item.SRate == 0}">
											<span class="star"> ☆☆☆☆☆ </span>
										</c:when>
									</c:choose>
								</div>
							</div>
						</a>
					</c:forEach>
				</c:if>
			</div>
			<div class="paging">
				<c:if test="${begin != 1}">
					<a class="page-prev"
						href="?page=${begin-1}${not empty param.keyword ? '&keyword='+=param.keyword : ''}">이전</a>
				</c:if>
				<c:forEach var="i" begin="${not empty param.page ?  begin : 1}" end="${end}" step="1">
					<a class="page-num ${param.page==i || (empty param.page && i == 1) ? 'page-selected' : ''}"
						href="?page=${i}${not empty param.keyword ? '&keyword='+=param.keyword : ''}">${i}</a>
				</c:forEach>
				<c:if test="${end < maxPageCount}">
					<a class="page-next"
						href="?page=${end+1}${not empty param.keyword ? '&keyword='+=param.keyword : ''}">다음</a>
				</c:if>
			</div>
		</div>
	</section>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

</html>