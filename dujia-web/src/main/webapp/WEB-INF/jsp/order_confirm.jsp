<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gt" uri="/WEB-INF/token.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta http-equiv="Cache-Control" content="no-transform"/>
    <meta name="applicable-device" content="pc">
    <link rel="shortcut icon" href="http://img.gomein.net.cn/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="http://css.gomein.net.cn/??gmlib/reset/1.1.0/reset.css,gmpro/1.0.0/public/1.0.0/css/foot-new.min.css,gmpro/1.0.0/public/1.0.0/css/top.min.css?v=201604131502">
    <link rel="stylesheet" href="${_domain}/css/common.css?_v=${_v}" type="text/css"/>
    <!-- 公共头部 导入的 -->
    <c:choose>
    <c:when test="${_env == 'dev'}">
    <script type="text/javascript">var dynSite='http://g.gome.com.cn',staSite='http://www.gome.com.cn',contextPath='/ec/homeus',imgServer='http://img.gomein.net.cn/images',secureURL='https://g.gome.com.cn:443',pictureserver='http://img.gomein.net.cn/image',cookieDomain='.gome.com.cn',stageJsServer='http://js.gomein.net.cn',stageImageServer='http://img.gomein.net.cn',stageCssServer='http://css.gomein.net.cn',cssserver='http://css.gomein.net.cn/css',jsserver='http://js.gomein.net.cn/js',versionData='1604130302';</script>
    </c:when>
    <c:otherwise>
    <!--#include virtual="/n/common/global/global.html"-->
    </c:otherwise>
    </c:choose>
    <title>度假订单页</title>
    <!--[if lt IE 9]>
    <script src="${_domain}/js/json2.js"></script>
		<![endif]-->
    <link rel="stylesheet" type="text/css" href="${_domain}/css/order_confirm.css,/css/datepicker.css?_v=${_v}">
</head>


  <body>
  	<gt:token/>
  	<input type="hidden" id="_productId" value="${productPackage.productId }"/>
      <input type="hidden" id="_packageId" value="${productPackage.packageId }"/>
      <input type="hidden" id="_travelDays" value="${travelDays }"/>
      <input type="hidden" id="_packageSaleDate" value="${productPackagePrice.packDate}"/>
      <input type="hidden" id="_adult_num" value="${productPackage.adultNum }"/>
      <input type="hidden" id="_child_num" value="${productPackage.childNum }"/>
      <input type="hidden" id="_tc_direct_price" value="${productPackagePrice.tcDirectPrice}"/>
      <input type="hidden" id="_need_card" value="${needCard}"/>
    <c:choose>
    <c:when test="${_env == 'dev'}">
    <div class="gome-top">
      <div id="gome-top-body" class="wbox">
        <ul>
          <li id="gome-logo-sm"><a data-code="public01001" href="http://www.gome.com.cn/" target="_blank"><i></i>国美在线</a></li>
          <li id="gome-user" data-load="user">
            <b id="loginDiv-name"><a data-code="public01053" href="http://v.gome.com.cn" target="_blank">国美会员</a><i></i></b>
            <div class="public-dropdown"></div>
          </li>
          <li id="loginDiv-link" class="gome-link">
            <a data-code="public01002" href="https://login.gome.com.cn/login">登录</a><a data-code="public01003" href="https://reg.gome.com.cn/register/index/person">注册</a>
          </li>
        </ul>
        <ul id="gome-top-right">
          <li class="gome-link"><a data-code="public01004" href="http://g.gome.com.cn/ec/homeus/myaccount/myOrders.jsp?flag=a0">我的订单</a></li>
          <li id="gome-mygome" data-load="mygome">
            <b><a data-code="public01050" href="http://g.gome.com.cn/ec/homeus/myaccount/gome/profileHome.jsp?flag=a" target="_blank">我的国美</a><i></i></b>
            <div class="public-dropdown"></div>
          </li>
          <li class="gome-spacer"></li>
          <li id="gome-service" class="drop" data-load="service">
            <b><em class="gm-icon">f</em><a data-code="public01052" href="http://help.gome.com.cn/" target="_blank">服务中心</a><i></i></b>
            <div class="public-dropdown"></div>
          </li>
          <li id="gome-sitemap" class="drop" data-type="b03" data-load="sitemap">
            <b>网站导航<i></i></b>
            <div class="public-dropdown"></div>
          </li>
          <li class="gome-spacer"></li>
          <li id="gome-phone" class="drop">
            <b><em class="gm-icon">á</em><a data-code="public01049" href="http://shouji.gome.com.cn/" target="_blank">手机国美</a><i></i></b>
            <div class="public-dropdown"><a data-code="public01005" href="http://shouji.gome.com.cn/" target="_blank"></a></div>
          </li>
        </ul>
      </div>
    </div>
    </c:when>
    <c:otherwise>
    <!-- 头部begin -->
    <!--#include virtual="/n/common/b03/head.html"-->
    <!-- 头部end -->
    </c:otherwise>
    </c:choose>
    <div class="pay-head">
      <div class="wrap">
        <div class="logo"><a href="http://lvxing.gome.com.cn/index.html"><img src="../images/gome-logo.png" alt=""></a></div>
        <ul class="step clearfix" id="_todo_">
          <li class="item cur"><b>1</b><div class="line"><span class="progress"></span><i></i></div><span class="text">填写订单信息</span>
          </li>
          <li class="item"><b>2</b><div class="line"></div><span class="text">在线支付</span>
          </li>
          <li class="item"><b>3</b><div class="line"></div><span class="text">待确认</span>
          </li>
          <li class="item"><b>4</b><span class="text">完成预订</span></li></ul>
      </div>
    </div>

    <div class="content clearfix mt30">
      <div class="reservationover">
        <div class="book_meal">
          <h3>预定套餐</h3><!-- /header -->
          <div class="book_direction">
            <h5>${productPackage.packageLongName}</h5>
            <dl class="clearfix">
              <dt><img src="${product.imageUrl}" alt="${productPackage.packageName}" width="205" height="110"></dt>
              <dd class="meal_name">${productPackage.packageName}</dd>
              <dd >
                <label>出游人数</label>
                <div class="total_num">
                  <div class="num_change"><i class="adult_num_del">-</i><input type="text" id="adultNum" value="${productPackage.adultNum}"/><i class="adult_num_add">+</i></div>
                  <span>成人</span>
                </div>
                <c:if test="${productPackage.childNum > 0}">
                  <div class="total_num">
                    <div class="num_change"><i class="child_num_del">-</i><input type="text" id="childNum" value="${productPackage.childNum}"/><i class="child_num_add">+</i></div>
                    <span>儿童</span>
                  </div>
                </c:if>
              </dd>
              <dd>
                <label>套餐份数</label>
                <div class="package_num total_num">
                  <div class="package_num_change num_change"><i class="total_num_del">-</i><input type="text" class="buyCount" id="buyCount" value="1"/><i class="total_num_add">+</i></div>
                  <span>份</span>
                  <em>(一份适用于${productPackage.adultNum}成人<c:if test="${productPackage.childNum > 0}">和${productPackage.childNum}儿童</c:if>)</em>
                </div>
              </dd>
              <dd class="order_amount">
                <label>订单金额</label>
                <div class="fontmaincolor amount">¥<i id="orderTotalAmount" class="orderTotalAmount">${productPackagePrice.tcDirectPrice}</i></div>
              </dd>
            </dl>
            <div class="life">
            	<c:if test="${not empty hotes }">
            		<table class="J_live live">
	                <thead>
	                <tr>
	                  <th width="5%"><i class="sleep"></i></th>
	                  <th style="display: none;">酒店资源产品id</th>
	                  <th width="20%">酒店名称</th>
	                  <th width="20%">房型</th>
	                  <th width="15%">数量</th>
	                  <th width="20%">入住日期</th>
	                  <th style="display: none;">第几天游玩</th>
	                  <th width="20%">退房日期</th>
	                </tr>
	                </thead>
	                <tbody>
	                  <c:forEach var="hote" items="${hotes}" varStatus="status">
	                    <c:if test="${hote.resType == 0}">
	                      <tr>
	                        <td></td>
	                        <td style="display: none;" class="resourceId">${hote.resId}</td>
	                        <td>${hote.resName}</td>
	                        <td>${hote.resProName}</td>
	                        <td>${hote.proContainCount}间</td>
	                        <td>
	                          <c:if test="${status.first==true }">
	                        		<span class="time_container">
	                           		<input id="J_Calender" class="useStartTime" readonly="readonly" placeholder="请选择入住日期" type="text" style="width: 100%;height: 100%; color:#666;"/>
	                            	<s></s>
                              </span>
	                        	</c:if>
	                          <c:if test="${status.first!=true }">
	                          	<span class="useStartTime"></span>
	                          </c:if>
	                        </td>
	                        <td style="display: none;" class="useDay">${hote.useDay}</td>
	                        <td>
	                          <span class="useEndTime"></span>
	                        </td>
	                      </tr>
	                    </c:if>
	                  </c:forEach>
	                </tbody>
	              </table>
            	</c:if>
            	<c:if test="${not empty sceneries }">
            		<table class="J_play">
                <thead>
                <tr>
                  <th width="5%"><i class="play"></i></th>
                  <th style="display: none;">景点资源产品id</th>
                  <th width="20%">景点名称</th>
                  <th width="20%">票种</th>
                  <th width="15%">数量</th>
                  <th width="20%">游玩日期</th>
                  <th style="display: none;">第几天游玩</th>
                  <th width="20%"></th>
                </tr>
                </thead>
                <tbody>
                	<c:forEach var="scenerie" items="${sceneries}" varStatus="status">
                    <c:if test="${scenerie.resType == 1}">
                      <tr>
                        <td></td>
                        <td style="display: none;" class="resourceId">${scenerie.resId}</td>
                        <td>${scenerie.resName}</td>
                        <td>${scenerie.resProName}</td>
                        <td>${scenerie.proContainCount}张</td>
                        <td>
                          <select id="playDate" class="useStartTime playDate" style="border: 1px solid #eee;height: 26px;width: 100%;color: #666;">
                            <option>请选择日期</option>
                          </select>
                        </td>
                        <td style="display: none;" class="useDay">${scenerie.useDay}</td>
                        <td>
                        </td>
                      </tr>
                    </c:if>
                  </c:forEach>
                </tbody>
              </table>
            	</c:if>
            </div>
          </div>
          <!-- 联系人信息 -->
          <div class="person_info">
            <h3>联系人信息</h3>
            <ul class="personform_info concat_person" id="concat_person">
              <c:if test="${not empty userConcats}">
                <li class="info_line clearfix">
                  <ol>
                    <li class="title">常用联系人：</li>
                    <c:forEach var="userConcat" items="${userConcats}" varStatus="status">
                      <li class="mr24" data_name="${userConcat.name}" data_mobile="${userConcat.mobile}" data_email="${userConcat.email}" onlyid="${userConcat.id}">
                          <label><input type="radio" name="contat" value="${userConcat.id}">${userConcat.name }</label>
                       </li>
                    </c:forEach>
                    <li><label><input type="radio" checked="checked" name="contat" value="new">新建联系人</label></li>
                  </ol>
                </li>
              </c:if>
              <c:if test="${empty userConcats}">
                <li class="info_line clearfix">
                  <ol>
                    <li class="title">常用联系人：</li>
                    <li><span><input type="radio" checked="checked" name="" value="new">新建联系人</span></li>
                  </ol>
                </li>
              </c:if>
              <li class="info_line clearfix">
                <ol>
                  <li class="title required">姓名：</li>
                  <li>
                    <span><input type="text" name="" class="userNname name" placeholder="请输入联系人姓名" maxlength="6"></span>
                    <span class="error errorName"></span>
                  </li>
                </ol>
              </li>
              <li class="info_line clearfix">
                <ol>
                  <li class="title required">手机号：</li>
                  <li>
                    <span><input type="text" name="" class="userMobile mobile" placeholder="请输入手机号码" onkeyup="value=value.replace(/[^1\d{10}]/,'')" maxlength="11"></span>
                    <span class="tips">预订成功后会向您发送短信通知。</span>
                    <span class="error errorPhone"></span>
                  </li>
                </ol>
              </li>
              <li class="info_line clearfix">
                <ol>
                  <li class="title">Email：</li>
                  <li>
                    <span><input type="text" name="" value="" class="userEmail email" placeholder="请填写正确的Email地址"></span>
                    <span class="error errorPhone"></span>
                  </li>
                </ol>
              </li>
            </ul>
          </div>

          <!-- 出行人信息 -->
           <div class="person_info trip_person_info">
            <h3>出行人信息</h3>

             <ul class="personform_info_title">
               <li class="info_line clearfix">
                 <ol>
                   <li class="title">常用出行人：</li>
                   <c:forEach var="userTraveler" items="${userTravelers}" varStatus="status">
                     <li class="mr24" data_name="${userTraveler.name}" data_mobile="${userTraveler.mobile}" data_email="${userTraveler.email}" data_certifi="${userTraveler.certificateCode}" onlyid="${userTraveler.id}" >
                       <span><input type="checkbox" name="" value="">${userTraveler.name}</span>
                     </li>
                   </c:forEach>
                 </ol>
               </li>
             </ul>
            <a href="javascript:" class="newperson">新增出行人</a>
          </div>
        </div>
        <div class="confirm">
          <p class="handle">应付金额：<span class="money"><i>¥</i><i class="orderTotalAmount">${productPackagePrice.tcDirectPrice}</i></span><a id="order_submit_pay">同意以下条款，立即支付</a></p>

        </div>

        <p class="deal_confirm"><input type="checkbox" name="" value="" checked="true" id="deal_confirm"> 我已阅读并同意 《委托服务协议书使用说明》</p>
        <div class="deal_content">
          <h6>预订须知：</h6>
          1.国家法定节假日，特殊节日（情人节，七夕），特殊活动产品，逾期出游，一经付款概不支持退改。</br>
          2.未确认订单出现变价，无房，套餐内容更改等均不做任何赔偿。</br>
          3.如遇天气，自然灾害，景点项目违约等因素无法出游，我司不予退款及补偿。</br>
          4.请您在预订时务必提供准确、完整的信息（姓名、性别、证件号码、联系方式、是否成人或儿童等），以免产生预订错误，影响出行。如因客人提供错误个人信息而造成损失，我司不承担任何责任。</br>
          5.请保持手机畅通，房间确认后以短信的形式通知，景区入凭证也以短信的形式发送到您手机上。</br>
          6.出行期间请随身携带本人的有效身份证原件，未满16周岁者请携带户口本原件；超过16周岁的游客若没有办理身份证，请在户口所在地开出相关身份证明，以免影响酒店入住。出行前请务必检查自己证件的有效期。</br>
          7.酒店入住：请报预订时填写的实住人姓名，如自身原因未能正常入住，责任自付。</br>
          8.游客入住酒店后如需升级或更换房型，需自费其中差价。</br>
          9.如出现单房差，费用自理。</br>
          10.周边游线路产品，已将相关景点，酒店进行优惠打包，因此不可重复享受优惠（如特殊证件，优惠门票等）。</br>
          11.景区入园：提供短信二维码，辅助码或身份证入园。</br>
          12.自由行期间请您做好自身防范措施，自由行期间对自身造成的损害，损伤，损失，我司不承担任何责任。</br></br>
          <h6>费用不包含：</h6>
          1.费用不包含：旅游人身意外保险.</br>
          2.费用不包含：酒店，景区自费项目。</br>
          3.费用不包含：交通费用。</br></br>
          <h6>退改说明</h6>
          以线路预订须知中的退改说明为准。</br></br>
          <h6>注意事项：</h6>
          1. 老人、未成年人、孕妇或有心脏病、高血压、呼吸系统等疾病病史者出游，建议征得医生意见，或经家属同意，或由家属陪伴为妥。</br>
          2. 潜水、游泳、高速摩托艇、攀岩、蹦极、滑雪、高原旅行等为高危娱乐，在充分考虑到自身条件后才可自愿参加.</br>
          3.出行人应尊重目的地城市的法律法规、风俗习惯、文化传统和宗教信仰，爱护旅游资源，保护生态环境，遵守文明行为规范，不参与色情、赌博和涉毒等活动。若违反，后果自负。</br>
        </div>
      </div>
      <div class="fee">
        <h3>费用信息</h3><!-- /header -->
        <div class="fee_content">
          <p>
            <span class="lab">商品单价</span>
            <span class="val fontmaincolor" id="singleprice">¥${productPackagePrice.tcDirectPrice}</span>
          </p>
          <p>
            <span class="lab">预定份数</span>
            <span class="val buyCount">1</span>
          </p>
          <p class="total_sum">
            <span class="lab">订单总额</span>
            <span class="val fontmaincolor money">¥<i class="orderTotalAmount">${productPackagePrice.tcDirectPrice}</i></span>
          </p>
          <p class="invoice">
            <i></i>
            如需开发票，请致电客服咨询</br>　&nbsp;&nbsp;&nbsp;客服电话：0512-80837557</p>

        </div>
      </div>
    </div>
    <a href="javascript:;" class="gome_flights_gotop">回顶部</a>
    <c:choose>
      <c:when test="${_env == 'dev'}">
        <!-- 尾部begin -->
        <div id="gome-foot-new" class="clearfix">
          <div id="gome-foot-new-body">
            <div class="gome-foot-slogan">
              <a data-code="1000048306" href="http://lvxing.gome.com.cn/" target="_blank"><img src="http://img12.gomein.net.cn/image/prodimg/topics/liubing-ds1/201603/18/20160318103416.jpg"></a>
            </div>
            <div class="gome-foot-help clearfix">
              <dl>
                <dt>机票预订帮助</dt>
                <dd><ul>
                  <li><a data-code="1000049073-0" href="http://help.gome.com.cn/question/252.html#lc" target="_blank" rel="nofollow">机票预订流程</a></li>
                  <li><a data-code="1000049073-1" href="http://help.gome.com.cn/question/252.html#xz" target="_blank" rel="nofollow">购票需知</a></li>
                  <li><a data-code="1000049073-2" href="http://help.gome.com.cn/question/252.html#wt" target="_blank" rel="nofollow">预定常见问题</a></li>
                  <li><a data-code="1000049073-3" href="http://help.gome.com.cn/question/252.html#sm" target="_blank" rel="nofollow">退票及改签说明</a></li>
                  <li><a data-code="1000049073-4" href="http://help.gome.com.cn/question/252.html#pz" target="_blank" rel="nofollow">行程单/发票/报销凭证</a></li>
                </ul></dd>
              </dl>
              <dl>
                <dt>酒店预订帮助</dt>
                <dd><ul>
                  <li><a data-code="1000049076-0" href="http://help.gome.com.cn/question/335.html#yd" target="_blank" rel="nofollow">如何预订酒店</a></li>
                  <li><a data-code="1000049076-1" href="http://help.gome.com.cn/question/335.html#jd" target="_blank" rel="nofollow">如何找到合适的酒店</a></li>
                  <li><a data-code="1000049076-2" href="http://help.gome.com.cn/question/335.html#rz" target="_blank" rel="nofollow">如何办理入住</a></li>
                  <li><a data-code="1000049076-3" href="http://help.gome.com.cn/question/335.html#db" target="_blank" rel="nofollow">信用卡担保问题说明</a></li>
                  <li><a data-code="1000049076-4" href="http://help.gome.com.cn/question/335.html#sm" target="_blank" rel="nofollow">常见问题说明</a></li>
                </ul></dd>
              </dl>
              <dl>
                <dt>火车票预订帮助</dt>
                <dd><ul>
                  <li><a data-code="1000049079-0" href="http://help.gome.com.cn/question/401.html#lc" target="_blank" rel="nofollow">火车票预订流程</a></li>
                  <li><a data-code="1000049079-1" href="http://help.gome.com.cn/question/401.html#xz" target="_blank" rel="nofollow">购票需知</a></li>
                  <li><a data-code="1000049079-2" href="http://help.gome.com.cn/question/401.html#bx" target="_blank" rel="nofollow">保险常见问题</a></li>
                  <li><a data-code="1000049079-3" href="http://help.gome.com.cn/question/401.html#yd" target="_blank" rel="nofollow">预定常见问题</a></li>
                  <li><a data-code="1000049079-4" href="http://help.gome.com.cn/question/401.html#tp" target="_blank" rel="nofollow">退票及退款说明</a></li>
                </ul></dd>
              </dl>
              <dl>
                <dt>会员专区</dt>
                <dd><ul>
                  <li><a data-code="1000049082-0" href="http://help.gome.com.cn/question/26.html" target="_blank" rel="nofollow">会员介绍</a></li>
                  <li><a data-code="1000049082-1" href="http://help.gome.com.cn/question/23.html" target="_blank" rel="nofollow">积分说明</a></li>
                  <li><a data-code="1000049082-2" href="http://help.gome.com.cn/question/27.html" target="_blank" rel="nofollow">优惠券说明</a></li>
                  <li><a data-code="1000049082-3" href="http://help.gome.com.cn/question/20.html" target="_blank" rel="nofollow">电子券</a></li>
                  <li><a data-code="1000049082-4" href="http://help.gome.com.cn/question/42.html" target="_blank" rel="nofollow">国美社区</a></li>
                </ul></dd>
              </dl>
              <dl>
                <dt>国美在线</dt>
                <dd><ul>
                  <li><a data-code="1000049085-0" href="http://help.gome.com.cn/about/company.html" target="_blank" rel="nofollow">关于国美在线</a></li>
                  <li><a data-code="1000049085-1" href="http://www.gome.com.cn/join/" target="_blank" rel="nofollow">商家入住</a></li>
                  <li><a data-code="1000049085-2" href="http://cps.gome.com.cn/" target="_blank" rel="nofollow">销售联盟</a></li>
                  <li><a data-code="1000049085-3" href="http://help.gome.com.cn/question/21.html" target="_blank" rel="nofollow">联系我们</a></li>
                  <li><a data-code="1000049085-4" href="http://www.gome.com.cn/links/" target="_blank" rel="nofollow">友情链接</a></li>
                </ul></dd>
              </dl>
            </div>
            <div class="gome-foot-code2">
              <i><a data-code="1000048307" href="http://shouji.gome.com.cn/?intcmp=sy-1000043112-0" target="_blank"><img src="http://img13.gomein.net.cn/image/prodimg/topics/liubing-ds1/201604/5/20160405183749.jpg"></a></i>
              <p>扫描下载客户端</p>
            </div>
          </div>
        </div>
        <div id="copyright">
          <p>
            <a href="http://www.bj.cyberpolice.cn/index.do" rel="nofollow" target="_blank">朝阳网络警察</a>
            <a href="http://search.cxwz.org/cert/l/CX20111018000607000618" rel="nofollow" target="_blank">诚信网站</a>
            <a href="https://ss.knet.cn/verifyseal.dll?sn=2011071100100011373&amp;comefrom=trust" rel="nofollow" target="_blank">可信网站信用评价</a>
            <a href="http://www.sgs.gov.cn/lz/licenseLink.do?method=licenceView&amp;entyId=20111216142637606" rel="nofollow" target="_blank">经营性网站备案信息</a>
            ©2000-2014 国美在线电子商务有限公司 版权所有 沪ICP备11009419号 沪B2-20120004号
          </p>
        </div>
      </c:when>
      <c:otherwise>
        <!-- 尾部begin -->
        <!--#include virtual="/n/common/b03/foot_new.html"-->
        <!-- 尾部end -->
      </c:otherwise>
    </c:choose>

    <div class="modal-wapper clearfix" id="alert">
      <a href="javascript:" class="close_modal"></a>
      <p class="modal-text"></p>
      <p class="btns">
        <a href="javascript:void(0)" class="btnsConfirm">确定</a>
      </p>
    </div>
    <!-- confirm弹框 -->
    <div class="modal-wapper clearfix" id="confirm">
      <a href="javascript:" class="close_modal"></a>
      <p class="modal-text"></p>
      <p class="btns">
        <a href="javascript:void(0)" class="btnsConfirm">确定</a>
        <a href="javascript:void(0)" class="btnsCancel">取消</a>
      </p>
    </div>

    <div class="mask_boxs" id="mask_boxs">
      <div class="masks"></div>
      <div id="mcon">
      </div>
    </div>

    <c:choose>
      <c:when test="${_env == 'dev'}">
        <script src="http://js.gomein.net.cn/gmlib/jq/1.7.1/jquery.js" type="text/javascript"></script>
        <script src="http://js.gomein.net.cn/gmlib/cookie/1.0.0/cookie.js" type="text/javascript"></script>
        <%--        <script src="http://js.gomein.net.cn/gmpro/1.0.0/public/1.0.0/js/signtop.min.js" type="text/javascript"></script>
                <script src="http://js.gomein.net.cn/gmlib/unit/bigcode/1.0.0/bigcode.min.js" type="text/javascript"></script>--%>
        <script type="text/javascript" src="http://js.gomein.net.cn/??/f2ejs/g/jsConfig.min.js,/f2ejs/g/jsCssImport.min.js?v=20150041002500060041"></script>
        <script>
          //$("#topnav").html(topHtml.con);
          g$("aaaaa","/js/g/jquery/jquery.cookie,/js/g/ui/live800_gome,/js/n/firstPage/ghover",function(){
            $("#topNavHover").ghover({
              addcss : "tophover"
            });
            $(".js-tophover").ghover( {
              addcss : "hover"
            });
            $(".triggerLive800").click(function () {
              //jqueryM的trigger在老版的webkit下支持的不好
              //所以webkit下 用原生js
              var isWebKit = window.navigator.userAgent.indexOf('WebKit') > 0;
              if (isWebKit) {
                var ev = document.createEvent('HTMLEvents');
                ev.initEvent('click', false, true);
                $(".live800_gome").get(0).dispatchEvent(ev);
              }
              else {
                $(".live800_gome").trigger("click");
              }
            });
            /*自定义方法*/
          });
          $(window).scroll(function (){
            if($(window).scrollTop()>0){
              $(".gome_flights_gotop").show();
            }else{
              $(".gome_flights_gotop").hide();
            };
          });
        </script>
      </c:when>
      <c:otherwise>
        <script src="http://js.${_resource_env}.net.cn/??<!--#include virtual='/n/common/b03/js.html'-->"></script>
      </c:otherwise>
    </c:choose>
    <script type="text/javascript" src="${_domain}/js/common.js,/js/indexGet.js,/tpl/build/template.js,/js/confirm.override.js,/js/commonbase.js?_v=${_v}"></script>
    <script type="text/javascript" src="http://js.${_resource_env}.net.cn/gmlib/unit/scode/1.0.0/scode.js"></script>
    <script type="text/javascript" src="http://js.${_resource_env}.net.cn/f2ejs/common/bigData.min.js"></script>
    <script type="text/javascript">

      //返回顶部
      $(".gome_flights_gotop").click(function (){
        $('html,body').animate({scrollTop: '0px'}, 100);
      });
      s.pageName="购物流程:度假周边游:预定填写页";
      s.channel="度假周边游";
      s.prop1="购物流程:度假周边游";
      s.prop2="购物流程:度假周边游:预定填写页";
      s.prop3="购物流程:度假周边游:预定填写页";
      s.prop4="购物流程:度假周边游";
      s.events="scCheckout";
      s.products=";${productPackage.productId};;;;eVar29=度假周边游";
      var s_code=s.t();
      if(s_code)document.write(s_code);
    </script>
    <!--server ip is <%=request.getLocalAddr()%> -->
    <script type="text/javascript" src="${_domain}/js/order_confirm.js,/lib/zlDate.js?_v=${_v}" ></script>

  </body>
</html>