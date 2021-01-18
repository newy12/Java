<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">
  <title>19시 :: 일 구하고 시퍼</title>
  <!-- favicon -->
  <link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
  <link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
  <link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">


  <style>
    .main-section {
      width: 1200px;
      margin: 0 auto;
      font-family: "Arita-dotum-Medium";
    }

    .famous-category-slider {
      width: 100%;
      margin: 50px 0;
    }

    .famous-category-list {
      width: 100%;
      height: 100%;
    }

    .category-item {
      position: relative;
      outline: none;
      border-radius: 4px;
    }

    .category-item img {
      width: 230px;
      border-radius: 4px;
    }

    /* 아이템 효과 */
    .category-title-background {
      transition: 0.3s;
      position: absolute;
      width: 230px;
      height: 50%;
      top: 50%;
      background: linear-gradient(to bottom, transparent, rgba(0, 0, 0, 0.8));
    }

    .cate-hover {
      height: 100%;
      background: linear-gradient(to bottom, transparent, rgba(0, 0, 0));
      top: 0%;
    }

    .category-title-background:hover,
    .category-item:hover>a>.category-title-background {
      height: 100%;
      background: linear-gradient(to bottom, transparent, rgba(0, 0, 0));
      top: 0%;
    }

    .category-title-background:hover~.category-title,
    .category-item>a:hover~.category-title,
    .category-title:hover {
      transition: 0.3s;
      color: #FF8F3F;
    }

    .category-title {
      position: absolute;
      top: 80%;
      left: 10%;
      color: white;
      font-size: 15px;

    }

    .category-title:hover {
      cursor: pointer;
    }

    .slider-top {
      height: 27px;
      width: 100%;
      margin-bottom: 10px;
    }

    .slider-title {
      height: 100%;
      width: 90%;
      float: left;
      font-size: 17px;
    }

    .slider-title>span {
      font-weight: bold;
    }

    .arrowSlider {
      width: 5%;
      float: right;
      margin-right: 5px;
    }

    .prev,
    .next {
      display: inline-block;
      width: 25px;
      height: 25px;
      line-height: 25px;
      text-align: center;
      border: 1px solid lightgray;
      border-radius: 1px;
      transition: 0.3s;
      color: rgba(0, 0, 0, 0.555);
      font-weight: bold;
    }

    .prev:hover,
    .next:hover {
      cursor: pointer;
      background-color: #FF8F3F;
      border-color: transparent;
    }

    /* 랭킹관련 css */
    .rank-container {
      height: 500px;
      width: 100%;
      margin-top: 200px;
    }

    .rank-content {
      margin: 0 auto;
      width: fit-content;
    }

    .rank-list {
      float: left;
    }

    .rank-list:not(:nth-child(1)) {
      margin-left: 20px;
    }

    .rank-top-background {
      background-color: #62A6A9;
      width: 380px;
      height: 170px;
      border-top-left-radius: 5px;
      border-top-right-radius: 5px;
    }

    .rank-category-title {
      height: 35%;
      width: fit-content;
      margin: 0 auto;
      font-size: large;
      font-weight: bold;
      line-height: 310%;
      /* border: 1px solid; */
    }

    .rank-1th-container {
      margin: 0 auto;
      width: 85%;
      height: 65%;
      background-color: white;
      border-top-left-radius: 5px;
      border-top-right-radius: 5px;
      box-shadow: 0px -3px 6px 0px rgba(0, 0, 0, 0.24);
    }

    .rank-1th-col1 {
      font-size: x-large;
      font-weight: bold;
      width: 30%;
      height: 100%;
      padding: 40px 0;
      text-align: center;
    }

    .rank-1th-col2 {
      height: 100%;
      padding: 30px 0;
    }

    .sumPrice {
      font-size: 20px;
      font-weight: bold;
      color: #4D4D4D;
    }

    .free-id {
      border: 1px solid lightgray;
      border-radius: 5px;
      text-align: center;
      width: fit-content;
      color: gray;
      padding: 0 10px;
    }

    .rank-row {
      margin: 0 auto;
      height: 60px;
      width: 350px;
      line-height: 60px;
      border-bottom: 1px solid lightgray;
    }

    .rank-col-1 {
      width: 10%;
      text-align: center;
      font-size: medium;
      color: gray;
    }

    .rank-col-2 {
      width: 45%;
      text-align: center;
      font-size: 17px;
      font-weight: bold;
      color: #4D4D4D;
    }

    .rank-col-3 {
      width: 45%;
      text-align: center;
      color: gray;
    }

    .r-col {
      float: left;
    }
  </style>

</head>

<body>
  <jsp:include page="/WEB-INF/views/common/header.jsp" />
  <script>
    $(document).ready(function () {
      // 슬라이더 부분
      $.each($('.famous-category-list'), function (index, valueOfElement) {
        $(valueOfElement).slick({
          infinite: true,
          slidesToShow: 5,
          slidesToScroll: 1,
          draggable: false,
          prevArrow: $("#arrow" + (index + 1) + "_prev"),
          nextArrow: $("#arrow" + (index + 1) + "_next")
        });
      });
      // 랭크 ajax 부분
      $.ajax({
        type: "post",
        url: "/rankAjax.do",
        data: {
          cateNum: 10
        },
        dataType: "json",
        success: function (response) {
          console.log(response);
          $(".rank-category-title:eq(0)").text("테스트 카테고리");
          $(".sumPrice:eq(0)").empty
          console.log(response["sump"]);
          $(".sumPrice:eq(0)").text(response[0].sumPrice + "원");
          $(".free-id:eq(0)").text(response[0].mId);
        }
      });
      $(".category-title").hover(function (e) {
        $(this).parent().children("a").eq(1).children("div").addClass("cate-hover");
      }, function (e) {
        $(this).parent().children("a").eq(1).children("div").removeClass("cate-hover");
      })
    });
  </script>
  <section class="main-section">
    <div class="slider-container">
      <div class="famous-category-slider">
        <div class="slider-top">
          <div class="slider-title">
            <span>디자인 카테고리</span>에서 인기있어요!
          </div>
          <div class="arrowSlider">
            <span class="prev" id="arrow1_prev">&lt;</span>
            <span class="next" id="arrow1_next">&gt;</span>
          </div>
        </div>
        <div class="famous-category-list">
          <div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-104.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div>
          <div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-101.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">로고·브랜딩</div>
            </a>
          </div><div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-107.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div><div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-101.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div><div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-113.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div><div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-107.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div>
        </div>
      </div>
      <div class="famous-category-slider">
        <div class="slider-top">
          <div class="slider-title">
            <span>디자인 카테고리</span>에서 인기있어요!
          </div>
          <div class="arrowSlider">
            <span class="prev" id="arrow1_prev">&lt;</span>
            <span class="next" id="arrow1_next">&gt;</span>
          </div>
        </div>
        <div class="famous-category-list">
          <div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-104.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div>
          <div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-101.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">로고·브랜딩</div>
            </a>
          </div><div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-107.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div><div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-101.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div><div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-113.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div><div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-107.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div>
        </div>
      </div>
      <div class="famous-category-slider">
        <div class="slider-top">
          <div class="slider-title">
            <span>디자인 카테고리</span>에서 인기있어요!
          </div>
          <div class="arrowSlider">
            <span class="prev" id="arrow1_prev">&lt;</span>
            <span class="next" id="arrow1_next">&gt;</span>
          </div>
        </div>
        <div class="famous-category-list">
          <div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-104.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div>
          <div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-101.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">로고·브랜딩</div>
            </a>
          </div><div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-107.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div><div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-101.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div><div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-113.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div><div class="category-item">
            <a href="/serviceList.do?cNo=10&reqPage=1">
              <img src="img/index/carousel/category-107.png" alt="">
              <div class="category-title-background"></div>
              <div class="category-title">캘리그라피·폰트</div>
            </a>
          </div>
        </div>
      </div>
    </div>
    <!-- 랭크 부분 -->
    <div class="rank-container">
      <div class="rank-description">
        <div>TOP 전문가 랭킹</div>
        <div>19시에서 가장 많이 판매한 인기 전문가 랭킹 입니다.</div>
      </div>
      <div class="rank-content">
        <div class="rank-list">
          <div class="rank-top-background">
            <div class="rank-category-title">IT.프로그래밍</div>
            <div class="rank-1th-container">
              <div class="rank-1th-col1 r-col">1위</div>
              <div class="rank-1th-col2 r-col">
                <div>
                  <div>총 판매 금액</div>
                  <div class="sumPrice"></div>
                  <div class="free-id">
                    프리랜서아이디
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="rank-row">
            <div class="rank-col-1 r-col">2위</div>
            <div class="rank-col-2 r-col">123,123,123원</div>
            <div class="rank-col-3 r-col">프리랜서아이디</div>
          </div>
          <div class="rank-row">
            <div class="rank-col-1 r-col">3위</div>
            <div class="rank-col-2 r-col"></div>
            <div class="rank-col-3 r-col"></div>
          </div>
          <div class="rank-row">
            <div class="rank-col-1 r-col">4위</div>
            <div class="rank-col-2 r-col"></div>
            <div class="rank-col-3 r-col"></div>
          </div>
          <div class="rank-row">
            <div class="rank-col-1">5위</div>
            <div class="rank-col-2"></div>
            <div class="rank-col-3"></div>
          </div>
        </div>
        <div class="rank-list">
          <div class="rank-top-background">
            <div class="rank-category-title">IT.프로그래밍</div>
            <div class="rank-1th-container">
              <div class="rank-1th-col1 r-col">1위</div>
              <div class="rank-1th-col2 r-col">
                <div>
                  <div>총 판매 금액</div>
                  <div class="sumPrice">154,997,581원</div>
                  <div class="free-id">
                    프리랜서아이디
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="rank-row">
            <div class="rank-col-1 r-col">2위</div>
            <div class="rank-col-2 r-col">123,123,123원</div>
            <div class="rank-col-3 r-col">프리랜서아이디</div>
          </div>
          <div class="rank-row">
            <div class="rank-col-1 r-col">3위</div>
            <div class="rank-col-2 r-col"></div>
            <div class="rank-col-3 r-col"></div>
          </div>
          <div class="rank-row">
            <div class="rank-col-1 r-col">4위</div>
            <div class="rank-col-2 r-col"></div>
            <div class="rank-col-3 r-col"></div>
          </div>
          <div class="rank-row">
            <div class="rank-col-1">5위</div>
            <div class="rank-col-2"></div>
            <div class="rank-col-3"></div>
          </div>
        </div>
        <div class="rank-list">
          <div class="rank-top-background">
            <div class="rank-category-title">IT.프로그래밍</div>
            <div class="rank-1th-container">
              <div class="rank-1th-col1 r-col">1위</div>
              <div class="rank-1th-col2 r-col">
                <div>
                  <div>총 판매 금액</div>
                  <div class="sumPrice">154,997,581원</div>
                  <div class="free-id">
                    프리랜서아이디
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="rank-row">
            <div class="rank-col-1 r-col">2위</div>
            <div class="rank-col-2 r-col">123,123,123원</div>
            <div class="rank-col-3 r-col">프리랜서아이디</div>
          </div>
          <div class="rank-row">
            <div class="rank-col-1 r-col">3위</div>
            <div class="rank-col-2 r-col"></div>
            <div class="rank-col-3 r-col"></div>
          </div>
          <div class="rank-row">
            <div class="rank-col-1 r-col">4위</div>
            <div class="rank-col-2 r-col"></div>
            <div class="rank-col-3 r-col"></div>
          </div>
          <div class="rank-row">
            <div class="rank-col-1">5위</div>
            <div class="rank-col-2"></div>
            <div class="rank-col-3"></div>
          </div>
        </div>
      </div>
    </div>
  </section>

  <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

</html>