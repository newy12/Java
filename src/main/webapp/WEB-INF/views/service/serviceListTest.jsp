<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            height: 1200px;
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

        table {
            margin: 0 auto;
        }

        td {
            width: 250px;

        }

        .serviceBox {
            color: #282828;
            font-family: 'BBTreeGR';
            margin: 0 auto;
            /*border: 1px solid gray;*/
            padding: 10px;
            width: 245px;

        }

        .serviceBox img {
            border-radius: 5px;
            width: 235px;
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
        	
            <p class="naviTitle">디자인</p>
            <ul>
                <li class="navi-item">
                    <a href="#">로고ㆍ브랜딩</a>
                </li>
                <li class="navi-item">
                    <a href="#">인쇄ㆍ홍보물ㆍ배너</a>
                </li>
                <li class="navi-item">
                    <a href="#">캘리그라피ㆍ폰트</a>
                </li>
                <li class="navi-item">
                    <a href="#">일러스트ㆍ캐리커쳐</a>
                </li>
                <li class="navi-item">
                    <a href="#">간판ㆍ시공</a>
                </li>
                <li class="navi-item">
                    <a href="#">이벤트ㆍ상세페이지</a>
                </li>
                <li class="navi-item">
                    <a href="#">의류</a>
                </li>
                <li class="navi-item">
                    <a href="#">웹툰ㆍ캐릭터ㆍ이모티콘</a>
                </li>

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
                <table>
                    <tr>
                        <td>
                            <div class="serviceBox">
                                <img src="img/test/matildadjerf%20discovered%20by%20Alma%20Dahl%20on%20We%20Heart%20It.jpg"><br>
                                <span class="preName">하이샵</span><br>
                                <p class="serviceCon">"로고디자인 전문" 평생사용하시는 로고로 깔끔하게 디자인해 드립니다.</p>
                                <p class="price">80,000원~</p>
                                <p class="score">평균 5.0점<span class="star"> ★★★★★</span></p>
                            </div>
                        </td>

                        <td>
                            <div class="serviceBox">
                                <img src="img/test/matildadjerf%20discovered%20by%20Alma%20Dahl%20on%20We%20Heart%20It.jpg"><br>
                                <span class="preName">하이샵</span><br>
                                <p class="serviceCon">"로고디자인 전문" 평생사용하시는 로고로 깔끔하게 디자인해 드립니다.</p>
                                <p class="price">80,000원~</p>
                                <p class="score">평균 5.0점<span class="star">★★★★★</span></p>
                            </div>
                        </td>
                        <td>
                            <div class="serviceBox">
                                <img src="img/test/matildadjerf%20discovered%20by%20Alma%20Dahl%20on%20We%20Heart%20It.jpg"><br>
                                <span class="preName">하이샵</span><br>
                                <p class="serviceCon">"로고디자인 전문" 평생사용하시는 로고로 깔끔하게 디자인해 드립니다.</p>
                                <p class="price">80,000원~</p>
                                <p class="score">평균 5.0점<span class="star">★★★★★</span></p>
                            </div>
                        </td>
                        <td>
                            <div class="serviceBox">
                                <img src="img/test/matildadjerf%20discovered%20by%20Alma%20Dahl%20on%20We%20Heart%20It.jpg"><br>
                                <span class="preName">하이샵</span><br>
                                <p class="serviceCon">"로고디자인 전문" 평생사용하시는 로고로 깔끔하게 디자인해 드립니다.</p>
                                <p class="price">80,000원~</p>
                                <p class="score">평균 5.0점<span class="star">★★★★★</span></p>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
		
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />


</body>
</html>