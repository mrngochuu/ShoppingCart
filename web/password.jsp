<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>G.A.U | ${sessionScope.USER.username}</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <sb:head/>
</head>
<body>
    <div class="container-fluid"> 
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card card-signin my-5">
                    <div class="card-body">
                        <h3 class="card-title text-center">CHANGE PASSWORD</h3>
                        <form action="MainController" method="POST" class="form-signin">
                            <input type="password" name="txtOldPassword" placeholder="Old Password" class="form-control mb-3" />
                            <font color="red"><p class="mb-3">${requestScope.INVALID.oldPasswordError}</p></font>
                            <input type="password" name="txtNewPassword" placeholder="New Password" class="form-control mb-3" />
                            <font color="red"><p class="mb-3">${requestScope.INVALID.passwordError}</p></font>
                            <input type="password" name="txtConfirm" placeholder="Cofirm Password" class="form-control mb-3" />
                            <font color="red"><p class="mb-3">${requestScope.INVALID.confirmError}</p></font>
                            <font color="red"><p class="mb-3">${requestScope.MESSAGE}</p></font>
                            <button type="submit" name="action" value="ChangePassword" class="btn btn-lg btn-primary btn-block text-uppercase mb-3">Update</button>
                        </form>
                        <hr class="my-4">
                        <div class="mb-3">
                            <c:url var="homeLink" value="MainController">
                                <c:param name="action" value="SearchProduct"/>
                            </c:url>
                            <a href="${homeLink}">Home Page</a>
                        </div>
                        <div class="mb-3">
                            <a href="register.jsp">Register Account</a>
                            <div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>