<%--
  Created by IntelliJ IDEA.
  User: liuhexin
  Date: 2016/6/1
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../common/js.jsp" %>
    <title></title>
</head>
<body>
<input type="button" onclick="es();" value="刷新索引"/>
<input type="button" onclick="refreshData();" value="获取数据全量"/>
<input type="button" onclick="refreshQuickLink();" value="刷新首页浮层"/>
<input type="button" onclick="refreshWeekendCache();" value="刷新首页周边去哪玩"/>
<input type="button" onclick="refreshIndexRecomm();" value="刷新首页楼层推荐"/>
<br>
<!-- 
订单号：<input type="text" id="orderId">
<input type="button" value="退款" onclick="refreshRefund();">
 -->
<script>
    function es() {
        if (confirm("确定要刷新索引？")) {
            $.ajax({
                type: "POST",
                async: false,
                url: "${_domain}/admin/refresh/refreshES.html",
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    if (data == "true") {
                        alert("成功");
                    }
                    else {
                        alert("失败");
                    }
                },
                error: function () {
                    alert("失败");
                }
            });
        }

    }

    function refreshQuickLink() {
        if (confirm("确定要刷新？")) {
            $.ajax({
                type: "POST",
                async: false,
                url: "${_domain}/admin/refresh/qlc.html",
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    if (data == "true") {
                        alert("成功");
                    }
                    else {
                        alert("失败");
                    }
                },
                error: function () {
                    alert("失败");
                }
            });
        }
    }


    function refreshData() {
        if (confirm("确定要刷新？")) {
            $.ajax({
                type: "POST",
                async: false,
                url: "${_domain}/admin/refresh/refreshData.html",
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    if (data == "true") {
                        alert("成功");
                    }
                    else {
                        alert("失败");
                    }
                },
                error: function () {
                    alert("失败");
                }
            });
        }
    }

    function refreshWeekendCache() {
        if (confirm("确定要刷新？")) {
            $.ajax({
                type: "POST",
                async: false,
                url: "${_domain}/admin/refresh/wkc.html",
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    if (data == "true") {
                        alert("成功");
                    }
                    else {
                        alert("失败");
                    }
                },
                error: function () {
                    alert("失败");
                }
            });
        }
    }

    function refreshIndexRecomm() {
        if (confirm("确定要刷新？")) {
            $.ajax({
                type: "POST",
                async: false,
                url: "${_domain}/admin/refresh/wkc.html",
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    if (data == "true") {
                        alert("成功");
                    }
                    else {
                        alert("失败");
                    }
                },
                error: function () {
                    alert("失败");
                }
            });
        }
    }

    function refreshRefund() {
    	var orderId = $("#orderId").val();
    	if(orderId) {
	        if (confirm("确定要刷新？")) {
	            $.ajax({
	                type: "POST",
	                async: false,
	                url: "${_domain}/admin/refresh/refund.html?orderId=" + orderId,
	                contentType: "application/json; charset=utf-8",
	                success: function (data) {
	                    if (data == "true") {
	                        alert("成功");
	                    }
	                    else {
	                        alert("失败");
	                    }
	                },
	                error: function () {
	                    alert("失败");
	                }
	            });
	        }
    	}else{
    		alert("请输入订单号");
    	}
    }
</script>
</body>
</html>
