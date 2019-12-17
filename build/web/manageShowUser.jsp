<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="layout/manageHeader.jsp" %>
            <!-- body amin -->
            <main>
                <div class="row">
                    <!-- menu -->
                    <%@include file="layout/manageLeft.jsp" %>
                    <div class="offset-lg-1 col-lg-6 bg-light mt-2 card" id="content">
                        <h1 class="text-center">Information</h1>
                        <div class="row bg-light mt-3">
                            <div class="offset-lg-1 col-lg-3">
                                <img src="img/avatar/${requestScope.INFO_USER.avatarURL}" width="150" height="150" border="2">
                            </div>
                            <div class="offset-lg-1 col-lg-6 bg-white mb-3">
                                <h6 class="card-text">ID: ${requestScope.INFO_USER.userID}</h6>
                                <h6>Username: ${requestScope.INFO_USER.username}</h6>
                                <h6>Fullname: ${requestScope.INFO_USER.fullname}</h6>
                                <h6>Phone: ${requestScope.INFO_USER.phoneNum}</h6>
                                <h6>Email: ${requestScope.INFO_USER.email}</h6>
                                <h6>Address: ${requestScope.INFO_USER.address}</h6>
                                <h6>City: ${requestScope.CITY_USER.cityName}</h6>
                                <h6>State: ${requestScope.STATE_USER.stateName}</h6>
                                <h6>Role: ${requestScope.ROLE_USER.roleName}</h6>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12 bg-light mt-3">
                        <c:if test="${not empty requestScope.LIST_ORDER}">
                            <h1 class="title text-center">TRANSACTION HISTORY</h1>
                            <div class="row">
                                <c:forEach items="${requestScope.LIST_ORDER}" var="orderDTO">
                                    <section class="col-lg-3">
                                        <div class="card brand">
                                            <div class="card-body">
                                                <div>ID: <strong>${orderDTO.orderID}</strong></div>
                                                <div>Total: <strong>$${orderDTO.total}</strong></div>
                                                <div>
                                                    State: <strong style="color: green;">Checkout</strong>
                                                </div>
                                                <div>Date Checkout: <strong>${orderDTO.dateCheckout}</strong></div>
                                            </div>

                                            <c:url var="showOrderDetailsLink" value="MainController">
                                                <c:param name="action" value="AdminShowOrderDetails"/>
                                                <c:param name="orderID" value="${orderDTO.orderID}"/>
                                                <c:param name="username" value="${requestScope.INFO_USER.username}"/>
                                            </c:url>
                                            <a href="${showOrderDetailsLink}" class="btn btn-secondary">See details</a>
                                        </div>
                                    </section>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                </div>
            </main>
            <%@include file="layout/manageFooter.jsp" %>
        </div>
    </body> 
</html>
