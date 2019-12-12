<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Search-->
<div class="container-fluid mt-3">
    <p class="title">Search Real Estate</p>
    <div class="row">
        <form action="MainController" class="form offset-lg-3 col-lg-6 bg-light row">
            <input class="form-control mb-3 mt-3" type="text" name="txtSearch" placeholder="Title" value="${param.txtSearch}"/>

            <!-- Type -->
            <select class="form-control browser-default custom-select mb-3" name="cbCategory" onchange="this.form.submit();">
                <option value="0-type" >Choose type of real estate</option>
                <!-- 1 vòng for -->
                <c:forEach items="${sessionScope.LIST_CATEGORY}" var="categoryDTO">
                    <c:set var="categoryStr" value="${categoryDTO.categoryID}-${categoryDTO.categoryName}" />
                    <option value="${categoryStr}" <c:if test="${categoryStr eq param.cbCategory}">selected</c:if> >${categoryDTO.categoryName}</option>
                </c:forEach>
            </select>

            <!-- City -->
            <select selected class="form-control browser-default custom-select mb-3" name="cbCity" onchange="this.form.submit();">
                <option value="0-city">Choose the city</option>
                <c:forEach items="${sessionScope.LIST_CITY}" var="cityDTO">
                    <c:set var="cityStr" value="${cityDTO.cityID}-${cityDTO.cityName}" />
                    <option value="${cityStr}" <c:if test="${cityStr eq param.cbCity}">selected</c:if>>${cityDTO.cityName}</option>
                </c:forEach>
            </select>

            <!-- State -->
            <select selected class="form-control browser-default custom-select mb-3" name="cbState" onchange="this.form.submit();">
                <option value="0-state">Choose the state</option>
                <c:forEach items="${sessionScope.LIST_STATE}" var="stateDTO">
                    <c:set var="stateStr" value="${stateDTO.stateID}-${stateDTO.stateName}"/>
                    <option value="${stateStr}" <c:if test="${stateStr eq param.cbState}">selected</c:if>>${stateDTO.cbName}</option>
                </c:forEach>
            </select>
            <button class="form-control btn btn-outline-success mb-3" type="submit" name="action" value="SearchProduct">Search</button>
            <input type="hidden" name="action" value="SearchProduct"/>
        </form>
    </div>
</div>
<!-- end search -->