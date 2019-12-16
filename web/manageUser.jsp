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
                    <form action="MainController" class="bg-light row pt-2 mr-1">
                        <input class="col-lg-3 ml-auto mr-2 mb-2" type="text" name="txtSearch" placeholder="Search by Username" value="${param.txtSearch}"/>
                        <button class="col-lg-2 btn btn-outline-success mr-auto mb-2" type="submit" name="action" value="AdminSearchUser">SEARCH</button>
                    </form>

                    <!-- Show Product -->
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th style="width: 10%; text-align: center;">ID</th>
                                <th style="width: 10%; text-align: center;">Avatar</th>
                                <th style="width: 20%; text-align: center;">Username</th>
                                <th style="width: 20%; text-align: center;">Fullname</th>
                                <th style="width: 10%; text-align: center;">Role</th>
                                <th style="width: 10%; text-align: center;">State</th>
                                <th style="width: 10%; text-align: center;">Update</th>
                                <th style="width: 10%; text-align: center;">Details</th>
                            </tr>
                        </thead>

                        <c:if test="${not empty requestScope.LIST_USER}">
                            <tbody>
                                <c:forEach items="${requestScope.LIST_USER}" var="userDTO">
                                <form action="MainController" method="POST">
                                    <td style="width: 5%; text-align: center;">${userDTO.userID}</td>
                                    <td style="width: 5%; text-align: center;"><img src='img/avatar/${userDTO.avatarURL}' width="50" height="50"/></td>
                                    <td style="width: 5%; text-align: center;">${userDTO.username}</td>
                                    <td style="width: 5%; text-align: center;">${userDTO.fullname}</td>
                                    <td style="width: 5%; padding-left: 20px;">
                                        <select name='cbRole'>
                                            <c:forEach items="${requestScope.LIST_ROLE}" var="roleDTO">
                                                <option value="${roleDTO.roleID}" <c:if test="${userDTO.roleID eq roleDTO.roleID}">selected </c:if>>${roleDTO.roleName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td style="width: 5%; padding-left: 20px;">
                                        <select name='cbState'>
                                            <option value="0" <c:if test="${userDTO.active eq false}">selected</c:if>>InActive</option>
                                            <option value="1" <c:if test="${userDTO.active eq true}">selected</c:if>>Active</option>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="submit" name="action" value="AdminUpdateUser" class="btn btn-warning btn-sm ml-3">Update</button>
                                            <input type="hidden" name="userID" value="${userDTO.userID}"/>
                                        </td>
                                        <td>
                                        <c:url value="MainController" var="AdminShowUserLink">
                                            <c:param name="action" value="AdminShowUser"/>
                                            <c:param name="userID" value="${userDTO.userID}" />
                                        </c:url>
                                        <a href="${AdminShowUserLink}" class="btn btn-dark btn-sm ml-3">Details</a>
                                    </td>
                                    <font color="red">${requestScope.MESSAGE}</font>
                                    <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                </form>
                            </c:forEach>
                            </tbody>
                        </c:if>
                    </table>
                </div>
            </div>
        </main>
        <%@include file="layout/manageFooter.jsp" %>
        </div>
    </body> 
</html>