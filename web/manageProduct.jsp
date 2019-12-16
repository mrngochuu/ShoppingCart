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
                        <form action="MainController" class="form-signin bg-light row pt-2">
                            <input class="col-lg-3 mb-2 ml-3 mr-2" type="text" name="txtSearch" placeholder="Title" value="${param.txtSearch}"/>

                            <!-- Type -->
                            <select class="col-lg-2 browser-default custom-select mb-2 mr-2" name="cbCategory" onchange="this.form.submit();">
                                <option value="0" >Choose type of real estate</option>
                                <!-- 1 vòng for -->
                                <c:forEach items="${sessionScope.LIST_CATEGORY}" var="categoryDTO">
                                    <option value="${categoryDTO.categoryID}" <c:if test="${categoryDTO.categoryID eq param.cbCategory}">selected</c:if> >${categoryDTO.categoryName}</option>
                                </c:forEach>
                            </select>

                            <!-- City -->
                            <select selected class="col-lg-2 browser-default custom-select mb-2 mr-2" name="cbCity" onchange="this.form.submit();">
                                <option value="0">Choose the city</option>
                                <c:forEach items="${sessionScope.LIST_CITY}" var="cityDTO">
                                    <option value="${cityDTO.cityID}" <c:if test="${cityDTO.cityID eq param.cbCity}">selected</c:if>>${cityDTO.cityName}</option>
                                </c:forEach>
                            </select>

                            <!-- State -->
                            <select selected class="col-lg-2 browser-default custom-select mb-2 mr-2" name="cbState" onchange="this.form.submit();">
                                <option value="0">Choose the state</option>
                                <c:forEach items="${sessionScope.LIST_STATE}" var="stateDTO">
                                    <option value="${stateDTO.stateID}" <c:if test="${stateDTO.stateID eq param.cbState}">selected</c:if>>${stateDTO.stateName}</option>
                                </c:forEach>
                            </select>
                            <button class="col-lg-2 btn btn-outline-success mb-2" type="submit" name="action" value="AdminSearchProduct">SEARCH</button>
                            <input type="hidden" name="action" value="AdminSearchProduct"/>
                        </form>

                        <!-- Show Product -->
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th style="width: 10%;">ID</th>
                                    <th style="width: 40%;">Real Estate</th>
                                    <th style="width: 10%;">Area</th>
                                    <th style="width: 10%;">Price</th>
                                    <th style="width: 10%;">State</th>
                                    <th style="width: 10%;">Update</th>
                                    <th style="width: 10%;">Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${not empty requestScope.HOUSE}">
                                    <c:forEach items="${requestScope.HOUSE}" var="realEstateDTO">
                                        <tr>
                                            <td>${realEstateDTO.realEstateID}</td>
                                            <td>
                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <img src="img/product/${requestScope.HOUSE_IMAGE[realEstateDTO.realEstateID]}" width="80" height="80">
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
                                            <td>
                                                <c:if test="${realEstateDTO.active}">
                                                    <font color="green">Available</font>
                                                </c:if>
                                                <c:if test="${!realEstateDTO.active}">
                                                    <font color="red">Sold-out</font>
                                                </c:if>   
                                            </td>
                                            <td class="active">
                                                <c:url var="UpdateProductLink" value="MainController" >
                                                    <c:param name="action" value="UpdateProduct"/>
                                                    <c:param name="realEstateID" value="${realEstateDTO.realEstateID}"/>
                                                </c:url>
                                                <a href="${UpdateProductLink}" class="btn btn-warning btn-sm">Update</a>
                                            </td>

                                            <td class="active">
                                                <c:url var="DeleteProductLink" value="MainController" >
                                                    <c:param name="action" value="DeleteProduct"/>
                                                    <c:param name="realEstateID" value="${realEstateDTO.realEstateID}"/>
                                                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                                                    <c:param name="cbCategory" value="${param.cbCategory}"/>
                                                    <c:param name="cbCity" value="${param.cbCity}"/>
                                                    <c:param name="cbState" value="${param.cbState}"/>
                                                </c:url>
                                                <a href="${DeleteProductLink}" class="btn btn-danger btn-sm">Delete</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>

                                <c:if test="${not empty requestScope.VILLA}">
                                    <c:forEach items="${requestScope.VILLA}" var="realEstateDTO">
                                        <tr>
                                            <td>${realEstateDTO.realEstateID}</td>
                                            <td>
                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <img src="img/product/${requestScope.VILLA_IMAGE[realEstateDTO.realEstateID]}" width="80" height="80">
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
                                            <td>
                                                <c:if test="${realEstateDTO.active}">
                                                    <font color="green">Available</font>
                                                </c:if>
                                                <c:if test="${!realEstateDTO.active}">
                                                    <font color="red">Sold-out</font>
                                                </c:if>   
                                            </td>
                                            <td class="active">
                                                <c:url var="UpdateProductLink" value="MainController" >
                                                    <c:param name="action" value="UpdateProduct"/>
                                                    <c:param name="realEstateID" value="${realEstateDTO.realEstateID}"/>
                                                </c:url>
                                                <a href="${UpdateProductLink}" class="btn btn-warning btn-sm">Update</a>
                                            </td>

                                            <td class="active">
                                                <c:url var="DeleteProductLink" value="MainController" >
                                                    <c:param name="action" value="DeleteProduct"/>
                                                    <c:param name="realEstateID" value="${realEstateDTO.realEstateID}"/>
                                                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                                                    <c:param name="cbCategory" value="${param.cbCategory}"/>
                                                    <c:param name="cbCity" value="${param.cbCity}"/>
                                                    <c:param name="cbState" value="${param.cbState}"/>
                                                </c:url>
                                                <a href="${DeleteProductLink}" class="btn btn-danger btn-sm">Delete</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>

                                <c:if test="${not empty requestScope.APARTMENT}">
                                    <c:forEach items="${requestScope.APARTMENT}" var="realEstateDTO">
                                        <tr>
                                            <td>${realEstateDTO.realEstateID}</td>
                                            <td>
                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <img src="img/product/${requestScope.APARTMENT_IMAGE[realEstateDTO.realEstateID]}" width="80" height="80">
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
                                            <td>
                                                <c:if test="${realEstateDTO.active}">
                                                    <font color="green">Available</font>
                                                </c:if>
                                                <c:if test="${!realEstateDTO.active}">
                                                    <font color="red">Sold-out</font>
                                                </c:if>   
                                            </td>
                                            <td class="active">
                                                <c:url var="UpdateProductLink" value="MainController" >
                                                    <c:param name="action" value="UpdateProduct"/>
                                                    <c:param name="realEstateID" value="${realEstateDTO.realEstateID}"/>
                                                </c:url>
                                                <a href="${UpdateProductLink}" class="btn btn-warning btn-sm">Update</a>
                                            </td>

                                            <td class="active">
                                                <c:url var="DeleteProductLink" value="MainController" >
                                                    <c:param name="action" value="DeleteProduct"/>
                                                    <c:param name="realEstateID" value="${realEstateDTO.realEstateID}"/>
                                                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                                                    <c:param name="cbCategory" value="${param.cbCategory}"/>
                                                    <c:param name="cbCity" value="${param.cbCity}"/>
                                                    <c:param name="cbState" value="${param.cbState}"/>
                                                </c:url>
                                                <a href="${DeleteProductLink}" class="btn btn-danger btn-sm">Delete</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>

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
                                            <td>
                                                <c:if test="${realEstateDTO.active}">
                                                    <font color="green">Available</font>
                                                </c:if>
                                                <c:if test="${!realEstateDTO.active}">
                                                    <font color="red">Sold-out</font>
                                                </c:if>   
                                            </td>
                                            <td class="active">
                                                <c:url var="UpdateProductLink" value="MainController" >
                                                    <c:param name="action" value="UpdateProduct"/>
                                                    <c:param name="realEstateID" value="${realEstateDTO.realEstateID}"/>
                                                </c:url>
                                                <a href="${UpdateProductLink}" class="btn btn-warning btn-sm">Update</a>
                                            </td>

                                            <td class="active">
                                                <c:url var="DeleteProductLink" value="MainController" >
                                                    <c:param name="action" value="DeleteProduct"/>
                                                    <c:param name="realEstateID" value="${realEstateDTO.realEstateID}"/>
                                                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                                                    <c:param name="cbCategory" value="${param.cbCategory}"/>
                                                    <c:param name="cbCity" value="${param.cbCity}"/>
                                                    <c:param name="cbState" value="${param.cbState}"/>
                                                </c:url>
                                                <a href="${DeleteProductLink}" class="btn btn-danger btn-sm">Delete</a>
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