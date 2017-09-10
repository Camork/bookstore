<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>书籍分类</title>
    <%@include file="../common/headSources.jsp" %>
    <link href="<%=request.getContextPath()%>/css/loader.css" rel="stylesheet">
</head>
<body class="loaded">

<nav class="nav-extended">
    <div class="nav-wrapper">

        <ul id="nav-mobile" class="left hide-on-med-and-down" style="margin-left: 5%">
            <li><a href="<%=request.getContextPath()%>/index" class="material-icons">arrow_back</a></li>
            <li><a class="brand-logo" style="margin-left: 20px">类型列表</a></li>
        </ul>
        <ul class="right">
            <li>
                <form>
                    <div class="input-field" style="height: 64px">
                        <input id="search" type="search" required>
                        <label class="label-icon" for="search"><i class="material-icons">search</i></label>
                        <i class="material-icons">close</i>
                    </div>
                </form>
            </li>
        </ul>
    </div>
    <div class="nav-content" style="height: 40%">
        <a class="btn-floating btn-large halfway-fab waves-effect waves-light red disabled">
            <i class="material-icons" onclick="refreshURL('<%=request.getContextPath()%>/book/updateType')">refresh</i>
        </a>
    </div>
</nav>

<div class="card-panel content centerCard">

    <c:forEach items="${categories}" var="m">
        <div class="row">
            <span class="new badge left red" data-badge-caption="${m.key}"></span>
        </div>
        <c:forEach items="${m.value}" var="s">
            <div class="chip">
                <a onclick="getTag('${s.typeName}')">${s.typeName}</a>
            </div>
        </c:forEach>

    </c:forEach>

</div>

<%@include file="../common/preloader.jsp" %>
</body>
<shiro:hasRole name="admin">
    <script>
        $('.disabled').removeClass('disabled');
    </script>
</shiro:hasRole>

</html>
