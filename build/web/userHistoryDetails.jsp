<%-- 
    Document   : cart
    Created on : Dec 13, 2019, 1:16:52 PM
    Author     : ngochuu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>G.A.U | ${sessionScope.USER.username}'s history</title>
    </head>
    <body>
        <%@include file="layout/header.jsp" %>
        <c:url var="showHomeLink" value="MainController">
            <c:param name="action" value="SearchProduct"/>
        </c:url>

        <div class="container-fluid mt-lg-4">
            <h1 class="text-center">OrderID: ${param.orderID}</h1>
            <!-- Show Product -->
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th style="width: 15%;">ID</th>
                        <th style="width: 50%;">Real Estate</th>
                        <th style="width: 15%;">Area</th>
                        <th style="width: 15%;">Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty requestScope.REAL_ESTATE}">
                        <c:forEach items="${requestScope.REAL_ESTATE}" var="realEstateDTO">
                            <tr>
                                <td>${realEstateDTO.realEstateID}</td>
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
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
            <c:url var="showHomeLink" value="MainController">
                <c:param name="action" value="SearchProduct"/>
            </c:url>
            <div class="mt-3">
                <a href="${showHomeLink}" class="btn btn-warning">&lsaquo; Return Home Page</a>
            </div>
        </div>
        <%@include file="layout/footer.jsp" %>
    </body>
</html>
