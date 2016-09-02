<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>待审批详情</title>
	<%@ include file="../common/css.jsp"%>
	<%@ include file="../common/js.jsp"%>
	<style>
    	.RightBg td {
		    width: 12.5%;
		}
	</style>
</head>
  
<body style="background:#afb8bf;overflow-x:hidden;overflow-y:scroll;*overflow-y:hidden">
	<table>
		<tr>
			<td background="${_domain}/images/RigNav.gif" height="31">
			    <table>
		          <tr>						
					  <td class="TextRig"><img src="${_domain}/images/nav_3.gif" width="11" height="7" /></td>						
					  <td style="width:97%" class="NavText DText">您的位置：演出票退款管理&gt;${oneLevelMenu}&gt;${twoLevelMenu}</td>	
				  </tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>
				<div class="RightBg">
					<div class="RightTab" >
						<table class="LBoder">
							<tr>
				   				<td class="NavText" colspan="6">订单信息</td>
				   			</tr>
				   			
							<tr class="TextCen LBodTop">
								<td class="LBodRit fB">订单编号</td>
				   				<td class="LBodRit LBodTop"><a href="javascript:void(0);" style="color: blue;" onclick="viewOrderDetail('${orderInfo.orderId}');">${orderInfo.orderId}</a></td>
				   				
				   				<td class="LBodRit fB">用户账户(ID)</td>
								<td class="LBodRit LBodTop">${orderRefund.userName}(${orderRefund.userId})</td>
							
								<td class="LBodRit fB">订单来源</td>
				   				<td class="LBodRit LBodTop">${orderSourceName}</td>
							</tr>
							
							<tr class="TextCen LBodTop">
								<td class="LBodRit fB">订单总额</td>
								<td class="LBodRit LBodTop"><font color=red><fmt:formatNumber value="${orderInfo.orderAmount}" pattern="0.00"/></font></td>
								
								<td class="LBodRit fB">应付金额</td>
								<td class="LBodRit LBodTop"><font color=red><fmt:formatNumber value="${orderInfo.paymentAmount}" pattern="0.00"/></font></td>
								
								<td class="LBodRit fB">下单时间</td>
				   				<td class="LBodRit LBodTop"><fmt:formatDate value="${orderInfo.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
							
							<tr class="TextCen LBodTop">
								<td class="LBodRit fB">优惠金额</td>
								<td class="LBodRit LBodTop"><font color=red><fmt:formatNumber value="${incentiveAmount}" pattern="0.00"/></font></td>
								
								<td class="LBodRit fB">实付金额</td>
								<td class="LBodRit LBodTop"><font color=red><fmt:formatNumber value="${orderInfo.paymentAmount}" pattern="0.00"/></font></td>
								
								<td class="LBodRit fB">订单状态</td>
				   				<td class="LBodRit LBodTop">${orderStatusName}</td>
							</tr>
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
							<tr>
				   				<td class="NavText" colspan="6">支付信息</td>
				   			</tr>
							
							<!-- 支付平台信息(如果重复支付，会显示多条) -->
							<c:choose>
								<c:when test="${orderPaysList != null }">
									<c:forEach items="${orderPaysList}" var="orderPay" varStatus="payIndex">
										<tr class="TextCen LBodTop">
											<td class="LBodRit fB">支付平台</td>
							   				<td class="LBodRit LBodTop">
							   					<c:forEach items="${payModeMap}" var="obj">
													<c:if test="${orderPay.payModeSap == obj.key}">${obj.value}</c:if>
												</c:forEach>
							   				</td>
							   				
							   				<td class="LBodRit fB">支付订单号</td>
							   				<td class="LBodRit LBodTop">${orderPay.transNo}</td>
							   				
							   				<td class="LBodRit fB">支付金额</td>
							   				<td class="LBodRit LBodTop"><font color=red><fmt:formatNumber value="${orderPay.payMoney}" pattern="0.00"/></font></td>
										</tr>
										
										<tr class="TextCen LBodTop">
											<td class="LBodRit fB">支付交易流水号</td>
							   				<td class="LBodRit LBodTop">${orderPay.merchantNo}</td>
							   				
							   				<td class="LBodRit fB">支付时间</td>
							   				<td class="LBodRit LBodTop"><fmt:formatDate value="${orderPay.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							   				
							   				<td class="LBodRit fB">支付状态</td>
							   				<td class="LBodRit LBodTop">${payStatusName}</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
										<tr class="TextCen LBodTop">
											<td class="LBodRit fB">支付平台</td>
							   				<td class="LBodRit LBodTop"></td>
							   				
							   				<td class="LBodRit fB">支付订单号</td>
							   				<td class="LBodRit LBodTop"></td>
							   				
							   				<td class="LBodRit fB">支付金额</td>
							   				<td class="LBodRit LBodTop"></td>
										</tr>
										
										<tr class="TextCen LBodTop">
											<td class="LBodRit fB">支付交易流水号</td>
							   				<td class="LBodRit LBodTop"></td>
							   				
							   				<td class="LBodRit fB">支付时间</td>
							   				<td class="LBodRit LBodTop"></td>
							   				
							   				<td class="LBodRit fB">支付状态</td>
							   				<td class="LBodRit LBodTop"></td>
										</tr>
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
							<tr>
				   				<td class="NavText" colspan="6">退款信息</td>
				   			</tr>
				   			
				   			<tr class="TextCen LBodTop">
								<td class="LBodRit fB">退款单编号</td>
				   				<td class="LBodRit LBodTop">${orderRefund.refundId}</td>
				   				
				   				<td class="LBodRit fB">退款类型</td>
				   				<td class="LBodRit LBodTop">${refundTypeName}</td>
				   				
				   				<td class="LBodRit fB">退款金额</td>
				   				<td class="LBodRit LBodTop"><font color=red><fmt:formatNumber value="${orderRefund.returnAmount}" pattern="0.00"/></font></td>
							</tr>
							
							<tr class="TextCen LBodTop">
								<td class="LBodRit fB">创建时间</td>
				   				<td class="LBodRit LBodTop"><fmt:formatDate value="${orderRefund.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				   				
				   				<td class="LBodRit fB">审批时间</td>
				   				<td class="LBodRit LBodTop"><fmt:formatDate value="${orderRefund.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				   				
				   				<td class="LBodRit fB">退款方式</td>
				   				<td class="LBodRit LBodTop">${refundModeName}</td>
							</tr>
							
							<tr class="TextCen LBodTop">
								<td class="LBodRit fB">退款支付平台</td>
				   				<td class="LBodRit LBodTop">
				   					<c:forEach items="${payModeMap}" var="obj">
										<c:if test="${orderRefund.payModeSap == obj.key}">${obj.value}</c:if>
									</c:forEach>
							   	</td>
				   				
				   				<td class="LBodRit fB">退款流水号</td>
				   				<td class="LBodRit LBodTop">${orderRefund.refundBankTransNum}</td>
				   				
				   				<td class="LBodRit fB">退款完成时间</td>
				   				<td class="LBodRit LBodTop"><fmt:formatDate value="${orderRefund.finishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
							
							<tr class="TextCen LBodTop">
								<td class="LBodRit fB">退款状态</td>
				   				<td class="LBodRit LBodTop">${refundStatusName}</td>
				   				
				   				<td class="LBodRit fB">退款原因</td>
				   				<td class="LBodRit LBodTop">${refundReasonName}</td>
				   				
				   				<td class="LBodRit fB">退款来源</td>
				   				<td class="LBodRit LBodTop">${refundSourceName}</td>
				   				<input type="hidden" id="refundQueryType" name="refundQueryType" value="${showFlag}"/>
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
		
		
<c:if test="${showFlag ne 'OTHER'}">
		<tr>	
			<td>
				<div class="RightBg">
					<div class="RightTab" >
						<c:choose>
							<c:when test="${(showFlag eq 'INIT') && (orderRefund.refundStatus == 0 || orderRefund.refundStatus == 2)}">
								<form action="" method="post" id="submitAuditForm" name="submitAuditForm">
									<input type="hidden" id="rid" name="rid" value="${orderRefund.id}">
									<table>
										退款审批意见
										<tr>
											<td>
												审批意见:<select id="auditFlag" name="auditFlag">
															<option value="1" <c:if test="${orderRefund.refundStatus == 1}">selected="selected"</c:if>>同意</option>
															<option value="2" <c:if test="${orderRefund.refundStatus == 2}">selected="selected"</c:if>>拒绝</option>
										   			   </select>
											</td>
										</tr>
										
										<tr>
											<td>
												备注:<textarea id="meno" rows="8" cols="28" maxlength="200">${orderRefund.auditNote}</textarea>
											</td>
										</tr>
									</table>
								</form>
							</c:when>
							<c:otherwise>
								<table class="LBoder">
									<tr class="TextCen LBodTop">
										<td class="LBodRit fB">退款审批意见</td>
			   							<td class="LBodRit LBodTop">
			   								<c:choose>
			   									<c:when test="${orderRefund.refundStatus == 0}">

			   									</c:when>
			   									<c:when test="${orderRefund.refundStatus == 2}">
			   										拒绝
			   									</c:when>
			   									<c:otherwise>
			   										同意
			   									</c:otherwise>
			   								</c:choose>
										</td>
										
										<td class="LBodRit fB">备注</td>
										<td class="LBodRit LBodTop">${orderRefund.auditNote}</td>
									</tr>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</td>
		</tr>
		
		<c:if test="${refundLogList != null}">
			<tr>
				<td>
					<div class="RightBg">
						<div class="RightTab" >
							<table class="LBoder">
								<input type="hidden" id="rid" name="rid" value="${orderRefund.id}">
								退款审批日志 <c:if test="${sendSapFlag}"><input type="button" onclick="sendSap();" style="width: 80px; height: 30px; color: #485058; margin-left: 68px;" value="重新推送sap"/></c:if>
								<tr class="TextCen LBodTop">
									<td class="LBodRit LBodTop">编号</td>
									<td class="LBodRit LBodTop">操作时间</td>
									<td class="LBodRit LBodTop">操作日志</td>
								</tr>
								<c:forEach items="${refundLogList}" var="refundLog">
									<tr class="TextCen LBodTop">
										<td class="LBodRit LBodTop">${refundLog.id}</td>
										<td class="LBodRit LBodTop"><fmt:formatDate value="${refundLog.recordTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td class="LBodRit LBodTop">${refundLog.details}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</td>		
			</tr>
		</c:if>

		<tr>
			<c:choose>
				<c:when test="${(showFlag eq 'INIT') && (orderRefund.refundStatus == 0 || orderRefund.refundStatus == 2)}">
					<td>
						<input type="button"  onclick="submitForm();"  style="width: 80px; height: 30px; color: #485058; margin-left: 10px;" value="提交退款审批"/>
						<input type="button"  onclick="goauditlist();" style="width: 80px; height: 30px; color: #485058; margin-left: 10px;" value="返回审批列表"/>
					</td>
				</c:when>
				<c:otherwise>
					<td>
						<input type="button"  onclick="goauditlist();" style="width: 80px; height: 30px; color: #485058; margin-left: 10px;" value="返回审批列表"/>
					</td>
				</c:otherwise>
			</c:choose>
	   </tr>
</c:if>		
	</table>
	
	<!-- 订单页面 -->
	<div style="display:none; overflow:hidden; padding:3px" id="viewOrder_dialog">
		<iframe frameborder="no" border="0" marginwidth="0" marginheight="0" id="viewOrderSrc"  scrolling="yes"  width="100%" height="100%"></iframe>
	</div>
	
	<script type="text/javascript">
		// 返回审批列表页
		function goauditlist(){
			window.location.href = '${_domain}/admin/refund/queryOrderRefundList.html?refundQueryType='+$("#refundQueryType").val();
		}
		
		// 手动推送sap
		function sendSap(){
			var refundId  = $("#rid").val();
			
			if(confirm("确认手动推送sap吗?")){
				$.ajax({
					type :'POST',
					url  : '${_domain}/admin/refund/sendSap.html',
					data:{refundId:refundId},
					dataType : "json",
					async:true,
					success : function(data) {
						if(data.resultCode == "E90000"){
							alert("推送sap操作成功!");
						}else{
							alert("推送sap操作失败,请联系管理员!");
						}
						window.location.href = '${_domain}/admin/refund/queryOrderRefundList.html?refundQueryType='+$("#refundQueryType").val();
					}
				});
			}
			
		}
		
		// 提交退款审批Form
		function submitForm(){
			var auditFlag = $("#auditFlag").val();
			var refundId  = $("#rid").val();
			var memo      = $("#meno").val();
			
			if(confirm("确认提交退款单审批吗?")){
				$.ajax({
					type :'POST',
					url  : '${_domain}/admin/refund/updateAuditeStatus.html',
					data:{auditFlag:auditFlag,refundId:refundId,memo:memo},
					dataType : "json",
					async:true,
					success : function(data) {
						if(data.resultCode == "E90000"){
							alert("退款单审批操作成功!");
						}else{
							alert("退款单审批操作失败,请联系管理员!");
						}
						window.location.href = '${_domain}/admin/refund/queryOrderRefundList.html?refundQueryType='+$("#refundQueryType").val();
					}
				});
			}
			
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
