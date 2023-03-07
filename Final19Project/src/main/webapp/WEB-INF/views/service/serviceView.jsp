<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 서비스 상세보기</title>

	<!--아이콘 부트스트랩-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- 캐러셀 부트스트랩-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    	<!-- favicon -->
	<link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
	<link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">

    
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


        .contentWrap {
            width: 1200px;
            height : 1800px;
            margin: 0 auto;
            display: block;
        }

       .crumb {
            font-family: 'Arita-dotum-Medium';
            color: dimgray;
            font-size: 10pt;
            padding: 20px;

        }

        .carousel {
            width: 100%;
            margin: 0 auto;
        }

        .carousel-inner img {
            width: 100%;
            height: 500px;
        }

        .carousel-indicators li {
            width: 10px;
            margin: 5px;
            border-radius: 10px;
        }

        /*.................... ▼ 왼쪽 컨텐츠...........................*/

        .leftContent {
            width: 57%;
            float: left;
        }

        .outer>ul {
            list-style: none;
            margin: 0;
            padding: 0;
        }

        .outer>ul li {
            margin: 0;
            padding: 0;
        }

        .tab {
            margin: 0;
            font-size: 7pt;
            list-style-type: none;
            display: inline-block;
            background-color: rgba(224, 224, 224, 0.42);
            width: 20%;
            height: 30px;
            text-align: center;
            line-height: 30px;
            border-top: 1px solid rgb(224, 224, 224);
            border-bottom: 1px solid black;
            border-right: 1px solid rgb(224, 224, 224);
            border-left: 1px solid rgb(224, 224, 224);
            float: left;
            color: gray;
        }

        .clickTab {
            background-color: transparent;
            border-top: 1px solid black;
            border-bottom: 1px solid transparent;
            border-left: 1px solid black;
            border-right: 1px solid black;
            font-weight: bold;
        }

        .tabcontent {
            width: 100%;
            height: 800px;
            text-align: left;
            padding: 20px;
            font-size: 8pt;
        }

        .tabcontent>h2 {
            font-size: 10pt;
            font-weight: bold;
            margin-top: 30px;
        }

        .priceInfo {
            border-top: 1px solid black;
            border-bottom: 1px solid black;
            height: 150px;
            background-color: rgba(224, 224, 224, 0.42);
            font-size: 8pt;
            padding: 10px;

        }

        .priceContent {
            float: left;
            width: 40%;
            height: 50px;
            margin: 10px;
        }

        .realPrice {
            float: right;
            text-align: right;
            margin: 10px;
        }

        .realPrice>p {
            font-size: 12pt;
            margin-bottom: 0;
        }

        .reviewBox {
            margin: 0 auto;
            border-bottom: 1px solid gray;
            width: 640px;
            padding: 10px;

        }

        .reviewBox p {
            margin-bottom: 10px;
        }

        .profile-img {
            float: left;
        }

        .reviewCon {
            margin-left: 120px;
        }

        .star {
            color: rgb(241, 196, 15);
            font-size: 12pt;
        }

        .reviewText {
            font-size: 10pt;
        }
        
        .anotherService{
        	border-radius:5px;
        	width: 185px;
        	height : 200px;
        	float: left;
        	margin: 5px;
        	padding: 2px;
        	box-shadow: 3px 3px 5px lightgray;
        }
        .anoImg{
        	border-top-left-radius: 10px;
        	border-top-right-radius: 10px;
        	margin-left: 3px;
        }

		.anoCon{
			
		}
		
		.anCon>p{
			font-size: 12pt;
		}
		.conView{
			white-space: pre-line;
		}
		
        /*.................... ▲ 왼쪽 컨텐츠...........................*/

        /*.................... ▼ 오른쪽 컨텐츠...........................*/
		 .rightContent {
                    margin: 0;
                    width: 478px;
                    float: right;

                }

                .titleWrap {
                    padding: 10px;
                    width: 450px;

                }

                .heart {
                    font-size: 11pt;
                }

                .rightInfo {
                    width: 450px;
                    height: 250px;
                    border: 1px solid gray;
                    margin: 0 auto;
                    padding: 23px;

                }

                .titlePrice {
                    font-size: 13pt;
                    text-align: right;
                    font-weight: bold;
                }

                .Infocon {
                    height: 80px;

                }

                .Infocon>p {
                    font-size: 11pt;
                    font-weight: bold;
                    margin-bottom: 2px;
                }

                .Infocon>span {
                    font-size: 10pt;
                }

                .conFirst {
                    width: 450px;
                }

                .othercon {
                    width: 200px;
                    float: left;
                }

                .centerBtn {
                    margin-left: 10px;
                    text-align: center;
                    width: 380px;
                    border: 1px solid #314C83;
                    background-color: #314C83;
                    color: white;
                    border-radius: 5px;
                    height: 35px;
                    font-size: 10.5pt;
                }

                .secu {
                    margin: 0 auto;
                    width: 450px;
                    border: 1px solid gray;
                    font-size: 11pt;
                    color: #282828;
                    padding: 13px;
                }

                .preIntro {
                    margin: 0 auto;
                    width: 450px;
                    border: 1px solid gray;
                    padding: 20px;
                }

                .pre-profile {
                    text-align: center;
                    margin: 20px;
                }

                .emptyBtn {
                    text-align: center;
                    width: 380px;
                    border: 3px solid #314C83;
                    background-color: transparent;
                    color: #314C83;
                    border-radius: 5px;
                    height: 35px;
                    font-size: 10.5pt;
                    font-weight: bold;
                }

                .bold-font {
                    font-weight: bold;
                }

                .Intro-detail {
                    font-size: 10pt;

                }
                .sIntro>pre,
                .Intro-detail>pre{
                	background-color: transparent;
                	border: none;
             
                }
                
                .heart:hover{
                	cursor: pointer;
                }
                
                 .fas{
			       	color: #ef4f4f;
			     }
				.contentTitle{
					border-bottom: 2px solid #314C83; 
					padding-bottom: 20px; 
					margin-bottom: 20px;
					font-size: 12pt;
					width: 448px;
					
					
				}


        /*.................... ▲ 오른쪽 컨텐츠...........................*/
    </style>


</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	 <div class="contentWrap">
        <div class="crumb">
            <div>홈 > 서비스 > 서비스 상세보기</div>
        </div>
        <div class="serviceContent">
            <div class="leftContent">
            
            <!-- 캐러셀 시작  -->
                <div id="demo" class="carousel slide" data-ride="carousel">

                    <!-- Indicators -->
                     <ul class="carousel-indicators">
                    	<c:forEach items="${fileList }" varStatus="status" >
                    		<c:if test="${status.index == 0 }">
                    			<li data-target="#demo" class="active" data-slide-to="${status.index }" ></li>
                    		</c:if>
                    		<c:if test="${status.index != 0 }">
                    			<li data-target="#demo" data-slide-to="${status.index }"></li>
                    		</c:if>
                    	</c:forEach>
                        
                    </ul>
                                        
                    <!-- The slideshow -->
                    <div class="carousel-inner">
                    	<c:forEach items="${fileList }" var="sf" varStatus="status">
                    		<c:if test="${status.index == 0 }">
                    			<div class="carousel-item active">
                           		 	<img src="upload/service/${sf.filepath }" alt="test${status.index+1 }">
                        		</div>
                    		</c:if>
                    		<c:if test="${status.index != 0 }">
                    			<div class="carousel-item">
                           		 	<img src="upload/service/${sf.filepath }" alt="test${status.index+1 }">
                        		</div>
                    		</c:if>
                    		
                    		
                    	</c:forEach>
                    </div>

                    <!-- Left and right controls -->
                    <a class="carousel-control-prev" href="#demo" data-slide="prev">
                        <span class="carousel-control-prev-icon"></span>
                    </a>
                    <a class="carousel-control-next" href="#demo" data-slide="next">
                        <span class="carousel-control-next-icon"></span>
                    </a>
                </div>
                <!-- 캐러셀 끝  -->


                <br><br><br>

                <!-- 서비스 설명 탭 -->
                <div class="outer">
                    <ul>
                        <li class="tab">서비스설명</li>
                        <li class="tab">가격정보</li>
                        <li class="tab">취소ㆍ환불규정</li>
                        <li class="tab">서비스평가</li>
                        <li class="tab">다른서비스보기</li>
                    </ul>
                </div>

                <div class="tabContentWrap">
                    <div class="tabcontent sIntro" style=" font-family: 'Arita-dotum-Medium';">

                        <h2>서비스 설명</h2>
                        <hr>
                        <pre class="conView" style=" font-family: 'Arita-dotum-Medium';">${s.SContent }</pre>
                    </div>
                    <div class="tabcontent">

                        <h2>가격정보</h2>
                        <div class="priceInfo">
                            <div class="priceContent">
                                <p class="bold-font">서비스종류</p>
                                <span>${s.mainCategoryName }</span> <span> > </span> <span>${s.subCategoryName }</span>

                            </div>
                            <div class="priceContent">
                                <p class="bold-font">서비스지역</p>
                                <span>${s.SArea }</span>

                            </div>
                            <div class="priceContent">
                                <p class="bold-font">예상작업일</p>
                                <span> <i class='far fa-calendar-alt'></i> ${s.workingDate }일</span>

                            </div>
                            <div class="realPrice">
                                <p class=" bold-font">${s.SPriceTxt }원~</p>
                                <span>(VAT포함가)</span>
                            </div>

                        </div>

                    </div>
                    <div class="tabcontent">

                        <h2>취소ㆍ환불규정</h2>
                        <hr>
                        <p>
                            가. 기본 환불 규정<br>
                            1. 전문가와 의뢰인의 상호 협의하에 청약 철회 및 환불이
                            가능합니다.<br>
                            2. 작업이 완료된 이후 또는 자료, 프로그램 등 서비스가 제공된 이후에는 환불이 <br>
                            불가합니다.( 소비자보호법 17조 2항의 5조. 용역 또는 「문화산업진흥 기본법」 <br>
                            제2조 제5호의 디지털콘텐츠의 제공이 개시된 경우에 해당)
                            <br><br>
                            나. 전문가 책임 사유<br>
                            1. 전문가의 귀책사유로 당초 약정했던 서비스 미이행 혹은 보편적인 관점에서 <br>
                            심각하게 잘못 이행한 경우 결제 금액 전체 환불이 가능합니다.
                            <br><br>
                            다. 의뢰인 책임 사유<br>
                            1. 서비스 진행 도중 의뢰인의 귀책사유로 인해 환불을 요청할 경우, 사용 금액을<br>
                            아래와 같이 계산 후 총 금액의 10%를 공제하여 환불합니다.<br><br>
                            총 작업량의 1/3 경과 전 : 이미 납부한 요금의 2/3해당액<br>
                            총 작업량의 1/2 경과 전 : 이미 납부한 요금의 1/2해당액<br>
                            총 작업량의 1/2 경과 후 : 반환하지 않음<br>
                        </p>

                    </div>
                    <div class="tabcontent">
                        <h2>서비스 평가 (평균 ${s.SRate }점)</h2>
                        <hr>
                        
                        <c:forEach items="${reviewList }" var="review">
                              <div class="reviewBox">
                                  <div class="profile-img">
                                      <img src="/img/test/icon_userProfile.svg" width="100px;">
                                  </div>
                                  <div class="reviewCon">
                                      <p class="date">${review.writeDate }</p>
                                      <p class="score star">
                                      <c:choose>
                                      	<c:when test="${review.RRate == 0}">
                                      		☆☆☆☆☆
                                      	</c:when>
                                      	<c:when test="${review.RRate == 1}">
                                      		☆☆☆☆★
                                      	</c:when>
                                      	<c:when test="${review.RRate == 2}">
                                      		☆☆☆★★
                                      	</c:when>
                                      	<c:when test="${review.RRate == 3}">
                                      		☆☆★★★
                                      	</c:when>
                                      	<c:when test="${review.RRate == 4}">
                                      		☆★★★★
                                      	</c:when>
                                      	<c:when test="${review.RRate == 5}">
                                      		★★★★★
                                      	</c:when>
                                      	
                                      </c:choose>
										<span style="font-size: 9pt; color: gray;">${review.RRate }.0점</span>                                        
                                      </p>
                                      <p class="reviewText">${review.RContent }</p>
                                      <p class="userId">${review.MId }</p>
                                  </div>
                              </div>          
                        </c:forEach>
                            
                            	<div style="text-align: center;">  
                            		 ${pageNavi } 
                            	</div>
                            
                        
                       

                    </div>
                    <div class="tabcontent">
                        <h2>다른서비스보기</h2>
                        <hr>
                        <c:forEach items="${sList }" var="ser" begin="0" end="4">
                        	<div class="anotherService"> 
	                        	<img class="anoImg" src="/upload/service/${ser.SImg }" width="180px;" style="margin: 0 auto;">
	                        	<div class="anoCon" style="margin-top: 10px; margin-bottom:10px; height: 80px;">
	                        	<input type="hidden" value="${ser.SNo }" id="serviceNo">
	                        	<c:if test="${loginMember != null }">
	                        		<a class="anoTitle" style="font-size: 10pt; height: 75px;" href="/serviceView.do?sNo=${ser.SNo}&reqPage=1&mNo=${loginMember.MNo }"> ${ser.STitle } </a>
	                        	</c:if>
	                        	<c:if test="${loginMember == null }">
	                        		<a class="anoTitle" style="font-size: 10pt; height: 75px;" href="/serviceView2.do?sNo=${ser.SNo}&reqPage=1"> ${ser.STitle } </a>
	                        	</c:if>
	                        	
	                        		<br>
	                        		<span>${ser.mainCategoryName } / ${ser.subCategoryName } </span>
	                        	</div>
                        </div>
                        </c:forEach>
                        
                        
                         

                    </div>
                </div>
                <!-- 서비스 설명 탭 끝-->
            </div>
			 <div class="rightContent">
                <div class="titleWrap">
                    <div class="contentTitle bold-font">${s.STitle }</div>
                    <p class="titlePrice">${s.SPriceTxt }원~</p>
                    <div class="heart">
                        <c:choose>
                        	<c:when test="${loginMember != null }">
                        		<input type="hidden" value="${loginMember.MNo }" id="m_no">
                        		<c:if test="${loginMember.MId == s.MId }">
                        			<a href="javascript:like_func_error();" style="color: gray;"><i class="full-heart far fa-heart"></i> 이 서비스 찜하기</a>
                        		</c:if>
                        		<c:if test="${loginMember.MId != s.MId }">
                        			<a href="javascript:like_func();" style="color: gray;"><i class="full-heart far fa-heart"></i> 이 서비스 찜하기</a>
                        		</c:if>
                        	</c:when>
                        	<c:otherwise>
                        		<a href="javascript: login_need();" style="color: gray;"><i class="full-heart far fa-heart"></i> 이 서비스 찜하기 </a>
                        	</c:otherwise>
                        </c:choose>
                        
                    </div>
                </div>
                <div class="rightInfo">
                    <div class="Infocon conFirst">
                        <p>서비스 종류</p>
                        <input type="hidden" value="${s.SNo }" id="s_no">
                        <span>${s.mainCategoryName }</span> <span> > </span> <span>${s.subCategoryName }</span>
                    </div>
                    <div class="Infocon othercon">
                        <p>예상 작업일</p>
                        <span> <i class='far fa-calendar-alt'></i> ${s.workingDate }일</span>
                    </div>
                    <div class="Infocon othercon">
                        <p>서비스 지역</p>
                        <span>${s.SArea }</span>

                    </div>
                    <br>
                    <c:if test="${s.deleteStatus ne 'y'.charAt(0) }">
                    <c:if test="${empty loginMember }">
               		 <button class="centerBtn">로그인 후 문의하세요</button>
               		</c:if>
                    <c:if test="${loginMember.MGrade eq 1 }">
                    <button class="centerBtn" onclick="startChat('${s.SNo }','${sessionScope.loginMember.MId }','${s.MId}','${sessionScope.loginMember.MNo }','${sessionScope.loginMember.MGrade}');">전문가에게 문의하기</button>
               		</c:if>
               		<c:if test="${loginMember.MGrade eq 2 }">
               		 <button class="centerBtn">일반회원으로 전환후 문의하세요</button>
               		</c:if>
               		</c:if>
               		<c:if test="${s.deleteStatus eq 'y'.charAt(0) }">
               		 <button class="centerBtn">삭제된 서비스입니다</button>
               		</c:if>
                </div>
                <br>
                <div class="secu">
                    <i class="material-icons" style="font-size: 13pt;">security</i>
                    일.구.시를 통해 결제하면 거래완료시까지 결제 대금을 안전하게 보호받을 수 있습니다.
                </div>
                <br>
                <div class="preIntro">
                    <div class="pre-profile">
                        <img src="/img/test/icon_profile.svg" width="100px;">
                        <p class="brandName bold-font">${m.brandName }</p>
						<c:if test="${not empty m.brandName }">
                        	<button class="emptyBtn" onclick="location.href='/introduceFrm.do?mId=${m.MId}&reqPage=1'">[${m.brandName }]의 프로필 보기</button>
                        </c:if>
                        <c:if test="${empty m.brandName }">
                        	<button class="emptyBtn" >탈퇴한 회원입니다</button>
                        </c:if>
                    </div>

                    <hr>
                    <div class="Intro-detail">
                        <p class="introTitle bold-font">전문가 소개</p>
                        <Pre style="white-space: pre-line; padding: 20px;  font-family: 'Arita-dotum-Medium';" > ${m.introduce } </Pre>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
    
    $(document).ready (function () {
    	
		var fCheck = ${favoriteCheck};
		console.log("favorite:"+fCheck);
		
		if(fCheck == true){
			$(".full-heart").addClass("fas");
		}
		
	
    })
    	// 서비스 상세보기 탭 구현 
        $(function() {
            var tab = $(".tab");
            var content = $(".tabcontent");

            $(".tabcontent").each(function(i, item) {
                $(this).hide();
                content.eq(0).show();
            });

            tab.eq(0).addClass("clickTab");

            $(".tab").click(function() {
                var index = tab.index(this);

                $(".tabcontent").each(function(idx, item) {
                    if (idx == index) {
                        $(item).show();
                    } else {
                        $(item).hide();
                    }
                });
                $(this).siblings().removeClass("clickTab");
                $(this).addClass("clickTab");

            })

            $(".heart").click(function() {
                $(this)
            })
        });
        
    	// 좋아요 클릭시 찜하기 insert 
        function like_func() {
        	var m_no = $("#m_no").val();
        	var s_no = $("#s_no").val();
        	var fCheck = ${favoriteCheck};
        	console.log("m_no:"+m_no+"s_no:"+s_no);
        	
        	if(m_no != null){
        		if(fCheck == true){
            		var fCon = confirm("해당 서비스를 찜한 목록에서 제거 할까요?")
            		if(fCon == true){
            			$.ajax({
                			url:"/deleteHeart.do",
                			type: "post",
                			data:{mNo:m_no, 
                    			sNo:s_no},
                    		error : function () {
    							alert("제거못함");
    						},
                    		success:function(){
                    			$(".full-heart").removeClass("fas");
                    			location.reload();
                    		}
                    		
                		})
            		}
            	}else{
            		$.ajax({
                		url:"/insertHeart.do",
                		type:"post",
                		data:{mNo:m_no, 
                			sNo:s_no},
                		success:function(){
                			$(".full-heart").addClass("fas");
                			location.reload();
                		}
                	})
            	}
        		
        	}else{
        		alert("찜하기는 로그인이 필요합니다.");
        	}
		}
    	
    	//본인 서비스는 찜할 수 없음
    	function like_func_error(){
    		alert("본인 서비스는 찜할 수 없습니다.");
    	}
    	
    
        function login_need() {
			alert("로그인이 필요합니다.");
		}
        
        function startChat(sNo, userId, freeId, mNo, mGrade) {
        	if(userId == freeId){
        		alert("본인의 서비스는 문의할 수 없습니다.");
        	}else{
			$.ajax({
				url : "/makeRoom.do",
				type : "post",
				async : false,
				data : {
					sNo : sNo,
					userId : userId,
					freeId : freeId,
					mNo : mNo,
					mGrade : mGrade
				},
				success : function(data) {
					/* location.href = "/enterRoom.do?cNo=-1&sNo=" + sNo
							+ "&myId=" + userId + "&yourId=" + freeId
							+ "&mGrade=" + mGrade */
					var loc="/enterRoom.do?cNo=-1&sNo=" + sNo + "&myId="+ userId + "&yourId=" + freeId+"&mGrade="+mGrade
					var _left = Math.ceil(( window.screen.width - 530 )/2);
					window.open(loc, '', 'width=530, height=630, left='+_left+', top=50, location=no,scrollbars=no,location=no, resizable=no'); 
				},
				error : function() {

				}
			});
       	}
	}
        
 /*        
        $(function () {
			var containerHeight = $(".serviceContent").height();
			$(".contentWrap").height(containerHeight+400);
		}) */
        
        
        
    </script>
    

<footer>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</footer>




</body>
</html>