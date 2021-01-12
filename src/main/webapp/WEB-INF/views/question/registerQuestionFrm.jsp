<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        .qna-bottom {
            margin: 0 auto;
            width: fit-content;
        }

    </style>
</head>

<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <script>
        $(document).ready(function () {
            $("form").on("submit", function (e) {
                let $qTitle = $("#qTitle");
                if($qTitle.val() == '' || $("#qContent").val()){
                    alert("빈칸없이 입력해주세요.")
                    e.preventDefault();
                }
            });
        });
    </script>

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
            <form action="registerQuestion.do" method="POST" enctype="multipart/form-data">
                <div class="qna-center">
                    <table>
                        <thead>
                            <tr>
                                <th>제목</th>
                                <th><input type="text" name="qTitle" id="qTitle" placeholder="내용을 입력해주세요."></th>
                            </tr>
                            <tr>
                                <th>첨부파일</th>
                                <th><input type="file" name="file" id="file" placeholder="내용을 입력해주세요."></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td colspan="2">
                                    <textarea name="qContent" id="qContent" cols="30" rows="10"></textarea>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>

                        </tfoot>
                    </table>
                </div>
                <div class="qna-bottom">
                    <div class="submit">
                        <input type="submit" value="등록하기">
                        <a href="/qna.do">목록으로</a>
                    </div>
                </div>
            </form>
        </div>
    </section>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

</html>