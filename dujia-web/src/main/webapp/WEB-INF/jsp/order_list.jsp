<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>会员中心—订单列表</title>
    <!-- 公共头部 -->
    <script type="text/javascript">var dynSite='http://g.gome.com.cn',staSite='http://www.gome.com.cn',contextPath='/ec/homeus',imgServer='http://img.gomein.net.cn/images',secureURL='https://g.gome.com.cn:443',pictureserver='http://img.gomein.net.cn/image',cookieDomain='.gome.com.cn',stageJsServer='http://js.gomein.net.cn',stageImageServer='http://img.gomein.net.cn',stageCssServer='http://css.gomein.net.cn',cssserver='http://css.gomein.net.cn/css',jsserver='http://js.gomein.net.cn/js',versionData='1604130302';</script>
    <link rel="stylesheet" href="http://css.gomein.net.cn/??gmlib/reset/1.1.0/reset.css,gmpro/1.0.0/public/1.0.0/css/foot-new.min.css,gmpro/1.0.0/public/1.0.0/css/top.min.css"  type="text/css">
    <link rel="shortcut icon" href="http://img.gomein.net.cn/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="http://css.gomein.net.cn/css/n/firstPage/h_b_c.min.css"  type="text/css" />
    <link rel="stylesheet" href="http://css.gomein.net.cn/f2ecss/myaccount/mygome_new.css"  type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${_domain}/css/order_list.css?_v=${_v}"  type="text/css">
</head>
<body>
<!--此处都是从线上环境扒下来的 新增加的会做标示不会影响已有的代码-->
<div id="all_orders" class="c-l-tab">

    <div class="bulletin_box">
        <div class="bulletin-board clearfix">
            <div id="timeSlotSelect" class="sel_choos active">
                <c:choose>
                    <c:when test="${timeSlot eq 1}">
                        <span id="timeSlotSpan" tval="1">近三个月的订单</span>
                    </c:when>
                    <c:when test="${timeSlot eq 2}">
                        <span id="timeSlotSpan" tval="2">近一年内的订单</span>
                    </c:when>
                    <c:when test="${timeSlot eq 3}">
                        <span id="timeSlotSpan" tval="3">更早的订单记录</span>
                    </c:when>
                    <c:otherwise>
                        <span id="timeSlotSpan" tval="1">近三个月的订单</span>
                    </c:otherwise>
                </c:choose>
                <div id="timeSlotBox" class="time-menu" style="display: none;">
                    <ul class="time-menu-ul">
                        <li><a href="javascript:;" time="1">近三个月的订单</a></li>
                        <li><a href="javascript:;" time="2">近一年内的订单</a></li>
                        <li><a href="javascript:;" time="3">更早的订单记录</a></li>
                    </ul>
                </div>
            </div>

            <div class="pay-box" id="d_wait_pay">
                <span class="wait-pay js-bullBtn" tval="1" id="wait_pay">待付款</span>
                <span class="pay-num" id="wait_pay_count">${waitPay}</span>
            </div>

            <div class="search-box">
                <input type="text" placeholder="订单编号" class="seach_inpt" id="orderId" value="${orderId}"/>
                <input type="button" value="" class="search-btn" id="js-search-btn"/>
            </div>
        </div>
    </div>

    <div class="list-box">
        <!-- 订单列表  新增部分内容 start-->
        <table cellpadding="0" cellspacing="0" width="100%" class="orders_train">
            <thead>
            <th width="5%">类型</th>
            <th width="15%">订单编号</th>
            <th width="10%">预订日期</th>
            <th width="20%">预订商品</th>
            <th width="15%">数量</th>
            <th width="10%">订单金额</th>
            <th width="10%">订单状态</th>
            <th width="15%">操作</th>
            </thead>
            <tbody>
            <c:if test="${fn:length(orderInfos) gt 0}">
                <c:forEach varStatus="vstatus" var="order" items="${orderInfos}">
                    <tr>
                        <td class="word_col"><img src="../images/order_type.png" /></td>
                        <td class="setNum">${order.orderId} </td>
                        <td class="setNum"><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd" /></td>
                        <td class="word_ali">
                            <div class="name">${order.packageName}</div>
                            <div class="_f">出游日期：<fmt:formatDate value="${order.travelStartTime}" pattern="yyyy-MM-dd"/></div>
                        </td>
                        <td>
                            <p style="padding:0;"><em class="dollar-sign">￥</em>${order.packagePrice}</p>
                            <span class="word_col">${order.buyCount} 张</span>
                        </td>
                        <td>
                            <p style="padding:0;"><em class="dollar-sign">￥</em><span style="font-family:Arial;">${order.orderAmount}</span></p>
                            <span class="pay-method">在线支付</span>
                        </td>

                        <td class="">
                            <p class="status">
                                <c:if test="${order.orderStatus == 1}">待付款</c:if>

                                <c:if test="${order.orderStatus == 2 || order.orderStatus == 3}">
                                    已付款
                                </c:if>

                                <c:if test="${order.orderStatus == 4 || order.orderStatus == 8}">
                                    已确认
                                </c:if>

                                <c:if test="${order.orderStatus == 6}">
                                    已取消
                                </c:if>

                                <c:if test="${order.orderStatus == 5 || order.orderStatus == 9}">
                                    已退款
                                </c:if>

                                <c:if test="${order.orderStatus == 7}">已完成</c:if>

                                <c:if test="${order.orderStatus == 10}">超时支付</c:if>
                            </p>
                        </td>
                        <!-- 操作部分 -->
                        <td class="qx">
                            <!--待付款订单-->
                            <div class="orders_train_control">
                                <c:if test="${order.orderStatus == 1}">
                                    <div class="timerDiv" time="${order.surplusTime}"  orderId="${order.orderId}" >
                                        <span class="time red order_flights_time"></span>
                                        <a href="javascript:;" class="time-ico r-time-ico"></a>
                                        <a href="${_domain}/order/pay.html?orderId=${order.orderId}"  target="_blank" class="pay-btn" id="cont_t_link">立即支付 </a>
                                    </div>
                                </c:if>
                            </div>
                            <!--待付款订单--end-->
                            <div class="order-cont">
                                <a class="modify v-o" target="_blank" href="${_domain}/order/order_detail/query.html?orderId=${order.orderId}" title="订单详情">订单详情</a>
                                <c:if test="${order.orderStatus == 1 }">
                                    <a class="cancel cancel_btns" title="取消订单" >取消订单</a>
                                    <input type="hidden" value="${order.orderId}" name='orderId'/>
                                </c:if>
                                <c:if test="${order.orderStatus == 7}">
                                    <a class="go_on cancel" href="/" target="_blank">继续预定</a>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>

        <c:if test="${fn:length(orderInfos) eq 0}">
            <div class="orderContent" style="border-bottom-style: none;">
                <p class="no-order">
                    暂无订单，这就去 <a href="/" class="guang" target="_blank">购买周边游线路</a>
                </p>
            </div>
        </c:if>
        <!-- 订单列表  新增部分内容 end-->
    </div>

    <c:if test="${fn:length(orderInfos) gt 0}">
        <div class="page_box">
            <div class="paging">
                <div class="paging-left">
                    <div class="previous" testers=";1">
                        <c:choose>
                            <c:when test="${pageIndex lt 2 }">
                                <a href="javascript:;" class="heavycolor">
                                    <span class="previous-sign">&lt;</span> 上一页
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:searchOrder(<c:out value="${pageIndex -1}" />)"  class="">
                                    <span class="previous-sign">&lt;</span> 上一页
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="page-num">
                        <ul>
                            <c:choose>
                                <c:when test="${totalPages le 7}">
                                    <c:forEach begin="1" end="${totalPages}" var="index" step="1">
                                        <c:choose>
                                            <c:when test="${pageIndex  eq  index}">
                                                <li a="<c:out value="${index}" />" class="real-num">
                                                    <a class="current" href="javascript:;"><c:out  value="${index}"/></a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li a="1" class="real-num">
                                                    <a class=""  href="javascript:searchOrder(<c:out value="${index}"/>)"><c:out value="${index}"/></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${pageIndex le 5}">
                                    <c:forEach begin="1" end="6" var="index" step="1">
                                        <c:choose>
                                            <c:when test="${pageIndex  eq  index}">
                                                <li a="1" class="real-num">
                                                    <a class="current"  href="javascript:;"><c:out value="${index}"/></a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li a="1" class="real-num">
                                                    <a class="" href="javascript:searchOrder(<c:out value="${index}" />)"><c:out value="${index}"/></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                    <c:if test="${pageIndex lt 5 && totalPages eq 8}">
                                        <li a="4" class="ellipsis">
                                            <a href="javascript:;"><c:out  value="..."/></a>
                                        </li>
                                    </c:if>

                                    <c:if test="${pageIndex eq 5}">
                                        <li a="1" class="real-num">
                                            <a class="" href="javascript:searchOrder(7)">7</a>
                                        </li>
                                    </c:if>

                                    <c:if test="${totalPages gt 8}">
                                        <li a="4" class="ellipsis">
                                            <a href="javascript:;"><c:out value="..."/></a>
                                        </li>
                                    </c:if>

                                    <li a="1" class="real-num">
                                        <a class="" href="javascript:searchOrder(<c:out value="${totalPages}" />)"><c:out value="${totalPages}"/></a>
                                    </li>
                                </c:when>
                                <c:when test="${( pageIndex + 2 ) le totalPages}">
                                    <li a="1" class="real-num"><a class="" href="javascript:searchOrder(1)">1</a></li>
                                    <li a="4" class="ellipsis"><a href="javascript:;"><c:out value="..."/></a></li>
                                    <c:forEach begin="${pageIndex-3}" end="${pageIndex + 2}"  var="index" step="1">
                                        <c:choose>
                                            <c:when test="${pageIndex eq index}">
                                                <li a="1" class="real-num">
                                                    <a class="current"  href="javascript:;"><c:out value="${index}"/></a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li a="1" class="real-num">
                                                    <a class="" href="javascript:searchOrder(<c:out value="${index}" />)"><c:out value="${index}"/></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <c:if test="${(pageIndex+3) lt totalPages}">
                                        <li a="4" class="ellipsis"><a href="javascript:;"><c:out value="..."/></a></li>
                                    </c:if>
                                    <c:if test="${(pageIndex+2) lt totalPages}">
                                        <li a="1" class="real-num">
                                            <a class="" href="javascript:searchOrder(<c:out value="${totalPages}" />)"><c:out value="${totalPages}"/></a>
                                        </li>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <li a="1" class="real-num">
                                        <a class="" href="javascript:searchOrder(1)">1</a>
                                    </li>
                                    <li a="4" class="ellipsis">
                                        <a href="javascript:;"><c:out value="..."/></a>
                                    </li>
                                    <c:forEach var="index" begin="${totalPages- 5}"  end="${totalPages}" step="1">
                                        <c:choose>
                                            <c:when test="${pageIndex eq index}">
                                                <li class="real-num">
                                                    <a class="current" href="javascript:;"><c:out value="${index}"/></a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="real-num">
                                                    <a class="" href="javascript:searchOrder(<c:out value="${index}" />)"><c:out value="${index}"/></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                    <c:choose>
                        <c:when test="${pageIndex lt totalPages}">
                            <div class="next">
                                <a href="javascript:searchOrder(<c:out value="${pageIndex + 1}"/>)" class="">下一页 <span class="next-sign">&gt;</span>
                                </a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="next">
                                <a href="javascript:;" class="heavycolor">下一页 <span class="next-sign">&gt;</span></a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="paging-right">
                    <div class="to-page">
                        <span>到</span> <input type="text" value="" id="toPageNum"/> <span>页</span>
                    </div>
                    <div class="warp-sure">
                        <a href="javascript:queryOrderByNum()" class="brownBtn fl page-confirm">确定</a>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>
<input type="hidden" id="currPageIndex" value="${pageIndex}"/>
<c:if test="${orderStatus != null}">
    <input type="hidden" id="orderStatus" value="${orderStatus}" />
</c:if>

<!-- 弹出窗 alert 和确认窗 confirm-->
<div class="modal-wapper clearfix" id="alert">
    <a href="javascript:" class="close_modal"></a>
    <p class="modal-text"></p>
    <p class="btns">
        <a href="javascript:void(0)" class="btnsConfirm"></a>
    </p>
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
<script type="text/javascript" src="${_domain}/js/jquery-1.7.2.min.js,/js/jquery.plugin.min.js,/js/jquery.countdown.min.js,/js/confirm.override.js,/js/dateUtil.js,/js/order_list.js?_v=${_v}"></script>
<script type="text/javascript">
    var context="${_domain}";
</script>
</body>
</html>