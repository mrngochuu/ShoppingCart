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
                    <div class="col-lg-9 bg-light">
                        <h1 class="text-center mb-5">POST REAL ESTATE</h1>
                        <h3 class="text-center mb-3"><font color="red">${requestScope.MESSAGE}</font></h3>
                        <c:set var="success" value="${requestScope.MESSAGE eq 'Insert success'}"></c:set>
                        <form action="MainController" method="GET" class="form-signin row">
                            <div class="offset-lg-1 col-lg-2">
                                <img src="img/product/realEstate_Default.jpg" alt="No Image" width="200" height="200" border="2">
                            </div>
                            <div class="offset-lg-2 col-lg-6">
                                <input type="text" name="title" value="<c:if test="${!success}">${param.title}</c:if>" placeholder="Title" class="form-control mb-3"/>
                                <font color="red"><p class="mb-3">${requestScope.INVALID.titleError}</p></font>
                                <textarea name="description" placeholder="Description" class="form-control mb-3"><c:if test="${!success}">${param.description}</c:if></textarea>
                                <font color="red"><p class="mb-3">${requestScope.INVALID.descriptionError}</p></font>
                                <input type="text" name="price" value="<c:if test="${!success}">${param.price}</c:if>" placeholder="Price" class="form-control mb-3"/>
                                <font color="red"><p class="mb-3">${requestScope.INVALID.priceError}</p></font>
                                <input type="text" name="area" value="<c:if test="${!success}">${param.area}</c:if>" placeholder="Area" class="form-control mb-3"/>
                                <font color="red"><p class="mb-3">${requestScope.INVALID.areaError}</p></font>
                                <textarea name="address" rows="3" placeholder="Address" class="form-control mb-3"><c:if test="${!success}">${param.address}</c:if></textarea>
                                <font color="red"><p class="mb-3">${requestScope.INVALID.addressError}</p></font>

                                <!-- category -->
                                <div class="row mb-3">
                                    <p class="col-lg-2 text-center">Category</p>
                                    <select class="col-lg-9" name="cbCategory">
                                        <option value="0">Choose the Category</option>
                                        <c:forEach items="${sessionScope.LIST_CATEGORY}" var="CategoryDTO">
                                            <option value="${CategoryDTO.categoryID}" <c:if test="${CategoryDTO.categoryID eq param.cbCategory}">selected</c:if>>${CategoryDTO.categoryName}</option>
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
                                            <option value="${CityDTO.cityID}" <c:if test="${CityDTO.cityID eq param.cbCity}">selected</c:if>>${CityDTO.cityName}</option>
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
                                                <option value="${StateDTO.stateID}" <c:if test="${StateDTO.stateID eq param.cbState}">selected</c:if>>${StateDTO.stateName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                                <font color="red"><p class="mb-3">${requestScope.INVALID.stateError}</p></font>
                                <button type="submit" name="action" value="AdminInsertProduct" class="btn btn-lg btn-primary btn-block text-uppercase mb-3">POST</button>
                            </div>
                            <input type="hidden" name="action" value="AdminChangeCity"/>
                        </form>
                    </div>
                </div>
            </main>
            <%@include file="layout/manageFooter.jsp" %>
        </div>
    </body> 
</html>
