<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title></title>
	<link rel=stylesheet type=text/css href="/css/zzsc.css"/>
</head>
<body>
<div id="firstpane" class="menu_list">
    <p class="menu_head current">订单管理</p>
    <div style="display:block" class="menu_body" >
      	<a href="javascript:" onclick="loadOrder(2)">已付款</a>
		<a href="javascript:" onclick="loadOrder(3)">待确认</a>
		<a href="javascript:" onclick="loadOrder(4)">已确认</a>
    </div>
    
    <p class="menu_head">同程退款管理</p>
    <div style="display:none" class="menu_body" >
      	<a href="javascript:" onclick="cancel()">同程退款</a>
    </div>
    
    <!-- <p class="menu_head">下单管理</p>
    <div style="display:none" class="menu_body" >
      	<a href="javascript:" onclick="push()">同程下单</a>
    </div> -->
    
    <p class="menu_head">退款管理</p>
    <div style="display:none" class="menu_body" >
      	<a href="javascript:" onclick="orderRefund()">申请退款</a>
    </div>
    
</div>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type=text/javascript>
	$(document).ready(function(){
		$("#firstpane .menu_body:eq(0)").show();
		$("#firstpane p.menu_head").click(function(){
			$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
			$(this).siblings().removeClass("current");
		});
	});
	
	function loadOrder(orderStatus) {
		top.main.location.href = "/mock/order/list.html?orderStatus="+orderStatus;
	}
	
	function orderRefund() {
		top.main.location.href = "/mock/refund/list.html";
	}
	
	function push() {
		top.main.location.href = "/mock/push/list.html";
	}
	
	function cancel() {
		top.main.location.href = "/mock/cancel/list.html";
	}
</script>
</body>
</html>
