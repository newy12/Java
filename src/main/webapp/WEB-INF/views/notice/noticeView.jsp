<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <style>
        .page-wrap {
            width: 911px;
            height: 1000px;
            margin: 0 auto;
            margin-top: 45px;
        }

        .text-box {
            height: 332px;
            padding-left: 30px;
            background-color: rgba(224, 224, 224, 0.5);
        }

        .text-box>div:first-child {
            height: 30px;
            padding-top: 30px;
        }

        .text-box>div:last-child {
            padding-top: 70px;
            font-size: 30px;
        }


        .btn {
            background-color: black;
        }

        .board-box {
            height: 40px;
            margin-top: 30px;
        }

        .board-box>div {
            float: right;
            margin-top: -13px;
        }

        .table {
            text-align: left;
            text-indent: 20px;
        }

        .btn-custom {
            width: 120px;
            height: 35px;
            color: white;
            border-radius: 5px;
            background-color: rgb(49, 76, 131);
            border: 1px solid rgb(49, 76, 131);
            font-weight: bold;
        }
        .btn-file{
            color: white;
            border-radius: 5px;
            background-color: rgb(49, 76, 131);
            border: 1px solid rgb(49, 76, 131);
            font-weight: bold;
            font-size: 10pt;
            padding: 5px;
            margin-left: 10px;
        }

        .btn-custom:hover {
            border: 2px solid rgb(49, 76, 131);
            background-color: transparent;
            color: rgb(49, 76, 131);
            font-weight: bold;

        }
        .write-box{
            border-top: 2px solid rgb(49, 76, 131);
        }
        
    </style>


</head>
<body>
	 <div class="page-wrap">
        <div class="text-box">
            <div>홈 > 게시판 > 공지사항</div>
            <div>공지사항</div>
        </div>

        <div class="board-box">
            <br><br>
            <table class="table write-box">
                <tr style="background-color: rgba(224, 224, 224, 0.5);">
                    <th style="width: 100px;" > 제목 </th>
                    <td> 내용내용</td>
                </tr>
                <tr style="background-color: rgba(224, 224, 224, 0.5);" >
                    <th style="height: 40px;"> 첨부파일 </th>
                    <td> 첨부파일 없음 </td>
                </tr>
                <tr>
                   <th>내용</th>
                   <td>
                       
                   </td>
                    
                    
                </tr>
            </table>
            <div>
                <button class="btn btn-custom">목록으로</button>
            </div>
            <div>

            </div>
        </div>
    </div>



</body>
</html>