<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 마이페이지</title>
<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
<style>
    .page-wrap{
        width: 1104px;
        height:700px;
        margin: 0 auto;
        margin-top:50px;
    }
    .board-box{
        width: 800px;
        height: 531px;
        float: left;
        margin-top: 40px;
        margin-left: 30px;
        border: 1px solid rgb(224, 224, 224);
    }
    .board-box>p{
        font-size: 18px;
        font-weight: bold;
        color: rgb(49, 76, 131);
        margin-top: 20px;
        margin-left: 30px;
    }
    .board-box>div{
        margin-top: 30px;
        margin-left: 50px;
    }
    .board-box>div>div{
        margin-bottom: 40px;
    }
    .board-box>div:last-child{
        text-align: center;
    }
    .form-box{
        width: 300px;
        height: 31px;
        border-radius: 3px;
        border: 1px solid rgb(204, 204, 204);
        background-color: rgba(224, 224, 224, 0.4);
    }
    .tel{
        width: 90px;
    }
    .btn2{
        width: 135px;
        height: 31px;
        border-radius: 5px;
    }
    .btn2:hover{
        border: 2px solid black;
    }
    .change{
        color: white;
        border: 1px solid rgb(49, 76, 131);
        background-color: rgb(49, 76, 131);
    }
    .delete{
        color: rgb(49, 76, 131);
        border: 1px solid rgb(49, 76, 131);
        background-color: white;
    }
    #email-check, #phone-check{
    	color:#008000;
    	font-weight:bold;
    	margin-left:20px;
    }
    /****모달*****/
    #modal {
	  position:absolute;
	  top:200px;
	  left:0;
	  display:none;
	  width:100%;
	  height:100%;
	  z-index:1;
	}
	
	#modal h2 {
	  margin:0;   
	}
	#modal>div>div{
		margin:0 auto;
		margin-top:40px;
		text-align: center;
	}
	#modal>div>div>button{
		width:100px;
	}
	
	#modal .modal_content {
	  width:400px;
	  height:280px;
	  margin:100px auto;
	  padding:30px 30px;
	  background:#fff;
	  border-radius: 5px;
	  border: 2px solid #314C83;
	  box-shadow: 1px 1px 12px 1px #314C83;
	}
	
	#modal .modal_layer {
	  position:fixed;
	  top:0;
	  left:0;
	  width:100%;
	  height:100%;
	  background:rgba(0, 0, 0, 0.5);
	  z-index:-1;
	}
	.pw{
		width:100%;
	}
    /****비밀번호 변경 모달*****/
    #modal_pwChange {
	  position:absolute;
	  top:200px;
	  left:0;
	  display:none;
	  width:100%;
	  height:100%;
	  z-index:1;
	}
	
	#modal_pwChange h2 {
	  margin:0;   
	}
	#modal_pwChange>div>div:last-child{
		margin-top:30px;
		text-align: center;
	}
	#modal_pwChange>div>div>button{
		width:100px;
	}
	
	#modal_pwChange .modal_content {
	  width:600px;
	  height:400px;
	  margin:100px auto;
	  padding:30px 30px;
	  background:#fff;
	  border-radius: 5px;
	  border: 2px solid #314C83;
	  box-shadow: 1px 1px 12px 1px #314C83;
	}
	#modal_pwChange>div>div>span{
		width:170px;
		display:inline-block;
	}

	#modal_pwChange .modal_layer {
	  position:fixed;
	  top:0;
	  left:0;
	  width:100%;
	  height:100%;
	  background:rgba(0, 0, 0, 0.5);
	  z-index:-1;
	}
	.pw2{
		width:67%;
	}
	.notice-text{
		font-size: 10px;
		color:#fff;
		text-align: right;
	}
</style>
</head>
<body>
	<div class="header">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
    <div class="page-wrap">
        <!-- 사이드메뉴 -->
        <jsp:include page="/WEB-INF/views/member/mypage-side.jsp" />
       
        <div class="board-box">
            <p>나의 프로필</p>
            <div>
                <div class="form-group">
                    <label for="id-label">아이디</label><br>
                    <input type="text" class="form-box" id="id-label" value="${loginMember.MId }" readonly>
                    <input type="text" style="display:none;" id="pw-label" value="${loginMember.MPw }" readonly>
                </div>
                <div class="form-group">
                    <label for="name-label">이름</label><br>
                    <input type="text" class="form-box" id="name-label" value="${loginMember.MName }" readonly>
                </div>
                <div class="form-group">
                    <label for="email-label">이메일</label><br>
                    <input type="email" class="form-box" id="email-label" value="${loginMember.MEmail }" readonly>&nbsp;&nbsp;
                    <a href='javascript:void(0);' onclick='emailEdit(this);' id="email-btn">수정</a>
                    <span id="email-check">정상적으로 수정되었습니다.</span>
                </div>
                <div class="form-group">
                    <label for="tel-label">연락처</label><br>
                    <input type="text" style="display:none;" value="${loginMember.MPhone }" id="phone">
                    <input type="tel" class="form-box tel" id="tel-label" value="010"  readonly> - 
                    <input type="tel" class="form-box tel"  value="" readonly id="phone1"> - 
                    <input type="tel" class="form-box tel"  value="" readonly id="phone2">&nbsp;&nbsp;
                    <a href='javascript:void(0);' onclick='phoneEdit(this);' id="phone-btn">수정</a>
                    <span id="phone-check">정상적으로 수정되었습니다.</span>
                </div>
            </div>
            <div>
                <button class="btn2 change" onclick="pwChange()">비밀번호 변경하기</button>&nbsp;&nbsp;
                <button class="btn2 delete" onclick="deleteMember()">회원 탈퇴하기</button>
            </div>
        </div>
    </div>

	<br><br><br>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
	
	
	<!-- 모달 -->
	<div id="modal">
	    <div class="modal_content">
	        <h2>회원탈퇴</h2>
	        <h5>아래 비밀번호를 입력하시면 탈퇴가 진행됩니다.</h5>
	        <hr>
	        <span style="color:#314C83">비밀번호 입력 </span>
	        <input type="password" style="display:none" id="id-check" value="${loginMember.MId }"> 
	        <input type="password" class="form-box pw" id="pw-check"> 
	        <div>
	        	<button class="btn2 delete" id="modal_confirm_btn">탈퇴하기</button>
	        	<button class="btn2 change" id="modal_close_btn">닫기</button>
	        </div>
	    </div>
	    <div class="modal_layer"></div>
	</div>
	
	<div id="modal_pwChange">
	    <div class="modal_content">
	        <h2>비밀번호 변경</h2>
	        <h5>현재 비밀번호와 변경할 비밀번호를 입력해주세요.</h5>
	        <h6>비밀번호는 영문, 숫자, 특수문자를 포함하여 4글자 이상으로 작성해야합니다.</h6>
	        <hr>
	        <div>
	        	<span style="color:#314C83">현재 비밀번호 입력 </span>
	        	<input type="password" class="form-box pw2" style="background-color:#fff" id="pw-current"> 
	        	<div style="height:15px;"><div class="notice-text"></div></div>
	        </div>
	        <div>
	        	<span style="color:#314C83">변경할 비밀번호 입력 </span>
	        	<input type="text" style="display:none;" id="reg-check" value="false">
	        	<input type="password" class="form-box pw2" id="pw-check1" readonly>
	        	<div style="height:15px;"><div class="notice-text"></div></div>
	        </div>
	        <div>
	        	<span style="color:#314C83">변경할 비밀번호 다시 입력 </span>
	        	<input type="password" class="form-box pw2" id="pw-check2"readonly>
	        	<div style="height:15px;"><div class="notice-text"></div></div>
	        </div>
	        <div>
	        	<button class="btn2 change" id="modal_pwChange_confirm_btn">변경저장</button>
	        	<button class="btn2 delete" id="modal_pwChange_close_btn">닫기</button>
	        </div>
	    </div>
	    <div class="modal_layer"></div>
	</div>
	
	
	<!-- 스크립트 -->
	<script>
	 document.addEventListener('DOMContentLoaded', function() {
		 $('#email-check').hide();
		 $('#phone-check').hide();
		 
		 //메뉴 고정
		 $(".menu").children().eq(0).find('a').css({'margin-left':'5px', 'font-weight':'bold'});
		 $(".menu").children().eq(0).find('img').css({'display':'inline'});
		 
		 //폰번호 분리
	 	var phone_data = $("#phone").val();
	 	$("#phone1").val(phone_data.substring(4,8));
	 	$("#phone2").val(phone_data.substring(9));
	 });
	 
	//이메일 수정하기
	 function emailEdit(obj){
		 var mId = $('#id-label').val();
		 var mPw = $('#pw-label').val();
		 var status = $('#email-btn').html();
		 var mEmail = $('#email-label').val();
		 if(status == "수정"){
			 $('#email-label').attr('readonly', false).css({'background-color': 'white'});
			 $('#email-btn').html('저장');
		 }else{
             $.ajax({
                 url : "/changeMypage.do",
                 type : "get",
                 data : {"mId":mId,"mPw":mPw,"data":mEmail,"object":"email" }, 
                 success : function(data){
                	 $('#email-label').attr('readonly', true).css({'background-color': 'rgba(224, 224, 224, 0.4)'});
        			 $('#email-btn').html("수정");
        			 $('#email-check').fadeIn(1000).delay(1000).fadeOut(1000);  
                 },
                error : function(){
                   console.log("실패");
                }
              });
		 }
	 }
	 
 	//전화번호 수정하기 누르면
 	function phoneEdit(obj){
 		var mId = $('#id-label').val();
		var mPw = $('#pw-label').val();
		var status = $('#phone-btn').html();
 		var phone1 = $("#phone1").val();
 		var phone2 = $("#phone2").val();
 		if(status == "수정"){
			 $('#phone1').attr('readonly', false).css({'background-color': 'white'});
			 $('#phone2').attr('readonly', false).css({'background-color': 'white'});
			 $('#phone-btn').html('저장');
		 }else{
			 var phone = "010-"+phone1+"-"+phone2;
			 var reg = /^[0-9]{4}$/;
			 if(reg.test(phone1) && reg.test(phone2)){
				$.ajax({
	                url : "/changeMypage.do",
	                type : "get",
	                data : {"mId":mId,"mPw":mPw,"data":phone,"object":"phone" }, 
	                success : function(data){
	                $('#phone1').attr('readonly', true).css({'background-color': 'rgba(224, 224, 224, 0.4)'});
	       			 $('#phone2').attr('readonly', true).css({'background-color': 'rgba(224, 224, 224, 0.4)'});
	       			 $('#phone-btn').html("수정");
	       			$('#phone-check').html('정상적으로 수정되었습니다').css({'color':'#008000'}).fadeIn(1000).delay(1000).fadeOut(1000); 
	                },
	               error : function(){
	                  console.log("실패");
	               }
	             }); 
			 }else{
				 $('#phone-check').html('숫자 4자리로 작성해주세요').css({'color':'red'}).fadeIn(1000).delay(1000).fadeOut(1000);  
			 } 
		 }
 	}
 	
 	//비밀번호 변경하기
 	function pwChange(){
 		$('#modal_pwChange').toggle('slow');
 	}
 	$("#modal_pwChange_close_btn").click(function(){
 		$('#modal_pwChange').toggle('slow');
    });
 	$("#modal_pwChange_confirm_btn").click(function(){
 		var mId = $('#id-label').val();
		var mPw = $('#pw-label').val();
 		var pwCheck1 = $("#pw-check1").val();
 		var pwCheck2 = $("#pw-check2").val();
 		var regCheck = $("#reg-check").val();
 		console.log(regCheck);
 		
 		if(pwCheck1 == pwCheck2 &&  pwCheck2 != '' && regCheck == 'true' ){
 			alert('비밀번호가 변경되었습니다.');
 			location.href="/changePw.do?mId="+mId+"&mPw="+mPw+"&data="+pwCheck2+"&object=pw";
 		}else{
 			alert('다시 한 번 확인해주세요');
 		}
 	});
 	$("#pw-current").keyup(function(){
 		var mPw_input = $('#pw-current').val();
 		var mPw_current = $('#pw-label').val();
 		if(mPw_input == mPw_current){
 			$('#pw-current').attr('readonly', true).css({'background-color': 'rgba(224, 224, 224, 0.4)'});
 			$('#pw-current').next().children().css({'color':'#008000'}).html('확인되셨습니다. 변경할 비밀번호를 입력해주세요').fadeIn(1000).delay(1000).fadeOut(1000); 
 			$('#pw-check1').attr('readonly', false).css({'background-color': 'white'}).focus()
  			$('#pw-check2').attr('readonly', false).css({'background-color': 'white'});
 		}else{
 			$('#pw-current').next().children().css({'color':'red'}).html('올바른 비밀번호를 입력해주세요');
 		}
 	});
 	$("#pw-check1").keyup(function(){
 		var pwCheck1 = $("#pw-check1").val();
 		var reg = /^(?=.*?[A-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{4,}$/.test(pwCheck1);
 		if(reg){
 			$('#reg-check').val('true');
 			$('#pw-check1').next().children().css({'color':'#008000'}).html('조건을 만족하였습니다').delay(1000).fadeOut(1000);
 		}else if(!reg && pwCheck1 != ''){
 			$('#reg-check').val('false');
 			$('#pw-check1').next().children().css({'color':'red'}).html('비밀번호 조건을 만족해주세요').show();
 			
 		}
 	});
 	$("#pw-check2").keyup(function(){
 		var pwCheck1 = $("#pw-check1").val();
 		var pwCheck2 = $("#pw-check2").val();
 		if(pwCheck1 == pwCheck2 && pwCheck2 != ''){
 			$('#pw-check2').next().children().css({'color':'#008000'}).html('일치합니다').fadeOut(1000);
 		}else if(pwCheck1 != pwCheck2 && pwCheck2 != ''){
 			$('#pw-check2').next().children().css({'color':'red'}).html('일치하지 않습니다').show();
 		}
 	});
 	
 	//회원탈퇴 하기
 	function deleteMember(){
 		$('#modal').toggle('slow');
 	}
 	$("#modal_close_btn").click(function(){
 		$('#modal').toggle('slow');
    });
 	$("#modal_confirm_btn").click(function(){
 		var mId = $('#id-label').val();
		var mPw = $('#pw-label').val();
		var mPw_check = $('#pw-check').val();
		if(mPw == mPw_check){
			location.href="/deleteMember.do?mId="+mId;
		}else{
			alert('비밀번호가 일치하지 않습니다. 다시 입력해주세요');
		}
 	});
	</script>
</body>
</html>