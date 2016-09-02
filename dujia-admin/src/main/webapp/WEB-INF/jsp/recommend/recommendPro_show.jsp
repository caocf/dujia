<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看度假推荐商品</title>
<%@ include file="../common/css.jsp"%>
<%@ include file="../common/js.jsp"%>
</head>
<body>
<body style="background: #afb8bf; overflow-x: hidden; overflow-y: scroll; *overflow-y: hidden">
	<form id="insertform" action="${_domain}/admin/advert/saveHomeFocs.html" method="post">
		<table>
			<tr>
				<td background="${_domain}/images/RigNav.gif" height="31">
					<table>
						<tr>
							<td class="TextRig"><img src="${_domain}/images/nav_3.gif" width="11" height="7" /></td>
							<td style="width: 97%" class="NavText DText">您的位置：度假广告管理/查看度假推荐商品</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div class="RightBg">
						<div class="RightTab">
							<table id="insertTable" class="LBoder" style="border: 1;">
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>选择平台：</td>
									<td class="NavLeft"><c:out value="${sourceMap[advert.plateform]}"></c:out></td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>选择展示频道：</td>
									<td class="NavLeft"><c:out value="${channelType[advert.type]}"></c:out></td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>展示城市：</td>
									<td class="NavLeft"><c:out value="${advert.cityName}"></c:out></td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>展示版块：</td>
									<td class="NavLeft"><c:out value="${pAdvert[advert.module]}"></c:out></td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>展示位置：</td>
									<td class="NavLeft"><c:out value="${pAdvert[advert.position]}"></c:out></td>
								</tr>
								<tr id="tr_name" class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit">产品编号：</td>
									<td class="NavLeft"><c:out value="${advert.productId}"></c:out></td>
								</tr>
								<tr id="tr_name" class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit">关联产品名称：</td>
									<td class="NavLeft"><c:out value="${advert.productName}"></c:out></td>
								</tr>
								<tr id="tr_pic" class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit">预览图：</td>
									<td class="NavLeft" id="pic1">
										<img id="viewImg" style="width: 300px; height: 200px" class="img _CLS" src="${advert.productImage }" />
									</td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit">URL链接：</td>
									<td class="NavLeft"><c:out value="${advert.url}"></c:out></td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>投放时间：</td>
									<td class="NavLeft"><fmt:formatDate value="${advert.startTime }" pattern="yyyy-MM-dd" /></td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>结束时间：</td>
									<td class="NavLeft"><fmt:formatDate value="${advert.endTime }" pattern="yyyy-MM-dd" /></td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit">状态：</td>
									<td class="NavLeft">
										<c:if test="${advert.states==0}">  
											<font color="blue">待展示</font>
										</c:if>
										<c:if test="${advert.states==1}">  
											<font color="blue">展示中</font>
										</c:if>
										<c:if test="${advert.states==3}">  
											<font color="blue">启用</font>
										</c:if>
										<c:if test="${advert.states==4}">  
											<font color="blue">已过期</font>
										</c:if>
									</td>
								</tr>
								<tr class="LBodTop">
									<td colspan="2" align="center">
										<input name="returnBack" type="button" style="width: 80px; height: 30px; color: #485058" value="返回" onclick="javascript:history.go(-1);" />
									</td>
								</tr>
							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>