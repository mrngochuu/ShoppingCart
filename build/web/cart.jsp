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
        <c:url var="showHomeLink" value="MainController">
            <c:param name="action" value="SearchProduct"/>
        </c:url>

        <div class="container mt-lg-4">
            <h2>Your shopping cart</h2>
            <c:if test="${not empty requestScope.REAL_ESTATE}" var="NoProduct">

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th style="width: 70%;">Real Estate</th>
                            <th style="width: 5%;">Area</th>
                            <th style="width: 10%;">Price</th>
                            <th style="width: 5%;">State</th>
                            <th style="width: 10%;">Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="total" value="${0}"/>
                        <c:set var="allowPayment" value="${true}"/>
                        <c:set var="realEstatePaymentStr" value="" />
                        <c:forEach items="${requestScope.REAL_ESTATE}" var="realEstateDTO">
                            <tr>
                                <td>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <img src="img/product/${requestScope.REAL_ESTATE_IMAGE[realEstateDTO.realEstateID]}" width="80" height="80">
                                        </div>
                                        <div class="col-sm-7">
                                            <h4>${realEstateDTO.title}</h4>
                                        </div>
                                    </div>
                                </td>
                                <td>${realEstateDTO.area}</td>
                                <td>
                                    $${realEstateDTO.price}
                                    <c:set var="total" value="${total + realEstateDTO.price}"/>
                                </td>
                                <td>
                                    <c:if test="${realEstateDTO.active}">
                                        <font color="green">Available</font>
                                        <c:set var="realEstatePaymentStr" value="${realEstatePaymentStr += realEstateDTO.realEstateID}-"/>
                                    </c:if>
                                    <c:if test="${!realEstateDTO.active}">
                                        <font color="red">Sold-out</font>
                                        <c:set var="allowPayment" value="${false}"/>
                                    </c:if>   
                                </td>
                                <td class="active">
                                    <c:url var="DeleteFromCartLink" value="MainController" >
                                        <c:param name="action" value="DeleteFromCart"/>
                                        <c:param name="realEstateID" value="${realEstateDTO.realEstateID}"/>
                                    </c:url>
                                    <a href="${DeleteFromCartLink}" class="btn btn-danger btn-sm">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td>
                                <a href="${showHomeLink}" class="btn btn-warning">&lsaquo; Return Home Page</a>
                            </td>
                            <td colspan="1"><strong>Total</strong></td>
                            <td class="text-center"><strong>$<span id="total_">${total}</span></strong></td>
                            <td colspan="1"></td>
                            <td>
                                <c:if test="${allowPayment}">
                                    <c:url var="PaymentLink" value="MainController">
                                        <c:param name="action" value="Payment" />
                                        <c:param name="listRealEstate" value="${realEstatePaymentStr}"/>
                                        <c:param name="Total" value="${total}"/>
                                    </c:url>
                                    <a href="${PaymentLink}" class="btn btn-info">Payment</a>
                                </c:if>

                            </td>
                        </tr>
                        <tr>
                            <td colspan="1"></td>
                            <td colspan="4">
                                <c:if test="${!allowPayment}">
                                    <font color="red">Delete all Real Estate sold out to payment! </font>
                                </c:if>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </c:if>
            <c:if test="${!NoProduct}">
                <h4>No Real Estate added!</h4>
                <a href="${showHomeLink}" class="btn btn-warning">&lsaquo; Return Home Page</a>
            </c:if> 
        </div>
        <%@include file="layout/footer.jsp" %>
    </body>
</html>
