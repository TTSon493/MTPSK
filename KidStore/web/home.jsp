<%-- 
    Document   : home
    Created on : Apr 11, 2024, 9:49:30 PM
    Author     : TUF
--%>
<%@page import="java.util.Base64"%>
<%@ page import="java.text.DecimalFormat" %>
<%@page import="DTO.Account"%>
<%@page import="DTO.Category"%>
<%@page import="DTO.Toy"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
        <%--Bootstrap 4.6.2--%>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet"/>
        <link href="css/footer.css" rel="stylesheet"/>
        <link href="css/profile.css" rel="stylesheet"/>
    </head>
    <style>
        .carousel-item img{
            width: 100%;
            height: 500px;
            object-fit: cover;
        }
        .container{
            max-width: 100%;
        </style>
        <body>
            <!-- Navbar-->
            <%@include file="components/navBarComponent.jsp" %>
            <!-- Header-->
            <header>

                    <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
                        <div class="container px-4 px-lg-5 my-5" style="position: absolute; top: 35%; left: 50%; transform: translate(-50%, -50%); z-index: 3;">
                            <div class="text-center text-primary">
                                <h1 class="display-4 fw-bolder" style="font-family: cursive">Kids Store</h1>
                                <p class="lead fw-normal text-dark-50 mb-0" style="font-family: Monospace">Yêu thương mong con tỏa sáng</p>
                            </div>
                        </div>
                        <div class="carousel-inner" >
                            <div class="carousel-item active img-fluid"  data-bs-interval="1500">
                                <img src="img/background.png" class="d-block w-100" alt="background1">
                            </div>
                            <div class="carousel-item img-fluid" data-bs-interval="2500">
                                <img src="img/background-1.png" class="d-block w-100" alt="background2">
                            </div>
                            <div class="carousel-item img-fluid" data-bs-interval="2500">
                                <img src="img/background-2.png" class="d-block w-100" alt="background3">
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>

                </header>
                <% Account a = (Account) session.getAttribute("acc");
                    int role = 0;
                    if (a != null) {
                        role = a.getRoleId();
                    }
                %>
                <!-- Section-->
                <section class="py-5">
                    <div class="container px-4 px-lg-5 mt-5">
                        <div class="row">

                            <!-- Section Left -->
                            <div class="col-md-3 mb-5">

                                <%
                                    ArrayList<Category> categoryList = (ArrayList<Category>) session.getAttribute("CATEGORY_LIST");
                                    if (categoryList != null) {
                                %>

                                <h3>Danh mục</h3>
                                <ul class="list-group category_block">
                                    <%
                                        for (Category category : categoryList) {
                                    %>

                                    <li class="list-group-item text-black"><a href="CategoryController?categoryId=<%=category.getCategoryId()%>"><%=category.getCategoryName()%></a></li>
                                        <%}
                                        } else {
                                        %>
                                    <p>Không có loại đồ chơi nào</p>
                                    <%
                                        }
                                    %>
                                </ul>
                            </div>

                            <!-- Section Right -->             
                            <div class="col-md-9">
                                <h3>Danh mục sản phẩm</h3>

                                <%-- Danh sách tất cả đồ chơi --%>
                                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 justify-content-center">

                                    <%
                                        ArrayList<Toy> toyList = (ArrayList<Toy>) session.getAttribute("TOY_LIST");
                                        ArrayList<Toy> toyCategoryList = (ArrayList<Toy>) request.getAttribute("TOY_CATEGORY_LIST");
                                        if (toyList != null && toyCategoryList == null) {
                                            // Define the number of items to display per page
                                            int itemsPerPage = 9;

                                            // Get the current page number from the request parameter, default to 1 if not provided
                                            int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;

                                            // Calculate the start and end index of items to display on the current page
                                            int startIndex = (currentPage - 1) * itemsPerPage;
                                            int endIndex = Math.min(startIndex + itemsPerPage, toyList.size());

                                            // Iterate over the subset of toyList based on the current page
                                            for (int i = startIndex; i < endIndex; i++) {
                                                Toy toy = toyList.get(i);
                                                String base64Image = Base64.getEncoder().encodeToString(toy.getImage());
 
                                    %>
                                    <div class="col mb-5">

                                        <div class="card h-100">

                                            <!--  Sale badge -->
                                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">
                                                Sale off
                                            </div>

                                            <!-- Product image -->
                                            <a href="MainController?btAction=Detail&toyId=<%=toy.getToyId()%>"><img class="card-img-top" src="data:image/jpeg;base64,<%= base64Image%>" alt="Toy Image"></a>


                                            <div class="card-body">
                                                <div class="text-center">

                                                    <!-- Product name -->
                                                    <h5 class="fw-bolder"><a href="MainController?btAction=Detail&toyId=<%=toy.getToyId()%>" style="text-decoration: none; color: black;"><%=toy.getToyName()%></a></h5>                                             </div>
                                        </div>
                                        <ul class="text-center list-group-item">
                                            <!-- Product reviews -->
                                            <div class="d-flex justify-content-center small text-warning mb-2">
                                                <div class="bi-star-fill"></div>
                                                <div class="bi-star-fill"></div>
                                                <div class="bi-star-fill"></div>
                                                <div class="bi-star-fill"></div>
                                                <div class="bi-star-fill"></div>
                                                <div class="bi-star-fill"></div>
                                            </div>
                                            <li class="text-center list-group-item">
                                                <!-- Format VND -->
                                                <%
                                                    double price = toy.getPrice();
                                                    double discount = toy.getDiscount();
                                                    double discountedPrice = price * discount;

                                                    DecimalFormat vnCurrencyFormat = new DecimalFormat("###,### VNĐ");

                                                    // Định dạng giá giảm giá thành tiền tệ Việt Nam
                                                    String formattedPrice = vnCurrencyFormat.format(discountedPrice);
                                                    String priceBefore = vnCurrencyFormat.format(price);
                                                %>
                                                <!-- Discount -->
                                                <span class="text-muted text-decoration-line-through"><%=priceBefore%></span><br/> 
                                                <!-- Price -->
                                                <span class="text-black"><%= formattedPrice%></span></li>
                                        </ul>
                                        <!-- Product actions -->
                                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                            <div class="text-center">
                                                <a class="btn btn-outline-dark mt-auto" href="MainController?btAction=Sell&toyId=<%=toy.getToyId()%>">
                                                    Thêm vào giỏ hàng
                                                </a>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <%}
                                %>

                                <!-- Pagination Controls -->

                            </div>
                            <div class="pagination">
                                <% if (currentPage > 1) {%>
                                <a href="?page=<%= currentPage - 1%>">&laquo; Previous</a>
                                <% } %>

                                <% int totalPages = (int) Math.ceil((double) toyList.size() / itemsPerPage); %>
                                <% for (int i = 1; i <= totalPages; i++) { %>
                                <% if (i == currentPage) {%>
                                <span class="current-page"><%= i%></span>
                                <% } else {%>
                                <a href="?page=<%= i%>"><%= i%></a>
                                <% } %>
                                <% } %>

                                <% if (currentPage < totalPages) {%>
                                <a href="?page=<%= currentPage + 1%>">Next &raquo;</a>
                                <% }%>
                            </div>

                            <%-- Danh sách đồ chơi theo CategoryId --%>
                            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 justify-content-center">

                                <%} else if (toyCategoryList != null && !toyCategoryList.isEmpty()) {
                                    // Define the number of items to display per page
                                    int itemsPerPage = 9;

                                    // Get the current page number from the request parameter, default to 1 if not provided
                                    int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;

                                    // Calculate the start and end index of items to display on the current page
                                    int startIndex = (currentPage - 1) * itemsPerPage;
                                    int endIndex = Math.min(startIndex + itemsPerPage, toyCategoryList.size());

                                    // Iterate over the subset of toyList based on the current page
                                    for (int i = startIndex; i < endIndex; i++) {
                                        Toy toy = toyCategoryList.get(i);
                                        String base64Image = Base64.getEncoder().encodeToString(toy.getImage());
                                %>
                                <div class="col mb-5">

                                    <div class="card h-100">

                                        <!--  Sale badge -->
                                        <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">
                                                Sale off
                                            </div>

                                            <!-- Product image -->
                                            <a href="MainController?btAction=Detail&toyId=<%=toy.getToyId()%>">
                                                <img class="card-img-top" src="data:image/jpeg;base64,<%= base64Image%>" alt="Toy Image"></a>


                                            <div class="card-body">
                                                <div class="text-center">

                                                    <!-- Product name -->
                                                    <h5 class="fw-bolder">
                                                        <a href="MainController?btAction=Detail&toyId=<%=toy.getToyId()%>" style="text-decoration: none; color: black;"><%=toy.getToyName()%></a></h5> 
                                            </div>
                                        </div>
                                        <ul class="text-center list-group-item">
                                            <!-- Product reviews -->
                                            <div class="d-flex justify-content-center small text-warning mb-2">
                                                <div class="bi-star-fill"></div>
                                                <div class="bi-star-fill"></div>
                                                <div class="bi-star-fill"></div>
                                                <div class="bi-star-fill"></div>
                                                <div class="bi-star-fill"></div>
                                                <div class="bi-star-fill"></div>
                                            </div>
                                            <li class="text-center list-group-item">
                                                <!-- Format VND -->
                                                <%
                                                    double price = toy.getPrice();
                                                    double discount = toy.getDiscount();
                                                    double discountedPrice = price * discount;

                                                    DecimalFormat vnCurrencyFormat = new DecimalFormat("###,### VNĐ");

                                                    // Định dạng giá giảm giá thành tiền tệ Việt Nam
                                                    String formattedPrice = vnCurrencyFormat.format(discountedPrice);
                                                    String priceBefore = vnCurrencyFormat.format(price);
                                                %>
                                                <!-- Discount -->
                                                <span class="text-muted text-decoration-line-through"><%=priceBefore%></span><br/> 
                                                <!-- Price -->
                                                <span class="text-black"><%= formattedPrice%></span></li>
                                        </ul>
                                        <!-- Product actions -->
                                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                            <div class="text-center">
                                                <a class="btn btn-outline-dark mt-auto" href="MainController?btAction=Sell&toyId=<%=toy.getToyId()%>">
                                                    Thêm vào giỏ hàng
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%}
                                %>
                            </div>
                            <!-- Pagination Controls -->
                            <div class="pagination">
                                <% if (currentPage > 1) {%>
                                <a href="?page=<%= currentPage - 1%>">&laquo; Previous</a>
                                <% } %>

                                <% int totalPages = (int) Math.ceil((double) toyList.size() / itemsPerPage); %>
                                <% for (int i = 1; i <= totalPages; i++) { %>
                                <% if (i == currentPage) {%>
                                <span class="current-page"><%= i%></span>
                                <% } else {%>
                                <a href="?page=<%= i%>"><%= i%></a>
                                <% } %>
                                <% } %>

                                <% if (currentPage < totalPages) {%>
                                <a href="?page=<%= currentPage + 1%>">Next &raquo;</a>
                                <% } %>
                            </div>

                            <%} else {
                            %>
                            <p>Không có danh sách của loại đồ chơi này</p>
                            <%
                                }
                            %>
                        </div>   
                    </div>
                </div>
            </div>
        </section>   

        <!-- Footer-->
        <%@include file="components/footerComponent.jsp" %>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
