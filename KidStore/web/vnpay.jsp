<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thanh toán VNPay</title>
        <!-- Include Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <!-- Custom CSS for styling -->
        <style>
            .container {
                padding: 50px 0;
            }
            .logo {
                width: 150px;
                margin-bottom: 20px;
            }
            .qr-code {
                max-width: 300px;
                margin: 20px auto;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="text-center">

                <img src="https://static.ybox.vn/2020/9/4/1600327260787-Thi%E1%BA%BFt%20k%E1%BA%BF%20kh%C3%B4ng%20t%C3%AAn%20(31).png" alt="VNPay Logo" class="logo">
            </div>


            <form  method="post">

                <div class="text-center">                    
                    <img src="img/qrcode.jpg" alt="QR Code" class="qr-code"/>
                </div>

                <div class="text-center" style="height: 40px;"> 
                    <div class="text-center">
                        <img src="https://i.gyazo.com/884ccd6cfb1e3587623a0815cd228aa2.png" alt="VNPay Logo" class="logo">  
                        <i class="fas fa-bars"> | </i>
                        <img src="https://inkythuatso.com/uploads/images/2021/09/logo-vietcombank-inkythuatso-10-10-41-18.jpg" alt="VNPay Logo" class="logo">
                    </div>


                </div>

        </div>
        <div class="text-center">
            <label for="cardholderName">Tên chủ thẻ: Nguyễn Hoàng Lê Minh</label>                   
        </div>
        <div class="text-center">
            <label for="accountNumber">Số tài khoản: 111111111111</label>
        </div>

        <div class="text-center">

            <a href="OrderDetailController" class="btn btn-success " style="margin-top: 20px;">Đã thanh toán</a>
        </div>
    </form>
</div>


<!-- Include Bootstrap JS (optional, if needed) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-lBgtDlzrksVcscM5A0t9QGx0H3UZCkRgmXsQrDfMEQhjQFZXgjuuqGmeXwoM6Gc0" crossorigin="anonymous"></script>
</body>
</html>
