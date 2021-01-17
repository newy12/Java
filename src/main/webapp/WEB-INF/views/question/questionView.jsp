<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    </style>
</head>

<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />

    <section class="qna-section">
        <div class="qna-container">
            <div class="qna-top">
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
                            <th>제목</th>
                            <th><span>답변 : ${question.QTitle}</span></th>
                        </tr>
                        <c:if test="${empty param.answer || param.answer == false}">
                            <tr>
                                <th>첨부파일</th>
                                <th><a href="#">${question.filePath}</a></th>
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
                    <c:if test="${question.MNo == loginMember.MNo}">
                        <a href="/questionFrm.do?q_No=${question.QNo}" class="btn-deepblue">수정하기</a>
                    </c:if>
                    <a href="/qna.do">목록으로</a>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

</html>