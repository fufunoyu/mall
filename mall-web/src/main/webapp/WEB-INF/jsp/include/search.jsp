<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<a href="/">
    <img id="logo" src="/static/img/site/logo.jpg" class="logo">
</a>

<form action="/search" method="post">
    <div class="searchDiv">
        <input name="keyword" type="text" value="${param.keyword}" placeholder="时尚男鞋  太阳镜 ">
        <button type="submit" class="searchButton">搜索</button>
        <div class="searchBelow">
            <c:forEach items="${cs}" var="c" varStatus="st">
                <c:if test="${st.count>=5 and st.count<=8}">
						<span>
							<a href="/category?cid=${c.id}">
                                    ${c.name}
                            </a>
							<c:if test="${st.count!=8}">
                                <span>|</span>
                            </c:if>
						</span>
                </c:if>
            </c:forEach>
        </div>
    </div>
</form>
