<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>豆豆书城</title>
    <%@include file="common/headSources.jsp" %>
    <link href="<%=request.getContextPath()%>/css/gallery-materialize.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/js/gallery.min.js"></script>

</head>

<body>
<%@include file="common/navTop.jsp" %>

<div class="content container">
    <div class="row">
        <span class="new badge left green" data-badge-caption="新书速递:"></span>
        <a class="waves-effect waves-green btn-flat right" href="<%=request.getContextPath()%>/book/category">
            <i class="material-icons right">list</i>更多分类</a>
    </div>

    <div id="portfolio" class="gray">
        <div class="db">
            <div class="b e">

                <c:if test="${!empty hotBooks}">
                    <c:forEach items="${hotBooks}" var="c" varStatus="vs">

                        <c:if test="${vs.count<=30}">

                            <div class="d hx hf gu gallery-item gallery-expand ce polygon">
                                <div class="placeholder">
                                    <div class="gallery-curve-wrapper">
                                        <a class="gallery-cover gray z-depth-3" style="width: 170px ">
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
                                                <i class="material-icons">add_shopping_cart</i></a>
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
</div>

<%@include file="common/userFooter.jsp" %>
</body>

</html>

<script>
    $(window).bind("scroll", function () {
        let sTop = parseInt($(window).scrollTop());

        if (sTop >= 50)
            $(".page-title").hide(500);
        else
            $(".page-title").show(500);

        let offGap = parseInt($("#faker_content").offset().top - $(window).scrollTop());
        if (offGap > -128) {
            $("nav").removeClass("z-depth-3");
            $(".fb").css('display', 'none');
        } else if (offGap <= -128) {
            $("nav").addClass("z-depth-3");
            $(".fb").css('display', 'block');
        }
    });
</script>

<style>
    #head_content {
        background-color: #4285f4 !important;
    }

    .nav-extended {
        background-color: #4285f4 !important;
    }

</style>