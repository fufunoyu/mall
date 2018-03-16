<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(function () {
        $("input.sortBarPrice").keyup(function () {
            var num = $(this).val();
            if (num.length == 0) {
                $("div.productUnit").show();
                return;
            }

            num = parseInt(num);
            if (isNaN(num))
                num = 1;
            if (num <= 0)
                num = 1;
            $(this).val(num);


            var begin = $("input.beginPrice").val() || 0;
            var end = $("input.endPrice").val() || 10000000;
            $("div.productUnit").hide();
            $("div.productUnit").each(function () {
                var price = $(this).attr("price");
                price = new Number(price);
                if (price <= end && price >= begin)
                    $(this).show();
            });

        });
    });
</script>
<div class="categorySortBar">


    <table class="categorySortBarTable categorySortTable">
        <tr>
            <%--<td <c:if test="${'all'==param.sort||empty param.sort}">class="grayColumn"</c:if> ><a href="?cid=${c.id}&sort=all">综合<span class="glyphicon glyphicon-arrow-down"></span></a></td>--%>
            <%--<td <c:if test="${'review'==param.sort}">class="grayColumn"</c:if> ><a href="?cid=${c.id}&sort=review">人气<span class="glyphicon glyphicon-arrow-down"></span></a></td>--%>
            <td <c:if test="${sorts.size()>0&&'saleDate,DESC'==sorts[0]}">class="grayColumn"</c:if>><a
                    href="?cid=${category.id}&sort=saleDate,DESC">新品<span class="glyphicon glyphicon-arrow-down"></span></a>
            </td>
            <td <c:if test="${sorts.size()>0&&'saleNum,DESC'==sorts[0]}">class="grayColumn"</c:if>><a
                    href="?cid=${category.id}&sort=saleNum,DESC">销量<span class="glyphicon glyphicon-arrow-down"></span></a>
            </td>
            <td <c:if test="${sorts.size()>0&&'discount,DESC'==sorts[0]}">class="grayColumn"</c:if>><a
                    href="?cid=${category.id}&sort=discount,DESC">价格<span
                    class="glyphicon glyphicon-resize-vertical"></span></a></td>
        </tr>
    </table>


    <table class="categorySortBarTable">
        <tr>
            <td><input class="sortBarPrice beginPrice" type="text" placeholder="请输入"></td>
            <td class="grayColumn priceMiddleColumn">-</td>
            <td><input class="sortBarPrice endPrice" type="text" placeholder="请输入"></td>
        </tr>
    </table>

</div>