<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid">
    <!-- List product -->
    <c:if test="${not empty requestScope.HOUSE}">
        <p class="title">House</p>
        <div class="row">
            <c:forEach items="${requestScope.HOUSE}" var="HouseDTO">
                <section class="col-lg-3">
                    <div class="card brand">
                        <img src="img/product/${requestScope.HOUSE_IMAGE[HouseDTO.realEstateID]}" alt="NO IMAGE" width="100%" height="250">
                        <div class="card-body">
                            <h4 class="card-title font-italic font-weight-bold">${HouseDTO.title}</h4>
                            <p class="card-text">${HouseDTO.description}</p>
                            <div class="row" style="height:50px;">
                                <div class="col-lg-6">Price: $${HouseDTO.price}</p></div>
                                <div class="offset-lg-1 col-lg-5">Area: ${HouseDTO.area}</div>
                            </div>
                        </div>
                            
                        <c:url var="showProductDetailsLink" value="MainController">
                            <c:param name="action" value="ShowProductDetails"/>
                            <c:param name="realEstateID" value="${HouseDTO.realEstateID}"></c:param>
                        </c:url>
                        <a href="${showProductDetailsLink}" class="btn btn-secondary pl-5 pr-5 mb-3">See details</a>
                    </div>
                </section>

            </c:forEach>
        </div>
    </c:if>
</div>