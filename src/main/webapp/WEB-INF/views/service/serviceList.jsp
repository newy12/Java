<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 일 구하고 시퍼_서비스</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
<style>
	@font-face {
            font-family: 'Arita-dotum-Medium';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Arita-dotum-Medium.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }

        .contentWrap {
            width: 1200px;
            margin: 0 auto;
            height: 1600px;
        }

        .sideNavi {
            color: #282828;
            font-family: 'Arita-dotum-Medium';
            /*border: 1px solid lightgray;*/
            width: 200px;
            float: left;

        }

        .serviceList {
            width: 980px;
            float: left;
        }

        .naviTitle {
            padding-left: 10px;
            font-size: 15pt;
            margin: 15px;
            border-left: 3px solid #314C83;
        }

        .menu ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            text-align: left;
        }

        .menu li>a {
            margin-left: 15px;
            text-decoration: none;
            width: 200px;
            height: 40px;
            line-height: 40px;
            display: block;
            text-indent: 10px;
            color: #282828;
            font-size: 11pt;
        }

        a:hover {
            font-weight: bold;

        }

        .crumb {
            font-family: 'Arita-dotum-Medium';
            color: dimgray;
            font-size: 10pt;
            padding: 20px;
            
        }

        .selectBox {
            height: 30px;
            width: 70px;
        }

        .searchDiv {
            width: 978px;
            height: 50px;
            float: right;
        }

        .searchBox {
            font-family: 'Arita-dotum-Medium';
            float: right;
        }

        .searchBox>button {
            outline: none;
            border: 1px solid #314C83;
            background-color: #314C83;
            color: white;
            font-weight: bold;
            border-radius: 3px;
            height: 30px;
            width: 55px;
        }

        .tableContainer {
            margin: 0 auto;

        }
        
        .serviceBox {
            color: #282828;
            font-family: 'BBTreeGR';
            margin: 10px;
            /*border: 1px solid gray;*/
            padding: 10px;
            width: 225px;
            display:inline-block;
            float: left;
        }

        .serviceBox img {
            border-radius: 5px;
            width: 225px;
            height: 180px;
        }

        .preName {
            text-align: left;
            font-size: 10pt;
            font-weight: bold;
            margin-bottom: px;
        }

        .serviceCon {
            font-size: 10pt;
            margin: 0;
        }

        .price {
            text-align: right;
            font-weight: bold;
            font-size: 12pt;
            margin: 0;
        }

        .score {
            font-size: 8pt;
            text-align: right;
            color: gray;
            margin: 0;
            height: 15px;
        }

        .star {
            color: rgb(241, 196, 15);
            font-size: 12pt;
        }

</style>

</head>
<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	
		<div class="contentWrap">
		
        <div class="crumb">
            <div>홈 > 서비스 </div>
        </div>
        
        <div class="sideNavi menu">
            <p class="naviTitle">${mainCate }</p>
            
            <ul>
				<c:forEach items="${catList }" var="c">
					<li class="navi-item">
	                    <a href="/serviceList.do?cNo=${c.CNo }"> ${c.CName } </a>
	                </li>	
				</c:forEach>
            </ul>

        </div>
        <div class="serviceList">
            <div class="searchDiv">
                <div class="searchBox">
                    <select class="selectBox">
                        <option>인기순</option>
                        <option>평점순</option>
                        <option>최신순</option>
                    </select>
                    <input type="text" name="searchKeyword">
                    <button>검색</button>
                </div>
            </div>
            <div class="tableContainer">
            <c:forEach items="${serviceList }" var="s" varStatus="status">
	            <div class="serviceBox">
	                    <img src="upload/service/${s.SImg }"><br>
	                    <span class="preName">${brandName[status.index] }</span><br>
	                    <p class="serviceCon" style="height: 30px;">${s.STitle }</p>
	                    <p class="price">${s.SPrice }원~</p>
	                    <p class="score">평점 ${s.SRate }.0
	                    
	                   		<c:if test="${s.SRate == 5}">
	                    		<span class="star">
	                    			★★★★★
	                   			</span>
	                   		</c:if>
	                   		
	                   		<c:if test="${s.SRate == 4}">
	                    		<span class="star">
	                    			☆★★★★
	                   			</span>
	                   		</c:if>
	                   		<c:if test="${s.SRate == 3}">
	                    		<span class="star">
	                    			☆☆★★★
	                   			</span>
	                   		</c:if>
	                   		<c:if test="${s.SRate == 2}">
	                    		<span class="star">
	                    			☆☆☆★★
	                   			</span>
	                   		</c:if>
	                   		<c:if test="${s.SRate == 1}">
	                    		<span class="star">
	                    			☆☆☆☆★
	                   			</span>
	                   		</c:if>
	                   		<c:if test="${s.SRate == 0}">
	                    		<span class="star">
	                    			☆☆☆☆☆
	                   			</span>
	                   		</c:if>                   	
	                    </p>
	               </div>
            </c:forEach>
                
                
                       
            
        </div>
    </div>
</div>
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />


<script >
	$(function (){
		
		
		
		
		
	})



</script>


</body>
</html>