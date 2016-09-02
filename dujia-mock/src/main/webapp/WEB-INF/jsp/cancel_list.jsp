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
	位置：同程退款管理-》同程退款
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
						<c:if test="${order.orderStatus == 3 }">待确认</c:if>
						<c:if test="${order.orderStatus == 4 }">已确认</c:if>
						<c:if test="${order.orderStatus == 7 }">已完成</c:if>
					</td>
					<td>${order.productName }</td>
					<td>${order.packageName }</td>
					<td>${order.packagePrice }</td>
					<td>${order.buyCount }</td>
					<td>${order.orderAmount }</td>
					<td><fmt:formatDate value="${order.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<input type="button" value="同程退款" onclick="updateOrderStatus('${order.orderId}')">
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
			window.location.href="/mock/cancel/list.html?orderId="+$("#orderId").val();
		}
		
		$("#orderId").keydown(function(event){
			if(event.keyCode==13) {
				window.location.href="/manual/cancel/list.html?orderId="+$("#orderId").val();
			}
		});
		
		function updateOrderStatus(orderId) {
			if(confirm("确定要同程退款吗？")) {
				$.ajax({
					type : "POST",
					async : false,
					dataType: "json",
					url : "/mock/cancel/cancel.html",
					data : {'orderId':orderId},
					success : function(data) {
						if(data.code == "Y") {
							alert("订单同程退款成功");
							window.location.href="/mock/cancel/list.html";
						}else{
							alert("订单同程退款失败");
						}
					}
				});
			}
		}
	</script>
</body>
</html>
