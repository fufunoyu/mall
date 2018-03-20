<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<div class="homepageCategoryProducts">
    <c:forEach items="${categoryWithProducts}" var="c" varStatus="stc">
        <div class="eachHomepageCategoryProducts">
            <div class="left-mark"></div>
            <span class="categoryTitle">${c.name}</span>
            <br>
            <c:forEach items="${c.products}" var="p" varStatus="st">
                <div class="productItem">
                    <a href="${pageContext.request.contextPath}/product?pid=${p.id}"><img width="100px"
                                                           src="${p.imageUrls.split(";")[0]}"></a>
                    <a class="productItemDescLink" href="${pageContext.request.contextPath}/product?pid=${p.id}">
								<span class="productItemDesc">[热销]
								${fn:substring(p.name, 0, 20)}
								</span>
                    </a>
                    <span class="productPrice">
								<fmt:formatNumber type="number" value="${p.discount}" minFractionDigits="2"/>
							</span>
                </div>
            </c:forEach>
            <div style="clear:both"></div>
        </div>
    </c:forEach>

    <img id="endpng" class="endpng" src="${pageContext.request.contextPath}/static/img/site/end.png">

</div>