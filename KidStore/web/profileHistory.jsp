<%@page import="java.util.Base64"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="DTO.OrderWarranty"%>
<%@page import="DTO.Account"%>
<%@page import="DTO.OrderHistory"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
        <%--Bootstrap 4.6.2--%>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet"/>
        <link href="css/footer.css" rel="stylesheet"/>
        <link href="css/profile.css" rel="stylesheet"/>
    </head>
    <style>
        .outer-box {
            width: 120%;
            height: auto;
            margin-top: 20px;
            border: 1px solid black;
            border-radius: 5px;
            padding: 20px;
        }
        .search-bar{
            width: 120%;
            height: auto;
        }
        .popup-form {
            display: none;
            width: 350px;
            height: 245px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #80dfff;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        #popup {
            display: none;
            width: 350px;
            height: 315px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: white;
            padding: 50px;
            border: 1px solid black;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
    </style>
    <body>
        <%@include file="components/navBarComponent.jsp" %>
        <div class="selection container row">

            <!-- SECTION LEFT -->

            <div class="col-md-12 col-lg-3">
                <div class="profile-img">
                    <img src="img/people.jpg" alt="User Avt"/>
                </div>
                <div class="profile-name">Customer</div>
                <ul id="profile-function" class="list-group">
                    <li class="function-select list-group-item list-group-item-action" id="selected">
                        <a href="LoadOrderHistoryController?userId=<%=acc.getUserId()%>" style="text-decoration: none">
                            <span class="text-profile">Lịch sử mua hàng</span>
                        </a>
                    </li>
                    <li class="function-select list-group-item list-group-item-action">
                        <a href="LoadWarrantyController?userId=<%=acc.getUserId()%>" style="text-decoration: none">
                            <span class="text-profile">Bảo hành sản phẩm</span>
                        </a>
                    </li>
                    <li class="function-select list-group-item list-group-item-action">
                        <a href="LogoutController" style="text-decoration: none">
                            <span class="text-profile">Logout</span>
                        </a>
                    </li>
                </ul>
            </div>

            <!-- SECTION RIGHT -->

            <div class="section_right col-md-12 col-lg-9">
                <div class="container" style="margin: 30px 20px; ">
                    <div class="row">
                        <h3>Lịch sử mua hàng</h3>
                    </div>

                    <!--bỏ script vào đây-->
                    <%
                        ArrayList<OrderHistory> orderList = (ArrayList<OrderHistory>) session.getAttribute("ORDER_HISTORY");
                        /* Format VND */
                        ArrayList<OrderHistory> OrderIDList = (ArrayList<OrderHistory>) session.getAttribute("ORDERID_HISTORY");
                        DecimalFormat vnCurrencyFormat = new DecimalFormat("###,### VNĐ");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        if (orderList != null && acc != null && OrderIDList != null) {
                            for (OrderHistory oid : OrderIDList) {
                                double TotalPrice = 0;
                                String orderDate = sdf.format(oid.getOrderDate());

                    %>

                    <!-- đơn thứ 1-->
                    <div class="outer-box">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="row" >
                                    <div class="col-md-6">
                                        <div class="product-name" style="justify-content: flex-start; display: flex;">&#128340; Ngày mua: <%=orderDate%></div>
                                    </div>
                                    <div class="col-md-6" >
                                        <div class="product-name" style="color: green; justify-content: flex-end; display: flex; font-weight: bold;"
                                             color: <%= oid.getStatus().equals("Đã Giao Hàng") ? "green"
                                                     : oid.getStatus().equals("Đang Xử Lí") ? "black"
                                                     : oid.getStatus().equals("Từ Chối Bán Hàng") ? "red" : "black"%>;"><%=oid.getStatus()%></div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Ảnh</th>
                                                    <th scope="col">Tên đồ chơi</th>
                                                    <th scope="col">Giá</th>
                                                    <th scope="col">Số lượng</th>
                                                    <th scope="col">Thành Tiền</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%for (OrderHistory order : orderList) {
                                                        if (order.getOrderId() == oid.getOrderId()) {
                                                            String formatOrderPrice = vnCurrencyFormat.format(order.getOrderPrice());
                                                            double TotalOrderDetail = order.getOrderPrice() * order.getQuantity();
                                                            String formatTotalOrderDetail = vnCurrencyFormat.format(TotalOrderDetail);
                                                            String base64Image = Base64.getEncoder().encodeToString(order.getImageToy());
                                                            TotalPrice += TotalOrderDetail;
                                                %>
                                            <form action="CartController">
                                                <tr>
                                                    <td><img src="data:image/jpeg;base64,<%= base64Image%>" alt="Toy Image" width="50"></td>
                                                    <td class="product-name"><%=order.getToyName()%></td>
                                                    <td class="product-name"><%=formatOrderPrice%></td>
                                                    <td class="product-name"><%=order.getQuantity()%></td>
                                                    <td class="product-name"><%=formatTotalOrderDetail%></td>
                                                </tr>
                                            </form>
                                            <%}
                                                }
                                                String formatOrderAmount = vnCurrencyFormat.format(oid.getOrderAmount());
                                                double Discount = TotalPrice - oid.getOrderAmount();
                                                String formatDiscount = vnCurrencyFormat.format(Discount);
                                                String formatTotalPrice = vnCurrencyFormat.format(TotalPrice);
                                            %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>


                                <div class="row">
                                    <div class="col-md-3">
                                        <p style="margin-top: 20px;">Hình thức thanh toán
                                            <%if (!oid.isPaymentMethod()) {%>
                                            bằng tiền mặt
                                            <%} else {%>
                                            bằng banking
                                            <%}%></p>
                                        <div><button hidden="" id="cancel" style="color:black; background-color: #ffcccc;">Hủy đơn hàng</button></div>
                                    </div>
                                    <div id="popup" >
                                        <form id="cancelForm">
                                            <h4>Lí do hủy đơn</h4>
                                            <div class="form-group">
                                                <textarea class="form-control" id="issueDescription" name="issueDescription" rows="5"></textarea>
                                            </div>  
                                            <button type="submit" class="btn btn-primary d-block mx-auto" >Gửi</button>
                                            <button type="button" class="btn btn-secondary d-block mx-auto" id="closePopup" onclick="closePopup">Đóng</button>
                                        </form>
                                    </div>

                                    <div class="col-md-9" >
                                        <div class="row">
                                            <div class="product-price" style="display: flex; align-items: center; justify-content: flex-start; font-size: 16px;">
                                                <button class="sendFeedback" hidden="">
                                                    <a href="#" style="text-decoration: none; color: black;" onclick="togglePopup()">Send Feedback<i class='bx bx-mail-send'></i></a>
                                                </button>
                                                <p style="color: green">${SEND_FEEDBACK_SUCCESS}</p>
                                                <p style="color: red">${SEND_FEEDBACK_FAILED}</p>
                                            </div>
                                            <!-- bảng giá -->
                                            <div class="product-name" style=" display: flex;justify-content: flex-end;">
                                                <h6>Subtotal: <%=formatTotalPrice%></h6>
                                            </div> 
                                            <div class="product-name" style=" display: flex;justify-content: flex-end;">
                                                <h6>Discount: <%=formatDiscount%></h6>
                                            </div> 
                                            <div class="product-name" style=" display: flex;justify-content: flex-end;">
                                                <h5><i class='bx bx-money'></i> Total: <%=formatOrderAmount%>  </h5>
                                            </div>                            
                                        </div>
                                    </div>
                                </div>

                                <div class="popup-form" id="popupForm">                    
                                    <form action="SendFeedbackController">
                                        <h4>Gửi Feedback</h4>
                                        <a class="close" href="#" style="text-decoration: none; color: black; margin-top: -14%;" onclick="togglePopup()">X</a>                        
                                        <div class="form-group">
                                            <label for="issueDescription">Mô tả vấn đề:</label>
                                            <textarea class="form-control" id="issueDescription" name="issueDescription" rows="3"></textarea>
                                            <input type="hidden" name="userId" value="<%=acc.getUserId()%>">
                                            <input type="hidden" name="orderId" value="">
                                        </div>     

                                        <button type="submit" class="btn btn-primary d-block mx-auto" >Gửi yêu cầu</button>
                                    </form>                    
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    } else {
                    %>
                    <p>${ORDER_HISTORY_ERROR}</p>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
        <%@include file="components/footerComponent.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
    <script>
                                            document.getElementById("cancel").addEventListener("click", function () {
                                                document.getElementById("popup").style.display = "block";
                                            });
    </script>
    <script>
        function togglePopup() {
            var popup = document.getElementById("popupForm");
            popup.style.display = (popup.style.display === "block") ? "none" : "block";
        }
    </script>
</html>
