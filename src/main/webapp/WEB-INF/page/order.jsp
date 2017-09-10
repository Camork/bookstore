<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>我的订单</title>
    <%@include file="../common/headSources.jsp" %>
</head>
<body>
<%@include file="../common/navTop.jsp"%>

<div class="container">
    <div class="row">
        <div class="col l2">
            <ul class="collection" id="left_order">
                <li ${param.status==null?"class='active light-blue darken-3'":"" }><a href="${pageContext.request.contextPath}/order/getMyOrders">全部订单</a></li>
                <li ${param.status==0?"class='active light-blue darken-3'":"" }><a href="${pageContext.request.contextPath}/order/getMyOrders?status=0">待付款订单</a></li>
                <li ${param.status==1?"class='active light-blue darken-3'":"" }><a href="${pageContext.request.contextPath}/order/getMyOrders?status=1">待发货订单</a></li>
                <li ${param.status==4?"class='active light-blue darken-3'":"" }><a href="${pageContext.request.contextPath}/order/getMyOrders?status=4">待收货订单</a></li>
                <li ${param.status==3?"class='active light-blue darken-3'":"" }><a href="${pageContext.request.contextPath}/order/getMyOrders?status=3">已退款订单</a></li>
                <li ${param.status==8?"class='active light-blue darken-3'":"" }><a href="${pageContext.request.contextPath}/order/getMyOrders?status=8">已退货订单</a></li>
                <li ${param.status==5?"class='active light-blue darken-3'":"" }><a href="${pageContext.request.contextPath}/order/getMyOrders?status=5">交易成功订单</a></li>
                <li ${param.status==9?"class='active light-blue darken-3'":"" }><a href="${pageContext.request.contextPath}/order/getMyOrders?status=9">交易关闭订单</a></li>
            </ul>
        </div>

        <div class="col l10">
            <c:forEach items="${orders}" var="o" varStatus="vs">
                <div class="card-panel">
                    <div class="panel-heading">
                        下单日期：
                        <fmt:formatDate value="${o.orderDate}" pattern="yyyy-MM-dd" />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 订单号：
                        <a href="${pageContext.request.contextPath}/order/getOrderDetailById?orderId=${o.orderId}">${o.orderCode}</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 订单状态： <span id="orderStatus"> <c:if test="${o.orderStatus==0}">
                        等待付款
                        <div class="right">
										<a class="light-blue-text text-darken-3 waves-effect waves-light" href="#" onclick="handleOrder(${o.orderId},9,'确定要取消这个订单吗？',event);">取消订单</a>
										|
										<a class="btn white light-blue-text text-darken-3 waves-effect" href="${pageContext.request.contextPath}/order/toPay?orderId=${o.orderId}">去付款</a>
									</div>
                    </c:if> <c:if test="${o.orderStatus==1}">
                        未发货
                        <div class="right">
										<a class="btn white light-blue-text text-darken-3 waves-effect" href="#" onclick="handleOrder(${o.orderId},2,'确定要申请退款吗？',event)">申请退款</a>
									</div>
                    </c:if> <c:if test="${o.orderStatus==2}">
                        已提交退款申请，请等待商家处理
                    </c:if> <c:if test="${o.orderStatus==3}">
                        退款成功
                    </c:if> <c:if test="${o.orderStatus==4}">
                        已发货
                        <div class="right">
										<a href="#" onclick="handleOrder(${o.orderId},5,'确定要收货吗？',event)">确认收货</a>
										|
										<a href="#" onclick="handleOrder(${o.orderId},6,'确定要申请退货吗？',event)">申请退货</a>
									</div>
                    </c:if> <c:if test="${o.orderStatus==5}">
                        交易成功
                        <div class="right">
										<a class="btn white light-blue-text text-darken-3 waves-effect" href="${pageContext.request.contextPath}/order/delOrder?orderId=${o.orderId}" onclick="return confirm('确定要删除这个订单吗？')">删除订单</a>
									</div>
                    </c:if> <c:if test="${o.orderStatus==6}">
                        已提交退货申请，请等待商家处理
                    </c:if> <c:if test="${o.orderStatus==7}">退货中</c:if> <c:if test="${o.orderStatus==8}">退货成功</c:if> <c:if test="${o.orderStatus==9}">
                        交易关闭
                        <div class="right">
										<a class="btn white light-blue-text text-darken-3 waves-effect" href="${pageContext.request.contextPath}/order/delOrder?orderId=${o.orderId}" onclick="return confirm('确定要删除这个订单吗？')">删除订单</a>
									</div>
                    </c:if>
							</span>
                    </div>
                    <div class="panel-body">
                        <table>
                            <tbody>
                            <c:set var="orderDetails" value="${o.odetails }" />
                            <c:set var="totalAmount" value="0" />
                            <c:forEach items="${orderDetails}" var="od" varStatus="odvs">
                                <tr>
                                    <td><img src="${pageContext.request.contextPath}${od.odetailPic}" width="30" height="30"></td>
                                    <%--<td><a href="${pageContext.request.contextPath}/goods/getGoodsDetailById?goodsId=${od.goodsId}">${fn:escapeXml(od.odetailName)}</a>&nbsp;&nbsp; ${fn:escapeXml(od.odetailSize)}&nbsp;&nbsp;${fn:escapeXml(od.odetailColor)}</td>--%>
                                    <td>${od.odetailPrice}</td>
                                    <td>${od.odetailNum}</td>
                                    <td>${od.odetailPrice*od.odetailNum }<c:set var="totalAmount" value="${totalAmount+od.odetailPrice*od.odetailNum}" />
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>

                        <table>
                            <tr>
                                <td>
                                    <button class="light-blue darken-3 waves-effect waves-light btn" id="back" type="button" onclick="javascript:location.href='${pageContext.request.contextPath}/order/getOrderDetailById?orderId=${o.orderId}'">查看订单详情</button>
                                </td>
                                <td class="right red-text">总金额（含运费${o.orderPostalfee}元）：${totalAmount+o.orderPostalfee }</td>
                            </tr>
                        </table>

                    </div>
                </div>
            </c:forEach>
        </div>
        <c:if test="${empty orders}">
            <div class="col l2 col offset-l1">对不起，暂无此类订单信息</div>
        </c:if>
    </div>
</div>
<%@include file="../common/userFooter.jsp"%>
</body>
</html>

<script>
    function handleOrder(orderId,status,msg,e){
        if(confirm(msg)){
            var os=$(e.target).parent().parent("#orderStatus");
            //alert($(e.target).parent().parent("#orderStatus").attr("id"))
            $.post(getContextPath()+"/order/handleOrderStatus",{orderId:orderId,status:status},function(result){
                if(checkLogin(result)){
                    if(result.handle=="success"){
                        if(status==2){
                            os.html("已提交退款申请，请等待商家处理");
                        }
                        else if(status==5){
                            os.html("交易成功<div class=\"right\"><a href=\"${pageContext.request.contextPath}/order/delOrder?orderId="+orderId+" onclick=\"return confirm('确定要删除这个订单吗？')\">删除订单</a></div>");
                        }
                        else if(status==6){
                            os.html("已提交退货申请，请等待商家处理");
                        }
                        else if(status==9){
                            os.html("交易关闭<div class=\"right\"><a href=\"${pageContext.request.contextPath}/order/delOrder?orderId="+orderId+" onclick=\"return confirm('确定要删除这个订单吗？')\">删除订单</a></div>");
                        }
                        $("#msgTitle").html("操作成功");
                        $("#msgBody").html("订单操作成功");
                        $("#msgModal").modal();
                    }else if(result.handle=="failure"){
                        $("#msgTitle").html("操作失败");
                        $("#msgBody").html("抱歉，目前的订单状态无法进行此操作");
                        $("#msgModal").modal();
                    }
                    else{
                        $("#msgTitle").html("操作失败");
                        $("#msgBody").html("操作订单失败");
                        $("#msgModal").modal();
                    }
                }
            });
        }
    }
</script>