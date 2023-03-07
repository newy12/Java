<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 서비스 등록</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"> 
</script>
<script type="text/javascript" src="/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
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
		border-top: 3px solid rgb(49, 76, 131);
		border-bottom: 3px solid rgb(49, 76, 131);
		text-align: center;
	}
	
	div.inner2 {
		width: 910px;
		margin: 0 auto;
		margin-top:5px;
	}
	
	div.bottom2 {
		width: 100%;
		height: 200px;
	}
	div.bottom2>input{
		color:white;
		font-size:17px;
		font-weight:bold;
		border-radius: 5px;
		background-color: rgb(49, 76, 131);
	}
	div.bottom2>input:hover{
		box-shadow: 1px 1px 5px 1px gray;
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
	.table-title>p{
		width:200px;
		height:50px;
		color:#403e3e;
		text-align:right;
		line-height:40px;
		font-weight: bold;
	}
	.input-noLine{
		outline: none;
		border:0;
	}
	.input-box{
		margin-top:10px;
        border-radius: 3px;
        border: 1px solid rgb(204, 204, 204);
	}
</style>

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
				<form action="/serviceJoin.do" method="post" autocomplete="off" enctype="multipart/form-data" id="joinForm">
					<table  style="border-collapse: collapse;" class="table">
						<tr>
							<td class="table-title" style="border:0;"><p>*서비스 제목</p></td>
							<td style="width: 800px;border:0;"><input type="text" name="sTitle" class="input-box" placeholder="제목을 입력하세요" maxlength="300" style="margin-left: 30px;width: 90%; height: 30px;float:left"  required="required"></td>
						</tr>
						<tr>
							<td class="table-title"><p>*카테고리 선택</p></td>
							<td style="width: 400px;">
								<input type="hidden" name="mId" value="${loginMember.MId}">
								<select class="input-box" name="mainCategory" style="width: 150px; float: left; margin-left: 30px; height: 30px;" id="mainCategory"  required="required">	
										<option value="0">메인 카테고리 선택</option>
										<option value="10">디자인</option>
										<option value="20">IT/프로그래밍</option>
										<option value="30">영상/사진/음향</option>
										<option value="40">교육</option>
										<option value="50">문서/글쓰기</option>
										<option value="60">비즈니스컨설팅</option>
										<option value="70">주문제작</option>
								</select>
								 <div style="float:left;line-height:50px;width:30px;font-align:center;margin-left: 30px;color: gray">>></div>
								 <select class="input-box" name="subCategory" style="width: 200px; float: left; margin-left: 30px; height: 30px;"  required="required">
									<option value="">서브 카테고리 선택</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="table-title"><p>*서비스 지역</p></td>
							<td style="width: 400px;">
							<select  class="input-box" name="sArea" style="width: 150px; float: left; margin-left: 30px; height: 30px;"  required="required">
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
							<td class="table-title"><p>*서비스 가격</p></td>
							<td style="width: 400px;">
								<input type="text"  class="input-box" name="sPrice" style="float: left; margin-left: 30px; height: 30px; width: 150px;" placeholder="5,000원 이상 입력" maxlength="10"  required="required">
								<h5 class="won" style="float: left; margin-left: 10px; margin-top: 20px;">원</h5>
								<span style="float: left; color: gray; font-size: 13px; margin-left: 10px; margin-top: 18px;"></span>
							</td>
						</tr>
						<tr>
							<td class="table-title"><p>*휴대폰 번호</p></td>
							<td style="width: 400px;">
								<input type="text" class="input-noLine" name="mPhone" style="width: 200px; height: 23px; float: left; margin-left: 30px; margin-top: 10px;" value="${loginMember.MPhone }" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td class="table-title"><p>*작업 기간</p></td>
							<td style="width: 400px;">
								<input type="text" class="input-box" name="workingDate" style="float: left; margin-left: 30px; height: 30px; width: 60px; max-height: 10000"  required="required">
								<h5 class="won" style="float: left; margin-left: 10px; margin-top: 20px;">일</h5>
								<span style="float: left; color: gray; font-size: 13px; margin-left: 10px; margin-top: 18px;"></span>
							</td>
						</tr>
						<tr>
							<td class="table-title"><p>*이미지등록</p></td>
							<td style="width: 400px;">
								<input type="file"  multiple name="ssImg" accept="image/*" id="gdsImg" value="파일첨부" style=" margin-left: 30px;margin-top:10px; color: gray; font-size: 15px;float:left" required="required">
								<p style="font-size: 8px; color: gray;  margin-top: 45px; text-align:left;margin-left: 30px;">이미지는 jpg,gif,png 만 등록할수 있습니다.(이미지 용량 2MG이하)</p> 
								<div class="select_img"><img src="" alt="썸네일사진" style="display:none;float:right; margin-top:-70px;border-radius:5px;"/></div>
								<p style="font-size: 8px;  color: gray;text-align:left;margin-left: 30px;">※여러파일 첨부가능합니다.</p>
								<p style="font-size: 8px; color: red; font-weight: bolder; margin-left: 30px;text-align:left;float:left">※첫번째 선택된 이미지가 썸네일 이미지로 등록됩니다.</p>
								
							</td>
						</tr>
						<tr>
							<td class="table-title" colspan="2""><p style="height:20px;text-align:left;">*서비스내용</p></td>
						</tr>
						<tr>
							<td colspan="2" style="border:0;" >
								<div  style="margin-left:-13px;">
									<textarea name="sContent" id="smartEditor" cols="50" rows="19"></textarea>
								</div>
								
							</td>
						</tr>
				</table>
			</div>
		</div>
		<div class="bottom2">
			<input type="button" value="등록 완료" style="width: 260px; height: 40px; margin-left: 450px; margin-top: 60px;">
		</div>
	</div>
	</form>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" /> 
	
	
	<script>
	//네이버 에디터
	var oEditors = [];
	    nhn.husky.EZCreator.createInIFrame({ 
	    	oAppRef : oEditors, elPlaceHolder : "smartEditor", 
	    	sSkinURI : "SE2/SmartEditor2Skin.html", 
	    	fCreator : "createSEditor2", 
	    	htParams : { 
	    		bUseToolbar : true, 
	    		bUseVerticalResizer : false, 
	    		bUseModeChanger : false 
	    		} 
	    }); 
	    
	    
	 $("#gdsImg").change(function(){
		 if(this.files && this.files[0]) {
		  var reader = new FileReader;
		  reader.onload = function(data) {
			  $(".select_img img").show();
		   	  $(".select_img img").attr("src", data.target.result).width(120).height(120);        
		  }
		  reader.readAsDataURL(this.files[0]);
		 }
	});

	$(function() {
		$("select[name=mainCategory]").change(function(){
			var temp = $("select[name=subCategory]");
			temp.focus();
			var a = $(this).val();
			temp.children().remove();
			if(a == '0'){
				temp.append('<option value="0">서브 카테고리를 선택</option>');
			}
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
		
		//금액 입력 유효성 검사(1. 숫자만 입력/2. 5000원 이상 입력)
		$("input[name=sPrice]").focusout(function(){
			var price = $(this).val();
			if(isNaN(price)){
				$(this).next().next().html("※숫자만 입력해주세요.").css({'color':'red'});
			}else if(price < 5000){
				$(this).next().next().html("※5,000원 이상을 입력해주세요.").css({'color':'red'});
			}else{
				$(this).next().next().html("");
			}
		});
		
		$("input[name=workingDate]").focusout(function(){
			var workingDate = $(this).val();
			if(isNaN(workingDate)){
				$(this).next().next().html("※숫자만 입력해주세요.").css({'color':'red'});
			}else{
				$(this).next().next().html("");
			}
		});
		
		//제출버튼 눌리면
		$("input[type=button]").click(function() { 
    		oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);  
    		var mainCategory = $("#mainCategory").val();
    		var price = $("input[name=sPrice]").val(); 
    		var day = $("input[name=workingDate]").val(); 
    		var content = document.getElementById("smartEditor").value; 
    		if($("input[name=sTitle]").val() == ""){
    			alert('제목을 입력해주세요.');
    			$("input[name=sTitle]").focus(); 
				return; 
    		}
    		if (mainCategory == 0) { 
    			alert('카테고리를 선택해주세요');
   				$("input[name=mainCategory]").focus(); 
   				return; 
   			}
    		if (isNaN(price) || price < 5000) { 
   				$("input[name=sPrice]").next().next().html("※올바른 값으로 입력해주세요.").css({'color':'red'});
   				$("input[name=sPrice]").focus(); 
   				return; 
   			} 
    		if (isNaN(day)) { 
    			$("input[name=workingDate]").next().next().html("※숫자만 입력해주세요.").css({'color':'red'});
				$("input[name=workingDate]").focus(); 
				return; 
			} 
    		if($('input[name=ssImg]').val() == ""){
    			alert("이미지를 등록해주세요.");
    			$('input[name=ssImg]').focus();
    			return;
    		}
    		if(content == "" || content == null || content == '&nbsp;' || content == '<br>' || content == '<br/>' || content == '<p>&nbsp;</p>'){ 
    			alert("본문을 작성해주세요."); 
    			oEditors.getById["smartEditor"].exec("FOCUS"); 
    			return; 
   			} 
    		if(getByteB(content)>4000){
    			alert("입력할 수 있는 글자수를 초과되었습니다.\n현재 본문크기 : "+getByteB(content)+"byte, 제한크기 : 4000byte");
    			return;
    		}
    		var result = confirm("등록 하시겠습니까?"); 
    		if(result){
    			$("#joinForm").submit(); 
   			}
    		else{ 
    			return; 
    			} 
    		});
	});
	
	function getByteB(str) {
		var Abyte = 0;
		for (var i=0; i<str.length; ++i) {
			// 기본 한글 3바이트 처리
			(str.charCodeAt(i) > 127) ? Abyte += 3 : Abyte++ ;
		}
			return Abyte;
		}

</script>

</body>
</html>