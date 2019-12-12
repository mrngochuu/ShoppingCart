<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Header -->
<div class="container-fluid">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <c:url var="homeLink" value="MainController">
            <c:param name="action" value="SearchProduct"/>
        </c:url>
        <a class="navbar-brand" href="${homeLink}"><img src="img/logo.png" width="20" height="20"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${homeLink}">Home</a>
                </li>
            </ul>
        </div>

        <div class="collapse navbar-collapse flex-grow-0" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <c:url var="showCartLink" value="MainController">
                    <c:param name="action" value="ShowCart"/>
                </c:url>
                <c:url  var="showInfoLink" value="MainController">
                    <c:param name="action" value="ShowInfo" />
                </c:url>
                <c:url var="logoutLink" value="MainController">
                    <c:param name="action" value="Logout"/>
                </c:url>

                <c:if test="${sessionScope.ROLE.roleName eq 'user'}" var="isUser">

                    <li class="nav-item">
                        <a class="nav-link ml-3" href="${showCartLink}"><i class="fas fa-shopping-cart mr-1"></i>Cart</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ml-3" href="${showInfoLink}"><i class="fas fa-user-circle mr-1"></i>${sessionScope.USER.username}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ml-3" href="${logoutLink}"><i class="fas fa-sign-out-alt mr-1"></i>Logout</a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.ROLE.roleName eq 'admin'}" var="isAdmin">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-tasks mr-1"></i></i>Manage
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">User</a>
                            <a class="dropdown-item" href="#">Real Estate</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ml-3" href="${showInfoLink}"><i class="fas fa-user-circle mr-1"></i>${sessionScope.USER.username}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ml-3" href="${logoutLink}"><i class="fas fa-sign-out-alt mr-1"></i>Logout</a>
                    </li>
                </c:if>

                <c:if test="${!isUser && !isAdmin}">
                    <li class="nav-item">
                        <a class="nav-link ml-3" href="login.jsp"><i class="fas fa-sign-in-alt mr-1"></i>Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ml-3" href="register.jsp"><i class="fas fa-plus-circle mr-1"></i>Register</a>
                    </li>       
                </c:if>
            </ul>
        </div>
    </nav>
</div>
<!-- end header -->