<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<div id="carousel-of-product" class="carousel-of-product carousel indexSlider" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <c:forEach items="${silders}" var="slider" varStatus="st">
            <li data-target="#carousel-of-product" data-indexSlider-to="${st.index}"
                class="${st.index==0?'active':''}"></li>
        </c:forEach>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <c:forEach items="${sliders}" var="slider" varStatus="st">
            <div class="item ${st.index==0?'active':''}">
                <a href="${slider.jumpUrl}">
                    <img class="carousel carouselImage" src="${slider.imageUrl}">
                </a>
            </div>
        </c:forEach>
    </div>

    <!-- Controls -->
   <%-- <a class="left carousel-control" href="#carousel-of-product" role="button" data-indexSlider="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    </a>
    <a class="right carousel-control" href="#carousel-of-product" role="button" data-indexSlider="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    </a>--%>

</div>	