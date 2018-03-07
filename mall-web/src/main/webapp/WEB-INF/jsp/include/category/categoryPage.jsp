<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<title>${category.name}</title>
<div id="category">
	<div class="categoryPageDiv">
		<%@include file="sortBar.jsp"%>
		<%@include file="productsByCategory.jsp"%>
	</div>

</div>