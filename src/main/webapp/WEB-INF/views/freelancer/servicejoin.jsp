<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous">
</script>
<style>
	.select_img img{
				width:50px;
				height:50px;	
	}
	h3.won {
		margin: 0;
		padding: 0;
	}
	
	div.inner {
		width: 1200px;
		margin: 0 auto;
	}
	
	div.top {
		width: 80%;
		margin: 0 auto;
		border-radius:10px;
		border: 2px dashed #e6e6e6;
		background-color: #f5faff;
	}
	
	div.toptext {
		margin-left: 40px;
	}
	
	div.bottom {
		width: 80%;
		height: 1100px;
		margin: 0 auto;
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
	.toptext>h3 {
		color:rgb(49, 76, 131);
	}
	.toptext>h3:last-child {
		color:#b52700;
		font-size: 14px;	
		font-weight: bold;
	}
	.toptext>h2{
		color:rgb(49, 76, 131);
		font-weight: bold;
	}
</style>
<script>
$(function() {
	$("select[name=mainCategory]").change(function(){
		var temp = $("select[name=subCategory]");
		var a = $(this).val();
		console.log(temp);
		console.log(a);
		temp.children().remove();

		if(a == '10'){
			temp.append('<option value="11">로고/브랜딩</option>');
			temp.append('<option value="12">인쇄ㆍ홍보물ㆍ배너</option>');
			temp.append('<option value="13">캘리그라피ㆍ폰트</option>');
			temp.append('<option value="14">일러스트ㆍ캐리커쳐</option>');
			temp.append('<option value="15">간판ㆍ시공</option>');
			temp.append('<option value="16">이벤트 ㆍ상세페이지</option>');
			temp.append('<option value="17">의류</option>');
			temp.append('<option value="18">웹툰ㆍ캐릭터ㆍ이모티콘</option>');
		}
		if(a == '20'){
			temp.append('<option value="21">워드프레스</option>');
			temp.append('<option value="22">웹사이트개발</option>');
			temp.append('<option value="23">프로그램 개발</option>');
			temp.append('<option value="24">데이터베이스</option>');
			temp.append('<option value="25">게임개발</option>');
			temp.append('<option value="26">보안</option>');
			temp.append('<option value="27">파일변환</option>');
		}
		if(a == '30'){
			temp.append('<option value="31">영상촬영ㆍ편집</option>');
			temp.append('<option value="32">유튜브 제작</option>');
			temp.append('<option value="33">애니메이션제작</option>');
			temp.append('<option value="34">더빙ㆍ녹음</option>');
			temp.append('<option value="35">음악ㆍ사운드</option>');
		}if(a =='40'){
			temp.append('<option value="41">외국어(영어)</option>');
			temp.append('<option value="42">외국어(기타언어)</option>');
			temp.append('<option value="43">취미 라이프</option>');
			temp.append('<option value="44">입시/학업</option>');
			temp.append('<option value="45">pptㆍ프레젠테이션</option>');
			temp.append('<option value="46">마케팅</option>');
			temp.append('<option value="47">사진</option>');
		}
		if(a == '50'){
			temp.append('<option value="51">보도자료</option>');
			temp.append('<option value="52">광고카피라이팅</option>');
			temp.append('<option value="53">마케팅 글작성</option>');
			temp.append('<option value="54">책ㆍ시나리오 작성</option>');
			temp.append('<option value="55">논문</option>');
			temp.append('<option value="56">교정ㆍ교육 첨삭</option>');
			temp.append('<option value="57">기타</option>');
		}
		if(a == '60'){
			temp.append('<option value="61">사업계획서 투자제안서</option>');
			temp.append('<option value="62">인사ㆍ노무</option>');
			temp.append('<option value="63">창업컨설팅</option>');
			temp.append('<option value="64">법률법무</option>');
			temp.append('<option value="65">업무지원ㆍcs</option>');
		}
		if(a == '70'){
			temp.append('<option value="71">인쇄</option>');
			temp.append('<option value="72">간판</option>');
			temp.append('<option value="73">기념품제작</option>');
			temp.append('<option value="74">모형제작</option>');
			temp.append('<option value="75">시스템제작</option>');
			temp.append('<option value="63">창업컨설팅</option>');
			temp.append('<option value="76">인테리어시공</option>');
			temp.append('<option value="77">패키지제작</option>');
		}
	});	
});
</script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" /><br>
	<br>
	<br>
	<br>
	<div class="inner">
		<div class="top">
			<div class="toptext">
				<h2>서비스 등록하기 (안전거래)</h2>
				<p>등록한 서비스는 MYPAGE에서 수정 할 수 있습니다.</p>
				<br> <br>
				<h3>서비스 등록시 승인이 거부되는 서비스 안내</h3>
				<p style="font-size: 15px;">1. 등록한 서비스의 내용이 부실한 경우(내용이 성의 없거나, 목록 이미지가 적절치 않은 경우)</p>
				<p style="font-size: 15px;">2. 등록한 서비스의 내용에 연락처 등 직거래를 유발할 수 있는 문구가 들어갈 경우(홈페이지, 이메일, 전화번호 등등)</p>
				<p style="font-size: 15px;">3. 등록한 서비스의 가격이 정확하게 표시되지 않거나, 적절한 가격으로 입력이 되지 않는 경우</p>
				<p style="font-size: 15px;">4. 등록한 서비스의 전문성이 다른 재능에 비해 확연히 떨어진다고 판단될 경우</p>
				<p style="font-size: 15px;">5. 등록한 서비스의 내용이 재능넷의 성격에 부합되지 않는 성격의 재능일 경우</p>
				<br> <br>
				<h3>※서비스 등록 후부터 성실한 판매에 임할 것을 동의하는 것으로 간주하며, 불성실한 판매 행위가 발견될 시에 판매정지 처리 될 수있습니다.</h3>
			</div>
		</div>
		<br> <br> <br> <br>
		<div class="bottom">
			<div class="inner2">
				<br> <br> <br>
				<form action="/serviceJoin.do" method="post" autocomplete="off" enctype="multipart/form-data">
					<table border="1" style="border-collapse: collapse">
						<tr>
						<td style="width: 250px; height: 80px;">*작성자</td>
						<td style="width: 800px; "><input type="text" name="mId" value="${loginMember.MId}" style="width: 90%; height: 50px;" readonly></td>
						</tr>
						<tr>
							<td style="width: 250px; height: 80px;">*제목</td>
							<td style="width: 800px;"><input type="text" name="sTitle" placeholder="내용을 입력하세요" style="width: 90%; height: 50px;"></td>
						</tr>
						<tr>
							<td style="width: 250px; height: 80px;">*카테고리 선택</td>
							<td style="width: 400px;">
							<select name="mainCategory" 
								style="width: 200px; float: left; margin-left: 30px; height: 30px;">	
									<option value="">선택하세요</option>
									<option value="10">디자인</option>
									<option value="20">IT/프로그래밍</option>
									<option value="30">영상/사진/음향</option>
									<option value="40">교육</option>
									<option value="50">문서/글쓰기</option>
									<option value="60">비즈니스컨설팅</option>
									<option value="70">주문제작</option>
							</select>
								<p style="margin-top: 9px;">※메인카테고리</p> ※서브카테고리
								 <select name="subCategory" style="width: 200px; float: left; margin-left: 30px; height: 30px;">
									<option value="">선택하세요</option>
								 	<option value="11">로고/브랜딩</option>
									<option value="12">인쇄ㆍ홍보물ㆍ배너</option>
									<option value="13">캘리그라피ㆍ폰트</option>
									<option value="14">일러스트ㆍ캐리커쳐</option>
									<option value="15">간판ㆍ시공</option>
									<option value="16">이벤트 ㆍ상세페이지</option>
									<option value="17">의류</option>
									<option value="18">웹툰ㆍ캐릭터ㆍ이모티콘</option>
									
									<option value="21">워드프레스</option>
									<option value="22">웹사이트개발</option>
									<option value="23">프로그램 개발</option>
									<option value="24">데이터베이스</option>
									<option value="25">게임개발</option>
									<option value="26">보안</option>
									<option value="27">파일변환</option>

									<option value="31">영상촬영ㆍ편집</option>
									<option value="32">유튜브 제작</option>
									<option value="33">애니메이션제작</option>
									<option value="34">더빙ㆍ녹음</option>
									<option value="35">음악ㆍ사운드</option>

									<option value="41">외국어(영어)</option>
									<option value="42">외국어(기타언어)</option>
									<option value="43">취미 라이프</option>
									<option value="44">입시/학업</option>
									<option value="45">pptㆍ프레젠테이션</option>
									<option value="46">마케팅</option>
									<option value="47">사진</option>

									<option value="51">보도자료</option>
									<option value="52">광고카피라이팅</option>
									<option value="53">마케팅 글작성</option>
									<option value="54">책ㆍ시나리오 작성</option>
									<option value="55">논문</option>
									<option value="56">교정ㆍ교육 첨삭</option>
									<option value="57">기타</option>

									<option value="61">사업계획서 투자제안서</option>
									<option value="62">인사ㆍ노무</option>
									<option value="63">창업컨설팅</option>
									<option value="64">법률법무</option>
									<option value="65">업무지원ㆍcs</option>

									<option value="71">인쇄</option>
									<option value="72">간판</option>
									<option value="73">기념품제작</option>
									<option value="74">모형제작</option>
									<option value="75">시스템제작</option>
									<option value="76">인테리어시공</option>
									<option value="77">패키지제작</option>
							</select></td>
						</tr>
						<tr>
							<td style="width: 250px; height: 80px;">*서비스 지역</td>
							<td style="width: 400px;">
							<select name="sArea" style="width: 200px; float: left; margin-left: 30px; height: 30px;">
									<option value='서울'>서울</option>
									<option value='부산'>부산</option>
									<option value='대구'>대구</option>
									<option value='인천'>인천</option>
									<option value='광주'>광주</option>
									<option value='대전'>대전</option>
									<option value='울산'>울산</option>
									<option value='강원'>강원</option>
									<option value='경기'>경기</option>
									<option value='경남'>경남</option>
									<option value='경북'>경북</option>
									<option value='전남'>전남</option>
									<option value='전북'>전북</option>
									<option value='제주'>제주</option>
									<option value='충남'>충남</option>
									<option value='충북'>충북</option>
							</select>
							</td>
						</tr>
						<tr>
							<td style="width: 250px; height: 80px;">*서비스 가격</td>
							<td style="width: 400px;"><input type="text" name="sPrice"
								style="float: left; margin-left: 30px; height: 30px; width: 130px;">
								<h3 class="won" style="float: left; margin-left: 10px; margin-top: 7px;">원</h3>
								<span style="float: left; color: gray; font-size: 13px; margin-left: 10px; margin-top: 10px;">※5,000원 이상 입력</span>
							</td>
						</tr>
						<tr>
							<td style="width: 250px; height: 80px;">*휴대폰 번호</td>
							<td style="width: 400px;"><input type="text" name="mPhone"
								style="width: 200px; height: 23px; float: left; margin-left: 30px;">
							</td>
						</tr>
						<tr>
							<td style="width: 250px; height: 80px;">*작업 기간</td>
							<td style="width: 400px;"><input type="text"
								name="workingDate" style="float: left; margin-left: 30px; height: 30px; width: 60px;">
								<h3 class="won" style="float: left; margin-left: 10px; margin-top: 7px;">일</h3>
							</td>
						</tr>
						<tr>
							<td style="width: 250px; height: 80px;">*이미지등록</td>
							<td style="width: 400px;">
								<p style="font-size: 8px; color: gray; margin-left: -259px; margin-top: 10px;">이미지는 jpg,gif,png 만 등록할수 있습니다.(이미지 용량 2MG이하)</p> 
								<input type="file" multiple name="ssImg" id="gdsImg" value="파일첨부" style="float: left; margin-left: 30px; color: gray; font-size: 15px;">
								<div class="select_img"><img src="" alt="썸네일사진" style="width:50px; height:50px;" /></div>
								<p style="font-size: 11px; margin-right: 150px; color: gray;">(여러파일 첨부가능합니다.)</p>
								<p style="font-size: 8px; color: red; font-weight: bolder; margin-left: 10px; margin-right: 3px;">※이미지를 성격에 맞게 등록하셔야, 조회수와 함께 구매율이 많이 올름</p>
							</td>
						</tr>
						<tr>
							<td style="width: 250px; height: 80px;">*서비스내용</td>
							<td><textarea name="sContent" cols="82" rows="7"></textarea>
						</td>
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
	<jsp:include page="/WEB-INF/views/common/footer.jsp" /> 
	
	
	<script>
	 $("#gdsImg").change(function(){
			   if(this.files && this.files[0]) {
			    var reader = new FileReader;
			    reader.onload = function(data) {
			     $(".select_img img").attr("src", data.target.result).width(150).height(150);        
			    }
			    reader.readAsDataURL(this.files[0]);
			   }
			  });
	</script>
	

</body>
</html>