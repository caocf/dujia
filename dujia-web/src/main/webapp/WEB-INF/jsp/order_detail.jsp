<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <meta http-equiv="Cache-Control" content="no-transform" />
    <meta http-equiv="Content-Type"  content="text/html; charset=utf-8" />
    <title>会员中心—订单详情</title>
	<c:choose>
		<c:when test="${_env == 'prd'}">
			<c:set var="numberEnv" value="gome"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="numberEnv" value="atguat"></c:set>
		</c:otherwise>
	</c:choose>
	<link rel="stylesheet" href="http://css.${numberEnv}.com.cn/??/gmpro/1.0.0/member/1.0.0/common/1.0.0/css/h_b_c.min.css,/gmpro/1.0.0/member/1.0.0/common/1.0.0/css/vipMainHeader.min.css,/gmpro/1.0.0/public/1.0.0/css/head.min.css,/gmpro/1.0.0/member/1.0.0/common/1.0.0/css/mygome_new.min.css,/gmpro/1.0.0/member/1.0.0/common/1.0.0/css/pager.min.css,/gmpro/1.0.0/member/1.0.0/common/1.0.0/css/combox.min.css?v=20160421"/>
    <!--#include virtual="/n/common/global/global.html"-->
    <link rel="stylesheet" href="http://css.${_resource_env}.net.cn/??gmlib/reset/1.1.0/reset.css,gmpro/1.0.0/public/1.0.0/css/top.min.css,gmpro/1.0.0/public/1.0.0/css/foot.min.css,gmpro/1.0.0/public/1.0.0/css/aside.min.css?v=201606281127" type="text/css" />
    <link rel="stylesheet" href="${_domain}/css/order_detail.css?_v=${_v}"  type="text/css">
    <script src="http://js.${_resource_env}.net.cn/??<!--#include virtual='/n/common/a09/js.html'-->"></script>
    <script type="text/javascript" >
        var flag=0;
    </script>
</head>

<body>
<!-- 头部begin -->
<!--#include virtual="/n/common/a09/head.html"-->
<!--#include virtual="/ec/homeus/memberHeader.html"-->
<!-- 头部 end-->

<!-- 以下是 面包屑及以下 的内容部分 -->
<div class="order-xq clearfix">
    <!-- 当前位置 -->
    <ul class="breadcrumb">
        <li id="tx1">您的位置：</li>
        <li id="tx2"><a href="http://myhome.${numberEnv}.com.cn/member/profileHome?intcmp=sy-public01051" title="我的国美">我的国美</a>&nbsp;&gt;</li>
        <li id="tx3"><a href="http://myhome.${numberEnv}.com.cn/member/myOrder?intcmp=dujia-public01004&flag=a0" title="我的订单">我的订单</a>&nbsp;&gt;</li>
        <li id="tx4">订单详情</li>
    </ul>

    <div class="cont-middle order-xq-l clearfix">
        <div class="cont-m-l flights_box">
            <div class="cont-l-top">
                <dl class="cont-l-a">
                    <dd>
                        <span>订单状态：</span>
                        <span >
                            <c:if test="${orderInfo.orderStatus == 1}">
                                <span>待付款</span>
                            </c:if>

                            <c:if test="${orderInfo.orderStatus == 2 || orderInfo.orderStatus == 3}">
                                <span>已付款</span>
                            </c:if>

                            <c:if test="${orderInfo.orderStatus == 4 || orderInfo.orderStatus == 8}">
                                <span>已确认</span>
                            </c:if>

                            <c:if test="${orderInfo.orderStatus == 6}">
                                <span>已取消</span>
                            </c:if>

                            <c:if test="${orderInfo.orderStatus == 5 || orderInfo.orderStatus == 9}">
                                <span>已退款</span>
                            </c:if>

                            <c:if test="${orderInfo.orderStatus == 7}">
                                <span>已完成</span>
                            </c:if>

                            <c:if test="${orderInfo.orderStatus == 10}">
                                <span>超时支付</span>
                            </c:if>
                        </span>
                    </dd>

                    <dd>订单总额：<span class="red-bold"> <em>¥</em>${orderInfo.orderAmount}</span></dd>

                    <!-- 待付款 -->
                    <c:if test="${orderInfo.orderStatus == 1 && orderInfo.surplusTime > 0}">
                        <dd class="cont-l-b-l">
                            <a href="javascript:void(0);" class="cancel cancel_btns" id="cont_link" title="取消订单">取消订单</a>
                        </dd>
                        <dd class="cont-l-b-l w80 red-btn pay-btn" style="margin-top:0px;" id="cont_t_link">
                            <a href="${_domain}/order/pay.html?orderId=${orderInfo.orderId}" target="_blank">立即支付 </a>
                        </dd>
                    </c:if>
                </dl>

                <!-- 待付款 -->
                <c:if test="${orderInfo.orderStatus == 1}">
                    <div class="last_time"><span class="time"></span>剩余支付时间：<span class="se_eee red-bold"></span>(逾期未支付，将自动取消订单)</div>
                </c:if>
            </div>

            <!-- 订单状态履历 -->
            <div class="cont-l-body">
                <div id="statck">
                    <ul class="step clearfix">
                    </ul>
                </div>
                <div id="deployAll"></div>
            </div>

            <div class="cont-l-body pd0">
                <div class="orderinfo_detail">
                    <h3>订单信息</h3>
                    <dl class="order_base clearfix">
                        <dd>
                            <span class="base_name">商品信息：</span>
                            <span class="base_content">
                                <a href="${_domain}/${orderInfo.productId}.html" title="${orderInfo.productName}">${orderInfo.productName}</a></br>
                                ${orderInfo.packageName}
                            </span>
                        </dd>

                        <dd class="order_room">
                            <!-- 酒店信息 -->
                            <c:if test="${hotelList != null && hotelList.size() > 0}">
                                <table>
                                    <thead>
                                        <tr>
                                            <th width="5%"><i class="sleep"></i></th>
                                            <th width="20%">酒店名称</th>
                                            <th width="20%">房型</th>
                                            <th width="15%">数量</th>
                                            <th width="20%">入住时间</th>
                                            <th width="20%">退房时间</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${hotelList}" var="hoelInfo">
                                        <tr>
                                          <td></td>
                                          <td>${hoelInfo.resourceName}</td>
                                          <td>${hoelInfo.resourceProName}</td>
                                          <td>${hoelInfo.resourceCount}间</td>
                                          <td><fmt:formatDate value="${hoelInfo.useStartTime}" pattern="yyyy-MM-dd" /></td>
                                          <td><fmt:formatDate value="${hoelInfo.useEndTime}"   pattern="yyyy-MM-dd" /></td>
                                        </tr>
                                       </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>

                            <!-- 景区信息 -->
                            <c:if test="${sceniList != null && sceniList.size() > 0}">
                                <table>
                                    <thead>
                                        <tr>
                                            <th width="5%"><i class="play"></i></th>
                                            <th width="20%">景点名称</th>
                                            <th width="20%">票种</th>
                                            <th width="15%">数量</th>
                                            <th width="20%">游玩日期</th>
                                            <th width="20%"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                      <c:forEach items="${sceniList}" var="sceniInfo">
                                      	<tr>
                                          <td></td>
                                          <td>${sceniInfo.resourceName}</td>
                                          <td>${sceniInfo.resourceProName}</td>
                                          <td>${sceniInfo.resourceCount}张</td>
                                          <td><fmt:formatDate value="${sceniInfo.useStartTime}" pattern="yyyy-MM-dd"/></td>
                                          <td></td>
                                         </tr>
                                      </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                        </dd>
                    </dl>

                    <dl class="party_info clearfix">
                        <dt>出游人信息 <span>${orderInfo.adultNum}成人</span></dt>
                        <dd>
                            <table bor="">
                                <thead>
                                <tr>
                                    <th width="25%">出游人姓名</th>
                                    <th width="25%">手机号码</th>
                                    <th width="25%">证件类型</th>
                                    <th width="25%">证件号码</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${travelerList}" var="travelerInfo">
                                        <tr>
                                            <td>${travelerInfo.name}</td>
                                            <td>${travelerInfo.mobile}</td>
                                            <td>
                                                <c:if test="${not empty travelerInfo.certificateCode && travelerInfo.certificateType == 1}">
                                                    身份证
                                                </c:if>
                                            </td>
                                            <td>${travelerInfo.certificateCode}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </dd>
                    </dl>

                    <dl class="party_info contacts_info clearfix">
                        <dt>联系人信息</dt>
                        <dd>
                            <span class="keys">联系人姓名：</span><span class="values">${orderInfo.contactsName}</span>
                        </dd>
                        <dd>
                            <span class="keys">联系方式：</span><span class="values">${orderInfo.contactsTelphone}</span>
                        </dd>
                        <dd>
                            <span class="keys">联系邮箱：</span><span class="values">${orderInfo.contactsEmail}</span>
                        </dd>
                    </dl>
                </div>
            </div>

            <!-- 退款信息 -->
            <c:if test="${refundList != null}">
                <div class="cont-l-body drawback">
                    <h3>退款信息</h3>
                    <p class="drawback_fee">退款总金额：<span><i>¥</i> ${refundTotalAmount}</span></p>
                    <table bor="">
                        <thead>
                            <tr>
                                <th width="25%">退款单号</th>
                                <th width="25%">退款申请时间</th>
                                <th width="25%">退款金额(元)</th>
                                <th width="25%">退款状态</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${refundList}" var="refundInfo">
                                <tr>
                                    <td>${refundInfo.refundId}</td>
                                    <td><fmt:formatDate value="${refundInfo.createTime}"     pattern="yyyy-MM-dd"/></td>
                                    <td><fmt:formatNumber value="${refundInfo.returnAmount}" pattern="0.00"/></td>
                                    <td>${refundInfo.refundStatusString}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>

    <!-- 右侧 -->
    <div class="cont-m-r order_info">
        <ul class="cont-m-r-item">
            <li class="cont-r-titles">
                <a href="http://shouji.gome.com.cn/" target="_blank" class="orderAlltime"><i></i>订单随时看</a>
                <h3 class="bold pdl20">订单信息</h3>
                <div class="pdl20 pdt10 order_block">
                    <p class="h25 clearfix"> <strong class="font-bold">订单编号：</strong>
                        <span class="font-bold" id="orderId">${orderInfo.orderId}</span>
                    </p>
                </div>

                <ul class="pdl20 pdt10 order_block">
                    <li class="online">
                        <strong class="fl">订单总额：</strong>
                        <em class="">¥ ${orderInfo.orderTotalAmount}</em>
                    </li>
                    <li class="online">
                        <strong class="fl">应付总额：</strong>
                        <em class="total">¥ ${orderInfo.paymentAmount}</em>
                    </li>
                </ul>

                <div class="pd_consult">
                    <img src="${_domain}/images/icon4.png" class="pd_telImg" />
                    <div class="pd_new">
                        <h2 class="pd_tel">客服电话</h2>
                        <ul class="consult">
                            <li>服务热线：<em>0512-80837557</em></li>
                            <li>咨询时间：早<em>8:00</em>-晚<em>24:00</em></li>
                        </ul>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>


<!-- 取消订单 start-->
<div class="pop_cancel_order">
    <a href="javascript:;" class="btn_flight_close"></a>
    <div class="clearfix flight_tip_box">
        <div class="tip_icon"></div>
        <div class="tip_worp">取消订单中，请稍后。</div>
    </div>
</div>
<!-- 取消订单 end-->


<!-- 提示是否取消订单 start -->
<div class="t hide" id="cancelOrderPop">
    <div class="pop delOrderPop">
        <div class="deleleText">
            <p>您确定要取消订单吗？</p>
            <p class="grayfont mt2 ml2">
                <a class="brownBtn fl" id="cancelConfirm">确定</a>
                <a href="javascript:;" class="whiteBtn fl ml2" id="notCancel">取消</a>
            </p>
        </div>
    </div>
</div>
<!-- 提示是否取消订单 end-->


<!-- 弹出窗 alert 和确认窗 confirm-->
<div class="modal-wapper clearfix" id="alert">
    <a href="javascript:" class="close_modal"></a>
    <p class="modal-text"></p>
    <p class="btns">
        <a href="javascript:void(0)" class="btnsConfirm"></a>
    </p>
</div>


<!-- 公共底部 start -->
<!--#include virtual="/n/common/a09/foot.html"-->
<!--#include virtual="/n/common/a09/aside.html"-->
<!-- 公共底部 end -->
<script type="text/javascript" src="/js/time.js,/js/common.${_env}.js,/js/dateUtil.js,/js/order_step.js,/js/order_detail.js?_v=${_v}" ></script>


<script type="text/javascript">
    $(function() {
        var surplusTime = parseInt('${orderInfo.surplusTime}');
        if(surplusTime <=  0){
            $('.cont-l-b-l').remove();
        }
        timerStart(surplusTime, ".se_eee", ".cont-l-b-l");
    });
</script>

<script type="text/javascript" src="http://js.${_resource_env}.net.cn/gmlib/unit/scode/1.0.0/scode.js"></script>
<script type="text/javascript" src="http://js.${_resource_env}.net.cn/f2ejs/common/bigData.min.js"></script>

<script type="text/javascript">
    s.pageName="用户中心:我的交易:我的订单:订单详情";
    s.channel="用户中心";
    s.prop1="用户中心:我的交易";
    s.prop2="用户中心:我的交易:我的订单";
    s.prop3="用户中心:我的交易:我的订单:订单详情";
    s.prop4="用户中心";
    var s_code=s.t();
    if(s_code)document.write(s_code);
</script>
</body>
</html>