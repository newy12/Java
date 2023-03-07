<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>19시 :: 문의사항</title>
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

        .qna-section {
            font-family: 'Arita-dotum-Medium';
            margin: 0 auto;
            width: 1200px;
        }

        .qna-container {
            margin: 0 auto;
            width: 90%;
        }
        
        .qna-top {
            position: relative;
            margin: 50px auto;
            width: 90%;
            height: 300px;
            padding: 1px;
            background-color: rgb(224, 224, 224, 0.5);
        }
        #qna-img{
            position: absolute;
            width:100%;
            height: 300px;
            object-fit: cover;
            z-index: -1;
        }

        .qna-top>.title {
            width: 100%;
            margin-top: 60px;
            margin-left: 50px;

        }

        .qna-top>.title>.title-main {
            font-size: 30px;
            border-left: 4px solid #314C83;
            padding-left: 10px;
            margin-bottom: 10px;
        }

        .qna-center {
            margin: 0 auto;
            width: 90%;
        }

        .qna-center>table {
            width: 100%;
            height: 600px;
            border-top: 2px solid #314C83;
            border-bottom: 2px solid #314C83;
        }

        .qna-center>table>thead {
            background-color: rgb(224, 224, 224, 0.5);
        }

        .qna-center .qna-col1 {
            width: 100px;
            height: 40px;
            text-align: center;
            font-size: 15px;
        }

        .qna-center .qna-col2>input {
            width: 700px;
            height: 30px;
            border-radius: 4px;
            outline: none;
            border: 1px solid lightgray;
        }

        .qna-bottom {
            margin: 10px auto;
            width: fit-content;
        }

        #qContent {
            width: 100%;
            height: 95%;
            resize: none;
        }

        .btn-answer {
            width: 80px;
            height: 35px;
            line-height: 35px;
            margin-left: 5px;
            outline: none;
            background-color: white;
            color: #314C83;
            border-radius: 5px;
            border: 2px solid #314C83;
            display: inline-block;
            text-decoration: none;
            font-size: small;
            text-align: center;
        }

        .btn-answer:hover {
            text-decoration: none;
            color: #314C83;
        }

        .btn-update {
            width: 80px;
            height: 35px;
            line-height: 35px;
            margin-left: 5px;
            outline: none;
            background-color: #314C83;
            color: white;
            border-radius: 5px;
            display: inline-block;
            border: none;
            text-decoration: none;
            font-size: small;
        }

        .btn-update:hover {
            color: white;
            text-decoration: none;
        }
    </style>
</head>

<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <script>
        $(document).ready(function () {
            $("#qna-reg-form").on("submit", function (e) {
                let $qTitle = $("#qTitle");
                if ($qTitle.val() == '' || $("#qContent").val() == '') {
                    alert("빈칸없이 입력해주세요.")
                    e.preventDefault();
                }
            });
        });
    </script>
    <c:if test="${empty loginMember}">
        <script>
            alert("권한이 없습니다.");
            location.href="/";
        </script>
    </c:if>
    <section class="qna-section">
        <div class="qna-container">
            <div class="qna-top">
                <img id="qna-img" src="img/question/6.jpg" alt="">
                <div class="title">
                    <div class="title-main">
                        문의사항
                    </div>
                    <div class="title-sub">
                        일구시에게 궁금한 점들을 물어보세요!
                    </div>
                </div>
            </div>
            <form id="qna-reg-form" action="registerQuestion.do" method="POST" enctype="multipart/form-data">
                <c:if test="${not empty param.answerNo}">
                    <input type="hidden" name="answer_No" value="${param.answerNo}">
                </c:if>
                <c:if test="${not empty question}">
                    <input type="hidden" name="q_No" value="${question.QNo}">
                </c:if>
                <div class="qna-center">
                    <table>
                        <thead>
                            <c:if test="${not empty param.answerNo}">
                                <tr>
                                    <th class="qna-col1">제목</th>
                                    <th class="qna-col2"><input type="text" name="qTitle" id="qTitle"
                                            value="답변입니다." disabled></th>
                                    <th></th>
                                </tr>
                            </c:if>
                            <c:if test="${empty param.answerNo}">
                                <tr>
                                    <th class="qna-col1">제목</th>
                                    <th class="qna-col2"><input type="text" name="qTitle" id="qTitle"
                                            placeholder="내용을 입력해주세요."
                                            value="${not empty question ? question.QTitle : ''}"  maxlength="33"></th>
                                    <th></th>
                                </tr>
                                <tr>
                                    <th class="qna-col1">첨부파일</th>
                                    <th class="qna-col2"><input type="file" name="file" id="file"></th>
                                    <th>
                                        <input type="checkbox" name="isPrivacy" id="isPrivacy">
                                        <label for="isPrivacy"> 비밀글 여부</label>
                                    </th>
                                </tr>
                            </c:if>
                        </thead>
                        <tbody>
                            <tr>
                                <td colspan="3">
                                    <textarea name="qContent" id="qContent" cols="30" rows="10"
                                        r>${(not empty question and empty param.answerNo) ? question.QContent : ''}${not empty param.answerNo ? question.answerContent : ''}</textarea>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>

                        </tfoot>
                    </table>
                </div>
                <div class="qna-bottom">
                    <div class="submit">

                        <input type="submit" class="btn-update" value="등록하기">
                        <a href="/qna.do" class="btn-answer">목록으로</a>
                    </div>
                </div>
            </form>
        </div>
    </section>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

</html>