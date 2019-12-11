<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>G.A.U | Infomation</title>
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
                        <h3 class="card-title text-center mt-3 mb-5"><font color="red">${session.USER.username}'s INFOMATION </font> </h3>
                        <form action="MainController" method="GET" class="form-signin row" >
                            <div class="offset-lg-1 col-lg-2 m-auto">
                                <img src="img/avatar/${requestScope.INFO.avatarURL}" alt="Loading..." width="200" height="200" border="1">
                                <input type="file" onchange="this.form.submit();" name="action" value="ChangeImage" />
                            </div>

                            <div class="offset-lg-2 col-lg-6">
                                <input type="text" name="txtFullname" value="${requestScope.INFO.fullname}" placeholder="Fullname" class="form-control mb-3"/>
                                <input type="text" name="txtPhoneNum" value="${requestScope.INFO.phoneNum}" placeholder="PhoneNum" class="form-control mb-3"/>
                                <input type="email" name="txtEmail" value="${requestScope.INFO.email}" placeholder="Email" class="form-control mb-3"/>
                                <textarea name="txtAddress" rows="3" placeholder="Address" class="form-control mb-3">${requestScope.INFO.address}</textarea>
                                <div class="row mb-3">
                                    <p class="col-lg-2 text-center">State:</p>
                                    <select class="col-lg-9" name="cbState">
                                        <c:forEach items="${requestScope.STATE}" var="StateDTO">
                                            <option value="${StateDTO.stateID}">${StateDTO.stateName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="row mb-3">
                                    <p class="col-lg-2 text-center">City:</p>
                                    <select class="col-lg-9" name="cbCity" onchange="this.form.submit();">
                                        <c:forEach items="" var="CityDTO">
                                            <option value="${CityDTO.cityID}-${CityDTO.cityName}" <c:if test="${requestScope.INFO.state.city.cityName eq CityDTO.cityName}">selected</c:if>>${CityDTO.cityName}</option>
                                        </c:forEach>
                                        <input type="hidden" name="change" value="ChangeCity">
                                    </select>
                                </div>

                                <input type="submit" name="action" value="Update" class="btn btn-lg btn-primary btn-block text-uppercase mb-3">
                            </div>

                        </form>
                        <hr class="my-4">
                        <div class="mb-3">
                            <a href="index.jsp">Home Page</a>
                        </div>
                        <div class="mb-3">
                            <a href="changePassword.jsp">Change password</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>