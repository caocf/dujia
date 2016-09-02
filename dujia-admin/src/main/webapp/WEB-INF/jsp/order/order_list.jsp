<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查询订单</title>
<%@ include file="../common/css.jsp"%>
<%@ include file="../common/js.jsp"%>
<style>
.cx {
	text-align: right;
	margin-right: 5px;
	display: inline-block;
	width: 80px;
}
</style>
</head>
<body style="background: #afb8bf; overflow-x: hidden; overflow-y: scroll; *overflow-y: hidden">
	<table>
		<tr>
			<td background="${_domain}/images/RigNav.gif" height="31">
				<table>
					<tr>
						<td class="TextRig"><img src="${_domain}/images/nav_3.gif" style="width: 11" height="7" /></td>
						<td style="width: 97%" class="NavText">您的位置：度假订单管理&gt;订单查询</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div class="RightBg">
					<div class="RightTab">
						<form action="${_domain}/admin/order/order_list.html" method="post" id="pageform" name="orderForm">
							<input type="hidden" id="ACT" name="ACT" value="${ACT}"/>
							<table id="searchTable">
								<tr>
									<td><span class="cx">订单编号</span> <input style="width: 130px;" type="text" id="orderId" name="orderId" value='${order.orderId}' /></td>
									<td><span class="cx">联系人手机</span> <input style="width: 130px;" type="text" id="contactsTelphone" name="contactsTelphone" value='${order.contactsTelphone}' /></td>
									<td><span class="cx">会员账号</span> <input style="width: 130px;" type="text" id="userName" name="userName" value="${order.userName}" /></td>
									<td><span class="cx">下单时间</span>
										<input id="orderStartTime" name="orderStartTime" type="text" readonly="readonly" class="Wdate" style="width: 130px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<fmt:formatDate value="${order.orderStartTime}" pattern="yyyy-MM-dd" />" />&nbsp; -
										<input id="orderEndTime" name="orderEndTime" type="text" readonly="readonly" class="Wdate" style="width: 130px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'orderStartTime\',{d:0});}'})" value="<fmt:formatDate value="${order.orderEndTime}" pattern="yyyy-MM-dd" />" />&nbsp;&nbsp;</td>
								</tr>
								<tr>
									<td><span class="cx">线路名称</span> <input style="width: 130px;" type="text" id="productName" name="productName" value='${order.productName}' /></td>
									<td><span class="cx">景点名称</span> <input style="width: 130px;" type="text" id="packageName" name="packageName" value='${order.packageName}' /></td>
									<td><span class="cx">订单来源</span> <select id="orderSource" name="orderSource" style="width: 130px; height: 22px">
										<option value="">全部</option>
										<option value="1" <c:if test="${order.orderSource==1 || order.orderSource==0}">selected="selected"</c:if>>WEB</option>
										<option value="2" <c:if test="${order.orderSource==2}">selected="selected"</c:if>>WAP</option>
										<option value="3" <c:if test="${order.orderSource==3}">selected="selected"</c:if>>APP</option>
									</select></td>
									<td><span class="cx">支付时间</span>
										<input id="payStartTime" name="payStartTime" type="text" readonly="readonly" class="Wdate" style="width: 130px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<fmt:formatDate value="${order.payStartTime}" pattern="yyyy-MM-dd" />" />&nbsp; -
										<input id="payEndTime" name="payEndTime" type="text" readonly="readonly" class="Wdate" style="width: 130px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'payStartTime\',{d:0});}'})" value="<fmt:formatDate value="${order.payEndTime}" pattern="yyyy-MM-dd" />" />&nbsp;&nbsp;</td>
								</tr>
								<tr>
									<td><span class="cx">支付订单号</span> <input style="width: 130px;" type="text" id="transNo" name="transNo" value='${order.transNo}' />
									</td>
									<td><span class="cx">景点所在城市</span>
										<input type=text id="packageCityName" style="width: 130px;" name="packageCityName" value="${order.packageCityName}" onkeyup="findCityList('packageCity')" onFocus="findCityList('packageCity');" onBlur="$('#packageCityDiv').hide();"/>
										<div id="packageCityDiv" style="margin-left: 88px;position:fixed;line-height:22px; background: #FFFFFF; border:1px solid #C4C4C4;overflow :auto;width: 130px;height: 250px;display:none;" ></div>
									</td>
									<td>
										<span class="cx">主题标签</span><input style="width: 130px;" type="text" id="recomTitle" name="recomTitle" value='${order.recomTitle}' />
									</td>
									<td><span class="cx">订单状态</span>
										<select id="orderStatus" name="orderStatus" style="width: 130px; height: 22px" onchange="changeStatus();">
										</select>
									</td>
								</tr>
								<tr>
									<td>
										<span class="cx">支付状态</span> <select id="payStatus" name="payStatus" style="width: 130px; height: 22px">
										<option value="" <c:if test="${order.payStatus!=0 && order.payStatus!=1}">selected="selected"</c:if>>全部</option>
										<option value="0" <c:if test="${order.payStatus==0}">selected="selected"</c:if>>未付款</option>
										<option value="1" <c:if test="${order.payStatus==1}">selected="selected"</c:if>>已付款</option>
									</select>
									</td>
									<td>
										<span class="cx">游玩天数</span>
										<select id="travelDay" name="travelDay" style="width: 130px; height: 22px" onchange="changeDays();">
										</select>
									</td>
									<td>
										<span class="cx">产品编号</span>
										<input style="width: 130px;" type="text" id="productId" name="productId" value='${order.productId}' />
									</td>
									<td colspan="2"  align="right">
										<input style="width: 80px; height: 30px; color: #485058" type="button" onclick="submitForm();" value="查询" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<c:if test="${ACT==1 }">
											<input type="button" name="addVenderBtn" value="导出数据" style="width: 120px; height: 30px; color: #485058; margin: 0 20px 0 -10px;" onclick="excelData()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</c:if>
									</td>
								</tr>
							</table>
						</form>
						<input type="hidden" id="orderStatusValue" name="orderStatusValue" value="${order.orderStatus}"/>
						<input type="hidden" id="travelDayValue" name="travelDayValue" value="${order.travelDay}"/>
					</div>
					<div class="H10"></div>
					<div class="RightTab">
						<table>
							<tr>
								<td colspan="2">
									<table class="LBoder">
										<thead>
											<tr class="TextCen fB GDDray">
												<td style="width: 100px" class="LBodRit">订单编号</td>
												<td style="width: 100px" class="LBodRit">第三方订单号</td>
												<td style="width: 100px" class="LBodRit">会员账号</td>
												<td style="width: 20px" class="LBodRit">所在城市</td>
												<td style="width: 800px" class="LBodRit">线路名称</td>
												<td style="width: 500px" class="LBodRit">主题标签</td>
												<td style="width: 500px" class="LBodRit">景点名称</td>
												<td style="width: 100px" class="LBodRit">游玩天数</td>
												<td style="width: 60px" class="LBodRit">数量</td>
												<td style="width: 100px" class="LBodRit">订单金额</td>
												<td style="width: 120px" class="LBodRit">出游时间</td>
												<td style="width: 120px" class="LBodRit">下单时间</td>
												<td style="width: 100px" class="LBodRit">支付状态</td>
										    	<td style="width: 100px" class="LBodRit">订单状态</td>
												<c:if test="${ACT==1 }">
												<td style="width: 50px" class="LBodRit">支付订单号</td>
												</c:if>
											</tr>
										</thead>
										<tbody>
											<c:forEach varStatus="vstatus" var="order" items="${orders }">
												<tr class="TextCen LBodTop">
													<td class="LBodRit LBodTop"><a href="${_domain}/admin/order/orderDetail.html?orderId=${order.orderId}&ACT=${ACT}"><font color="blue">${order.orderId}</font></a></td>
													<td class="LBodRit LBodTop" style="color: gray;">${order.venderOrderId}</td>
													<td class="LBodRit LBodTop" style="color: gray;" title="${order.extended1}">${order.userName}</td>
													<td class="LBodRit LBodTop" style="color: gray;">${order.packageCityName }</td>
													<td class="LBodRit LBodTop" style="color: gray;">${order.productName }</td>
													<td class="LBodRit LBodTop" style="color: gray;">${order.recomTitle }</td>
													<td class="LBodRit LBodTop" style="color: gray;">${order.packageName }</td>
													<td class="LBodRit LBodTop" style="color: gray;">${order.travelDayDisplay }</td>
													<td class="LBodRit LBodTop" style="color: gray;">${order.buyCount }</td>
													<td class="LBodRit LBodTop" style="color: gray;"><font color="red"><fmt:formatNumber pattern="0.00" value="${order.orderAmount}" /></font></td>
													<td class="LBodRit LBodTop" style="color: gray;"><fmt:formatDate value="${order.travelStartTime }" pattern="yyyy-MM-dd EEEE" /></td>
													<td class="LBodRit LBodTop" style="color: gray;"><fmt:formatDate value="${order.orderTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
												    <td class="LBodRit LBodTop" style="color: gray;">
														<c:if test="${order.payStatus==0}" >未付款</c:if>
														<c:if test="${order.payStatus==1}" >已付款</c:if>
													</td>
													<td class="LBodRit LBodTop" style="color: gray;">${order.orderStatusDisplay }</td>
													<c:if test="${ACT==1 }">
													<td class="LBodRit LBodTop" style="color: gray;">${fn:replace(order.transNo, ',', '<br>') }</td>
													</c:if>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="11" class="td1">
									<div align="right">
										<%--<font color="red">sap收款：用户支付成功&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;sap收入：给永乐结算订单 </font>--%>
										<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
										<c:out value="${pageResult}" escapeXml="false" />
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
	</table>
	<div id="loading" style="position:fixed !important;position:absolute;top:0;left:0;height:100%; width:100%; z-index:999; background:#000 url(${_domain}/images/loading.gif) no-repeat center center; opacity:0.6; filter:alpha(opacity=60);font-size:14px;line-height:20px;display:none">
		<p id="loading-one" style="color: #fff; position: absolute; top: 50%; left: 50%; margin: 20px 0 0 -50px; padding: 3px 10px;">提交中，请稍后...</p>
	</div>
	<div id="downloading" style="position: fixed !important; position: absolute; top: 0; left: 0; height: 100%; width: 100%; z-index: 999; background: #000 url() no-repeat center center; opacity: 0.6; filter: alpha(opacity =     60); font-size: 14px; line-height: 20px; display: none">
		<p id="loading-one" style="color: #fff; position: absolute; top: 50%; left: 50%; margin: 20px 0 0 -50px; padding: 3px 10px;">数据导出中，请稍后...</p>
	</div>

<script type="text/javascript">

	$(function() {
		getOrderStatus();
		getTravelDays();
	})
	function getOrderStatus() {
		var select = $("#orderStatusValue").val();
		$("#orderStatus").children().remove();
		$.ajax({
			type : "POST",
			async : false,
			url : "${_domain}/admin/order/getOrderStatus.html?selected="+select,
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#orderStatus").append(data);
			},
			error : function() {
				$("#orderStatus").append('<option value="">无数据</option>');
			}
		});
	}
	function getTravelDays() {
		var select = $("#travelDayValue").val();
		$("#travelDay").children().remove();
		$.ajax({
			type : "POST",
			async : false,
			url : "${_domain}/admin/order/getTravelDays.html?selected="+select,
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#travelDay").append(data);
			},
			error : function() {
				$("#travelDay").append("<option value=''>无数据</option>");
			}
		});
	}
	function submitForm() {
		$("#pageform").submit();
	}
	function smsMessage(type, id) {
		var send = confirm("确认推送吗?");
		if (send) {
			$("#loading").show();
			$.ajax({
				url : "${_domain}/admin/order/smsMessage",
				type : "POST",
				cache : false,
				async : false,
				data : {
					"type" : type,
					"id" : id
				},
				success : function(data) {
					debugger;
					$("#loading").hide();
					alert(data.info);

					/* if(data=='success'){
						alert("取消成功!");
						window.location.href='${_domain}/admin/order/queryOrderListByPage';
					}else if(data=='failure'){
						alert("取消失败!");
					} */
				}
			});
		}
	}
	function excelData() {
		var excel = confirm("确认导出订单数据吗?");
		if (excel) {
			$("#downloading").show();
			/* var localOrderCode = $("#localOrderCode").val();
			var concatMobile = $("#concatMobile").val(); */
			var param = $("#pageform").serialize();
			param = decodeURIComponent(param, true)
			/* jQuery.ajax({
				url : '${_domain}/admin/order/excelData',
				data : $("#pageform").serialize(),
				type : "POST",
				dataType:"JSON",
				async: false,
				success : function() {
					$("#downloading").hide();
				}
			}); */
			debugger;
			window.location.href = "${_domain}/admin/order/excelData.html?" + param;
			$("#downloading").hide();
		}

	}
	function changeStatus(){
		var select = $("#orderStatus").val();
		$("#orderStatusValue").attr("value",select);
	}
	function changeDays(){
		var select = $("#travelDay").val();
		$("#travelDayValue").attr("value",select);
	}
	function findCityList(key) {
		$("#"+key+"Div").show();
		$.ajax({
			type : "POST",
			async : false,
			url : "${_domain}/admin/basedata/findCityInfoByName.html",
			data : {
				"cityName" : $("#"+key+"Name").val()
			},
			success : function(data) {
				var str = "";
				var city = eval("(" + data + ")");
				if( data =='[]' || city.length == 0 ){
					$("#"+key+"Id").val('');
				}else{
					for(var i=0; i<city.length; i++){
						str += "<span style='display:block;' id='"+city[i].id+"_"+key+"_span'onMouseOver='selectCityInfo("+city[i].id+",\""+city[i].name+"\",\""+key+"\",\""+city[i].pinyin+"\")' onMouseOut='removeCss("+city[i].id+",\""+key+"\")'>" + city[i].name + "</span>";
					}
				}
				$("#"+key+"Div").html(str);
			},
			error : function() {
				alert("提交失败!");
			}
		});
	}
	function selectCityInfo(cityId,cityName,key,cityPinyin){
		//$("#"+key+"Id").val(cityId);
		$("#"+key+"Name").val(cityName);
		$("#"+cityId+"_"+key+"_span").css("background-color","#FFD2A6").css("cursor","pointer");
	}
	function removeCss(cityId,key){
		$("#"+cityId+"_"+key+"_span").css("background-color","#FFFFFF");
	}
</script>
</body>
</html>
