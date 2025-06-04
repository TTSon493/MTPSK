<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Base64"%>
<%@page import="java.util.HashMap"%>
<%@page import="DTO.Toy"%>
<%@page import="DTO.Image"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>detail</title>
        <!-- Favicon-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
        <%--Bootstrap 4.6.2--%>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet"/>
        <link href="css/footer.css" rel="stylesheet"/>
    </head>
    <style>

        .product-container {
            padding: 30px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-top: 70px;
            margin-bottom: 130px;
        }

        .carousel-item img {
            width: 100%;
            height: 500px; 
            object-fit: cover; 
        }

        .product-details {
            padding-left: 20px;
        }

        .product-name {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .product-price {
            font-size: 18px;
            color: #333;
            margin-bottom: 20px;
        }

        .quantity-input {
            width: 80px;
            text-align: center;
        }

        .add-to-cart-button {
            display: block;
            width: 100%;
            max-width: 200px;
            margin: 0 auto;
        }
        .rating {
            font-size: 24px;
            color: #ffcc33;
        }
    </style>
    <body>
        <%@include file="components/navBarComponent.jsp" %>
        <% Toy toy = (Toy) session.getAttribute("toy");%>
        <div class="container">
            <div class="product-container">
                <div class="row">                   
                    <div class="col-md-6">
                        <div id="carouselExample" class="carousel slide">
                            <div class="carousel-inner">
                                <% ArrayList<Image> imageList = (ArrayList<Image>) session.getAttribute("IMAGE_LIST");
                                    for (Image i : imageList) {
                                        if (i != null) {
                                            if (i.isMain()) {
                                                String base64Image = Base64.getEncoder().encodeToString(i.getImage());

                                %>
                                <div class="carousel-item active">
                                    <img src="data:image/jpeg;base64,<%= base64Image%>" alt="Toy Image" class="d-block w-100">
                                </div>
                                <%} else {
                                    String base64Image = Base64.getEncoder().encodeToString(i.getImage());%>   
                                <div class="carousel-item">
                                    <img src="data:image/jpeg;base64,<%= base64Image%>" alt="Toy Image" class="d-block w-100">
                                </div>
                                <%}
                                        }
                                    }%>
                            </div>

                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>                  
                    </div>
                    <div class="col-md-6">
                        <form action="CartController">
                            <div class="product-details">
                                <%
                                    double price = toy.getPrice();
                                    double discount = toy.getDiscount();
                                    double discountedPrice = price * discount;

                                    DecimalFormat vnCurrencyFormat = new DecimalFormat("###,### VNĐ");

                                    // Định dạng giá giảm giá thành tiền tệ Việt Nam
                                    String formattedPrice = vnCurrencyFormat.format(discountedPrice);
                                    String priceBefore = vnCurrencyFormat.format(price);
                                %>

                                <input type="hidden" name="toyId" value="<%=toy.getToyId()%>">
                                <div class="product-name"><%=toy.getToyName()%></div>
                                <div class="rating">
                                    &#9733; &#9733; &#9733; &#9733; &#9733;
                                </div>
<!--                                <div class="product-price"><%=toy.getPrice()%></div>-->
                                <div class="product-price"><span class="text-muted text-decoration-line-through"><%=priceBefore%></span> <span class="text-black"><%= formattedPrice%></span></div>
                                <div class="product-name"><p>&#10159; Bảo hành chính hãng</p></div>
                                <div class="product-name"><p>&#10159; Áp dụng nhiều voucher giảm giá</p></div>
                                <div class="product-name"><p>&#10159; Hoàn trả khi sản phẩm có lỗi</p></div>

                                <div class="quantity">
                                    
                                    <input type="number" id="quantity" class="quantity-input" name="InputValue" min="1" max="<%=toy.getQuantity()%>" value="1">
                                    <div><label for="quantity">Số Lượng: </label><%=toy.getQuantity()%> sản phẩm có sẵn</div>
                                    
                                </div>                                  
                                <div class="product-name">ID: <%=toy.getToyId()%></div>
                                <div class="product-name">Danh mục: <%=toy.getName_category()%></div>
                                <a href="MainController?btAction=Sell&toyId=<%=toy.getToyId()%>">
                                    <button type="submit"style="margin-top: 50px;" class="btn btn-success btn-block">Thêm vào giỏ hàng</button>
                                </a>
                            </div>                       
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="product-details">
                        <div class="product-name">Thông tin chi tiết</div>

                    </div>
                    <div class="product-details">
                        <div class="col-md-6">
                            <%=toy.getDescription()%>
                        </div>                                                                
                    </div>
                </div>

            </div>
        </div>
        <%@include file="components/footerComponent.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
