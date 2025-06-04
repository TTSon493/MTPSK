<%-- 
    Document   : news_detail
    Created on : Apr 25, 2024, 10:34:30 PM
    Author     : huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>News</title>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
        <%--Bootstrap 4.6.2--%>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet"/>
        <link href="css/footer.css" rel="stylesheet"/>
        <link href="css/profile.css" rel="stylesheet"/>
    </head>
    <style>
        .container-news{
            border: 1px solid;
            border-radius: 20px;
            margin: 20px;
        }
        .img-news{
            display: block;
            margin-left: auto;
            margin-right: auto;
            width: 80%;
            height: 50%;
        }
        .border-news-img{
            border: 1px none;
            padding: 30px;
        }
        .title h4{
            margin: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 24px;
            background: #f2f2f2;
            border-radius: 24px;
            margin-bottom: 33px;
            margin-left: auto;
            margin-right: auto;
            width: 77%;
        }
        .news-content {
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 20px;
            line-height: 22px;
            text-align: justify;
            color: #111;
            margin: 0 90px;
            padding-bottom: 30px;
        }
    </style>
    <body>
        <%@include file="components/navBarComponent.jsp" %>
        <div class="container-news">

            <!-- Title news -->
            <div class="text-center font-weight-bolder mr-3" style="padding-top:30px;">
                <h1>Gợi Ý 5 Món Quà 1/6 Cho Bé Trai Yêu Xe Đồ Chơi</h1>
                <h4 class="text-muted">22.04.2024 BTV Trần Diệu</h4>
            </div>

            <!-- News Image -->
            <div class="border-news-img">
                <img class="img-news" src="https://www.mykingdom.com.vn/cdn/shop/articles/qua-1-6-cho-be-trai_thumbnail.jpg?v=1713756618" alt="News Image">
            </div>

            <!-- Nội dung bài viết -->
            <div class="title">
                <h4>Nội dung bài viết</h4>
            </div>

            <!-- Bài viết -->
            <div class="news-content">
                Xe đồ chơi luôn là đam mê của các bé trai, giúp con tìm hiểu về phương tiện giao thông cũng như thỏa mãn đam mê học hỏi về các dòng xe. Nếu ba mẹ đang phân vân việc chọn quà 1/6 cho bé trai giữa các loại xe điều khiển, xe die-cast, xe xe mô hình… thì tham khảo ngay 5 gợi ý của Mykingdom.
            </div>

        </div>
        <%@include file="components/footerComponent.jsp" %>
    </body>
</html>
