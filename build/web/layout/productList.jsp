<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid">
    <!-- HOUSE -->
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
        <!-- VILLA -->
        <c:if test="${not empty requestScope.VILLA}">
        <p class="title">Villa</p>
        <div class="row">
            <c:forEach items="${requestScope.VILLA}" var="VillaDTO">
                <section class="col-lg-3">
                    <div class="card brand">
                        <img src="img/product/${requestScope.VILLA_IMAGE[VillaDTO.realEstateID]}" alt="NO IMAGE" width="100%" height="250">
                        <div class="card-body">
                            <h4 class="card-title font-italic font-weight-bold">${VillaDTO.title}</h4>
                            <p class="card-text">${VillaDTO.description}</p>
                            <div class="row" style="height:50px;">
                                <div class="col-lg-6">Price: $${VillaDTO.price}</p></div>
                                <div class="offset-lg-1 col-lg-5">Area: ${VillaDTO.area}</div>
                            </div>
                        </div>
                            
                        <c:url var="showProductDetailsLink" value="MainController">
                            <c:param name="action" value="ShowProductDetails"/>
                            <c:param name="realEstateID" value="${VillaDTO.realEstateID}"></c:param>
                        </c:url>
                        <a href="${showProductDetailsLink}" class="btn btn-secondary pl-5 pr-5 mb-3">See details</a>
                    </div>
                </section>
            </c:forEach>
        </div>
    </c:if>
        <!-- Apartment -->
        <c:if test="${not empty requestScope.APARTMENT}">
        <p class="title">Apartment</p>
        <div class="row">
            <c:forEach items="${requestScope.APARTMENT}" var="ApartmentDTO">
                <section class="col-lg-3">
                    <div class="card brand">
                        <img src="img/product/${requestScope.APARTMENT_IMAGE[ApartmentDTO.realEstateID]}" alt="NO IMAGE" width="100%" height="250">
                        <div class="card-body">
                            <h4 class="card-title font-italic font-weight-bold">${ApartmentDTO.title}</h4>
                            <p class="card-text">${ApartmentDTO.description}</p>
                            <div class="row" style="height:50px;">
                                <div class="col-lg-6">Price: $${ApartmentDTO.price}</p></div>
                                <div class="offset-lg-1 col-lg-5">Area: ${ApartmentDTO.area}</div>
                            </div>
                        </div>
                            
                        <c:url var="showProductDetailsLink" value="MainController">
                            <c:param name="action" value="ShowProductDetails"/>
                            <c:param name="realEstateID" value="${ApartmentDTO.realEstateID}"></c:param>
                        </c:url>
                        <a href="${showProductDetailsLink}" class="btn btn-secondary pl-5 pr-5 mb-3">See details</a>
                    </div>
                </section>
            </c:forEach>
        </div>
    </c:if>
        
        <c:if test="${not empty requestScope.REAL_ESTATE}">
        <p class="title">Search Result</p>
        <div class="row">
            <c:forEach items="${requestScope.REAL_ESTATE}" var="RealEstateDTO">
                <section class="col-lg-3">
                    <div class="card brand">
                        <img src="img/product/${requestScope.REAL_ESTATE_IMAGE[RealEstateDTO.realEstateID]}" alt="NO IMAGE" width="100%" height="250">
                        <div class="card-body">
                            <h4 class="card-title font-italic font-weight-bold">${RealEstateDTO.title}</h4>
                            <p class="card-text">${RealEstateDTO.description}</p>
                            <div class="row" style="height:50px;">
                                <div class="col-lg-6">Price: $${RealEstateDTO.price}</p></div>
                                <div class="offset-lg-1 col-lg-5">Area: ${RealEstateDTO.area}</div>
                            </div>
                        </div>
                            
                        <c:url var="showProductDetailsLink" value="MainController">
                            <c:param name="action" value="ShowProductDetails"/>
                            <c:param name="realEstateID" value="${RealEstateDTO.realEstateID}"></c:param>
                        </c:url>
                        <a href="${showProductDetailsLink}" class="btn btn-secondary pl-5 pr-5 mb-3">See details</a>
                    </div>
                </section>
            </c:forEach>
        </div>
    </c:if>
</div>