<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() +
            "://"
            + request.getServerName() + ":"
            + request.getServerPort()
            + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">

    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css"
          rel="stylesheet"/>

    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript"
            src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

    <link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
    <script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination/en.js"></script>

    <style>
        a.disabled {
            pointer-events: none;
            filter: alpha(opacity=50); /*IE滤镜，透明度50%*/
            -moz-opacity: 0.5; /*Firefox私有，透明度50%*/
            opacity: 0.5; /*其他，透明度50%*/
        }
    </style>
    <script>
        window.onload = function () {
            var button = document.getElementById("but");
            button.onclick = function () {
                var pageNum = document.getElementById("pn_input").value;
                window.location.href = "${url}&pageNum=" + pageNum;
            }
        }
    </script>
</head>
<body>

<div style="position: absolute;top: 350px; left: 5px;width:800px;">
    <nav>
        <ul class="pagination">

    <c:choose>
        <c:when test="${pageInfo.hasPreviousPage}"><%--如果有上一页，就可以点上一页，否则不可点击--%>
        <li><a href="${url}&pageNum=1">首页</a></li>
        <li><a href="${url}&pageNum=${pageInfo.prePage}">上一页</a></li>
        </c:when>
        <c:otherwise>
        <li><a class="disabled" href="${url}&pageNum=1">首页</a></li>
        <li><a class="disabled" href="${url}&pageNum=${pageInfo.prePage}">上一页</a></li>
        </c:otherwise>
    </c:choose>

    <%--页码输出的开始--%>
    <c:choose>
        <%--情况1：如果总页码小于等于5 的情况，页码的范围是：1-总页码--%>
        <c:when test="${ pageInfo.pages <= 5 }">
            <c:set var="begin" value="1" scope="page"/>
            <c:set var="end" value="${pageInfo.pages}" scope="page"/>
        </c:when>
        <%--情况2：总页码大于5 的情况--%>
        <c:when test="${pageInfo.pages > 5}">
            <c:choose>
                <%--小情况1：当前页码为前面3 个：1，2，3 的情况，页码范围是：1-5.--%>
                <c:when test="${pageInfo.pageNum <= 3}">
                    <c:set var="begin" value="1" scope="page"/>
                    <c:set var="end" value="5" scope="page"/>
                </c:when>
                <%--小情况2：当前页码为最后3 个，8，9，10，页码范围是：总页码减4 - 总页码--%>
                <c:when test="${pageInfo.pageNum > pageInfo.pages-3}">
                    <c:set var="begin" value="${pageInfo.pages-4}"/>
                    <c:set var="end" value="${pageInfo.pages}"/>
                </c:when>
                <%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
                <c:otherwise>
                    <c:set var="begin" value="${pageInfo.pageNum-2}"/>
                    <c:set var="end" value="${pageInfo.pageNum+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == pageInfo.pageNum}">
        <li><a href="javascript:void(0)" style="color: #0f0f0f">${i}</a></li>
        </c:if>
        <c:if test="${i != pageInfo.pageNum}">
        <li><a href="${url}&pageNum=${i}">${i}</a></li>
        </c:if>
    </c:forEach>
    <%--页码输出的结束--%>

    <c:choose>
        <c:when test="${pageInfo.hasNextPage}">
        <li><a href="${url}&pageNum=${pageInfo.nextPage}">下一页</a></li>
        <li><a href="${url}&pageNum=${pageInfo.pages}">末页</a></li>

        <li>
            <button type="button" class="btn btn-default" style="cursor: default;">共<b>${pageInfo.pages}</b>页,<b>${pageInfo.total}</b>条记录</button>
        </li>
        <li>到第<input value="${page.pageNum}" size="1px" style="height: 32px; width: 30px; text-align:center;" name="pn" id="pn_input"/>页&nbsp;
            <input type="button" id="but" class="btn btn-default" value="确定"></li>
        </c:when>
        <c:otherwise>
        <li> <a class="disabled" href="${url}&pageNum=${pageInfo.nextPage}">下一页</a></li>
        <li> <a class="disabled" href="${url}&pageNum=${pageInfo.pages}">末页</a></li>&nbsp;

        <li>
            <button type="button" class="btn btn-default" style="cursor: default;">共<b>${pageInfo.pages}</b>页,<b>${pageInfo.total}</b>条记录</button>
        </li>
        <li>到第<input value="${pageInfo.pageNum}" size="1px" style="height: 32px; width: 30px; text-align:center;" name="pn" id="pn_input"/>页&nbsp;
        <input type="button" id="but" class="btn btn-default" value="确定"></li>
        </c:otherwise>
    </c:choose>

</div>


</body>
</html>
