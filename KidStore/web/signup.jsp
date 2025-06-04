<%-- 
    Document   : signup
    Created on : Apr 15, 2024, 11:03:47 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>

        <!-- Bootstrap icons-->
        <%--Bootstrap 4.6.2--%>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet"/>
        <script src="login.js"></script>
    </head>
    <body>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-7">
                    <img src="img/background-register.jpg" style="max-width: 100%; height: 100%; margin-left: -1.3%">
                </div>
                <div class="col-md-5">
                    <form style="margin-top: 0%" action="SignUpController" method="POST" onsubmit="return checkPasswordLength()">
                        <h1 style="text-align: center; font-weight: 800; color: #d8ffde; text-shadow: 4px 3px 10px #1a451b">REGISTER</h1>
                        <div style="margin-left: 3%">
                            <label for="userName">User Name</label><br>
                            <input onkeyup="checkUserName()" style="outline: none; border-style: none none solid none; margin-bottom: 4%; width: 93%" type="text" id="userName" name="userName" required></br>
                            <span id="userNameError" style="color: red"></span><br>
                            <label for="fullName">Full Name</label></br>
                            <input onkeyup="checkFullName()" onkeypress="return isAlphabetKey(event)" style="outline: none; border-style: none none solid none; margin-bottom: 4%; width: 93%" type="text" id="fullName" name="fullName" required></br>
                            <span id="fullNameError" style="color: red"></span><br>
                            <label for="phone">Phone</label></br>                            
                            <input onkeyup="checkPhone()" onkeypress="return isNumberKey(event)" style="outline: none; border-style: none none solid none; margin-bottom: 4%; width: 93%" type="text" id="phone" name="phone" required></br>
                            <span id="phoneError" style="color: red"></span><br>
                            <label for="address">Address</label></br>
                            <input onkeyup="checkAddress()" style="outline: none; border-style: none none solid none; margin-bottom: 4%; width: 93%" type="text" id="address" name="address" required></br>
                            <span id="addressError" style="color: red"></span><br>
                            <label for="password">Password</label></br>
                            <input onkeyup="checkPassword(); checkMathPassword();" style="outline: none; border-style: none none solid none; margin-bottom: 4%; width: 93%" type="password" id="password" name="password" required></br>
                            <span id="passwordError" style="color: red"></span><br>
                            <label for="confirmpassword">Confirm Password</label></br>
                            <input onkeyup="checkConfirmPassword(); checkMathPassword();" style="outline: none; border-style: none none solid none; margin-bottom: 0%; width: 93%" type="password" id="confirmpassword" name="confirmPassword" required></br>
                            <span id="confirmPasswordError" style="color: red"></span><br>
                            <span id="mathPasswordError" style="color: red"></span><br>
                            <input style="border-radius: 40px; font-size: 24px; font-weight: 800; color: white; margin: 0% 19%; padding: 2% 25%; background: linear-gradient(90deg, rgba(58,116,180,1) 0%, rgba(29,161,253,1) 50%, rgba(192,196,19,1) 100%);; border: none" type="submit" value="Register">                            
                            <p style="color: red">${SIGNUP_ERROR}</p>
                            <p style="color: red">${PASS_NOT_MATH}</p>
                            <p style="color: red">${USERNAME_HAVE_BEEN_USED}</p>
                            <a style="color: #007bff; font-size: 16px; margin-left: 1%;" href="login.jsp">Log in</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
            function checkPhone() {
                var phone = document.getElementById("phone").value;
                var phoneError = document.getElementById("phoneError");
                if (phone.length < 10 && phone !== "") {
                    phoneError.innerHTML = "Số Điện Thoại Không Hợp Lệ";
                } else {
                    phoneError.innerHTML = "";
                }
            }

            function checkFullName() {
                var fullName = document.getElementById("fullName").value;
                var fullNameError = document.getElementById("fullNameError");
                if (fullName.length < 10) {
                    fullNameError.innerHTML = "Bạn Phải Nhập Tên Đầy Đủ";
                } else {
                    fullNameError.innerHTML = "";
                }
            }

            function checkAddress() {
                var address = document.getElementById("address").value;
                var addressError = document.getElementById("addressError");
                if (address.length < 20) {
                    addressError.innerHTML = "Bạn phải nhập địa chỉ đầy đủ";
                } else {
                    addressError.innerHTML = "";
                }
            }

            function checkUserName() {
                var userName = document.getElementById("userName").value;
                var userNameError = document.getElementById("userNameError");
                if (userName.length < 6) {
                    userNameError.innerHTML = "Username phải chứa ít nhất 6 kí tự";
                } else {
                    userNameError.innerHTML = "";
                }
            }

            function checkPassword() {
                var password = document.getElementById("password").value;
                var passwordError = document.getElementById("passwordError");
                if (password.length < 8) {
                    passwordError.innerHTML = "Mật Khẩu Phải Có Ít Nhất 8 Kí Tự";
                } else {
                    passwordError.innerHTML = "";
                }
            }

            function checkConfirmPassword() {
                var confirmPassword = document.getElementById("confirmpassword").value;
                var confirmPasswordError = document.getElementById("confirmPasswordError");
                if (confirmPassword.length < 8) {
                    confirmPasswordError.innerHTML = "Mật Khẩu Xác Nhận Phải Có Ít Nhất 8 Kí Tự";
                } else {
                    confirmPasswordError.innerHTML = "";
                }
            }

            function checkMathPassword() {
                var password = document.getElementById("password").value;
                var confirmPassword = document.getElementById("confirmpassword").value;
                var mathPasswordError = document.getElementById("mathPasswordError");
                if (confirmPassword === password) {
                    mathPasswordError.innerHTML = "";
                } else if (confirmPassword !== "") {
                    mathPasswordError.innerHTML = "Mật Khẩu Và Xác Nhận Mật Khẩu Không Khớp";
                }
            }

            function checkPasswordLength() {
                var password = document.getElementById("password").value;
                if (password.length < 8) {
                    alert("Mật Khẩu Phải Có Ít Nhất 8 Ký Tự");
                    return false;
                }
                return true;
            }

            function isNumberKey(evt) {
                var charCode = (evt.which) ? evt.which : event.keyCode;
                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                    return false;
                }
                return true;
            }

            function isAlphabetKey(evt) {
                var charCode = (evt.which) ? evt.which : event.keyCode;
                if ((charCode >= 65 && charCode <= 90) || (charCode >= 97 && charCode <= 122) || charCode === 32) {
                    return true;
                } else {
                    return false;
                }
            }
        </script>
    </body>

</html>