<%-- 
    Document   : admin_dashboard
    Created on : Apr 29, 2024, 6:48:39 PM
    Author     : minhn
--%>

<%@page import="java.util.Base64"%>
<%@page import="DTO.OrderDetail"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DTO.OrderDashboard"%>
<%@page import="DTO.OrderSold"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="DTO.OrderHistory"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="css/admin.css">
        <!-- ICON CSS-->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
            integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <title>AdminHub</title>
        <style>
            .popup-form {
                display: none;
                width: 1200px;
                height: 700px;
                position: fixed;
                top: 2%;
                left: 13%;
                background-color: whitesmoke;
                padding: 20px;
                border: 2px solid black;
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                z-index: 3000;
                overflow-y: auto;
            }

            .close{
                float: right;
                font-size: 1.5rem;
                font-weight: 700;
                line-height: 1;
                color: #000;
                text-shadow: 0 1px 0 #fff;
                opacity: .5;
            }
            /* CSS */
            .button-13 {
                background-color: #fff;
                border: 1px solid #d5d9d9;
                border-radius: 8px;
                box-shadow: rgba(213, 217, 217, .5) 0 2px 5px 0;
                box-sizing: border-box;
                color: #0f1111;
                cursor: pointer;
                display: inline-block;
                font-family: "Amazon Ember",sans-serif;
                font-size: 13px;
                line-height: 29px;
                padding: 0 10px 0 11px;
                position: relative;
                text-align: center;
                text-decoration: none;
                user-select: none;
                -webkit-user-select: none;
                touch-action: manipulation;
                vertical-align: middle;
                width: 70px;
            }

            .button-13:hover {
                background-color: #f7fafa;
            }

            .button-13:focus {
                border-color: #008296;
                box-shadow: rgba(213, 217, 217, .5) 0 2px 5px 0;
                outline: 0;
            }

            .date-13 {
                background-color: #fff;
                border: 1px solid #d5d9d9;
                border-radius: 8px;
                box-shadow: rgba(213, 217, 217, .5) 0 2px 5px 0;
                box-sizing: border-box;
                color: #0f1111;
                cursor: pointer;
                display: inline-block;
                font-family: "Amazon Ember",sans-serif;
                font-size: 13px;
                line-height: 29px;
                padding: 0 10px 0 11px;
                position: relative;
                text-align: center;
                text-decoration: none;
                user-select: none;
                -webkit-user-select: none;
                touch-action: manipulation;
                vertical-align: middle;
                width: 150px;
            }

            .date-13:hover {
                background-color: #f7fafa;
            }

            .date-13:focus {
                border-color: #008296;
                box-shadow: rgba(213, 217, 217, .5) 0 2px 5px 0;
                outline: 0;
            }
        </style>
    </head>
    <body>


        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="#" class="brand">
                <i class='bx bxs-smile'></i>
                <span class="text">AdminHub</span>
            </a>
            <ul class="side-menu top">
                <li class="active">
                    <a href="DashboardController">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="AccountController">
                        <i class='bx bxs-group' ></i>
                        <span class="text">Account</span>
                    </a>
                </li>
                <li>
                    <a href="AdminProductController">
                        <i class='bx bxs-archive' ></i>
                        <span class="text">Product</span>
                    </a>
                </li>
                <li>
                    <a href="AdminCategoryController">
                        <i class='bx bx-add-to-queue'></i>
                        <span class="text">Category</span>
                    </a>
                </li>
                <li hidden="">
                    <a href="RevenueController">
                        <i class='bx bx-money'></i>
                        <span class="text">Revenue</span>
                    </a>
                </li>
            </ul>
            <ul class="side-menu">
                <li>
                    <a href="LogoutController" id="logoutButton" class="menu_button">
                        <i class='bx bxs-log-out-circle'></i>
                        <span class="text">Logout</span>
                    </a>                    

                    <!--                     Logout confirmation dialog 
                                        <div id="logoutConfirmationDialog" class="modal">
                                            <div class="modal-content">
                                                <p>Are you sure you want to logout?</p>
                                                <button id="confirmLogout">Logout</button>
                                                <button id="cancelButton">Cancel</button>
                                            </div>
                                        </div>-->
                </li>
            </ul>
        </section>
        <!-- SIDEBAR -->



        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu' ></i>
                <a href="#" class="nav-link"></a>
                <form action="#">
                    <div class="search">
                        <input type="text" class="searchTerm" placeholder=" Search....." hidden="">
                        <button type="submit" class="searchButton" hidden=""><i class="bx bx-search"></i></button>
                    </div>
                </form>
                <input type="checkbox" id="switch-mode" hidden>
                <label for="switch-mode" class="switch-mode"></label>

                <a href="#" class="profile">
                    <img src="img/people.jpg">
                </a>
            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <div class="head-title">
                    <div class="left">
                        <h1>Dashboard</h1>
                        <fieldset style="background: var(--light); padding: 12px; border-radius: 20px; align-items: center; grid-gap: 24px; border: none">
                            <p  style="font-size: 20px; font-weight: bold;">Select date</p>

                            <form action ="DashboardController" method="POST">
                                <input type="date" name="date" value="" class="date-13"> 
                                <button type="submit" class="button-13" <!--style="border-radius: 20px; padding: 5px; background: var(--light)" --> Submit </button>
                            </form>
                        </fieldset>
                    </div>
                </div>
                <%  
                    int No2 = 0;
                    double TotalSales = 0;
                    DecimalFormat vnCurrencyFormat = new DecimalFormat("###,### VNĐ");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    ArrayList<OrderDashboard> orderIDList
                            = (ArrayList<OrderDashboard>) session.getAttribute("OrderIDList");
                    ArrayList<OrderHistory> ODList
                            = (ArrayList<OrderHistory>) session.getAttribute("OrderDetailList");
                    if (orderIDList != null) {
                        for (OrderDashboard od : orderIDList) {
                            No2++;
                            TotalSales += od.getOrderAmount();
                        }
                    }
                    String formatTotalSales = vnCurrencyFormat.format(TotalSales);
                %>
                <ul class="box-info">
                    <li>
                        <i class='bx bxs-calendar-check' ></i>
                        <span class="text">
                            <h3><%=No2%></h3>
                            <p>Number of Order</p>
                        </span>
                    </li>
                    <li>
                        <i class='bx bxs-dollar-circle' ></i>
                        <span class="text">
                            <h3><%=formatTotalSales%></h3>
                            <p>Total Sales</p>
                        </span>
                    </li>
                </ul>


                <div class="table-data">
                    <div class="order">
                        <div class="head">
                            <h3>Recent Orders</h3>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>Order Create Date</th>
                                    <th>Total</th>
                                    <th>Detail</th>
                                </tr>
                            </thead>
                            <% for (OrderDashboard odh : orderIDList) {
                                    String formatValue = vnCurrencyFormat.format(odh.getOrderAmount());
                                    String date = sdf.format(odh.getOrderDate());
                            %>
                            <tbody>                               
                                <tr>
                                    <td><%=odh.getOrderId()%></td>
                                    <td style="text-align: center; padding-left: 5px;"><%=date%></td>
                                    <td style="text-align: left; padding-left: 250px;"><%=formatValue%></td>
                                    <td>
                                        <button type="button" class="button-detail" onclick="togglePopup('<%= "popupForm_" + odh.getOrderId()%>')" style="border: none; background: var(--light);"><i class='bx bx-detail'></i>&nbsp;Detail </button>
                                        <div class="popup-form" id="popupForm_<%= odh.getOrderId()%>">
                                            <p style="font-weight: bold; font-style: italic; text-align: center; margin-bottom: 20px; font-size: 30px;">Order Detail</p>
                                            <a class="close" href="#" style="text-decoration: none; color: black; margin-top: -5%;" onclick="togglePopup('<%= "popupForm_" + odh.getOrderId()%>')">X</a>
                                            <div class="form-detail" style="margin-top: 30px;">
                                                <p style="font-weight: bold; font-size: 25px; text-align: left;">Thông tin đơn hàng</p>
                                                <div style="display:flex;">
                                                    <p style="font-weight: bold;">Mã đơn hàng:</p>
                                                    <p>&nbsp;&nbsp; <%=odh.getOrderId()%></p>
                                                </div>
                                                <div style="display: flex;">
                                                    <p style="font-weight: bold;">Ngày tạo đơn: </p>
                                                    <p>&nbsp; <%=date%></p>
                                                </div>
                                            </div>

                                            <div class="form-detail" style="margin-top: 30px;">
                                                <p style="font-weight: bold; font-size: 25px; text-align: left;">Thông tin khách hàng</p>
                                                <div style="display: flex;">
                                                    <p style="font-weight: bold;">Họ và Tên: </p> 
                                                    <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<%=odh.getName()%></p>
                                                </div>
                                                <div style="display: flex;">
                                                    <p style="font-weight: bold;">Số điện thoại: </p>
                                                    <p>&nbsp; &nbsp;<%=odh.getPhone()%></p>
                                                </div>
                                                <div style="display: flex;">
                                                    <p style="font-weight: bold;">Địa chỉ: </p>
                                                    <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <%=odh.getAddress()%></p>
                                                </div>
                                            </div> 

                                            <div class="form-detail" style="margin-top: 30px;">
                                                <p style="font-weight: bold; font-size: 25px; text-align: left;">Thông tin thanh toán</p>
                                                <div style="display: flex;">
                                                    <p style="font-weight: bold;">Hình thức: </p> 
                                                    <p>&nbsp; &nbsp; &nbsp; &nbsp;<%= (odh.isPaymentType() == false) ? "Thanh toán khi nhận hàng" : "Thanh toán qua ngân hàng"%></p>
                                                </div>
                                                <div style="display: flex;">
                                                    <p style="font-weight: bold;">Total: </p> 
                                                    <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <%=formatValue%></p>  
                                                </div>                     
                                            </div>
                                            <p style="font-weight: bold; margin-top: 30px; font-size: 25px; text-align: left;">Thông tin sản phẩm</p>
                                            <%
                                                if (ODList != null) {
                                                    for (OrderHistory od : ODList) {
                                                        if (od.getOrderId() == odh.getOrderId()) {
                                                            String base64Image = Base64.getEncoder().encodeToString(od.getImageToy());
                                                            String price = vnCurrencyFormat.format(od.getPrice());
                                                            String discount = vnCurrencyFormat.format(od.getPrice() * (1 - od.getDiscount()));
                                                            String total = vnCurrencyFormat.format(od.getOrderPrice() * od.getQuantity());
                                            %>
                                            <div class="form-detail" >                            
                                                <div class="flex items-center" style="margin-top: 20px; display: flex;">
                                                    <img src="data:image/jpeg;base64,<%= base64Image%>" alt="Product Image" style="width: 120px; height: 120px; margin-left: 20px; margin-right: 20px;">
                                                    <div>
                                                        <div style="display: flex;">
                                                            <p style="font-weight: bold;">Tên sản phẩm: </p> 
                                                            <p>&nbsp; &nbsp; &nbsp;<%=od.getToyName()%></p>
                                                        </div>
                                                        <div style="display: flex;">
                                                            <p style="font-weight: bold;">Giá sản phẩm: </p>
                                                            <p>&nbsp; &nbsp; &nbsp; <%=price%></p>
                                                        </div>
                                                        <div style="display: flex;">
                                                            <p style="font-weight: bold;">Giảm giá: </p>
                                                            <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<%=discount%></p>
                                                        </div>
                                                        <div style="display: flex;">
                                                            <p style="font-weight: bold;">Số lượng:</p>
                                                            <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<%=od.getQuantity()%></p>
                                                        </div>
                                                        <div style="display: flex;">
                                                            <p style="font-weight: bold;">Thành tiền: </p>
                                                            <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <%=total%> </p>
                                                        </div>
                                                    </div>
                                                </div>                          
                                            </div>
                                            <%}
                                                }
                                            } else {
                                            %>
                                            <p></p>
                                            <%}%>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                            <%}%>
                        </table>
                    </div>

                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->
        <script>
            function togglePopup(popupId) {
                var popup = document.getElementById(popupId);
                if (popup) {
                    popup.style.display = (popup.style.display === "block") ? "none" : "block";
                }
            }
        </script>
        <script src="js/admin.js"></script>
        <script src="js/toast.js"></script>
    </body>
</html>
