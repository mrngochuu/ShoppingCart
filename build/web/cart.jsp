<%-- 
    Document   : cart
    Created on : Dec 13, 2019, 1:16:52 PM
    Author     : ngochuu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <script src="javascript/details.js" type="text/javascript" charset="utf-8" async defer></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <!-- fontawesome.com -->
        <script src="https://kit.fontawesome.com/5a401084f7.js" crossorigin="anonymous"></script>
        <title>G.A.U | ${sessionScope.USER.username}'s cart</title>
    </head>
    <body>
        <%@include file="layout/header.jsp" %>
        <div class="container mt-lg-4">
            <h2>Your shopping cart</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th style="width: 50%;">Product</th>
                        <th style="width: 8%;">Price</th>
                        <th style="width: 8%;">Quantity</th>
                        <th style="width: 22%; text-align: center;">Subtotal</th>
                        <th style="width: 12%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty requestScope.REAL_ESTATE}" var="NoProduct">
                        <c:forEach items="${requestScope.REAL_ESTATE}" var="realEstateDTO">
                            <tr>
                                <td>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <img src="img/product/${requestScope.REAL_ESTATE_IMAGE[realEstateDTO.realEstateID]}" width="100" height="100">
                                        </div>
                                        <div class="col-sm-7">
                                            <h4>${realEstateDTO.title}</h4>
                                        </div>
                                    </div>
                                </td>
                            <td>$4,070</td>
                            <td><input type="number" class="form-control text-center" value="1" min="0" max="10" id="number-01" onclick="changePrice(4070, 'number-01', 'total-01')"></td>
                            <td class="text-center">$<span id="total-01">4,070</span></td>
                            <td class="active"><button class="btn btn-danger btn-sm">Remove</button></td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${!NoProduct}}">
                <h4>No Real Estate added!</h4>
            </c:if> 
            </tbody>
            <tfoot>
                <tr>
                    <td>
                        <a href="index.html" class="btn btn-warning">&lsaquo; Return Home Page</a>
                    </td>
                    <td colspan="2"></td>
                    <td class="text-center"><strong>Total $<span id="total_">6,169</span></strong></td>
                    <td>
                        <a href="#" class="btn btn-info">Payment</a>
                    </td>
                </tr>
            </tfoot>
        </table>
    </div>
    <%@include file="layout/footer.jsp" %>
</body>
</html>
