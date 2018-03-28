<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%--<img src="${pageContext.request.contextPath}/static/img/site/timg.gif" class="anim" alt="">--%>
<div class="header">
    <a href="${pageContext.request.contextPath}/">
        <img id="logo" src="${pageContext.request.contextPath}/static/img/site/logo.jpg" class="logo">
    </a>
    <form action="${pageContext.request.contextPath}/product/search" method="post" id="search_form" class="search">
        <input name="query" type="text" value="${query}" placeholder="时尚男鞋  太阳镜 ">
        <button type="submit" class="search__button">搜索</button>
        <div class="search__below">
            <c:forEach items="${cs}" var="c" varStatus="st">
                <c:if test="${st.count>=5 and st.count<=8}">
						<span>
							<a href="${pageContext.request.contextPath}/category/list?cid=${c.id}">
                                    ${c.name}
                            </a>
							<c:if test="${st.count!=8}">
                                <span>|</span>
                            </c:if>
						</span>
                </c:if>
            </c:forEach>
        </div>
    </form>
</div>