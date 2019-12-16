<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="manageProductLink" value="MainController">
    <c:param name="action" value="AdminSearchProduct"/>
</c:url>

<c:url var="manageUserLink" value="MainController">
    <c:param name="action" value="AdminSearchUser"/>
</c:url>

<!-- menu -->
<div class="col-lg-3" id="menu">
    <div class="list-group-item menu_">
        <a href="${manageProductLink}" class="font-weight-bold"><i class="fas fa-sign"></i></i> Real Estate management</a>
    </div>
    <div class="list-group-item menu_">
        <a href="${manageUserLink}" class="font-weight-bold"> <i class="fas fa-copy"></i> User management</a>
    </div>
    <div class="list-group-item menu_">
        <a href="managePost.jsp" class="font-weight-bold"><i class="fas fa-mail-bulk"></i> Post Real Estate</a>
    </div>
</div>