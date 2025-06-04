<%-- 
    Document   : login
    Created on : Apr 11, 2024, 4:14:18 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/login.css">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <script src="login.js"></script>
        <title></title>
        <script>
            function doForgetPass() {
                window.location.href = "forgetPassword";
            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-7">
                    <img src="img/background-register.jpg" style="max-width: 100%; height: 100vh; margin-left: -1vw">
                </div>
                <div class="col-md-5">
                    <form id="loginForm" style="margin-top: 10%" action="LoginController" onsubmit="return validateForm()" method="POST">
                        <h1 style="text-align: center; font-weight: 800; color: #d8ffde; text-shadow: 4px 3px 10px #1a451b">LOGIN</h1>
                        <div style="margin-left: 3%">
                            <label for="fullname">User Name</label><br>
                            <input style="border-style: none none solid none; margin-bottom: 4%; width: 93%" type="text" id="fullname" name="Username" required></br>
                            <label for="password">Password</label></br>
                            <input style="border-style: none none solid none; margin-bottom: 4%; width: 93%" type="password" id="password" name="Password" required></br>
                            <input type="checkbox" id="terms" name="terms">
                            <label for="terms">Remember me</label></br>
                            <input style="border-radius: 40px; font-size: 24px; font-weight: 800; color: white; margin: 2% 19%; padding: 2% 25%; background: linear-gradient(90deg, rgba(58,116,180,1) 0%, rgba(29,161,253,1) 50%, rgba(192,196,19,1) 100%);; border: none" type="submit" value="Login">
                            <p style="color: red">${LOGIN_ERROR}</p>
                            <a style="color: #007bff; font-size: 16px; margin-left: 1%;" href="signup.jsp">Register an account ?</a>
                        </div>
                        <p style="color: green">${SIGNUP_SUCCESS}</p>
                    </form>
                </div>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="js/login.js"></script>
    </body>
</html>



