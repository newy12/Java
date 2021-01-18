<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <title>Insert title here</title>

    <style>
                @font-face {
	font-family: 'Arita-dotum-Medium';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Arita-dotum-Medium.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
        body{
            margin: 0;
        }
        .header-container{
            width: 100%;
            height: 80px;
            background-color: #314C83;
        }
        header{
            width: 1200px;
            height: 100%;
            margin: 0 auto;
            
        }
        .header-left{
            width: 13%;
        }
        .header-left img{
            width: 100%;
        }
    </style>
</head>
    <div class="header-container">
        <header>
            <div class="header-left">
                <a href="/"><img src="/img/logo/logo_navy.png" alt=""></a>
            </div>
        </header>
    </div>


</html>