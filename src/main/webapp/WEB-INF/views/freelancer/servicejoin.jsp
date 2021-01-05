<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" /><br>
	<br>
	<hr>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="inner">
		<div class="top">
			<div class="toptext">
				<h2>서비스 등록하기 (안전거래)</h2>
				<p>등록한 서비스는 MYPAGE에서 수정 할 수 있습니다.</p>
				<br> <br> <br> <br> <br> <br>
				<h3>서비스 등록시 승인이 거부되는 서비스 안내</h3>
				<p style="font-size: 15px;">1. 등록한 서비스의 내용이 부실한 경우(내용이 성의 없거나,
					목록 이미지가 적절치 않은 경우)</p>
				<p style="font-size: 15px;">2. 등록한 서비스의 내용에 연락처 등 직거래를 유발할 수 있는
					문구가 들어갈 경우(홈페이지, 이메일, 전화번호 등등)</p>
				<p style="font-size: 15px;">3. 등록한 서비스의 가격이 정확하게 표시되지 않거나, 적절한
					가격으로 입력이 되지 않는 경우</p>
				<p style="font-size: 15px;">4. 등록한 서비스의 전문성이 다른 재능에 비해 확연히 떨어진다고
					판단될 경우</p>
				<p style="font-size: 15px;">5. 등록한 서비스의 내용이 재능넷의 성격에 부합되지 않는 성격의
					재능일 경우</p>
				<br> <br> <br>
				<h3 style="color: red;">서비스 등록 후부터 성실한 판매에 임할 것을 동의하는 것으로 간주하며,
					불성실한 판매 행위가 발견될 시에 판매정지 처리 될 수있습니다.</h3>
			</div>
		</div>
		<br> <br> <br> <br>
		<div class="bottom">
			<div class="inner2">
				<br> <br> <br>
				<form action="/serviceJoin.do">
					<table border="1" style="border-collapse: collapse">
						<tr>
							<td style="width: 250px; height: 80px;">*제목</td>
							<td style="width: 800px;"><input type="text" name="sTitle"
								placeholder="내용을 입력하세요" style="width: 90%; height: 50px;">
							</td>
						</tr>
						<tr>
							<td style="width: 250px; height: 80px;">*카테고리 선택</td>

							<td style="width: 400px;"><select name="mainCategory"
								style="width: 200px; float: left; margin-left: 30px; height: 30px;">
									<option value="10">디자인</option>
									<option value="20">IT/프로그래밍</option>
									<option value="30">영상/사진/음향</option>
									<option value="40">교육</option>
									<option value="50">문서/글쓰기</option>
									<option value="60">비즈니스컨설팅</option>
									<option value="70">주문제작</option>
							</select>
								<p style="margin-top: 9px;">※메인카테고리</p> ※서브카테고리 <!-- <select name="subitems" style="width:200px; float: left; margin-left: 30px; height: 30px;">
                            <option value="one">로고/브랜딩</option>
                            <option value="two">자바스크립트</option>
                            <option value="three">CSS</option>
                        </select> --></td>
						</tr>
						<tr>
							<!-- <td style="width: 250px; height: 80px;">*서비스 지역</td>
							<td style="width: 400px;"><select name="items"
								style="width: 200px; float: left; margin-left: 30px; height: 30px;">
									<option value='1'>서울</option>
									<option value='2'>부산</option>
									<option value='3'>대구</option>
									<option value='4'>인천</option>
									<option value='5'>광주</option>
									<option value='6'>대전</option>
									<option value='7'>울산</option>
									<option value='8'>강원</option>
									<option value='9'>경기</option>
									<option value='10'>경남</option>
									<option value='11'>경북</option>
									<option value='12'>전남</option>
									<option value='13'>전북</option>
									<option value='14'>제주</option>
									<option value='15'>충남</option>
									<option value='16'>충북</option>

							</select></td> -->
						</tr>
						<tr>
							<td style="width: 250px; height: 80px;">*서비스 가격</td>
							<td style="width: 400px;"><input type="text" name="sPrice"
								style="float: left; margin-left: 30px; height: 30px; width: 130px;">
								<h3 class="won"
									style="float: left; margin-left: 10px; margin-top: 7px;">원</h3>
								<span
								style="float: left; color: gray; font-size: 13px; margin-left: 10px; margin-top: 10px;">※5,000원
									이상 입력</span></td>
						</tr>
						<tr>
							<td style="width: 250px; height: 80px;">*휴대폰 번호</td>
							<td style="width: 400px;"><input type="text" name="mPhone"
								style="width: 200px; height: 23px; float: left; margin-left: 30px;">
							</td>
						</tr>
						<tr>
							<td style="width: 250px; height: 80px;">*작업 기간</td>
							<td style="width: 400px;"><input type="text" name="workingDate"
								style="float: left; margin-left: 30px; height: 30px; width: 60px;">
								<h3 class="won"
									style="float: left; margin-left: 10px; margin-top: 7px;">일</h3>
							</td>
						</tr>
						<tr>
							<td style="width: 250px; height: 80px;">*이미지등록</td>
							<td style="width: 400px;">
								<p
									style="font-size: 8px; color: gray; margin-left: -259px; margin-top: 10px;">이미지는
									jpg,gif,png 만 등록할수 있습니다.(이미지 용량 2MG이하)</p> <input type="file"
								multiple-multiple name="sImg" value="파일첨부"
								style="float: left; margin-left: 30px; color: gray; font-size: 15px;">
								<p style="font-size: 11px; margin-right: 150px; color: gray;">(여러파일
									첨부가능합니다.)</p>
								<p
									style="font-size: 8px; color: red; font-weight: bolder; margin-left: 10px; margin-right: 3px;">※이미지를
									성격에 맞게 등록하셔야, 조회수와 함께 구매율이 많이 올름</p>
							</td>
						</tr>
						<tr>
							<td style="width: 250px; height: 80px;">*서비스내용</td>
							<td><textarea name="sContent" cols="82" rows="7"></textarea></td>


						</tr>
						

					</table>
			</div>
		</div>
		<div class="bottom2">
			<input type=submit value="등록 완료"
				style="width: 260px; height: 40px; margin-left: 450px; margin-top: 100px;">
		</div>
	</div>
	</form>

	<hr>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<style>
h3.won {
	margin: 0;
	padding: 0;
}

div.inner {
	width: 1200px;
	margin: 0 auto;
}

div.top {
	width: 100%;
	border: 1px solid gray;
}

div.toptext {
	margin-left: 40px;
}

div.bottom {
	width: 100%;
	height: 800px;
	border: 1px solid gray;
	text-align: center;
}

div.inner2 {
	width: 800px;
	margin: 0 auto;
}

div.bottom2 {
	width: 100%;
	height: 200px;
}
</style>
</body>
</html>