<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>订单详情</title>
<%@ include file="../common/css.jsp"%>
<%@ include file="../common/js.jsp"%>
<style type="text/css">
#nav ul{list-style:none;line-height:40px;}
#nav li{display:block;float:left;}
#nav a{display:block;color:#FF6347;none;padding:0 20px;}
#nav a:hover{background-color:#A6A6A6;font-weight:bold;}
li.activeing  > a{background-color:#A6A6A6;}
</style>
</head>
<body style="background:#afb8bf;overflow-x:hidden;overflow-y:scroll;*overflow-y:hidden">
	<form id="pageform">
		<table>
	        <tr>
	           <td background="${_domain}/images/RigNav.gif" height="31">
				    <table>
			          <tr>						
						  <td class="TextRig"><img src="${_domain}/images/nav_3.gif" width="11" height="7" /></td>						
						  <td style="width:97%" class="NavText DText">您的位置：度假订单管理&gt;订单查询&gt;订单详情</td>
					  </tr>
					</table>
				</td>
	        </tr>
		   	<tr>
		   		<td>
					<div class="RightBg">
						<div class="RightTab" >
				   			<table class="LBoder">
				   				<tr >
				   					<td class="NavText" colspan="5">订单信息</td>
					   				<td align="right">
				   					<c:if test="${!(ACT eq 'OTHER') }">
					   						<input style="width: 80px; height: 30px; color: #485058" type="button" onclick="back2list();" value="返回订单列表"/>&nbsp;&nbsp;
				   					</c:if>
					   				</td>
				   				</tr>
				   				<tr class="TextCen LBodTop">
				   					<td class="LBodRit LBodTop fB" style="width: 10%">订单编号</td>
				   					<td class="LBodRit LBodTop" style="width: 20%">${order.orderId }</td>
				   					<td class="LBodRit LBodTop fB" style="width: 10%">会员账号</br>(ID)</td>
				   					<td class="LBodRit LBodTop" style="width: 20%">${order.userName}<br>(ID:${order.userId})</td>
				   					<td class="LBodRit LBodTop fB" style="width: 10%">订单来源</td>
				   					<td class="LBodRit LBodTop" style="width: 20%">
				   						<c:if test="${order.orderSource =='1'}">WEB</c:if>
				   					</td>
				   				</tr>
				   				<tr class="TextCen LBodTop">
				   					<td class="LBodRit LBodTop fB">订单总额</td>
				   					<td class="LBodRit LBodTop" ><font color="red">¥<fmt:formatNumber pattern="0.00" value="${order.orderTotalAmount}" /></font></td>
				   					<td class="LBodRit LBodTop fB" >应付金额</td>
				   					<td class="LBodRit LBodTop" ><font color="red">¥<fmt:formatNumber pattern="0.00" value="${order.paymentAmount}" /></font></td>
				   					<td class="LBodRit LBodTop fB" >下单时间</td>
				   					<td class="LBodRit LBodTop"><fmt:formatDate value="${order.orderTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				   				</tr>
				   				<tr class="TextCen LBodTop">
				   					<td class="LBodRit LBodTop fB">供应商订单号</td>
				   					<td class="LBodRit LBodTop">${order.venderOrderId}</td>
				   					<td class="LBodRit LBodTop fB" >支付金额</td>
				   					<td class="LBodRit LBodTop" ><font color="red">¥<fmt:formatNumber pattern="0.00" value="${order.paymentAmount}" /></font></td>
				   					<td class="LBodRit LBodTop fB">订单状态</td>
				   					<td class="LBodRit LBodTop LBodTop"><font color="blue">${order.extended2}</font></td>
				   				</tr>
				   			</table>
			   			</div>
		   			</div>
		   		</td>
		   	</tr>
		   	
		   	<tr>
		   		<td>
			   		<div class="RightBg">
						<div class="RightTab">
				   			<table class="LBoder">
				   				<tr>
				   					<td class="NavText" colspan="6">商品信息</td>
				   				</tr>
				   				<tr class="TextCen LBodTop">
				   					<td class="LBodRit LBodTop fB"  style="width: 10%">线路类型</td>
				   					<td class="LBodRit LBodTop" style="width: 20%">周边游-酒景套餐</td>
				   					<td class="LBodRit LBodTop fB"  style="width: 10%">线路名称</td>
				   					<td class="LBodRit LBodTop" style="width: 20%">${product.subName }</td>
				   					<td class="LBodRit LBodTop fB"  style="width: 10%">套餐名称</td>
				   					<td class="LBodRit LBodTop" style="width: 20%">${order.packageName }</td>
				   				</tr>
				   				<tr class="TextCen LBodTop">
				   					<td class="LBodRit LBodTop fB">出游天数</td>
				   					<td class="LBodRit LBodTop">${order.extended1}</td>
				   					<td class="LBodRit LBodTop fB" >出游日期</td>
				   					<td class="LBodRit LBodTop">
										<fmt:formatDate value="${order.travelStartTime }" pattern="yyyy-MM-dd" />
									</td>
				   					<td class="LBodRit LBodTop fB"  >出游人数</td>
				   					<td class="LBodRit LBodTop">
				   						${order.adultNum }成人
										<c:if test="${order.childNum!=null && order.childNum>0}">
											${order.childNum}儿童
										</c:if>
									</td>
				   				</tr>
				   				<tr class="TextCen LBodTop">
				   					<td class="LBodRit LBodTop fB" >产品编号</td>
				   					<td class="LBodRit LBodTop">${product.productId}</td>
				   					<td class="LBodRit LBodTop fB"  >目的地城市</td>
				   					<td class="LBodRit LBodTop">${product.cityName }</td>
				   					<td class="LBodRit LBodTop fB">套餐数量</td>
				   					<td class="LBodRit LBodTop">${order.buyCount}</td>
				   				</tr>
								<tr class="TextCen LBodTop">
									<td class="LBodRit LBodTop fB">主题</td>
									<td class="LBodRit LBodTop" colspan="5" align="left">
											${recomTitle}
									</td>
								</tr>
				   				<c:forEach items="${packageDetails}" var="packageDetail" varStatus="packageIndex">
									<c:if test="${packageDetail!=null}">
										<c:if test="${packageDetail.resType==0}">
											<tr class="LBoder">
												<td class="NavText" colspan="6">酒店信息</td>
											</tr>
											<tr class="TextCen LBodTop">
												<td class="LBodRit LBodTop fB" >酒店名称</td>
												<td class="LBodRit LBodTop">${packageDetail.resName}</td>
												<td class="LBodRit LBodTop fB">房间信息</td>
												<td class="LBodRit LBodTop">${packageDetail.resProName }</td>
												<td class="LBodRit LBodTop fB" >入住信息</td>
												<td class="LBodRit LBodTop">${packageDetail.proContainCount }间</td>
											</tr>
											<tr class="TextCen LBodTop">
												<td class="LBodRit LBodTop fB" >入住退房</td>
												<td class="LBodRit LBodTop">
												<c:forEach items="${orderDetails}" var="orderDetail" varStatus="orderDetailIndex">
													<c:if test="${orderDetail.resourceId==packageDetail.relatedId && orderDetail.resourceType==0}">
														<fmt:formatDate value="${orderDetail.useStartTime }" pattern="yyyy-MM-dd EEEE HH:mm" /> 入住
														<br>
														<fmt:formatDate value="${orderDetail.useEndTime }" pattern="yyyy-MM-dd EEEE HH:mm" /> 退房
													</c:if>
												</c:forEach>
												</td>
												<td class="LBodRit LBodTop fB">早餐</td>
												<td class="LBodRit LBodTop">${packageDetail.supPpName }</td>
												<td class="LBodRit LBodTop fB" >酒店地址</td>
												<td class="LBodRit LBodTop">${packageDetail.address }</td>
											</tr>
										</c:if>
										<c:if test="${packageDetail.resType==1}">
											<tr class="LBoder">
												<td class="NavText" colspan="6">景点信息</td>
											</tr>
											<tr class="TextCen LBodTop">
												<td class="LBodRit LBodTop fB" >景点名称</td>
												<td class="LBodRit LBodTop" >${packageDetail.resName}</td>
												<td class="LBodRit LBodTop fB"  >门票数量</td>
												<td class="LBodRit LBodTop" >${packageDetail.proContainCount }张</td>
												<td class="LBodRit LBodTop fB">票型</td>
												<td class="LBodRit LBodTop">${packageDetail.resProName}</td>
											</tr>
											<tr class="TextCen LBodTop">
												<td class="LBodRit LBodTop fB" >游玩时间</td>
												<td class="LBodRit LBodTop" >
													<c:forEach items="${orderDetails}" var="orderDetail" varStatus="orderDetailIndex">
														<c:if test="${orderDetail.resourceId==packageDetail.relatedId && orderDetail.resourceType==1}">
															<fmt:formatDate value="${orderDetail.useStartTime }" pattern="yyyy-MM-dd EEEE HH:mm" />
														</c:if>
													</c:forEach>
												</td>
												<td class="LBodRit LBodTop fB"  >取票方式</td>
												<td class="LBodRit LBodTop" >${packageDetail.getTicketWay}</td>
												<td class="LBodRit LBodTop fB">景区地址</td>
												<td class="LBodRit LBodTop">${packageDetail.address}</td>
											</tr>
										</c:if>
									</c:if>
								</c:forEach>
								<tr class="LBoder">
									<td class="NavText" colspan="6">出游人信息</td>
								</tr>
				   				<c:forEach items="${passengerInfos}" var="passengerInfo" varStatus="passengerInfoIndex">
									<tr class="TextCen LBodTop">
										<td class="LBodRit LBodTop fB" >出游人姓名</td>
										<td class="LBodRit LBodTop" >${passengerInfo.name}</td>
										<td class="LBodRit LBodTop fB"  >手机号</td>
										<td class="LBodRit LBodTop" >${passengerInfo.mobile}</td>
										<td class="LBodRit LBodTop fB">证件号码</td>
										<c:if test="${passengerInfo.certType==0}">
											<td class="LBodRit LBodTop">身份证|${passengerInfo.idNo}</td>
										</c:if>
									</tr>
								</c:forEach>
				   				<tr class="LBoder">
				   					<td class="NavText" colspan="6">联系人信息</td>
				   				</tr>
								<tr class="TextCen LBodTop">
									<td class="LBodRit LBodTop fB" >联系人</td>
									<td class="LBodRit LBodTop" >${order.contactsName}</td>
									<td class="LBodRit LBodTop fB"  >手机号</td>
									<td class="LBodRit LBodTop" >${order.contactsTelphone}</td>
									<td class="LBodRit LBodTop fB">邮箱</td>
									<td class="LBodRit LBodTop">${order.contactsEmail}</td>
								</tr>
							</table>
			   			</div>
			   		</div>
		   		</td>
		   	</tr>
		   	<tr>
		   		<td>
			   		<div class="RightBg">
						<div class="RightTab">
				   			<table class="LBoder">
				   				<tr>
				   					<td class="NavText" colspan="8">支付信息</td>
				   				</tr>
		   						<c:choose>
				   					<c:when test="${orderPays==null||fn:length(orderPays) == 0}">
					   					<tr class="TextCen LBodTop">
						   					<td class="LBodRit LBodTop fB"  style="width: 10%">支付平台</td>
						   					<td class="LBodRit LBodTop" style="width: 20%"></td>
						   					<td class="LBodRit LBodTop fB"  style="width: 10%">支付订单号</td>
						   					<td class="LBodRit LBodTop" style="width: 20%"></td>
						   					<td class="LBodRit LBodTop fB"  style="width: 10%">支付时间</td>
						   					<td class="LBodRit LBodTop" style="width: 20%"></td>
					   					</tr>
					   					<tr class="TextCen LBodTop">
						   					<td class="LBodRit LBodTop fB"  >支付流水号</td>
						   					<td class="LBodRit LBodTop"></td>
						   					<td class="LBodRit LBodTop fB"  >支付金额</td>
						   					<td class="LBodRit LBodTop"></td>
						   					<td class="LBodRit LBodTop fB" >支付状态</td>
						   					<td class="LBodRit LBodTop"></td>
					   					</tr>
				   					</c:when>
				   					<c:otherwise>
						   				<c:forEach items="${orderPays}" var="orderPay" varStatus="payIndex">
						   					<tr class="TextCen LBodTop">
							   					<td class="LBodRit LBodTop fB"  style="width: 10%">支付平台</td>
							   					<td class="LBodRit LBodTop" style="width: 20%">
							   						<c:forEach items="${payMode}" var="obj" varStatus="sIndex">
														<c:if test="${orderPay.payModeSap == obj.key}">
															${obj.value}
														</c:if>
													</c:forEach>
							   					</td>
							   					<td class="LBodRit LBodTop fB"  style="width: 10%">支付订单号</td>
							   					<td class="LBodRit LBodTop" style="width: 20%">${orderPay.transNo}</td>
							   					<td class="LBodRit LBodTop fB"  style="width: 10%">支付时间</td>
							   					<td class="LBodRit LBodTop" style="width: 20%"><fmt:formatDate value="${orderPay.payTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						   					</tr>
						   					<tr class="TextCen LBodTop">
							   					<td class="LBodRit LBodTop fB"  >支付流水号</td>
							   					<td class="LBodRit LBodTop">${orderPay.merchantNo}</td>
							   					<td class="LBodRit LBodTop fB"  >支付金额</td>
							   					<td class="LBodRit LBodTop"><font color="red">¥<fmt:formatNumber pattern="0.00" value="${orderPay.payMoney}"/></font></td>
							   					<td class="LBodRit LBodTop fB"  >支付状态</td>
							   					<td class="LBodRit LBodTop">
							   						<c:if test="${order.orderPay==0}" >未支付</c:if>
													<c:if test="${order.orderPay==1}" >已支付</c:if>
												</td>	
						   					</tr>
						   				
						   				</c:forEach>
					   				</c:otherwise>
				   				</c:choose>
		   					</table>
			   			</div>
			   		</div>
		   		</td>
		   	</tr>
		   
		   <tr>
		   		<td>
					<div class="RightBg">
						<div class="RightTab" >
				   			<table class="LBoder">
				   				<tr >
				   					<td class="NavText" colspan="6">退款信息</td>
				   				</tr>
				   				<c:choose>
				   					<c:when test="${orderRefunds==null||fn:length(orderRefunds) == 0}">
				   						<tr class="TextCen LBodTop">
							   				<td class="LBodRit LBodTop fB" style="width: 10%">退款单编号</td>
						   					<td class="LBodRit LBodTop" style="width: 20%"></td>
						   					<td class="LBodRit LBodTop fB" style="width: 10%">退款状态</td>
						   					<td class="LBodRit LBodTop" style="width: 20%"></td>
						   					<td class="LBodRit LBodTop fB" style="width: 10%">退款金额</td>
						   					<td class="LBodRit LBodTop" style="width: 20%"></td>
					   					</tr>
				   					</c:when>
				   					<c:otherwise>
						   				<c:forEach items="${orderRefunds}" var="orderRefund" varStatus="payIndex">
						   					<tr class="TextCen LBodTop">
							   					<td class="LBodRit LBodTop fB"  style="width: 10%">退款单号</td>
							   					<td class="LBodRit LBodTop" style="width: 20%"><a href="javascript:void(0);" style="color: blue;" onclick="viewRefundOrder('${orderRefund.id }');">${orderRefund.refundId }</a></td>
							   					<td class="LBodRit LBodTop fB"  style="width: 10%">退款状态</td>
							   					<td class="LBodRit LBodTop" style="width: 20%">
							   						<c:forEach items="${RefundStatus}" var="obj" varStatus="sIndex">
														<c:if test="${orderRefund.refundStatus == sIndex.index}">
															${obj.name}
														</c:if>
													</c:forEach>
							   					</td>
							   					<td class="LBodRit LBodTop fB"  style="width: 10%">退款金额</td>
							   					<td class="LBodRit LBodTop" style="width: 20%"><font color="red">¥<fmt:formatNumber pattern="0.00" value="${orderRefund.refundAmount}"/></font></td>
						   					</tr>
						   				</c:forEach>
					   				</c:otherwise>
				   				</c:choose>
				   				
				   			</table>
			   			</div>
		   			</div>
		   		</td>
		   	</tr>
		   	
	   		<tr>
		   		<td>
		   			<div class="RightBg">
						<div class="RightTab tabContainer">
							<div id="nav" >
								<ul class="nav-tabs">
								    <li class="activeing"><a href="javascript:void(0)">订单日志</a></li>
								    <li><a href="javascript:void(0)">短信日志</a></li>    
								</ul>
								<c:if test="${pushSapFlag}">
									<input type="button" onclick="sendSAP('${order.orderId}');" style="width: 80px; height: 30px; color: #485058; margin-left: 68px;" value="重新推送SAP"/>
								</c:if>
							</div>
							<div class="table-responsive" style="clear:both;">
								<table class="LBoder">
									<thead>
										<tr class="TextCen fB">
											<td style="width:100px" class="LBodRit">编号</td>
											<td style="width:100px" class="LBodRit">创建时间</td>
											<td style="width:200px" class="LBodRit">状态说明</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach varStatus="varStatus" var="orderStatusLog" items="${orderStatusLogs }">
											<tr class="TextCen LBodTop">
												<td class="LBodRit LBodTop">${orderStatusLog.id }</td>
						   						<td class="LBodRit LBodTop"><fmt:formatDate value="${orderStatusLog.recordTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						   						<td class="LBodRit LBodTop">${orderStatusLog.details }</td>
					   						</tr>
										</c:forEach>
									</tbody>
					   			</table>
							</div>
							<div class="table-responsive" style="display:none;clear:both;">
								<table class="LBoder">
									<thead>
										<tr class="TextCen fB ">
											<td style="width:37px" class="LBodRit">推送序号</td>
											<td style="width:37px" class="LBodRit">预订时间</td>
											<td style="width:37px" class="LBodRit">推送时间</td>
											<td style="width:37px" class="LBodRit">短信内容</td>
											<td style="width:37px" class="LBodRit">推送状态</td>
											<td style="width:37px" class="LBodRit">操作</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach varStatus="varStatus" var="smsLog" items="${smsLogs }">
											<tr class="TextCen LBodTop">
												<td class="LBodRit LBodTop">${varStatus.count }</td>
												<td id="td_createTime"  class="LBodRit LBodTop"><fmt:formatDate value="${smsLog.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td class="LBodRit LBodTop"><fmt:formatDate value="${smsLog.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						   						<td class="LBodRit LBodTop">${smsLog.content }</td>
						   						<td class="LBodRit LBodTop">
						   							<c:if test="${smsLog.status == true}">成功</c:if>
						   							<c:if test="${smsLog.status == false}">失败</c:if>
						   						</td>
						   						<td class="LBodRit LBodTop">
						   							<c:if test="${smsLog.status == false}">
						   							<a href="javascript:void(0);" onclick="smsMessage('${order.orderId}','${smsLog.smsType}','${smsLog.phoneNumber}');">推送消息</a></c:if>
						   						</td>
					   						</tr>
										</c:forEach>
					   					
									</tbody>
					   			</table>
							</div>
			   			</div>
			   		</div>
		   		</td>
		   	</tr>
		   	<input type="hidden" id="act" value="${ACT}"/>
	   	</table>
	</form>
	<div id="loading"
	style="position:fixed !important;position:absolute;top:0;left:0;height:100%; width:100%; 
	z-index:999; background:#000 url() no-repeat center center; opacity:0.6; 
	filter:alpha(opacity=60);font-size:14px;line-height:20px;display:none">
	<p id="loading-one"
		style="color: #fff; position: absolute; top: 50%; left: 50%; margin: 20px 0 0 -50px; padding: 3px 10px;">正在处理，请稍后...</p>
</div>

<!-- 退款单页面 -->
<div style="display:none; overflow:hidden; padding:3px" id="viewRefund_dialog">
	<iframe frameborder="no" border="0" marginwidth="0" marginheight="0" id="viewRefundSrc"  scrolling="yes"  width="100%" height="100%"></iframe>
</div>


<script type="text/javascript">
$(function(){
	$(".tabContainer").find(".nav-tabs li").click(function(){
		$(this).addClass("activeing").siblings().removeClass("activeing");
		$(".tabContainer").find("div.table-responsive").eq($(this).index()).show().siblings("div.table-responsive").hide();
	});
});

function back2list(){
	window.location.href = '${_domain}/admin/order/order_list.html?ACT='+$("#act").val();
}
function cancelOrder(id,partnerOrderCode){
	var send = confirm("确认取消此订单吗?");
	if(send){
		$("#loading").show();
		$.ajax({
			url : "${_domain}/admin/order/cancelOrder",
			type : "POST",
			cache : false,
			async : false,
			data:{"id":id,"partnerOrderCode":partnerOrderCode},
			success : function(data) {
				debugger;
				$("#loading").hide();
				alert(data.info);
				var infoString = data.info;
				if(infoString.indexOf("成功")>0){
					window.location.href="${_domain}/admin/order/orderDetail?id="+id;
				}
			}
		});
	}
}
function smsMessage(orderId,smsType,phone){
	var send = confirm("确认推送吗?");
	var createTime = $("#td_createTime").text();//短信创建时间
	if(send){
		$("#loading").show();
		$.ajax({
			url : "${_domain}/admin/order/smsMessage.html",
			type : "POST",
			cache : false,
			async : false,
			data:{"orderId":orderId,"smsType":smsType,"phone":phone,"createTime":createTime},
			success : function(data) {
				$("#loading").hide();
				alert(data);
				history.go(0);
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
function sendSAP(orderId){
	if(confirm("确认手动推送sap吗?")){
		$("#loading").show();
		$.ajax({
			type :'POST',
			url  : '${_domain}/admin/order/sendSap.html',
			data:{orderId:orderId},
			dataType : "json",
			async:true,
			success : function(data) {
				$("#loading").hide();
				if(data.resultCode == "E90000"){
					alert("重推SAP操作成功!");
				}else{
					alert("重推SAP操作失败,请联系管理员!");
				}
				history.go(0);
			}
		});
	}
}


// 退款单信息
function viewRefundOrder(refundId){
	var url='${_domain}/admin/refund/queryOrderRefundDetail.html?showFlag=OTHER&refundId='+refundId;
	 $("#viewRefundSrc").attr("src",url); //设置IFRAME的SRC;
      $("#viewRefund_dialog").dialog({
        bgiframe: true,
        resizable : false,
        height: 700, 
        width: 1000, 
        draggable: false, 
        title: "退款单详情",
        modal: true,
        open: function (e) {},
        close: function () {}
      });
}

</script>
</body>
</html>
    