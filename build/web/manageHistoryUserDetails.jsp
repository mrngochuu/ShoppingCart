<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <div class="row" style="min-height: 700px;">
                    <!-- menu -->
                    <%@include file="layout/manageLeft.jsp" %>
                    <div class="col-lg-9" id="content">
                        <h1 class="text-center">OrderID: ${param.orderID}</h1>
                        <h1 class="text-center">Username: ${param.username}</h1>
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
                    </div>
                </div>
            </main>
            <%@include file="layout/manageFooter.jsp" %>
        </div>
    </body> 
</html>