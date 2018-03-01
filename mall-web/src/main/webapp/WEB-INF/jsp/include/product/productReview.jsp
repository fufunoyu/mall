
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%--引入kkpager--%>
<script type="text/javascript" src="/static/js/kkpager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="/static/css/kkpager/kkpager_orange.css" />
<%--使用kkpager--%>
<script type="text/javascript">
    //init
    $(function(){
        //生成分页
        //有些参数是可选的，比如lang，若不传有默认值
        kkpager.generPageHtml({
            pno : 1,
            //总页码
            total : 10,
            //总数据条数
            totalRecords : 100,
            mode : 'click',//默认值是link，可选link或者click
            click : function(n){
                // do something
                //手动选中按钮
                this.selectPage(n);
                return false;
            }
            /*
            ,lang				: {
                firstPageText			: '首页',
                firstPageTipText		: '首页',
                lastPageText			: '尾页',
                lastPageTipText			: '尾页',
                prePageText				: '上一页',
                prePageTipText			: '上一页',
                nextPageText			: '下一页',
                nextPageTipText			: '下一页',
                totalPageBeforeText		: '共',
                totalPageAfterText		: '页',
                currPageBeforeText		: '当前第',
                currPageAfterText		: '页',
                totalInfoSplitStr		: '/',
                totalRecordsBeforeText	: '共',
                totalRecordsAfterText	: '条数据',
                gopageBeforeText		: ' 转到',
                gopageButtonOkText		: '确定',
                gopageAfterText			: '页',
                buttonTipBeforeText		: '第',
                buttonTipAfterText		: '页'
            }*/
        });
    });
</script>


<div class="productReviewDiv" >
	<div class="productReviewTopPart">
		<a  href="#nowhere" class="productReviewTopPartSelectedLink">商品详情</a>
		<a  href="#nowhere" class="selected">累计评价 <span class="productReviewTopReviewLinkNumber">${productVo.product.commentNum}</span> </a>
	</div>
	
		
	<div class="productReviewContentPart">
		<c:forEach items="${reviews}" var="r">
		<div class="productReviewItem">
		
			<div class="productReviewItemDesc">
				<div class="productReviewItemContent">
					${r.content }
				</div>
				<div class="productReviewItemDate"><fmt:formatDate value="${r.createDate}" pattern="yyyy-MM-dd"/></div>
			</div>
			<div class="productReviewItemUserInfo">
			
				${r.user.anonymousName}<%--<span class="userInfoGrayPart">（匿名）</span>--%>
			</div>
			
			<div style="clear:both"></div>
		
		</div>
		</c:forEach>

		<%--显示kkpager--%>
		<div id="kkpager"></div>

	</div>

</div>
