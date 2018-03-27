<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<style>


    #solid{
        width:100%;
        height:500px;
        clear:both;
        position:relative;
    }
    #solid .solid0,#solid .solid1,#solid .solid2{
        position:absolute;
        left:0px;
        top:0px;
        width:100%;
        height:500px;
        display:none;
    }
    #solid .solid0{
        background:#78c4db;
    }
    #solid .solid1{
        background:#c5c9db;
    }
    #solid .solid2{
        background:#e7dfd1;
    }
    #solid ul{
        margin:0px;
        padding:0px;
        list-style:none;
    }
    #solid ul li{
        position:absolute;
        left:50%;
        top:0px;
        width:905px;
        height:500px;
        margin-left:-452px;
        display:none;
        cursor:pointer;
    }
    #solid #btt{
        width:905px;
        height:55px;
        top:445px;
        left:50%;
        position:relative;
        margin-left:-452px;
    }
    #solid #btt span{
        position:absolute;
        top:15px;
        left:45%;
        display:block;
        width:30px;
        height:8px;
        margin:0 15px;
        text-align:center;
        background:#3d3d3d;
        z-index:10;
        cursor:pointer;
        filter:alpha(opacity=70); /*IE滤镜，透明度50%*/
        -moz-opacity:0.7; /*Firefox私有，透明度50%*/
        opacity:0.7;/*其他，透明度50%*/
}
</style>


<div id="solid">
    <div class="solid0"></div><div class="solid1"></div><div class="solid2"></div>
    <ul>
        <li><img src="${pageContext.request.contextPath}/static/img/demo/width_pic1.jpg" /></li>
        <li><img src="${pageContext.request.contextPath}/static/img/demo/width_pic2.jpg" /></li>
        <li><img src="${pageContext.request.contextPath}/static/img/demo/width_pic3.jpg" /></li>
    </ul>
    <div id="btt"><span></span> <span></span> <span></span></div>
</div>
<script src="${pageContext.request.contextPath}/static/js/mall/fordboy.js"></script>


