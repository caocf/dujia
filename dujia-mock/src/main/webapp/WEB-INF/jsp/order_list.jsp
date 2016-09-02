<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title></title>
<link rel=stylesheet type=text/css href="/css/tablecloth.css">
</style>
</head>
<body>
	位置：订单管理-》
	<c:if test="${orderStatus == 2 }">已付款</c:if>
	<c:if test="${orderStatus == 3 }">待确认</c:if>
	<c:if test="${orderStatus == 4 }">已确认</c:if>
	<hr>
	<span style="padding-left: 50px;">
	订单号：<input type="text" id="orderId" name="orderId" placeholder="请输入订单号" value="${orderId }" maxlength="10" >
	<input type="button" value="查询" onclick="orderList()">
	<span style="color: red;">${error }</span>
	</span>
	<br>
	<br>
	<hr>
	<table cellspacing="0" cellpadding="0">
		<tbody>
			<tr>
				<th>编号</th>
				<th>订单号</th>
				<th>订单状态</th>
				<th>线路名称</th>
				<th>套餐名称</th>
				<th>套餐价格</th>
				<th>数量</th>
				<th>订单金额</th>
				<th>下单时间</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${orders }" var="order" varStatus="status">
				<tr>
					<td>${status.index + 1 }</td>
					<td>${order.orderId }</td>
					<td>
						<c:if test="${orderStatus == 2 }">已付款</c:if>
						<c:if test="${orderStatus == 3 }">待确认</c:if>
						<c:if test="${orderStatus == 4 }">已确认</c:if>
					</td>
					<td>${order.productName }</td>
					<td>${order.packageName }</td>
					<td>${order.packagePrice }</td>
					<td>${order.buyCount }</td>
					<td>${order.orderAmount }</td>
					<td><fmt:formatDate value="${order.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<c:if test="${orderStatus == 2 }"><input type="button" value="待确认" onclick="updateOrderStatus('${order.orderId}')"></c:if>
						<c:if test="${orderStatus == 3 }"><input type="button" value="已确认" onclick="updateOrderStatus('${order.orderId}')"></c:if>
						<c:if test="${orderStatus == 4 }"><input type="button" value="已完成" onclick="updateOrderStatus('${order.orderId}')"></c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="/js/tablecloth.js"></script>
	<script>
		$("#orderId").focus();
	
		function orderList() {
			window.location.href="/mock/order/list.html?orderStatus=${orderStatus}&orderId="+$("#orderId").val();
		}
		
		$("#orderId").keydown(function(event){
			if(event.keyCode==13) {
				window.location.href="/manual/order/list.html?orderStatus=${orderStatus}&orderId="+$("#orderId").val();
			}
		});
		
		function updateOrderStatus(orderId) {
			var str = orderId + " 更新状态为【";
			if('${orderStatus}' == 2) {
				str += "待确认】";
			}else if('${orderStatus}' == 3) {
				str += "已确认】";
			}else if('${orderStatus}' == 4) {
				str += "已完成】";
			}
			if(confirm("确定要更新订单状态吗？")) {
				$.ajax({
					type : "POST",
					async : false,
					dataType: "json",
					url : "/mock/order/update.html",
					data : {'orderId':orderId, 'orderStatus':'${orderStatus}'},
					success : function(data) {
						if(data.code == "Y") {
							alert(str+"成功");
							window.location.href="/mock/order/list.html?orderStatus=${orderStatus}";
						}else{
							str += "失败";
							if(data.message) {
								str += "\n【"+data.message+"】";
							}
							alert(str);
						}
					}
				});
			}
		}
	</script>
</body>
</html>
