<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Base64"%>
<%@page import="DTO.OrderWarranty"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Staff Dashboard</title>
        <link rel="stylesheet" href="./assets/css/tailwind.output.css">
        <!-- CSS Icon -->
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <style>
        .action-btn{
            background-color: #e5e5e5;
            padding: 10px 10px;
            margin: 2px 2px;
            border-radius: 30px;
            font-weight: 600;
        }
        img {
            max-width: 124px;
        }
        .description {
            max-height: 188px;
            margin-top: -93px;
        }
        img {
            max-width: 124px;
        }
        input#image {
            width: 273px;
            text-wrap: wrap;
        }

        th:nth-child(3), td:nth-child(3) {
            max-width: 100px;
            ;
        }
        .py-4.text-gray-500.dark\:text-gray-400 {
            text-align: center;
            background-color: #80DFFF;
        }
        .mt-6 {
            display: flex;
            justify-content: center;
        }
        .text-gray-800 {
            --text-opacity: 1;
            color: #1a1c23;
            font-family: serif;
            font-size: 30px;
        }
        th:nth-child(1), td:nth-child(1) {
            max-width: 20%;
        }
        .popup-form {
            display: none;
            width: 700px;
            height: 570px;
            position: fixed;
            top: 12%;
            left: 28%;
            background-color: whitesmoke;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            z-index: 3000;
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
    </style>
    <body>

        <div>
            <a href="LogoutController" style="position: absolute; top: 20px; right: 30px">Logout</a>
        </div>
        <a class="navbar-brand" href="MainController?TOY_LIST=ALL"><img src="img/logo-kids-new3.png" class="logo" alt="KidStore"></a>
        <div class="py-4 text-gray-500 dark:text-gray-400">
            <a class="ml-6 text-lg font-bold text-gray-800 dark:text-gray-200" href="#">
                STAFF DASHBOARD
            </a>
            <ul class="mt-6" style="display: flex">
                <li class="relative px-6 py-3">
                    <a class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200" href="LoadCategoryController">
                        <svg class="w-5 h-5" aria-hidden="true" fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
                        <path d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01"></path>
                        </svg>
                        <span class="ml-4">Manage Category</span>
                    </a>
                </li>
                <li class="relative px-6 py-3">
                    <a class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200" href="LoadManagerProductController">
                        <svg class="w-5 h-5" aria-hidden="true" fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
                        <path d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"></path>
                        </svg>
                        <span class="ml-4">Manage Product</span>
                    </a>
                </li>


                <li class="relative px-6 py-3">
                    <a class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200" href="LoadOrderFeedback">
                        <svg class="w-5 h-5" aria-hidden="true" fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
                        <path d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10"></path>
                        </svg>
                        <span class="ml-4">Manage Customer</span>
                    </a>
                </li>
                <li class="relative px-6 py-3">
                    <a class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200" href="LoadManagerOtherController">
                        <svg class="w-5 h-5" aria-hidden="true" fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
                        <path d="M11 3.055A9.001 9.001 0 1020.945 13H11V3.055z"></path>
                        <path d="M20.488 9H15V3.512A9.025 9.025 0 0120.488 9z"></path>
                        </svg>
                        <span class="ml-4">Manage Warranty</span>
                    </a>
                </li>
                <li class="relative px-6 py-3">
                    <a class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200" href="LoadHistoryProductController">
                        <svg class="w-5 h-5" aria-hidden="true" fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
                        <path d="M11 3.055A9.001 9.001 0 1020.945 13H11V3.055z"></path>
                        <path d="M20.488 9H15V3.512A9.025 9.025 0 0120.488 9z"></path>
                        </svg>
                        <span class="ml-4">Manage Order</span>
                    </a>
                </li>
                <li class="relative px-6 py-3">
                    <a class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200" href="AddNew.jsp">
                        <svg class="w-5 h-5" aria-hidden="true" fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
                        <path d="M15 15l-2 5L9 9l11 4-5 2zm0 0l5 5M7.188 2.239l.777 2.897M5.136 7.965l-2.898-.777M13.95 4.05l-2.122 2.122m-5.657 5.656l-2.12 2.122"></path>
                        </svg>
                        <span class="ml-4">Add News</span>
                    </a>
                </li>
            </ul>                        
        </div>
        <div class="w-full overflow-hidden rounded-lg shadow-xs">
            <div class="w-full overflow-x-auto">
                <table class="w-full whitespace-no-wrap" style="text-align: center;">
                    <thead>
                        <tr class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800" style="text-align: center;">
                            <!--
                            <th class="py-3" style="padding-left: 3rem; padding-right: 3rem;">ID Warranty</th>
                            <th class="py-3" style="padding-left: 3rem; padding-right: 3rem;">Product Name</th>
                            <th class="py-3" style="padding-left: 3rem; padding-right: 3rem;">Quantity</th>
                            <th class="py-3" style="padding-left: 3rem; padding-right: 3rem;">Image</th>
                            <th class="py-3" style="padding-left: 3rem; padding-right: 3rem;">Name Customer</th>
                            <th class="py-3" style="padding-left: 3rem; padding-right: 3rem;">Phone</th>
                            <th class="py-3" style="padding-left: 3rem; padding-right: 3rem;">Address</th>
                            <th class="py-3" style="padding-left: 3rem; padding-right: 3rem;">Created Date</th>
                            <th class="py-3" style="padding-left: 3rem; padding-right: 3rem;">Warranty</th>
                            <th class="py-3" style="padding-left: 3rem; padding-right: 3rem;">Status</th>
                            <th class="py-3" style="width: 100px;" style="padding-left: 5rem; padding-right: 5rem;">Description</th>
                            <th class="py-3" style="padding-left: 3rem; padding-right: 3rem;">Action</th>
                            -->
                            <th class="py-3" style="padding-left: 1rem; padding-right: 1rem;">ID Warranty</th>
                            <th class="py-3" style="padding-left: 3rem; padding-right: 3rem;">Image</th>
                            <th class="py-3" style="padding-left:20rem; padding-right: 20rem; word-wrap: break-word;">Name Toy</th>
                            <th class="py-3" style="padding-left: 1rem; padding-right: 1rem;">Quantity</th>
                            <th class="py-3" style="padding-left: 2rem; padding-right: 2rem;">Purchase Date</th>
                            <th class="py-3" style="padding-left: 2rem; padding-right: 2rem;">Warranty Date</th>
                            <th class="py-3" style="padding-left: 3rem; padding-right: 3rem;">Detail</th>
                            <th class="py-3" style="padding-left: 8rem; padding-right: 8rem;">Status</th>
                            <th class="py-3">Action</th>
                        </tr>
                    </thead>
                    <tbody class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800">
                        <%
                            ArrayList<OrderWarranty> warrantyList = (ArrayList<OrderWarranty>) session.getAttribute("ORDER_WARRANTY");

                            Collections.sort(warrantyList, new Comparator<OrderWarranty>() {
                                public int compare(OrderWarranty w1, OrderWarranty w2) {

                                    boolean w1IsSentForWarranty = w1.getStatus().equals("Gửi Bảo Hành");
                                    boolean w2IsSentForWarranty = w2.getStatus().equals("Gửi Bảo Hành");

                                    boolean w1IsProcessingForWarranty = w1.getStatus().equals("Đang Xử Lí");
                                    boolean w2IsProcessingForWarranty = w2.getStatus().equals("Đang Xử Lí");

                                    boolean w1IsReceivedForWarranty = w1.getStatus().equals("Đã Nhận Hàng");
                                    boolean w2IsReceivedForWarranty = w2.getStatus().equals("Đã Nhận Hàng");

                                    boolean w1IsUnderWarrantyForWarranty = w1.getStatus().equals("Đang Bảo Hành");
                                    boolean w2IsUnderWarrantyForWarranty = w2.getStatus().equals("Đang Bảo Hành");

                                    boolean w1IsShippingForWarranty = w1.getStatus().equals("Đang Giao Hàng");
                                    boolean w2IsShippingForWarranty = w2.getStatus().equals("Đang Giao Hàng");

                                    boolean w1IsDeliveredForWarranty = w1.getStatus().equals("Đã Giao Hàng");
                                    boolean w2IsDeliveredForWarranty = w2.getStatus().equals("Đã Giao Hàng");

                                    boolean w1IsRefuseForWarranty = w1.getStatus().equals("Từ Chối Bảo Hành");
                                    boolean w2IsRefuseForWarranty = w2.getStatus().equals("Từ Chối Bảo Hành");

                                    if (w1IsSentForWarranty && !w2IsSentForWarranty) {
                                        return -1;
                                    } else if (!w1IsSentForWarranty && w2IsSentForWarranty) {
                                        return 1;
                                    }

                                    if (w1IsProcessingForWarranty && !w2IsProcessingForWarranty) {
                                        return -1;
                                    } else if (!w1IsProcessingForWarranty && w2IsProcessingForWarranty) {
                                        return 1;
                                    }

                                    if (w1IsReceivedForWarranty && !w2IsReceivedForWarranty) {
                                        return -1;
                                    } else if (!w1IsReceivedForWarranty && w2IsReceivedForWarranty) {
                                        return 1;
                                    }

                                    if (w1IsUnderWarrantyForWarranty && !w2IsUnderWarrantyForWarranty) {
                                        return -1;
                                    } else if (!w1IsUnderWarrantyForWarranty && w2IsUnderWarrantyForWarranty) {
                                        return 1;
                                    }

                                    if (w1IsShippingForWarranty && !w2IsShippingForWarranty) {
                                        return -1;
                                    } else if (!w1IsShippingForWarranty && w2IsShippingForWarranty) {
                                        return 1;
                                    }

                                    if (w1IsDeliveredForWarranty && !w2IsDeliveredForWarranty) {
                                        return -1;
                                    } else if (!w1IsDeliveredForWarranty && w2IsDeliveredForWarranty) {
                                        return 1;
                                    }

                                    if (w1IsRefuseForWarranty && !w2IsRefuseForWarranty) {
                                        return -1;
                                    } else if (!w1IsRefuseForWarranty && w2IsRefuseForWarranty) {
                                        return 1;
                                    }

                                    return w2.getOrderDetailId() - w1.getOrderDetailId();
                                }
                            });

                            if (warrantyList != null) {
                                for (OrderWarranty warranty : warrantyList) {
                                    String base64Image = Base64.getEncoder().encodeToString(warranty.getImageToy());

                        %>
                        <tr class="text-gray-700 dark:text-gray-400">
                            <td class="px-4 py-3">
                                <%=warranty.getWarrantyCode()%>
                            </td>
                            <td class="px-4 py-3 text-xs">
                                <img src="data:image/jpeg;base64,<%= base64Image%>" alt="Toy Image">
                            </td>
                            <td><%=warranty.getToyName()%></td>
                            <td><%=warranty.getQuantity()%></td>

                            <%--
                            <td><%=warranty.getNameUser()%></td>   
                            <td><%=warranty.getPhone()%></td>
                            <td><%=warranty.getAddress()%></td>
                            --%>

                            <td><%=warranty.getOrderDate()%></td>
                            <td><%=warranty.getWarrantyTime()%></td>

                            <!-- Thông tin khách hàng + mô tả lỗi -->
                            <td>
                                <button type="button" class="button-detail" onclick="togglePopup('<%= "popupForm_" + warranty.getOrderDetailId()%>')"><i class='bx bx-detail'></i> &nbsp; Warranty Details</button>                                                             
                            </td>

                            <td><%=warranty.getStatus()%></td>
                            <%--
                            <%
                                if (warranty.getDescriptionWarranty() != null) {
                            %>
                            <td style="overflow-y: scroll; max-width: 100px;" class="px-4 py-3 text-sm">
                                <div class="description" style="white-space: pre-wrap;">
                                    <%=warranty.getDescriptionWarranty()%>
                                </div>
                            </td>
                            <!--
                            <td>
                                <a href="load?pid=${p.id}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                <a href="#" class="delete" data-toggle="modal" onclick="doDelete(${p.id})"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                            </td>
                            -->
                            <%
                            } else {
                            %>
                            <td>No Description</td>
                            <%
                                }
                            %>
                            --%>

                            <td class="px-4 py-3">           
                                <select id="statusSelect_<%=warranty.getOrderDetailId()%>" <% if (warranty.getStatus().equals("Đã Giao Hàng") || warranty.getStatus().equals("Từ Chối Bảo Hành") || warranty.getStatus().equals("Không Có Yêu Cầu Bảo Hành")) { %> style="display: none;" <% } %>>
                                    <option value="default">Đặt Trạng Thái</option>
                                    <% if (warranty.getStatus().equals("Gửi Bảo Hành")) { %>
                                    <option value="processing" data-action="ProcessingWarrantyController">Processing</option>          
                                    <option value="refuse" data-action="RefuseWarrantyController">Refuse</option>
                                    <% } %>  
                                    <% if (warranty.getStatus().equals("Đang Xử Lí")) { %>
                                    <option value="received" data-action="ReceivedWarrantyController">Received</option>
                                    <option value="refuse" data-action="RefuseWarrantyController">Refuse</option>
                                    <% } %>
                                    <% if (warranty.getStatus().equals("Đã Nhận Hàng")) { %>
                                    <option value="underwarranty" data-action="UnderWarrantyController">Under Warranty</option>
                                    <option value="refuse" data-action="RefuseWarrantyController">Refuse</option>
                                    <% } %>
                                    <% if (warranty.getStatus().equals("Đang Bảo Hành")) { %>                    
                                    <option value="shipping" data-action="ShippingWarrantyController">Shipping</option>
                                    <option value="refuse" data-action="RefuseWarrantyController">Refuse</option>
                                    <% }%>    
                                    <% if (warranty.getStatus().equals("Đang Giao Hàng")) { %>                           
                                    <option value="delivered" data-action="DeliveredWarrantyController">Delivered</option>
                                    <option value="refuse" data-action="RefuseWarrantyController">Refuse</option>
                                    <% }%>  
                                    <% if (warranty.getStatus().equals("Đã Giao Hàng")) { %>                           
                                    <option value="delivered" data-action="DeliveredWarrantyController">Delivered</option>     
                                    <% }%>  

                                </select>
                            </td>
                    <script>
                        document.getElementById('statusSelect_<%=warranty.getOrderDetailId()%>').addEventListener('change', function () {
                            var selectedValue = this.value;
                            var action = this.options[this.selectedIndex].getAttribute('data-action');
                            if (selectedValue !== 'default' && action) {
                                window.location.href = action + "?orderDetailId=<%=warranty.getOrderDetailId()%>";
                            }
                        });
                    </script>
                    </tr>
                    <div class="popup-form" id="popupForm_<%= warranty.getOrderDetailId()%>">  
                        <p style="font-size: 40px; font-weight: bold; font-style: italic; text-align: center;">Warranty - Details</p>
                        <a class="close" href="#" style="text-decoration: none; color: black; margin-top: -5%;" onclick="togglePopup('<%= "popupForm_" + warranty.getOrderDetailId()%>')">X</a>
                        <div class="form-detail" style="text-align: left;">
                            <p style="font-size:30px; padding-top:50px;">Customer Name: <%=warranty.getNameUser()%></p>
                            <p style="font-size:30px; padding-top:50px;">Phone: <%=warranty.getPhone()%> </p>
                            <p style="font-size:30px; padding-top:50px;">Address: <%=warranty.getAddress()%></p>
                            <% if (warranty.getDescriptionWarranty() != null && !warranty.getDescriptionWarranty().isEmpty()) {%>
                            <p style="font-size:30px; padding-top:50px; word-wrap: break-word;">Description: <%=warranty.getDescriptionWarranty()%></p>
                            <% } else { %>
                            <p style="font-size:30px; padding-top:50px; word-wrap: break-word;"></p>    
                            <% } %>
                        </div>   
                    </div>
                    <%}
                    } else {
                    %>
                    <p></p>
                    <%
                        }
                    %>  
                    </tbody>

                </table>
            </div>
        </div>
        <script>
            function togglePopup(popupId) {
                var popup = document.getElementById(popupId);
                if (popup) {
                    popup.style.display = (popup.style.display === "block") ? "none" : "block";
                }
            }
        </script>
    </body>
</html>
