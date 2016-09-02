<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>退款单(${location})</title>
	<%@ include file="../common/css.jsp"%>
	<%@ include file="../common/js.jsp"%>
<style>
.cx {
	text-align: right;
	margin-right: 5px;
	display: inline-block;
	width: 100px;
}
</style>
</head>
	  
	  
<body style="background: #afb8bf; overflow-x: auto; overflow-y: scroll; *overflow-y: hidden;min-width:1128px;">
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td background="${_domain}/images/RigNav.gif" height="31">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td class="TextRig"><img src="${_domain}/images/nav_3.gif" style="width: 11" height="7" /></td>
						<td style="width: 97%" class="NavText">您的位置：度假退款管理&gt;${location}</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>
				<div class="RightBg">
					<div class="RightTab">
						<form action="${_domain}/admin/refund/queryOrderRefundList.html" method="post" id="pageform" name="queryForm">
							<table id="searchTable">
								<tr>
									<td>
										<span class="cx">订单编号:</span> <input id="orderId" name="orderId" value="${refundInfo.orderId}"   style="width: 127px;" type="text" style="width: 127px;" type="text" maxlength="20"/>
									</td>
									
									<td>
										<span class="cx">退款单号:</span> <input id="refundId" name="refundId" value="${refundInfo.refundId}" style="width: 127px;" type="text" maxlength="20"/>
									</td>
									
									<td>
										<span class="cx" style="width: 127px;">退款单创建时间:</span> <input id="startTime" name="startTime" type="text" value="<fmt:formatDate value="${refundInfo.startTime}" pattern="yyyy-MM-dd" />" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})" style="width: 127px;" type="text"/>&nbsp;&nbsp;至 <input id="endTime" name="endTime" type="text" value="<fmt:formatDate value="${refundInfo.endTime}" pattern="yyyy-MM-dd" />" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})" style="width: 127px;" type="text"/>
									</td>
								</tr>
								
								
								<tr>
									<td>
										<span class="cx">支付订单号:</span> <input id="payTrxorderId" name="payTrxorderId" value="${refundInfo.payTrxorderId}"   style="width: 127px;" type="text" style="width: 127px;" type="text" maxlength="50"/>
									</td>
									
									<td>
										<span class="cx">会员账号:</span> <input id="userName" name="userName" value="${refundInfo.userName}" style="width: 127px;" type="text" maxlength="50"/>
									</td>
									
									<c:choose>
										<c:when test="${refundInfo.refundQueryType eq 'INIT'}">
											<td>
												<span class="cx">退款类型:</span>
												<select id="refundType" name="refundType" style="width: 127px;">
													<option value="">全部</option>
													<c:forEach items="${refundPayTypeMap}" var="status" varStatus="sIndex">
														<option value="${sIndex.index}" <c:if test="${not empty refundInfo.refundType && refundInfo.refundType == sIndex.index}">selected="selected"</c:if>>${status.name}</option>
													</c:forEach>
												</select>
											</td>
										</c:when>
										<c:otherwise>
											<td>
												<span class="cx" style="width: 127px;">退款审批时间:</span> <input id="auditeStartTime" name="auditeStartTime" type="text" value="<fmt:formatDate value="${refundInfo.auditeStartTime}" pattern="yyyy-MM-dd" />" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'auditeEndTime\')||\'2020-10-01\'}'})" style="width: 127px;" type="text"/>&nbsp;&nbsp;至 <input id="auditeEndTime" name="auditeEndTime" type="text" value="<fmt:formatDate value="${refundInfo.auditeEndTime}" pattern="yyyy-MM-dd" />" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'auditeStartTime\')}',maxDate:'2020-10-01'})" style="width: 127px;" type="text"/>
											</td>
										</c:otherwise>
									</c:choose>
								</tr>
								
								
								<c:if test="${refundInfo.refundQueryType eq 'ALL' || refundInfo.refundQueryType eq 'EXPORT'}">
									<tr>
										<td>
											<span class="cx">退款状态:</span>
											<select id="refundStatus" name="refundStatus"  style="width: 127px;">
												<option value="">全部</option>
												<c:forEach items="${refundStatusMap}" var="status" varStatus="sIndex">
													<option value="${sIndex.index}" <c:if test="${not empty refundInfo.refundStatus && refundInfo.refundStatus == sIndex.index}">selected="selected"</c:if>>${status.name}</option>
												</c:forEach>
											</select>
										</td>
										
										<td>
											<span class="cx">退款原因:</span>
											<select id="refundReason" name="refundReason" style="width: 127px;">
												<option value="">全部</option>
												<c:forEach items="${RefundReasonMap}" var="status" varStatus="sIndex">
													<c:if test="${RefundReasonMap!=null}">
													<option value="${sIndex.index}" <c:if test="${not empty refundInfo.refundReason && refundInfo.refundReason == sIndex.index}">selected="selected"</c:if>>${status.value}</option>
													</c:if>
												</c:forEach>
											</select>
										</td>
										
										<td>
											<span class="cx" style="width: 127px;">退款完成时间:</span> <input id="refundFinishStartTime" name="refundFinishStartTime" type="text" value="<fmt:formatDate value="${refundInfo.refundFinishStartTime}" pattern="yyyy-MM-dd" />" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'refundFinishEndTime\')||\'2020-10-01\'}'})" style="width: 127px;" type="text"/>&nbsp;&nbsp;至 <input id="refundFinishEndTime" name="refundFinishEndTime" type="text" value="<fmt:formatDate value="${refundInfo.refundFinishEndTime}" pattern="yyyy-MM-dd" />" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'refundFinishStartTime\')}',maxDate:'2020-10-01'})" style="width: 127px;" type="text"/>
										</td>
									</tr>
									
									<tr>
										<td>
											<span class="cx">供应商订单号:</span> <input id="venderOrderId" name="venderOrderId" value="${refundInfo.venderOrderId}"  style="width: 127px;" type="text"  type="text" maxlength="20"/>
										</td>
										<td>
											<span class="cx">退款支付平台:</span>
											<select id="orderPayModeSap" name="orderPayModeSap" style="width: 127px;">
												<option value="">全部</option>
												<c:forEach items="${platformMaps}" var="platformMap" varStatus="sIndex">
													<c:if test="${platformMap!=null}">
														<option value="${platformMap.key}" <c:if test="${not empty refundInfo.orderPayModeSap && refundInfo.orderPayModeSap == platformMap.key}">selected="selected"</c:if>>${platformMap.value}</option>
													</c:if>
												</c:forEach>
											</select>
										</td>
										<td>
											<span class="cx" style="width: 127px;">退款类型:</span>
											<select id="refundType" name="refundType" style="width: 127px;">
												<option value="">全部</option>
												<c:forEach items="${refundPayTypeMap}" var="status" varStatus="sIndex">
													<option value="${sIndex.index}" <c:if test="${not empty refundInfo.refundType && refundInfo.refundType == sIndex.index}">selected="selected"</c:if>>${status.name}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td>
											<span class="cx">审核方式:</span>
											<select id="auditStatus" name="auditStatus" style="width: 127px;">
												<option value="">全部</option>
												<c:forEach items="${RefundAuditStatus}" var="status" varStatus="sIndex">
													<option value="${sIndex.index}" <c:if test="${not empty refundInfo.auditStatus && refundInfo.auditStatus == sIndex.index}">selected="selected"</c:if>>${status.name}</option>
												</c:forEach>
											</select>
										</td>
										<td colspan="4"></td>
									</tr>
								</c:if>
								
								<tr>
									<td colspan="4" align="right">
										<input type="hidden"  id="refundQueryType" name="refundQueryType" value="${refundInfo.refundQueryType}"/>
										<input type="button"  onclick="submitForm();" style="width: 80px; height: 30px; color: #485058; margin-left: 68px;" value="查询"/>
										
										<!-- 只有退款单导出，显示以下信息 -->
										<c:if test="${refundInfo.refundQueryType eq 'EXPORT'}">
											<input type="button" name="exportBtn" value="导出Excel " onclick="refundExcelData()" style="width:80px; height:30px; color:#485058" />
										</c:if>
									</td>
								</tr>
							</table>
						</form>
					</div>
					
					<div class="H10"></div>
					
					<div class="RightTab">
						<table cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="2">
									<!-- 显示字段列表 -->
									<table cellpadding="0" cellspacing="0" class="LBoder">
										<c:choose>
											<c:when test="${refundList != null}">
												<thead>
													<tr class="TextCen fB GDDray">
														<td style="width: 37px" class="LBodRit">操作(${totalCount})</td>
														
														<td style="width: 100px" class="LBodRit">订单编号</td>
														<td style="width: 100px" class="LBodRit">退款单号</td>
														<td style="width: 100px" class="LBodRit">会员账号</td>
														<td style="width: 300px" class="LBodRit">支付平台</td>
														<td style="width: 100px" class="LBodRit">实际支付金额</td>
														<td style="width: 140px" class="LBodRit">支付时间</td>
														<td style="width: 140px" class="LBodRit">支付订单号</td>
														<td style="width: 140px" class="LBodRit">退款创建时间</td>
														<td style="width: 100px" class="LBodRit">退款金额</td>
														<td style="width: 100px" class="LBodRit">退款类型</td>
														<td style="width: 150px" class="LBodRit">退款原因</td>
														
														<c:if test="${refundInfo.refundQueryType eq 'ALL' || refundInfo.refundQueryType eq 'EXPORT'}">
															<td style="width: 100px" class="LBodRit">退款状态</td>
															<td style="width: 100px" class="LBodRit">退款方式</td>
															<td style="width: 100px" class="LBodRit">审核人</td>
															<td style="width: 100px" class="LBodRit">审核方式</td>
															<td style="width: 140px" class="LBodRit">完成时间</td>
															<td style="width: 140px" class="LBodRit">审核时间</td>
														</c:if>
													</tr>
												</thead>
												
												<!-- 遍历数据 -->
												<tbody>
													<c:forEach  items="${refundList}" var="orderRefund" varStatus="refundIndex">
														<tr class="TextCen LBodTop">
															<td class="LBodRit LBodTop">
																<a href="javascript:;" onclick="auditRefund('${orderRefund.id}')">
																	<c:choose>
																		<c:when test="${refundInfo.refundQueryType eq 'INIT'}">
																			<font color=blue>审批</font>
																		</c:when>
																		<c:otherwise>
																			<font color=blue>明细</font>
																		</c:otherwise>
																	</c:choose>
																</a>
															</td>
															<td class="LBodRit LBodTop"><a href="javascript:void(0);" style="color: blue;" onclick="viewOrderDetail('${orderRefund.orderId}');">${orderRefund.orderId}</a></td>
															<td class="LBodRit LBodTop">${orderRefund.refundId}</td>
															<td class="LBodRit LBodTop" title="${orderRefund.userName}">
																<c:choose>
																	<c:when test="${null!=orderRefund.userName && fn:length(orderRefund.userName)>12}">
																		${fn:substring(orderRefund.userName,0,12)}...
																	</c:when>
																	<c:otherwise>
																		${orderRefund.userName}
																	</c:otherwise>
																</c:choose>
															</td>
															<td class="LBodRit LBodTop">${orderRefund.payModeName}</td>
															<td class="LBodRit LBodTop">
																<font color=red>
																	<fmt:formatNumber value="${orderRefund.orderPayMoney}" pattern="0.00"/>
																</font>
															</td>
															<td class="LBodRit LBodTop"><fmt:formatDate value="${orderRefund.orderPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
															<td class="LBodRit LBodTop">${orderRefund.payTrxorderId}</td>
															<td class="LBodRit LBodTop"><fmt:formatDate value="${orderRefund.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
															<td class="LBodRit LBodTop">
																<font color=red>
																	<fmt:formatNumber value="${orderRefund.returnAmount}" pattern="0.00"/>
																</font>
															</td>
															<td class="LBodRit LBodTop">${orderRefund.refundPayTypeName.name}</td>
															<td class="LBodRit LBodTop">${orderRefund.refundReasonName.value}</td>
															
															
															<c:if test="${refundInfo.refundQueryType eq 'ALL' || refundInfo.refundQueryType eq 'EXPORT'}">
																<td class="LBodRit LBodTop">${orderRefund.refundStatusName.name}</td>
																<td class="LBodRit LBodTop">${orderRefund.refundModeName.name}</td>
																<td class="LBodRit LBodTop">${orderRefund.auditName}</td>
																<td class="LBodRit LBodTop">
																	<c:choose>
																		<c:when test="${orderRefund.refundReason==0}">
																			系统审核
																		</c:when>
																		<c:otherwise>
																			人工审核
																		</c:otherwise>
																	</c:choose>

																</td>
																<td class="LBodRit LBodTop"><fmt:formatDate value="${orderRefund.finishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
																<td class="LBodRit LBodTop"><fmt:formatDate value="${orderRefund.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
															</c:if>
														</tr>
													</c:forEach>
												</tbody>
											</c:when>
											<c:otherwise>
												无满足条件的数据!
											</c:otherwise>
										</c:choose>
									</table>
								</td>
							</tr>
							
							<c:if test="${refundList != null}">
								<tr>
									<td colspan="11" class="td1">
										<div align="right">
											<c:out value="${pageResult}" escapeXml="false" />
										</div>
									</td>
								</tr>
							</c:if>
						</table>
					</div>
				</div>
			</td>
		</tr>
	</table>
	
	<div id="loading" style="position:fixed !important;position:absolute;top:0;left:0;height:100%; 
	width:100%; z-index:999; background:#000 url(${_domain}/images/loading.gif) no-repeat center center; 
	opacity:0.6; filter:alpha(opacity=60);font-size:14px;line-height:20px;display:none">
		<p id="loading-one" style="color: #fff; position: absolute; top: 50%; left: 50%; margin: 20px 0 0 -50px; padding: 3px 10px;">提交中，请稍后...</p>
	</div>
	
	<!-- 订单页面 -->
	<div style="display:none; overflow:hidden; padding:3px" id="viewOrder_dialog">
		<iframe frameborder="no" border="0" marginwidth="0" marginheight="0" id="viewOrderSrc"  scrolling="yes"  width="100%" height="100%"></iframe>
	</div>
	
	<script type="text/javascript">
		// 提交Form表单
		function submitForm() {
			$("#pageform").submit();
		}
		
		// 审批---待审批退款单
		function auditRefund(refundId){
			window.location.href = '${_domain}/admin/refund/queryOrderRefundDetail.html?refundId='+refundId+"&showFlag="+$("#refundQueryType").val();
		}
		
		// 下载待审批退款单数据
		function refundExcelData(){
			var refundId       = $("#refundId").val();
			var orderId        = $("#orderId").val();
			var userName 	   = $("#userName").val();
			var payTrxorderId  = $("#payTrxorderId").val();
			var refundReason     = $("#refundReason").val();
			var refundType  = $("#refundType").val();
			var refundStatus   = $("#refundStatus").val();
			var startTime      = $("#startTime").val();
			var endTime  	   = $("#endTime").val();
			var auditeStartTime= $("#auditeStartTime").val();
			var auditeEndTime  = $("#auditeEndTime").val();
			var finishStartTime= $("#refundFinishStartTime").val();
			var finishEndTime  = $("#refundFinishEndTime").val();
			var venderOrderId = $("#venderOrderId").val();
			var orderPayModeSap=$("#orderPayModeSap").val();
			var auditStatus=$("#auditStatus").val();
		
			var param = "refundId="+refundId+"&orderId="+orderId+"&userName="+userName+"&payTrxorderId="+payTrxorderId
								   +"&refundReason="+refundReason+"&refundType="+refundType+"&refundStatus="+refundStatus
								   +"&startTime="+startTime+"&endTime="+endTime
								   +"&auditeStartTime="+auditeStartTime+"&auditeEndTime="+auditeEndTime
								   +"&finishStartTime="+finishStartTime+"&finishEndTime="+finishEndTime
					               +"&venderOrderId="+venderOrderId+"&orderPayModeSap="+orderPayModeSap
								   +"&auditStatus="+auditStatus;
			window.location.href = '${_domain}/admin/refund/refundExcelData.html?'+param;
	   }
		
		// 订单信息
		function viewOrderDetail(orderId){
			var url='${_domain}/admin/order/orderDetail.html?orderId='+orderId+'&ACT=OTHER';
			  $("#viewOrderSrc").attr("src",url); //设置IFRAME的SRC;
		      $("#viewOrder_dialog").dialog({
		        bgiframe: true,
		        resizable : false,
		        height: 700, 
		        width: 1000, 
		        draggable: false, 
		        title: "订单详情",
		        modal: true,
		        open: function (e) {},
		        close: function () {}
		      });	  
		}
	</script>
</body>
</html>
