<%@page import="java.text.SimpleDateFormat"%>
<%@page import="huudn.dtos.RealEstateDTO"%>
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
                <div class="row" style="min-height: 700px;">
                    <!-- menu -->
                    <%@include file="layout/manageLeft.jsp" %>
                    <div class="col-lg-9 bg-light" id="content">
                        <h1 class="text-center mb-5">UPDATE REAL ESTATE</h1>
                        <h3 class="text-center mb-3"><font color="red">${requestScope.MESSAGE}</font></h3>
                            <c:set var="success" value="${requestScope.MESSAGE eq 'Update success'}"/>
                        <form action="MainController" method="GET" class="form-signin row">
                            <div class="col-lg-5 ml-3 mr-3">
                                <img src="img/product/${requestScope.IMAGES[0].imageURL}" width="100%" height="300" id="img_details">
                                <c:if test="${fn:length(requestScope.IMAGES) > 1}">
                                    <div class="row mt-3">
                                        <c:forEach items="${requestScope.IMAGES}" var="image">
                                            <button type="button" class="mr-auto ml-auto" onclick="changeIMG('img/product/${image.imageURL}', 'img_details')"><img src="img/product/${image.imageURL}" width="108"></button>
                                            </c:forEach>
                                    </div>
                                </c:if>
                            </div>
                            <div class="col-lg-6">
                                Title:<input type="text" name="title" value="${requestScope.REAL_ESTATE.title}" placeholder="Title" class="form-control mb-3"/>
                                <font color="red"><p class="mb-3">${requestScope.INVALID.titleError}</p></font>
                                Description:<textarea name="description" placeholder="Description" class="form-control mb-3">${requestScope.REAL_ESTATE.description}</textarea>
                                <font color="red"><p class="mb-3">${requestScope.INVALID.descriptionError}</p></font>
                                Price:<input type="text" name="price" value="${requestScope.REAL_ESTATE.price}" placeholder="Price" class="form-control mb-3"/>
                                <font color="red"><p class="mb-3">${requestScope.INVALID.priceError}</p></font>
                                Area:<input type="text" name="area" value="${requestScope.REAL_ESTATE.area}" placeholder="Area" class="form-control mb-3"/>
                                <font color="red"><p class="mb-3">${requestScope.INVALID.areaError}</p></font>
                                Address:<textarea name="address" rows="3" placeholder="Address" class="form-control mb-3">${requestScope.REAL_ESTATE.address}</textarea>
                                <font color="red"><p class="mb-3">${requestScope.INVALID.addressError}</p></font>
                                <%
                                    String time = new SimpleDateFormat("dd/MM/yyyy").format(((RealEstateDTO) request.getAttribute("REAL_ESTATE")).getPostTime());
                                %>
                                Post Time:<input type="text" name="postTime" value="<%= time%>" placeholder="Post Time" class="form-control mb-3" readonly="true"/>

                                <div class="row mb-3">
                                    <p class="col-lg-2 text-center">Active:</p>
                                    <select class="col-lg-9" name="cbActive">
                                        <option value="false" <c:if test="${requestScope.REAL_ESTATE.active == false}">selected</c:if>>Sold-out</option>
                                        <option value="true" <c:if test="${requestScope.REAL_ESTATE.active == true}">selected</c:if>>Available</option>
                                        </select>
                                    </div>

                                    <!-- category -->
                                    <div class="row mb-3">
                                        <p class="col-lg-2 text-center">Category:</p>
                                        <select class="col-lg-9" name="cbCategory">
                                            <option value="0">Choose the Category</option>
                                        <c:forEach items="${sessionScope.LIST_CATEGORY}" var="CategoryDTO">
                                            <option value="${CategoryDTO.categoryID}" <c:if test="${CategoryDTO.categoryID eq requestScope.REAL_ESTATE.categoryID}">selected</c:if>>${CategoryDTO.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <font color="red"><p class="mb-3">${requestScope.INVALID.categoryError}</p></font>

                                <!-- city -->
                                <div class="row mb-3">
                                    <p class="col-lg-2 text-center">City:</p>
                                    <select class="col-lg-9" name="cbCity" onchange="this.form.submit();">
                                        <option value="0">Choose the City</option>
                                        <c:forEach items="${sessionScope.LIST_CITY}" var="CityDTO">
                                            <option value="${CityDTO.cityID}" <c:if test="${CityDTO.cityID eq requestScope.LIST_STATE[0].cityID}">selected</c:if>>${CityDTO.cityName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <font color="red"><p class="mb-3">${requestScope.INVALID.cityError}</p></font>

                                <!-- state -->
                                <div class="row mb-3">
                                    <p class="col-lg-2 text-center">State:</p>
                                    <select class="col-lg-9" name="cbState">
                                        <option value="0">Choose the State</option>
                                        <c:if test="${not empty requestScope.LIST_STATE}">
                                            <c:forEach items="${requestScope.LIST_STATE}" var="StateDTO">
                                                <option value="${StateDTO.stateID}" <c:if test="${StateDTO.stateID eq requestScope.REAL_ESTATE.stateID}">selected</c:if>>${StateDTO.stateName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                                <font color="red"><p class="mb-3">${requestScope.INVALID.stateError}</p></font>

                                <button type="submit" name="action" value="AdminUpdateProduct" class="btn btn-lg btn-primary btn-block text-uppercase mb-3">UPDATE</button>
                            </div>
                            <input type="hidden" name="action" value="AdminUpdateProductChangeCity"/>
                            <input type="hidden" name="realEstateID" value="${requestScope.REAL_ESTATE.realEstateID}"/>
                        </form>
                    </div>
                </div>
            </main>
            <%@include file="layout/manageFooter.jsp" %>
        </div>
    </body> 
</html>
