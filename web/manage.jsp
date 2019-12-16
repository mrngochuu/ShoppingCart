<!DOCTYPE html>
<html>
    <head>
        <title>Adminstator Control Panel</title>
        <script src="javascript/details.js" type="text/javascript" charset="utf-8" async defer></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/admin.css">
        <script src="javascript/admin.js" type="text/javascript" charset="utf-8" async defer></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    </head>
    <body>
        <%@include file="layout/manageHeader.jsp" %>
            <!-- body amin -->
            <main>
                <div class="row">
                    <!-- menu -->
                    <%@include file="layout/manageLeft.jsp" %>
                    <div class="col-lg-9" id="content">
                        <div class="row">
                            <!-- Dashboard -->
                            <div class="col-lg-3 dashboard" style="border-top: 2px solid blue;">
                                <div class="row">
                                    <div class="col-lg-5 a">
                                        <img src="img/admin/fb.png" width="50px" height="50px" class="mt-2">
                                    </div>
                                    <div class="col-lg-7 b">
                                        LIKES
                                        <h3>91,015</h3>
                                    </div>
                                </div>
                            </div>
                            <div class="offset-lg-1 col-lg-3 dashboard" style="border-top: 2px solid red;">
                                <div class="row">
                                    <div class="col-lg-5">
                                        <img src="img/admin/ins.png" width="50px" height="50px" class="mt-2">
                                    </div>
                                    <div class="col-lg-7">
                                        LIKES
                                        <h3>31,085</h3>
                                    </div>
                                </div>
                            </div>
                            <div class="offset-lg-1 col-lg-3 dashboard" style="border-top: 2px solid green;">
                                <div class="row">
                                    <div class="col-lg-5">
                                        <img src="img/admin/skype.png" width="50px" height="50px" class="mt-2">
                                    </div>
                                    <div class="col-lg-7">
                                        LIKES
                                        <h3>21,450</h3>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Chart -->
                        
                        <div class="row mt-lg-3">
                            <div class="col-lg-7 bg-white" style="border-top: 2px solid blue; border-radius: 5px;">
                                <p class="text-center font-weight-bold">Lastest Orders</p>
                                <table class="table text-center">
                                    <thead>
                                        <tr>
                                            <th>OrderID</th>
                                            <th>Item</th>
                                            <th>Amout</th>
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>OR9842</td>
                                            <td>Karah</td>
                                            <td>2</td>
                                            <td><p class="text-white" style="background-color: #00a65a; border-radius: 5px">Shipped</p></td>
                                        </tr>
                                        <tr>
                                            <td>OR9841</td>
                                            <td>Kassidy</td>
                                            <td>1</td>
                                            <td><p class="text-white" style="background-color: #dd4b39; border-radius: 5px">Delivered</p></td>
                                        </tr>
                                        <tr>
                                            <td>OR9840</td>
                                            <td>Easton</td>
                                            <td>1</td>
                                            <td><p class="text-white" style="background-color: #00c0ef; border-radius: 5px">Processing</p></td>
                                        </tr>
                                        <tr>
                                            <td>OR9839</td>
                                            <td>Kassidy</td>
                                            <td>1</td>
                                            <td><p class="text-white" style="background-color: #f39c12; border-radius: 5px">Delivered</p></td>
                                        </tr>
                                        <tr>
                                            <td>OR9838</td>
                                            <td>Easton</td>
                                            <td>3</td>
                                            <td><p class="text-white" style="background-color: #00c0ef; border-radius: 5px">Processing</p></td>
                                        </tr>
                                        <tr>
                                            <td>OR9837</td>
                                            <td>Karah</td>
                                            <td>1</td>
                                            <td><p class="text-white" style="background-color: #00a65a; border-radius: 5px">Shipped</p></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <div class="offset-lg-1 col-lg-4 bg-white added">
                                <h6 class="text-center font-weight-bold">Recently Added Products</h6>
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <td><img src="img/details/karah-01.jpeg" width="70" height="70" class="float-left"></td>
                                            <td><a href="#">Karah</a>
                                                <p>Exclusive to all Luv Bridal Showrooms in Australia</p>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td><img src="img/details/kassidy-01.jpeg" width="70" height="70" class="float-left"></td>
                                            <td><a href="#">Kassidy</a>
                                                <p>Exclusive to all Luv Bridal Showrooms in Australia</p>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td><img src="img/details/EASTON-01.jpeg" width="70" height="70" class="float-left"></td>
                                            <td><a href="#">Easton</a>
                                                <p>Designed by: Madi Lane</p>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td><img src="img/details/eden-01.jpg" width="70" height="70" class="float-left"></td>
                                            <td><a href="#">Eden</a>
                                                <p>Designed by: Madi Lane</p>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
            </main>
            <%@include file="layout/manageFooter.jsp" %>
        </div>
    </body> 
</html>