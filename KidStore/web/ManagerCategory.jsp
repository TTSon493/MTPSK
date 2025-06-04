
<%@page import="DTO.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Staff Dashboard</title> 
        <link rel="stylesheet" href="./assets/css/tailwind.output.css">     
        <style>
            label {
                position: absolute;
                width: 274px;
                height: 47px;
                text-align: left;
                left: 14px;
                padding-top: 0px;
                color: white;
                text-shadow: 2px 2px 5px black;
            }
            div#add-product-form {
                position: absolute;
                top: 6.5%;
                left: 40%;
                border-radius: 20px;
            }
            .overlay {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5); 
                display: none; 
                z-index: 2;
            }

            .add {
                color: white;
                background-color: #7e3af2;
                border: none;
                padding: 10px 0px;
                border-radius: 13px;
                position: absolute;
                top: 67px;
                right: 107px;
                z-index: 3;
                width: 11%;
            }
            .close{
                position: absolute;
                top: 4px;
                right: 18px;
            }

            span {

            }
            input{
                color: black;
            }
            img {
                max-width: 124px;
            }
            .py-4.text-gray-500.dark\:text-gray-400 {
                background-color: #80DFFF;
                text-align: center;
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
                max-width: 5px;
            }
            .px-4 {
                padding-left: 1rem;
                padding-right: 1rem;
            }

        </style>
    </head>
    <body>

        <%
            Account acc = (Account) session.getAttribute("acc");
        %>

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
                        <h2 style="color: greenyellow">${ADD_CATEGORY_SUCCESS}</h2>
                        <h2 style="color: red">${ADD_CATEGORY_FAILED}</h2>
                    </a>
                </li>
            </ul>                        
        </div>
        <div class="w-full overflow-hidden rounded-lg shadow-xs">
            <div class="w-full overflow-x-auto">
                <table class="w-full whitespace-no-wrap">
                    <thead>
                        <tr class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
                            <th class="px-4 py-3">ID</th>
                            <th class="px-4 py-3">Category Name</th>  
                            <th class="px-4 py-3">Status</th>
                        </tr>
                    </thead>
                    <tbody class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800">
                        <%
                            ArrayList<Category> categoryList = (ArrayList<Category>) session.getAttribute("CATEGORY_LIST_ALL");
                            if (categoryList != null) {
                                for (Category category : categoryList) {
                        %>

                        <tr class="text-gray-700 dark:text-gray-400">
                            <td class="px-4 py-3"><%=category.getCategoryId()%></td>
                            <td class="px-4 py-3">
                                <%=category.getCategoryName()%>
                            </td>
                            <td class="px-4 py-3">
                                <%
                                    if (category.getIsActive() == 1) {
                                %>
                                <p>Approved</p>
                                <%
                                } else {
                                %>
                                <p>No Process</p>
                                <%
                                    }
                                %>
                            </td>
                            <td class="px-4 py-3">
                                <div class="flex items-center space-x-7 text-sm">
                                    <button class="flex items-center justify-between px-2 py-2 text-sm font-medium leading-5 text-purple-600 rounded-lg dark:text-gray-400 focus:outline-none focus:shadow-outline-gray" aria-label="Edit">
                                        <svg class="w-5 h-5" aria-hidden="true" fill="currentColor" viewBox="0 0 20 20">                                        
                                        </svg>
                                    </button>
                                    <button class="flex items-center justify-between px-2 py-2 text-sm font-medium leading-5 text-purple-600 rounded-lg dark:text-gray-400 focus:outline-none focus:shadow-outline-gray" aria-label="Delete">
                                        <svg class="w-5 h-5" aria-hidden="true" fill="currentColor" viewBox="0 0 20 20">                                      
                                        </svg>
                                    </button>
                                </div>
                            </td>

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

        <section class="my-8 mx-auto max-w-4xl">
            <button onclick="toggleAddCategoryForm()" class="add">Add New Category</button>            
        </section>
        <div class="wrap">
            <div class="addCategory overlay">
                <div style="text-align: center; color: white; font-size: 24px; font-weight: 600; margin-top: -2%; background-color: darkkhaki; width: 350px; padding-bottom: 25px" id="add-product-form">                    
                    <div>
                        <form action="AddCategoryController">                                        
                            <button onclick="toggleAddCategoryForm()" class="close" style="text-align: right">X</button>                                                                    
                            <label for="categoryname">Category Name</label></br>
                            <input type="text" id="categoryname" name="nameCategory" required></br>  
                            <input name="userId" type="hidden" value="<%=acc.getUserId()%>">
                            <button style="background-color: green; border-radius: 30px; padding: 3px 10px; margin-top: 15px" type="submit">Add New Category</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        function toggleAddCategoryForm() {
            var addCategoryForm = document.querySelector('.addCategory');
            var add = document.querySelector('.add');
            if (addCategoryForm.style.display === 'none') {
                addCategoryForm.style.display = 'block';
                add.style.display = 'none';
                addCategoryForm.classList.add("overlay");
            } else {
                addCategoryForm.style.display = 'none';
                addCategoryForm.classList.remove("overlay");
                add.style.display = 'block';
            }
        }
    </script>
</html>
