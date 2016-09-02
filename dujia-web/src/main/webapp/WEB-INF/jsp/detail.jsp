<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<t:head_foot>
  <jsp:attribute name="header">
    <title>${productDetail.detail.subName}-国美在线</title>
    <meta name="description" content="国美旅行提供${detail.cityName}度假${productDetail.detail.subName},有国美,生活美！">
    <meta name="Keywords" content="${detail.cityName}度假,${productDetail.detail.subName}">
    <link rel="stylesheet" href="${_domain}/css/detail.css,/css/datepicker.css,/css/Focusmap.css?_v=${_v}" type="text/css"/>
  </jsp:attribute>
  <jsp:attribute name="footer">
      <script type="text/javascript" src="${_domain}/lib/zlDate.js,/js/jqueryFocusmap.js,/lib/jquery.lazyload.js,/js/detail.js,/js/baiduMap.js?_v=${_v}"></script>
      <script>
            (function(){
                var bp = document.createElement('script');
                bp.src = '//push.zhanzhang.baidu.com/push.js';
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(bp, s);
            })();
            //      埋码
            s.pageName="度假周边游:详情页:${productDetail.detail.subName}";
            s.channel="度假周边游";
            s.prop1="度假周边游";
            s.prop2="度假周边游:详情页";
            s.prop3="度假周边游:详情页";
            s.prop4="度假周边游";
            var s_code=s.t();
            if(s_code)document.write(s_code);

      </script>
  </jsp:attribute>
    <jsp:body>

        <div class="content">
            <!-- 面包屑 -->
            <div class="breadcrumb">
                <h2><a href="${_domain_lvxing}">旅行</a></h2>&gt;<a  href="${_domain}">度假</a>&gt;<a  href="${_domain}/${productDetail.detail.cityPinyin}/t-d-c-s-h.html">${productDetail.detail.cityName}周边游</a>&gt;<span>${productDetail.detail.subName}</span>
            </div>
            <div class="content pt20 bg_white main_content clearfix">
                <!--轮播图 -->
                <div class="focal" id="focal01">
                    <div class="focal-con">
                        <div class="scroll-wrap-inner">
                            <ul>
                                <c:forEach var="img" items="${productDetail.imgUrl}">
                                    <li>
                                        <a href="javascript:">
                                            <img src="${img.value}" alt="${productDetail.detail.subName}" width="500"/>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="focal-btn">
                        <a class="focal-btn-lt" href="javascript:;" onclick="return false;"></a>
                        <a class="focal-btn-rt" href="javascript:;" onclick="return false;"></a>
                        <ol class="focal-tab">

                            <c:forEach var="img" items="${productDetail.imgUrl}" varStatus="status">
                                <li <c:if test="${status.index == 0}"> class="focal-active"</c:if>>
                                    <a href="javascript:">
                                        <img src="${img.key}" alt="${productDetail.detail.subName}" height="74">
                                    </a>
                                </li>
                            </c:forEach>

                        </ol>
                    </div>
                </div>

                <div class="summary">
                    <h1 class="title">${productDetail.detail.mainName}</h1>
                    <p>
                        <c:if test="${fn:length(productDetail.theme) > 0}">
                        <span></span>
                        <c:forEach items="${productDetail.theme}" var="the">${the}&nbsp;&nbsp;</c:forEach>
                        </c:if>
                    </p>
                    <div class="price_code">

                        <c:choose>
                            <c:when test="${productDetail.isDelete == 'true'}">
                                <dl class="price">已下架</dl>
                            </c:when>
                            <c:otherwise>
                                <dl class="price">
                                    <dt class="price_tab">国美价：</dt>
                                    <dd class="pricenum">¥ <i></i> ${productDetail.detail.sellUnit}</dd>
                                </dl>
                            </c:otherwise>
                        </c:choose>
                        <div class="declare-box">
                            <span class="">价格说明</span>
                            <span class="J_instruction price-description" data_content="价格将会随所选出行日期、人数及套餐内容而变，此价格为不含附加服务(如单人房差、保险费等)的基本价格。"></span>
                        </div>
                        <dl class="code">
                            <dt>产品编号：</dt>
                            <dd>${productDetail.detail.productId}</dd>
                        </dl>
                    </div>
                    <ul class="about">
                        <li><span class="about-days"></span><span>行程天数：</span><span>${productDetail.detail.days}天${productDetail.detail.days-1}晚</span></li>
                        <c:if test="${fn:length(productDetail.sceneries) > 0}">
                            <li class="J_detail">
                                <span class="about-sencen"></span>
                                <span>游玩景点：</span>
                                <span>
                                    <c:forEach items="${productDetail.sceneries}" var="res">
                                        <c:if test="${res.resType == 1}">
                                            <a href="javascript:" resId="${res.resId}">${res.resName}&nbsp;&nbsp;</a>
                                        </c:if>
                                    </c:forEach>
                                </span>
                            </li>
                        </c:if>
                        <c:if test="${fn:length(productDetail.hotels) > 0}">
                        <li class="J_detail">
                            <span class="about-hotel"></span>
                            <span>入住酒店：</span>
                            <span>
                                 <c:set var="hotelIndex1" value=""></c:set>
                                 <c:forEach items="${productDetail.hotels}" var="res" varStatus="state">
                                     <c:if test="${res.resType == 0}">
                                         <a href="javascript:" resId="${res.resId}">${res.resName}&nbsp;&nbsp;</a>
                                     </c:if>
                                     <c:if test="${state.index == 0}">
                                         <c:set var="hotelIndex1" value="${res.lon}-${res.lat}"></c:set>
                                     </c:if>
                                 </c:forEach>
                            </span>
                        </li>
                        </c:if>
                    </ul>
                </div>
            </div>
            <div class="meal_info">
                <!-- <p class="time_container">
                    <label for="J_Calender">入住</label>
                    <span class="time">
                        <input type="text" id="J_Calender" readonly="readonly"/>
                        <s></s>
                    </span>
                </p> -->
                <div class="meal content">
                    <div class="comboGroup" id="meals" style="display: none;">
                        <div class="no_meals">
                            <img src="../images/tips.png" />很抱歉，暂无可选择的套餐，您可以查看其它商品！
                        </div>
                    </div>
                    <div class="cover">
                        <img src="../../images/icon_jiazai.gif" />正在加载中...
                    </div>
                </div>
            </div>

            <div class="content direction clearfix">
                <div class="dir">
                    <div class="headert">
                        <h2><a href="#privilege" class="active">优惠信息</a></h2>
                        <h2><a href="#journey">行程推荐</a></h2>
                        <h2><a href="#notice">预定须知</a></h2>
                    </div><!-- /header -->
                    <div id="privilege">
                        <p class="direction_tit">优惠信息</p>
                        ${productDetail.detail.preferential}
                    </div>
                    </br>
                    <div id="">
                        <p class="direction_tit">友情提醒</p>
                        ${productDetail.detail.remindInfo}
                    </div>
                    <div id="journey">
                        <p class="direction_tit">行程推荐</p>
                        ${productDetail.detail.productRouteDes}
                    </div>
                    </br>
                    <div id="notice">
                        <p class="direction_tit">预定须知</p>
                        ${productDetail.detail.attentionInfo}
                    </div>
                    </br>
                    <div>
                        <p class="direction_tit">退改说明</p>
                        ${productDetail.detail.refundRuleInfo}
                    </div>
                    </br>
                </div>
                <div class="adver">
                    <!-- map -->
                    <div class="map">
                        <div id="header" class="title">
                            <h2>交通地图</h2>
                            <a href="javascript:" class="mini_map_action"></a>
                        </div><!-- /header -->
                        <div id="mini_map" addr="${hotelIndex1}"></div>
                        <div id="mini_map_panel" class="mini_map_panel">
                            <div class="accordion map_accordion map_accordion-processed">
                                <div class="accordion_group group_index_0" data-type="hotels_to_sceneries">
                                    <div class="accordion_title">
                                        <a href="javascript:">酒店到景区的交通线路</a>
                                    </div>
                                    <div class="accordion_content">
                                        <div class="traffic_routers mini-traffic-routers-processed">
                                            <c:forEach var="hotelSingle" items="${productDetail.hotels}" varStatus="hotelStates">
                                                <c:forEach var="sencenSingle" items="${productDetail.sceneries}" varStatus="sencenStates">
                                                    <div class="traffic_router traffic_router_min" data-traffic-router-index="${hotelStates.index + sencenStates.index +1}" data_hTs_index="${hotelStates.index},${sencenStates.index}">
                                                        <dl class="" beginlon="${hotelSingle.lon}" beginlat="${hotelSingle.lat}" endlon="${sencenSingle.lon}" endlat="${sencenSingle.lat}">
                                                            <dt> ${hotelStates.index + sencenStates.index +1}</dt>
                                                            <dd>
                                                                <span class="largemap_hotel">${hotelSingle.resName}</span> → <span class="largemap_sencen">${sencenSingle.resName}</span>
                                                            </dd>
                                                            <dd class="total_distance"></dd>
                                                        </dl>
                                                    </div>
                                                </c:forEach>
                                            </c:forEach>
                                        </div>

                                    </div>
                                </div>
                                <%--<div class="accordion_group group_index_1 expanded" data-type="stations">					<div class="accordion_title">
                                    <a href="#">机场/火车站/汽车站</a>
                                </div>
                                    <div class="accordion_content">
                                        <div class="traffic_routers mini-traffic-routers-processed">
                                            <div class="traffic_router traffic_router_6" data-traffic-router-index="6" data-start-point="120.189606,30.249207" data-end-point="120.095729,30.263314"><dl class=""><dt>1</dt><dd><span>杭州站</span> → <span>杭州溪尚酒店</span></dd><dd class="total_distance">直线距离约9.2公里</dd></dl></div>

                                            <div class="traffic_router traffic_router_7" data-traffic-router-index="7" data-start-point="120.219395,30.297149" data-end-point="120.095729,30.263314"><dl class=""><dt>2</dt><dd><span>杭州东站</span> → <span>杭州溪尚酒店</span></dd><dd class="total_distance">直线距离约12.5公里</dd></dl></div>

                                            <div class="traffic_router traffic_router_8" data-traffic-router-index="8" data-start-point="120.30038,30.177773" data-end-point="120.095729,30.263314"><dl class=""><dt>3</dt><dd><span>杭州火车南站</span> → <span>杭州溪尚酒店</span></dd><dd class="total_distance">直线距离约21.8公里</dd></dl></div>

                                            <div class="traffic_router traffic_router_9" data-traffic-router-index="9" data-start-point="120.443345,30.238707" data-end-point="120.095729,30.263314"><dl class=""><dt>4</dt><dd><span>杭州萧山国际机场</span> → <span>杭州溪尚酒店</span></dd><dd class="total_distance">直线距离约33.5公里</dd></dl></div>
                                        </div>
                                    </div>
                                </div>--%>
                            </div>
                        </div>
                    </div>
                    <!-- favorate -->
                    <div class="favorate">
                        <h2>您还可能喜欢</h2><!-- /header -->
                        <ul class="favorate_content" id="favorate"></ul>
                    </div>

                    <!-- 历史记录 -->
                    <div class="hotest_sales" id="historyView">
                        <h4>浏览历史</h4>
                        <ul id="history"></ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="largemap"  >
            <div class="largemap_top clearfix">
                <h3>交通地图</h3>
                <a href="javascript:" class="largemap_close" id="largemap_close">
                    <em class="m_icon"></em>
                </a>
            </div>
            <div class="largemap_left" id="largemap">
            </div>
            <div class="largemap_right">
                <div id="map-line" class="mini_map_panel bigMap">
                    <div class="accordion map_accordion map_accordion-processed">
                        <div class="accordion_group group_index_0" data-type="hotels_to_sceneries">
                            <div class="accordion_title">
                                <a href="javascript:">酒店到景区的交通线路</a>
                            </div>
                            <div class="accordion_content">
                                <div class="traffic_routers mini-traffic-routers-processed">
                                    <c:forEach var="hotelSingle" items="${productDetail.hotels}" varStatus="hotelStates">
                                        <c:forEach var="sencenSingle" items="${productDetail.sceneries}" varStatus="sencenStates">
                                            <div class="traffic_router traffic_router_big" data-traffic-router-index="${hotelStates.index + sencenStates.index +1}" data_hTs_index="${hotelStates.index},${sencenStates.index}">
                                                <dl class="" beginlon="${hotelSingle.lon}" beginlat="${hotelSingle.lat}" endlon="${sencenSingle.lon}" endlat="${sencenSingle.lat}">
                                                    <dt> ${hotelStates.index + sencenStates.index +1}</dt>
                                                    <dd>
                                                        <span class="largemap_hotel">${hotelSingle.resName}</span> → <span class="largemap_sencen">${sencenSingle.resName}</span>
                                                    </dd>
                                                    <dd class="total_distance"></dd>
                                                </dl>
                                                <div class="tap_nav clearfix">
                                                    <ul class="tap_nav_driver clearfix">
                                                        <li class="active" type="1"><i></i>驾车</li>
                                                        <li type="2"><em></em>公交</li>
                                                    </ul>
                                                    <div class="driver" id="driver${sencenStates.index}"></div>
                                                    <div class="busing" id="busing${sencenStates.index}"></div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:forEach>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="lean_overlay" class="lean_overlay" style="display: none;"></div>
        <div id="popdetail" class="popdetail"></div>
        <input type="hidden" id="productId" value="${productDetail.detail.productId}" />
        <input type="hidden" id="betweenDays" value="${productDetail.detail.days}" />
        <input type="hidden" id="inDate" value="20160505" />
        <input type="hidden" id="isDelete" value="${productDetail.isDelete}">
    </jsp:body>
</t:head_foot>