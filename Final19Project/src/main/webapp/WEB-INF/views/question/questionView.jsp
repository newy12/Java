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

        .btn-quest {
            display: inline-block;
            outline: none;
            border: none;
            width: 70px;
            height: 25px;
            line-height: 25px;
            text-align: center;
            text-decoration: none;
            color: white;
        }

        .btn-quest:hover {
            color: white;
            text-decoration: none;
        }

        .btn-white {
            display: inline-block;
            outline: none;
            width: 70px;
            height: 25px;
            line-height: 25px;
            text-align: center;
            background-color: white;
            border: 2px solid #314C83;
            border-radius: 5px;
            text-decoration: none;
            color: #314C83;
        }
    </style>
</head>

<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <c:if test="${loginMember.MGrade != 0}">
        <c:if test="${question.secretStatus == 1 and loginMember.MNo != question.MNo}">
            <script>
                alert("권한이 없습니다.");
                location.href = "/qna.do?page=1";
            </script>
        </c:if>
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
            <div class="qna-center">
                <table>
                    <thead>
                        <tr>
                            <th class="qna-col1">제목</th>
                            <th class="qna-col2"><span>${question.QTitle}</span></th>
                        </tr>
                        <c:if test="${empty param.answer || param.answer == false}">
                            <tr>
                                <th class="qna-col1">첨부파일</th>
                                <th class="qna-col2"><a
                                        href="/questionDown.do?fileName=${question.filePath}">${question.filePath}</a>
                                </th>
                            </tr>
                        </c:if>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="2">
                                <textarea name="qContent" id="qContent" cols="30" rows="10" disabled><c:if test="${empty param.answer || param.answer == false}">${question.QContent}</c:if><c:if test="${param.answer == true}">${question.answerContent}</c:if>
                                </textarea>
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>

                    </tfoot>
                </table>
            </div>
            <div class="qna-bottom">
                <div class="submit">
                    <c:if test="${question.MNo == loginMember.MNo and empty param.answer}">
                        <a href="/questionFrm.do?q_No=${question.QNo}" class="btn-deepblue btn-quest">수정하기</a>
                        <form action="deleteQuestion.do" method="post" style="display: inline-block;">
                            <input type="hidden" name="qNo" value="${param.qNo}">
                            <input type="hidden" name="loc" id="delete_loc" value="/qna.do">
                            <button class="btn-deepblue btn-quest">삭제하기</button>
                        </form>
                    </c:if>
                    <a href="/qna.do" class="btn-white">목록으로</a>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

</html>