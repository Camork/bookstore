<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<header>
    <div id="head_content" class="z-depth-3">
        <div class="navbar-fixed">
            <nav class="nav-extended">
                <div class="nav-wrapper container legitRipple">
                    <a id="logo" href="<%=request.getContextPath()%>/index" class="brand-logo">首页</a>
                    <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>

                    <ul class="right hide-on-med-and-down">
                        <shiro:user>
                            <li><a class="waves-effect waves-light">欢迎<shiro:principal/>登录</a></li>
                        </shiro:user>
                        <shiro:lacksRole name="admin">
                            <li><a class="waves-effect waves-light" onclick="showCart(<%=SecurityUtils.getSubject().isAuthenticated()%>)">购物车列表</a>
                            </li>
                            <li><a class="waves-effect waves-light" href="<%=request.getContextPath()%>/order/getMyOrders">订单列表</a></li>
                        </shiro:lacksRole>
                        <shiro:hasRole name="admin">
                            <li><a class="waves-effect waves-light" href="<%=request.getContextPath()%>/admin/orderList">用户订单列表</a></li>
                            <li><a class="waves-effect waves-light" href="<%=request.getContextPath()%>/admin/adminCenter">管理员后台</a></li>
                        </shiro:hasRole>
                    </ul>
                    <ul id="nav-mobile" class="side-nav">
                        <li><a href="#"><i class="material-icons left">home</i>Home</a></li>
                        <li><a href="#"><i class="material-icons left">settings</i>Service</a></li>
                        <li><a href="#"><i class="material-icons left">image</i>Portfolio</a></li>
                        <li><a href="#"><i class="material-icons left">account_circle</i>Team</a></li>
                        <li><a href="#"><i class="material-icons left">mail</i>Contact</a></li>
                    </ul>
                </div>

                <% boolean a = SecurityUtils.getSubject().isAuthenticated();
                    if (a) {%>
                <a class="fb btn-floating btn-large halfway-fab waves-effect waves-light pink" style="display: none"
                   href="${pageContext.request.contextPath}/logout">
                    <i class="material-icons">input</i>
                </a>
                <%} else {%>
                <a class="fb btn-floating btn-large halfway-fab waves-effect waves-light pink" style="display: none"
                   href="${pageContext.request.contextPath}/login">
                    <i class="material-icons">perm_identity</i>
                </a>
                <%}%>
            </nav>

        </div>

        <div id="faker_content" class="page">
            <div class="page-title-container">
                <h1 class="page-title">豆豆书城</h1>
            </div>
            <% if (a) {%>
            <a class="btn-floating btn-large halfway-fab waves-effect waves-light pink"
               href="${pageContext.request.contextPath}/logout">
                <i class="material-icons">input</i>
            </a>
            <%} else {%>
            <a class="btn-floating btn-large halfway-fab waves-effect waves-light pink"
               href="${pageContext.request.contextPath}/login">
                <i class="material-icons">perm_identity</i>
            </a>
            <%}%>
        </div>
    </div>
</header>

<div id="cart" class="modal bottom-sheet">
    <div class="modal-content">
        <div class="row">
            <h4 class="left">购物车列表</h4>
            <a class="right btn btn-flat waves-effect waves-light" onclick="clearCart()">清空购物车</a>
        </div>

        <ul class="collection table" style="background-color: #fff"></ul>
        <div class="row footer"></div>
    </div>
</div>