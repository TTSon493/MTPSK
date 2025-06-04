<%-- 
    Document   : admin_page
    Created on : Apr 15, 2024, 6:10:37 PM
    Author     : TUF
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Base64"%>
<%@page import="DTO.Toy"%>
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
    </head>

    <style>
        #content main .table-data .order table td:nth-child(3) {
            text-align: left;
            border: 2px solid var(--grey);
            padding-left: 0px;
        }
    </style>

    <body>


        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="#" class="brand">
                <i class='bx bxs-smile'></i>
                <span class="text">AdminHub</span>
            </a>
            <ul class="side-menu top">
                <li>
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
                <li class="active">
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

                    <!--                     Logout confirmation dialog -->
                    <div id="logoutConfirmationDialog" class="modal">
                        <div class="modal-content">
                            <p>Are you sure you want to logout?</p>
                            <button id="confirmLogout">Logout</button>
                            <button id="cancelButton">Cancel</button>
                        </div>
                    </div>
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
                        <input hidden="" type="text" class="searchTerm" placeholder=" Search.....">
                        <button hidden="" type="submit" class="searchButton"><i class="bx bx-search"></i></button>
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
            <main class="">
                <div class="head-title">
                    <div class="left">
                        <h1>Manage Product</h1>
                    </div>
                </div>
                <ul class="notifications"></ul>
                <div class="table-data">
                    <div class="order">
                        <div class="head">
                            <h3>Product List</h3>
                            <p style="color: green">${APPROVE_SUCCESS}</p>
                            <p style="color: green">${CANCEL_SUCCESS}</p>
                            <p style="color: red">${APPROVE_FAILED}</p>
                            <p style="color: red">${CANCEL_FAILED}</p>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Name</th>
                                    <th>Image</th>
                                    <th>Staff</th>
                                    <th>Price</th>
                                    <th>Active</th>
                                    <th>Cancel</th>
                                </tr>
                            </thead>
                            <tbody>  
                                <%
                                    ArrayList<Toy> toyList = (ArrayList<Toy>) session.getAttribute("TOY_LIST_NOT_APPROVE");
                                    DecimalFormat vnCurrencyFormat = new DecimalFormat("###,### VNĐ");
                                    if (toyList != null) {
                                        for (Toy toy : toyList) {
                                            String base64Image = Base64.getEncoder().encodeToString(toy.getImage());
                                            String formatPrice = vnCurrencyFormat.format(toy.getPrice());

                                %>
                                <tr>
                                    <td><%=toy.getToyId()%></td>
                                    <td><%=toy.getToyName()%></td>
                                    <td><img src="data:image/jpeg;base64,<%= base64Image%>" alt="Toy Image" style="width: 100px; height: 100px; margin-left: auto; margin-right: auto;"></td>
                                    <td><%=toy.getName_staff()%></td>
                                    <td><%=formatPrice%></td>
                                    <td><a href="ApproveToyController?toyId=<%=toy.getToyId()%>"class="button-bordered" id="success">Approve</a></td> 
                                    <td><a href="CancelToyController?toyId=<%=toy.getToyId()%>" class="button-bordered" id="error">Disable</a></td>  
                                    <td></td>
                                </tr>
                                <%}
                                } else {
                                %>
                            <p>Không có loại đồ chơi nào</p>
                            <%
                                }
                            %>

                            </tbody>
                        </table>
                    </div>
                </div>
            </main>

            <!-- MAIN -->
        </section>
        <!-- CONTENT -->

        <script src="js/admin.js"></script>
        <script src="js/toast.js"></script>
    </body>
</html>
