<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@include file="../common/headSources.jsp" %>
    <link href="<%=request.getContextPath()%>/css/gallery-materialize.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/loader.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/js/gallery.min.js"></script>
</head>
<body>

<nav class="nav-extended  green darken-3">
    <div class="nav-wrapper">
        <ul id="nav-mobile" class="left hide-on-med-and-down" style="margin-left: 5%">
            <li><a class="material-icons" href="<%=request.getContextPath()%>/book/category" style="cursor: pointer">arrow_back</a></li>
            <li><a class="brand-logo" style="margin-left: 20px">图书列表</a></li>
        </ul>
    </div>
    <div class="nav-content" style="height: 40%">
        <a class="btn-floating btn-large halfway-fab waves-effect waves-light amber" onclick="showCart()">
            <i class="material-icons">shopping_cart</i>
        </a>
    </div>
</nav>

<div class="card-panel centerCard">

    <h3>标签:${typeName}---</h3>
    <div id="portfolio" class="gray">
        <div class="db">
            <div class="b e">
                <c:if test="${!empty lists}">
                    <c:forEach items="${lists}" var="c" varStatus="vs">
                        <c:if test="${vs.count<=9}">
                            <div class="d hx hf gu gallery-item gallery-expand ce polygon" style="font-size">
                                <div class="placeholder">
                                    <div class="gallery-curve-wrapper">
                                        <a class="gallery-cover gray z-depth-3" style="width: 180px">
                                            <img class="responsive-img " src="${c.bookPic}" alt="placeholder" crossorigin="anonymous">
                                        </a>

                                        <div class="gallery-body">
                                            <div class="title-wrapper">
                                                <h3>${c.bookName}</h3>
                                                <span class="gk">价格:${c.bookPrice}元</span>
                                                <div>
                                                    <span class="left">作者:${c.bookAuthor}</span>
                                                    <span class="right">出版社:${c.publisher}</span>
                                                </div>


                                            </div>
                                            <p class="fi" style="text-indent: 28px">${c.bookDescribe}</p>
                                        </div>
                                        <div class="gallery-action">
                                            <a class="btn-floating btn-large waves-effect waves-light"
                                               onclick="addCart(${c.bookId},'${c.bookPic}','${c.bookName}','${c.bookAuthor}','${c.publisher}',${c.bookPrice})">
                                                <i class="material-icons">add_shopping_cart</i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>

    <ul class="pagination center-align">
        <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
        <li class="active"><a href="#!">1</a></li>
        <li class="waves-effect"><a href="#!">2</a></li>
        <li class="waves-effect"><a href="#!">3</a></li>
        <li class="waves-effect"><a href="#!">4</a></li>
        <li class="waves-effect"><a href="#!">5</a></li>
        <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
    </ul>
</div>

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

<%@include file="../common/preloader.jsp" %>

</body>

<script type="text/javascript">

</script>

</html>
