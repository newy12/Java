<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        .qna-section {
            margin: 0 auto;
            width: 1200px;
        }

        .qna-container {
            margin: 0 auto;
            width: 90%;
        }

        .qna-top {
            margin: 50px auto;
            width: 90%;
            height: 300px;
            padding: 1px;
            background-color: rgb(224, 224, 224, 0.5);
        }

        .qna-top>.title {
            width: 100%;
            margin-top: 60px;
            margin-left: 50px;

        }

        .qna-top>.title>.title-main {
            font-size: 30px;
        }

        .qna-center {
            margin: 0 auto;
            width: fit-content;
        }

        .qna-center table {
            border-top: 2px solid gray;
            border-bottom: 2px solid gray;
        }

        .qna-center table td {
            text-align: center;
        }

        .qna-center table tr {
            border-top: 1px solid lightgray;
        }

        .qna-center table>tbody tr:hover {
            background-color: rgba(211, 211, 211, 0.5);
        }

        .qna-center table>thead {
            background-color: rgb(235, 235, 235);
            font-size: medium;
            color: #282828;
        }

        .qna-center table>thead th {
            padding: 4px 0;
            color: #282828;
            text-align: center;
        }

        .qna-center table>thead th:nth-child(2) {
            width: 100px;
        }

        .qna-center table>thead th:nth-child(3) {
            width: 520px;
        }

        .qna-center table>thead th:nth-child(4) {
            width: 200px;
        }

        .qna-center table>thead th:nth-child(5) {
            width: 150px;
        }

        .qna-center table>tbody tr td {
            padding: 5px 0;
            font-size: medium;
        }

        .qna-bottom {
            margin: 0 auto;
            width: 90%;
            text-align: center;
        }

        .qna-bottom>.paging {
            display: inline-block;
        }

        .btn-write {
            display: inline-block;
            float: right;
        }

        .btn-answer {
            width: 50px;
            height: 20px;
            line-height: 20px;
            margin-left: 5px;
            outline: none;
            background-color: white;
            color: #314C83;
            border-radius: 5px;
            border: 2px solid #314C83;
            display: inline-block;
            text-decoration: none;
            font-size: small;
        }

        .btn-answer:hover {
            text-decoration: none;
            color: #314C83;
        }

        .btn-update {
            width: 50px;
            height: 20px;
            line-height: 20px;
            margin-left: 5px;
            outline: none;
            background-color: #314C83;
            color: white;
            border-radius: 5px;
            border: 2px solid #314C83;
            display: inline-block;
            text-decoration: none;
            font-size: small;
        }

        .background-screen {
            display: none;
            position: absolute;
            width: 100vw;
            height: 100vh;
            background-color: black;
            opacity: 0.2;
        }
        .qna-view-container,
        .qna-view-container-answer{
            display: none;
            position: absolute;
            background-color: white;
            width: 550px;
            height: 600px;
            border: 2px solid #314C83;
            box-shadow: 2px 2px 12px 1px gray;
            border-radius: 5px;
            left: calc(50% - 275px);
            top: calc(50% - 350px);
            /* box-sizing: border-box; */
        }
        .qna-view-container>.qna-view-main{
            width: 90%;
            height: 90%;
            margin: 0 auto;
            margin-top: 30px;
        }
        .qna-view-top{
            width: 100%;
            height: 25px;
            background-color: #314C83;
        }
        .qna-view-top>.qna-view-top-title{
            margin-top: 2px;
            display: inline-block;
            width: 100%;
            text-align: center;
            color: white;
            margin: 0;
        }
        .modal-exit{
            float: right;
            width: 30px;
            text-align: center;
            color: white;
        }
        .modal-exit:hover{
            cursor: pointer;
        }
        .qna-view-title,
        .qna-view-file{
            width: 100%;
            height: 5%;
            border: 1px solid;
            margin: 10px 0;
        }
        .qna-view-content{
            width: 100%;
            height: 75%;
            border: 1px solid;
            margin: 10px 0;
        }
    </style>
</head>

<body>
    <div class="background-screen"></div>
    <jsp:include page="/WEB-INF/views/admin/adminMainpage.jsp" />
    <script>
        $(document).ready(function () {

            // 체크박스 토글기능
            function toggleCheckBox($target) {
                if ($target.prop("checked") == true)
                    $target.prop("checked", false);
                else
                    $target.prop("checked", true);
            }
            $(".tr").on("click", function (e) {
                console.log("Asd");
                toggleCheckBox($(this).children("td:eq(0)").children("input:checkbox"));
            });
            $("input:checkbox").on("click", function (e) {
                e.stopPropagation();
            });

            //모달창 관련
            $(".background-screen").on("click",function (e) {
                $(this).hide();
                $(".qna-view-container").hide();
                $(".qna-view-container-answer").hide();
            });
            $(".modal-exit").on("click",function (e) {
                $(".qna-view-container").hide();
                $(".qna-view-container-answer").hide();
                $(".background-screen").hide();
            });
            $(".list-title").on("click", function (e) {
                //ajax
                $.ajax({
                    type: "post",
                    url: "/questionViewAjax.do",
                    data: {qNo:$(this).siblings("input:hidden").val()},
                    dataType: "json",
                    success: function (response) {
                        $(".qna-view-title").empty();
                        $(".qna-view-title").append("제목: "+response.qTitle);
                        $(".qna-view-file").empty();
                        $(".qna-view-file").append("파일: "+response.filePath);
                        $(".qna-view-content").empty();
                        $(".qna-view-content").append(response.qContent);
                    }
                });
                $(".background-screen").show();
                $(".qna-view-container").show();
                e.stopPropagation();
            });
            $(".answer-list-title").on("click", function (e) {
                //ajax
                $.ajax({
                    type: "post",
                    url: "/questionViewAjax.do",
                    data: {qNo:$(this).siblings("input:hidden").val()},
                    dataType: "json",
                    success: function (response) {
                        $(".qna-view-title").empty();
                        $(".qna-view-title").append("답변: "+response.qTitle);
                        $(".qna-view-file").empty();
                        $(".qna-view-file").hide();
                        $(".qna-view-content").empty();
                        $(".qna-view-content").append(response.answerContent);
                    }
                });
                $(".background-screen").show();
                $(".qna-view-container").show();
                e.stopPropagation();
            });
        });
    </script>
    
    <div class="qna-view-container">
        <div class="qna-view-top">
            <div class="qna-view-top-title">
                보기
                <div class="modal-exit">
                    x
                </div>
            </div>
        </div>
        <div class="qna-view-main">
            <div class="qna-view-title">
            </div>
            <div class="qna-view-file">
            </div>
            <div class="qna-view-content">
            </div>
        </div>
    </div>
    <div class="qna-view-container-answer">
        <div class="qna-view-top">
            <div class="qna-view-top-title">
                답변 작성
                <div class="modal-exit">
                    x
                </div>
            </div>
        </div>
        <div class="qna-view-main">
            <div class="qna-view-title">
            </div>
            <div class="qna-view-file">
            </div>
            <div class="qna-view-content">
            </div>
        </div>
    </div>
    <section class="qna-section">
        <div class="qna-container">
            <select name="list_num">
                <option value="10">10개</option>
                <option value="20">20개</option>
                <option value="30">30개</option>
            </select>
            <div class="qna-center">
                <table>
                    <thead>
                        <tr>
                            <th></th>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성날짜</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="qList" items="${questionList}">
                            <tr class="tr">
                                <td><input type="checkbox" name="qNo"></td>
                                <td>${qList.QNo}</td>
                                <td>
                                    <input type="hidden" value="${qList.QNo}">
                                    <a href="#" class="list-title">${qList.QTitle}</a>
                                    <c:if test="${qList.answerStatus == 0}">
                                        <a href="questionFrm.do?answerNo=${qList.QNo}" class="btn-answer">답변</a>
                                    </c:if>
                                    <c:if test="${qList.secretStatus == 1}">
                                        <span>&#128274;</span>
                                    </c:if>
                                </td>
                                <td>${qList.MId}</td>
                                <td>${qList.writeDate}</td>
                            </tr>
                            <c:if test="${qList.answerStatus == 1}">
                                <tr style="border-top: none;">
                                    <td></td>
                                    <td></td>
                                    <td>
                                        <input type="hidden" value="${qList.QNo}">
                                        <a href="#" class="answer-list-title">&#10551; 답변입니다.</a>
                                        <a href="questionFrm.do?answerNo=${qList.QNo}" class="btn-update">수정</a>
                                    </td>
                                    <td>
                                        관리자
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                    <tfoot>

                    </tfoot>
                </table>
            </div>
            <div class="qna-bottom">
                <div class="paging">
                    <c:forEach var="i" begin="${not empty param.page ?  begin : 1}" end="${end}" step="1">
                        <a
                            href="?page=${i}${not empty param.qna_type ? '&qna_type='+=param.qna_type : ''}${not empty param.qna_keyword ? '&qna_keyword='+=param.qna_keyword : ''}">${i}</a>
                    </c:forEach>
                </div>
                <div class="qna-search">
                    <form action="" method="get">
                        <select name="qna_type">
                            <option value="1">제목</option>
                            <option value="2">작성자</option>
                        </select>
                        <input type="text" name="qna_keyword">
                        <input type="submit" value="검색">
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>

</html>