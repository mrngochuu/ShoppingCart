<%-- 
    Document   : productDetail
    Created on : Dec 11, 2019, 10:59:25 PM
    Author     : ngochuu
--%>

<%@page import="huudn.dtos.RealEstateDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <title>G.A.U | ${requestScope.REAL_ESTATE_DTO.title}</title>
    </head>
    <body>
        <%@include file="layout/header.jsp" %>
        <!-- Product detail-->
        <div class="row mt-5 mb-5">
            <div class="offset-lg-2 col-lg-4">
                <img src="img/product/${requestScope.IMAGES[0].imageURL}" width="100%" height="300" id="img_details">
                <c:if test="${fn:length(requestScope.IMAGES) > 1}">
                    <div class="row mt-3">
                        <c:forEach items="${requestScope.IMAGES}" var="image">
                            <button type="button" class="mr-auto ml-auto" onclick="changeIMG('img/product/${image.imageURL}', 'img_details')"><img src="img/product/${image.imageURL}" width="108"></button>
                            </c:forEach>
                    </div>
                </c:if>
            </div>
            <div class="offset-lg-1 col-lg-4 details">
                <h1 class="font-weight-bold mb-3">${requestScope.REAL_ESTATE_DTO.title}</h1>
                <h3 class="mb-3">DESCRIPTION</h3>
                <p class="mb-3">${requestScope.REAL_ESTATE_DTO.description}</p>
                <h3 class="mb-3">DETAILS</h3>
                <p class="mb-3">Price: ${requestScope.REAL_ESTATE_DTO.price}</p>
                <p class="mb-3">Area: ${requestScope.REAL_ESTATE_DTO.area}</p>
                <p class="mb-3">Address: ${requestScope.REAL_ESTATE_DTO.address}</p>
                <p class="mb-3">State: ${requestScope.STATE.stateName}</p>
                <p class="mb-3">City: ${requestScope.CITY.cityName}</p>
                <%
                    String time = new SimpleDateFormat("dd/MM/yyyy").format(((RealEstateDTO) request.getAttribute("REAL_ESTATE_DTO")).getPostTime());
                %>
                <p class="mb-3">Post Time: <%= time%></p>
                <hr>
                <c:if test="${sessionScope.ROLE.roleName eq 'user'}">
                <form action="MainController" method="POST" >
                    <button name="action" value="AddToCart" class="btn btn-dark">Add To Cart</button>
                    <input type="hidden" name="realEstateID" value="${requestScope.REAL_ESTATE_DTO.realEstateID}">
                </form>
                </c:if>
                <font color="red"><p class="mt-3">${requestScope.MESSAGE}</p></font>
            </div>
        </div>
        <%@include file="layout/footer.jsp" %>

    </body>
</html>
