<%@page import="java.util.List"%>
<%@page import="huudn.daos.CityDAO"%>
<%@page import="huudn.dtos.CityDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>G.A.U | Information</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid bg-light"> 
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <div class="card card-signin my-5">
                        <div class="card-body">
                            <h3 class="card-title text-center mt-3 mb-5"><font color="red">${sessionScope.USER.username}</font> </h3>
                            <form action="MainController" method="POST" class="form-signin row">
                                <div class="offset-lg-1 col-lg-2">
                                    <img src="img/avatar/${requestScope.INFO.avatarURL}" width="200" height="200" border="2">
                                </div>

                                <div class="offset-lg-2 col-lg-6">
                                    <input type="text" name="txtFullname" value="${requestScope.INFO.fullname}" placeholder="Fullname" class="form-control mb-3"/>
                                    <font color="red"><p class="mb-3">${requestScope.INVALID.fullnameError}</p></font>
                                    <input type="text" name="txtPhoneNum" value="${requestScope.INFO.phoneNum}" placeholder="PhoneNum" class="form-control mb-3"/>
                                    <font color="red"><p class="mb-3">${requestScope.INVALID.phoneNumError}</p></font>
                                    <input type="email" name="txtEmail" value="${requestScope.INFO.email}" placeholder="Email" class="form-control mb-3"/>
                                    <font color="red"><p class="mb-3">${requestScope.INVALID.emailError}</p></font>
                                    <textarea name="txtAddress" rows="3" placeholder="Address" class="form-control mb-3">${requestScope.INFO.address}</textarea>
                                    <font color="red"><p class="mb-3">${requestScope.INVALID.addressError}</p></font>
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
                                                    <option value="${StateDTO.stateID}" <c:if test="${StateDTO.stateID eq requestScope.INFO.stateID}">selected</c:if>>${StateDTO.stateName}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                    <font color="red"><p class="mb-3">${requestScope.INVALID.stateError}</p></font>
                                    <button type="submit" name="action" value="UpdateInfo" class="btn btn-lg btn-primary btn-block text-uppercase mb-3">Update</button>
                                    <font color="red"><p class="mb-3">${requestScope.MESSAGE}</p></font>
                                </div>
                                <input type="hidden" name="action" value="ChangeCity"/>
                                <input type="hidden" name="txtAvatarURL" value="${requestScope.INFO.avatarURL}"/>
                            </form>
                            <hr class="my-4">
                            <div class="mb-3">
                                <c:url var="homeLink" value="MainController">
                                    <c:param name="action" value="SearchProduct"/>
                                </c:url>
                                <a href="${homeLink}">Home Page</a>
                            </div>
                            <div class="mb-3">
                                <a href="password.jsp">Change password</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>