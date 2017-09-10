<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <title>我的订单</title>
    <%@include file="../common/headSources.jsp" %>
</head>

<body>
<%@include file="../common/navTop.jsp" %>

<div class="container">
    <div class="row">

        <div class="col l2">
            <div class="collection" id="left_order">
                <a class="collection-item" href="${pageContext.request.contextPath}/order/getMyOrders">全部订单</a>
                <a class="collection-item" href="${pageContext.request.contextPath}/order/getMyOrders?status=0">待付款订单</a>
                <a class="collection-item" href="${pageContext.request.contextPath}/order/getMyOrders?status=1">待发货订单</a>
                <a class="collection-item" href="${pageContext.request.contextPath}/order/getMyOrders?status=2">已发货订单</a>
                <a class="collection-item" href="${pageContext.request.contextPath}/order/getMyOrders?status=3">交易成功订单</a>
                <a class="collection-item" href="${pageContext.request.contextPath}/order/getMyOrders?status=4">交易关闭订单</a>
            </div>
        </div>

        <div class="col l10">
            <c:forEach items="${orders}" var="o" varStatus="vs">
                <div class="card-panel">
                    <div class="panel-heading">
                        下单日期：<fmt:formatDate value="${o.orderDate}" type="date"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        订单号：<a>${o.orderCode}</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 订单状态： <span style="color: red" id="orderStatus">
                        <c:if test="${o.orderStatus==0}">
                            等待付款
                            <div class="right">
                            <a class="light-blue-text text-darken-3 waves-effect waves-light" href="#"
                               onclick="handleOrder(${o.orderId},4,'确定要取消这个订单吗？',event);">取消订单</a>
                            <a class="btn white light-blue-text text-darken-3 waves-effect"
                               onclick="handleOrder(${o.orderId},1,'确定要付款吗？',event);">去付款</a>
                            </div>
                        </c:if>
                        <c:if test="${o.orderStatus==1}">
                            未发货
                            <div class="right">
                                <a class="btn white light-blue-text text-darken-3 waves-effect" href="#"
                                   onclick="handleOrder(${o.orderId},4,'确定要取消订单吗？',event)">取消订单</a>
                            </div>
                        </c:if>
                        <c:if test="${o.orderStatus==2}">
                            已发货
                            <div class="right">
                                <a href="#" onclick="handleOrder(${o.orderId},3,'确定要收货吗？',event)">确认收货</a>
                                <a href="#" onclick="handleOrder(${o.orderId},4,'确定要取消订单吗？',event)">取消订单</a>
                            </div>
                        </c:if>
                        <c:if test="${o.orderStatus==3}">
                            交易成功
                            <div class="right">
                                <a class="btn white light-blue-text text-darken-3 waves-effect"
                                   href="${pageContext.request.contextPath}/order/delOrder?orderId=${o.orderId}"
                                   onclick="return confirm('确定要删除这个订单吗？')">删除订单</a>
                            </div>
                        </c:if>

                         <c:if test="${o.orderStatus==4}">
                             订单已取消
                             <div class="right">
                                 <a class="btn white light-blue-text text-darken-3 waves-effect"
                                    href="${pageContext.request.contextPath}/order/delOrder?orderId=${o.orderId}"
                                    onclick="return confirm('确定要删除这个订单吗？')">删除订单</a>
                            </div>
                         </c:if>
                    </span>
                    </div>
                    <div class="panel-body">
                        <table>
                            <thead>
                            <tr>
                                <th>图片</th>
                                <th>名字</th>
                                <th>单价</th>
                                <th>数量</th>
                                <th>总价</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${o.odetails}" var="od" varStatus="odvs">
                                <tr>
                                    <td><img src="${od.bookPic}" width="50"></td>
                                    <td>${od.bookName}</td>
                                    <td>${od.orderPrice}</td>
                                    <td>${od.bookNum}</td>
                                    <td>${od.bookNum*od.orderPrice}</td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>

                        <table>
                            <tr>
                                <td>
                                    <button class="light-blue darken-3 waves-effect waves-light btn" id="back" type="button">
                                        查看订单详情
                                    </button>
                                </td>
                                <td class="right red-text">总金额:${o.totalAmount}元</td>
                            </tr>
                        </table>

                    </div>
                </div>
            </c:forEach>
        </div>
        <c:if test="${empty orders}">
            <h4 style="text-align: center">对不起，暂无此类订单信息</h4>
        </c:if>
    </div>
</div>


</body>

</html>

<style>
    #head_content {
        background-color: #34a853 !important;
    }

    .nav-extended {
        background-color: #34a853 !important;
    }
</style>

<script>
    $(".page-title").text("我的订单");

    function handleOrder(orderId, status, msg, e) {
        if (confirm(msg)) {
            var os = $(e.target).parent().parent("#orderStatus");
            $.post(getContextPath() + "/order/handleOrderStatus", {orderId: orderId, status: status}, function (result) {
                if (result.handle == "success") {
                    if (status == 2) {
                        os.html("已提交退款申请，请等待商家处理");
                    }
                }


            });
        }
    }
</script>

