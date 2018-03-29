<%--
  Created by IntelliJ IDEA.
  User: Rhys Xia
  Date: 2018/03/28
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<style>
    /*body,h1,h2,h3,h4,h5,h6,p,ul,ol,li,form,img,dl,dt,dd,table,th,td,blockquote,fieldset,div,strong,label,em{margin:0;padding:0;border:0}
    ul,ol,li{list-style:none}
    input,button{margin:0;font-size:12px;vertical-align:middle}*/
    /*body{font-size:12px;font-family:Arial, Helvetica, sans-serif;background:url(${pageContext.request.contextPath}/static/img/site/bg01.jpg) repeat-x left top; text-align:center;margin:0 auto}*/
    a{ text-decoration:none}
    img{ display:block}

    .area01{ width:100%;margin:20px auto;height:300px;border-top:2px solid #000;overflow:hidden}
    .box01-tmall{ float:left;width:190px}

    .box{ width:780px;float:left;position:relative;height:300px}
    .box a{ float:left}

    .floor-maskItem{ position:absolute;float:left}
    .floor-maskItem img{ float:left}

    .mask{ position:absolute;left:0;top:0;background:#000;width:195px;height:150px;opacity:0;filter:alpha(opacity=0)}
    .hover .mask{background:#000;opacity:0.5;filter:alpha(opacity=50);cursor:pointer}
    .hover .qq .mask{opacity:0;filter:alpha(opacity=0)}
</style>

<script type="text/javascript">
    $(function(){
        $(".floor-maskItem").mouseover(function(){
            $(this).addClass("qq").parent().addClass("hover");
        }).mouseout(function(){
            $(this).removeClass("qq").parent().removeClass("hover");
        });
    })
</script>

<%--<div class="activity">
    <img src="${pageContext.request.contextPath}/static/img/activity.png" alt="">
</div>--%>
<div class="activity">
    <div class="area01">
        <div class="box01-tmall"><img src="${pageContext.request.contextPath}/static/img/site/bg01.jpg" /></div>
        <div class="box">
            <a href="" target="_blank" class="floor-maskItem" style="left:0;top:0;">
                <img src="${pageContext.request.contextPath}/static/img/site/img01.jpg" />
                <div class="mask"></div>
            </a>
            <a href="" target="_blank" class="floor-maskItem" style="left:195px;top:0;">
                <img src="${pageContext.request.contextPath}/static/img/site/img02.jpg" />
                <div class="mask" style="width:390px;"></div>
            </a>
            <a href="" target="_blank" class="floor-maskItem" style="left:0;top:150px;">
                <img src="${pageContext.request.contextPath}/static/img/site/img03.jpg" />
                <div class="mask"></div>
            </a>
            <a href="" target="_blank" class="floor-maskItem" style="left:195px;top:150px;">
                <img src="${pageContext.request.contextPath}/static/img/site/img04.jpg" />
                <div class="mask"></div>
            </a>
            <a href="" target="_blank" class="floor-maskItem" style="left:390px;top:150px;width:195px;">
                <img src="${pageContext.request.contextPath}/static/img/site/img05.jpg" />
                <div class="mask"></div>
            </a>
            <a href="" target="_blank" class="floor-maskItem" style="left:585px;top:0;height:300px;">
                <img src="${pageContext.request.contextPath}/static/img/site/img06.jpg" />
                <div class="mask" style="height:300px;"></div>
            </a>
            <a href="" target="_blank" class="floor-maskItem" style="left:585px;top:0;height:300px;">
                <img src="${pageContext.request.contextPath}/static/img/site/img07.jpg" style="height:300px;width: 625px"/>
                <div class="mask" style="height:300px;width: 625px"></div>
            </a>
        </div>
    </div>
</div>

