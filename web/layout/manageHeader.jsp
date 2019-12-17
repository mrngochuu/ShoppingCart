<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url  var="showInfoLink" value="MainController">
    <c:param name="action" value="ShowInfo" />
</c:url>
<!-- header -->
<header class="header">
    <div class="float-left">
        <c:url var="homeLink" value="MainController">
            <c:param name="action" value="SearchProduct"/>
        </c:url>
        <a class="navbar-brand ml-3" href="${homeLink}"><img src="img/logo.png" width="20" height="20"></a>

        <button type="button" class="btn" onclick="showMenu()"><i class="fas fa-bars text-white"></i></button>
    </div>
    <div class="info_admin">
        <a href="${showInfoLink}" class="btn"><i class="fas fa-users-cog text-white"></i><strong> ${sessionScope.USER.username}</strong>
        </a>
    </div>
    <div class="icon">
        <a href="#" class="btn"><i class="far fa-flag text-white"></i></a>
    </div>
    <div class="icon">
        <a href="#" class="btn"><i class="far fa-bell text-white"></i></a>
    </div>
    <div class="icon">
        <a href="#" class="btn"><i class="far fa-envelope text-white"></i></a>
    </div>
</header>